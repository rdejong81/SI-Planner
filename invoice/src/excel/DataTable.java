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
public interface DataTable extends Com4jObject {
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
   * Getter method for the COM property "ShowLegendKey"
   * </p>
   */

  @DISPID(171)
  @PropGet
  boolean getShowLegendKey();


  /**
   * <p>
   * Setter method for the COM property "ShowLegendKey"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(171)
  @PropPut
  void setShowLegendKey(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "HasBorderHorizontal"
   * </p>
   */

  @DISPID(1671)
  @PropGet
  boolean getHasBorderHorizontal();


  /**
   * <p>
   * Setter method for the COM property "HasBorderHorizontal"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(1671)
  @PropPut
  void setHasBorderHorizontal(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "HasBorderVertical"
   * </p>
   */

  @DISPID(1672)
  @PropGet
  boolean getHasBorderVertical();


  /**
   * <p>
   * Setter method for the COM property "HasBorderVertical"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(1672)
  @PropPut
  void setHasBorderVertical(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "HasBorderOutline"
   * </p>
   */

  @DISPID(1673)
  @PropGet
  boolean getHasBorderOutline();


  /**
   * <p>
   * Setter method for the COM property "HasBorderOutline"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(1673)
  @PropPut
  void setHasBorderOutline(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "Border"
   * </p>
   */

  @DISPID(128)
  @PropGet
  excel.Border getBorder();


  /**
   * <p>
   * Getter method for the COM property "Font"
   * </p>
   */

  @DISPID(146)
  @PropGet
  excel.Font getFont();


  /**
   */

  @DISPID(235)
  void select();


  /**
   */

  @DISPID(117)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "AutoScaleFont"
   * </p>
   */

  @DISPID(1525)
  @PropGet
  java.lang.Object getAutoScaleFont();


  /**
   * <p>
   * Setter method for the COM property "AutoScaleFont"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @DISPID(1525)
  @PropPut
  void setAutoScaleFont(
    java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "Format"
   * </p>
   */

  @DISPID(116)
  @PropGet
  excel.ChartFormat getFormat();


  // Properties:
}
