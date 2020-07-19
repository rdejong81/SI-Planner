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

package Sqlite;

import Data.*;
import Sql.QueryResult;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Attribute Data Acccess Object
 * @author Richard de Jong
 * @see IAttributeDAO
 */
public class DocumentTemplateSqliteDAO implements IDocumentTemplateDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<DocumentTemplate> documentInstances;  // list of instances of Task to prevent duplicate objects of the same database id.
    private final ArrayList<DocumentTemplate> documentsUpdating;  // list of Task instances that are being updated.

    public DocumentTemplateSqliteDAO(SqliteConnection sqliteConnection)
    {
        this.sqliteConnection = sqliteConnection;
        documentInstances = new ArrayList<>();

        documentsUpdating = new ArrayList<>();
    }

    private DocumentTemplate processRow(HashMap<String,Object> row)
    {
        DocumentTemplate documentTemplate = null;

        for(DocumentTemplate currentTemplate : documentInstances)
        {
            if (currentTemplate.getId() == (Integer) row.get("id"))
            {
                documentsUpdating.add(currentTemplate);
                currentTemplate.setName((String) row.get("name"));
                currentTemplate.setData((byte[]) row.get("templateDocument"));
                documentTemplate = currentTemplate;

            }
        }

        if(documentTemplate == null)
        {
            documentTemplate = new DocumentTemplate(this,(Integer) row.get("id"),
                    (String) row.get("name"),
                    (byte[])row.get("templateDocument"),
                    sqliteConnection.customerDao().findById((Integer) row.get("customers_id")),
                    (Integer) row.get("documentType"));
            documentInstances.add(documentTemplate);
            documentsUpdating.add(documentTemplate);
        }

        documentsUpdating.remove(documentTemplate);
        return documentTemplate;
    }

    @Override
    public DocumentTemplate findById(int id)
    {
        for(DocumentTemplate documentTemplate : documentInstances)
        {
            if(documentTemplate.getId() == id) return documentTemplate;
        }

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from templates where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public List<DocumentTemplate> findAll()
    {
        ArrayList<DocumentTemplate> documentTemplates = new ArrayList<>();

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from templates"));

        for(HashMap<String,Object> row : result.getRows())
        {
            documentTemplates.add(processRow(row));
        }

        return Collections.unmodifiableList(documentTemplates);
    }


    @Override
    public DaoResult insertDocumentTemplate(DocumentTemplate documentTemplate)
    {
        long createdKey=-1;
        try
        {
            PreparedStatement preparedStatement = sqliteConnection.getConnection().prepareStatement(
                    "insert into templates (templateDocument,name,documentType,customers_id) values (?,?,?,?)"
            );
            preparedStatement.setBytes(1, documentTemplate.getData());
            preparedStatement.setString(2,documentTemplate.getName());
            preparedStatement.setInt(3,documentTemplate.getType());
            preparedStatement.setInt(4,documentTemplate.getCustomer().getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet != null && resultSet.next())
            { // This is an insert result, get created result id.
                createdKey = resultSet.getLong(1);
            }

            documentTemplate.setId((int) createdKey);
            documentInstances.add(documentTemplate);

            return DaoResult.OP_OK;

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return DaoResult.DAO_DUPLICATE;
        }

    }

    @Override
    public DaoResult updateDocumentTemplate(DocumentTemplate documentTemplate)
    {
        if(documentsUpdating.contains(documentTemplate)) return DaoResult.OP_OK; //already updating

        long createdKey=-1;
        try
        {
            PreparedStatement preparedStatement = sqliteConnection.getConnection().prepareStatement(
                    "update templates set templateDocument=?,name=?,customers_id=?,documentType=? where id=?"
            );
            preparedStatement.setBytes(1, documentTemplate.getData());
            preparedStatement.setString(2,documentTemplate.getName());
            preparedStatement.setInt(3,documentTemplate.getCustomer().getId());
            preparedStatement.setInt(4,documentTemplate.getType());
            preparedStatement.setInt(5,documentTemplate.getId());
            preparedStatement.executeUpdate();

            return DaoResult.OP_OK;

        } catch (SQLException throwables)
        {
            throwables.printStackTrace();
            return DaoResult.DAO_MISSING;
        }
    }

    @Override
    public DaoResult deleteDocumentTemplate(DocumentTemplate documentTemplate)
    {
        if(documentsUpdating.contains(documentTemplate)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from templates where id=%d", documentTemplate.getId()));
        if(result.getLastError() == null)
        {
            documentTemplate.getCustomer().removeDocumentTemplate(documentTemplate);
            documentInstances.remove(documentTemplate);
            return DaoResult.OP_OK;
        }
        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }


}
