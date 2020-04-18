package data;

import java.sql.SQLException;

public class CustomerList extends DataEntityList
{

    public CustomerList() throws SQLException
    {
        super(new Customer(-1));

    }




}
