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
public enum MsoPresetThreeDFormat implements ComEnum {
  /**
   * <p>
   * The value of this constant is -2
   * </p>
   */
  msoPresetThreeDFormatMixed(-2),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoThreeD1(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoThreeD2(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  msoThreeD3(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoThreeD4(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  msoThreeD5(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  msoThreeD6(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  msoThreeD7(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  msoThreeD8(8),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  msoThreeD9(9),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  msoThreeD10(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  msoThreeD11(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  msoThreeD12(12),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  msoThreeD13(13),
  /**
   * <p>
   * The value of this constant is 14
   * </p>
   */
  msoThreeD14(14),
  /**
   * <p>
   * The value of this constant is 15
   * </p>
   */
  msoThreeD15(15),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  msoThreeD16(16),
  /**
   * <p>
   * The value of this constant is 17
   * </p>
   */
  msoThreeD17(17),
  /**
   * <p>
   * The value of this constant is 18
   * </p>
   */
  msoThreeD18(18),
  /**
   * <p>
   * The value of this constant is 19
   * </p>
   */
  msoThreeD19(19),
  /**
   * <p>
   * The value of this constant is 20
   * </p>
   */
  msoThreeD20(20),
  ;

  private final int value;
  MsoPresetThreeDFormat(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
