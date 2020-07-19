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

@IID("{000C0365-0000-0000-C000-000000000046}")
public interface FileDialogFilters extends office._IMsoDispObj,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(1610809344) //= 0x60030000. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(10)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1610809346) //= 0x60030002. The runtime will prefer the VTID if present
  @VTID(11)
  int getCount();


  /**
   * @param index Mandatory int parameter.
   * @return  Returns a value of type office.FileDialogFilter
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(12)
  @DefaultMethod
  office.FileDialogFilter item(
    int index);


  /**
   * @param filter Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(1610809348) //= 0x60030004. The runtime will prefer the VTID if present
  @VTID(13)
  void delete(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object filter);


  /**
   */

  @DISPID(1610809349) //= 0x60030005. The runtime will prefer the VTID if present
  @VTID(14)
  void clear();


  /**
   * @param description Mandatory java.lang.String parameter.
   * @param extensions Mandatory java.lang.String parameter.
   * @param position Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type office.FileDialogFilter
   */

  @DISPID(1610809350) //= 0x60030006. The runtime will prefer the VTID if present
  @VTID(15)
  office.FileDialogFilter add(
    java.lang.String description,
    java.lang.String extensions,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object position);


  // Properties:
}
