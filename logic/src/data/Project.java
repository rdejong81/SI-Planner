package data;

import facade.ISQLConnection;

import java.util.Map;

public class Project extends DataEntity
{
    private String name;

    final public static String TABLE_PROJECTS="customers";
    final public static String TABLE_PROJECTS_ROW_NAME="name";
    final public static String TABLE_PROJECTS_ROW_CUSTOMER="customer_id";

    // Create based on existing db record.
    protected Project(ISQLConnection sqlConnection, int id)
    {
        super(sqlConnection,id, TABLE_PROJECTS);
    }


    @Override
    public Map<String, Object> readFields()
    {
        return Map.of(
                TABLE_PROJECTS_ROW_NAME,name
        );
    }

    @Override
    public void updateField(String fieldName, Object fieldValue)
    {
        switch (fieldName){
            case TABLE_PROJECTS_ROW_NAME: name = (String)fieldValue; break;
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        sqlConnection.updateField(getTableName(),id,TABLE_PROJECTS_ROW_NAME,name);
        this.name = name;
    }

}
