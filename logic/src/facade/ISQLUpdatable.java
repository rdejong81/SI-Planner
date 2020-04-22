/*
 *  Used for communication to DataEntities from SQL Select results.
 */

package facade;

public interface ISQLUpdatable
{
    void updateField(String fieldName, Object fieldValue);
    int getId();
    String getTableName();
}
