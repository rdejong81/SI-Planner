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

@IID("{000C03A1-0000-0000-C000-000000000046}")
public interface ThemeColor extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "RGB"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  int getRGB();


  /**
   * <p>
   * Setter method for the COM property "RGB"
   * </p>
   * @param rgb Mandatory int parameter.
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(10)
  @DefaultMethod
  void setRGB(
    int rgb);


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(11)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "ThemeColorSchemeIndex"
   * </p>
   * @return  Returns a value of type office.MsoThemeColorSchemeIndex
   */

  @DISPID(1610809347) //= 0x60030003. The runtime will prefer the VTID if present
  @VTID(12)
  office.MsoThemeColorSchemeIndex getThemeColorSchemeIndex();


  // Properties:
}
