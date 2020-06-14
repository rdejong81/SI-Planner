package Data;

import Planning.Planning;
import Projects.Project;
import Projects.ProjectTask;
import Timeregistration.Timeregistration;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EntityType
{
    CUSTOMER(0, Customer.class,true),
    EMPLOYEE(1, Employee.class,true),
    PROJECT(2, Project.class,true),
    TASK(3, ProjectTask.class,false),
    PLANNING(4, Planning.class,true),
    TIMEREGISTRATION(5, Timeregistration.class,true);


    private final int id;
    private final Class<?> typeClass;
    private final boolean allowAttributes;
    private EntityType(int id, Class<?> typeClass, boolean allowAttributes)
    {
        this.id = id;
        this.typeClass = typeClass;
        this.allowAttributes = allowAttributes;
    }

    public int getId()
    {
        return id;
    }
    public boolean isAllowAttributes() { return allowAttributes; }

    public Class<?> getTypeClass() { return typeClass; }


    static final Map<Class<?>, EntityType> typeClassMap = Arrays.stream(EntityType.values())
            .collect(Collectors.toMap(EntityType::getTypeClass, Function.identity()));
    static final Map<Integer, EntityType> idMap = Arrays.stream(EntityType.values())
            .collect(Collectors.toMap(EntityType::getId, Function.identity()));

    public static EntityType fromEntity(final DataEntity entity) {
        return typeClassMap.get(entity.getClass());
    }
    public static EntityType fromId(final int id) {
        return idMap.get(id);
    }

    public static List<EntityType> entityTypesWithAttributes(){
        ArrayList<EntityType> entityTypes = new ArrayList<>();
        for (EntityType entityType : EntityType.values())
        {
            if(entityType.isAllowAttributes())
                entityTypes.add(entityType);
        }
        return Collections.unmodifiableList(entityTypes);
    }

}
