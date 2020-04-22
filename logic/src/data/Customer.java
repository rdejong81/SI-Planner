package data;

import com.sun.jdi.request.DuplicateRequestException;

import java.util.*;

import facade.IQueryResult;
import facade.ISQLConnection;

import static data.DataEntityList.allEmployees;
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
    protected Customer(ISQLConnection sqlConnection, int id){
        super(sqlConnection,id,TABLE_CUSTOMERS);
        employees = new ArrayList<>();
        projects = new ArrayList<>();

        try
        {
            IQueryResult projectResults = sqlConnection.selectAllRowsIf(TABLE_PROJECTS,TABLE_PROJECTS_ROW_CUSTOMER,this.id);

            for (HashMap<String, Object> row : projectResults.getRows())
            {
                projects.add(new Project(sqlConnection,(Integer) row.get("id")));
            }

        } catch (Exception e){

        }

            // load linked employees relations to employees and this customer
            IQueryResult employeeResults = sqlConnection.selectAllRowsIf(TABLE_EMPCUST,TABLE_EMPCUST_ROW_CUSTOMERS_ID,this.id);


            for(Map<String,Object> row : employeeResults.getRows())
            {
                if(this.id == ((Integer)row.get(TABLE_EMPCUST_ROW_CUSTOMERS_ID)).intValue())
                {
                    for(Employee employee : allEmployees.getEntities())
                    {
                        if( employee.id == ((Integer)row.get(TABLE_EMPCUST_ROW_EMPLOYEES_ID)).intValue())
                        {
                            // make relationship on both sides.
                            employee.addCustomer(this);
                            employees.add(employee);
                        }
                    }
                }
            }

    }

    // Create new db record
    public Customer(ISQLConnection sqlConnection, String name)
    {
        this(sqlConnection,-1);
        this.name = name;
        IQueryResult queryResult = sqlConnection.insertRow(TABLE_CUSTOMERS,readFields());
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

    public void setName(String name)
    {
        sqlConnection.updateField(getTableName(),id,TABLE_CUSTOMERS_ROW_NAME,name);
        this.name = name;
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public void addEmployee(Employee employee) throws DuplicateRequestException
    {
        if(employees.contains(employee)) throw new DuplicateRequestException("Already in list");
        HashMap<String, Object> row = new HashMap<>();
        row.put(TABLE_EMPCUST_ROW_CUSTOMERS_ID,this.id);
        row.put(TABLE_EMPCUST_ROW_EMPLOYEES_ID,employee.id);
        sqlConnection.insertRow(TABLE_EMPCUST,row);
        employees.add(employee);
        employee.addCustomer(this);
    }

    public boolean removeEmployee(int id)
    {
        return employees.removeIf(n -> (n.id == id && n.removeCustomer(this.id)));
    }


}
