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

@IID("{000C033D-0000-0000-C000-000000000046}")
public interface ICTPFactory extends Com4jObject {
  // Methods:
  /**
   * @param ctpAxID Mandatory java.lang.String parameter.
   * @param ctpTitle Mandatory java.lang.String parameter.
   * @param ctpParentWindow Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type office._CustomTaskPane
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  office._CustomTaskPane createCTP(
    java.lang.String ctpAxID,
    java.lang.String ctpTitle,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object ctpParentWindow);


  // Properties:
}
