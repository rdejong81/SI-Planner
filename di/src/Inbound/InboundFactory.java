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
