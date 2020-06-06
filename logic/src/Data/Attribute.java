package Data;

import java.time.LocalDateTime;

public class Attribute
{
    private Object value;
    private String name;
    private int id;
    private final IAttributeDAO attributeDao;
    private final DataEntity parent;
    private EntityType entityType;

    public Attribute(IAttributeDAO attributeDao, int id,String name, Object value, DataEntity parent, EntityType entityType)
    {
        this.id = id;
        this.name = name;
        this.value = value;
        this.attributeDao = attributeDao;
        this.parent = parent;
        this.entityType = entityType;
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
        value = switch(attributeType)
                {
                    case STRING -> "";
                    case DOUBLE -> 1.0;
                    case DATE -> LocalDateTime.now();
                    case BOOLEAN -> true;
                    case INTEGER -> 1;
                };
        attributeDao.updateAttribute(this);
    }

    public DataEntity getParent(){
        return parent;
    }
}
