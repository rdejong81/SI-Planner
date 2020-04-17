package data;

import db.Query;
import program.AppFacade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import static data.Employee.*;

public class EmployeeList
{
    ArrayList<Employee> employees;

    public EmployeeList() throws SQLException
    {
        employees = new ArrayList<>();
        // Load entries from database upon creation of the list.
        Query q = AppFacade.db.selectAllRows(TABLE_EMPLOYEES);
        for (HashMap<String, Object> row : q.getRows())
        {
            employees.add(new Employee((int)row.get("id"),(String)row.get(TABLE_EMPLOYEES_ROW_NAME),(String)row.get(TABLE_EMPLOYEES_ROW_SQLLOGIN)));
        }

    }

    public void addEmployee(Employee employee)
    {
        employees.add(employee);
    }

    public boolean removeEmployee(int id)
    {
        for (Employee employee : employees)
        {
            if (employee.getId() == id) {
                try
                {
                    AppFacade.db.deleteRow(TABLE_EMPLOYEES, id);
                } catch(Exception e){
                    System.err.println("Unable to remove employee from database: "+e.getMessage());
                    return false;
                }
                return employees.remove(employee);
            }
        }
        return false;
    }

    public Collection<Employee> getEmployees()
    {
        return Collections.unmodifiableCollection(employees);
    }
}
