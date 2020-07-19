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

package Planning;

import Data.Customer;
import Data.DaoResult;
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
    DaoResult insertPlanning(Planning planning);

    /**
     * Update instance of {@link Planning} in datasource
     * @param planning instance of {@link Planning}
     * @return true if successful
     * @since 1.0
     */
    DaoResult updatePlanning(Planning planning);

    /**
     * Delete instance of {@link Planning} in datasource
     * @param planning instance of {@link Planning}
     * @return true if successful
     * @since 1.0
     */
    DaoResult deletePlanning(Planning planning);
}
