package data;

import db.ISQLUpdatable;
import db.QueryResult;

import java.sql.SQLException;
import java.util.*;

abstract public class DataEntity implements ISQLUpdatable
{
    final private String tableName;
    protected Integer id;

    DataEntity(int id, String tableName)
    {
        this.id = id;
        this.tableName = tableName;
        loadEntityData();

    }

    final protected void loadEntityData(){
        try
        {
            program.AppFacade.db.selectEntity(this);

        } catch(Exception e) {

        }
    }

    final protected void saveEntityData() throws SQLException
    {
        program.AppFacade.db.updateRow(tableName,id,readFields());
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


    public abstract DataEntity factoryCreateFromId(int id);
}
