package Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockEmployeeDao implements IEmployeeDAO
{
    private final ArrayList<Employee> employees;
    private final ArrayList<Employee> employeesUpdating; // list of Employee instances that are being updated.

    private final IDataSource dataSource;

    public MockEmployeeDao(IDataSource dataSource)
    {
        employees = new ArrayList<>();
        employeesUpdating = new ArrayList<>();
        employees.add(new Employee(this,1,"Test name","testlogin"));
        this.dataSource = dataSource;
    }

    @Override
    public List<Employee> findAll()
    {
        return employees;
    }

    @Override
    public Employee findById(int id)
    {
        for(Employee employee : employees)
            if(employee.getId() == id) return employee;
        return null;
    }

    @Override
    public DaoResult insertEmployee(Employee employee)
    {
        return employees.add(employee) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult updateEmployee(Employee employee)
    {
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteEmployee(Employee employee)
    {
        return employees.remove(employee) ? DaoResult.OP_OK : DaoResult.DAO_INUSE;
    }

    @Override
    public DaoResult linkCustomer(Employee employee, Customer customer)
    {
        if(employeesUpdating.contains(employee)) return DaoResult.OP_OK; //already updating
        employeesUpdating.add(employee);
        customer.addEmployee(employee);
        employeesUpdating.remove(employee);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult unlinkCustomer(Employee employee, Customer customer)
    {
        if(employeesUpdating.contains(employee)) return DaoResult.OP_OK; //already updating
        employeesUpdating.add(employee);
        customer.removeEmployee(employee);
        employeesUpdating.remove(employee);
        return DaoResult.OP_OK;
    }
}
