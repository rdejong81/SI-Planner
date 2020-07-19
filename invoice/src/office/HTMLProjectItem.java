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

@IID("{000C0358-0000-0000-C000-000000000046}")
public interface HTMLProjectItem extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  java.lang.String getName();


  /**
   * <p>
   * Getter method for the COM property "IsOpen"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  boolean getIsOpen();


  /**
   * @param fileName Mandatory java.lang.String parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  void loadFromFile(
    java.lang.String fileName);


  /**
   * @param openKind Optional parameter. Default value is 0
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(12)
  void open(
    @Optional @DefaultValue("0") office.MsoHTMLProjectOpen openKind);


  /**
   * @param fileName Mandatory java.lang.String parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(13)
  void saveCopyAs(
    java.lang.String fileName);


  /**
   * <p>
   * Getter method for the COM property "Text"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(14)
  java.lang.String getText();


  /**
   * <p>
   * Setter method for the COM property "Text"
   * </p>
   * @param text Mandatory java.lang.String parameter.
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(15)
  void setText(
    java.lang.String text);


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(16)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  // Properties:
}
