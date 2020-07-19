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
public interface Sparkline extends Com4jObject {
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
   * Getter method for the COM property "Location"
   * </p>
   */

  @DISPID(1397)
  @PropGet
  excel.Range getLocation();


  /**
   * <p>
   * Setter method for the COM property "Location"
   * </p>
   * @param rhs Mandatory excel.Range parameter.
   */

  @DISPID(1397)
  @PropPut
  void setLocation(
    excel.Range rhs);


  /**
   * <p>
   * Getter method for the COM property "SourceData"
   * </p>
   */

  @DISPID(686)
  @PropGet
  java.lang.String getSourceData();


  /**
   * <p>
   * Setter method for the COM property "SourceData"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @DISPID(686)
  @PropPut
  void setSourceData(
    java.lang.String rhs);


  /**
   * @param range Mandatory excel.Range parameter.
   */

  @DISPID(2949)
  void modifyLocation(
    excel.Range range);


  /**
   * @param formula Mandatory java.lang.String parameter.
   */

  @DISPID(2950)
  void modifySourceData(
    java.lang.String formula);


  // Properties:
}
