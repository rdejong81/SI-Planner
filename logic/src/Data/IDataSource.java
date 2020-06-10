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

}
