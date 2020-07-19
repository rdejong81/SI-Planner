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
public interface PivotFilter extends Com4jObject {
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
   * Getter method for the COM property "Order"
   * </p>
   */

  @DISPID(192)
  @PropGet
  int getOrder();


  /**
   * <p>
   * Setter method for the COM property "Order"
   * </p>
   * @param rhs Mandatory int parameter.
   */

  @DISPID(192)
  @PropPut
  void setOrder(
    int rhs);


  /**
   * <p>
   * Getter method for the COM property "FilterType"
   * </p>
   */

  @DISPID(2686)
  @PropGet
  excel.XlPivotFilterType getFilterType();


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
   * Getter method for the COM property "Description"
   * </p>
   */

  @DISPID(218)
  @PropGet
  java.lang.String getDescription();


  /**
   */

  @DISPID(117)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "Active"
   * </p>
   */

  @DISPID(2312)
  @PropGet
  boolean getActive();


  /**
   * <p>
   * Getter method for the COM property "PivotField"
   * </p>
   */

  @DISPID(731)
  @PropGet
  excel.PivotField getPivotField();


  /**
   * <p>
   * Getter method for the COM property "DataField"
   * </p>
   */

  @DISPID(2091)
  @PropGet
  excel.PivotField getDataField();


  /**
   * <p>
   * Getter method for the COM property "DataCubeField"
   * </p>
   */

  @DISPID(2687)
  @PropGet
  excel.CubeField getDataCubeField();


  /**
   * <p>
   * Getter method for the COM property "Value1"
   * </p>
   */

  @DISPID(2688)
  @PropGet
  java.lang.Object getValue1();


  /**
   * <p>
   * Getter method for the COM property "Value2"
   * </p>
   */

  @DISPID(1388)
  @PropGet
  java.lang.Object getValue2();


  /**
   * <p>
   * Getter method for the COM property "MemberPropertyField"
   * </p>
   */

  @DISPID(2689)
  @PropGet
  excel.PivotField getMemberPropertyField();


  /**
   * <p>
   * Getter method for the COM property "IsMemberPropertyFilter"
   * </p>
   */

  @DISPID(2690)
  @PropGet
  boolean getIsMemberPropertyFilter();


  // Properties:
}
