/*
 *    Copyright © 2020 Richard de Jong
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package Sqlite;

import Data.*;
import Projects.Project;
import Sql.QueryResult;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Customer Data Acccess Object
 * @author Richard de Jong
 * @see ICustomerDAO
 */
public class CustomerSqliteDAO implements ICustomerDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<Customer> customerInstances;  // list of instances of customer to prevent duplicate objects of the same database id.
    private final ArrayList<Customer> customersUpdating;  // list of Customer instances that are being updated.

    public CustomerSqliteDAO(SqliteConnection sqliteConnection)
    {
        this.sqliteConnection = sqliteConnection;
        customerInstances = new ArrayList<>();
        customersUpdating = new ArrayList<>();
    }

    /**
     * Convert table row data to Customer object.
     * Each table id is ensured to have 1 instance the existing instance is updated instead.
     *
     * @param row table data
     * @return    the customer object created or updated.
     * @see Customer
     */
    private Customer processRow(HashMap<String,Object> row)
    {
        Customer customer=null;
        for(Customer currentCustomer : customerInstances)
        {
            if(currentCustomer.getId() == (Integer)row.get("id"))
            {
                customersUpdating.add(currentCustomer);
                currentCustomer.setName((String)row.get("name"));
                currentCustomer.setShortName((String)row.get("shortName"));
                customer=currentCustomer;
            }
        }
        if(customer == null)
        {
            customer = new Customer(this,(Integer) row.get("id"), (String) row.get("name"), (String) row.get("shortName"));
            customerInstances.add(customer);
            customersUpdating.add(customer);
        }

        // find employees linked to customer
        QueryResult childResults = new QueryResult(sqliteConnection, String.format("select employees_id from employees_customers where customers_id=%d",
                customer.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            Employee employee = sqliteConnection.employeeDao().findById((Integer)childRow.get("employees_id"));
            if(employee != null && !customer.getEmployees().contains(employee))
                customer.addEmployee(employee);
        }

        // find projects linked to customer
        childResults = new QueryResult(sqliteConnection, String.format("select id from projects where customer_id=%d",
                customer.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            Project project = sqliteConnection.projectDao().findById((Integer)childRow.get("id"));
            if(project != null && !customer.getProjects().contains(project))
                customer.addProject(project);
        }

        // find document templates linked to customer
        childResults = new QueryResult(sqliteConnection, String.format("select id from templates where customers_id=%d",
                customer.getId()));

        for(HashMap<String,Object> childRow : childResults.getRows())
        {
            DocumentTemplate documentTemplate = sqliteConnection.documentTemplateDao().findById((Integer)childRow.get("id"));
            if(documentTemplate != null && !customer.getDocumentTemplates().contains(documentTemplate))
                customer.addDocumentTemplate(documentTemplate);
        }

        // find attribute definitions linked to customer
        for(Attribute attribute : sqliteConnection.attributeDefinitionDao().findAll(customer))
        {
            if(!customer.getAttributeDefinitions().contains(attribute))
                customer.addAttributeDefinition(attribute);
        }

        // find attributes linked to customer.
        for(Attribute attribute : sqliteConnection.attributeDao().findAll(customer))
        {
            customer.addAttribute(attribute);
        }

        customersUpdating.remove(customer);
        return customer;
    }

    /**
     * Returns all Customer objects in datasource.
     * Existing instances of Customer are retained and updated.
     *
     * @return List of all customer objects.
     */
    @Override
    public List<Customer> findAll()
    {
        QueryResult result = new QueryResult(sqliteConnection, "select * from customers");

        for(HashMap<String,Object> row : result.getRows())
        {
            processRow(row);
        }

        return Collections.unmodifiableList(customerInstances);
    }

    /**
     * Return Customer object in datasource by table row id number.
     * Any existing instance is retained and updated.
     *
     * @param id
     * @return
     */
    @Override
    public Customer findById(int id)
    {
        for(Customer customer : customerInstances)
        {
            if(customer.getId() == id) return customer;
        }

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from customers where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public DaoResult insertCustomer(Customer customer)
    {
        QueryResult result = new QueryResult(sqliteConnection,String.format("insert into customers (name,shortName) values ('%s','%s')",
                customer.getName(),
                customer.getShortName()
        ));
        customer.setId((int)result.getCreatedKey());
        customerInstances.add(customer);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateCustomer(Customer customer)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format("update customers set name='%s',shortName='%s' where id=%d",
                customer.getName().replace("'","%%%"),
                customer.getShortName().replace("'","%%%"),
                customer.getId()
        ));
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult deleteCustomer(Customer customer)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating

        // remove associated employees - make new list while changing original
        for(Employee employee : new ArrayList<>(customer.getEmployees()))
        {
            customer.removeEmployee(employee);
        }

        // remove associated projects - make new list while changing original
        for(Project project : new ArrayList<>(customer.getProjects()))
        {
            sqliteConnection.projectDao().deleteProject(project);
        }

        // remove associated templates - make new list while changing original
        for(DocumentTemplate documentTemplate : new ArrayList<>(customer.getDocumentTemplates()))
        {
            sqliteConnection.documentTemplateDao().deleteDocumentTemplate(documentTemplate);
        }

        // remove associated attribute definitions
        for(Attribute attribute : new ArrayList<>(customer.getAttributeDefinitions()))
        {
            sqliteConnection.attributeDefinitionDao().deleteAttribute(attribute);
        }

        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from customers where id=%d",customer.getId()));
        if(result.getLastError() == null)
        {
            customerInstances.remove(customer);
            return DaoResult.OP_OK;
        }

        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }

    @Override
    public DaoResult linkEmployee(Customer customer, Employee employee)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        new QueryResult(sqliteConnection,String.format("insert into employees_customers (employees_id,customers_id) values (%d,%d)",
                employee.getId(),
                customer.getId()
        ));
        customersUpdating.add(customer);
        employee.addCustomer(customer);
        customersUpdating.remove(customer);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult unlinkEmployee(Customer customer, Employee employee)
    {
        if(customersUpdating.contains(customer)) return DaoResult.OP_OK; //already updating
        new QueryResult(sqliteConnection,String.format("delete from employees_customers where employees_id=%d and customers_id=%d",
                employee.getId(),
                customer.getId()
        ));
        customersUpdating.add(customer);
        employee.removeCustomer(customer);
        customersUpdating.remove(customer);
        return DaoResult.OP_OK;
    }
}
