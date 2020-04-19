package data;

import com.sun.jdi.request.DuplicateRequestException;
import db.QueryResult;
import program.AppFacade;

import java.sql.SQLException;
import java.util.*;

public class Employee extends DataEntity
{
    final public static String TABLE_EMPLOYEES="employees";
    final public static String TABLE_EMPLOYEES_ROW_SQLLOGIN="sqlLogin";
    final public static String TABLE_EMPLOYEES_ROW_NAME="name";


    private String name;
    private String sqlLogin;
    private ArrayList<Customer> customers;

    // Create employee based on existing database record.
    protected Employee(int id)
    {
        super(id,TABLE_EMPLOYEES);
        this.customers = new ArrayList<>();
    }

    // Create new employee in database
    public Employee(String name, String sqlLogin) throws SQLException
    {
        this(-1);
        this.sqlLogin = sqlLogin;
        this.name = name;
        QueryResult queryResult = AppFacade.db.insertRow(TABLE_EMPLOYEES,readFields());
        id = (int) queryResult.getCreatedKey();
        loadEntityData();
    }
    // Create new employee with SQL Login
    public Employee(String name, String sqlLogin,String password) throws SQLException
    {
        this(name,sqlLogin);
        AppFacade.db.createUser(sqlLogin,password);
    }


    public String getName()
    {
        return name;
    }

    public String getSqlLogin()
    {
        return sqlLogin;
    }


    @Override
    public void updateField(String fieldName, Object fieldValue)
    {
        switch (fieldName)
        {
            case TABLE_EMPLOYEES_ROW_SQLLOGIN: sqlLogin=(String)fieldValue;
            case TABLE_EMPLOYEES_ROW_NAME: name=(String)fieldValue;
        }
    }

    @Override
    public Map<String, Object> readFields()
    {
        return Map.of(
                TABLE_EMPLOYEES_ROW_NAME,name,
                TABLE_EMPLOYEES_ROW_SQLLOGIN,sqlLogin
        );
    }

    public void setName(String name) throws SQLException
    {
        AppFacade.db.updateField(getTableName(),id,TABLE_EMPLOYEES_ROW_NAME,name);
        this.name = name;
    }

    public void setSqlLogin(String sqlLogin) throws SQLException
    {
        AppFacade.db.updateField(getTableName(),id,TABLE_EMPLOYEES_ROW_SQLLOGIN,sqlLogin);
        this.sqlLogin = sqlLogin;
    }

    public Collection<Customer> getCustomers()
    {
        return Collections.unmodifiableCollection(customers);
    }

    public void addCustomer(Customer customer)
    {
        if(customers.contains(customer)) throw new DuplicateRequestException("Already in list");
        customers.add(customer);
        try
        {
            customer.addEmployee(this);
        }
        catch (Exception e){

        }
    }

    public boolean removeCustomer(int id)
    {
        return customers.removeIf(n -> (n.id == id && n.removeEmployee(this.id)));
    }
}
