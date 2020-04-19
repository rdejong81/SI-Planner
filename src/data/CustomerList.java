package data;

import db.QueryResult;
import program.AppFacade;

import java.sql.SQLException;
import java.util.Map;

import static data.Employee.*;

public class CustomerList extends DataEntityList
{

    public CustomerList() throws SQLException
    {
        super(new Customer(-1));


    }




}
