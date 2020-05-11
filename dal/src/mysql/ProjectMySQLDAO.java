package mysql;

import Data.*;
import Projects.ProjectTask;
import db.QueryResult;
import Projects.IProjectDAO;
import Projects.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Project Data Acccess Object
 * @author Richard de Jong
 * @see ICustomerDAO
 */
public class ProjectMySQLDAO implements IProjectDAO
{
    private MySQLConnection mySQLConnection;
    private ArrayList<Project> projectInstances;  // list of instances of Project to prevent duplicate objects of the same database id.
    private ArrayList<Project> projectsUpdating;  // list of Project instances that are being updated.

    protected ProjectMySQLDAO(MySQLConnection mySQLConnection)
    {
        this.mySQLConnection = mySQLConnection;
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
                currentProject.setCustomer(mySQLConnection.customerMySQLDAO.findById((Integer)row.get("customer_id")));
                project=currentProject;
            }
        }
        if(project == null)
        {
            Customer customer = mySQLConnection.customerMySQLDAO.findById((Integer)row.get("customer_id"));
            project = new Project(mySQLConnection,(Integer) row.get("id"),
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
        QueryResult childResults = new QueryResult(mySQLConnection, String.format("select id from tasks where projects_id=%d",
                project.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            ProjectTask projectTask = mySQLConnection.taskDao().findById((Integer)childRow.get("id"));
            if(projectTask != null && !project.getProjectTasks().contains(projectTask))
                project.addTask(projectTask);
        }

        projectsUpdating.remove(project);
        return project;
    }

    @Override
    public List<Project> findAll()
    {
        QueryResult result = new QueryResult(mySQLConnection, "select * from projects");

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

        QueryResult result = new QueryResult(mySQLConnection, String.format("select * from projects where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public boolean insertProject(Project project)
    {
        QueryResult result = new QueryResult(mySQLConnection,String.format(
                "insert into projects (name,shortCode,color,timesheetNeeded,customer_id) values ('%s','%s',%d,%d,%d)",
                project.getName().replace("'","%%%"),
                project.getShortName().replace("'","%%%"),
                project.getColor(),
                project.isInvoice() ? 1 : 0,
                project.getCustomer().getId()
        ));
        project.setId((int)result.getCreatedKey());
        projectInstances.add(project);
        return true;
    }

    @Override
    public boolean updateProject(Project project)
    {
        if(projectsUpdating.contains(project)) return true; //already updating
        QueryResult result = new QueryResult(mySQLConnection,String.format("update projects set name='%s',shortCode='%s',color=%d,timesheetNeeded=%d,customer_id=%d where id=%d",
                project.getName().replace("'","%%%"),
                project.getShortName().replace("'","%%%"),
                project.getColor(),
                project.isInvoice() ? 1 : 0,
                project.getCustomer().getId(),
                project.getId()
        ));
        return true;
    }

    @Override
    public boolean deleteProject(Project project)
    {
        if(projectsUpdating.contains(project)) return true; //already updating

        // delete all owned tasks
        for(ProjectTask projectTask : new ArrayList<>(project.getProjectTasks()))
        {
            mySQLConnection.taskMySQLDAO.deleteTask(projectTask);
        }

        QueryResult result = new QueryResult(mySQLConnection,String.format("delete from projects where id=%d",project.getId()));
        if(result.getLastError() == null)
        {
            project.getCustomer().removeProject(project);
            projectInstances.remove(project);
            return true;
        }
        return false;
    }


}
