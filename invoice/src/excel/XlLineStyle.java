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
public enum XlLineStyle implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlContinuous(1),
  /**
   * <p>
   * The value of this constant is -4115
   * </p>
   */
  xlDash(-4115),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlDashDot(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlDashDotDot(5),
  /**
   * <p>
   * The value of this constant is -4118
   * </p>
   */
  xlDot(-4118),
  /**
   * <p>
   * The value of this constant is -4119
   * </p>
   */
  xlDouble(-4119),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  xlSlantDashDot(13),
  /**
   * <p>
   * The value of this constant is -4142
   * </p>
   */
  xlLineStyleNone(-4142),
  ;

  private final int value;
  XlLineStyle(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
