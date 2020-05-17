package Data;

import Facade.AppFacade;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeList
{
    private List<Employee> employees;
    private IEmployeeDAO employeeDao;

    public EmployeeList(IEmployeeDAO employeeDao){
        this.employeeDao = employeeDao;
        employees = employeeDao.findAll();
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public boolean addEmployee(Employee employee)
    {
        return employeeDao.insertEmployee(employee) == DaoResult.OP_OK;
    }

    public boolean removeEmployee(Employee employee)
    {
        return employeeDao.deleteEmployee(employee) == DaoResult.OP_OK;
    }
}
