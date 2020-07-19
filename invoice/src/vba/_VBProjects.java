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

@IID("{EEE00919-E393-11D1-BB03-00C04FB6C4A6}")
public interface _VBProjects extends vba._VBProjects_Old {
  // Methods:
  /**
   * @param type Mandatory vba.vbext_ProjectType parameter.
   * @return  Returns a value of type vba._VBProject
   */

  @DISPID(137) //= 0x89. The runtime will prefer the VTID if present
  @VTID(12)
  vba._VBProject add(
    vba.vbext_ProjectType type);


  /**
   * @param lpc Mandatory vba._VBProject parameter.
   */

  @DISPID(138) //= 0x8a. The runtime will prefer the VTID if present
  @VTID(13)
  void remove(
    vba._VBProject lpc);


  /**
   * @param bstrPath Mandatory java.lang.String parameter.
   * @return  Returns a value of type vba._VBProject
   */

  @DISPID(139) //= 0x8b. The runtime will prefer the VTID if present
  @VTID(14)
  vba._VBProject open(
    java.lang.String bstrPath);


  // Properties:
}
