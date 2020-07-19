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

@IID("{000C172B-0000-0000-C000-000000000046}")
public interface ChartGroups extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(7)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(118) //= 0x76. The runtime will prefer the VTID if present
  @VTID(8)
  int getCount();


  /**
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type office.IMsoChartGroup
   */

  @DISPID(170) //= 0xaa. The runtime will prefer the VTID if present
  @VTID(9)
  office.IMsoChartGroup item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(10)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(148) //= 0x94. The runtime will prefer the VTID if present
  @VTID(11)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(149) //= 0x95. The runtime will prefer the VTID if present
  @VTID(12)
  int getCreator();


  // Properties:
}
