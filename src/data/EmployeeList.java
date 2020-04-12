package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EmployeeList
{
    ArrayList<Employee> employees;


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
