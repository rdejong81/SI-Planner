package data;

import db.QueryResult;

import java.sql.SQLException;
import java.util.*;

import program.AppFacade;

public class Customer extends DataEntity
{
    private ArrayList<Employee> employees;
    private String name;

    final public static String TABLE_CUSTOMERS="customers";
    final public static String TABLE_CUSTOMERS_ROW_NAME="name";


    // Create based on existing db record.
    protected Customer(int id){
        super(id,TABLE_CUSTOMERS);
        employees = new ArrayList<>();
    }

    // Create new db record
    public Customer(String name) throws SQLException
    {
        this(-1);
        this.name = name;
        QueryResult queryResult = AppFacade.db.insertRow(TABLE_CUSTOMERS,readFields());
        id = (int) queryResult.getCreatedKey();
        loadEntityData();
    }


    @Override
    public void updateField(String fieldName, Object fieldValue)
    {
        switch (fieldName){
            case TABLE_CUSTOMERS_ROW_NAME: name = (String)fieldValue; break;
        }
    }

    @Override
    public Map<String, Object> readFields()
    {
        return Map.of(
                TABLE_CUSTOMERS_ROW_NAME,name
        );
    }

    @Override
    public DataEntity factoryCreateFromId(int id)
    {
        return new Customer(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) throws SQLException
    {
        AppFacade.db.updateField(getTableName(),id,TABLE_CUSTOMERS_ROW_NAME,name);
        this.name = name;
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeEmployee(int id)
    {
        employees.removeIf(n -> (n.id == id));
    }


}
