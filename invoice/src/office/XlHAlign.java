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
public enum XlHAlign implements ComEnum {
  /**
   * <p>
   * The value of this constant is -4108
   * </p>
   */
  xlHAlignCenter(-4108),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlHAlignCenterAcrossSelection(7),
  /**
   * <p>
   * The value of this constant is -4117
   * </p>
   */
  xlHAlignDistributed(-4117),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlHAlignFill(5),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlHAlignGeneral(1),
  /**
   * <p>
   * The value of this constant is -4130
   * </p>
   */
  xlHAlignJustify(-4130),
  /**
   * <p>
   * The value of this constant is -4131
   * </p>
   */
  xlHAlignLeft(-4131),
  /**
   * <p>
   * The value of this constant is -4152
   * </p>
   */
  xlHAlignRight(-4152),
  ;

  private final int value;
  XlHAlign(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
