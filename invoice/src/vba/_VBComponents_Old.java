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

@IID("{0002E162-0000-0000-C000-000000000046}")
public interface _VBComponents_Old extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type vba._VBComponent
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(7)
  @DefaultMethod
  vba._VBComponent item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type vba._VBProject
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  vba._VBProject getParent();


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(9)
  int getCount();


  /**
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(10)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * @param vbComponent Mandatory vba._VBComponent parameter.
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(11)
  void remove(
    vba._VBComponent vbComponent);


  /**
   * @param componentType Mandatory vba.vbext_ComponentType parameter.
   * @return  Returns a value of type vba._VBComponent
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(12)
  vba._VBComponent add(
    vba.vbext_ComponentType componentType);


  /**
   * @param fileName Mandatory java.lang.String parameter.
   * @return  Returns a value of type vba._VBComponent
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(13)
  vba._VBComponent _import(
    java.lang.String fileName);


  /**
   * <p>
   * Getter method for the COM property "VBE"
   * </p>
   * @return  Returns a value of type vba.VBE
   */

  @DISPID(20) //= 0x14. The runtime will prefer the VTID if present
  @VTID(14)
  vba.VBE getVBE();


  // Properties:
}
