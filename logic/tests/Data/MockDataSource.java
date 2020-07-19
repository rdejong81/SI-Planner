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
import Planning.MockPlanningDao;
import Projects.*;
import Timeregistration.ITimeregistrationDAO;
import Timeregistration.MockTimeregistrationDao;

import java.util.HashSet;
import java.util.Set;

public class MockDataSource implements IDataSource
{
    private final IEmployeeDAO employeeDAO;
    private final ICustomerDAO customerDAO;
    private final IProjectDAO projectDAO;
    private final IProjectTaskDAO projectTaskDAO;
    private final ITimeregistrationDAO timeregistrationDAO;
    private final IPlanningDAO planningDAO;

    public MockDataSource()
    {
        employeeDAO = new MockEmployeeDao(this);
        customerDAO = new MockCustomerDao(this);
        projectDAO = new MockProjectDao(this);
        projectTaskDAO = new MockProjectTaskDao(this);
        timeregistrationDAO = new MockTimeregistrationDao(this);
        planningDAO = new MockPlanningDao(this);
    }

    @Override
    public void closeConnection()
    {

    }

    @Override
    public void openConnection() throws EDataSourceConnection
    {

    }

    @Override
    public Set<DSCapability> getCapabilities()
    {

        return new HashSet<DSCapability>();
    }

    @Override
    public ICustomerDAO customerDao()
    {
        return customerDAO;
    }

    @Override
    public IEmployeeDAO employeeDao()
    {
        return employeeDAO;
    }

    @Override
    public IProjectDAO projectDao()
    {
        return projectDAO;
    }

    @Override
    public IProjectTaskDAO taskDao()
    {
        return projectTaskDAO;
    }

    @Override
    public IPlanningDAO planningDao()
    {
        return null;
    }

    @Override
    public ITimeregistrationDAO timeregistrationDao()
    {
        return timeregistrationDAO;
    }

    @Override
    public IAttributeDAO attributeDao()
    {
        return null;
    }

    @Override
    public IAttributeDAO attributeDefinitionDao()
    {
        return null;
    }

    @Override
    public IDocumentTemplateDAO documentTemplateDao()
    {
        return null;
    }
}
