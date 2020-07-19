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

package MySQL;

import Data.Attribute;
import Data.DaoResult;
import Data.Employee;
import Planning.IPlanningDAO;
import Planning.Planning;
import Projects.ProjectTask;
import Sql.QueryResult;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Project Data Acccess Object
 * @author Richard de Jong
 * @see IPlanningDAO
 */
public class PlanningMySQLDAO implements IPlanningDAO
{
    private final MySQLConnection mySQLConnection;
    private final ArrayList<Planning> planningInstances;  // list of instances of Planning to prevent duplicate objects of the same database id.
    private final ArrayList<Planning> planningsUpdating;  // list of Planning instances that are being updated.

    public PlanningMySQLDAO(MySQLConnection mySQLConnection)
    {
        this.mySQLConnection = mySQLConnection;
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
                currentPlanning.setStart(((LocalDateTime) row.get("start")).atZone(ZoneId.of("UTC")));
                currentPlanning.setEnd(((LocalDateTime) row.get("end")).atZone(ZoneId.of("UTC")));
                currentPlanning.setSynced((Boolean) row.get("synced"));
                currentPlanning.setProjectTask(mySQLConnection.taskDao().findById((Integer)row.get("tasks_id")));
                currentPlanning.setEmployee(mySQLConnection.employeeDao().findById((Integer)row.get("employeesid")));
                currentPlanning.setSynckey((String)row.get("synckey"));
                planning = currentPlanning;
            }
        }
        if(planning == null)
        {
            ProjectTask projectTask = mySQLConnection.taskDao().findById((Integer)row.get("tasks_id"));
            Employee employee = mySQLConnection.employeeDao().findById((Integer)row.get("employeesid"));
            planning = new Planning(this,(Integer) row.get("id"),
                    (Boolean) row.get("synced"),
                    ((LocalDateTime) row.get("start")).atZone(ZoneId.of("UTC")),
                    ((LocalDateTime) row.get("end")).atZone(ZoneId.of("UTC")),
                    projectTask,
                    employee,
                    (String)row.get("synckey")
            );
            planningInstances.add(planning);
            planningsUpdating.add(planning);
        }

        // find attributes linked to project.
        for(Attribute attribute : mySQLConnection.attributeDao().findAll(planning))
        {
            planning.addAttribute(attribute);
        }

        planningsUpdating.remove(planning);
        return planning;
    }

    @Override
    public List<Planning> findAll()
    {
        QueryResult result = new QueryResult(mySQLConnection, "select * from time where planned=1");

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

        QueryResult result = new QueryResult(mySQLConnection, String.format("select * from time where id=%d and planned=1",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertPlanning(Planning planning)
    {
        QueryResult result = new QueryResult(mySQLConnection,String.format(
                "insert into time (start,end,planned,synced,tasks_id,employeesid,synckey) values ('%s','%s',1,%d,%d,%d,'%s')",
                planning.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.isSynced() ? 1 : 0,
                planning.getProjectTask().getId(),
                planning.getEmployee().getId(),
                planning.getSynckey()
        ));
        planning.setId((int)result.getCreatedKey());
        planningInstances.add(planning);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updatePlanning(Planning planning)
    {
        if(planningsUpdating.contains(planning)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(mySQLConnection,String.format(
                "update time set start='%s',end='%s',planned=1,synced=%d,tasks_id=%d,employeesid=%d,synckey='%s' where id=%d",
                planning.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                planning.isSynced() ? 1 : 0,
                planning.getProjectTask().getId(),
                planning.getEmployee().getId(),
                planning.getSynckey(),
                planning.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deletePlanning(Planning planning)
    {
        if(planningsUpdating.contains(planning)) return DaoResult.OP_OK; //already updating

        QueryResult result = new QueryResult(mySQLConnection,String.format("delete from time where id=%d", planning.getId()));
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
