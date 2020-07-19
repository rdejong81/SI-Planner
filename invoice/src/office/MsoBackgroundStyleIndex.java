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
public enum MsoBackgroundStyleIndex implements ComEnum {
  /**
   * <p>
   * The value of this constant is -2
   * </p>
   */
  msoBackgroundStyleMixed(-2),
  /**
   * <p>
   * The value of this constant is 0
   * </p>
   */
  msoBackgroundStyleNotAPreset(0),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoBackgroundStylePreset1(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoBackgroundStylePreset2(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  msoBackgroundStylePreset3(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoBackgroundStylePreset4(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  msoBackgroundStylePreset5(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  msoBackgroundStylePreset6(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  msoBackgroundStylePreset7(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  msoBackgroundStylePreset8(8),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  msoBackgroundStylePreset9(9),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  msoBackgroundStylePreset10(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  msoBackgroundStylePreset11(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  msoBackgroundStylePreset12(12),
  ;

  private final int value;
  MsoBackgroundStyleIndex(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
