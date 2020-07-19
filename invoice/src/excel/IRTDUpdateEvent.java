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

@IID("{A43788C1-D91B-11D3-8F39-00C04F3651B8}")
public interface IRTDUpdateEvent extends Com4jObject {
  // Methods:
  /**
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(7)
  void updateNotify();


  /**
   * <p>
   * Getter method for the COM property "HeartbeatInterval"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(8)
  int getHeartbeatInterval();


  /**
   * <p>
   * Setter method for the COM property "HeartbeatInterval"
   * </p>
   * @param plRetVal Mandatory int parameter.
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(9)
  void setHeartbeatInterval(
    int plRetVal);


  /**
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(10)
  void disconnect();


  // Properties:
}
