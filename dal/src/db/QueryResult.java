package db;

import facade.IQueryResult;
import facade.ISQLUpdatable;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.*;

public class QueryResult implements IQueryResult
{
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;
    private ArrayList<HashMap<String, Object>> rows;
    private long createdKey;
    private Exception lastError;

    public static final int COL_VARCHAR = 12;
    public static final int COL_INTEGER = 4;
    public static final int COL_BIGINT = -5;
    public static final int COL_MEDIUMTEXT = -1;

    public QueryResult(SQLConnection db, String statement, ISQLUpdatable entity)
    {
        rows = new ArrayList<>();

        try
        {
            this.statement = db.getConnection().createStatement();
            this.statement.execute(statement, Statement.RETURN_GENERATED_KEYS);
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
                        case COL_VARCHAR:
                        case COL_MEDIUMTEXT:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getString(i));
                            if (entity != null)
                                entity.updateField(resultSetMetaData.getColumnLabel(i), resultSet.getString(i));
                            break;
                        case COL_INTEGER:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getInt(i));
                            if (entity != null)
                                entity.updateField(resultSetMetaData.getColumnLabel(i), resultSet.getInt(i));
                            break;
                        case COL_BIGINT:
                            rowMap.put(resultSetMetaData.getColumnLabel(i), resultSet.getLong(i));
                            if (entity != null)
                                entity.updateField(resultSetMetaData.getColumnLabel(i), resultSet.getLong(i));
                            break;
                    }
                }
                rows.add(rowMap);
            }
            lastError = null;
        } catch (Exception e){
            lastError = e;  // for use with exception handling code
        }

    }

    public QueryResult(SQLConnection db, String statement)
    {
        this(db,statement,null);
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
