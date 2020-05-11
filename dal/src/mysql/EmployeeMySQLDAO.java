package mysql;

import Data.Customer;
import Data.Employee;
import Data.IEmployeeDAO;
import db.QueryResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EmployeeMySQLDAO implements IEmployeeDAO
{
    private MySQLConnection mySQLConnection;
    private ArrayList<Employee> employeeInstances; // list of instances of Employee to prevent duplicate objects of the same database id.
    private ArrayList<Employee> employeesUpdating; // list of Employee instances that are being updated.

    protected EmployeeMySQLDAO(MySQLConnection mySQLConnection){
        this.mySQLConnection = mySQLConnection;
        employeeInstances = new ArrayList<>();
        employeesUpdating = new ArrayList<>();
    }

    /**
     * Convert table row data to Employee object.
     * Each table id is ensured to have 1 instance the existing instance is updated instead.
     *
     * @param row table data
     * @return    the Employee object created or updated.
     * @see Employee
     */
    private Employee processRow(HashMap<String,Object> row)
    {
        Employee employee=null;
        for(Employee currentEmployee : employeeInstances)
        {
            if(currentEmployee.getId() == (Integer)row.get("id"))
            {
                employeesUpdating.add(currentEmployee);
                currentEmployee.setName((String)row.get("name"));
                currentEmployee.setSqlLoginName((String)row.get("sqlLogin"));
                employee=currentEmployee;
            }
        }
        if(employee == null)
        {
            employee = new Employee(mySQLConnection,(Integer) row.get("id"), (String) row.get("name"), (String) row.get("sqlLogin"));
            employeeInstances.add(employee);
            employeesUpdating.add(employee);
        }

        // find employees linked to customer
        QueryResult childResults = new QueryResult(mySQLConnection, String.format("select * from employees_customers where employees_id=%d",
                employee.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            Customer customer = mySQLConnection.customerMySQLDAO.findById((Integer)childRow.get("customers_id"));
            if(customer != null && !employee.getCustomers().contains(customer))
                employee.addCustomer(customer);
        }
        employeesUpdating.remove(employee);
        return employee;
    }


    @Override
    public List<Employee> findAll()
    {
        QueryResult result = new QueryResult(mySQLConnection, "select * from employees");

        for(HashMap<String,Object> row : result.getRows())
        {
            processRow(row);
        }

        return Collections.unmodifiableList(employeeInstances);
    }

    @Override
    public Employee findById(int id)
    {
        if(employeeInstances.isEmpty()) findAll();
        for(Employee employee : employeeInstances)
        {
            if(employee.getId() == id) return employee;
        }

        return null;
    }

    @Override
    public boolean insertEmployee(Employee employee)
    {
        if(employeeInstances.contains(employee)) return false;
        QueryResult result = new QueryResult(mySQLConnection,String.format("insert into employees (name,sqlLogin) values ('%s','%s')",
                employee.getName(),
                employee.getSqlLoginName()
        ));
        employee.setId((int)result.getCreatedKey());
        return employeeInstances.add(employee);
    }

    @Override
    public boolean updateEmployee(Employee employee)
    {
        if(employeesUpdating.contains(employee)) return true; // already updating.

        // remove any associated customers
        for (Customer customer : new ArrayList<>(employee.getCustomers()))
        {
            employee.removeCustomer(customer);
        }

        new QueryResult(mySQLConnection,String.format("update employees set name='%s',sqlLogin='%s' where id=%d",
                employee.getName().replace("'","%%%"),
                employee.getSqlLoginName().replace("'","%%%"),
                employee.getId()
        ));
        return true;
    }

    @Override
    public boolean deleteEmployee(Employee employee)
    {
        if(employeesUpdating.contains(employee)) return true; // already updating.
        new QueryResult(mySQLConnection,String.format("delete from employees where id=%d",employee.getId()));
        return employeeInstances.remove(employee);
    }

    @Override
    public boolean linkCustomer(Employee employee, Customer customer)
    {
        if(employeesUpdating.contains(employee)) return true; //already updating
        new QueryResult(mySQLConnection,String.format("insert into employees_customers (employees_id,customers_id) values (%d,%d)",
                employee.getId(),
                customer.getId()
        ));
        employeesUpdating.add(employee);
        customer.addEmployee(employee);
        employeesUpdating.remove(employee);
        return true;
    }

    @Override
    public boolean unlinkCustomer(Employee employee, Customer customer)
    {
        if(employeesUpdating.contains(employee)) return true; //already updating
        new QueryResult(mySQLConnection,String.format("delete from employees_customers where employees_id=%d and customers_id=%d",
                employee.getId(),
                customer.getId()
        ));
        employeesUpdating.add(employee);
        customer.removeEmployee(employee);
        employeesUpdating.remove(employee);
        return true;
    }
}
