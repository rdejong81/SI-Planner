package Data;

import facade.AppFacade;

import java.util.*;

public class CustomerList
{
    private final List<Customer> customers;

    public CustomerList(){
        customers = AppFacade.appFacade.getDataSource().customerDao().findAll();
    }

    public Collection<Customer> getCustomers(){
        return Collections.unmodifiableCollection(customers);
    }

    public boolean addCustomer(Customer customer)
    {
        return AppFacade.appFacade.getDataSource().customerDao().insertCustomer(customer);
    }

    public boolean removeCustomer(Customer customer)
    {

        return AppFacade.appFacade.getDataSource().customerDao().deleteCustomer(customer);
    }
}
