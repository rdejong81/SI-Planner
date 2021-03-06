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

package Projects;

import Data.Customer;
import Data.DataEntity;
import Data.IDataSource;
import Facade.AppFacade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project extends DataEntity
{
    private String name,shortName;
    private int color;
    private boolean invoice;
    private final IProjectDAO projectDao;
    private Customer customer;
    private final ArrayList<ProjectTask> projectTasks;

    public Project(IProjectDAO projectDao, int id, String name, int color, boolean invoice, String shortName, Customer customer)
    {
        super(id);
        this.name = name != null ? name : "undefined";
        this.invoice = invoice;
        this.color = color;
        this.projectDao = projectDao;
        this.customer = customer;
        customer.addProject(this);
        this.shortName = shortName != null ? shortName : "undefined";
        projectTasks = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name == null ? "undefined" : name;
        projectDao.updateProject(this);
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
        projectDao.updateProject(this);
        broadcastUpdate();
    }

    public boolean isInvoice()
    {
        return invoice;
    }

    public void setInvoice(boolean invoice)
    {
        this.invoice = invoice;
        projectDao.updateProject(this);
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        if(customer == null) return;
        this.customer.removeProject(this);
        this.customer = customer;
        customer.addProject(this);
        projectDao.updateProject(this);
        AppFacade.appFacade.resetPresentation();
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
        projectDao.updateProject(this);
    }

    public List<ProjectTask> getProjectTasks(){
        return Collections.unmodifiableList(projectTasks);
    }

    public boolean addTask(ProjectTask projectTask)
    {
        return projectTasks.add(projectTask);
    }

    public boolean removeTask(ProjectTask projectTask)
    {
        return projectTasks.remove(projectTask);
    }
}
