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

package Invoice;

import ExcelInvoice.ExcelInvoiceIAO;
import Facade.InvoiceConnectionFactory;
import Timeregistration.InvoiceIAO;

import java.util.Map;

public class InvoiceFactory implements InvoiceConnectionFactory
{

    public Map<String,Integer> getInvoiceDrivers(){
        return Map.of(
                "Excel",1
        );
    }

    public InvoiceIAO InvoiceFactoryCreate(int type)
    {
        switch (type)
        {
            case 1:
                return new ExcelInvoiceIAO();
        }

        return null;
    }

}
