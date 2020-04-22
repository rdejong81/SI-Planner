package data;

import facade.ISQLConnection;
import facade.ISQLUpdatable;

import java.util.*;

abstract public class DataEntity implements ISQLUpdatable
{
    final private String tableName;
    protected Integer id;
    protected ISQLConnection sqlConnection;


    DataEntity(ISQLConnection sqlConnection, int id, String tableName)
    {
        this.id = id;
        this.tableName = tableName;
        this.sqlConnection = sqlConnection;
        loadEntityData();

    }

    final protected void loadEntityData(){
            sqlConnection.selectEntity(this);
    }

    abstract public Map<String, Object> readFields(); // used in saving data, or iterating through value data.

    final public String getTableName()
    {
        return tableName;
    }

    final public int getId()
    {
        return id;
    }



}
