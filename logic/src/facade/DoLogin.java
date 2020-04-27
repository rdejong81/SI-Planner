package facade;

public class DoLogin
{
    LoginProcessor loginProcessor;
    ISQLConnection sqlConnection;

    public boolean DoLogin (ILoginController loginController,ISQLConnectionFactory sqlConnectionFactory)
    {
        loginProcessor = new LoginProcessor(loginController,sqlConnectionFactory);
        sqlConnection = loginProcessor.getSqlConnection();

        return loginProcessor.getConnected();
    }

    public ISQLConnection getSqlConnection(){
        return sqlConnection;
    }
}
