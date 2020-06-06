package Data;

public enum DaoResult
{
    OP_OK,
    DAO_DUPLICATE,
    DAO_MISSING,
    /**
     * DB Constraint error
     */
    DAO_INUSE,
    DS_DISCONNECTED;
}
