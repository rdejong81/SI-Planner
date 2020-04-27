package facade;

import java.util.Map;

public interface ISQLConnection
{

    IQueryResult selectAllRowsIf(String table, String column, Object isValue);
    IQueryResult selectIds(String table);
    void deleteRow(String table, int id);
    IQueryResult insertRow(String table, Map<String,Object> row);
    void createUser(String username, String password);
    boolean canCreateUser();
    void selectEntity(ISQLUpdatable entity);
    void selectEntity(ISQLUpdatable entity, String column);

    IQueryResult updateField(String table,Integer id, String column, Object value);


}
