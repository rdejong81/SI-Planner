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

@IID("{000C03C6-0000-0000-C000-000000000046}")
public interface SmartArt extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "AllNodes"
   * </p>
   * @return  Returns a value of type office.SmartArtNodes
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  office.SmartArtNodes getAllNodes();


  @VTID(10)
  @ReturnValue(defaultPropertyThrough={office.SmartArtNodes.class})
  office.SmartArtNode getAllNodes(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);

  /**
   * <p>
   * Getter method for the COM property "Nodes"
   * </p>
   * @return  Returns a value of type office.SmartArtNodes
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(11)
  office.SmartArtNodes getNodes();


  @VTID(11)
  @ReturnValue(defaultPropertyThrough={office.SmartArtNodes.class})
  office.SmartArtNode getNodes(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);

  /**
   * <p>
   * Getter method for the COM property "Layout"
   * </p>
   * @return  Returns a value of type office.SmartArtLayout
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(12)
  office.SmartArtLayout getLayout();


  /**
   * <p>
   * Setter method for the COM property "Layout"
   * </p>
   * @param layout Mandatory office.SmartArtLayout parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(13)
  void setLayout(
    office.SmartArtLayout layout);


  /**
   * <p>
   * Getter method for the COM property "QuickStyle"
   * </p>
   * @return  Returns a value of type office.SmartArtQuickStyle
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(14)
  office.SmartArtQuickStyle getQuickStyle();


  /**
   * <p>
   * Setter method for the COM property "QuickStyle"
   * </p>
   * @param style Mandatory office.SmartArtQuickStyle parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(15)
  void setQuickStyle(
    office.SmartArtQuickStyle style);


  /**
   * <p>
   * Getter method for the COM property "Color"
   * </p>
   * @return  Returns a value of type office.SmartArtColor
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(16)
  office.SmartArtColor getColor();


  /**
   * <p>
   * Setter method for the COM property "Color"
   * </p>
   * @param colorStyle Mandatory office.SmartArtColor parameter.
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(17)
  void setColor(
    office.SmartArtColor colorStyle);


  /**
   * <p>
   * Getter method for the COM property "Reverse"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(18)
  office.MsoTriState getReverse();


  /**
   * <p>
   * Setter method for the COM property "Reverse"
   * </p>
   * @param reverse Mandatory office.MsoTriState parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(19)
  void setReverse(
    office.MsoTriState reverse);


  /**
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(20)
  void reset();


  // Properties:
}
