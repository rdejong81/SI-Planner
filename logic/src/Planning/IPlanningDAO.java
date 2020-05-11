package Planning;

import Data.Customer;
import Data.Employee;

import java.util.List;

/**
 * Retrieve, create and update {@link Planning} objects from data source.
 * @author Richard de Jong
 * @version 1.0
 * @since 1.0
 * @see Planning
 */
public interface IPlanningDAO
{
    /**
     * Request all instances of {@link Planning} in datasource.
     * @return A read-only list of all instances of {@link Planning}.
     * @since 1.0
     */
    List<Planning> findAll();

    /**
     * Request instance of {@link Planning} by unique id.
     * @param id unique data source integer.
     * @return instance of {@link Planning}.
     * @since 1.0
     */
    Planning findById(int id);

    /**
     * Insert new instance of {@link Planning}
     * @param planning newly created instance of Customer.
     * @return true if successful
     * @since 1.0
     */
    boolean insertPlanning(Planning planning);

    /**
     * Update instance of {@link Planning} in datasource
     * @param planning instance of {@link Planning}
     * @return true if successful
     * @since 1.0
     */
    boolean updatePlanning(Planning planning);

    /**
     * Delete instance of {@link Planning} in datasource
     * @param planning instance of {@link Planning}
     * @return true if successful
     * @since 1.0
     */
    boolean deletePlanning(Planning planning);
}
