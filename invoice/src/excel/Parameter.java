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
public interface Parameter extends Com4jObject {
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
   * Getter method for the COM property "DataType"
   * </p>
   */

  @DISPID(722)
  @PropGet
  excel.XlParameterDataType getDataType();


  /**
   * <p>
   * Setter method for the COM property "DataType"
   * </p>
   * @param rhs Mandatory excel.XlParameterDataType parameter.
   */

  @DISPID(722)
  @PropPut
  void setDataType(
    excel.XlParameterDataType rhs);


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   */

  @DISPID(108)
  @PropGet
  excel.XlParameterType getType();


  /**
   * <p>
   * Getter method for the COM property "PromptString"
   * </p>
   */

  @DISPID(1599)
  @PropGet
  java.lang.String getPromptString();


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
   * Getter method for the COM property "SourceRange"
   * </p>
   */

  @DISPID(1600)
  @PropGet
  excel.Range getSourceRange();


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
   * @param type Mandatory excel.XlParameterType parameter.
   * @param value Mandatory java.lang.Object parameter.
   */

  @DISPID(1601)
  void setParam(
    excel.XlParameterType type,
    java.lang.Object value);


  /**
   * <p>
   * Getter method for the COM property "RefreshOnChange"
   * </p>
   */

  @DISPID(1879)
  @PropGet
  boolean getRefreshOnChange();


  /**
   * <p>
   * Setter method for the COM property "RefreshOnChange"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(1879)
  @PropPut
  void setRefreshOnChange(
    boolean rhs);


  // Properties:
}
