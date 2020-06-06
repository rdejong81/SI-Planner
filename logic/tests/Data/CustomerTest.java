package Data;

import Facade.AppFacade;
import Facade.IDataEntityPresenter;
import Projects.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomerTest
{
    private AppFacade appFacade;

    @BeforeAll
    private void init()
    {
        appFacade = new AppFacade(new MockDBFactory());
        appFacade.DoLogin("testlogin","","","","TEST");
    }

    /**
     * Test weird combinations of strings, and test handling of null
     * @param name
     */
    @ParameterizedTest
    @ValueSource(strings = {""," ","test customer","custtest1234"})
    @NullSource
    void getName(String name)
    {

        //init
        Customer customer = appFacade.addCustomer(name,name);

        assertNotNull(customer,"Failed to create customer.");


        //act
        String testName = customer.getName();

        //assert

        assertEquals(name == null ? "undefined" : name,testName);
        assertNotNull(testName,"Failed to filter out null.");

    }

    @ParameterizedTest
    @ValueSource(strings = {"te2341"})
    void setName(String name)
    {
        // init
        Customer customer = appFacade.addCustomer("test"+name,name);
        assertNotNull(customer,"Failed to create customer.");

        // act
        customer.setName(name);

        // assert
        assertEquals(name,customer.getName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"tst1","cu2"})
    @NullSource
    void getShortName(String name)
    {
        //init
        Customer customer = appFacade.addCustomer(name,name);
        assertNotNull(customer,"Failed to create customer.");


        //act
        String testName = customer.getShortName();

        //assert

        assertEquals(name == null ? "undefined" : name,testName);
        assertNotNull(testName,"Failed to filter out null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"te23412"})
    void setShortName(String name)
    {
        // init
        Customer customer = appFacade.addCustomer("test"+name,name);
        assertNotNull(customer,"Failed to create customer.");

        // act
        customer.setShortName(name);

        // assert
        assertEquals(name,customer.getShortName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"te234123","klant c"})
    void addEmployee(String name)
    {
        // init
        Employee employee = appFacade.addEmployee("testaddcu"+name,"testaddcu"+name);
        Customer customer = appFacade.addCustomer("testaddcu"+name,"testaddcu"+name);

        // act
        customer.addEmployee(employee);

        // assert
        assertTrue(employee.getCustomers().contains(customer));
        assertTrue(customer.getEmployees().contains(employee));
    }

    @Test
    void removeEmployee()
    {
        // init
        Employee employee=appFacade.getDataSource().employeeDao().findById(1);
        Customer customer=appFacade.getDataSource().customerDao().findById(1);

        customer.addEmployee(employee);
        assertNotNull(customer);
        assertNotNull(employee);

        //act
        Boolean result = customer.removeEmployee(employee);
        assertTrue(result);

        //assert
        assertFalse(employee.getCustomers().contains(customer));
        assertFalse(customer.getEmployees().contains(employee));
    }

    @ParameterizedTest
    @ValueSource(strings = {"project abc"})
    void addProject(String name)
    {
        // init
        final Project[] project = new Project[1];
        Customer customer = appFacade.getDataSource().customerDao().findById(1);
        appFacade.getLoggedinEmployee().addCustomer(customer); // there must be an available customer

        IDataEntityPresenter dataEntityPresenter = new IDataEntityPresenter()
        {
            @Override
            public void showDataEntity(DataEntity dataEntity)
            {
                if(dataEntity instanceof Project)
                {
                    project[0] = (Project)dataEntity;
                }
            }

            @Override
            public void hideDataEntity(DataEntity dataEntity)
            {

            }
        };
        appFacade.subscribeDataEntityPresenter(dataEntityPresenter);

        // act
        appFacade.addProject(name);

        // assert
        assertTrue(customer.getProjects().contains(project[0]));
        assertEquals(project[0].getCustomer(), customer);
        appFacade.unsubscribeDataEntityPresenter(dataEntityPresenter);
    }

    @ParameterizedTest
    @ValueSource(strings = {"project xyz"})
    void removeProject(String name)
    {
        // init
        final Project[] project = new Project[1];
        Customer customer = appFacade.getDataSource().customerDao().findById(1);
        appFacade.getLoggedinEmployee().addCustomer(customer); // there must be an available customer

        IDataEntityPresenter dataEntityPresenter = new IDataEntityPresenter()
        {
            @Override
            public void showDataEntity(DataEntity dataEntity)
            {
                if(dataEntity instanceof Project)
                {
                    project[0] = (Project)dataEntity;
                }
            }

            @Override
            public void hideDataEntity(DataEntity dataEntity)
            {

            }
        };
        appFacade.subscribeDataEntityPresenter(dataEntityPresenter);

        appFacade.addProject(name);

        // act

        assertTrue(customer.removeProject(project[0]));

        // assert
        assertFalse(customer.getProjects().contains(project[0]));
        assertEquals(project[0].getCustomer(), customer);
        appFacade.unsubscribeDataEntityPresenter(dataEntityPresenter);
    }

    @Test
    void addAttributeDefinition()
    {
    }

    @Test
    void removeAttributeDefinition()
    {
    }


    /**
     * Test if appFacade correctly removes a created customer
     */
    @Test
    void createRemoveCustomer()
    {
        // init

        // act
        Customer customer = appFacade.addCustomer("test123","test123");
        assertNotNull(customer,"Failed to create customer.");
        customer.setId(1234);
        assertNotNull(appFacade.getDataSource().customerDao().findById(1234),
                "Failed to store and retrieve customer");
        assertTrue(appFacade.removeCustomer(customer));

        //assert
        assertNull(appFacade.getDataSource().customerDao().findById(1234));
    }
}