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

@IID("{000C0306-0000-0000-C000-000000000046}")
public interface CommandBarControls extends office._IMsoDispObj,Iterable<Com4jObject> {
  // Methods:
  /**
   * @param type Optional parameter. Default value is com4j.Variant.getMissing()
   * @param id Optional parameter. Default value is com4j.Variant.getMissing()
   * @param parameter Optional parameter. Default value is com4j.Variant.getMissing()
   * @param before Optional parameter. Default value is com4j.Variant.getMissing()
   * @param temporary Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type office.CommandBarControl
   */

  @DISPID(1610809344) //= 0x60030000. The runtime will prefer the VTID if present
  @VTID(9)
  office.CommandBarControl add(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object type,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object id,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object parameter,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object before,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object temporary);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1610809345) //= 0x60030001. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type office.CommandBarControl
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(11)
  @DefaultMethod
  office.CommandBarControl getItem(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(12)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type office.CommandBar
   */

  @DISPID(1610809348) //= 0x60030004. The runtime will prefer the VTID if present
  @VTID(13)
  office.CommandBar getParent();


  // Properties:
}
