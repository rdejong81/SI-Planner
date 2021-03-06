
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

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum AttributeType
{
    STRING(0, String.class,"Text field"),
    INTEGER(1, Integer.class,"Numeric field"),
    BOOLEAN(2, Boolean.class, "Checkbox field"),
    DATE(3, LocalDateTime.class,"Date field"),
    DOUBLE(4, Double.class,"Precision field");

    private final int id;
    private final Class<?> classType;
    private final String friendlyName;
    private AttributeType(int id, Class<?> classType,String friendlyName)
    {
        this.id = id;
        this.classType = classType;
        this.friendlyName = friendlyName;
    }

    public int getId()
    {
        return id;
    }
    public Class<?> getClassType()
    {
        return classType;
    }

    public final String getFriendlyName()
    {
        return friendlyName;
    }

    static final Map<Integer, AttributeType> idMap = Arrays.stream(AttributeType.values())
            .collect(Collectors.toMap(AttributeType::getId, Function.identity()));

    static final Map<Class<?>, AttributeType> classMap = Arrays.stream(AttributeType.values())
            .collect(Collectors.toMap(AttributeType::getClassType, Function.identity()));

    static final Map<String, AttributeType> nameMap = Arrays.stream(AttributeType.values())
            .collect(Collectors.toMap(AttributeType::getFriendlyName, Function.identity()));

    public static AttributeType fromId(final int id) {
        return idMap.get(id);
    }

    public static AttributeType fromFriendlyName(final String friendlyName)
    {
        return nameMap.get(friendlyName);
    }

    public static AttributeType fromClass(final Class<?> classType)
    {
        return classMap.get(classType);
    }
}
