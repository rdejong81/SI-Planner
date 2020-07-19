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

@IID("{000C0375-0000-0000-C000-000000000046}")
public interface UserPermission extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "UserId"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  java.lang.String getUserId();


  /**
   * <p>
   * Getter method for the COM property "Permission"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  int getPermission();


  /**
   * <p>
   * Setter method for the COM property "Permission"
   * </p>
   * @param permission Mandatory int parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(11)
  void setPermission(
    int permission);


  /**
   * <p>
   * Getter method for the COM property "ExpirationDate"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(12)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getExpirationDate();


  /**
   * <p>
   * Setter method for the COM property "ExpirationDate"
   * </p>
   * @param expirationDate Mandatory java.lang.Object parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(13)
  void setExpirationDate(
    @MarshalAs(NativeType.VARIANT) java.lang.Object expirationDate);


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(14)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(15)
  void remove();


  // Properties:
}
