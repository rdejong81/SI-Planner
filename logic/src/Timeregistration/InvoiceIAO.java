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
