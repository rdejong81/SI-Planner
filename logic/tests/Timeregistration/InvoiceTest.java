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

import Data.*;
import Facade.AppFacade;
import Facade.IDataEntityPresenter;
import Projects.Project;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InvoiceTest
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
    void generate()
    {
        //init
        InvoiceIAO invoiceIAO = new MockInvoiceIAO();
        Customer customer = appFacade.getDataSource().customerDao().findById(1);
        Employee employee = appFacade.getDataSource().employeeDao().findById(1);
        byte[] templateData = "test".getBytes();
        DocumentTemplate documentTemplate = new DocumentTemplate(null,1,"test",
                templateData,
                customer,1);
        Invoice invoice = new Invoice(documentTemplate,employee,customer,invoiceIAO);
        //act
        invoice.generate(LocalDate.now().minusWeeks(3),LocalDate.now().plusWeeks(3));

        //assert
        assertArrayEquals(templateData,invoice.getDocument());

    }

    @Test
    void getDocumentTemplate()
    {
    }

    @Test
    void getDocument()
    {
    }
}