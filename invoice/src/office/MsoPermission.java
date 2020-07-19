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
public enum MsoPermission implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoPermissionView(1),
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoPermissionRead(1),
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoPermissionEdit(2),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoPermissionSave(4),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  msoPermissionExtract(8),
  /**
   * <p>
   * The value of this constant is 15
   * </p>
   */
  msoPermissionChange(15),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  msoPermissionPrint(16),
  /**
   * <p>
   * The value of this constant is 32
   * </p>
   */
  msoPermissionObjModel(32),
  /**
   * <p>
   * The value of this constant is 64
   * </p>
   */
  msoPermissionFullControl(64),
  /**
   * <p>
   * The value of this constant is 127
   * </p>
   */
  msoPermissionAllCommon(127),
  ;

  private final int value;
  MsoPermission(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
