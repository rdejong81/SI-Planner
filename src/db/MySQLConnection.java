package db;

import java.sql.SQLException;

public class MySQLConnection extends SQLConnection
{
    final public static String MYSQL_TYPE_STR="MySQL";
    public MySQLConnection(String server,String database,String user,String password) throws SQLException, ClassNotFoundException
    {
        super(server,database,user,password);
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connectDatabase();
    }

    @Override
    public String getConnectString()
    {
        return String.format("jdbc:mysql://%s:3306/%s",this.getServer(),this.getDatabase());
    }

    @Override
    public Query selectAllRows(String table) throws SQLException
    {
        return new Query(this,String.format("select * from %s;",table));
    }

    @Override
    public Query selectAllRowsLike(String table, String column, String pattern) throws SQLException
    {
        return new Query(this,String.format("select * from %s where %s like '%s'",table,column,pattern));
    }

}
