package Projects;

import java.util.List;

/**
 * Retrieve, create and update {@link Project} objects from data source.
 * @author Richard de Jong
 * @version 1.0
 * @since 1.0
 * @see Project
 */
public interface IProjectDAO
{
    /**
     * Request all instances of {@link Project} in datasource.
     * @return A read-only list of all instances of {@link Project}.
     * @since 1.0
     */
    List<Project> findAll();

    /**
     * Request instance of {@link Project} by unique id.
     * @param id unique data source integer.
     * @return instance of {@link Project}.
     * @since 1.0
     */
    Project findById(int id);

    /**
     * Insert new instance of {@link Project}
     * @param project newly created instance of Customer.
     * @return true if successful
     * @since 1.0
     */
    boolean insertProject(Project project);

    /**
     * Update instance of {@link Project} in datasource
     * @param project instance of {@link Project}
     * @return true if successful
     * @since 1.0
     */
    boolean updateProject(Project project);

    /**
     * Delete instance of {@link Project} in datasource
     * @param project instance of {@link Project}
     * @return true if successful
     * @since 1.0
     */
    boolean deleteProject(Project project);

}
