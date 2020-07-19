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

package vba  ;

import com4j.*;

@IID("{0002E167-0000-0000-C000-000000000046}")
public interface Events extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "ReferencesEvents"
   * </p>
   * @param vbProject Mandatory vba._VBProject parameter.
   * @return  Returns a value of type vba._ReferencesEvents
   */

  @DISPID(202) //= 0xca. The runtime will prefer the VTID if present
  @VTID(7)
  vba._ReferencesEvents getReferencesEvents(
    vba._VBProject vbProject);


  /**
   * <p>
   * Getter method for the COM property "CommandBarEvents"
   * </p>
   * @param commandBarControl Mandatory com4j.Com4jObject parameter.
   * @return  Returns a value of type vba._CommandBarControlEvents
   */

  @DISPID(205) //= 0xcd. The runtime will prefer the VTID if present
  @VTID(8)
  vba._CommandBarControlEvents getCommandBarEvents(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject commandBarControl);


  // Properties:
}
