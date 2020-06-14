package Timeregistration;

import java.util.List;

/**
 * Invoice Access Object to fill in document template with required values.
 * A new document can be retrieved after replacing needed document parameters.
 * Available document parameters can also be checked.
 *
 * @author Richard de Jong
 * @since 1.0
 * @see Invoice
 */
public interface InvoiceIAO
{

    /**
     * start new template transformation
     * @param invoice
     */
    void start(Invoice invoice,String pageName);

    /**
     * stop template transformation, and cleanup.
     */
    void stop();

    List<String> findParameters();
    void fillParameter(String name, Object value);
    byte[] retrieve();
    String supportedFileExtension();
}
