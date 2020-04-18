package data;

import db.QueryResult;
import program.AppFacade;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Employee extends DataEntity
{
    final public static String TABLE_EMPLOYEES="employees";
    final public static String TABLE_EMPLOYEES_ROW_SQLLOGIN="sqlLogin";
    final public static String TABLE_EMPLOYEES_ROW_NAME="name";

    // Create employee based on existing database record.
    protected Employee(int id)
    {
        super(id,TABLE_EMPLOYEES);
    }

    // Create new employee in database
    public Employee(HashMap<String, Object> row) throws SQLException
    {
        this(-1);
        QueryResult queryResult = AppFacade.db.insertRow(TABLE_EMPLOYEES,row);
        id = (int) queryResult.getCreatedKey();
        loadEntityData();
    }
    // Create new employee with SQL Login
    public Employee(HashMap<String, Object> row,String password) throws SQLException
    {
        this(row);
        AppFacade.db.createUser((String)row.get(TABLE_EMPLOYEES_ROW_SQLLOGIN),password);
    }


    public String getName()
    {
        return (String)rowData.get(TABLE_EMPLOYEES_ROW_NAME);
    }

    public String getSqlLogin()
    {
        return (String)rowData.get(TABLE_EMPLOYEES_ROW_SQLLOGIN);
    }


    @Override
    public DataEntity factoryCreateFromId(int id)
    {
        return new Employee(id);
    }
}
