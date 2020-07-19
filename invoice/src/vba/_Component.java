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

package vba  ;

import com4j.*;

@IID("{0002E163-0000-0000-C000-000000000046}")
public interface _Component extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type vba.Application
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  vba.Application getApplication();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type vba._Components
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  vba._Components getParent();


  /**
   * <p>
   * Getter method for the COM property "IsDirty"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(9)
  boolean getIsDirty();


  /**
   * <p>
   * Setter method for the COM property "IsDirty"
   * </p>
   * @param lpfReturn Mandatory boolean parameter.
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(10)
  void setIsDirty(
    boolean lpfReturn);


  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(48) //= 0x30. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String getName();


  /**
   * <p>
   * Setter method for the COM property "Name"
   * </p>
   * @param pbstrReturn Mandatory java.lang.String parameter.
   */

  @DISPID(48) //= 0x30. The runtime will prefer the VTID if present
  @VTID(12)
  void setName(
    java.lang.String pbstrReturn);


  // Properties:
}
