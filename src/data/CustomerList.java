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


        // load linked employees
        QueryResult results = AppFacade.db.selectAllRows(TABLE_EMPCUST);

        for(Map<String,Object> row : results.getRows())
        {
            for(DataEntity customer : getEntities())
            {
                if( ((Customer)customer).id == ((Integer)row.get(TABLE_EMPCUST_ROW_CUSTOMERS_ID)).intValue())
                {
                    for(DataEntity employee : AppFacade.employees.getEntities())
                    {
                        if( ((Employee)employee).id == ((Integer)row.get(TABLE_EMPCUST_ROW_EMPLOYEES_ID)).intValue())
                        {
                            // make relationship on both sides.
                            ((Employee) employee).addCustomer((Customer) customer);
                            ((Customer) customer).addEmployee((Employee) employee);
                        }
                    }
                }
            }
        }

    }




}
