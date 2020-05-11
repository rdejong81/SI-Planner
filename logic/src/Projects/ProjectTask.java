package Projects;

import Data.DataEntity;
import Data.IDataSource;
import Planning.Planning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectTask extends DataEntity
{
    private String name;
    private boolean completed;
    private IDataSource dataSource;
    private Project project;
    private ArrayList<Planning> plannings;

    public ProjectTask(IDataSource dataSource, int id, String name, boolean completed, Project project)
    {
        super(id);
        this.name = name;
        this.completed = completed;
        this.dataSource = dataSource;
        this.project = project;
        project.addTask(this);
        this.plannings = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        dataSource.taskDao().updateTask(this);
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
        dataSource.taskDao().updateTask(this);
    }

    public Project getProject(){
        return project;
    }

    public void setProject(Project project){
        this.project.removeTask(this);
        this.project = project;
        project.addTask(this);
        dataSource.taskDao().updateTask(this);
    }

    public boolean addPlanning(Planning planning)
    {
        return plannings.add(planning);
    }

    public boolean removePlanning(Planning planning)
    {
        return plannings.remove(planning);
    }

    public List<Planning> getPlannings()
    {
        return Collections.unmodifiableList(plannings);
    }

}
