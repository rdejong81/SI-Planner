package Projects;

import Data.DataEntity;
import Data.IDataSource;
import Planning.Planning;
import Timeregistration.Timeregistration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProjectTask extends DataEntity
{
    private String name;
    private boolean completed;
    private IProjectTaskDAO projectTaskDao;
    private Project project;
    private ArrayList<Planning> plannings;
    private ArrayList<Timeregistration> timeregistrations;

    public ProjectTask(IProjectTaskDAO projectTaskDao, int id, String name, boolean completed, Project project)
    {
        super(id);
        this.name = name;
        this.completed = completed;
        this.projectTaskDao = projectTaskDao;
        this.project = project;
        project.addTask(this);
        this.plannings = new ArrayList<>();
        this.timeregistrations = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        projectTaskDao.updateTask(this);
    }

    public boolean isCompleted()
    {
        return completed;
    }

    public void setCompleted(boolean completed)
    {
        this.completed = completed;
        projectTaskDao.updateTask(this);
    }

    public Project getProject(){
        return project;
    }

    public void setProject(Project project){
        this.project.removeTask(this);
        this.project = project;
        project.addTask(this);
        projectTaskDao.updateTask(this);
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

    public boolean addTimeregistration(Timeregistration timeregistration) {
        return timeregistrations.add(timeregistration);
    }

    public boolean removeTimeregistration(Timeregistration timeregistration)
    {
        return timeregistrations.remove(timeregistration);
    }

    public List<Timeregistration> getTimeregistrations()
    {
        return Collections.unmodifiableList(timeregistrations);
    }

}
