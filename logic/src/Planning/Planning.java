package Planning;

import Data.DataEntity;
import Data.Employee;
import Data.IDataSource;
import Projects.ProjectTask;

import java.time.LocalDateTime;
import java.util.Date;

public class Planning extends DataEntity
{
    private boolean synced;
    private IPlanningDAO planningDao;
    private ProjectTask projectTask;
    private Employee employee;
    private LocalDateTime start,end;


    public Planning(IPlanningDAO planningDao, int id, boolean synced,LocalDateTime start, LocalDateTime end, ProjectTask projectTask, Employee employee)
    {
        super(id);
        this.synced = synced;
        this.planningDao = planningDao;
        this.projectTask = projectTask;
        this.employee = employee;
        this.start = start;
        this.end = end;
        projectTask.addPlanning(this);
    }

    public boolean isSynced()
    {
        return synced;
    }

    public void setSynced(boolean synced)
    {
        this.synced = synced;
        planningDao.updatePlanning(this);
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
        planningDao.updatePlanning(this);
    }

    public LocalDateTime getStart()
    {
        return start;
    }

    public void setStart(LocalDateTime start)
    {
        this.start = start;
        planningDao.updatePlanning(this);
    }

    public LocalDateTime getEnd()
    {
        return end;
    }

    public void setEnd(LocalDateTime end)
    {
        this.end = end;
        planningDao.updatePlanning(this);
    }

    public ProjectTask getProjectTask(){
        return projectTask;
    }

    public void setProjectTask(ProjectTask projectTask){
        this.projectTask.removePlanning(this);
        this.projectTask = projectTask;
        projectTask.addPlanning(this);
        planningDao.updatePlanning(this);
    }

}
