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
import Data.Customer;
import Data.DaoResult;
import Data.ICustomerDAO;
import Projects.IProjectDAO;
import Projects.Project;
import Projects.ProjectTask;
import Sql.QueryResult;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Project Data Acccess Object
 * @author Richard de Jong
 * @see ICustomerDAO
 */
public class ProjectSqliteDAO implements IProjectDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<Project> projectInstances;  // list of instances of Project to prevent duplicate objects of the same database id.
    private final ArrayList<Project> projectsUpdating;  // list of Project instances that are being updated.

    public ProjectSqliteDAO(SqliteConnection sqliteConnection)
    {
        this.sqliteConnection = sqliteConnection;
        projectInstances = new ArrayList<>();
        projectsUpdating = new ArrayList<>();
    }

    private Project processRow(HashMap<String,Object> row)
    {
        Project project =null;
        for(Project currentProject : projectInstances)
        {
            if(currentProject.getId() == (Integer)row.get("id"))
            {
                projectsUpdating.add(currentProject);
                currentProject.setName((String)row.get("name"));
                currentProject.setShortName((String)row.get("shortCode"));
                currentProject.setColor((Integer)row.get("color"));
                currentProject.setInvoice((Boolean) row.get("timesheetNeeded"));
                currentProject.setCustomer(sqliteConnection.customerDao().findById((Integer)row.get("customer_id")));
                project=currentProject;
            }
        }
        if(project == null)
        {
            Customer customer = sqliteConnection.customerDao().findById((Integer)row.get("customer_id"));
            project = new Project(this,(Integer) row.get("id"),
                    (String) row.get("name"),
                    (Integer) row.get("color"),
                    (Boolean) row.get("timesheetNeeded"),
                    (String) row.get("shortCode"),
                    customer
            );
            projectInstances.add(project);
            projectsUpdating.add(project);
        }

        // find tasks belonging to project
        QueryResult childResults = new QueryResult(sqliteConnection, String.format("select id from tasks where projects_id=%d",
                project.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            ProjectTask projectTask = sqliteConnection.taskDao().findById((Integer)childRow.get("id"));
            if(projectTask != null && !project.getProjectTasks().contains(projectTask))
                project.addTask(projectTask);
        }

        // find attributes linked to project.
        for(Attribute attribute : sqliteConnection.attributeDao().findAll(project))
        {
            project.addAttribute(attribute);
        }

        projectsUpdating.remove(project);
        return project;
    }

    @Override
    public List<Project> findAll()
    {
        QueryResult result = new QueryResult(sqliteConnection, "select * from projects");

        for(HashMap<String,Object> row : result.getRows())
        {
            processRow(row);
        }

        return Collections.unmodifiableList(projectInstances);
    }

    @Override
    public Project findById(int id)
    {
        for(Project project : projectInstances)
        {
            if(project.getId() == id) return project;
        }

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from projects where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertProject(Project project)
    {
        QueryResult result = new QueryResult(sqliteConnection,String.format(
                "insert into projects (name,shortCode,color,timesheetNeeded,customer_id) values ('%s','%s',%d,%d,%d)",
                project.getName().replace("'","%%%"),
                project.getShortName().replace("'","%%%"),
                project.getColor(),
                project.isInvoice() ? 1 : 0,
                project.getCustomer().getId()
        ));
        project.setId((int)result.getCreatedKey());
        projectInstances.add(project);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateProject(Project project)
    {
        if(projectsUpdating.contains(project)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format("update projects set name='%s',shortCode='%s',color=%d,timesheetNeeded=%d,customer_id=%d where id=%d",
                project.getName().replace("'","%%%"),
                project.getShortName().replace("'","%%%"),
                project.getColor(),
                project.isInvoice() ? 1 : 0,
                project.getCustomer().getId(),
                project.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteProject(Project project)
    {
        if(projectsUpdating.contains(project)) return DaoResult.OP_OK; //already updating

        // delete all owned tasks
        for(ProjectTask projectTask : new ArrayList<>(project.getProjectTasks()))
        {
            sqliteConnection.taskDao().deleteTask(projectTask);
        }

        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from projects where id=%d",project.getId()));
        if(result.getLastError() == null)
        {
            project.getCustomer().removeProject(project);
            projectInstances.remove(project);
            return DaoResult.OP_OK;
        }
        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }


}
