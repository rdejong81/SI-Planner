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

package excel  ;

import com4j.*;

@IID("{000C0310-0000-0000-C000-000000000046}")
public interface Adjustments extends office._IMsoDispObj {
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
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory int parameter.
   * @return  Returns a value of type float
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(11)
  @DefaultMethod
  float getItem(
    int index);


  /**
   * <p>
   * Setter method for the COM property "Item"
   * </p>
   * @param index Mandatory int parameter.
   * @param val Mandatory float parameter.
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(12)
  @DefaultMethod
  void setItem(
    int index,
    float val);


  // Properties:
}
