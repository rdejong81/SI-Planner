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

import Data.DaoResult;
import Planning.Planning;
import Projects.IProjectTaskDAO;
import Projects.Project;
import Projects.ProjectTask;
import Sql.QueryResult;
import Timeregistration.Timeregistration;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Project Data Acccess Object
 * @author Richard de Jong
 * @see IProjectTaskDAO
 */
public class ProjectTaskSqliteDAO implements IProjectTaskDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<ProjectTask> projectTaskInstances;  // list of instances of Task to prevent duplicate objects of the same database id.
    private final ArrayList<ProjectTask> tasksUpdating;  // list of Task instances that are being updated.

    public ProjectTaskSqliteDAO(SqliteConnection sqliteConnection)
    {
        this.sqliteConnection = sqliteConnection;
        projectTaskInstances = new ArrayList<>();
        tasksUpdating = new ArrayList<>();
    }

    private ProjectTask processRow(HashMap<String,Object> row)
    {
        ProjectTask projectTask =null;
        for(ProjectTask currentProjectTask : projectTaskInstances)
        {
            if(currentProjectTask.getId() == (Integer)row.get("id"))
            {
                tasksUpdating.add(currentProjectTask);
                currentProjectTask.setName((String)row.get("name"));
                currentProjectTask.setCompleted((Boolean) row.get("completed"));
                currentProjectTask.setProject(sqliteConnection.projectDao().findById((Integer)row.get("projects_id")));
                projectTask = currentProjectTask;
            }
        }
        if(projectTask == null)
        {
            Project project = sqliteConnection.projectDao().findById((Integer)row.get("projects_id"));
            projectTask = new ProjectTask(this,(Integer) row.get("id"),
                    (String) row.get("name"),
                    (Boolean) row.get("completed"),
                    project
            );
            projectTaskInstances.add(projectTask);
            tasksUpdating.add(projectTask);
        }

        // find plannings and timeregistrations belonging to projecttask
        QueryResult childResults = new QueryResult(sqliteConnection, String.format("select id,planned from time where tasks_id=%d",
                projectTask.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            if((Boolean)childRow.get("planned"))
            {
                Planning planning = sqliteConnection.planningDao().findById((Integer)childRow.get("id"));
                if(planning != null && !projectTask.getPlannings().contains(planning))
                    projectTask.addPlanning(planning);
            } else {
                Timeregistration timeregistration = sqliteConnection.timeregistrationDao().findById((Integer)childRow.get("id"));
                if(timeregistration != null && !projectTask.getTimeregistrations().contains(timeregistration))
                    projectTask.addTimeregistration(timeregistration);
            }

        }


        tasksUpdating.remove(projectTask);
        return projectTask;
    }

    @Override
    public List<ProjectTask> findAll()
    {
        QueryResult result = new QueryResult(sqliteConnection, "select * from tasks");

        for(HashMap<String,Object> row : result.getRows())
        {
            processRow(row);
        }

        return Collections.unmodifiableList(projectTaskInstances);
    }

    @Override
    public ProjectTask findById(int id)
    {
        for(ProjectTask projectTask : projectTaskInstances)
        {
            if(projectTask.getId() == id) return projectTask;
        }

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from tasks where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertTask(ProjectTask projectTask)
    {
        QueryResult result = new QueryResult(sqliteConnection,String.format(
                "insert into tasks (name,completed,projects_id) values ('%s',%d,%d)",
                projectTask.getName().replace("'","%%%"),
                projectTask.isCompleted() ? 1 : 0,
                projectTask.getProject().getId()
        ));
        projectTask.setId((int)result.getCreatedKey());
        projectTaskInstances.add(projectTask);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateTask(ProjectTask projectTask)
    {
        if(tasksUpdating.contains(projectTask)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format("update tasks set name='%s',completed=%d,projects_id=%d where id=%d",
                projectTask.getName().replace("'","%%%"),
                projectTask.isCompleted() ? 1 : 0,
                projectTask.getProject().getId(),
                projectTask.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteTask(ProjectTask projectTask)
    {
        if(tasksUpdating.contains(projectTask)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from tasks where id=%d", projectTask.getId()));
        if(result.getLastError() == null)
        {
            projectTask.getProject().removeTask(projectTask);
            projectTaskInstances.remove(projectTask);
            return DaoResult.OP_OK;
        }
        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }


}
