package data;

import db.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import program.AppFacade;

public class Customer
{
    private int id;
    private String name;
    ArrayList<Employee> employees;

    final public static String TABLE_CUSTOMERS="customers";
    final public static String TABLE_CUSTOMERS_ROW_NAME="name";


    // Create based on existing db record.
    protected Customer(int id, String name){
        this.id = id;
        this.name = name;
    }

    // Create new db record
    public Customer(String name) throws SQLException
    {
        this(-1,name);
        HashMap<String,Object> map = new HashMap<>();
        map.put(TABLE_CUSTOMERS_ROW_NAME,name);
        Query query = AppFacade.db.insertRow(TABLE_CUSTOMERS,map);
        id = (int)query.getCreatedKey();
    }
}
