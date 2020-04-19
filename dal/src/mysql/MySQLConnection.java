package mysql;

import db.ISQLUpdatable;
import db.QueryResult;
import db.SQLConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MySQLConnection extends SQLConnection
{
    final public static String MYSQL_TYPE_STR = "mysql";

    public MySQLConnection(String server, String database, String user, String password) throws SQLException, ClassNotFoundException
    {
        super(server, database, user, password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connectDatabase();
    }

    @Override
    public String getConnectString()
    {
        return String.format("jdbc:mysql://%s:3306/%s", this.getServer(), this.getDatabase());
    }

    @Override
    public QueryResult selectIds(String table) throws SQLException
    {
        return new QueryResult(this, String.format("select id from %s;", table));
    }

    @Override
    public QueryResult selectAllRows(String table) throws SQLException
    {
        return new QueryResult(this, String.format("select * from %s;", table));
    }

    @Override
    public QueryResult selectAllRowsLike(String table, String column, String pattern) throws SQLException
    {
        return new QueryResult(this, String.format("select * from %s where %s like '%s'", table, column, pattern));
    }

    @Override
    public void selectEntity(ISQLUpdatable entity) throws SQLException
    {
        new QueryResult(this, String.format("select * from %s where id=%d", entity.getTableName(), entity.getId()),entity);
    }

    @Override
    public void selectEntity(ISQLUpdatable entity,String column) throws SQLException
    {
        new QueryResult(this, String.format("select * from %s where %s=%d", entity.getTableName(),column, entity.getId()),entity);
    }

    @Override
    public QueryResult updateRow(String table, Integer id, Map<String, Object> row) throws SQLException
    {
        ArrayList<String> valuePairs = new ArrayList<>();
        for (String column : row.keySet()){
            if(row.get(column) instanceof Integer) valuePairs.add(String.format("%s=%d",column,row.get(column)));
            if(row.get(column) instanceof String) valuePairs.add(String.format("%s='%s'",column,row.get(column)));
        }
        return new QueryResult(this,String.format("update %s set %s where id=%d", table, String.join(",",valuePairs), id));
    }

    @Override
    public QueryResult updateField(String table, Integer id, String column, Object value) throws SQLException
    {
        switch(value.getClass().getSimpleName())
        {
            case "Integer":return new QueryResult(this,String.format("update %s set %s=%d where id=%d", table, column,value, id));
            default:return new QueryResult(this,String.format("update %s set %s='%s' where id=%d", table, column,value, id));
        }

    }

    @Override
    public QueryResult insertRow(String table, Map<String, Object> row) throws SQLException
    {
        ArrayList<String> values = new ArrayList<>();
        for (Object value : row.values())
        {
            if (value instanceof Integer)
            {
                values.add(value.toString());
            }
            if (value instanceof String)
            {
                values.add("'" + value + "'");
            }
        }

        String statement = String.format("insert into %s (%s) values (%s)", table,
                String.join(",", row.keySet()),
                String.join(",", values));
        return new QueryResult(this, statement);
    }

    @Override
    public void createUser(String username, String password) throws SQLException
    {
        String statement = String.format("create user '%s'@'%%' identified by '%s'", username, password);
        String statement2 = String.format("grant all privileges on %s.* to '%s'@'%%'", super.getDatabase(), username);
        new QueryResult(this, statement);
        new QueryResult(this, statement2);
        new QueryResult(this,"FLUSH PRIVILEGES");
    }

    @Override
    public void deleteRow(String table, int id) throws SQLException
    {
        new QueryResult(this,String.format("delete from %s where id=%d",table,id));
    }

    @Override
    public boolean canCreateUser()
    {
        try
        {
            QueryResult queryResult = new QueryResult(this, "show grants for CURRENT_USER");

            for(HashMap<String, Object> row : queryResult.getRows()){
                for(Object value : row.values())
                {
                    if(value.toString().contains("CREATE USER")) return true;
                }

            }

        } catch (Exception e)
        {
            return false;
        }

        return false;
    }


}
