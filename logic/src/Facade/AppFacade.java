package Facade;

import Data.CustomerList;
import Data.EmployeeList;
import Data.*;
import Planning.Planning;
import Projects.Project;
import Projects.ProjectTask;
import Timeregistration.Timeregistration;

import javax.security.auth.login.FailedLoginException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class AppFacade
{
    static public AppFacade appFacade; // instantiated in main.
    private IDataSource dataSource;
    private final ISQLConnectionFactory sqlConnectionFactory;
    private CustomerList customerList;
    private EmployeeList employeeList;
    private Employee loggedinEmployee,showCalendarEmployee;
    private final ArrayList<IDataEntityPresenter> dataEntityPresenters;
    private final ArrayList<DataEntity> shownDataEntities;

    public Employee getLoggedinEmployee()
    {
        return loggedinEmployee;
    }

    public AppFacade(ISQLConnectionFactory sqlConnectionFactory)
    {
        this.sqlConnectionFactory = sqlConnectionFactory;
        dataEntityPresenters = new ArrayList<>();
        shownDataEntities = new ArrayList<>();
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

    private void broadcastShowDataEntity(DataEntity dataEntity)
    {
        for (IDataEntityPresenter dataEntityPresenter : dataEntityPresenters)
        {
            dataEntityPresenter.showDataEntity(dataEntity);
        }
    }

    private void broadcastHideDataEntity(DataEntity dataEntity)
    {
        for (IDataEntityPresenter dataEntityPresenter : dataEntityPresenters)
        {
            dataEntityPresenter.hideDataEntity(dataEntity);
        }
    }

    public Set<String> getDatabaseTypes()
    {
        return Collections.unmodifiableSet(sqlConnectionFactory.getDatabaseDrivers().keySet());
    }

    public DaoResult DoLogin(String userName, String password, String dbName, String location, String dbType)
    {
        try
        {
            dataSource = sqlConnectionFactory.SQLFactoryCreate(sqlConnectionFactory.getDatabaseDrivers().get(dbType),
                    location, dbName, userName, password);
        } catch (FailedLoginException e)
        {
            return DaoResult.DS_DISCONNECTED;
        }

        employeeList = new EmployeeList(dataSource.employeeDao());
        customerList = new CustomerList(dataSource.customerDao());

        // first user of the database automatically gets to be the first employee
        if (employeeList.getEmployees().isEmpty())
        {
            loggedinEmployee = new Employee(dataSource.employeeDao(), 0, userName, userName);

            dataSource.employeeDao().insertEmployee(loggedinEmployee);
            // todo: remove console output from logic layer
            System.out.printf("First application user %s created as employee.\n", userName);
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


    public IDataSource getDataSource()
    {
        return dataSource;
    }

    public void showCalendar(Employee employee)
    {
        showCalendarEmployee = employee;
        refreshData();
    }

    public void addProject(String name)
    {
        Customer customer = showCalendarEmployee.getCustomers().iterator().next();
        if(customer == null) return;
        Project project = new Project(dataSource.projectDao(),0,name,0,false,name,customer);
        dataSource.projectDao().insertProject(project);
        refreshData();
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

    public void resetPresentation()
    {
        for(DataEntity dataEntity : shownDataEntities)
            broadcastHideDataEntity(dataEntity);
        shownDataEntities.clear();
        refreshData();
    }

    // refresh all data entity presenters based on existing being shown
    public void refreshData()
    {
        if(showCalendarEmployee == null) showCalendarEmployee = loggedinEmployee;  // default calendar to logged in user.

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
            if(!customer.getEmployees().contains(showCalendarEmployee) &&
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
                        if(showCalendarEmployee != planning.getEmployee()) continue; // only show time for active employee calendar.
                        handledObjects.remove(planning);
                        if (!shownDataEntities.contains(planning))
                        {
                            shownDataEntities.add(planning);
                            broadcastShowDataEntity(planning);
                        }
                    }

                    for (Timeregistration timeregistration : projectTask.getTimeregistrations())
                    {
                        if(showCalendarEmployee != timeregistration.getEmployee()) continue; // only show time for active employee calendar.
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

        // broadcast removed database items
        for(DataEntity dataEntity : handledObjects)
        {
            broadcastHideDataEntity(dataEntity);
            shownDataEntities.remove(dataEntity);
        }

    }


}
