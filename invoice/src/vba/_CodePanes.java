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

@IID("{0002E172-0000-0000-C000-000000000046}")
public interface _CodePanes extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type vba.VBE
   */

  @DISPID(1610743808) //= 0x60020000. The runtime will prefer the VTID if present
  @VTID(7)
  vba.VBE getParent();


  /**
   * <p>
   * Getter method for the COM property "VBE"
   * </p>
   * @return  Returns a value of type vba.VBE
   */

  @DISPID(1610743809) //= 0x60020001. The runtime will prefer the VTID if present
  @VTID(8)
  vba.VBE getVBE();


  /**
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type vba._CodePane
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  vba._CodePane item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1610743811) //= 0x60020003. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(11)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "Current"
   * </p>
   * @return  Returns a value of type vba._CodePane
   */

  @DISPID(1610743813) //= 0x60020005. The runtime will prefer the VTID if present
  @VTID(12)
  vba._CodePane getCurrent();


  /**
   * <p>
   * Setter method for the COM property "Current"
   * </p>
   * @param codePane Mandatory vba._CodePane parameter.
   */

  @DISPID(1610743813) //= 0x60020005. The runtime will prefer the VTID if present
  @VTID(13)
  void setCurrent(
    vba._CodePane codePane);


  // Properties:
}
