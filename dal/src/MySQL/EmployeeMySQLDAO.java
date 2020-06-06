package MySQL;

import Data.Customer;
import Data.DaoResult;
import Data.Employee;
import Data.IEmployeeDAO;
import Sql.QueryResult;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class EmployeeMySQLDAO implements IEmployeeDAO
{
    private final MySQLConnection mySQLConnection;
    private final ArrayList<Employee> employeeInstances; // list of instances of Employee to prevent duplicate objects of the same database id.
    private final ArrayList<Employee> employeesUpdating; // list of Employee instances that are being updated.

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
            employee = new Employee(this,(Integer) row.get("id"), (String) row.get("name"), (String) row.get("sqlLogin"));
            employeeInstances.add(employee);
            employeesUpdating.add(employee);
        }

        // find employees linked to customer
        QueryResult childResults = new QueryResult(mySQLConnection, String.format("select * from employees_customers where employees_id=%d",
                employee.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            Customer customer = mySQLConnection.customerDao().findById((Integer)childRow.get("customers_id"));
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
        for(Employee employee : employeeInstances)
        {
            if(employee.getId() == id) return employee;
        }

        QueryResult result = new QueryResult(mySQLConnection, String.format("select * from employees where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertEmployee(Employee employee)
    {
        if(employeeInstances.contains(employee)) return DaoResult.OP_OK;
        QueryResult result = new QueryResult(mySQLConnection,String.format("insert into employees (name,sqlLogin) values ('%s','%s')",
                employee.getName(),
                employee.getSqlLoginName()
        ));
        employee.setId((int)result.getCreatedKey());
        return employeeInstances.add(employee) ? DaoResult.OP_OK : DaoResult.DAO_DUPLICATE;
    }

    @Override
    public DaoResult updateEmployee(Employee employee)
    {
        if(employeesUpdating.contains(employee)) return DaoResult.OP_OK; // already updating.


        new QueryResult(mySQLConnection,String.format("update employees set name='%s',sqlLogin='%s' where id=%d",
                employee.getName().replace("'","%%%"),
                employee.getSqlLoginName().replace("'","%%%"),
                employee.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteEmployee(Employee employee)
    {
        if(employeesUpdating.contains(employee)) return DaoResult.OP_OK; // already updating.
        QueryResult result = new QueryResult(mySQLConnection,String.format("delete from employees where id=%d",employee.getId()));
        if(result.getLastError() == null)
        {
            employeeInstances.remove(employee);
            return DaoResult.OP_OK;
        }

        if(result.getLastError() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }

    @Override
    public DaoResult linkCustomer(Employee employee, Customer customer)
    {
        if(employeesUpdating.contains(employee)) return DaoResult.OP_OK; //already updating
        new QueryResult(mySQLConnection,String.format("insert into employees_customers (employees_id,customers_id) values (%d,%d)",
                employee.getId(),
                customer.getId()
        ));
        employeesUpdating.add(employee);
        customer.addEmployee(employee);
        employeesUpdating.remove(employee);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult unlinkCustomer(Employee employee, Customer customer)
    {
        if(employeesUpdating.contains(employee)) return DaoResult.OP_OK; //already updating
        new QueryResult(mySQLConnection,String.format("delete from employees_customers where employees_id=%d and customers_id=%d",
                employee.getId(),
                customer.getId()
        ));
        employeesUpdating.add(employee);
        customer.removeEmployee(employee);
        employeesUpdating.remove(employee);
        return DaoResult.OP_OK;
    }
}
