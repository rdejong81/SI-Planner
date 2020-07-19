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
public enum XlParameterDataType implements ComEnum {
  /**
   * <p>
   * The value of this constant is 0
   * </p>
   */
  xlParamTypeUnknown(0),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlParamTypeChar(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlParamTypeNumeric(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  xlParamTypeDecimal(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlParamTypeInteger(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlParamTypeSmallInt(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  xlParamTypeFloat(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlParamTypeReal(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  xlParamTypeDouble(8),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  xlParamTypeVarChar(12),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  xlParamTypeDate(9),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  xlParamTypeTime(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  xlParamTypeTimestamp(11),
  /**
   * <p>
   * The value of this constant is -1
   * </p>
   */
  xlParamTypeLongVarChar(-1),
  /**
   * <p>
   * The value of this constant is -2
   * </p>
   */
  xlParamTypeBinary(-2),
  /**
   * <p>
   * The value of this constant is -3
   * </p>
   */
  xlParamTypeVarBinary(-3),
  /**
   * <p>
   * The value of this constant is -4
   * </p>
   */
  xlParamTypeLongVarBinary(-4),
  /**
   * <p>
   * The value of this constant is -5
   * </p>
   */
  xlParamTypeBigInt(-5),
  /**
   * <p>
   * The value of this constant is -6
   * </p>
   */
  xlParamTypeTinyInt(-6),
  /**
   * <p>
   * The value of this constant is -7
   * </p>
   */
  xlParamTypeBit(-7),
  /**
   * <p>
   * The value of this constant is -8
   * </p>
   */
  xlParamTypeWChar(-8),
  ;

  private final int value;
  XlParameterDataType(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
