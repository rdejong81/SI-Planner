package mysql;

import Data.ICustomerDAO;
import Data.IEmployeeDAO;
import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import db.SQLConnection;

import java.sql.SQLException;

public class MySQLConnection extends SQLConnection
{
    CustomerMySQLDAO customerMySQLDAO;
    EmployeeMySQLDAO employeeMySQLDAO;
    ProjectMySQLDAO projectMySQLDAO;
    ProjectTaskMySQLDAO taskMySQLDAO;
    PlanningMySQLDAO planningMySQLDAO;

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


}
