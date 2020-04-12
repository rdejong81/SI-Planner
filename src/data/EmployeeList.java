package data;

import db.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class EmployeeList
{
    ArrayList<Employee> employees;

    public EmployeeList() throws SQLException
    {
        employees = new ArrayList<>();
        Query q = program.AppFacade.db.selectAllRows("employees");
        for (HashMap<String, Object> row : q.getRows())
        {
            employees.add(new Employee((int)row.get("id"),(String)row.get("name"),(String)row.get("sqlLogin")));
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
            if (employee.getId() == id) return employees.remove(employee);
        }
        return false;
    }

    public Collection<Employee> getEmployees()
    {
        return Collections.unmodifiableCollection(employees);
    }
}
