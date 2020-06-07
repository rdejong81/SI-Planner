package Data;

import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import Projects.MockProjectDao;
import Timeregistration.ITimeregistrationDAO;

public class MockDataSource implements IDataSource
{
    private final IEmployeeDAO employeeDAO;
    private final ICustomerDAO customerDAO;
    private final IProjectDAO projectDAO;
    public MockDataSource()
    {
        employeeDAO = new MockEmployeeDao(this);
        customerDAO = new MockCustomerDao(this);
        projectDAO = new MockProjectDao(this);
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
        return null;
    }

    @Override
    public IPlanningDAO planningDao()
    {
        return null;
    }

    @Override
    public ITimeregistrationDAO timeregistrationDao()
    {
        return null;
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
}