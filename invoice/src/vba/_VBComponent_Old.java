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

@IID("{0002E164-0000-0000-C000-000000000046}")
public interface _VBComponent_Old extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Saved"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(7)
  boolean getSaved();


  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(48) //= 0x30. The runtime will prefer the VTID if present
  @VTID(8)
  java.lang.String getName();


  /**
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param pbstrReturn Mandatory java.lang.String parameter.
   */

  @DISPID(48) //= 0x30. The runtime will prefer the VTID if present
  @VTID(9)
  void setName(
    java.lang.String pbstrReturn);


  /**
   * <p>
   * Getter method for the COM property "Designer"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(49) //= 0x31. The runtime will prefer the VTID if present
  @VTID(10)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getDesigner();


  /**
   * <p>
   * Getter method for the COM property "CodeModule"
   * </p>
   * @return  Returns a value of type vba._CodeModule
   */

  @DISPID(50) //= 0x32. The runtime will prefer the VTID if present
  @VTID(11)
  vba._CodeModule getCodeModule();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type vba.vbext_ComponentType
   */

  @DISPID(51) //= 0x33. The runtime will prefer the VTID if present
  @VTID(12)
  vba.vbext_ComponentType getType();


  /**
   * @param fileName Mandatory java.lang.String parameter.
   */

  @DISPID(52) //= 0x34. The runtime will prefer the VTID if present
  @VTID(13)
  void export(
    java.lang.String fileName);


  /**
   * <p>
   * Getter method for the COM property "VBE"
   * </p>
   * @return  Returns a value of type vba.VBE
   */

  @DISPID(53) //= 0x35. The runtime will prefer the VTID if present
  @VTID(14)
  vba.VBE getVBE();


  /**
   * <p>
   * Getter method for the COM property "Collection"
   * </p>
   * @return  Returns a value of type vba._VBComponents
   */

  @DISPID(54) //= 0x36. The runtime will prefer the VTID if present
  @VTID(15)
  vba._VBComponents getCollection();


  /**
   * <p>
   * Getter method for the COM property "HasOpenDesigner"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(55) //= 0x37. The runtime will prefer the VTID if present
  @VTID(16)
  boolean getHasOpenDesigner();


  /**
   * <p>
   * Getter method for the COM property "Properties"
   * </p>
   * @return  Returns a value of type vba._Properties
   */

  @DISPID(56) //= 0x38. The runtime will prefer the VTID if present
  @VTID(17)
  vba._Properties getProperties();


  /**
   * @return  Returns a value of type vba.Window
   */

  @DISPID(57) //= 0x39. The runtime will prefer the VTID if present
  @VTID(18)
  vba.Window designerWindow();


  /**
   */

  @DISPID(60) //= 0x3c. The runtime will prefer the VTID if present
  @VTID(19)
  void activate();


  // Properties:
}
