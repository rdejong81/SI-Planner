package data;

import java.sql.SQLException;

public class EmployeeList extends DataEntityList
{
    public EmployeeList() throws SQLException
    {
        super(new Employee(-1));
    }

}
