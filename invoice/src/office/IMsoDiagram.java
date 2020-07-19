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

@IID("{000C036D-0000-0000-C000-000000000046}")
public interface IMsoDiagram extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(100) //= 0x64. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Nodes"
   * </p>
   * @return  Returns a value of type office.DiagramNodes
   */

  @DISPID(101) //= 0x65. The runtime will prefer the VTID if present
  @VTID(10)
  office.DiagramNodes getNodes();


  @VTID(10)
  @ReturnValue(defaultPropertyThrough={office.DiagramNodes.class})
  excel.DiagramNode getNodes(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);

  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type office.MsoDiagramType
   */

  @DISPID(102) //= 0x66. The runtime will prefer the VTID if present
  @VTID(11)
  office.MsoDiagramType getType();


  /**
   * <p>
   * Getter method for the COM property "AutoLayout"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(12)
  office.MsoTriState getAutoLayout();


  /**
   * <p>
   * Setter method for the COM property "AutoLayout"
   * </p>
   * @param autoLayout Mandatory office.MsoTriState parameter.
   */

  @DISPID(103) //= 0x67. The runtime will prefer the VTID if present
  @VTID(13)
  void setAutoLayout(
    office.MsoTriState autoLayout);


  /**
   * <p>
   * Getter method for the COM property "Reverse"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(14)
  office.MsoTriState getReverse();


  /**
   * <p>
   * Setter method for the COM property "Reverse"
   * </p>
   * @param reverse Mandatory office.MsoTriState parameter.
   */

  @DISPID(104) //= 0x68. The runtime will prefer the VTID if present
  @VTID(15)
  void setReverse(
    office.MsoTriState reverse);


  /**
   * <p>
   * Getter method for the COM property "AutoFormat"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(16)
  office.MsoTriState getAutoFormat();


  /**
   * <p>
   * Setter method for the COM property "AutoFormat"
   * </p>
   * @param autoFormat Mandatory office.MsoTriState parameter.
   */

  @DISPID(105) //= 0x69. The runtime will prefer the VTID if present
  @VTID(17)
  void setAutoFormat(
    office.MsoTriState autoFormat);


  /**
   * @param type Mandatory office.MsoDiagramType parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(18)
  void convert(
    office.MsoDiagramType type);


  /**
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(19)
  void fitText();


  // Properties:
}
