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

@IID("{00020400-0000-0000-C000-000000000046}")
public interface RTD extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "ThrottleInterval"
   * </p>
   */

  @DISPID(2240)
  @PropGet
  int getThrottleInterval();


  /**
   * <p>
   * Setter method for the COM property "ThrottleInterval"
   * </p>
   * @param rhs Mandatory int parameter.
   */

  @DISPID(2240)
  @PropPut
  void setThrottleInterval(
    int rhs);


  /**
   */

  @DISPID(2241)
  void refreshData();


  /**
   */

  @DISPID(2242)
  void restartServers();


  // Properties:
}
