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

package Timeregistration;

import Data.DaoResult;

import java.util.List;

/**
 * Retrieve, create and update {@link Timeregistration} objects from data source.
 * @author Richard de Jong
 * @version 1.0
 * @since 1.0
 * @see Timeregistration
 */
public interface ITimeregistrationDAO
{
    /**
     * Request all instances of {@link Timeregistration} in datasource.
     * @return A read-only list of all instances of {@link Timeregistration}.
     * @since 1.0
     */
    List<Timeregistration> findAll();

    /**
     * Request instance of {@link Timeregistration} by unique id.
     * @param id unique data source integer.
     * @return instance of {@link Timeregistration}.
     * @since 1.0
     */
    Timeregistration findById(int id);

    /**
     * Insert new instance of {@link Timeregistration}
     * @param timeregistration newly created instance of Customer.
     * @return true if successful
     * @since 1.0
     */
    DaoResult insertTimeregistration(Timeregistration timeregistration);

    /**
     * Update instance of {@link Timeregistration} in datasource
     * @param timeregistration instance of {@link Timeregistration}
     * @return true if successful
     * @since 1.0
     */
    DaoResult updateTimeregistration(Timeregistration timeregistration);

    /**
     * Delete instance of {@link Timeregistration} in datasource
     * @param timeregistration instance of {@link Timeregistration}
     * @return true if successful
     * @since 1.0
     */
    DaoResult deleteTimeregistration(Timeregistration timeregistration);
}
