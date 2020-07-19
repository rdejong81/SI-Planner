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
