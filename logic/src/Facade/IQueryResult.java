package Facade;

import java.util.Collection;
import java.util.HashMap;

public interface IQueryResult
{
    Collection<HashMap<String, Object>> getRows();
    Exception getLastError();
    long getCreatedKey();

}
