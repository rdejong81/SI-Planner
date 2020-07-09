package Sqlite;

import Data.Attribute;
import Data.DaoResult;
import Data.Employee;
import Planning.IPlanningDAO;
import Planning.Planning;
import Projects.ProjectTask;
import Sql.QueryResult;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
                        ZonedDateTime.ofLocal((LocalDateTime)row.get("start"), ZoneId.of("UTC"),null));
                currentPlanning.setEnd(
                        ZonedDateTime.ofLocal((LocalDateTime)row.get("end"),ZoneId.of("UTC"),null));
                currentPlanning.setSynced((Boolean) row.get("synced"));
                currentPlanning.setProjectTask(sqliteConnection.taskDao().findById((Integer)row.get("tasks_id")));
                currentPlanning.setEmployee(sqliteConnection.employeeDao().findById((Integer)row.get("employeesid")));
                currentPlanning.setSynckey((String)row.get("synckey"));
                planning = currentPlanning;
            }
        }
        if(planning == null)
        {
            ProjectTask projectTask = sqliteConnection.taskDao().findById((Integer)row.get("tasks_id"));
            Employee employee = sqliteConnection.employeeDao().findById((Integer)row.get("employeesid"));
            planning = new Planning(this,(Integer) row.get("id"),
                    (Boolean) row.get("synced"),
                    ZonedDateTime.ofLocal((LocalDateTime)row.get("start"), ZoneId.of("UTC"),null),
                    ZonedDateTime.ofLocal((LocalDateTime)row.get("end"),ZoneId.of("UTC"),null),
                    projectTask,
                    employee,
                    (String)row.get("synckey")
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
        PreparedStatement preparedStatement;
        long createdKey=-1;
        try
        {
            preparedStatement = sqliteConnection.getConnection().prepareStatement(
                    "insert into time (start,end,planned,synced,tasks_id,employeesid,synckey) values (?,?,1,?,?,?,?)");

            preparedStatement.setTimestamp(1,new Timestamp(
                            Instant.from(planning.getStart()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(planning.getStart().getZone()))
            );
            preparedStatement.setTimestamp(2,new Timestamp(
                            Instant.from(
                                    planning.getEnd()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(planning.getEnd().getZone())));
            preparedStatement.setInt(3,planning.isSynced() ? 1 : 0);
            preparedStatement.setInt(4,planning.getProjectTask().getId());
            preparedStatement.setInt(5,planning.getEmployee().getId());
            preparedStatement.setString(6,planning.getSynckey());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next())
            { // This is an insert result, get created result id.
                createdKey = resultSet.getLong(1);
            }

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return DaoResult.DAO_MISSING;
        }

        planning.setId((int)createdKey);
        planningInstances.add(planning);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updatePlanning(Planning planning)
    {
        PreparedStatement preparedStatement;

        if(planningsUpdating.contains(planning)) return DaoResult.OP_OK; //already updating

        try
        {
            preparedStatement = sqliteConnection.getConnection().prepareStatement(
                    "update time set start=?,end=?,planned=1,synced=?,tasks_id=?,employeesid=?,synckey=? where id=?");

            preparedStatement.setTimestamp(1,
                    new Timestamp(
                            Instant.from(planning.getStart()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(planning.getStart().getZone()))
            );
            preparedStatement.setTimestamp(2,
                    new Timestamp(
                            Instant.from(planning.getEnd()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(planning.getEnd().getZone()))
            );
            preparedStatement.setInt(3,planning.isSynced() ? 1 : 0);
            preparedStatement.setInt(4,planning.getProjectTask().getId());
            preparedStatement.setInt(5,planning.getEmployee().getId());
            preparedStatement.setString(6,planning.getSynckey());
            preparedStatement.setInt(7,planning.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            return DaoResult.DAO_MISSING;
        }
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
