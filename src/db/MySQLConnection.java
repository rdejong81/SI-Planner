package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MySQLConnection extends SQLConnection
{
    final public static String MYSQL_TYPE_STR = "MySQL";

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
    public Query selectAllRows(String table) throws SQLException
    {
        return new Query(this, String.format("select * from %s;", table));
    }

    @Override
    public Query selectAllRowsLike(String table, String column, String pattern) throws SQLException
    {
        return new Query(this, String.format("select * from %s where %s like '%s'", table, column, pattern));
    }

    @Override
    public Query insertRow(String table, HashMap<String, Object> row) throws SQLException
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
                values.add("'" + (String) value + "'");
            }
        }

        String statement = String.format("insert into %s (%s) values (%s)", table,
                String.join(",", row.keySet()),
                String.join(",", values));
        return new Query(this, statement);
    }

    @Override
    public void createUser(String username, String password) throws SQLException
    {
        String statement = String.format("create user '%s'@'%%' identified by '%s'", username, password);
        String statement2 = String.format("grant all privileges on %s.* to '%s'@'%%'", super.getDatabase(), username);
        new Query(this, statement);
        new Query(this, statement2);
        new Query(this,"FLUSH PRIVILEGES");
    }

    @Override
    public void deleteRow(String table, int id) throws SQLException
    {
        new Query(this,String.format("delete from %s where id=%d",table,id));
    }

    @Override
    public boolean canCreateUser()
    {
        try
        {
            Query query = new Query(this, "show grants for CURRENT_USER");

            for(HashMap<String, Object> row : query.getRows()){
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
