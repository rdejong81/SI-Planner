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

package office  ;

import com4j.*;

/**
 */
public enum XlDisplayUnit implements ComEnum {
  /**
   * <p>
   * The value of this constant is -2
   * </p>
   */
  xlHundreds(-2),
  /**
   * <p>
   * The value of this constant is -3
   * </p>
   */
  xlThousands(-3),
  /**
   * <p>
   * The value of this constant is -4
   * </p>
   */
  xlTenThousands(-4),
  /**
   * <p>
   * The value of this constant is -5
   * </p>
   */
  xlHundredThousands(-5),
  /**
   * <p>
   * The value of this constant is -6
   * </p>
   */
  xlMillions(-6),
  /**
   * <p>
   * The value of this constant is -7
   * </p>
   */
  xlTenMillions(-7),
  /**
   * <p>
   * The value of this constant is -8
   * </p>
   */
  xlHundredMillions(-8),
  /**
   * <p>
   * The value of this constant is -9
   * </p>
   */
  xlThousandMillions(-9),
  /**
   * <p>
   * The value of this constant is -10
   * </p>
   */
  xlMillionMillions(-10),
  /**
   * <p>
   * The value of this constant is -4114
   * </p>
   */
  xlDisplayUnitCustom(-4114),
  /**
   * <p>
   * The value of this constant is -4142
   * </p>
   */
  xlDisplayUnitNone(-4142),
  ;

  private final int value;
  XlDisplayUnit(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
