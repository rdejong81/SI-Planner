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
public enum MsoFarEastLineBreakLanguageID implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1041
   * </p>
   */
  MsoFarEastLineBreakLanguageJapanese(1041),
  /**
   * <p>
   * The value of this constant is 1042
   * </p>
   */
  MsoFarEastLineBreakLanguageKorean(1042),
  /**
   * <p>
   * The value of this constant is 2052
   * </p>
   */
  MsoFarEastLineBreakLanguageSimplifiedChinese(2052),
  /**
   * <p>
   * The value of this constant is 1028
   * </p>
   */
  MsoFarEastLineBreakLanguageTraditionalChinese(1028),
  ;

  private final int value;
  MsoFarEastLineBreakLanguageID(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
