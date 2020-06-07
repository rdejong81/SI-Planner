package MySQL;

import Data.*;
import Facade.AppFacade;
import Facade.EDataSourceConnection;
import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import Sql.QueryResult;
import Sql.SQLConnection;
import Timeregistration.ITimeregistrationDAO;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.io.InputStream;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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

    public MySQLConnection(String server, String database, String user, String password) throws EDataSourceConnection
    {
        super(server, database, user, password);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e)
        {
            throw new EDataSourceConnection(DaoResult.DAO_MISSING,e.getMessage());
        }

        try
        {
            this.connectDatabase();
            // make sure to use correct database
            new QueryResult(this, "use " + database + ";");

            //check schema

            if (!checkTableExists("employees", database))
            {
                //import schema
                InputStream schema = MySQLConnection.class.getResourceAsStream("SIPlanner.sql");
                importSQL(schema);

            }
        } catch (CommunicationsException e)
        {
            throw new EDataSourceConnection(DaoResult.DAO_MISSING,e.getMessage());
        }
        catch (SQLException e)
        {
            throw new EDataSourceConnection(DaoResult.DS_DISCONNECTED,e.getMessage());
        }
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

    private boolean checkTableExists(String tableName,String database) throws SQLException
    {
        boolean tExists = false;
        try (ResultSet rs = connection.getMetaData().getTables(database, database, tableName, null)) {
            while (rs.next()) {
                String tName = rs.getString("TABLE_NAME");
                if (tName != null && tName.equals(tableName)) {
                    tExists = true;
                    break;
                }
            }
        }
        return tExists;
    }

    private void importSQL(InputStream in) throws SQLException
    {
        Scanner s = new Scanner(in);
        s.useDelimiter("(;(\r)?\n)|((\r)?\n)?(--)?.*(--(\r)?\n)");
        Statement st = null;
        try
        {
            st = connection.createStatement();
            while (s.hasNext())
            {
                String line = s.next();
                if (line.startsWith("/*!") && line.endsWith("*/"))
                {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0)
                {
                    try {
                        st.execute(line);
                    } catch (Exception e)
                    {

                    }

                }
            }
        } catch (Exception e)
        {

        }
        finally
        {
            if (st != null) st.close();
        }
    }

}
