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

@IID("{000C03BD-0000-0000-C000-000000000046}")
public interface GlowFormat extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Radius"
   * </p>
   * @return  Returns a value of type float
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  float getRadius();


  /**
   * <p>
   * Setter method for the COM property "Radius"
   * </p>
   * @param radius Mandatory float parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  void setRadius(
    float radius);


  /**
   * <p>
   * Getter method for the COM property "Color"
   * </p>
   * @return  Returns a value of type office.ColorFormat
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(11)
  office.ColorFormat getColor();


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
