package data;

import facade.IQueryResult;
import facade.ISQLConnection;

import java.io.InvalidClassException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Consumer;

public class DataEntityList<T extends DataEntity> implements Iterable<T>
{
    static public DataEntityList<Customer> allCustomers; // shared in the module, all loaded customers.
    static public DataEntityList<Employee> allEmployees; // shared in the module all loaded employees

    private ArrayList<T> entities;
    private String pluralEntityName;
    private String singularEntityName;
    private ISQLConnection sqlConnection;
    private String tableName;
    private Class<T> classType;

    public DataEntityList(ISQLConnection sqlConnection,Class<T> classType)
    {
        entities = new ArrayList<>();
        this.sqlConnection = sqlConnection;

        // to get the table name we need an instance of classType
        try
        {
            T instance = classType.getDeclaredConstructor(ISQLConnection.class,int.class).newInstance(sqlConnection,-1);
            tableName = instance.getTableName();
        } catch(Exception e) {
            e.printStackTrace();
        }

        IQueryResult results = sqlConnection.selectIds(tableName);

        for (HashMap<String, Object> row : results.getRows())
        {
            try
            {
                // constructor with sqlconnection and integer parameter (database table id) must exist. else runtime error.
                this.addEntity(classType.getDeclaredConstructor(ISQLConnection.class, int.class).newInstance(sqlConnection,row.get("id")));
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }

    final public boolean addEntity(T entity)
    {
        if(entities.isEmpty())
        {
            singularEntityName = entity.getClass().getSimpleName();
            pluralEntityName = singularEntityName + "s"; // simple solution for now.
        }

        return entities.add(entity);
    }

    public boolean removeEntity(int id)
    {
        for (T entity : entities)
        {
            if (entity.getId() == id) {
                try
                {
                    sqlConnection.deleteRow(tableName, id);
                    entities.remove(entity); // if this fails there is corruption in customers array!

                    //todo: replace console access with exceptions or use return status true.
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

    public Collection<T> getEntities()
    {
        return Collections.unmodifiableCollection(entities);
    }

    @Override
    public Iterator<T> iterator()
    {
        return entities.iterator();
    }

    @Override
    public void forEach(Consumer action)
    {
        entities.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator()
    {
       return entities.spliterator();
    }

    public boolean isEmpty(){
        return entities.isEmpty();
    }

    public int size(){
        return entities.size();
    }
}
