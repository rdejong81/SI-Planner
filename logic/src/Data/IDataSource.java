package Data;

import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import Timeregistration.ITimeregistrationDAO;

public interface IDataSource
{

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
