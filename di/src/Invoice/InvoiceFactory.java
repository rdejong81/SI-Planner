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
