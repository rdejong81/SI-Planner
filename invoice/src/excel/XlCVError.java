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
public enum XlCVError implements ComEnum {
  /**
   * <p>
   * The value of this constant is 2007
   * </p>
   */
  xlErrDiv0(2007),
  /**
   * <p>
   * The value of this constant is 2042
   * </p>
   */
  xlErrNA(2042),
  /**
   * <p>
   * The value of this constant is 2029
   * </p>
   */
  xlErrName(2029),
  /**
   * <p>
   * The value of this constant is 2000
   * </p>
   */
  xlErrNull(2000),
  /**
   * <p>
   * The value of this constant is 2036
   * </p>
   */
  xlErrNum(2036),
  /**
   * <p>
   * The value of this constant is 2023
   * </p>
   */
  xlErrRef(2023),
  /**
   * <p>
   * The value of this constant is 2015
   * </p>
   */
  xlErrValue(2015),
  ;

  private final int value;
  XlCVError(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
