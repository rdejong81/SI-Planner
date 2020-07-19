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
public enum MsoCharacterSet implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoCharacterSetArabic(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoCharacterSetCyrillic(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  msoCharacterSetEnglishWesternEuropeanOtherLatinScript(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoCharacterSetGreek(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  msoCharacterSetHebrew(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  msoCharacterSetJapanese(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  msoCharacterSetKorean(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  msoCharacterSetMultilingualUnicode(8),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  msoCharacterSetSimplifiedChinese(9),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  msoCharacterSetThai(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  msoCharacterSetTraditionalChinese(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  msoCharacterSetVietnamese(12),
  ;

  private final int value;
  MsoCharacterSet(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
