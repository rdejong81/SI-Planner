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

package Data;

import java.util.List;

/**
 * Retrieve, create and update {@link Customer} objects from data source.
 * @author Richard de Jong
 * @version 1.0
 * @since 1.0
 * @see Customer
 */
public interface ICustomerDAO
{
    /**
     * Request all instances of {@link Customer} in datasource.
     * @return A read-only list of all instances of {@link Customer}.
     * @since 1.0
     */
    List<Customer> findAll();

    /**
     * Request instance of {@link Customer} by unique id.
     * @param id unique data source integer.
     * @return instance of {@link Customer}.
     * @since 1.0
     */
    Customer findById(int id);

    /**
     * Insert new instance of {@link Customer}
     * @param customer newly created instance of Customer.
     * @return true if successful
     * @since 1.0
     */
    DaoResult insertCustomer(Customer customer);

    /**
     * Update instance of {@link Customer} in datasource
     * @param customer instance of {@link Customer}
     * @return true if successful
     * @since 1.0
     */
    DaoResult updateCustomer(Customer customer);

    /**
     * Delete instance of {@link Customer} in datasource
     * @param customer instance of {@link Customer}
     * @return true if successful
     * @since 1.0
     */
    DaoResult deleteCustomer(Customer customer);

    /**
     * Link {@link Employee} to {@link Customer} in datasource
     * @param customer instance to link
     * @param employee instance to link
     * @return true if successful
     * @since 1.0
     */
    DaoResult linkEmployee(Customer customer, Employee employee);
    /**
     * Unlink {@link Employee} from {@link Customer} in datasource
     * @param customer instance to unlink
     * @param employee instance to unlink
     * @return true if successful
     * @since 1.0
     */
    DaoResult unlinkEmployee(Customer customer, Employee employee);
}
