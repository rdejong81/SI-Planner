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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Attribute Data Acccess Object
 * @author Richard de Jong
 * @see IAttributeDAO
 */
public class AttributeSqliteDAO implements IAttributeDAO
{
    private final SqliteConnection sqliteConnection;
    private final ArrayList<Attribute> attributeInstances;  // list of instances of Task to prevent duplicate objects of the same database id.
    private final ArrayList<Attribute> attributesUpdating;  // list of Task instances that are being updated.

    public AttributeSqliteDAO(SqliteConnection sqliteConnection)
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
                    case STRING -> row.get("stringValue");
                    case INTEGER -> row.get("intValue");
                    case BOOLEAN -> row.get("boolValue");
                    case DATE -> row.get("dateValue") == null ? LocalDateTime.now() :
                            LocalDateTime.parse((String)row.get("dateValue"),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    case DOUBLE -> row.get("doubleValue");
                };

        for(Attribute currentAttribute : attributeInstances)
        {
            if(currentAttribute.getId() == (Integer)row.get("id_av"))
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

            parent = switch (entityType)
                    {
                        case CUSTOMER -> sqliteConnection.customerDao().findById((Integer) row.get("entity_id"));
                        case TASK -> sqliteConnection.taskDao().findById((Integer) row.get("entity_id"));
                        case EMPLOYEE -> sqliteConnection.employeeDao().findById((Integer) row.get("entity_id"));
                        case PROJECT -> sqliteConnection.projectDao().findById((Integer) row.get("entity_id"));
                        case PLANNING -> sqliteConnection.planningDao().findById((Integer) row.get("entity_id"));
                        case TIMEREGISTRATION -> sqliteConnection.timeregistrationDao().findById((Integer) row.get("entity_id"));
                    };


            attribute = new Attribute(this,(Integer) row.get("id_av"),
                    (String) row.get("attributeName"),
                    attributeValue,
                    parent,
                    sqliteConnection.attributeDefinitionDao().findById((Integer)row.get("id_ad")),
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
        for(Attribute attribute : attributeInstances)
        {
            if(attribute.getId() == id) return attribute;
        }

        QueryResult result = new QueryResult(sqliteConnection, String.format("select *,attribute_definitions.id,attribute_values.id as id_av as id_ad from attribute_values " +
                "left join attribute_definitions on attribute_values.attribute_definitions_id = attribute_definitions.id " +
                "where attribute_values.id=%d",id));

        for(HashMap<String,Object> row : result.getRows())
        {
            return processRow(row);
        }

        return null;
    }

    @Override
    public List<Attribute> findAll(DataEntity parent)
    {
        EntityType entityType = parent == null ? null : EntityType.fromEntity(parent);
        ArrayList<Attribute> results = new ArrayList<>();

        QueryResult result = parent == null ? new QueryResult(sqliteConnection,
                "select *,attribute_definitions.id as id_ad,attribute_values.id as id_av from attribute_values " +
                        "left join attribute_definitions on attribute_values.attribute_definitions_id = attribute_definitions.id ")
                : new QueryResult(sqliteConnection,
                String.format("select *,attribute_definitions.id as id_ad,attribute_values.id as id_av from attribute_values " +
                        "left join attribute_definitions on attribute_values.attribute_definitions_id = attribute_definitions.id " +
                        "where entity_id=%d and entityType=%d",parent.getId(),entityType.getId()));

        for(HashMap<String,Object> row : result.getRows())
        {
            results.add(processRow(row));
        }

        return Collections.unmodifiableList(results);
    }

    @Override
    public DaoResult insertAttribute(Attribute attribute, Attribute attributeDefinition)
    {
        QueryResult result = switch(AttributeType.fromClass(attributeDefinition.getValue().getClass()))
        {
            case STRING -> new QueryResult(sqliteConnection,String.format(
                    "insert into attribute_values (attribute_definitions_id,stringValue,entity_id) values (%d,'%s',%d)",
                    attributeDefinition.getId(),
                    ((String)attribute.getValue()).replace("'","%%%"),
                    attribute.getParent().getId()
            ));
            case INTEGER -> new QueryResult(sqliteConnection,String.format(
                    "insert into attribute_values (attribute_definitions_id,intValue,entity_id) values (%d,%d,%d)",
                    attributeDefinition.getId(),
                    attribute.getValue(),
                    attribute.getParent().getId()
            ));
            case BOOLEAN -> new QueryResult(sqliteConnection,String.format(
                    "insert into attribute_values (attribute_definitions_id,boolValue,entity_id) values (%d,%d,%d)",
                    attributeDefinition.getId(),
                    (Boolean)attribute.getValue() ? 1 : 0,
                    attribute.getParent().getId()
            ));
            case DATE -> new QueryResult(sqliteConnection,String.format(
                    "insert into attribute_values (attribute_definitions_id,dateValue,entity_id) values (%d,'%s',%d)",
                    attributeDefinition.getId(),
                    ((LocalDateTime)attribute.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                    attribute.getParent().getId()
            ));
            case DOUBLE -> new QueryResult(sqliteConnection,String.format(
                    "insert into attribute_values (attribute_definitions_id,doubleValue,entity_id) values (%d,%s,%d)",
                    attributeDefinition.getId(),
                    ((Double)attribute.getValue()).toString(),
                    attribute.getParent().getId()
            ));
        };

        attribute.setId((int)result.getCreatedKey());
        attributeInstances.add(attribute);
        return DaoResult.OP_OK;
    }

    @Override
    public DaoResult updateAttribute(Attribute attribute)
    {
        if(attributesUpdating.contains(attribute)) return DaoResult.OP_OK; //already updating

        QueryResult result = switch(AttributeType.fromClass(attribute.getValue().getClass()))
                {
                    case STRING -> new QueryResult(sqliteConnection,String.format(
                            "update attribute_values set stringValue='%s',entity_id=%d where id=%d",
                            ((String)attribute.getValue()).replace("'","%%%"),
                            attribute.getParent().getId(),
                            attribute.getId()
                    ));
                    case INTEGER -> new QueryResult(sqliteConnection,String.format(
                            "update attribute_values set intValue=%d,entity_id=%d where id=%d",
                            attribute.getValue(),
                            attribute.getParent().getId(),
                            attribute.getId()
                    ));
                    case BOOLEAN -> new QueryResult(sqliteConnection,String.format(
                            "update attribute_values set boolValue=%d,entity_id=%d where id=%d",
                            (Boolean)attribute.getValue() ? 1 : 0,
                            attribute.getParent().getId(),
                            attribute.getId()
                    ));
                    case DATE -> new QueryResult(sqliteConnection,String.format(
                            "update attribute_values set dateValue='%s',entity_id=%d where id=%d",
                            ((LocalDateTime)attribute.getValue()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                            attribute.getParent().getId(),
                            attribute.getId()
                    ));
                    case DOUBLE -> new QueryResult(sqliteConnection,String.format(
                            "update attribute_values set doubleValue=%s,entity_id=%d where id=%d",
                            ((Double)attribute.getValue()).toString(),
                            attribute.getParent().getId(),
                            attribute.getId()
                    ));
                };
        if(result.getLastError() == null)
            return DaoResult.OP_OK; else
                return DaoResult.DAO_MISSING;
    }

    @Override
    public DaoResult deleteAttribute(Attribute attribute)
    {
        if(attributesUpdating.contains(attribute)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(sqliteConnection,String.format("delete from attribute_values where id=%d", attribute.getId()));
        if(result.getLastError() == null)
        {
            attributeInstances.remove(attribute);
            return DaoResult.OP_OK;
        }
        if(result.getLastError().getCause() instanceof SQLIntegrityConstraintViolationException)
        {
            return DaoResult.DAO_INUSE;
        }
        return DaoResult.DAO_MISSING;
    }


}
