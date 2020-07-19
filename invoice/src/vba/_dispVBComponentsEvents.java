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

@IID("{00020400-0000-0000-C000-000000000046}")
public interface _dispVBComponentsEvents extends Com4jObject {
  // Methods:
  /**
   * @param vbComponent Mandatory vba._VBComponent parameter.
   */

  @DISPID(1)
  void itemAdded(
    vba._VBComponent vbComponent);


  /**
   * @param vbComponent Mandatory vba._VBComponent parameter.
   */

  @DISPID(2)
  void itemRemoved(
    vba._VBComponent vbComponent);


  /**
   * @param vbComponent Mandatory vba._VBComponent parameter.
   * @param oldName Mandatory java.lang.String parameter.
   */

  @DISPID(3)
  void itemRenamed(
    vba._VBComponent vbComponent,
    java.lang.String oldName);


  /**
   * @param vbComponent Mandatory vba._VBComponent parameter.
   */

  @DISPID(4)
  void itemSelected(
    vba._VBComponent vbComponent);


  /**
   * @param vbComponent Mandatory vba._VBComponent parameter.
   */

  @DISPID(5)
  void itemActivated(
    vba._VBComponent vbComponent);


  /**
   * @param vbComponent Mandatory vba._VBComponent parameter.
   */

  @DISPID(6)
  void itemReloaded(
    vba._VBComponent vbComponent);


  // Properties:
}
