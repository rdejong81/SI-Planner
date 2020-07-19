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

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Attribute Data Acccess Object
 * @author Richard de Jong
 * @see IAttributeDAO
 */
public class AttributeDefinitionSqliteDAO implements IAttributeDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<Attribute> attributeInstances;  // list of instances of Task to prevent duplicate objects of the same database id.
    private final ArrayList<Attribute> attributesUpdating;  // list of Task instances that are being updated.

    public AttributeDefinitionSqliteDAO(SqliteConnection sqliteConnection)
    {
        this.sqliteConnection = sqliteConnection;
        attributeInstances = new ArrayList<>();
        attributesUpdating = new ArrayList<>();
    }

    private Attribute processRow(HashMap<String,Object> row)
    {
        Attribute attribute = null;
        AttributeType attributeType = AttributeType.fromId((Integer)row.get("attributeType"));
        Object attributeValue = switch (attributeType)
                {
                    case STRING -> "";
                    case INTEGER -> 1;
                    case BOOLEAN -> Boolean.TRUE;
                    case DATE -> LocalDateTime.now();
                    case DOUBLE -> 1.0;
                };

        for(Attribute currentAttribute : attributeInstances)
        {
            if(currentAttribute.getId() == (Integer)row.get("id"))
            {
                attributesUpdating.add(currentAttribute);
                currentAttribute.setName((String)row.get("attributeName"));
                currentAttribute.setValue(attributeValue);
        //        currentAttribute.setCompleted((Boolean) row.get("completed"));
        //        currentAttribute.setProject(mySQLConnection.projectDao().findById((Integer)row.get("projects_id")));
                attribute = currentAttribute;
            }
        }
        if(attribute == null)
        {
            DataEntity parent;
            EntityType entityType = EntityType.fromId((Integer)row.get("entityType"));

            parent = sqliteConnection.customerDao().findById((Integer) row.get("customers_id"));

            attribute = new Attribute(this,(Integer) row.get("id"),
                    (String) row.get("attributeName"),
                    attributeValue,
                    parent,
                    null,
                    entityType
            );
            attributeInstances.add(attribute);
            attributesUpdating.add(attribute);
        }

        attributesUpdating.remove(attribute);
        return attribute;
    }

    @Override
    public Attribute findById(int id)
    {
        for(Attribute attributeDefinition : attributeInstances)
        {
            if(attributeDefinition.getId() == id) return attributeDefinition;
        }

        QueryResult result = new QueryResult(sqliteConnection, String.format("select * from attribute_definitions where id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public List<Attribute> findAll(DataEntity parent)
    {
        ArrayList<Attribute> found = new ArrayList<>();

        QueryResult result = new QueryResult(sqliteConnection,
                String.format("select * from attribute_definitions where customers_id=%d",parent.getId()));

        for(HashMap<String,Object> row : result.getRows())
        {
            found.add(processRow(row));
        }

        return Collections.unmodifiableList(found);
    }

    @Override
    public DaoResult insertAttribute(Attribute attribute, Attribute attributeDefinition)
    {
        QueryResult result = new QueryResult(sqliteConnection,String.format(
                    "insert into attribute_definitions (attributeType,attributeName,entityType,customers_id) values (%d,'%s',%d,%d)",
                    AttributeType.fromClass(attribute.getValue().getClass()).getId(),
                    attribute.getName().replace("'","%%%"),
                    attribute.getEntityType().getId(),
                    attribute.getParent().getId()
            ));

        attribute.setId((int)result.getCreatedKey());
        attributeInstances.add(attribute);
        ((Customer)attribute.getParent()).addAttributeDefinition(attribute);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateAttribute(Attribute attribute)
    {
        if(attributesUpdating.contains(attribute)) return DaoResult.OP_OK; //already updating

        QueryResult result = new QueryResult(sqliteConnection,String.format(
                            "update attribute_definitions set attributeType=%d,attributeName='%s',entityType=%d,customers_id=%d where id=%d",
                            AttributeType.fromClass(attribute.getValue().getClass()).getId(),
                            attribute.getName().replace("'","%%%"),
                            attribute.getEntityType().getId(),
                            attribute.getParent().getId(),
                            attribute.getId()
                    ));

        // update child attributes
        for(Attribute attributeChild : sqliteConnection.attributeDao().findAll(null))
        {
            if(attributeChild.getParentDefinition() == attribute)
            {
                // synchronize name and type that could have changed in presentation layer.
                attributeChild.setAttributeType(AttributeType.fromClass(attribute.getValue().getClass()));
                attributeChild.setName(attribute.getName());

            }
        }


        if(result.getLastError() == null)
            return DaoResult.OP_OK; else
                return DaoResult.DAO_MISSING;
    }

    @Override
    public DaoResult deleteAttribute(Attribute attribute)
    {
        if(attributesUpdating.contains(attribute)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from attribute_definitions where id=%d", attribute.getId()));
        if(result.getLastError() == null)
        {
            attributeInstances.remove(attribute);
            ((Customer)attribute.getParent()).removeAttributeDefinition(attribute);
            return DaoResult.OP_OK;
        }
        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }


}
