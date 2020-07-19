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
public enum XlLocationInTable implements ComEnum {
  /**
   * <p>
   * The value of this constant is -4110
   * </p>
   */
  xlColumnHeader(-4110),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlColumnItem(5),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  xlDataHeader(3),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlDataItem(7),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlPageHeader(2),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  xlPageItem(6),
  /**
   * <p>
   * The value of this constant is -4153
   * </p>
   */
  xlRowHeader(-4153),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlRowItem(4),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  xlTableBody(8),
  ;

  private final int value;
  XlLocationInTable(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
