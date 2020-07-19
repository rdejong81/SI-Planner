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

@IID("{000C03A2-0000-0000-C000-000000000046}")
public interface ThemeColorScheme extends office._IMsoDispObj,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   * @param index Mandatory office.MsoThemeColorSchemeIndex parameter.
   * @return  Returns a value of type office.ThemeColor
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(11)
  @DefaultMethod
  office.ThemeColor colors(
    office.MsoThemeColorSchemeIndex index);


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(12)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * @param fileName Mandatory java.lang.String parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(13)
  void load(
    java.lang.String fileName);


  /**
   * @param fileName Mandatory java.lang.String parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(14)
  void save(
    java.lang.String fileName);


  /**
   * @param name Mandatory java.lang.String parameter.
   * @return  Returns a value of type int
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(15)
  int getCustomColor(
    java.lang.String name);


  // Properties:
}
