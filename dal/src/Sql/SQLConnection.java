package Sql;

import Data.IDataSource;

import java.sql.*;

abstract public class SQLConnection implements IDataSource
{

    private String connectString;
    private String database;
    private String user;
    private String server;
    private String password;
    private Connection connection;

    protected SQLConnection(String server, String database, String user, String password)
    {
        this.user = user;
        this.password = password;
        this.server = server;
        this.database = database;
        this.connectString = this.getConnectString();
    }

    final protected void connectDatabase() throws SQLException
    {
        this.connection = DriverManager.getConnection(this.connectString,this.getUser(),this.getPassword());
    }

    abstract public String getConnectString();

    final public String getDatabase()
    {
        return database;
    }

    final public String getUser()
    {
        return user;
    }

    final public String getServer()
    {
        return server;
    }

    final public String getPassword()
    {
        return password;
    }

    final public Connection getConnection()
    {
        return connection;
    }



}
