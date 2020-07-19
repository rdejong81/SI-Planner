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

@IID("{000244BC-0001-0000-C000-000000000046}")
public interface ISparkVerticalAxis extends Com4jObject {
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
   * Getter method for the COM property "MinScaleType"
   * </p>
   * @return  Returns a value of type excel.XlSparkScale
   */

  @VTID(10)
  excel.XlSparkScale getMinScaleType();


  /**
   * <p>
   * Setter method for the COM property "MinScaleType"
   * </p>
   * @param rhs Mandatory excel.XlSparkScale parameter.
   */

  @VTID(11)
  void setMinScaleType(
    excel.XlSparkScale rhs);


  /**
   * <p>
   * Getter method for the COM property "CustomMinScaleValue"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(12)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getCustomMinScaleValue();


  /**
   * <p>
   * Setter method for the COM property "CustomMinScaleValue"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(13)
  void setCustomMinScaleValue(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "MaxScaleType"
   * </p>
   * @return  Returns a value of type excel.XlSparkScale
   */

  @VTID(14)
  excel.XlSparkScale getMaxScaleType();


  /**
   * <p>
   * Setter method for the COM property "MaxScaleType"
   * </p>
   * @param rhs Mandatory excel.XlSparkScale parameter.
   */

  @VTID(15)
  void setMaxScaleType(
    excel.XlSparkScale rhs);


  /**
   * <p>
   * Getter method for the COM property "CustomMaxScaleValue"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(16)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getCustomMaxScaleValue();


  /**
   * <p>
   * Setter method for the COM property "CustomMaxScaleValue"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(17)
  void setCustomMaxScaleValue(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  // Properties:
}
