package Data;

import java.util.List;

/**
 * Retrieve, create and update {@link Employee} objects from data source.
 * @author Richard de Jong
 * @version 1.0
 * @see Employee
 */
public interface IEmployeeDAO
{
    /**
     * Request all instances of {@link Employee} in datasource.
     * @return A read-only list of all instances of {@link Employee}.
     * @since 1.0
     */
    List<Employee> findAll();
    Employee findById(int id);

    DaoResult insertEmployee(Employee employee);
    DaoResult updateEmployee(Employee employee);
    DaoResult deleteEmployee(Employee employee);
    DaoResult linkCustomer(Employee employee, Customer customer);
    DaoResult unlinkCustomer(Employee employee, Customer customer);
}
