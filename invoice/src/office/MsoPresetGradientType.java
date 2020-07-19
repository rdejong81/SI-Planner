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
public enum MsoPresetGradientType implements ComEnum {
  /**
   * <p>
   * The value of this constant is -2
   * </p>
   */
  msoPresetGradientMixed(-2),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoGradientEarlySunset(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoGradientLateSunset(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  msoGradientNightfall(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoGradientDaybreak(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  msoGradientHorizon(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  msoGradientDesert(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  msoGradientOcean(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  msoGradientCalmWater(8),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  msoGradientFire(9),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  msoGradientFog(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  msoGradientMoss(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  msoGradientPeacock(12),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  msoGradientWheat(13),
  /**
   * <p>
   * The value of this constant is 14
   * </p>
   */
  msoGradientParchment(14),
  /**
   * <p>
   * The value of this constant is 15
   * </p>
   */
  msoGradientMahogany(15),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  msoGradientRainbow(16),
  /**
   * <p>
   * The value of this constant is 17
   * </p>
   */
  msoGradientRainbowII(17),
  /**
   * <p>
   * The value of this constant is 18
   * </p>
   */
  msoGradientGold(18),
  /**
   * <p>
   * The value of this constant is 19
   * </p>
   */
  msoGradientGoldII(19),
  /**
   * <p>
   * The value of this constant is 20
   * </p>
   */
  msoGradientBrass(20),
  /**
   * <p>
   * The value of this constant is 21
   * </p>
   */
  msoGradientChrome(21),
  /**
   * <p>
   * The value of this constant is 22
   * </p>
   */
  msoGradientChromeII(22),
  /**
   * <p>
   * The value of this constant is 23
   * </p>
   */
  msoGradientSilver(23),
  /**
   * <p>
   * The value of this constant is 24
   * </p>
   */
  msoGradientSapphire(24),
  ;

  private final int value;
  MsoPresetGradientType(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
