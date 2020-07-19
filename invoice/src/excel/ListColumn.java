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
public interface ListColumn extends Com4jObject {
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
   */

  @DISPID(117)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   */

  @DISPID(0)
  @PropGet
  @DefaultMethod
  java.lang.String get_Default();


  /**
   * <p>
   * Getter method for the COM property "ListDataFormat"
   * </p>
   */

  @DISPID(2321)
  @PropGet
  excel.ListDataFormat getListDataFormat();


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
   * Getter method for the COM property "Name"
   * </p>
   */

  @DISPID(110)
  @PropGet
  java.lang.String getName();


  /**
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @DISPID(110)
  @PropPut
  void setName(
    java.lang.String rhs);


  /**
   * <p>
   * Getter method for the COM property "Range"
   * </p>
   */

  @DISPID(197)
  @PropGet
  excel.Range getRange();


  /**
   * <p>
   * Getter method for the COM property "TotalsCalculation"
   * </p>
   */

  @DISPID(2322)
  @PropGet
  excel.XlTotalsCalculation getTotalsCalculation();


  /**
   * <p>
   * Setter method for the COM property "TotalsCalculation"
   * </p>
   * @param rhs Mandatory excel.XlTotalsCalculation parameter.
   */

  @DISPID(2322)
  @PropPut
  void setTotalsCalculation(
    excel.XlTotalsCalculation rhs);


  /**
   * <p>
   * Getter method for the COM property "XPath"
   * </p>
   */

  @DISPID(2258)
  @PropGet
  excel.XPath getXPath();


  /**
   * <p>
   * Getter method for the COM property "SharePointFormula"
   * </p>
   */

  @DISPID(2323)
  @PropGet
  java.lang.String getSharePointFormula();


  /**
   * <p>
   * Getter method for the COM property "DataBodyRange"
   * </p>
   */

  @DISPID(705)
  @PropGet
  excel.Range getDataBodyRange();


  /**
   * <p>
   * Getter method for the COM property "Total"
   * </p>
   */

  @DISPID(2681)
  @PropGet
  excel.Range getTotal();


  // Properties:
}
