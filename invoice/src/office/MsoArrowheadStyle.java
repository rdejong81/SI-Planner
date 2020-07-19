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
public enum MsoArrowheadStyle implements ComEnum {
  /**
   * <p>
   * The value of this constant is -2
   * </p>
   */
  msoArrowheadStyleMixed(-2),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoArrowheadNone(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoArrowheadTriangle(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  msoArrowheadOpen(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoArrowheadStealth(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  msoArrowheadDiamond(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  msoArrowheadOval(6),
  ;

  private final int value;
  MsoArrowheadStyle(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
