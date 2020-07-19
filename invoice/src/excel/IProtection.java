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

@IID("{00024467-0001-0000-C000-000000000046}")
public interface IProtection extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "AllowFormattingCells"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(7)
  boolean getAllowFormattingCells();


  /**
   * <p>
   * Getter method for the COM property "AllowFormattingColumns"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(8)
  boolean getAllowFormattingColumns();


  /**
   * <p>
   * Getter method for the COM property "AllowFormattingRows"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(9)
  boolean getAllowFormattingRows();


  /**
   * <p>
   * Getter method for the COM property "AllowInsertingColumns"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(10)
  boolean getAllowInsertingColumns();


  /**
   * <p>
   * Getter method for the COM property "AllowInsertingRows"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(11)
  boolean getAllowInsertingRows();


  /**
   * <p>
   * Getter method for the COM property "AllowInsertingHyperlinks"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(12)
  boolean getAllowInsertingHyperlinks();


  /**
   * <p>
   * Getter method for the COM property "AllowDeletingColumns"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(13)
  boolean getAllowDeletingColumns();


  /**
   * <p>
   * Getter method for the COM property "AllowDeletingRows"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(14)
  boolean getAllowDeletingRows();


  /**
   * <p>
   * Getter method for the COM property "AllowSorting"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(15)
  boolean getAllowSorting();


  /**
   * <p>
   * Getter method for the COM property "AllowFiltering"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(16)
  boolean getAllowFiltering();


  /**
   * <p>
   * Getter method for the COM property "AllowUsingPivotTables"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(17)
  boolean getAllowUsingPivotTables();


  /**
   * <p>
   * Getter method for the COM property "AllowEditRanges"
   * </p>
   * @return  Returns a value of type excel.AllowEditRanges
   */

  @VTID(18)
  excel.AllowEditRanges getAllowEditRanges();


  // Properties:
}
