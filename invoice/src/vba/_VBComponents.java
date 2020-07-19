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

@IID("{EEE0091C-E393-11D1-BB03-00C04FB6C4A6}")
public interface _VBComponents extends vba._VBComponents_Old {
  // Methods:
  /**
   * @param progId Mandatory java.lang.String parameter.
   * @return  Returns a value of type vba._VBComponent
   */

  @DISPID(25) //= 0x19. The runtime will prefer the VTID if present
  @VTID(15)
  vba._VBComponent addCustom(
    java.lang.String progId);


  /**
   * @param index Optional parameter. Default value is 0
   * @return  Returns a value of type vba._VBComponent
   */

  @DISPID(26) //= 0x1a. The runtime will prefer the VTID if present
  @VTID(16)
  vba._VBComponent addMTDesigner(
    @Optional @DefaultValue("0") int index);


  // Properties:
}
