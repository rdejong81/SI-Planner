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

package Data;

public class DocumentTemplate
{
    private String name;
    private int id;
    private byte[] data;
    private final Customer customer;
    private int type;
    private final IDocumentTemplateDAO documentTemplateDAO;

    public DocumentTemplate(IDocumentTemplateDAO documentTemplateDAO, int id, String name, byte[] data, Customer customer, int type)
    {
        this.data = data;
        this.id = id;
        this.name = name;
        this.customer = customer;
        this.documentTemplateDAO = documentTemplateDAO;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public DaoResult setName(String name)
    {
        this.name = name;
        return documentTemplateDAO.updateDocumentTemplate(this);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public byte[] getData()
    {
        return data;
    }

    public String getSize()
    {
        return data == null ? "empty" : String.valueOf(data.length);
    }

    public DaoResult setData(byte[] data)
    {
        this.data = data;
        return documentTemplateDAO.updateDocumentTemplate(this);
    }

    public Customer getCustomer()
    {
        return customer;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }
}
