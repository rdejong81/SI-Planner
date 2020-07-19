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

@IID("{00020400-0000-0000-C000-000000000046}")
public interface Diagram extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   */

  @DISPID(148)
  @PropGet
  excel._Application getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   */

  @DISPID(149)
  @PropGet
  excel.XlCreator getCreator();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   */

  @DISPID(150)
  @PropGet
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Nodes"
   * </p>
   */

  @DISPID(1701)
  @PropGet
  excel.DiagramNodes getNodes();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   */

  @DISPID(108)
  @PropGet
  office.MsoDiagramType getType();


  /**
   * <p>
   * Getter method for the COM property "AutoLayout"
   * </p>
   */

  @DISPID(2243)
  @PropGet
  office.MsoTriState getAutoLayout();


  /**
   * <p>
   * Setter method for the COM property "AutoLayout"
   * </p>
   * @param rhs Mandatory office.MsoTriState parameter.
   */

  @DISPID(2243)
  @PropPut
  void setAutoLayout(
    office.MsoTriState rhs);


  /**
   * <p>
   * Getter method for the COM property "Reverse"
   * </p>
   */

  @DISPID(2244)
  @PropGet
  office.MsoTriState getReverse();


  /**
   * <p>
   * Setter method for the COM property "Reverse"
   * </p>
   * @param rhs Mandatory office.MsoTriState parameter.
   */

  @DISPID(2244)
  @PropPut
  void setReverse(
    office.MsoTriState rhs);


  /**
   * <p>
   * Getter method for the COM property "AutoFormat"
   * </p>
   */

  @DISPID(114)
  @PropGet
  office.MsoTriState getAutoFormat();


  /**
   * <p>
   * Setter method for the COM property "AutoFormat"
   * </p>
   * @param rhs Mandatory office.MsoTriState parameter.
   */

  @DISPID(114)
  @PropPut
  void setAutoFormat(
    office.MsoTriState rhs);


  /**
   * @param type Mandatory office.MsoDiagramType parameter.
   */

  @DISPID(1046)
  void convert(
    office.MsoDiagramType type);


  /**
   */

  @DISPID(2304)
  void fitText();


  // Properties:
}
