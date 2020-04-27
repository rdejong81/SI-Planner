package db;

import facade.ISQLConnection;
import facade.ISQLConnectionFactory;
import mysql.MySQLConnection;

import javax.security.auth.login.FailedLoginException;
import java.util.Map;

public class DatabaseFactory implements ISQLConnectionFactory
{

    public Map<String,Integer> getDatabaseDrivers(){
        return Map.of(
                "MySQL",1
        );
    }

    public ISQLConnection SQLFactoryCreate(int type, String server, String database, String user, String password) throws FailedLoginException
    {
        try
        {
            switch (type)
            {
                case 1:
                    return new MySQLConnection(server, database, user,password);
            }
        } catch (Exception e){
            throw new FailedLoginException(e.getMessage());
        }

        return null;
    }

}
