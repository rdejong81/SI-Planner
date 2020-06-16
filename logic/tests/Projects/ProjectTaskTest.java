package Projects;

import Data.DataEntity;
import Data.MockDBFactory;
import Facade.AppFacade;
import Facade.IDataEntityPresenter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectTaskTest
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


    @ParameterizedTest
    @ValueSource(strings = {""," ","test project","proj123"})
    void getName(String name)
    {
        //init
        ProjectTask projectTask = new ProjectTask(appFacade.getDataSource().taskDao(),
                900,name,false,
                appFacade.getDataSource().projectDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        String testName = projectTask.getName();


        //assert
        assertEquals(name,testName);
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","test project","proj123"})
    void setName(String name)
    {
        //init
        ProjectTask projectTask = new ProjectTask(appFacade.getDataSource().taskDao(),
                900,"initialname",false,
                appFacade.getDataSource().projectDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        projectTask.setName(name);


        //assert
        assertEquals(name,projectTask.getName());
    }

    @Test
    void isCompleted()
    {
        //init
        ProjectTask projectTask = new ProjectTask(appFacade.getDataSource().taskDao(),
                900,"initialname",true,
                appFacade.getDataSource().projectDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        boolean completed = projectTask.isCompleted();


        //assert
        assertEquals(true,completed);
    }

    @Test
    void setCompleted()
    {
        //init
        ProjectTask projectTask = new ProjectTask(appFacade.getDataSource().taskDao(),
                900,"initialname",true,
                appFacade.getDataSource().projectDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        projectTask.setCompleted(false);


        //assert
        assertEquals(false,projectTask.isCompleted());
    }

    @Test
    void getProject()
    {
        //init
        ProjectTask projectTask = new ProjectTask(appFacade.getDataSource().taskDao(),
                900,"initialname",true,
                appFacade.getDataSource().projectDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        Project project = projectTask.getProject();


        //assert
        assertEquals(appFacade.getDataSource().projectDao().findById(1),project);
    }

    @Test
    void setProject()
    {
        //init
        ProjectTask projectTask = new ProjectTask(appFacade.getDataSource().taskDao(),
                900,"initialname",true,
                appFacade.getDataSource().projectDao().findById(2));
        assertNotNull(project,"Failed to create project.");

        //act
        projectTask.setProject(appFacade.getDataSource().projectDao().findById(1));


        //assert
        assertEquals(appFacade.getDataSource().projectDao().findById(1),projectTask.getProject());
    }

    @Test
    void addPlanning()
    {

    }

    @Test
    void removePlanning()
    {
    }

    @Test
    void getPlannings()
    {
    }

    @Test
    void addTimeregistration()
    {
    }

    @Test
    void removeTimeregistration()
    {
    }

    @Test
    void getTimeregistrations()
    {
    }
}