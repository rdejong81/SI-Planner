package Planning;

import Data.DataEntity;
import Data.Employee;
import Data.IDataSource;
import Projects.ProjectTask;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

public class Planning extends DataEntity
{
    private boolean synced;
    private String synckey;
    private IPlanningDAO planningDao;
    private ProjectTask projectTask;
    private Employee employee;
    private ZonedDateTime start,end;


    public Planning(IPlanningDAO planningDao, int id, boolean synced,ZonedDateTime start, ZonedDateTime end, ProjectTask projectTask, Employee employee)
    {
        super(id);
        this.synced = synced;
        this.planningDao = planningDao;
        this.projectTask = projectTask;
        this.employee = employee;
        this.start = start;
        this.end = end;
        this.synckey = "";
        projectTask.addPlanning(this);
    }

    public Planning(IPlanningDAO planningDao, int id, boolean synced,ZonedDateTime start, ZonedDateTime end, ProjectTask projectTask, Employee employee, String synckey)
    {
        this(planningDao,id,synced,start,end,projectTask,employee);
        this.synckey = synckey;
    }

    public String getSynckey()
    {
        return synckey;
    }

    public void setSynckey(String synckey)
    {
        this.synckey = synckey;
        planningDao.updatePlanning(this);
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

    public ZonedDateTime getStart()
    {
        return start;
    }

    public void setStart(ZonedDateTime start)
    {
        if(this.start.equals(start)) return;
        this.start = start;
        planningDao.updatePlanning(this);
        //broadcastUpdate();
    }

    public ZonedDateTime getEnd()
    {
        return end;
    }

    public void setEnd(ZonedDateTime end)
    {
        if(this.end.equals(end)) return;
        this.end = end;
        planningDao.updatePlanning(this);
        broadcastUpdate();
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
