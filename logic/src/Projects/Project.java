package Projects;

import Data.Customer;
import Data.DataEntity;
import Data.IDataSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Project extends DataEntity
{
    private String name,shortName;
    private int color;
    private boolean invoice;
    private IProjectDAO projectDao;
    private Customer customer;
    private ArrayList<ProjectTask> projectTasks;

    public Project(IProjectDAO projectDao, int id, String name, int color, boolean invoice, String shortName, Customer customer)
    {
        super(id);
        this.name = name;
        this.invoice = invoice;
        this.color = color;
        this.projectDao = projectDao;
        this.customer = customer;
        customer.addProject(this);
        this.shortName = shortName;
        projectTasks = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
        this.customer.removeProject(this);
        this.customer = customer;
        customer.addProject(this);
        projectDao.updateProject(this);
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
