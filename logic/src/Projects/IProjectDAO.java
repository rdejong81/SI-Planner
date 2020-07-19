/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package Projects;

import Data.DaoResult;

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
    DaoResult insertProject(Project project);

    /**
     * Update instance of {@link Project} in datasource
     * @param project instance of {@link Project}
     * @return true if successful
     * @since 1.0
     */
    DaoResult updateProject(Project project);

    /**
     * Delete instance of {@link Project} in datasource
     * @param project instance of {@link Project}
     * @return true if successful
     * @since 1.0
     */
    DaoResult deleteProject(Project project);

}
