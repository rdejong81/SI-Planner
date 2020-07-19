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

@IID("{EC0E6191-DB51-11D3-8F3E-00C04F3651B8}")
public interface IRtdServer extends Com4jObject {
  // Methods:
  /**
   * @param callbackObject Mandatory excel.IRTDUpdateEvent parameter.
   * @return  Returns a value of type int
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(7)
  int serverStart(
    excel.IRTDUpdateEvent callbackObject);


    /**
     * @param topicCount Mandatory Holder<Integer> parameter.
     * @return  Returns a value of type java.lang.Object[]
     */

    @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
    @VTID(9)
    java.lang.Object[] refreshData(
      Holder<Integer> topicCount);


    /**
     * @param topicID Mandatory int parameter.
     */

    @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
    @VTID(10)
    void disconnectData(
      int topicID);


    /**
     * @return  Returns a value of type int
     */

    @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
    @VTID(11)
    int heartbeat();


    /**
     */

    @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
    @VTID(12)
    void serverTerminate();


    // Properties:
  }
