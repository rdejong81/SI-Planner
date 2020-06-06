package Data;

import Facade.AppFacade;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeList
{
    private final List<Employee> employees;
    private final IEmployeeDAO employeeDao;

    public EmployeeList(IEmployeeDAO employeeDao){
        this.employeeDao = employeeDao;
        employees = employeeDao.findAll();
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public DaoResult addEmployee(Employee employee)
    {
        return employeeDao.insertEmployee(employee);
    }

    public DaoResult removeEmployee(Employee employee)
    {
        return employeeDao.deleteEmployee(employee);
    }
}
