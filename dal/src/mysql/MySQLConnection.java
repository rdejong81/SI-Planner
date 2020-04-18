package mysql;

import db.QueryResult;
import db.SQLConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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
    public QueryResult selectTableColumns(String table) throws SQLException
    {
        QueryResult results = new QueryResult(this,String.format("SHOW COLUMNS FROM %s;",table));
       /* results.getResultSet().beforeFirst();

            while(results.getResultSet().next())
            {
                String columnName = results.getResultSet().getString("Field");
                columns.put()
            }

*/

        return results;
    }

    @Override
    public QueryResult selectAllRowsLike(String table, String column, String pattern) throws SQLException
    {
        return new QueryResult(this, String.format("select * from %s where %s like '%s'", table, column, pattern));
    }

    @Override
    public QueryResult insertRow(String table, HashMap<String, Object> row) throws SQLException
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
