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

@IID("{000C035A-0000-0000-C000-000000000046}")
public interface MsoDebugOptions extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "FeatureReports"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(9)
  int getFeatureReports();


  /**
   * <p>
   * Setter method for the COM property "FeatureReports"
   * </p>
   * @param puintFeatureReports Mandatory int parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(10)
  void setFeatureReports(
    int puintFeatureReports);


  /**
   * <p>
   * Getter method for the COM property "OutputToDebugger"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(11)
  boolean getOutputToDebugger();


  /**
   * <p>
   * Setter method for the COM property "OutputToDebugger"
   * </p>
   * @param pvarfOutputToDebugger Mandatory boolean parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(12)
  void setOutputToDebugger(
    boolean pvarfOutputToDebugger);


  /**
   * <p>
   * Getter method for the COM property "OutputToFile"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(13)
  boolean getOutputToFile();


  /**
   * <p>
   * Setter method for the COM property "OutputToFile"
   * </p>
   * @param pvarfOutputToFile Mandatory boolean parameter.
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(14)
  void setOutputToFile(
    boolean pvarfOutputToFile);


  /**
   * <p>
   * Getter method for the COM property "OutputToMessageBox"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(15)
  boolean getOutputToMessageBox();


  /**
   * <p>
   * Setter method for the COM property "OutputToMessageBox"
   * </p>
   * @param pvarfOutputToMessageBox Mandatory boolean parameter.
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(16)
  void setOutputToMessageBox(
    boolean pvarfOutputToMessageBox);


  /**
   * <p>
   * Getter method for the COM property "UnitTestManager"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(17)
  com4j.Com4jObject getUnitTestManager();


  // Properties:
}
