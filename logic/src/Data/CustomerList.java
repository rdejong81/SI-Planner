package Data;

import Facade.AppFacade;

import java.util.*;

public class CustomerList
{
    private List<Customer> customers;
    private ICustomerDAO customerDao;

    public CustomerList(ICustomerDAO customerDao)
    {
        this.customerDao = customerDao;
        customers = customerDao.findAll();
    }

    public Collection<Customer> getCustomers(){
        return Collections.unmodifiableCollection(customers);
    }

    public boolean addCustomer(Customer customer)
    {
        return customerDao.insertCustomer(customer) == DaoResult.OP_OK;
    }

    public boolean removeCustomer(Customer customer)
    {

        return customerDao.deleteCustomer(customer) == DaoResult.OP_OK;
    }
}
