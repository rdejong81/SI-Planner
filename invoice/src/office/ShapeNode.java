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

@IID("{000C0318-0000-0000-C000-000000000046}")
public interface ShapeNode extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "EditingType"
   * </p>
   * @return  Returns a value of type office.MsoEditingType
   */

  @DISPID(100) //= 0x64. The runtime will prefer the VTID if present
  @VTID(10)
  office.MsoEditingType getEditingType();


  /**
   * <p>
   * Getter method for the COM property "Points"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(11)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getPoints();


  /**
   * <p>
   * Getter method for the COM property "SegmentType"
   * </p>
   * @return  Returns a value of type office.MsoSegmentType
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(12)
  office.MsoSegmentType getSegmentType();


  // Properties:
}
