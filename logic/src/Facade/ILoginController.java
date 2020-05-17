package Facade;

public interface ILoginController
{
    // before show to populate database options in UI
    void addDatabaseType(String type, int typeValue);

    // start UI asking for input
    void showAndWait(ILoginProcessor loginProcessor);

    // during show
    void loginError(String error);
    String getUserName();
    String getPassword();
    String getServer();
    String getDatabase();
    int getDBType();

    // after show to retrieve chosen values from UI
    boolean getCancelled();







}
