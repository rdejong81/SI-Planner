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
public interface ColorScaleCriterion extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Index"
   * </p>
   */

  @DISPID(486)
  @PropGet
  int getIndex();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   */

  @DISPID(108)
  @PropGet
  excel.XlConditionValueTypes getType();


  /**
   * <p>
   * Setter method for the COM property "Type"
   * </p>
   * @param rhs Mandatory excel.XlConditionValueTypes parameter.
   */

  @DISPID(108)
  @PropPut
  void setType(
    excel.XlConditionValueTypes rhs);


  /**
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   */

  @DISPID(6)
  @PropGet
  java.lang.Object getValue();


  /**
   * <p>
   * Setter method for the COM property "Value"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @DISPID(6)
  @PropPut
  void setValue(
    java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "FormatColor"
   * </p>
   */

  @DISPID(2717)
  @PropGet
  excel.FormatColor getFormatColor();


  // Properties:
}
