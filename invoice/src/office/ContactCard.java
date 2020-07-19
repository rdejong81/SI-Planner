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

@IID("{000C03F1-0000-0000-C000-000000000046}")
public interface ContactCard extends office._IMsoDispObj {
  // Methods:
  /**
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  void close();


  /**
   * @param cardStyle Mandatory office.MsoContactCardStyle parameter.
   * @param rectangleLeft Mandatory int parameter.
   * @param rectangleRight Mandatory int parameter.
   * @param rectangleTop Mandatory int parameter.
   * @param rectangleBottom Mandatory int parameter.
   * @param horizontalPosition Mandatory int parameter.
   * @param showWithDelay Optional parameter. Default value is false
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  void show(
    office.MsoContactCardStyle cardStyle,
    int rectangleLeft,
    int rectangleRight,
    int rectangleTop,
    int rectangleBottom,
    int horizontalPosition,
    @Optional @DefaultValue("0") boolean showWithDelay);


  // Properties:
}
