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

import Projects.Project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Customer extends DataEntity
{
    private String name, shortName;
    private final ArrayList<Employee> employees;
    private final ArrayList<Project> projects;
    private final ArrayList<Attribute> attributeDefinitions;
    private final ArrayList<DocumentTemplate> documentTemplates;
    private final ICustomerDAO customerDao;

    public Customer(ICustomerDAO customerDao, int id, String name, String shortName)
    {
        super(id);
        this.name = name != null ? name : "undefined";
        this.shortName = shortName != null ? shortName : "undefined";
        employees = new ArrayList<>();
        projects = new ArrayList<>();
        attributeDefinitions = new ArrayList<>();
        this.customerDao = customerDao;
        documentTemplates = new ArrayList<>();
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        customerDao.updateCustomer(this);
    }

    public String getShortName()
    {
        return shortName;
    }

    public void setShortName(String shortName)
    {
        this.shortName = shortName;
        customerDao.updateCustomer(this);
    }

    public void addEmployee(Employee employee)
    {
        employees.add(employee);
        customerDao.linkEmployee(this,employee);
    }

    public boolean removeEmployee(Employee employee)
    {
        return customerDao.unlinkEmployee(this,employee) == DaoResult.OP_OK && employees.remove(employee);
    }

    public Collection<Employee> getEmployees(){
        return Collections.unmodifiableCollection(employees);
    }

    public List<Project> getProjects()
    {
        return Collections.unmodifiableList(projects);
    }

    public boolean addProject(Project project)
    {
        return projects.add(project);
    }

    public boolean removeProject(Project project)
    {
        return projects.remove(project);
    }

    public List<Attribute> getAttributeDefinitions()
    {
        return Collections.unmodifiableList(attributeDefinitions);
    }

    public boolean addAttributeDefinition(Attribute attribute)
    {
        return attributeDefinitions.add(attribute);
    }

    public boolean removeAttributeDefinition(Attribute attribute)
    {
        return attributeDefinitions.remove(attribute);
    }

    public boolean addDocumentTemplate(DocumentTemplate documentTemplate)
    {
        return documentTemplates.add(documentTemplate);
    }

    public boolean removeDocumentTemplate(DocumentTemplate documentTemplate)
    {
        return documentTemplates.remove(documentTemplate);
    }

    public List<DocumentTemplate> getDocumentTemplates()
    {
        return Collections.unmodifiableList(documentTemplates);
    }
}
