/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package Sqlite;

import Data.Attribute;
import Data.DaoResult;
import Data.Employee;
import Projects.ProjectTask;
import Sql.QueryResult;
import Timeregistration.ITimeregistrationDAO;
import Timeregistration.Timeregistration;

import java.sql.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Timeregistration Data Acccess Object
 * @author Richard de Jong
 * @see ITimeregistrationDAO
 */
public class TimeregistrationSqliteDAO implements ITimeregistrationDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<Timeregistration> timeregistrationInstances;  // list of instances of Timeregistration to prevent duplicate objects of the same database id.
    private final ArrayList<Timeregistration> timeregistrationsUpdating;  // list of Timeregistration instances that are being updated.

    public TimeregistrationSqliteDAO(SqliteConnection sqliteConnection)
    {
        this.sqliteConnection = sqliteConnection;
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
                currentTimeregistration.setStart(
                        ZonedDateTime.ofLocal((LocalDateTime)row.get("start"),ZoneId.of("UTC"),null));
                currentTimeregistration.setEnd(
                        ZonedDateTime.ofLocal((LocalDateTime)row.get("end"),ZoneId.of("UTC"),null));
                currentTimeregistration.setSynckey((String) row.get("synckey"));
                currentTimeregistration.setSynced((Boolean) row.get("synced"));
                currentTimeregistration.setProjectTask(sqliteConnection.taskDao().findById((Integer)row.get("tasks_id")));
                currentTimeregistration.setEmployee(sqliteConnection.employeeDao().findById((Integer)row.get("employeesid")));
                timeregistration = currentTimeregistration;
            }
        }
        if(timeregistration == null)
        {
            ProjectTask projectTask = sqliteConnection.taskDao().findById((Integer)row.get("tasks_id"));
            Employee employee = sqliteConnection.employeeDao().findById((Integer)row.get("employeesid"));
            timeregistration = new Timeregistration(this,(Integer) row.get("id"),
                    (Boolean) row.get("synced"),
                    ZonedDateTime.ofLocal((LocalDateTime)row.get("start"),ZoneId.of("UTC"),null),
                    ZonedDateTime.ofLocal((LocalDateTime)row.get("end"),ZoneId.of("UTC"),null),
                    projectTask,
                    employee,
                    (String) row.get("synckey")
            );
            timeregistrationInstances.add(timeregistration);
            timeregistrationsUpdating.add(timeregistration);
        }

        // find attributes linked to project.
        for(Attribute attribute : sqliteConnection.attributeDao().findAll(timeregistration))
        {
            timeregistration.addAttribute(attribute);
        }

        timeregistrationsUpdating.remove(timeregistration);
        return timeregistration;
    }

    @Override
    public List<Timeregistration> findAll()
    {
        QueryResult result = new QueryResult(sqliteConnection, "select * from time where planned=0");

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

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from time where id=%d and planned=0",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertTimeregistration(Timeregistration timeregistration)
    {
        PreparedStatement preparedStatement;
        long createdKey=-1;
        try
        {
            preparedStatement = sqliteConnection.getConnection().prepareStatement(
                    "insert into time (start,end,planned,synced,tasks_id,employeesid,synckey) values (?,?,0,?,?,?,?)");

            preparedStatement.setTimestamp(1,new Timestamp(
                    Instant.from(timeregistration.getStart()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(timeregistration.getStart().getZone()))
            );
            preparedStatement.setTimestamp(2,new Timestamp(
                    Instant.from(
                    timeregistration.getEnd()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(timeregistration.getEnd().getZone())));
            preparedStatement.setInt(3,timeregistration.isSynced() ? 1 : 0);
            preparedStatement.setInt(4,timeregistration.getProjectTask().getId());
            preparedStatement.setInt(5,timeregistration.getEmployee().getId());
            preparedStatement.setString(6,timeregistration.getSynckey());

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



        /*QueryResult result = new QueryResult(sqliteConnection,String.format(
                "insert into time (start,end,planned,synced,tasks_id,employeesid,synckey) values ('%s','%s',0,%d,%d,%d,'%s')",
                timeregistration.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                timeregistration.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                timeregistration.isSynced() ? 1 : 0,
                timeregistration.getProjectTask().getId(),
                timeregistration.getEmployee().getId(),
                timeregistration.getSynckey()
        ));
        timeregistration.setId((int)result.getCreatedKey());*/
        timeregistration.setId((int)createdKey);
        timeregistrationInstances.add(timeregistration);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateTimeregistration(Timeregistration timeregistration)
    {
        PreparedStatement preparedStatement;

        if (timeregistrationsUpdating.contains(timeregistration)) return DaoResult.OP_OK; //already updating

        try
        {
            preparedStatement = sqliteConnection.getConnection().prepareStatement(
                    "update time set start=?,end=?,planned=0,synced=?,tasks_id=?,employeesid=?,synckey=? where id=?");

            preparedStatement.setTimestamp(1,
                    new Timestamp(
                            Instant.from(timeregistration.getStart()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(timeregistration.getStart().getZone()))
            );
            preparedStatement.setTimestamp(2,
                    new Timestamp(
                            Instant.from(timeregistration.getEnd()).toEpochMilli()),
                    Calendar.getInstance(TimeZone.getTimeZone(timeregistration.getEnd().getZone()))
            );
            preparedStatement.setInt(3,timeregistration.isSynced() ? 1 : 0);
            preparedStatement.setInt(4,timeregistration.getProjectTask().getId());
            preparedStatement.setInt(5,timeregistration.getEmployee().getId());
            preparedStatement.setString(6,timeregistration.getSynckey());
            preparedStatement.setInt(7,timeregistration.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e)
        {
            return DaoResult.DAO_MISSING;
        }
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteTimeregistration(Timeregistration timeregistration)
    {
        if(timeregistrationsUpdating.contains(timeregistration)) return DaoResult.OP_OK; //already updating

        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from time where id=%d", timeregistration.getId()));
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
