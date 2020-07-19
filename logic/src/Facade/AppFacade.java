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

package Facade;

import Data.CustomerList;
import Data.EmployeeList;
import Data.*;
import Planning.Planning;
import Planning.InboundIAO;
import Projects.Project;
import Projects.ProjectTask;
import Timeregistration.*;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;

public class AppFacade
{
    static public AppFacade appFacade; // instantiated in main.
    static public final String APPVERSION = "0.1.51";
    private IDataSource dataSource;
    private final ISQLConnectionFactory sqlConnectionFactory;
    private CustomerList customerList;
    private EmployeeList employeeList;
    private Employee loggedinEmployee, shownCalendarEmployee;
    private final ArrayList<IStatusPresenter> statusPresenters;
    private final ArrayList<IDataEntityPresenter> dataEntityPresenters;
    private final ArrayList<DataEntity> shownDataEntities;
    private final InvoiceConnectionFactory invoiceConnectionFactory;
    private final InboundConnectionFactory inboundConnectionFactory;
    private final ArrayList<ConfigurationSetting> configurationSettings;
    private final HashMap<Integer,InboundIAO> inboundDrivers;

    public Employee getLoggedinEmployee()
    {
        return loggedinEmployee;
    }

    public AppFacade(ISQLConnectionFactory sqlConnectionFactory, InvoiceConnectionFactory invoiceConnectionFactory
    , InboundConnectionFactory inboundConnectionFactory)
    {
        this.sqlConnectionFactory = sqlConnectionFactory;
        this.invoiceConnectionFactory = invoiceConnectionFactory;
        this.inboundConnectionFactory = inboundConnectionFactory;
        dataEntityPresenters = new ArrayList<>();
        shownDataEntities = new ArrayList<>();
        statusPresenters = new ArrayList<>();
        configurationSettings = new ArrayList<>();
        appFacade = this;
        inboundDrivers = new HashMap<>();
        for(Integer inbound : inboundConnectionFactory.getInboundDrivers().values())
        {
            InboundIAO inboundIAO = inboundConnectionFactory.InboundFactoryCreate(inbound);
            inboundDrivers.put(inbound,inboundIAO);
            inboundIAO.initialize();
        }
    }

    public boolean addConfigurationSetting(ConfigurationSetting configurationSetting)
    {
        return configurationSettings.add(configurationSetting);
    }

    public List<ConfigurationSetting> getConfigurationSettings()
    {
        return Collections.unmodifiableList(configurationSettings);
    }

    public Object getConfigurationValue(String name)
    {
        for(ConfigurationSetting configurationSetting : configurationSettings)
        {
            if(configurationSetting.getName().equals(name))
                return configurationSetting.getValue();
        }

        return null;
    }

    public Map<Integer,InboundIAO> getInboundDrivers()
    {
        return Collections.unmodifiableMap(inboundDrivers);
    }

    public boolean subscribeStatusPresenter(IStatusPresenter statusPresenter)
    {
        statusPresenter.statusTick("",0,0);
        return statusPresenters.add(statusPresenter);
    }

    public boolean unSubscribeStatusPresenter(IStatusPresenter statusPresenter)
    {
        return statusPresenters.remove(statusPresenter);
    }

    public void broadcastStatusUpdate(String message,int progress, int total)
    {
        for(IStatusPresenter statusPresenter : statusPresenters)
            statusPresenter.statusTick(message,progress,total);
    }

    public boolean subscribeDataEntityPresenter(IDataEntityPresenter dataEntityPresenter)
    {
        // show all existing items to new presenter
        for(DataEntity dataEntity : shownDataEntities)
        {
            dataEntityPresenter.showDataEntity(dataEntity);
        }
        if(dataEntityPresenters.add(dataEntityPresenter))
        {
            refreshData();
            return true;
        } else return false;
    }

    public boolean unsubscribeDataEntityPresenter(IDataEntityPresenter dataEntityPresenter)
    {
        return dataEntityPresenters.remove(dataEntityPresenter);
    }

    public void broadcastShowDataEntity(DataEntity dataEntity)
    {
        if(!shownDataEntities.contains(dataEntity))
            shownDataEntities.add(dataEntity);
        for (IDataEntityPresenter dataEntityPresenter : dataEntityPresenters)
        {
            dataEntityPresenter.showDataEntity(dataEntity);
        }
    }

    public void broadcastHideDataEntity(DataEntity dataEntity)
    {
        if(shownDataEntities.contains(dataEntity))
            shownDataEntities.remove(dataEntity);
        for (IDataEntityPresenter dataEntityPresenter : dataEntityPresenters)
        {
            dataEntityPresenter.hideDataEntity(dataEntity);
        }
    }

    public Set<String> getDatabaseTypes()
    {
        return Collections.unmodifiableSet(sqlConnectionFactory.getDatabaseDrivers().keySet());
    }

    public InvoiceIAO getInvoiceType(int type)
    {
        return invoiceConnectionFactory.InvoiceFactoryCreate(type);
    }

    public Map<String,Integer> getInvoiceTypes()
    {
        return Collections.unmodifiableMap(invoiceConnectionFactory.getInvoiceDrivers());
    }

    public DaoResult DoLogin(String userName, String password, String dbName, String location, String dbType)
    {
        try
        {
            dataSource = sqlConnectionFactory.SQLFactoryCreate(sqlConnectionFactory.getDatabaseDrivers().get(dbType),
                    location, dbName, userName, password);
            dataSource.openConnection();
        } catch (EDataSourceConnection e)
        {
            return e.getReason();
        }

        employeeList = new EmployeeList(dataSource.employeeDao());
        customerList = new CustomerList(dataSource.customerDao());

        // first user of the database automatically gets to be the first employee
        if (employeeList.getEmployees().isEmpty())
        {

            // if datasource supports login, create login user as first employee
            if(dataSource.getCapabilities().contains(DSCapability.LOGIN))
            {
                loggedinEmployee = new Employee(dataSource.employeeDao(), 0, userName, userName);
                dataSource.employeeDao().insertEmployee(loggedinEmployee);
                shownCalendarEmployee = loggedinEmployee;

                // todo: remove console output from logic layer
                System.out.printf("First application user %s created as employee.\n", userName);
            }

            // create initial demo employees, customer and projects for calendar.

            Employee demoEmployee = addEmployee("Demo Employee","demouser");
            Employee demoEmployeeB = addEmployee("Demo Employee B","demouserB");

            if(!dataSource.getCapabilities().contains(DSCapability.LOGIN))
                loggedinEmployee = demoEmployee;

            shownCalendarEmployee = demoEmployee;

            Customer customer = addCustomer("Example customer","EXC");
            customer.addEmployee(demoEmployeeB);
            customer.addEmployee(demoEmployee);

            Project projectA = addProject("Example project A","PJA");
            Project projectB = addProject("Example project B","PJB");
            projectA.setColor(1);
            projectB.setColor(2);
            projectA.setInvoice(true);
            projectB.setInvoice(true);
            appFacade.createAttributeDefinition(customer,"Timesheet tel code",AttributeType.STRING,EntityType.EMPLOYEE);
            appFacade.createAttributeDefinition(customer,"Purchase Order",AttributeType.STRING,EntityType.PROJECT);
            appFacade.createAttributeDefinition(customer,"Project department",AttributeType.STRING,EntityType.PROJECT);
            appFacade.createAttributeDefinition(customer,"Timesheet project signer",AttributeType.STRING,EntityType.PROJECT);
            appFacade.createAttributeDefinition(customer,"Ticket",AttributeType.STRING,EntityType.TIMEREGISTRATION);

            ProjectTask projectTaskA = new ProjectTask(dataSource.taskDao(),0,"Prepare A",false,projectA);
            ProjectTask projectTaskB = new ProjectTask(dataSource.taskDao(),0,"Prepare B",false,projectB);
            ProjectTask projectTaskC = new ProjectTask(dataSource.taskDao(),0,"Demo Employee task",false,projectB);
            dataSource.taskDao().insertTask(projectTaskA);
            dataSource.taskDao().insertTask(projectTaskB);
            dataSource.taskDao().insertTask(projectTaskC);

            createPlanning(ZonedDateTime.now(),ZonedDateTime.now().plusHours(1),projectTaskA,demoEmployeeB);
            createPlanning(ZonedDateTime.now().minusDays(1),ZonedDateTime.now().minusDays(1).plusHours(1), projectTaskB,demoEmployeeB);
            createPlanning(ZonedDateTime.now().minusDays(1),ZonedDateTime.now().minusDays(1).plusHours(1), projectTaskC,demoEmployee);

            return DaoResult.OP_OK;
        }

        if(!dataSource.getCapabilities().contains(DSCapability.LOGIN))
        {
            loggedinEmployee=dataSource.employeeDao().findAll().iterator().next();
            return DaoResult.OP_OK;
        }


        for (Employee employee : employeeList.getEmployees())
        {
            if (employee.getSqlLoginName().equals(userName))
            {
                loggedinEmployee = employee;
                return DaoResult.OP_OK;
            }
        }

        dataSource = null;
        loggedinEmployee = null;
        return DaoResult.DS_DISCONNECTED;
    }

    public Customer addCustomer(String name, String shortName)
    {
        for (Customer customer : customerList.getCustomers())
        {
            if (customer.getName().equals(name) || customer.getShortName().equals(shortName)) return null;
        }
        Customer newCustomer = new Customer(dataSource.customerDao(), 0, name, shortName);
        if (customerList.addCustomer(newCustomer))
        {
            refreshData();
            return newCustomer;
        }

        return null;
    }

    public boolean removeCustomer(Customer customer)
    {
        if (customerList.removeCustomer(customer))
        {
            refreshData();
            return true;
        }
        return false;
    }

    public Employee addEmployee(String name, String loginName)
    {
        for (Employee employee : employeeList.getEmployees())
        {
            if (employee.getName().equals(name) || employee.getSqlLoginName().equals(loginName)) return null;
        }
        Employee newEmployee = new Employee(dataSource.employeeDao(), 0, name, loginName);
        DaoResult result = employeeList.addEmployee(newEmployee);
        if (result == DaoResult.OP_OK)
        {
            refreshData();
            return newEmployee;
        }

        return null;
    }

    public DaoResult removeEmployee(Employee employee)
    {
        DaoResult result = employeeList.removeEmployee(employee);
        if (result == DaoResult.OP_OK)
        {
            refreshData();
            return result;
        }
        return result;
    }

    public DaoResult addDocumentTemplate(File file,Customer customer)
    {
        FileInputStream fileInputStream;
        byte[] bytes;
        try
        {
            fileInputStream = new FileInputStream(file);
            bytes = fileInputStream.readAllBytes();
        } catch (Exception e)
        {
            e.printStackTrace();
            return DaoResult.DAO_MISSING;
        }

        DocumentTemplate documentTemplate = new DocumentTemplate(dataSource.documentTemplateDao(),-1,file.getName(),bytes,
                customer, 1);
        DaoResult result = dataSource.documentTemplateDao().insertDocumentTemplate(documentTemplate);
        if(result == DaoResult.OP_OK)
        {
            customer.addDocumentTemplate(documentTemplate);
            resetPresentation();
        }
        return result;
    }

    public DaoResult removeDocumentTemplate(DocumentTemplate documentTemplate)
    {
        DaoResult result = dataSource.documentTemplateDao().deleteDocumentTemplate(documentTemplate);
        if(result == DaoResult.OP_OK)
        {
            resetPresentation();
            return result;
        }
        return result;
    }


    public IDataSource getDataSource()
    {
        return dataSource;
    }

    public void showCalendar(Employee employee)
    {
        shownCalendarEmployee = employee;
        refreshData();
    }

    public Project addProject(String name, String shortCode)
    {
        if(shownCalendarEmployee.getCustomers().isEmpty()) return null;
        Customer customer = shownCalendarEmployee.getCustomers().iterator().next();

        Project project = new Project(dataSource.projectDao(),0,name,0,false,shortCode,customer);
        dataSource.projectDao().insertProject(project);
        refreshData();
        return project;
    }

    public DaoResult removeProject(Project project)
    {
        DaoResult result = dataSource.projectDao().deleteProject(project);
        if(result == DaoResult.OP_OK)
            refreshData();
        return result;
    }

    public DaoResult createAttributeDefinition(Customer customer, String name, AttributeType attributeType, EntityType entityType)
    {
        Object value = switch(attributeType)
                {
                    case STRING -> "";
                    case INTEGER -> 1;
                    case BOOLEAN -> true;
                    case DATE -> LocalDateTime.now();
                    case DOUBLE -> 1.0;
                };
        Attribute attribute = new Attribute(dataSource.attributeDefinitionDao(),-1,name,value,customer,null,entityType);
        DaoResult daoResult = dataSource.attributeDefinitionDao().insertAttribute(attribute,attribute);
        if(daoResult == DaoResult.OP_OK)
        {
            // workaround for refresh.
            // todo: create dedicated refresh object mechanism.
            resetPresentation();
            return daoResult;
        }
        return daoResult;
    }

    public DaoResult deleteAttributeDefinition(Attribute attribute)
    {
        // delete all associated attributes first.
        for(Attribute attributeChild : new ArrayList<>(dataSource.attributeDao().findAll(null)))
        {
            if(attributeChild.getParentDefinition() == attribute)
            {
                dataSource.attributeDao().deleteAttribute(attributeChild);
                attributeChild.getParent().removeAttribute(attributeChild);
            }

        }
        DaoResult result = dataSource.attributeDefinitionDao().deleteAttribute(attribute);
        if(result == DaoResult.OP_OK)
            resetPresentation();
        return result;
    }

    public DaoResult createPlanning(ZonedDateTime start, ZonedDateTime end, ProjectTask projectTask, Employee employee)
    {
        Planning planning = new Planning(dataSource.planningDao(),0,false,start,end
                ,projectTask,employee);

        DaoResult result = dataSource.planningDao().insertPlanning(planning);
        if(result == DaoResult.OP_OK)
        {
            broadcastShowDataEntity(planning);
            return DaoResult.OP_OK;
        } else {
            return result;
        }
    }

    public void reassignAttributeDefinition(Attribute attributeDefinition, EntityType entityType)
    {
        // delete all associated attributes.
        for(Attribute attributeChild : new ArrayList<>(dataSource.attributeDao().findAll(null)))
        {
            if(attributeChild.getParentDefinition() == attributeDefinition)
            {
                dataSource.attributeDao().deleteAttribute(attributeChild);
                attributeChild.getParent().removeAttribute(attributeChild);
            }

        }

        attributeDefinition.setEntityType(entityType);

        for(DataEntity dataEntity : shownDataEntities)
        {
            // refresh all data entities of new type.
            if(EntityType.fromEntity(dataEntity) == entityType)
            {
                dataEntity.getAttributes();

            }
        }
        resetPresentation();



    }

    /**
     * Returns files of generated invoices based on selected/active employee in selected month.
     * List can be empty when no project has invoice able timeregistrations
     * @return
     */
    public List<Invoice> generateInvoices(LocalDate start,LocalDate end)
    {
        ArrayList<Invoice> invoices = new ArrayList<>();

        for(Customer customer : shownCalendarEmployee.getCustomers())
        {
            for(DocumentTemplate documentTemplate : customer.getDocumentTemplates())
            {
                InvoiceIAO invoiceIAO = invoiceConnectionFactory.InvoiceFactoryCreate(documentTemplate.getType());
                Invoice invoice = new Invoice(documentTemplate, shownCalendarEmployee,customer,invoiceIAO);
                invoice.generate(start,end);
                if(invoice.getDocument() != null)
                    invoices.add(invoice);
            }
        }





        return Collections.unmodifiableList(invoices);
    }

    public void resetPresentation()
    {
        for(DataEntity dataEntity : new ArrayList<>(shownDataEntities))
            broadcastHideDataEntity(dataEntity);
        shownDataEntities.clear();
        refreshData();
    }

    public void refreshInbound(int type)
    {
        InboundIAO inboundIAO = inboundDrivers.get(type);
        inboundIAO.parse();
    }

    // refresh all data entity presenters based on existing being shown
    public void refreshData()
    {
        if(employeeList == null) return;
        if(shownCalendarEmployee == null) shownCalendarEmployee = loggedinEmployee;  // default calendar to logged in user.

        // to detect deleted database objects
        ArrayList<DataEntity> handledObjects = new ArrayList<>(shownDataEntities);

        for (Employee employee : employeeList.getEmployees())
        {
            handledObjects.remove(employee);

            if (!shownDataEntities.contains(employee))
            {
                shownDataEntities.add(employee);
                broadcastShowDataEntity(employee);
            }
        }

        for (Customer customer : customerList.getCustomers())
        {
            if(!customer.getEmployees().contains(shownCalendarEmployee) &&
                    !customer.getEmployees().isEmpty()) continue; // do not show if employee is unlinked to customer.
                                                                    // except when customer not linked to any employee.

            handledObjects.remove(customer);
            if (!shownDataEntities.contains(customer))
            {
                shownDataEntities.add(customer);
                broadcastShowDataEntity(customer);
            }

            for (Project project : customer.getProjects())
            {
                handledObjects.remove(project);
                if (!shownDataEntities.contains(project))
                {
                    shownDataEntities.add(project);
                    broadcastShowDataEntity(project);
                }

                for (ProjectTask projectTask : project.getProjectTasks())
                {
                    handledObjects.remove(projectTask);
                    if (!shownDataEntities.contains(projectTask))
                    {
                        shownDataEntities.add(projectTask);
                        broadcastShowDataEntity(projectTask);
                    }



                    for (Planning planning : projectTask.getPlannings())
                    {
                        if(shownCalendarEmployee != planning.getEmployee()) continue; // only show time for active employee calendar.
                        handledObjects.remove(planning);
                        if (!shownDataEntities.contains(planning))
                        {
                            shownDataEntities.add(planning);
                            broadcastShowDataEntity(planning);
                        }
                    }

                    for (Timeregistration timeregistration : projectTask.getTimeregistrations())
                    {
                        if(shownCalendarEmployee != timeregistration.getEmployee()) continue; // only show time for active employee calendar.
                        handledObjects.remove(timeregistration);
                        if (!shownDataEntities.contains(timeregistration))
                        {
                            shownDataEntities.add(timeregistration);
                            broadcastShowDataEntity(timeregistration);
                        }

                    }


                }
            }
        }

        broadcastStatusUpdate("Done loading data.",0,0);
        // broadcast removed database items
        for(DataEntity dataEntity : handledObjects)
        {
            broadcastHideDataEntity(dataEntity);
            shownDataEntities.remove(dataEntity);
        }

    }


}
