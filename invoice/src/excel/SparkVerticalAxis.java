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
public interface SparkVerticalAxis extends Com4jObject {
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
   * Getter method for the COM property "MinScaleType"
   * </p>
   */

  @DISPID(2965)
  @PropGet
  excel.XlSparkScale getMinScaleType();


  /**
   * <p>
   * Setter method for the COM property "MinScaleType"
   * </p>
   * @param rhs Mandatory excel.XlSparkScale parameter.
   */

  @DISPID(2965)
  @PropPut
  void setMinScaleType(
    excel.XlSparkScale rhs);


  /**
   * <p>
   * Getter method for the COM property "CustomMinScaleValue"
   * </p>
   */

  @DISPID(2966)
  @PropGet
  java.lang.Object getCustomMinScaleValue();


  /**
   * <p>
   * Setter method for the COM property "CustomMinScaleValue"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @DISPID(2966)
  @PropPut
  void setCustomMinScaleValue(
    java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "MaxScaleType"
   * </p>
   */

  @DISPID(2967)
  @PropGet
  excel.XlSparkScale getMaxScaleType();


  /**
   * <p>
   * Setter method for the COM property "MaxScaleType"
   * </p>
   * @param rhs Mandatory excel.XlSparkScale parameter.
   */

  @DISPID(2967)
  @PropPut
  void setMaxScaleType(
    excel.XlSparkScale rhs);


  /**
   * <p>
   * Getter method for the COM property "CustomMaxScaleValue"
   * </p>
   */

  @DISPID(2968)
  @PropGet
  java.lang.Object getCustomMaxScaleValue();


  /**
   * <p>
   * Setter method for the COM property "CustomMaxScaleValue"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @DISPID(2968)
  @PropPut
  void setCustomMaxScaleValue(
    java.lang.Object rhs);


  // Properties:
}
