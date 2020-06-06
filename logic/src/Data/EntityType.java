package Data;

import Planning.Planning;
import Projects.Project;
import Projects.ProjectTask;
import Timeregistration.Timeregistration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum EntityType
{
    CUSTOMER(0, Customer.class),
    EMPLOYEE(1, Employee.class),
    PROJECT(2, Project.class),
    TASK(3, ProjectTask.class),
    PLANNING(4, Planning.class),
    TIMEREGISTRATION(5, Timeregistration.class);


    private final int id;
    private final Class<?> typeClass;
    private EntityType(int id, Class<?> typeClass)
    {
        this.id = id;
        this.typeClass = typeClass;
    }

    public int getId()
    {
        return id;
    }

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

}
