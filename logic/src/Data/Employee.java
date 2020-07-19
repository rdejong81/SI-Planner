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

package Data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Employee extends DataEntity
{
    private String name,sqlLoginName;
    private final ArrayList<Customer> customers;
    private final IEmployeeDAO employeeDao;

    public Employee(IEmployeeDAO employeeDao, int id, String name, String sqlLoginName)
    {
        super(id);
        this.name = name != null ? name : "undefined";
        this.sqlLoginName = sqlLoginName != null ? sqlLoginName : "undefined";
        customers = new ArrayList<>();
        this.employeeDao = employeeDao;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        employeeDao.updateEmployee(this);
    }

    public String getSqlLoginName()
    {
        return sqlLoginName;
    }

    public void setSqlLoginName(String sqlLoginName)
    {
        this.sqlLoginName = sqlLoginName;
        employeeDao.updateEmployee(this);
    }

    public void addCustomer(Customer customer)
    {
        if(customers.contains(customer)) return;

        customers.add(customer);
        employeeDao.linkCustomer(this,customer);
    }

    public boolean removeCustomer(Customer customer)
    {
        if(!customers.contains(customer)) return false;
        return customers.remove(customer) && employeeDao.unlinkCustomer(this,customer) == DaoResult.OP_OK;
    }

    public Collection<Customer> getCustomers()
    {
        return Collections.unmodifiableCollection(customers);
    }
}
