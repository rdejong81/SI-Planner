package Timeregistration;

import Data.DataEntity;
import Data.Employee;
import Projects.ProjectTask;

import java.time.LocalDateTime;

public class Timeregistration extends DataEntity
{
    private boolean synced;
    private ITimeregistrationDAO timeregistrationDao;
    private ProjectTask projectTask;
    private Employee employee;
    private LocalDateTime start,end;


    public Timeregistration(ITimeregistrationDAO timeregistrationDao, int id, boolean synced, LocalDateTime start, LocalDateTime end, ProjectTask projectTask, Employee employee)
    {
        super(id);
        this.synced = synced;
        this.timeregistrationDao = timeregistrationDao;
        this.projectTask = projectTask;
        this.employee = employee;
        this.start = start;
        this.end = end;
        projectTask.addTimeregistration(this);
    }

    public boolean isSynced()
    {
        return synced;
    }

    public void setSynced(boolean synced)
    {
        this.synced = synced;
        timeregistrationDao.updateTimeregistration(this);
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
        timeregistrationDao.updateTimeregistration(this);
    }

    public LocalDateTime getStart()
    {
        return start;
    }

    public void setStart(LocalDateTime start)
    {
        this.start = start;
        timeregistrationDao.updateTimeregistration(this);
    }

    public LocalDateTime getEnd()
    {
        return end;
    }

    public void setEnd(LocalDateTime end)
    {
        this.end = end;
        timeregistrationDao.updateTimeregistration(this);
    }

    public ProjectTask getProjectTask(){
        return projectTask;
    }

    public void setProjectTask(ProjectTask projectTask){
        this.projectTask.removeTimeregistration(this);
        this.projectTask = projectTask;
        projectTask.addTimeregistration(this);
        timeregistrationDao.updateTimeregistration(this);
    }

}
