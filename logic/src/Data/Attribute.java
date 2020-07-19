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

import Facade.AppFacade;

import java.time.LocalDateTime;

public class Attribute
{
    private Object value;
    private String name;
    private int id;
    private final IAttributeDAO attributeDao;
    private final DataEntity parent;
    private EntityType entityType;


    private final Attribute parentDefinition;

    public Attribute(IAttributeDAO attributeDao, int id, String name, Object value, DataEntity parent, Attribute parentDefinition, EntityType entityType)
    {
        this.id = id;
        this.name = name;
        this.value = value;
        this.attributeDao = attributeDao;
        this.parent = parent;
        this.entityType = entityType;
        this.parentDefinition = parentDefinition;
    }

    public IAttributeDAO getAttributeDao()
    {
        return attributeDao;
    }

    public Object getValue()
    {
        return value;
    }

    public DaoResult setValue(Object value)
    {
        if(this.value.equals(value)) return DaoResult.OP_OK; // no change, update is expensive
        this.value = value;
        if(parent != null)
            parent.broadcastUpdate();
        return attributeDao.updateAttribute(this);
    }

    public String getName()
    {
        return name;
    }

    public DaoResult setName(String name)
    {
        this.name = name;
        return attributeDao.updateAttribute(this);
    }

    public EntityType getEntityType()
    {
        return entityType;
    }

    public DaoResult setEntityType(EntityType entityType)
    {
        this.entityType = entityType;
        // update presentation layer
        return attributeDao.updateAttribute(this);
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public AttributeType getAttributeType()
    {
        return AttributeType.fromClass(value.getClass());
    }

    public void setAttributeType(AttributeType attributeType)
    {
        // initialize new value based on attribute Type.
        value = switch (attributeType)
                {
                    case STRING -> "";
                    case DOUBLE -> 0.0;
                    case DATE -> LocalDateTime.now();
                    case BOOLEAN -> false;
                    case INTEGER -> 0;
                };
        // update presentation layer
        attributeDao.updateAttribute(this);
        if(parent != null )
            AppFacade.appFacade.resetPresentation();
    }

    public DataEntity getParent()
    {
        return parent;
    }

    public Attribute getParentDefinition()
    {
        return parentDefinition;
    }

}
