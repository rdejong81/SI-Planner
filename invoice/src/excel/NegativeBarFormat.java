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
public interface NegativeBarFormat extends Com4jObject {
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
   * Getter method for the COM property "ColorType"
   * </p>
   */

  @DISPID(2195)
  @PropGet
  excel.XlDataBarNegativeColorType getColorType();


  /**
   * <p>
   * Setter method for the COM property "ColorType"
   * </p>
   * @param rhs Mandatory excel.XlDataBarNegativeColorType parameter.
   */

  @DISPID(2195)
  @PropPut
  void setColorType(
    excel.XlDataBarNegativeColorType rhs);


  /**
   * <p>
   * Getter method for the COM property "BorderColorType"
   * </p>
   */

  @DISPID(2969)
  @PropGet
  excel.XlDataBarNegativeColorType getBorderColorType();


  /**
   * <p>
   * Setter method for the COM property "BorderColorType"
   * </p>
   * @param rhs Mandatory excel.XlDataBarNegativeColorType parameter.
   */

  @DISPID(2969)
  @PropPut
  void setBorderColorType(
    excel.XlDataBarNegativeColorType rhs);


  /**
   * <p>
   * Getter method for the COM property "Color"
   * </p>
   */

  @DISPID(99)
  @PropGet
  com4j.Com4jObject getColor();


  /**
   * <p>
   * Getter method for the COM property "BorderColor"
   * </p>
   */

  @DISPID(2970)
  @PropGet
  com4j.Com4jObject getBorderColor();


  // Properties:
}
