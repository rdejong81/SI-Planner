package facade;


import Data.Employee;
import Data.IDataSource;

import javax.security.auth.login.FailedLoginException;
import java.util.List;


// class is package private
class LoginProcessor implements ILoginProcessor
{
    private ISQLConnectionFactory sqlConnectionFactory;
    IDataSource dataSource;

    LoginProcessor(ILoginController loginController, ISQLConnectionFactory sqlConnectionFactory) {

        this.sqlConnectionFactory = sqlConnectionFactory;

        for(String dbType : sqlConnectionFactory.getDatabaseDrivers().keySet())
        { // populate available database drivers
            loginController.addDatabaseType(dbType,sqlConnectionFactory.getDatabaseDrivers().get(dbType));
        }

        loginController.showAndWait(this);
    }

    @Override
    public boolean tryLogin(ILoginController loginController)
    {
        try
        {
            dataSource = sqlConnectionFactory.SQLFactoryCreate(loginController.getDBType(),
                    loginController.getServer(), loginController.getDatabase(),
                    loginController.getUserName(), loginController.getPassword());

        } catch (FailedLoginException e){
            loginController.loginError("Login failed: "+e.getMessage());
            return false;
        } catch (Exception e){
            loginController.loginError(e.getMessage());
            return false;
        }
        if(dataSource == null)
        {
            loginController.loginError("Unable to connect.");
            return false;
        }

        List<Employee> employeeList = dataSource.employeeDao().findAll();

        // first user of the database automatically gets to be the first employee
        if (employeeList.isEmpty())
        {
            dataSource.employeeDao().insertEmployee(new Employee(dataSource,0,loginController.getUserName(), loginController.getUserName()));
            // todo: remove console output from logic layer
            System.out.printf("First application user %s created as employee.\n", loginController.getUserName());
            return true;
        }

        for(Employee employee : employeeList)
        {
            if(employee.getSqlLoginName().equals(loginController.getUserName())) return true;
        }

        loginController.loginError("Unknown application user - sql login succeeded but not known as employee to use this application.");
        dataSource = null;
        return false;// login not allowed
    }

    public IDataSource getDataSource()
    {
        return dataSource;
    }
}
