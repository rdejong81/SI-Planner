package data;

import db.QueryResult;
import program.AppFacade;

import java.sql.SQLException;
import java.util.*;

public abstract class DataEntityList
{
    private ArrayList<DataEntity> entities;
    private String pluralEntityName;
    private String singularEntityName;
    private Map<String, Integer> columns;

    DataEntityList(DataEntity entityBase) throws SQLException
    {
        entities = new ArrayList<>();

        singularEntityName = entityBase.getClass().getSimpleName();
        pluralEntityName = entityBase.getTableName(); // database table names are plural.
        columns = entityBase.columnData;

        QueryResult q = AppFacade.db.selectIds(entityBase.getTableName());


        for (HashMap<String, Object> row : q.getRows())
        {
            DataEntity retrievedEntity = entityBase.factoryCreateFromId((Integer)row.get("id"));
            this.addEntity(retrievedEntity);
        }

    }

    final public boolean addEntity(DataEntity entity)
    {
        return entities.add(entity);
    }

    public boolean removeEntity(int id)
    {
        for (DataEntity entity : entities)
        {
            if (entity.getId() == id) {
                try
                {
                    AppFacade.db.deleteRow(entity.getTableName(), id);
                    entities.remove(entity); // if this fails there is corruption in customers array!
                    System.out.printf("Removed %s %d\n",this.getSingularEntityName(),entity.getId());
                } catch(Exception e){
                    System.err.println("Unable to remove entity from database: "+e.getMessage());
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    // used for iterating through lists
    public String getSingularEntityName(){
        return singularEntityName;
    }

    public String getPluralEntityName(){
        return pluralEntityName;
    }

    public Collection<DataEntity> getEntities()
    {
        return Collections.unmodifiableCollection(entities);
    }

    public Map<String,Integer> getColumns()
    {
        return Collections.unmodifiableMap(columns);
    }

}
