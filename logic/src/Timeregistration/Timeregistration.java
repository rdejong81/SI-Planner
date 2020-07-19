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

package Timeregistration;

import Data.DataEntity;
import Data.Employee;
import Projects.ProjectTask;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Timeregistration extends DataEntity
{
    private boolean synced;
    private String synckey;
    private ITimeregistrationDAO timeregistrationDao;
    private ProjectTask projectTask;
    private Employee employee;
    private ZonedDateTime start,end;


    public Timeregistration(ITimeregistrationDAO timeregistrationDao, int id, boolean synced, ZonedDateTime start, ZonedDateTime end, ProjectTask projectTask, Employee employee)
    {
        super(id);
        this.synced = synced;
        this.timeregistrationDao = timeregistrationDao;
        this.projectTask = projectTask;
        this.employee = employee;
        this.start = start;
        this.end = end;
        this.synckey = "";
        projectTask.addTimeregistration(this);
    }
    public Timeregistration(ITimeregistrationDAO timeregistrationDao, int id, boolean synced, ZonedDateTime start, ZonedDateTime end,
                            ProjectTask projectTask, Employee employee,String synckey)
    {
        this(timeregistrationDao,id,synced,start,end,projectTask,employee);
        this.synckey = synckey;

    }

    public String getSynckey()
    {
        return synckey;
    }

    public void setSynckey(String synckey)
    {
        this.synckey = synckey;
        timeregistrationDao.updateTimeregistration(this);
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

    public ZonedDateTime getStart()
    {
        return start;
    }

    public void setStart(ZonedDateTime start)
    {
        if(this.start.equals(start)) return;
        this.start = start;
        timeregistrationDao.updateTimeregistration(this);
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
        timeregistrationDao.updateTimeregistration(this);
        broadcastUpdate();
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
