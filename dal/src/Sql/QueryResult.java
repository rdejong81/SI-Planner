package Sql;

import Data.DSCapability;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

public class QueryResult
{
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;
    private ArrayList<HashMap<String, Object>> rows;
    private long createdKey;
    private Exception lastError;

    public QueryResult(SQLConnection db, String statement)
    {
        rows = new ArrayList<>();

        try
        {
            this.statement = db.executeStatement(statement);
            this.resultSet = this.statement.getResultSet();
            if (resultSet == null)
            {
                resultSet = this.statement.getGeneratedKeys();
                if (resultSet != null && resultSet.next())
                { // This is an insert result, get created result id.
                    createdKey = resultSet.getLong(1);
                }
            }
            this.resultSetMetaData = resultSet.getMetaData();


            while (resultSet.next())
            {
                HashMap<String, Object> rowMap = new HashMap();
                for (int i = 1; i - 1 < resultSetMetaData.getColumnCount(); i++)
                {
                    switch (resultSetMetaData.getColumnType(i))
                    {
                        case Types.BLOB:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getBytes(i));
                            break;
                        case Types.VARCHAR:
                        case Types.LONGVARCHAR:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getString(i));
                            break;
                        case Types.BIT:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getBoolean(i));
                            break;
                        case Types.INTEGER:
                        case Types.TINYINT:
                            if(resultSetMetaData.getPrecision(i) == 1 &&
                                    !db.getCapabilities().contains(DSCapability.BOOLEAN))
                                rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getInt(i) == 1 ? true : false); else
                                    rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getInt(i));
                            break;
                        case Types.BIGINT:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getLong(i));
                            break;
                        case Types.DOUBLE:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getDouble(i));
                            break;
                        case Types.TIMESTAMP:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getTimestamp(i) != null ? resultSet.getTimestamp(i).toLocalDateTime() : null);
                            break;

                    }
                }
                rows.add(rowMap);
            }
            lastError = null;
        } catch (Exception e){
            System.out.println(statement +"\n"+e.getMessage());
            lastError = e;  // for use with exception handling code
        }

    }

    public long getCreatedKey()
    {
        return createdKey;
    }

    public Exception getLastError()
    {
        return lastError;
    }



    final public Collection<HashMap<String, Object>> getRows()
    {
        return Collections.unmodifiableCollection(rows);
    }

}
