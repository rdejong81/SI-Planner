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

@IID("{000C03D6-0000-0000-C000-000000000046}")
public interface IConverterUICallback extends Com4jObject {
  // Methods:
  /**
   * @param uPercentComplete Mandatory int parameter.
   */

  @VTID(3)
  void hrReportProgress(
    int uPercentComplete);


  /**
   * @param bstrText Mandatory java.lang.String parameter.
   * @param bstrCaption Mandatory java.lang.String parameter.
   * @param uType Mandatory int parameter.
   * @return  Returns a value of type int
   */

  @VTID(4)
  int hrMessageBox(
    java.lang.String bstrText,
    java.lang.String bstrCaption,
    int uType);


  /**
   * @param bstrText Mandatory java.lang.String parameter.
   * @param bstrCaption Mandatory java.lang.String parameter.
   * @param fPassword Mandatory int parameter.
   * @return  Returns a value of type java.lang.String
   */

  @VTID(5)
  @ReturnValue(index=2)
  java.lang.String hrInputBox(
    java.lang.String bstrText,
    java.lang.String bstrCaption,
    int fPassword);


  // Properties:
}
