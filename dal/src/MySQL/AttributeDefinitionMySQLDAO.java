package MySQL;

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
public class AttributeDefinitionMySQLDAO implements IAttributeDAO
{
    private final MySQLConnection mySQLConnection;
    private final ArrayList<Attribute> attributeInstances;  // list of instances of Task to prevent duplicate objects of the same database id.
    private final ArrayList<Attribute> attributesUpdating;  // list of Task instances that are being updated.

    protected AttributeDefinitionMySQLDAO(MySQLConnection mySQLConnection)
    {
        this.mySQLConnection = mySQLConnection;
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

            parent = mySQLConnection.customerDao().findById((Integer) row.get("customers_id"));

            attribute = new Attribute(this,(Integer) row.get("id"),
                    (String) row.get("attributeName"),
                    attributeValue,
                    parent,
                    entityType
            );
            attributeInstances.add(attribute);
            attributesUpdating.add(attribute);
        }

        attributesUpdating.remove(attribute);
        return attribute;
    }

    @Override
    public List<Attribute> findAll(DataEntity parent)
    {
        ArrayList<Attribute> found = new ArrayList<>();

        QueryResult result = new QueryResult(mySQLConnection,
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
        QueryResult result = new QueryResult(mySQLConnection,String.format(
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

        QueryResult result = new QueryResult(mySQLConnection,String.format(
                            "update attribute_definitions set attributeType=%d,attributeName='%s',entityType=%d,customers_id=%d where id=%d",
                            AttributeType.fromClass(attribute.getValue().getClass()).getId(),
                            attribute.getName().replace("'","%%%"),
                            attribute.getEntityType().getId(),
                            attribute.getParent().getId(),
                            attribute.getId()
                    ));

        if(result.getLastError() == null)
            return DaoResult.OP_OK; else
                return DaoResult.DAO_MISSING;
    }

    @Override
    public DaoResult deleteAttribute(Attribute attribute)
    {
        if(attributesUpdating.contains(attribute)) return DaoResult.OP_OK; //already updating
        QueryResult result = new QueryResult(mySQLConnection,String.format("delete from attribute_values where id=%d", attribute.getId()));
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
