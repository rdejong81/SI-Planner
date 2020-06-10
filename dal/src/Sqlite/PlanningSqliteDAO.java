package Sqlite;

import Data.Attribute;
import Data.DaoResult;
import Data.Employee;
import Planning.IPlanningDAO;
import Planning.Planning;
import Projects.ProjectTask;
import Sql.QueryResult;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Project Data Acccess Object
 * @author Richard de Jong
 * @see IPlanningDAO
 */
public class PlanningSqliteDAO implements IPlanningDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<Planning> planningInstances;  // list of instances of Planning to prevent duplicate objects of the same database id.
    private final ArrayList<Planning> planningsUpdating;  // list of Planning instances that are being updated.

    public PlanningSqliteDAO(SqliteConnection sqliteConnection)
    {
        this.sqliteConnection = sqliteConnection;
        planningInstances = new ArrayList<>();
        planningsUpdating = new ArrayList<>();
    }

    private Planning processRow(HashMap<String,Object> row)
    {
        Planning planning=null;
        for(Planning currentPlanning : planningInstances)
        {
            if(currentPlanning.getId() == (Integer)row.get("id"))
            {
                planningsUpdating.add(currentPlanning);
                currentPlanning.setStart(
                        LocalDateTime.parse((String)row.get("start"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                currentPlanning.setEnd(
                        LocalDateTime.parse((String)row.get("end"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                currentPlanning.setSynced((Boolean) row.get("synced"));
                currentPlanning.setProjectTask(sqliteConnection.taskDao().findById((Integer)row.get("tasks_id")));
                currentPlanning.setEmployee(sqliteConnection.employeeDao().findById((Integer)row.get("employeesid")));
                planning = currentPlanning;
            }
        }
        if(planning == null)
        {
            ProjectTask projectTask = sqliteConnection.taskDao().findById((Integer)row.get("tasks_id"));
            Employee employee = sqliteConnection.employeeDao().findById((Integer)row.get("employeesid"));
            planning = new Planning(this,(Integer) row.get("id"),
                    (Boolean) row.get("synced"),
                    LocalDateTime.parse((String)row.get("start"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    LocalDateTime.parse((String)row.get("end"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    projectTask,
                    employee
            );
            planningInstances.add(planning);
            planningsUpdating.add(planning);
        }

        // find attributes linked to project.
        for(Attribute attribute : sqliteConnection.attributeDao().findAll(planning))
        {
            planning.addAttribute(attribute);
        }

        planningsUpdating.remove(planning);
        return planning;
    }

    @Override
    public List<Planning> findAll()
    {
        QueryResult result = new QueryResult(sqliteConnection, "select * from time where planned=1");

        for(HashMap<String,Object> row : result.getRows())
        {
            processRow(row);
        }

        return Collections.unmodifiableList(planningInstances);
    }

    @Override
    public Planning findById(int id)
    {
        for(Planning planning : planningInstances)
        {
            if(planning.getId() == id) return planning;
        }

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from time where id=%d and planned=1",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertPlanning(Planning planning)
    {
        QueryResult result = new QueryResult(sqliteConnection,String.format(
                "insert into time (start,end,planned,synced,tasks_id,employeesid) values ('%s','%s',1,%d,%d,%d)",
                planning.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.isSynced() ? 1 : 0,
                planning.getProjectTask().getId(),
                planning.getEmployee().getId()
        ));
        planning.setId((int)result.getCreatedKey());
        planningInstances.add(planning);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updatePlanning(Planning planning)
    {
        if(planningsUpdating.contains(planning)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format(
                "update time set start='%s',end='%s',planned=1,synced=%d,tasks_id=%d,employeesid=%d where id=%d",
                planning.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.isSynced() ? 1 : 0,
                planning.getProjectTask().getId(),
                planning.getEmployee().getId(),
                planning.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deletePlanning(Planning planning)
    {
        if(planningsUpdating.contains(planning)) return DaoResult.OP_OK; //already updating

        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from time where id=%d", planning.getId()));
        if(result.getLastError() == null)
        {
            planning.getProjectTask().removePlanning(planning);
            planningInstances.remove(planning);
            return DaoResult.OP_OK;
        }
        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }


}
