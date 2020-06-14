package Facade;

import Timeregistration.InvoiceIAO;

import java.util.Map;

public interface InvoiceConnectionFactory
{
    Map<String,Integer> getInvoiceDrivers();
    InvoiceIAO InvoiceFactoryCreate(int type);
}
