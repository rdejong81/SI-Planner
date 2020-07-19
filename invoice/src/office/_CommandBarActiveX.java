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

@IID("{000C030D-0000-0000-C000-000000000046}")
public interface _CommandBarActiveX extends office.CommandBarControl {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "ControlCLSID"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610940416) //= 0x60050000. The runtime will prefer the VTID if present
  @VTID(83)
  java.lang.String getControlCLSID();


  /**
   * <p>
   * Setter method for the COM property "ControlCLSID"
   * </p>
   * @param pbstrClsid Mandatory java.lang.String parameter.
   */

  @DISPID(1610940416) //= 0x60050000. The runtime will prefer the VTID if present
  @VTID(84)
  void setControlCLSID(
    java.lang.String pbstrClsid);


  /**
   * <p>
   * Getter method for the COM property "QueryControlInterface"
   * </p>
   * @param bstrIid Mandatory java.lang.String parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(1610940418) //= 0x60050002. The runtime will prefer the VTID if present
  @VTID(85)
  com4j.Com4jObject getQueryControlInterface(
    java.lang.String bstrIid);


  /**
   * @param pUnk Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(1610940419) //= 0x60050003. The runtime will prefer the VTID if present
  @VTID(86)
  void setInnerObjectFactory(
    com4j.Com4jObject pUnk);


  /**
   */

  @DISPID(1610940420) //= 0x60050004. The runtime will prefer the VTID if present
  @VTID(87)
  void ensureControl();


  /**
   * <p>
   * Setter method for the COM property "InitWith"
   * </p>
   * @param rhs Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(1610940421) //= 0x60050005. The runtime will prefer the VTID if present
  @VTID(88)
  void setInitWith(
    com4j.Com4jObject rhs);


  /**
   * <p>
   * Getter method for the COM property "InstanceIdPtr"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(1610940422) //= 0x60050006. The runtime will prefer the VTID if present
  @VTID(89)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getInstanceIdPtr();


  // Properties:
}
