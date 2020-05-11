package Data;

import facade.AppFacade;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class EmployeeList
{
    private final List<Employee> employees;

    public EmployeeList(){
        employees = AppFacade.appFacade.getDataSource().employeeDao().findAll();
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public boolean addEmployee(Employee employee)
    {
        return AppFacade.appFacade.getDataSource().employeeDao().insertEmployee(employee);
    }

    public boolean removeEmployee(Employee employee)
    {
        return AppFacade.appFacade.getDataSource().employeeDao().deleteEmployee(employee);
    }
}
