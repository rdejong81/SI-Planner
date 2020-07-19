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
public enum XlCellType implements ComEnum {
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlCellTypeBlanks(4),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlCellTypeConstants(2),
  /**
   * <p>
   * The value of this constant is -4123
   * </p>
   */
  xlCellTypeFormulas(-4123),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  xlCellTypeLastCell(11),
  /**
   * <p>
   * The value of this constant is -4144
   * </p>
   */
  xlCellTypeComments(-4144),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  xlCellTypeVisible(12),
  /**
   * <p>
   * The value of this constant is -4172
   * </p>
   */
  xlCellTypeAllFormatConditions(-4172),
  /**
   * <p>
   * The value of this constant is -4173
   * </p>
   */
  xlCellTypeSameFormatConditions(-4173),
  /**
   * <p>
   * The value of this constant is -4174
   * </p>
   */
  xlCellTypeAllValidation(-4174),
  /**
   * <p>
   * The value of this constant is -4175
   * </p>
   */
  xlCellTypeSameValidation(-4175),
  ;

  private final int value;
  XlCellType(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
