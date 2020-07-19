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

@IID("{00024432-0001-0000-C000-000000000046}")
public interface IAutoFilter extends Com4jObject {
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
   * Getter method for the COM property "Range"
   * </p>
   * @return  Returns a value of type excel.Range
   */

  @VTID(10)
  excel.Range getRange();


  /**
   * <p>
   * Getter method for the COM property "Filters"
   * </p>
   * @return  Returns a value of type excel.Filters
   */

  @VTID(11)
  excel.Filters getFilters();


  /**
   * <p>
   * Getter method for the COM property "FilterMode"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(12)
  boolean getFilterMode();


  /**
   * <p>
   * Getter method for the COM property "Sort"
   * </p>
   * @return  Returns a value of type excel.Sort
   */

  @VTID(13)
  excel.Sort getSort();


  /**
   */

  @VTID(14)
  void applyFilter();


  /**
   */

  @VTID(15)
  void showAllData();


  // Properties:
}
