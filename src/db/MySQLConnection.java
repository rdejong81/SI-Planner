package db;

import java.sql.SQLException;

public class MySQLConnection extends SQLConnection
{
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

    public Query selectAllRows(String table) throws SQLException
    {
        return new Query(this,String.format("select * from %s;",table));
    }

}
