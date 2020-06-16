
package Timeregistration;

import java.util.ArrayList;
import java.util.List;

public class MockInvoiceIAO implements InvoiceIAO
{
    @Override
    public void start(Invoice invoice, String pageName)
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public List<String> findParameters()
    {
        return new ArrayList<>();
    }

    @Override
    public void fillParameter(String name, Object value)
    {

    }

    @Override
    public byte[] retrieve()
    {
        return "test".getBytes();
    }

    @Override
    public String supportedFileExtension()
    {
        return null;
    }
}
