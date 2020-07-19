
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
