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

@IID("{0002446F-0001-0000-C000-000000000046}")
public interface IDiagram extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type excel._Application
   */

  @VTID(7)
  excel._Application getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   * @return  Returns a value of type excel.XlCreator
   */

  @VTID(8)
  excel.XlCreator getCreator();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Nodes"
   * </p>
   * @return  Returns a value of type excel.DiagramNodes
   */

  @VTID(10)
  excel.DiagramNodes getNodes();


  @VTID(10)
  @ReturnValue(defaultPropertyThrough={excel.DiagramNodes.class})
  excel.DiagramNode getNodes(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);

  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type office.MsoDiagramType
   */

  @VTID(11)
  office.MsoDiagramType getType();


  /**
   * <p>
   * Getter method for the COM property "AutoLayout"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @VTID(12)
  office.MsoTriState getAutoLayout();


  /**
   * <p>
   * Setter method for the COM property "AutoLayout"
   * </p>
   * @param rhs Mandatory office.MsoTriState parameter.
   */

  @VTID(13)
  void setAutoLayout(
    office.MsoTriState rhs);


  /**
   * <p>
   * Getter method for the COM property "Reverse"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @VTID(14)
  office.MsoTriState getReverse();


  /**
   * <p>
   * Setter method for the COM property "Reverse"
   * </p>
   * @param rhs Mandatory office.MsoTriState parameter.
   */

  @VTID(15)
  void setReverse(
    office.MsoTriState rhs);


  /**
   * <p>
   * Getter method for the COM property "AutoFormat"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @VTID(16)
  office.MsoTriState getAutoFormat();


  /**
   * <p>
   * Setter method for the COM property "AutoFormat"
   * </p>
   * @param rhs Mandatory office.MsoTriState parameter.
   */

  @VTID(17)
  void setAutoFormat(
    office.MsoTriState rhs);


  /**
   * @param type Mandatory office.MsoDiagramType parameter.
   */

  @VTID(18)
  void convert(
    office.MsoDiagramType type);


  /**
   */

  @VTID(19)
  void fitText();


  // Properties:
}
