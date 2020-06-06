package MySQL;

import Data.IAttributeDAO;
import Data.ICustomerDAO;
import Data.IEmployeeDAO;
import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import Sql.SQLConnection;
import Timeregistration.ITimeregistrationDAO;

import java.sql.SQLException;

public class MySQLConnection extends SQLConnection
{
    private final CustomerMySQLDAO customerMySQLDAO;
    private final EmployeeMySQLDAO employeeMySQLDAO;
    private final ProjectMySQLDAO projectMySQLDAO;
    private final ProjectTaskMySQLDAO taskMySQLDAO;
    private final PlanningMySQLDAO planningMySQLDAO;
    private final TimeregistrationMySQLDAO timeregistrationMySQLDAO;
    private final AttributeDefinitionMySQLDAO attributeDefinitionMySQLDAO;
    private final AttributeMySQLDAO attributeMySQLDAO;

    public MySQLConnection(String server, String database, String user, String password) throws SQLException, ClassNotFoundException
    {
        super(server, database, user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connectDatabase();
        customerMySQLDAO = new CustomerMySQLDAO(this);
        employeeMySQLDAO = new EmployeeMySQLDAO(this);
        projectMySQLDAO = new ProjectMySQLDAO(this);
        taskMySQLDAO = new ProjectTaskMySQLDAO(this);
        planningMySQLDAO = new PlanningMySQLDAO(this);
        timeregistrationMySQLDAO = new TimeregistrationMySQLDAO(this);
        attributeDefinitionMySQLDAO = new AttributeDefinitionMySQLDAO(this);
        attributeMySQLDAO = new AttributeMySQLDAO(this);
    }

    @Override
    public String getConnectString()
    {
        return String.format("jdbc:mysql://%s:3306/%s", this.getServer(), this.getDatabase());
    }



    @Override
    public ICustomerDAO customerDao()
    {
        return customerMySQLDAO;
    }

    @Override
    public IEmployeeDAO employeeDao()
    {
        return employeeMySQLDAO;
    }

    @Override
    public IProjectDAO projectDao()
    {
        return projectMySQLDAO;
    }

    @Override
    public IProjectTaskDAO taskDao()
    {
        return taskMySQLDAO;
    }

    @Override
    public IPlanningDAO planningDao()
    {
        return planningMySQLDAO;
    }
    public ITimeregistrationDAO timeregistrationDao()
    {
        return timeregistrationMySQLDAO;
    }

    @Override
    public IAttributeDAO attributeDao()
    {
        return attributeMySQLDAO;
    }

    @Override
    public IAttributeDAO attributeDefinitionDao()
    {
        return attributeDefinitionMySQLDAO;
    }


}
