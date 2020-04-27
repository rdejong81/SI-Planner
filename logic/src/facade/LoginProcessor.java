package facade;

import data.Customer;
import data.DataEntityList;
import data.Employee;

import javax.security.auth.login.FailedLoginException;

import static data.DataEntityList.allCustomers;
import static data.Employee.TABLE_EMPLOYEES;
import static data.Employee.TABLE_EMPLOYEES_ROW_SQLLOGIN;
import static data.DataEntityList.allEmployees;

// class is package private
class LoginProcessor implements ILoginProcessor
{
    private ISQLConnection sqlConnection;
    private ISQLConnectionFactory sqlConnectionFactory;
    private boolean connected;

    LoginProcessor(ILoginController loginController, ISQLConnectionFactory sqlConnectionFactory) {

        this.sqlConnectionFactory = sqlConnectionFactory;

        for(String dbType : sqlConnectionFactory.getDatabaseDrivers().keySet())
        { // populate available database drivers
            loginController.addDatabaseType(dbType,sqlConnectionFactory.getDatabaseDrivers().get(dbType));
        }

        loginController.showAndWait(this);
        connected = !loginController.getCancelled() && sqlConnection != null;
    }

    @Override
    public boolean tryLogin(ILoginController loginController)
    {
        try
        {
            sqlConnection = sqlConnectionFactory.SQLFactoryCreate(loginController.getDBType(),
                    loginController.getServer(), loginController.getDatabase(),
                    loginController.getUserName(), loginController.getPassword());

        } catch (FailedLoginException e){
            loginController.loginError("Login failed: "+e.getMessage());
            return false;
        } catch (Exception e){
            loginController.loginError(e.getMessage());
            return false;
        }
        if(sqlConnection == null)
        {
            loginController.loginError("Unable to connect.");
            return false;
        }
        // fetch all employees
        allEmployees = new DataEntityList<>(sqlConnection,Employee.class);
        allCustomers = new DataEntityList<>(sqlConnection,Customer.class);

        // first user of the database automatically gets to be the first employee
        if (allEmployees.getEntities().isEmpty())
        {
            allEmployees.addEntity(new Employee(sqlConnection,loginController.getUserName(), loginController.getUserName()));
            // todo: remove console output from logic layer
            System.out.printf("First application user %s created as employee.\n", loginController.getUserName());
            return true;
        }

        for(Employee employee : allEmployees.getEntities())
        {
            if(employee.getSqlLogin().equals(loginController.getUserName())) return true; // login successfull
        }

        IQueryResult queryResult = sqlConnection.selectAllRowsIf(TABLE_EMPLOYEES,TABLE_EMPLOYEES_ROW_SQLLOGIN,loginController.getUserName());

        loginController.loginError("Unknown application user - sql login succeeded but not known as employee to use this application.");
        return false;// login not allowed
    }
    protected ISQLConnection getSqlConnection(){
        return sqlConnection;
    }
    protected boolean getConnected()
    {
        return connected;
    }
}
