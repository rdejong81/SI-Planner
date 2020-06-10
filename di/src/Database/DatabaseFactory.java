package Database;

import Data.IDataSource;
import Facade.EDataSourceConnection;
import Facade.ISQLConnectionFactory;
import MySQL.MySQLConnection;
import Sqlite.SqliteConnection;

import javax.security.auth.login.FailedLoginException;
import java.util.Map;

public class DatabaseFactory implements ISQLConnectionFactory
{

    public Map<String,Integer> getDatabaseDrivers(){
        return Map.of(
                "MySQL",1,
                "Sqlite",2
        );
    }

    public IDataSource SQLFactoryCreate(int type, String server, String database, String user, String password) throws EDataSourceConnection
    {
        try
        {
            switch (type)
            {
                case 1:
                    return new MySQLConnection(server, database, user,password);
                case 2:
                    return new SqliteConnection(server,database,user,password);
            }
        } catch (EDataSourceConnection e){
            System.out.println(e.getMessage());
            throw new EDataSourceConnection(e.getReason(),e.getMessage());
        }

        return null;
    }

}
