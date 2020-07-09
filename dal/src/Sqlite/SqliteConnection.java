package Sqlite;

import Data.*;
import Facade.EDataSourceConnection;
import MySQL.*;
import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import Sql.QueryResult;
import Sql.SQLConnection;
import Timeregistration.ITimeregistrationDAO;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;
import org.sqlite.SQLiteJDBCLoader;

import java.io.File;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class SqliteConnection extends SQLConnection
{
    private final CustomerSqliteDAO customerMySQLDAO;
    private final EmployeeSqliteDAO employeeMySQLDAO;
    private final ProjectSqliteDAO projectMySQLDAO;
    private final ProjectTaskSqliteDAO taskMySQLDAO;
    private final PlanningSqliteDAO planningMySQLDAO;
    private final TimeregistrationSqliteDAO timeregistrationMySQLDAO;
    private final AttributeDefinitionSqliteDAO attributeDefinitionMySQLDAO;
    private final AttributeSqliteDAO attributeMySQLDAO;
    private final DocumentTemplateSqliteDAO documentTemplateSqliteDAO;

    public SqliteConnection(String server, String database, String user, String password) throws EDataSourceConnection
    {
        super(server, database, user, password);
        try
        {
            DriverManager.registerDriver(new org.sqlite.JDBC());
        } catch (SQLException e)
        {
            throw new EDataSourceConnection(DaoResult.DAO_MISSING,e.getMessage());
        }


        customerMySQLDAO = new CustomerSqliteDAO(this);
        employeeMySQLDAO = new EmployeeSqliteDAO(this);
        projectMySQLDAO = new ProjectSqliteDAO(this);
        taskMySQLDAO = new ProjectTaskSqliteDAO(this);
        planningMySQLDAO = new PlanningSqliteDAO(this);
        timeregistrationMySQLDAO = new TimeregistrationSqliteDAO(this);
        attributeDefinitionMySQLDAO = new AttributeDefinitionSqliteDAO(this);
        attributeMySQLDAO = new AttributeSqliteDAO(this);
        documentTemplateSqliteDAO = new DocumentTemplateSqliteDAO(this);
    }

    @Override
    public void closeConnection()
    {
        try
        {
            getConnection().close();
        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    @Override
    public void openConnection() throws EDataSourceConnection
    {
        try
        {
            this.connectDatabase();
            // make sure to use correct database
            //new QueryResult(this, "use " + database + ";");

            //check schema

            if (!checkTableExists("employees", getDatabase()))
            {
                //import schema
                InputStream schema = SqliteConnection.class.getResourceAsStream("SIPlanner.sql");
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
    }

    @Override
    public String getConnectString()
    {
        String userHome = System.getProperty("user.home");
        File file = new File(String.format("%s/.siplanner",userHome));
        if(!file.exists())
            file.mkdir();
        return String.format("jdbc:sqlite:%s/.siplanner/%s.db",userHome, this.getDatabase());
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

    @Override
    public IDocumentTemplateDAO documentTemplateDao()
    {
        return documentTemplateSqliteDAO;
    }


    private boolean checkTableExists(String tableName,String database) throws SQLException
    {
        boolean tExists = false;
        try (ResultSet rs = getConnection().getMetaData().getTables(database, database, tableName, null)) {
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
            st = getConnection().createStatement();
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
                        System.out.println(e.getMessage());

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

    @Override
    public Statement executeStatement(String statementText)
    {
        try
        {
            Statement statement = getConnection().createStatement();
            statement.execute(statementText);
            return statement;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Set<DSCapability> getCapabilities()
    {
        DSCapability capabilities[] = {DSCapability.DATABASENAME};

        return new HashSet<>(Arrays.asList(capabilities));
    }

}
