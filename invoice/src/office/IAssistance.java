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

@IID("{4291224C-DEFE-485B-8E69-6CF8AA85CB76}")
public interface IAssistance extends Com4jObject {
  // Methods:
  /**
   * <p>
   * ShowHelp Method
   * </p>
   * @param helpId Optional parameter. Default value is ""
   * @param scope Optional parameter. Default value is ""
   */

  @DISPID(1610743808) //= 0x60020000. The runtime will prefer the VTID if present
  @VTID(7)
  void showHelp(
    @Optional @DefaultValue("") java.lang.String helpId,
    @Optional @DefaultValue("") java.lang.String scope);


  /**
   * <p>
   * SearchHelp Method
   * </p>
   * @param query Mandatory java.lang.String parameter.
   * @param scope Optional parameter. Default value is ""
   */

  @DISPID(1610743809) //= 0x60020001. The runtime will prefer the VTID if present
  @VTID(8)
  void searchHelp(
    java.lang.String query,
    @Optional @DefaultValue("") java.lang.String scope);


  /**
   * <p>
   * SetDefaultContext Method
   * </p>
   * @param helpId Mandatory java.lang.String parameter.
   */

  @DISPID(1610743810) //= 0x60020002. The runtime will prefer the VTID if present
  @VTID(9)
  void setDefaultContext(
    java.lang.String helpId);


  /**
   * <p>
   * ClearDefaultContext Method
   * </p>
   * @param helpId Mandatory java.lang.String parameter.
   */

  @DISPID(1610743811) //= 0x60020003. The runtime will prefer the VTID if present
  @VTID(10)
  void clearDefaultContext(
    java.lang.String helpId);


  // Properties:
}
