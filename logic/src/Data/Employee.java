package Data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Employee extends DataEntity
{
    private String name,sqlLoginName;
    private final ArrayList<Customer> customers;
    private final IEmployeeDAO employeeDao;

    public Employee(IEmployeeDAO employeeDao, int id, String name, String sqlLoginName)
    {
        super(id);
        this.name = name != null ? name : "undefined";
        this.sqlLoginName = sqlLoginName != null ? sqlLoginName : "undefined";
        customers = new ArrayList<>();
        this.employeeDao = employeeDao;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        employeeDao.updateEmployee(this);
    }

    public String getSqlLoginName()
    {
        return sqlLoginName;
    }

    public void setSqlLoginName(String sqlLoginName)
    {
        this.sqlLoginName = sqlLoginName;
        employeeDao.updateEmployee(this);
    }

    public void addCustomer(Customer customer)
    {
        if(customers.contains(customer)) return;

        customers.add(customer);
        employeeDao.linkCustomer(this,customer);
    }

    public boolean removeCustomer(Customer customer)
    {
        if(!customers.contains(customer)) return false;
        return customers.remove(customer) && employeeDao.unlinkCustomer(this,customer) == DaoResult.OP_OK;
    }

    public Collection<Customer> getCustomers()
    {
        return Collections.unmodifiableCollection(customers);
    }
}
