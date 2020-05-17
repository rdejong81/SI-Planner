package Data;

import Projects.Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Customer extends DataEntity
{
    private String name, shortName;
    private ArrayList<Employee> employees;
    private ArrayList<Project> projects;
    private ICustomerDAO customerDao;

    public Customer(ICustomerDAO customerDao, int id, String name, String shortName)
    {
        super(id);
        this.name = name;
        this.shortName = shortName;
        employees = new ArrayList<>();
        projects = new ArrayList<>();
        this.customerDao = customerDao;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        customerDao.updateCustomer(this);
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
        customerDao.updateCustomer(this);
    }

    public void addEmployee(Employee employee)
    {
        employees.add(employee);
        customerDao.linkEmployee(this,employee);
    }

    public boolean removeEmployee(Employee employee)
    {
        return customerDao.unlinkEmployee(this,employee) == DaoResult.OP_OK && employees.remove(employee);
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public List<Project> getProjects()
    {
        return Collections.unmodifiableList(projects);
    }

    public boolean addProject(Project project)
    {
        return projects.add(project);
    }

    public boolean removeProject(Project project)
    {
        return projects.remove(project);
    }
}
