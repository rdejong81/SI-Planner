package db;

import db.SQLConnection;
import mysql.MySQLConnection;

import javax.security.auth.login.FailedLoginException;
import java.util.Map;

public class DatabaseFactory
{
    final public static Map<String,Integer> DATABASE_DRIVERS = Map.of(
            "MySQL",1
    );

    static public SQLConnection SQLFactoryCreate(int type,String server,String database,String user, String password) throws FailedLoginException
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
