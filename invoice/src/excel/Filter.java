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
public interface Filter extends Com4jObject {
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
   * Getter method for the COM property "On"
   * </p>
   */

  @DISPID(1618)
  @PropGet
  boolean getOn();


  /**
   * <p>
   * Getter method for the COM property "Criteria1"
   * </p>
   */

  @DISPID(796)
  @PropGet
  java.lang.Object getCriteria1();


  /**
   * <p>
   * Getter method for the COM property "_Operator"
   * </p>
   */

  @DISPID(2641)
  @PropGet
  excel.XlAutoFilterOperator get_Operator();


  /**
   * <p>
   * Getter method for the COM property "Criteria2"
   * </p>
   */

  @DISPID(798)
  @PropGet
  java.lang.Object getCriteria2();


  /**
   * <p>
   * Getter method for the COM property "Operator"
   * </p>
   */

  @DISPID(797)
  @PropGet
  excel.XlAutoFilterOperator getOperator();


  /**
   * <p>
   * Setter method for the COM property "Operator"
   * </p>
   * @param rhs Mandatory excel.XlAutoFilterOperator parameter.
   */

  @DISPID(797)
  @PropPut
  void setOperator(
    excel.XlAutoFilterOperator rhs);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   */

  @DISPID(118)
  @PropGet
  int getCount();


  // Properties:
}
