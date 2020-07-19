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

@IID("{000C0334-0000-0000-C000-000000000046}")
public interface PropertyTests extends office._IMsoDispObj,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory int parameter.
   * @param lcid Mandatory int parameter.
   * @return  Returns a value of type office.PropertyTest
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  office.PropertyTest getItem(
    int index,
    @LCID int lcid);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   * @param name Mandatory java.lang.String parameter.
   * @param condition Mandatory office.MsoCondition parameter.
   * @param value Optional parameter. Default value is com4j.Variant.getMissing()
   * @param secondValue Optional parameter. Default value is com4j.Variant.getMissing()
   * @param connector Optional parameter. Default value is 1
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  void add(
    java.lang.String name,
    office.MsoCondition condition,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object value,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object secondValue,
    @Optional @DefaultValue("1") office.MsoConnector connector);


  /**
   * @param index Mandatory int parameter.
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  void remove(
    int index);


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(13)
  java.util.Iterator<Com4jObject> iterator();

  // Properties:
}
