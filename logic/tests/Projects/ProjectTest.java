package Projects;

import Data.Customer;
import Data.DataEntity;
import Data.MockDBFactory;
import Facade.AppFacade;
import Facade.IDataEntityPresenter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectTest
{
    private AppFacade appFacade;
    private Project[] project = new Project[1];
    IDataEntityPresenter dataEntityPresenter;

    @BeforeAll
    private void init()
    {
        appFacade = new AppFacade(new MockDBFactory(),null);
        appFacade.DoLogin("testlogin","","","","TEST");

        appFacade.getLoggedinEmployee().addCustomer(
                appFacade.getDataSource().customerDao().findById(1));

        dataEntityPresenter = new IDataEntityPresenter()
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
    }


    /**
     * Test weird combinations of strings, and test handling of null
     * @param name
     */
    @ParameterizedTest
    @ValueSource(strings = {""," ","test project","proj123"})
    @NullSource
    void getName(String name)
    {

        //init
        appFacade.addProject(name,name);

        assertNotNull(project[0],"Failed to create customer.");

        //act
        String testName = project[0].getName();

        //assert

        assertEquals(name == null ? "undefined" : name,testName);
        assertNotNull(testName,"Failed to filter out null.");

    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","test project","proj123"})
    @NullSource
    void setName(String name)
    {

        //init
        appFacade.addProject("settest"+name,"settest"+name);

        assertNotNull(project[0],"Failed to create customer.");

        //act
        project[0].setName(name);
        String testName = project[0].getName();

        //assert

        assertEquals(name == null ? "undefined" : name,testName);
        assertNotNull(testName,"Failed to filter out null.");

    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,5,10000,-1})
    void getColor(int color)
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",color,false,"TEST",
                appFacade.getDataSource().customerDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        int testColor = project.getColor();

        //assert
        assertEquals(testColor,color);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,5,10000,-1})
    void setColor(int color)
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                appFacade.getDataSource().customerDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        project.setColor(color);

        //assert
        assertEquals(project.getColor(),color);
    }

    @Test
    void isInvoice()
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                appFacade.getDataSource().customerDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act


        //assert
        assertEquals(project.isInvoice(),false);
    }

    @Test
    void setInvoice()
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                appFacade.getDataSource().customerDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        project.setInvoice(true);


        //assert
        assertEquals(project.isInvoice(),true);
    }

    @Test
    void getCustomer()
    {
        //init
        Customer customer = appFacade.getDataSource().customerDao().findById(1);
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                customer);


        assertNotNull(project,"Failed to create project.");

        //act
        project.setInvoice(true);


        //assert
        assertEquals(project.getCustomer(),customer);
    }

    @Test
    void setCustomer()
    {
        //init
        Customer customer = appFacade.getDataSource().customerDao().findById(1);
        Customer customerB = appFacade.getDataSource().customerDao().findById(2);
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                customer);

        //act
        project.setCustomer(customerB);


        //assert
        assertEquals(project.getCustomer(),customerB);
    }

    @Test
    void getShortName()
    {

    }

    @Test
    void setShortName()
    {
    }

    @Test
    void addTask()
    {
    }

    @Test
    void removeTask()
    {
    }
}