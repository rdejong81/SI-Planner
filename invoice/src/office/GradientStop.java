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

@IID("{000C03BF-0000-0000-C000-000000000046}")
public interface GradientStop extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Color"
   * </p>
   * @return  Returns a value of type office.ColorFormat
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  office.ColorFormat getColor();


  /**
   * <p>
   * Getter method for the COM property "Position"
   * </p>
   * @return  Returns a value of type float
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  float getPosition();


  /**
   * <p>
   * Setter method for the COM property "Position"
   * </p>
   * @param position Mandatory float parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(11)
  void setPosition(
    float position);


  /**
   * <p>
   * Getter method for the COM property "Transparency"
   * </p>
   * @return  Returns a value of type float
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(12)
  float getTransparency();


  /**
   * <p>
   * Setter method for the COM property "Transparency"
   * </p>
   * @param transparency Mandatory float parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(13)
  void setTransparency(
    float transparency);


  // Properties:
}
