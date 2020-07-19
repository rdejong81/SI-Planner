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

@IID("{000C1712-0000-0000-C000-000000000046}")
public interface Axes extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1610743808) //= 0x60020000. The runtime will prefer the VTID if present
  @VTID(7)
  int getCount();


  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param type Mandatory office.XlAxisType parameter.
   * @param axisGroup Optional parameter. Default value is 1
   * @return  Returns a value of type office.IMsoAxis
   */

  @DISPID(1610743809) //= 0x60020001. The runtime will prefer the VTID if present
  @VTID(8)
  office.IMsoAxis getItem(
    office.XlAxisType type,
    @Optional @DefaultValue("1") office.XlAxisGroup axisGroup);


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(1610743810) //= 0x60020002. The runtime will prefer the VTID if present
  @VTID(9)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(148) //= 0x94. The runtime will prefer the VTID if present
  @VTID(10)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(149) //= 0x95. The runtime will prefer the VTID if present
  @VTID(11)
  int getCreator();


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @param type Mandatory office.XlAxisType parameter.
   * @param axisGroup Optional parameter. Default value is 1
   * @return  Returns a value of type office.IMsoAxis
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(12)
  @DefaultMethod
  office.IMsoAxis get_Default(
    office.XlAxisType type,
    @Optional @DefaultValue("1") office.XlAxisGroup axisGroup);


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(13)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  // Properties:
}
