
package Data;

import Facade.ISQLConnectionFactory;

import javax.security.auth.login.FailedLoginException;
import java.util.List;
import java.util.Map;

public class MockDBFactory implements ISQLConnectionFactory
{
    @Override
    public Map<String, Integer> getDatabaseDrivers()
    {
        return Map.of("TEST",1);
    }

    @Override
    public IDataSource SQLFactoryCreate(int type, String server, String database, String user, String password) throws FailedLoginException
    {
        return new MockDataSource();
    }
}
