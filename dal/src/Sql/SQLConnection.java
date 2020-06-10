package Sql;

import Data.IDataSource;

import java.sql.*;

abstract public class SQLConnection implements IDataSource
{

    private final String connectString;
    private final String database;
    private final String user;
    private final String server;
    private final String password;
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

    abstract public Statement executeStatement(String statementText);

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
