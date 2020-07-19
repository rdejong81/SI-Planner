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
public enum XlConsolidationFunction implements ComEnum {
  /**
   * <p>
   * The value of this constant is -4106
   * </p>
   */
  xlAverage(-4106),
  /**
   * <p>
   * The value of this constant is -4112
   * </p>
   */
  xlCount(-4112),
  /**
   * <p>
   * The value of this constant is -4113
   * </p>
   */
  xlCountNums(-4113),
  /**
   * <p>
   * The value of this constant is -4136
   * </p>
   */
  xlMax(-4136),
  /**
   * <p>
   * The value of this constant is -4139
   * </p>
   */
  xlMin(-4139),
  /**
   * <p>
   * The value of this constant is -4149
   * </p>
   */
  xlProduct(-4149),
  /**
   * <p>
   * The value of this constant is -4155
   * </p>
   */
  xlStDev(-4155),
  /**
   * <p>
   * The value of this constant is -4156
   * </p>
   */
  xlStDevP(-4156),
  /**
   * <p>
   * The value of this constant is -4157
   * </p>
   */
  xlSum(-4157),
  /**
   * <p>
   * The value of this constant is -4164
   * </p>
   */
  xlVar(-4164),
  /**
   * <p>
   * The value of this constant is -4165
   * </p>
   */
  xlVarP(-4165),
  /**
   * <p>
   * The value of this constant is 1000
   * </p>
   */
  xlUnknown(1000),
  ;

  private final int value;
  XlConsolidationFunction(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
