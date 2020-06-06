
package Data;

import Facade.AppFacade;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.NullString;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class EmployeeTest
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
    @ValueSource(strings = {""," ","test name","te1341"})
    @NullSource
    void getName(String name)
    {

        //init
        Employee employee = appFacade.addEmployee(name,name);

        assertNotNull(employee,"Failed to create employee.");


        //act
        String testName = employee.getName();

        //assert

        assertEquals(name == null ? "undefined" : name,testName);
        assertNotNull(testName,"Failed to filter out null.");

    }

    @ParameterizedTest
    @ValueSource(strings = {"te2341"})
    void setName(String name)
    {
        // init
        Employee employee = appFacade.addEmployee("test"+name,name);
        assertNotNull(employee,"Failed to create employee.");

        // act
        employee.setName(name);

        // assert
        assertEquals(name,employee.getName());
    }

    /**
     * Test weird combinations of strings, and test handling of null
     * @param name
     */
    @ParameterizedTest
    @ValueSource(strings = {"test name2","te13412"})
    @NullSource
    void getSqlLoginName(String name)
    {
        //init
        Employee employee = appFacade.addEmployee(name,name);
        assertNotNull(employee,"Failed to create employee.");

        //act
        String testName = employee.getSqlLoginName();

        //assert

        assertEquals(name == null ? "undefined" : name,testName);
        assertNotNull(testName,"Failed to filter out null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"te23412"})
    void setSqlLoginName(String name)
    {
        // init
        Employee employee = appFacade.addEmployee("test"+name,"test"+name);
        assertNotNull(employee,"Failed to create employee.");

        // act
        employee.setSqlLoginName(name);

        // assert
        assertEquals(name,employee.getSqlLoginName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"te23412","klant B"})
    void addCustomer(String name)
    {
        // init
        Employee employee = appFacade.addEmployee("testaddcu"+name,"testaddcu"+name);
        Customer customer = appFacade.addCustomer("testaddcu"+name,"testaddcu"+name);

        // act
        employee.addCustomer(customer);

        // assert
        assertTrue(employee.getCustomers().contains(customer));
        assertTrue(customer.getEmployees().contains(employee));
    }

    @Test
    void removeCustomer()
    {
        // init
        Employee employee=appFacade.getDataSource().employeeDao().findById(1);
        Customer customer=appFacade.getDataSource().customerDao().findById(1);

        employee.addCustomer(customer);
        assertNotNull(customer);
        assertNotNull(employee);

        //act
        Boolean result = employee.removeCustomer(customer);
        assertTrue(result);

        //assert
        assertFalse(employee.getCustomers().contains(customer));
        assertFalse(customer.getEmployees().contains(employee));
    }

    /**
     * Test if appFacade correctly removes a created employee
     */
    @Test
    void createRemoveEmployee()
    {
        // init

        // act
        Employee employee = appFacade.addEmployee("test123","test123");
        assertNotNull(employee,"Failed to create employee.");
        employee.setId(1234);
        assertNotNull(appFacade.getDataSource().employeeDao().findById(1234),
                "Failed to store and retrieve employee");
        assertTrue(appFacade.removeEmployee(employee) == DaoResult.OP_OK);

        //assert
        assertNull(appFacade.getDataSource().employeeDao().findById(1234));
    }
}