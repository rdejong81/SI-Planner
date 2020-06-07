package MySQL;

import Data.Attribute;
import Data.DaoResult;
import Data.Employee;
import Planning.Planning;
import Projects.ProjectTask;
import Sql.QueryResult;
import Timeregistration.ITimeregistrationDAO;
import Timeregistration.Timeregistration;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Timeregistration Data Acccess Object
 * @author Richard de Jong
 * @see ITimeregistrationDAO
 */
public class TimeregistrationMySQLDAO implements ITimeregistrationDAO
{
    private MySQLConnection mySQLConnection;
    private ArrayList<Timeregistration> timeregistrationInstances;  // list of instances of Timeregistration to prevent duplicate objects of the same database id.
    private ArrayList<Timeregistration> timeregistrationsUpdating;  // list of Timeregistration instances that are being updated.

    protected TimeregistrationMySQLDAO(MySQLConnection mySQLConnection)
    {
        this.mySQLConnection = mySQLConnection;
        timeregistrationInstances = new ArrayList<>();
        timeregistrationsUpdating = new ArrayList<>();
    }

    private Timeregistration processRow(HashMap<String,Object> row)
    {
        Timeregistration timeregistration=null;
        for(Timeregistration currentTimeregistration : timeregistrationInstances)
        {
            if(currentTimeregistration.getId() == (Integer)row.get("id"))
            {
                timeregistrationsUpdating.add(currentTimeregistration);
                currentTimeregistration.setStart((LocalDateTime) row.get("start"));
                currentTimeregistration.setEnd((LocalDateTime) row.get("end"));
                currentTimeregistration.setSynced((Boolean) row.get("synced"));
                currentTimeregistration.setProjectTask(mySQLConnection.taskDao().findById((Integer)row.get("tasks_id")));
                currentTimeregistration.setEmployee(mySQLConnection.employeeDao().findById((Integer)row.get("employeesid")));
                timeregistration = currentTimeregistration;
            }
        }
        if(timeregistration == null)
        {
            ProjectTask projectTask = mySQLConnection.taskDao().findById((Integer)row.get("tasks_id"));
            Employee employee = mySQLConnection.employeeDao().findById((Integer)row.get("employeesid"));
            timeregistration = new Timeregistration(this,(Integer) row.get("id"),
                    (Boolean) row.get("synced"),
                    (LocalDateTime) row.get("start"),
                    (LocalDateTime) row.get("end"),
                    projectTask,
                    employee
            );
            timeregistrationInstances.add(timeregistration);
            timeregistrationsUpdating.add(timeregistration);
        }

        // find attributes linked to project.
        for(Attribute attribute : mySQLConnection.attributeDao().findAll(timeregistration))
        {
            timeregistration.addAttribute(attribute);
        }

        timeregistrationsUpdating.remove(timeregistration);
        return timeregistration;
    }

    @Override
    public List<Timeregistration> findAll()
    {
        QueryResult result = new QueryResult(mySQLConnection, "select * from time where planned=0");

        for(HashMap<String,Object> row : result.getRows())
        {
            processRow(row);
        }

        return Collections.unmodifiableList(timeregistrationInstances);
    }

    @Override
    public Timeregistration findById(int id)
    {
        for(Timeregistration timeregistration : timeregistrationInstances)
        {
            if(timeregistration.getId() == id) return timeregistration;
        }

        QueryResult result = new QueryResult(mySQLConnection, String.format("select * from time where id=%d and planned=0",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertTimeregistration(Timeregistration timeregistration)
    {
        QueryResult result = new QueryResult(mySQLConnection,String.format(
                "insert into time (start,end,planned,synced,tasks_id,employeesid) values ('%s','%s',0,%d,%d,%d)",
                timeregistration.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                timeregistration.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                timeregistration.isSynced() ? 1 : 0,
                timeregistration.getProjectTask().getId(),
                timeregistration.getEmployee().getId()
        ));
        timeregistration.setId((int)result.getCreatedKey());
        timeregistrationInstances.add(timeregistration);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateTimeregistration(Timeregistration timeregistration)
    {
        if(timeregistrationsUpdating.contains(timeregistration)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(mySQLConnection,String.format(
                "update time set start='%s',end='%s',planned=0,synced=%d,tasks_id=%d,employeesid=%d where id=%d",
                timeregistration.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                timeregistration.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                timeregistration.isSynced() ? 1 : 0,
                timeregistration.getProjectTask().getId(),
                timeregistration.getEmployee().getId(),
                timeregistration.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteTimeregistration(Timeregistration timeregistration)
    {
        if(timeregistrationsUpdating.contains(timeregistration)) return DaoResult.OP_OK; //already updating

        QueryResult result = new QueryResult(mySQLConnection,String.format("delete from time where id=%d", timeregistration.getId()));
        if(result.getLastError() == null)
        {
            timeregistration.getProjectTask().removeTimeregistration(timeregistration);
            timeregistrationInstances.remove(timeregistration);
            return DaoResult.OP_OK;
        }
        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }


}
