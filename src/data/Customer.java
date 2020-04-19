package data;

import com.sun.jdi.request.DuplicateRequestException;
import db.QueryResult;

import java.sql.SQLException;
import java.util.*;

import program.AppFacade;

import static data.Employee.*;
import static data.Project.TABLE_PROJECTS;
import static data.Project.TABLE_PROJECTS_ROW_CUSTOMER;

public class Customer extends DataEntity
{
    private ArrayList<Employee> employees;
    private ArrayList<Project> projects;
    private String name;

    final public static String TABLE_CUSTOMERS="customers";
    final public static String TABLE_CUSTOMERS_ROW_NAME="name";
    final public static String TABLE_EMPCUST="employees_customers";
    final public static String TABLE_EMPCUST_ROW_CUSTOMERS_ID="customers_id";
    final public static String TABLE_EMPCUST_ROW_EMPLOYEES_ID="employees_id";


    // Create based on existing db record.
    protected Customer(int id){
        super(id,TABLE_CUSTOMERS);
        employees = new ArrayList<>();
        projects = new ArrayList<>();

        try
        {
            QueryResult projectResults = AppFacade.db.selectAllRowsIf(TABLE_PROJECTS,TABLE_PROJECTS_ROW_CUSTOMER,this.id);

            for (HashMap<String, Object> row : projectResults.getRows())
            {
                projects.add(new Project((Integer) row.get("id")));
            }

        } catch (Exception e){

        }

        try
        {
            // load linked employees relations to employees and this customer
            QueryResult employeeResults = AppFacade.db.selectAllRowsIf(TABLE_EMPCUST,TABLE_EMPCUST_ROW_CUSTOMERS_ID,this.id);


            for(Map<String,Object> row : employeeResults.getRows())
            {
                if(this.id == ((Integer)row.get(TABLE_EMPCUST_ROW_CUSTOMERS_ID)).intValue())
                {
                    for(DataEntity employee : AppFacade.employees.getEntities())
                    {
                        if( ((Employee)employee).id == ((Integer)row.get(TABLE_EMPCUST_ROW_EMPLOYEES_ID)).intValue())
                        {
                            // make relationship on both sides.
                            ((Employee) employee).addCustomer(this);
                            employees.add((Employee) employee);
                        }
                    }
                }
            }
        } catch (Exception e){

        }
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

    public void addEmployee(Employee employee) throws SQLException,DuplicateRequestException
    {
        if(employees.contains(employee)) throw new DuplicateRequestException("Already in list");
        HashMap<String, Object> row = new HashMap<>();
        row.put(TABLE_EMPCUST_ROW_CUSTOMERS_ID,this.id);
        row.put(TABLE_EMPCUST_ROW_EMPLOYEES_ID,employee.id);
        AppFacade.db.insertRow(TABLE_EMPCUST,row);
        employees.add(employee);
        employee.addCustomer(this);
    }

    public boolean removeEmployee(int id)
    {
        return employees.removeIf(n -> (n.id == id && n.removeCustomer(this.id)));
    }


}
