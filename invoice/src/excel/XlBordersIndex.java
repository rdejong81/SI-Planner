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
public enum XlBordersIndex implements ComEnum {
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  xlInsideHorizontal(12),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  xlInsideVertical(11),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlDiagonalDown(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  xlDiagonalUp(6),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  xlEdgeBottom(9),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlEdgeLeft(7),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  xlEdgeRight(10),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  xlEdgeTop(8),
  ;

  private final int value;
  XlBordersIndex(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
