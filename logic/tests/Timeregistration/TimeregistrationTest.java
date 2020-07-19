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

package Timeregistration;

import Data.DataEntity;
import Data.Employee;
import Data.MockDBFactory;
import Facade.AppFacade;
import Facade.IDataEntityPresenter;
import Projects.Project;
import Projects.ProjectTask;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TimeregistrationTest
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


    @Test
    void isSynced()
    {
        //init
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, ZonedDateTime.now(),
                ZonedDateTime.now(),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        boolean isSynced = timeregistration.isSynced();


        //assert
        assertEquals(true,isSynced);
    }

    @Test
    void setSynced()
    {
        //init
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, ZonedDateTime.now(),
                ZonedDateTime.now(),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        timeregistration.setSynced(false);


        //assert
        assertEquals(false,timeregistration.isSynced());
    }

    @Test
    void getEmployee()
    {
        //init
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, ZonedDateTime.now(),
                ZonedDateTime.now(),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        Employee employee = timeregistration.getEmployee();


        //assert
        assertEquals(appFacade.getDataSource().employeeDao().findById(1),employee);
    }

    @Test
    void setEmployee()
    {
        //init
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, ZonedDateTime.now(),
                ZonedDateTime.now(),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        timeregistration.setEmployee(appFacade.getDataSource().employeeDao().findById(2));


        //assert
        assertEquals(appFacade.getDataSource().employeeDao().findById(2),timeregistration.getEmployee());
    }

    @Test
    void getStart()
    {
        //init
        ZonedDateTime initialTime = ZonedDateTime.now();
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, initialTime,
                ZonedDateTime.now().plusHours(1),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        ZonedDateTime start = timeregistration.getStart();


        //assert
        assertEquals(initialTime,start);
    }

    @Test
    void setStart()
    {
        //init
        ZonedDateTime initialTime = ZonedDateTime.now();
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, initialTime,
                initialTime.plusHours(1),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        timeregistration.setStart(initialTime.plusMinutes(1));


        //assert
        assertEquals(initialTime.plusMinutes(1),timeregistration.getStart());
    }

    @Test
    void getEnd()
    {
        //init
        ZonedDateTime initialTime = ZonedDateTime.now();
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, initialTime,
                initialTime.plusHours(1),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        ZonedDateTime end = timeregistration.getEnd();


        //assert
        assertEquals(initialTime.plusHours(1),end);
    }

    @Test
    void setEnd()
    {
        //init
        ZonedDateTime initialTime = ZonedDateTime.now();
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, initialTime,
                initialTime.plusHours(1),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        timeregistration.setEnd(initialTime.plusMinutes(1));


        //assert
        assertEquals(initialTime.plusMinutes(1),timeregistration.getEnd());
    }

    @Test
    void getProjectTask()
    {
        //init
        ZonedDateTime initialTime = ZonedDateTime.now();
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, initialTime,
                initialTime.plusHours(1),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        //act
        ProjectTask projectTask = timeregistration.getProjectTask();


        //assert
        assertEquals(appFacade.getDataSource().taskDao().findById(1),projectTask);
    }

    @Test
    void setProjectTask()
    {
        //init
        ZonedDateTime initialTime = ZonedDateTime.now();
        Timeregistration timeregistration = new Timeregistration(
                appFacade.getDataSource().timeregistrationDao(),
                900,true, initialTime,
                initialTime.plusHours(1),
                appFacade.getDataSource().taskDao().findById(1),
                appFacade.getDataSource().employeeDao().findById(1));

        ProjectTask projectTask = appFacade.getDataSource().taskDao().findById(2);

        //act
        timeregistration.setProjectTask(projectTask);


        //assert
        assertEquals(projectTask,timeregistration.getProjectTask());
    }
}