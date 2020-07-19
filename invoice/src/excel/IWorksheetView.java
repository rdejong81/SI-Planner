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

@IID("{00024487-0001-0000-C000-000000000046}")
public interface IWorksheetView extends Com4jObject {
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
   * Getter method for the COM property "Sheet"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(10)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getSheet();


  /**
   * <p>
   * Getter method for the COM property "DisplayGridlines"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(11)
  boolean getDisplayGridlines();


  /**
   * <p>
   * Setter method for the COM property "DisplayGridlines"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(12)
  void setDisplayGridlines(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayFormulas"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(13)
  boolean getDisplayFormulas();


  /**
   * <p>
   * Setter method for the COM property "DisplayFormulas"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(14)
  void setDisplayFormulas(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayHeadings"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(15)
  boolean getDisplayHeadings();


  /**
   * <p>
   * Setter method for the COM property "DisplayHeadings"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(16)
  void setDisplayHeadings(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayOutline"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(17)
  boolean getDisplayOutline();


  /**
   * <p>
   * Setter method for the COM property "DisplayOutline"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(18)
  void setDisplayOutline(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "DisplayZeros"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(19)
  boolean getDisplayZeros();


  /**
   * <p>
   * Setter method for the COM property "DisplayZeros"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(20)
  void setDisplayZeros(
    boolean rhs);


  // Properties:
}
