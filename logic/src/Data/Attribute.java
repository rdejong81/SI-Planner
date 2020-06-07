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
        this.value = value;
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
