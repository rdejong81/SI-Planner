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
public enum XlPageBreakExtent implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlPageBreakFull(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  xlPageBreakPartial(2),
  ;

  private final int value;
  XlPageBreakExtent(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
