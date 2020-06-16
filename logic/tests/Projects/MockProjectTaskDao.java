package Projects;

import Data.DaoResult;
import Data.IDataSource;

import java.util.ArrayList;
import java.util.List;

public class MockProjectTaskDao implements IProjectTaskDAO
{
    private final ArrayList<ProjectTask> projectTasks;
    private final ArrayList<ProjectTask> projectTasksUpdating; // list of Employee instances that are being updated.

    private final IDataSource dataSource;

    public MockProjectTaskDao(IDataSource dataSource)
    {
        projectTasks = new ArrayList<>();
        projectTasksUpdating = new ArrayList<>();
        projectTasks.add(new ProjectTask(
                this,1,"Task A",false,dataSource.projectDao().findById(1)));

        projectTasks.add(new ProjectTask(
                this,2,"Task B",false,dataSource.projectDao().findById(1)));
        this.dataSource = dataSource;
    }

    @Override
    public List<ProjectTask> findAll()
    {
        return projectTasks;
    }

    @Override
    public ProjectTask findById(int id)
    {
        for(ProjectTask projectTask : projectTasks)
            if(projectTask.getId() == id) return projectTask;
        return null;
    }

    @Override
    public DaoResult insertTask(ProjectTask projectTask)
    {
        return projectTasks.add(projectTask) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult updateTask(ProjectTask projectTask)
    {
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteTask(ProjectTask projectTask)
    {
        return projectTasks.remove(projectTask) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

}
