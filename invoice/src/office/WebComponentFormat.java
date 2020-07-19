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

@IID("{000CD102-0000-0000-C000-000000000046}")
public interface WebComponentFormat extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getApplication();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "URL"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String getURL();


  /**
   * <p>
   * Setter method for the COM property "URL"
   * </p>
   * @param retValue Mandatory java.lang.String parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(10)
  void setURL(
    java.lang.String retValue);


  /**
   * <p>
   * Getter method for the COM property "HTML"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String getHTML();


  /**
   * <p>
   * Setter method for the COM property "HTML"
   * </p>
   * @param retValue Mandatory java.lang.String parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(12)
  void setHTML(
    java.lang.String retValue);


  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String getName();


  /**
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param retValue Mandatory java.lang.String parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(14)
  void setName(
    java.lang.String retValue);


  /**
   * <p>
   * Getter method for the COM property "Width"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(15)
  int getWidth();


  /**
   * <p>
   * Setter method for the COM property "Width"
   * </p>
   * @param retValue Mandatory int parameter.
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(16)
  void setWidth(
    int retValue);


  /**
   * <p>
   * Getter method for the COM property "Height"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(17)
  int getHeight();


  /**
   * <p>
   * Setter method for the COM property "Height"
   * </p>
   * @param retValue Mandatory int parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(18)
  void setHeight(
    int retValue);


  /**
   * <p>
   * Getter method for the COM property "PreviewGraphic"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(19)
  java.lang.String getPreviewGraphic();


  /**
   * <p>
   * Setter method for the COM property "PreviewGraphic"
   * </p>
   * @param retval Mandatory java.lang.String parameter.
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(20)
  void setPreviewGraphic(
    java.lang.String retval);


  /**
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(21)
  void launchPropertiesWindow();


  // Properties:
}
