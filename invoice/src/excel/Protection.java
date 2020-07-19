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
public interface Protection extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "AllowFormattingCells"
   * </p>
   */

  @DISPID(2032)
  @PropGet
  boolean getAllowFormattingCells();


  /**
   * <p>
   * Getter method for the COM property "AllowFormattingColumns"
   * </p>
   */

  @DISPID(2033)
  @PropGet
  boolean getAllowFormattingColumns();


  /**
   * <p>
   * Getter method for the COM property "AllowFormattingRows"
   * </p>
   */

  @DISPID(2034)
  @PropGet
  boolean getAllowFormattingRows();


  /**
   * <p>
   * Getter method for the COM property "AllowInsertingColumns"
   * </p>
   */

  @DISPID(2035)
  @PropGet
  boolean getAllowInsertingColumns();


  /**
   * <p>
   * Getter method for the COM property "AllowInsertingRows"
   * </p>
   */

  @DISPID(2036)
  @PropGet
  boolean getAllowInsertingRows();


  /**
   * <p>
   * Getter method for the COM property "AllowInsertingHyperlinks"
   * </p>
   */

  @DISPID(2037)
  @PropGet
  boolean getAllowInsertingHyperlinks();


  /**
   * <p>
   * Getter method for the COM property "AllowDeletingColumns"
   * </p>
   */

  @DISPID(2038)
  @PropGet
  boolean getAllowDeletingColumns();


  /**
   * <p>
   * Getter method for the COM property "AllowDeletingRows"
   * </p>
   */

  @DISPID(2039)
  @PropGet
  boolean getAllowDeletingRows();


  /**
   * <p>
   * Getter method for the COM property "AllowSorting"
   * </p>
   */

  @DISPID(2040)
  @PropGet
  boolean getAllowSorting();


  /**
   * <p>
   * Getter method for the COM property "AllowFiltering"
   * </p>
   */

  @DISPID(2041)
  @PropGet
  boolean getAllowFiltering();


  /**
   * <p>
   * Getter method for the COM property "AllowUsingPivotTables"
   * </p>
   */

  @DISPID(2042)
  @PropGet
  boolean getAllowUsingPivotTables();


  /**
   * <p>
   * Getter method for the COM property "AllowEditRanges"
   * </p>
   */

  @DISPID(2236)
  @PropGet
  excel.AllowEditRanges getAllowEditRanges();


  // Properties:
}
