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
public enum XlPictureConvertorType implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlBMP(1),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlCGM(7),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlDRW(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlDXF(5),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  xlEPS(8),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  xlHGL(6),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  xlPCT(13),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  xlPCX(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  xlPIC(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  xlPLT(12),
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  xlTIF(9),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlWMF(2),
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  xlWPG(3),
  ;

  private final int value;
  XlPictureConvertorType(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
