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

package MySQL;

import Data.*;
import Facade.EDataSourceConnection;
import Planning.IPlanningDAO;
import Projects.IProjectDAO;
import Projects.IProjectTaskDAO;
import Sql.QueryResult;
import Sql.SQLConnection;
import Timeregistration.ITimeregistrationDAO;
import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
    private final DocumentTemplateMySQLDAO documentTemplateMySQLDAO;

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


        customerMySQLDAO = new CustomerMySQLDAO(this);
        employeeMySQLDAO = new EmployeeMySQLDAO(this);
        projectMySQLDAO = new ProjectMySQLDAO(this);
        taskMySQLDAO = new ProjectTaskMySQLDAO(this);
        planningMySQLDAO = new PlanningMySQLDAO(this);
        timeregistrationMySQLDAO = new TimeregistrationMySQLDAO(this);
        attributeDefinitionMySQLDAO = new AttributeDefinitionMySQLDAO(this);
        attributeMySQLDAO = new AttributeMySQLDAO(this);
        documentTemplateMySQLDAO = new DocumentTemplateMySQLDAO(this);
    }

    @Override
    public String getConnectString()
    {
        return String.format("jdbc:mysql://%s:3306/%s", this.getServer(), this.getDatabase());
    }

    @Override
    public Statement executeStatement(String statementText)
    {
        try
        {
            Statement statement = getConnection().createStatement();
            statement.execute(statementText, Statement.RETURN_GENERATED_KEYS);
            return statement;
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public void closeConnection()
    {
        try
        {
            getConnection().close();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void openConnection() throws EDataSourceConnection
    {
        try
        {
            this.connectDatabase();
            // make sure to use correct database
            new QueryResult(this, "use " + getDatabase() + ";");

            //check schema
            if (!checkTableExists("employees", getDatabase()))
            {
                //import schema
                InputStream schema = MySQLConnection.class.getResourceAsStream("SIPlanner.sql");
                importSQL(schema);

            }
            if(!checkColumnExists("time","synckey"))
            {
                getConnection().createStatement().execute("ALTER TABLE time ADD COLUMN synckey varchar(255);");
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
    public Set<DSCapability> getCapabilities()
    {
        DSCapability capabilities[] = {DSCapability.BOOLEAN,DSCapability.DATABASENAME,DSCapability.LOGIN,DSCapability.SERVERLOCATION};

        return new HashSet<>(Arrays.asList(capabilities));
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
        return documentTemplateMySQLDAO;
    }

    private boolean checkColumnExists(String tableName, String column) throws SQLException
    {
        String query = String.format("SHOW COLUMNS FROM `%s` LIKE '%s';",tableName,column);
        Statement statement = getConnection().createStatement();
        return statement.execute(query);
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
