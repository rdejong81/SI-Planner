
package Facade;

import Data.DaoResult;

public class EDataSourceConnection extends Exception
{
    private final DaoResult reason;
    public EDataSourceConnection(DaoResult reason, String details)
    {
        super(details);
        this.reason = reason;
    }

    public DaoResult getReason()
    {
        return reason;
    }
}
