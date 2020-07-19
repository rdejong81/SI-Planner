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
public interface WorksheetView extends Com4jObject {
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
   * Getter method for the COM property "Sheet"
   * </p>
   */

  @DISPID(751)
  @PropGet
  com4j.Com4jObject getSheet();


  /**
   * <p>
   * Getter method for the COM property "DisplayGridlines"
   * </p>
   */

  @DISPID(645)
  @PropGet
  boolean getDisplayGridlines();


  /**
   * <p>
   * Setter method for the COM property "DisplayGridlines"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(645)
  @PropPut
  void setDisplayGridlines(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayFormulas"
   * </p>
   */

  @DISPID(644)
  @PropGet
  boolean getDisplayFormulas();


  /**
   * <p>
   * Setter method for the COM property "DisplayFormulas"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(644)
  @PropPut
  void setDisplayFormulas(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayHeadings"
   * </p>
   */

  @DISPID(646)
  @PropGet
  boolean getDisplayHeadings();


  /**
   * <p>
   * Setter method for the COM property "DisplayHeadings"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(646)
  @PropPut
  void setDisplayHeadings(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayOutline"
   * </p>
   */

  @DISPID(647)
  @PropGet
  boolean getDisplayOutline();


  /**
   * <p>
   * Setter method for the COM property "DisplayOutline"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(647)
  @PropPut
  void setDisplayOutline(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayZeros"
   * </p>
   */

  @DISPID(649)
  @PropGet
  boolean getDisplayZeros();


  /**
   * <p>
   * Setter method for the COM property "DisplayZeros"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(649)
  @PropPut
  void setDisplayZeros(
    boolean rhs);


  // Properties:
}
