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
public enum XlPattern implements ComEnum {
  /**
   * <p>
   * The value of this constant is -4105
   * </p>
   */
  xlPatternAutomatic(-4105),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  xlPatternChecker(9),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  xlPatternCrissCross(16),
  /**
   * <p>
   * The value of this constant is -4121
   * </p>
   */
  xlPatternDown(-4121),
  /**
   * <p>
   * The value of this constant is 17
   * </p>
   */
  xlPatternGray16(17),
  /**
   * <p>
   * The value of this constant is -4124
   * </p>
   */
  xlPatternGray25(-4124),
  /**
   * <p>
   * The value of this constant is -4125
   * </p>
   */
  xlPatternGray50(-4125),
  /**
   * <p>
   * The value of this constant is -4126
   * </p>
   */
  xlPatternGray75(-4126),
  /**
   * <p>
   * The value of this constant is 18
   * </p>
   */
  xlPatternGray8(18),
  /**
   * <p>
   * The value of this constant is 15
   * </p>
   */
  xlPatternGrid(15),
  /**
   * <p>
   * The value of this constant is -4128
   * </p>
   */
  xlPatternHorizontal(-4128),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  xlPatternLightDown(13),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  xlPatternLightHorizontal(11),
  /**
   * <p>
   * The value of this constant is 14
   * </p>
   */
  xlPatternLightUp(14),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  xlPatternLightVertical(12),
  /**
   * <p>
   * The value of this constant is -4142
   * </p>
   */
  xlPatternNone(-4142),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  xlPatternSemiGray75(10),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlPatternSolid(1),
  /**
   * <p>
   * The value of this constant is -4162
   * </p>
   */
  xlPatternUp(-4162),
  /**
   * <p>
   * The value of this constant is -4166
   * </p>
   */
  xlPatternVertical(-4166),
  /**
   * <p>
   * The value of this constant is 4000
   * </p>
   */
  xlPatternLinearGradient(4000),
  /**
   * <p>
   * The value of this constant is 4001
   * </p>
   */
  xlPatternRectangularGradient(4001),
  ;

  private final int value;
  XlPattern(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
