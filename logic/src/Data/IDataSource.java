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

import Facade.EDataSourceConnection;
import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import Timeregistration.ITimeregistrationDAO;

import java.util.Set;

public interface IDataSource
{
    void closeConnection();
    void openConnection() throws EDataSourceConnection;
    Set<DSCapability> getCapabilities();

    // DAO pattern
    ICustomerDAO customerDao();
    IEmployeeDAO employeeDao();
    IProjectDAO projectDao();
    IProjectTaskDAO taskDao();
    IPlanningDAO planningDao();
    ITimeregistrationDAO timeregistrationDao();
    IAttributeDAO attributeDao();
    IAttributeDAO attributeDefinitionDao();
    IDocumentTemplateDAO documentTemplateDao();

}
