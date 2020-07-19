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
        appFacade = new AppFacade(new MockDBFactory(),null,null);
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

    @ParameterizedTest
    @ValueSource(strings = {""," ","test project","proj123"})
    void getShortName(String name)
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,name,0,false,name,
                appFacade.getDataSource().customerDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        String testName = project.getShortName();


        //assert
        assertEquals(testName,name);
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","test project","proj123"})
    void setShortName(String name)
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                appFacade.getDataSource().customerDao().findById(1));
        assertNotNull(project,"Failed to create project.");

        //act
        project.setShortName(name);


        //assert
        assertEquals(project.getShortName(),name);
    }

    @Test
    void addTask()
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                appFacade.getDataSource().customerDao().findById(1));
        ProjectTask projectTask = appFacade.getDataSource().taskDao().findById(1);

        //act
        project.addTask(projectTask);


        //assert
        assertEquals(true,project.getProjectTasks().contains(projectTask));

    }

    @Test
    void removeTask()
    {
        //init
        Project project = new Project(appFacade.getDataSource().projectDao(),
                900,"test",0,false,"TEST",
                appFacade.getDataSource().customerDao().findById(1));
        ProjectTask projectTask = appFacade.getDataSource().taskDao().findById(1);
        project.addTask(projectTask);

        //act
        project.removeTask(projectTask);

        //assert
        assertEquals(false,project.getProjectTasks().contains(projectTask));
    }
}