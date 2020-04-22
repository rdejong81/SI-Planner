package facade;

public class DoLogin
{
    LoginProcessor loginProcessor;
    ISQLConnection sqlConnection;

    public boolean DoLogin (ILoginController loginController)
    {
        loginProcessor = new LoginProcessor(loginController);
        sqlConnection = loginProcessor.getSqlConnection();

        return loginProcessor.getConnected();
    }

    public ISQLConnection getSqlConnection(){
        return sqlConnection;
    }
}
