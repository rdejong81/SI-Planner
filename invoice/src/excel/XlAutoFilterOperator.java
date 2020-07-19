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

/**
 */
public enum XlAutoFilterOperator implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlAnd(1),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlBottom10Items(4),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  xlBottom10Percent(6),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlOr(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  xlTop10Items(3),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlTop10Percent(5),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlFilterValues(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  xlFilterCellColor(8),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  xlFilterFontColor(9),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  xlFilterIcon(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  xlFilterDynamic(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  xlFilterNoFill(12),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  xlFilterAutomaticFontColor(13),
  /**
   * <p>
   * The value of this constant is 14
   * </p>
   */
  xlFilterNoIcon(14),
  ;

  private final int value;
  XlAutoFilterOperator(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
