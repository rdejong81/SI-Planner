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
public enum MsoBevelType implements ComEnum {
  /**
   * <p>
   * The value of this constant is -2
   * </p>
   */
  msoBevelTypeMixed(-2),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoBevelNone(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoBevelRelaxedInset(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  msoBevelCircle(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoBevelSlope(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  msoBevelCross(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  msoBevelAngle(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  msoBevelSoftRound(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  msoBevelConvex(8),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  msoBevelCoolSlant(9),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  msoBevelDivot(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  msoBevelRiblet(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  msoBevelHardEdge(12),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  msoBevelArtDeco(13),
  ;

  private final int value;
  MsoBevelType(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
