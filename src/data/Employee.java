package data;

import db.Query;
import program.AppFacade;

import java.sql.SQLException;
import java.util.HashMap;

public class Employee
{
    final public static String TABLE_EMPLOYEES="employees";
    final public static String TABLE_EMPLOYEES_ROW_SQLLOGIN="sqlLogin";
    final public static String TABLE_EMPLOYEES_ROW_NAME="name";

    private String name;
    private String sqlLogin;
    private int id;

    // Create employee based on existing database record.
    protected Employee(int id, String name, String sqlLogin)
    {
        this.id = id;
        this.name = name;
        this.sqlLogin = sqlLogin;
    }

    // Create new employee in database
    public Employee(String name, String sqlLogin) throws SQLException
    {
        this(-1,name,sqlLogin);
        HashMap<String,Object> map = new HashMap<>();
        map.put(TABLE_EMPLOYEES_ROW_NAME,name);
        map.put(TABLE_EMPLOYEES_ROW_SQLLOGIN,sqlLogin);
        Query query = AppFacade.db.insertRow(TABLE_EMPLOYEES,map);
        id = (int)query.getCreatedKey();
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

    public int getId()
    {
        return id;
    }
}
