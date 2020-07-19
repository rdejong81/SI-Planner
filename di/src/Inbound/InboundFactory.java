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

package Inbound;

import ExcelInvoice.ExcelInvoiceIAO;
import Facade.InboundConnectionFactory;
import Facade.InvoiceConnectionFactory;
import OutlookInbound.OutlookInboundIAO;
import Planning.InboundIAO;
import Timeregistration.InvoiceIAO;

import java.util.Map;

public class InboundFactory implements InboundConnectionFactory
{

    public Map<String,Integer> getInboundDrivers(){
        return Map.of(
                "Outlook Online Calendar",1
        );
    }

    public InboundIAO InboundFactoryCreate(int type)
    {
        switch (type)
        {
            case 1:
                return new OutlookInboundIAO();
        }

        return null;
    }

}
