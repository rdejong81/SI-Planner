package Data;

import java.util.ArrayList;
import java.util.List;

public class MockCustomerDao implements ICustomerDAO
{
    private final ArrayList<Customer> customers;
    private final ArrayList<Customer> customersUpdating; // list of Employee instances that are being updated.

    private final IDataSource dataSource;

    public MockCustomerDao(IDataSource dataSource)
    {
        customers = new ArrayList<>();
        customersUpdating = new ArrayList<>();
        customers.add(new Customer(this,1,"Customer A","CUA"));
        customers.add(new Customer(this,2,"Customer B","CUB"));
        this.dataSource = dataSource;
    }

    @Override
    public List<Customer> findAll()
    {
        return customers;
    }

    @Override
    public Customer findById(int id)
    {
        for(Customer customer : customers)
            if(customer.getId() == id) return customer;
        return null;
    }

    @Override
    public DaoResult insertCustomer(Customer customer)
    {
        return customers.add(customer) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult updateCustomer(Customer customer)
    {
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteCustomer(Customer customer)
    {
        return customers.remove(customer) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult linkEmployee(Customer customer, Employee employee)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        customersUpdating.add(customer);
        employee.addCustomer(customer);
        customersUpdating.remove(customer);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult unlinkEmployee(Customer customer, Employee employee)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        customersUpdating.add(customer);
        employee.removeCustomer(customer);
        customersUpdating.remove(customer);
        return DaoResult.OP_OK;
    }
}
