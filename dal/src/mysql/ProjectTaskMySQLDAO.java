package mysql;

import Planning.Planning;
import Projects.ProjectTask;
import db.QueryResult;
import Projects.IProjectTaskDAO;
import Projects.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Project Data Acccess Object
 * @author Richard de Jong
 * @see IProjectTaskDAO
 */
public class ProjectTaskMySQLDAO implements IProjectTaskDAO
{
    private MySQLConnection mySQLConnection;
    private ArrayList<ProjectTask> projectTaskInstances;  // list of instances of Task to prevent duplicate objects of the same database id.
    private ArrayList<ProjectTask> tasksUpdating;  // list of Task instances that are being updated.

    protected ProjectTaskMySQLDAO(MySQLConnection mySQLConnection)
    {
        this.mySQLConnection = mySQLConnection;
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
                currentProjectTask.setProject(mySQLConnection.projectMySQLDAO.findById((Integer)row.get("projects_id")));
                projectTask = currentProjectTask;
            }
        }
        if(projectTask == null)
        {
            Project project = mySQLConnection.projectMySQLDAO.findById((Integer)row.get("projects_id"));
            projectTask = new ProjectTask(mySQLConnection,(Integer) row.get("id"),
                    (String) row.get("name"),
                    (Boolean) row.get("completed"),
                    project
            );
            projectTaskInstances.add(projectTask);
            tasksUpdating.add(projectTask);
        }

        // find plannings belonging to projecttask
        QueryResult childResults = new QueryResult(mySQLConnection, String.format("select id from time where tasks_id=%d and planned=1",
                projectTask.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            Planning planning = mySQLConnection.planningDao().findById((Integer)childRow.get("id"));
            if(planning != null && !projectTask.getPlannings().contains(planning))
                projectTask.addPlanning(planning);
        }

        tasksUpdating.remove(projectTask);
        return projectTask;
    }

    @Override
    public List<ProjectTask> findAll()
    {
        QueryResult result = new QueryResult(mySQLConnection, "select * from tasks");

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

        QueryResult result = new QueryResult(mySQLConnection, String.format("select * from tasks where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public boolean insertTask(ProjectTask projectTask)
    {
        QueryResult result = new QueryResult(mySQLConnection,String.format(
                "insert into tasks (name,completed,projects_id) values ('%s',%d,%d)",
                projectTask.getName().replace("'","%%%"),
                projectTask.isCompleted() ? 1 : 0,
                projectTask.getProject().getId()
        ));
        projectTask.setId((int)result.getCreatedKey());
        projectTaskInstances.add(projectTask);
        return true;
    }

    @Override
    public boolean updateTask(ProjectTask projectTask)
    {
        if(tasksUpdating.contains(projectTask)) return true; //already updating
        QueryResult result = new QueryResult(mySQLConnection,String.format("update tasks set name='%s',completed=%d,projects_id=%d where id=%d",
                projectTask.getName().replace("'","%%%"),
                projectTask.isCompleted() ? 1 : 0,
                projectTask.getProject().getId(),
                projectTask.getId()
        ));
        return true;
    }

    @Override
    public boolean deleteTask(ProjectTask projectTask)
    {
        if(tasksUpdating.contains(projectTask)) return true; //already updating
        QueryResult result = new QueryResult(mySQLConnection,String.format("delete from tasks where id=%d", projectTask.getId()));
        if(result.getLastError() == null)
        {
            projectTask.getProject().removeTask(projectTask);
            projectTaskInstances.remove(projectTask);
            return true;
        }
        return false;
    }


}
