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
public enum XlRemoveDocInfoType implements ComEnum {
  /**
   * <p>
   * The value of this constant is 1
   * </p>
   */
  xlRDIComments(1),
  /**
   * <p>
   * The value of this constant is 4
   * </p>
   */
  xlRDIRemovePersonalInformation(4),
  /**
   * <p>
   * The value of this constant is 5
   * </p>
   */
  xlRDIEmailHeader(5),
  /**
   * <p>
   * The value of this constant is 6
   * </p>
   */
  xlRDIRoutingSlip(6),
  /**
   * <p>
   * The value of this constant is 7
   * </p>
   */
  xlRDISendForReview(7),
  /**
   * <p>
   * The value of this constant is 8
   * </p>
   */
  xlRDIDocumentProperties(8),
  /**
   * <p>
   * The value of this constant is 10
   * </p>
   */
  xlRDIDocumentWorkspace(10),
  /**
   * <p>
   * The value of this constant is 11
   * </p>
   */
  xlRDIInkAnnotations(11),
  /**
   * <p>
   * The value of this constant is 12
   * </p>
   */
  xlRDIScenarioComments(12),
  /**
   * <p>
   * The value of this constant is 13
   * </p>
   */
  xlRDIPublishInfo(13),
  /**
   * <p>
   * The value of this constant is 14
   * </p>
   */
  xlRDIDocumentServerProperties(14),
  /**
   * <p>
   * The value of this constant is 15
   * </p>
   */
  xlRDIDocumentManagementPolicy(15),
  /**
   * <p>
   * The value of this constant is 16
   * </p>
   */
  xlRDIContentType(16),
  /**
   * <p>
   * The value of this constant is 18
   * </p>
   */
  xlRDIDefinedNameComments(18),
  /**
   * <p>
   * The value of this constant is 19
   * </p>
   */
  xlRDIInactiveDataConnections(19),
  /**
   * <p>
   * The value of this constant is 20
   * </p>
   */
  xlRDIPrinterPath(20),
  /**
   * <p>
   * The value of this constant is 99
   * </p>
   */
  xlRDIAll(99),
  ;

  private final int value;
  XlRemoveDocInfoType(int value) { this.value=value; }
  public int comEnumValue() { return value; }
}
