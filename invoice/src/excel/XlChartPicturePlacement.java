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
public enum XlChartPicturePlacement implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlSides(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlEnd(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  xlEndSides(3),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlFront(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlFrontSides(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  xlFrontEnd(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlAllFaces(7),
  ;

  private final int value;
  XlChartPicturePlacement(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
