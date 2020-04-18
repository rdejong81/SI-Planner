package data;

import db.QueryResult;

import java.util.*;

abstract public class DataEntity
{
    final private String tableName;
    protected HashMap<String, Object> rowData; // from database
    protected Map<String, Integer> columnData; // from database
    protected int id;

    DataEntity(int id, String tableName)
    {
        this.id = id;
        this.tableName = tableName;
        loadEntityData();

    }

    final protected void loadEntityData(){
        try
        {
            QueryResult results = program.AppFacade.db.selectAllRowsLike(tableName, "id", Integer.toString(id));
            columnData = results.getColumns();
            assert(results.getRows().size() == 1);
            for(HashMap<String,Object> row : results.getRows())
            {
                rowData = row;
            }
        } catch(Exception e) {

        }
    }

    final public String getTableName()
    {
        return tableName;
    }

    final public int getId()
    {
        return id;

    }

    final public Map<String,Integer> getColumns() {
        return Collections.unmodifiableMap(columnData);
    }

    final public Map<String,Object> getValues() {
        return Collections.unmodifiableMap(rowData);
    }

    public abstract DataEntity factoryCreateFromId(int id);
}
