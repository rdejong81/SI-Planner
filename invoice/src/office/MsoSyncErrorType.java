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
public enum MsoSyncErrorType {
  /**
   * <p>
   * The value of this constant is 0
   * </p>
   */
  msoSyncErrorNone, // 0
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  msoSyncErrorUnauthorizedUser, // 1
  /**
   * <p>
   * The value of this constant is 2
   * </p>
   */
  msoSyncErrorCouldNotConnect, // 2
  /**
   * <p>
   * The value of this constant is 3
   * </p>
   */
  msoSyncErrorOutOfSpace, // 3
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  msoSyncErrorFileNotFound, // 4
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  msoSyncErrorFileTooLarge, // 5
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  msoSyncErrorFileInUse, // 6
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  msoSyncErrorVirusUpload, // 7
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  msoSyncErrorVirusDownload, // 8
  /**
   * <p>
   * The value of this constant is 9
   * </p>
   */
  msoSyncErrorUnknownUpload, // 9
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  msoSyncErrorUnknownDownload, // 10
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  msoSyncErrorCouldNotOpen, // 11
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  msoSyncErrorCouldNotUpdate, // 12
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  msoSyncErrorCouldNotCompare, // 13
  /**
   * <p>
   * The value of this constant is 14
   * </p>
   */
  msoSyncErrorCouldNotResolve, // 14
  /**
   * <p>
   * The value of this constant is 15
   * </p>
   */
  msoSyncErrorNoNetwork, // 15
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  msoSyncErrorUnknown, // 16
}
