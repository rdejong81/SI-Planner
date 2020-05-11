package Data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Employee extends DataEntity
{
    private String name,sqlLoginName;
    private ArrayList<Customer> customers;
    private IDataSource dataSource;

    public Employee(IDataSource dataSource,int id,String name, String sqlLoginName)
    {
        super(id);
        this.name = name;
        this.sqlLoginName = sqlLoginName;
        customers = new ArrayList<>();
        this.dataSource = dataSource;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        dataSource.employeeDao().updateEmployee(this);
    }

    public String getSqlLoginName()
    {
        return sqlLoginName;
    }

    public void setSqlLoginName(String sqlLoginName)
    {
        this.sqlLoginName = sqlLoginName;
        dataSource.employeeDao().updateEmployee(this);
    }

    public void addCustomer(Customer customer)
    {
        if(customers.contains(customer)) return;

        customers.add(customer);
        dataSource.employeeDao().linkCustomer(this,customer);
    }

    public boolean removeCustomer(Customer customer)
    {
        if(!customers.contains(customer)) return false;
        return customers.remove(customer) && dataSource.employeeDao().unlinkCustomer(this,customer);
    }

    public Collection<Customer> getCustomers()
    {
        return Collections.unmodifiableCollection(customers);
    }
}
