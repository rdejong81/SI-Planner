package data;

import db.QueryResult;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import program.AppFacade;

public class Customer extends DataEntity
{
    private String name;
    ArrayList<Employee> employees;

    final public static String TABLE_CUSTOMERS="customers";
    final public static String TABLE_CUSTOMERS_ROW_NAME="name";


    // Create based on existing db record.
    protected Customer(int id){
        super(id,TABLE_CUSTOMERS);


    }


    // Create new db record
    public Customer(HashMap<String, Object> row) throws SQLException
    {
        this(-1);
        QueryResult queryResult = AppFacade.db.insertRow(TABLE_CUSTOMERS,row);
        id = (int) queryResult.getCreatedKey();
        loadEntityData();
    }

    @Override
    public DataEntity factoryCreateFromId(int id)
    {
        return new Customer(id);
    }
}
