package MySQL;

import Data.*;
import Projects.Project;
import Sql.QueryResult;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Customer Data Acccess Object
 * @author Richard de Jong
 * @see Data.ICustomerDAO
 */
public class CustomerMySQLDAO implements ICustomerDAO
{
    private final MySQLConnection mySQLConnection;
    private final ArrayList<Customer> customerInstances;  // list of instances of customer to prevent duplicate objects of the same database id.
    private final ArrayList<Customer> customersUpdating;  // list of Customer instances that are being updated.

    protected CustomerMySQLDAO(MySQLConnection mySQLConnection)
    {
        this.mySQLConnection = mySQLConnection;
        customerInstances = new ArrayList<>();
        customersUpdating = new ArrayList<>();
    }

    /**
     * Convert table row data to Customer object.
     * Each table id is ensured to have 1 instance the existing instance is updated instead.
     *
     * @param row table data
     * @return    the customer object created or updated.
     * @see Customer
     */
    private Customer processRow(HashMap<String,Object> row)
    {
        Customer customer=null;
        for(Customer currentCustomer : customerInstances)
        {
            if(currentCustomer.getId() == (Integer)row.get("id"))
            {
                customersUpdating.add(currentCustomer);
                currentCustomer.setName((String)row.get("name"));
                currentCustomer.setShortName((String)row.get("shortName"));
                customer=currentCustomer;
            }
        }
        if(customer == null)
        {
            customer = new Customer(this,(Integer) row.get("id"), (String) row.get("name"), (String) row.get("shortName"));
            customerInstances.add(customer);
            customersUpdating.add(customer);
        }

        // find employees linked to customer
        QueryResult childResults = new QueryResult(mySQLConnection, String.format("select employees_id from employees_customers where customers_id=%d",
                customer.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            Employee employee = mySQLConnection.employeeDao().findById((Integer)childRow.get("employees_id"));
            if(employee != null && !customer.getEmployees().contains(employee))
                customer.addEmployee(employee);
        }

        // find projects linked to customer
        childResults = new QueryResult(mySQLConnection, String.format("select id from projects where customer_id=%d",
                customer.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            Project project = mySQLConnection.projectDao().findById((Integer)childRow.get("id"));
            if(project != null && !customer.getProjects().contains(project))
                customer.addProject(project);
        }

        // find attribute definitions linked to customer
        for(Attribute attribute : mySQLConnection.attributeDefinitionDao().findAll(customer))
        {
            if(!customer.getAttributeDefinitions().contains(attribute))
                customer.addAttributeDefinition(attribute);
        }

        customersUpdating.remove(customer);
        return customer;
    }

    /**
     * Returns all Customer objects in datasource.
     * Existing instances of Customer are retained and updated.
     *
     * @return List of all customer objects.
     */
    @Override
    public List<Customer> findAll()
    {
        QueryResult result = new QueryResult(mySQLConnection, "select * from customers");

        for(HashMap<String,Object> row : result.getRows())
        {
            processRow(row);
        }

        return Collections.unmodifiableList(customerInstances);
    }

    /**
     * Return Customer object in datasource by table row id number.
     * Any existing instance is retained and updated.
     *
     * @param id
     * @return
     */
    @Override
    public Customer findById(int id)
    {
        for(Customer customer : customerInstances)
        {
            if(customer.getId() == id) return customer;
        }

        QueryResult result = new QueryResult(mySQLConnection, String.format("select * from customers where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertCustomer(Customer customer)
    {
        QueryResult result = new QueryResult(mySQLConnection,String.format("insert into customers (name,shortName) values ('%s','%s')",
                customer.getName(),
                customer.getShortName()
        ));
        customer.setId((int)result.getCreatedKey());
        customerInstances.add(customer);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateCustomer(Customer customer)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(mySQLConnection,String.format("update customers set name='%s',shortName='%s' where id=%d",
                customer.getName().replace("'","%%%"),
                customer.getShortName().replace("'","%%%"),
                customer.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteCustomer(Customer customer)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating

        // remove associated employees - make new list while changing original
        for(Employee employee : new ArrayList<>(customer.getEmployees()))
        {
            customer.removeEmployee(employee);
        }

        // remove associated projects - make new list while changing original
        for(Project project : new ArrayList<>(customer.getProjects()))
        {
            mySQLConnection.projectDao().deleteProject(project);
        }

        // remove associated attribute definitions
        for(Attribute attribute : new ArrayList<>(customer.getAttributeDefinitions()))
        {
            mySQLConnection.attributeDefinitionDao().deleteAttribute(attribute);
        }

        QueryResult result = new QueryResult(mySQLConnection,String.format("delete from customers where id=%d",customer.getId()));
        if(result.getLastError() == null)
        {
            customerInstances.remove(customer);
            return DaoResult.OP_OK;
        }

        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }

    @Override
    public DaoResult linkEmployee(Customer customer, Employee employee)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        new QueryResult(mySQLConnection,String.format("insert into employees_customers (employees_id,customers_id) values (%d,%d)",
                employee.getId(),
                customer.getId()
        ));
        customersUpdating.add(customer);
        employee.addCustomer(customer);
        customersUpdating.remove(customer);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult unlinkEmployee(Customer customer, Employee employee)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        new QueryResult(mySQLConnection,String.format("delete from employees_customers where employees_id=%d and customers_id=%d",
                employee.getId(),
                customer.getId()
        ));
        customersUpdating.add(customer);
        employee.removeCustomer(customer);
        customersUpdating.remove(customer);
        return DaoResult.OP_OK;
    }
}
