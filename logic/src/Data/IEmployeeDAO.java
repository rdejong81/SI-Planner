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

    boolean insertEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(Employee employee);
    boolean linkCustomer(Employee employee, Customer customer);
    boolean unlinkCustomer(Employee employee, Customer customer);
}
