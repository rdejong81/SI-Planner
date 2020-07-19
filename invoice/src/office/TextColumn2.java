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

@IID("{000C03B2-0000-0000-C000-000000000046}")
public interface TextColumn2 extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Number"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  int getNumber();


  /**
   * <p>
   * Setter method for the COM property "Number"
   * </p>
   * @param number Mandatory int parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  void setNumber(
    int number);


  /**
   * <p>
   * Getter method for the COM property "Spacing"
   * </p>
   * @return  Returns a value of type float
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(11)
  float getSpacing();


  /**
   * <p>
   * Setter method for the COM property "Spacing"
   * </p>
   * @param spacing Mandatory float parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(12)
  void setSpacing(
    float spacing);


  /**
   * <p>
   * Getter method for the COM property "TextDirection"
   * </p>
   * @return  Returns a value of type office.MsoTextDirection
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(13)
  office.MsoTextDirection getTextDirection();


  /**
   * <p>
   * Setter method for the COM property "TextDirection"
   * </p>
   * @param direction Mandatory office.MsoTextDirection parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(14)
  void setTextDirection(
    office.MsoTextDirection direction);


  // Properties:
}
