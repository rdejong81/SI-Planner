package Facade;

import Data.CustomerList;
import Data.EmployeeList;
import Data.*;

public class AppFacade
{
    static public AppFacade appFacade; // instantiated in main.
    private IDataSource dataSource;
    private LoginProcessor loginProcessor;
    private ILoginController loginController;
    private ISQLConnectionFactory sqlConnectionFactory;
    private IWindow mainWindow;
    private CustomerList customerList;
    private EmployeeList employeeList;

    public CustomerList getCustomerList()
    {
        return customerList;
    }

    public EmployeeList getEmployeeList()
    {
        return employeeList;
    }

    public AppFacade(ISQLConnectionFactory sqlConnectionFactory){
        this.sqlConnectionFactory = sqlConnectionFactory;

    }

    public void showMain(IWindow mainWindow)
    {
        this.mainWindow = mainWindow;
        mainWindow.refreshData();
        mainWindow.showAndWait();
    }

    public ILoginController getLoginController()
    {
        return loginController;
    }

    public boolean DoLogin(ILoginController loginController)
    {
        this.loginController = loginController;
        loginProcessor = new LoginProcessor(loginController,sqlConnectionFactory);

        if(loginProcessor.getDataSource() != null)
        {
            dataSource = loginProcessor.getDataSource();
            employeeList = new EmployeeList(dataSource.employeeDao());
            customerList = new CustomerList(dataSource.customerDao());
            return true;
        }

        return false;
    }

    public Customer addCustomer(String name, String shortName)
    {
        for(Customer customer : customerList.getCustomers()) {
            if(customer.getName().equals(name) || customer.getShortName().equals(shortName)) return null;
        }
        Customer newCustomer = new Customer(dataSource.customerDao(),0, name, shortName);
        if(customerList.addCustomer(newCustomer))
        {
            mainWindow.refreshData();
            return newCustomer;
        }

        return null;
    }

    public boolean removeCustomer(Customer customer){
        if(customerList.removeCustomer(customer)) {
            mainWindow.refreshData();
            return true;
        }
        return false;
    }

    public Employee addEmployee(String name, String loginName)
    {
        for(Employee employee : employeeList.getEmployees()) {
            if(employee.getName().equals(name) || employee.getSqlLoginName().equals(loginName)) return null;
        }
        Employee newEmployee = new Employee(dataSource.employeeDao(),0, name, loginName);
        if(employeeList.addEmployee(newEmployee))
        {
            mainWindow.refreshData();
            return newEmployee;
        }

        return null;
    }

    public boolean removeEmployee(Employee employee){
        if(employeeList.removeEmployee(employee)) {
            mainWindow.refreshData();
            return true;
        }
        return false;
    }


    public IDataSource getDataSource()
    {
        return dataSource;
    }


}
