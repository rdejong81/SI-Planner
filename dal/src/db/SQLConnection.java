package db;

import facade.ISQLConnection;
import facade.ISQLUpdatable;

import java.sql.*;
import java.util.Map;

abstract public class SQLConnection implements ISQLConnection
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

    public abstract QueryResult selectAllRows(String table) throws SQLException;
    public abstract QueryResult selectIds(String table);
    public abstract QueryResult selectAllRowsIf(String table, String column, Object isValue);
    public abstract QueryResult selectAllRowsLike(String table, String column, String pattern) throws SQLException;
    public abstract void selectEntity(ISQLUpdatable entity);
    public abstract void selectEntity(ISQLUpdatable entity, String column);

    public abstract QueryResult updateRow(String table, Integer id, Map<String, Object> row) throws SQLException;
    public abstract QueryResult updateField(String table,Integer id, String column, Object value);

    public abstract QueryResult insertRow(String table, Map<String,Object> row);

    public abstract void createUser(String username, String password);

    public abstract void deleteRow(String table, int id);

    public abstract boolean canCreateUser();


}
