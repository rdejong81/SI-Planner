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

@IID("{000C03C1-0000-0000-C000-000000000046}")
public interface Ruler2 extends office._IMsoDispObj {
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
   * Getter method for the COM property "Levels"
   * </p>
   * @return  Returns a value of type office.RulerLevels2
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  office.RulerLevels2 getLevels();


  @VTID(10)
  @ReturnValue(defaultPropertyThrough={office.RulerLevels2.class})
  office.RulerLevel2 getLevels(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);

  /**
   * <p>
   * Getter method for the COM property "TabStops"
   * </p>
   * @return  Returns a value of type office.TabStops2
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(11)
  office.TabStops2 getTabStops();


  @VTID(11)
  @ReturnValue(defaultPropertyThrough={office.TabStops2.class})
  office.TabStop2 getTabStops(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);

  // Properties:
}
