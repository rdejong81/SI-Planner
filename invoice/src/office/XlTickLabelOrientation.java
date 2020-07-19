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
public enum XlTickLabelOrientation implements ComEnum {
  /**
   * <p>
   * The value of this constant is -4105
   * </p>
   */
  xlTickLabelOrientationAutomatic(-4105),
  /**
   * <p>
   * The value of this constant is -4170
   * </p>
   */
  xlTickLabelOrientationDownward(-4170),
  /**
   * <p>
   * The value of this constant is -4128
   * </p>
   */
  xlTickLabelOrientationHorizontal(-4128),
  /**
   * <p>
   * The value of this constant is -4171
   * </p>
   */
  xlTickLabelOrientationUpward(-4171),
  /**
   * <p>
   * The value of this constant is -4166
   * </p>
   */
  xlTickLabelOrientationVertical(-4166),
  ;

  private final int value;
  XlTickLabelOrientation(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
