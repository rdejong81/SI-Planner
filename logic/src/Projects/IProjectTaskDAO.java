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
    DaoResult insertTask(ProjectTask projectTask);

    /**
     * Update instance of {@link ProjectTask} in datasource
     * @param projectTask instance of {@link ProjectTask}
     * @return true if successful
     * @since 1.0
     */
    DaoResult updateTask(ProjectTask projectTask);

    /**
     * Delete instance of {@link ProjectTask} in datasource
     * @param projectTask instance of {@link ProjectTask}
     * @return true if successful
     * @since 1.0
     */
    DaoResult deleteTask(ProjectTask projectTask);

}
