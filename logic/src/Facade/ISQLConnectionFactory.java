package Facade;

import Data.IDataSource;

import javax.security.auth.login.FailedLoginException;
import java.util.Map;

public interface ISQLConnectionFactory
{
    Map<String,Integer> getDatabaseDrivers();
    IDataSource SQLFactoryCreate(int type, String server, String database, String user, String password) throws EDataSourceConnection;
}
