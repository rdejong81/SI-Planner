package data;

import db.QueryResult;
import program.AppFacade;

import java.sql.SQLException;
import java.util.Map;

import static data.Employee.TABLE_EMPCUST;

public class EmployeeList extends DataEntityList
{
    public EmployeeList() throws SQLException
    {
        super(new Employee(-1));
    }

}
