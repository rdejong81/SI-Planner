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

    @BeforeAll
    private void init()
    {
        appFacade = new AppFacade(new MockDBFactory(),null);
        appFacade.DoLogin("testlogin","","","","TEST");
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
        Project[] project = new Project[1];
        appFacade.getLoggedinEmployee().addCustomer(
                appFacade.getDataSource().customerDao().findById(1));

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
        appFacade.addProject(name,name);
        appFacade.unsubscribeDataEntityPresenter(dataEntityPresenter);

        assertNotNull(project[0],"Failed to create customer.");


        //act
        String testName = project[0].getName();

        //assert

        assertEquals(name == null ? "undefined" : name,testName);
        assertNotNull(testName,"Failed to filter out null.");

    }

    @Test
    void setName()
    {
    }

    @Test
    void getColor()
    {
    }

    @Test
    void setColor()
    {
    }

    @Test
    void isInvoice()
    {
    }

    @Test
    void setInvoice()
    {
    }

    @Test
    void getCustomer()
    {
    }

    @Test
    void setCustomer()
    {
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