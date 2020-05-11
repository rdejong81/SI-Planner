package Projects;

import java.util.List;

/**
 * Retrieve, create and update {@link ProjectTask} objects from data source.
 * @author Richard de Jong
 * @version 1.0
 * @since 1.0
 * @see ProjectTask
 */
public interface IProjectTaskDAO
{
    /**
     * Request all instances of {@link ProjectTask} in datasource.
     * @return A read-only list of all instances of {@link ProjectTask}.
     * @since 1.0
     */
    List<ProjectTask> findAll();

    /**
     * Request instance of {@link ProjectTask} by unique id.
     * @param id unique data source integer.
     * @return instance of {@link ProjectTask}.
     * @since 1.0
     */
    ProjectTask findById(int id);

    /**
     * Insert new instance of {@link ProjectTask}
     * @param projectTask newly created instance of Customer.
     * @return true if successful
     * @since 1.0
     */
    boolean insertTask(ProjectTask projectTask);

    /**
     * Update instance of {@link ProjectTask} in datasource
     * @param projectTask instance of {@link ProjectTask}
     * @return true if successful
     * @since 1.0
     */
    boolean updateTask(ProjectTask projectTask);

    /**
     * Delete instance of {@link ProjectTask} in datasource
     * @param projectTask instance of {@link ProjectTask}
     * @return true if successful
     * @since 1.0
     */
    boolean deleteTask(ProjectTask projectTask);

}
