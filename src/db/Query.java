package db;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class Query
{
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;
    private ArrayList<HashMap<String,Object>> rows;

    public static final int COL_VARCHAR = 12;
    public static final int COL_INTEGER = 4;

    public Query(SQLConnection db, String statement) throws SQLException
    {
        this.statement = db.getConnection().createStatement();
        this.resultSet = this.statement.executeQuery(statement);
        this.resultSetMetaData = resultSet.getMetaData();
        rows = new ArrayList<>();
        while(resultSet.next()){
            HashMap<String,Object> rowMap = new HashMap();
            for(int i=0;i<resultSetMetaData.getColumnCount();i++){

                switch(resultSetMetaData.getColumnType(i+1))
                {
                    case COL_VARCHAR:
                        rowMap.put(resultSetMetaData.getColumnLabel(i+1), resultSet.getString(i+1));
                        break;
                    case COL_INTEGER:
                        rowMap.put(resultSetMetaData.getColumnLabel(i+1), resultSet.getInt(i+1));
                        break;
                }
            }
            rows.add(rowMap);
        }
    }

    public String getString(String column) throws SQLException
    {
        return resultSet.getString(column);
    }

    public ArrayList<HashMap<String,Object>> getRows()
    {
        return rows;
    }
}