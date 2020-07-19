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

@IID("{0002E16A-0000-0000-C000-000000000046}")
public interface _Windows_old extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "VBE"
   * </p>
   * @return  Returns a value of type vba.VBE
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  vba.VBE getVBE();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type vba.Application
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  vba.Application getParent();


  /**
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type vba.Window
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  vba.Window item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(201) //= 0xc9. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(11)
  java.util.Iterator<Com4jObject> iterator();

  // Properties:
}
