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

package office  ;

import com4j.*;

@IID("{000C038E-0000-0000-C000-000000000046}")
public interface MetaProperties extends office._IMsoDispObj,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type office.MetaProperty
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  office.MetaProperty getItem(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * @param internalName Mandatory java.lang.String parameter.
   * @return  Returns a value of type office.MetaProperty
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  office.MetaProperty getItemByInternalName(
    java.lang.String internalName);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(11)
  int getCount();


  /**
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(12)
  java.lang.String validate();


  /**
   * <p>
   * Getter method for the COM property "ValidationError"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String getValidationError();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(14)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "SchemaXml"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(15)
  java.lang.String getSchemaXml();


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(16)
  java.util.Iterator<Com4jObject> iterator();

  // Properties:
}
