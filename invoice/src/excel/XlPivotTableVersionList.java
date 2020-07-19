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
public enum XlPivotTableVersionList implements ComEnum {
  /**
   * <p>
   * The value of this constant is 0
   * </p>
   */
  xlPivotTableVersion2000(0),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlPivotTableVersion10(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlPivotTableVersion11(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  xlPivotTableVersion12(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlPivotTableVersion14(4),
  /**
   * <p>
   * The value of this constant is -1
   * </p>
   */
  xlPivotTableVersionCurrent(-1),
  ;

  private final int value;
  XlPivotTableVersionList(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
