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

@IID("{000C03A0-0000-0000-C000-000000000046}")
public interface OfficeTheme extends office._IMsoDispObj {
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
   * Getter method for the COM property "ThemeColorScheme"
   * </p>
   * @return  Returns a value of type office.ThemeColorScheme
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  office.ThemeColorScheme getThemeColorScheme();


  @VTID(10)
  @ReturnValue(defaultPropertyThrough={office.ThemeColorScheme.class})
  office.ThemeColor getThemeColorScheme(
    office.MsoThemeColorSchemeIndex index);

  /**
   * <p>
   * Getter method for the COM property "ThemeFontScheme"
   * </p>
   * @return  Returns a value of type office.ThemeFontScheme
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(11)
  office.ThemeFontScheme getThemeFontScheme();


  /**
   * <p>
   * Getter method for the COM property "ThemeEffectScheme"
   * </p>
   * @return  Returns a value of type office.ThemeEffectScheme
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(12)
  office.ThemeEffectScheme getThemeEffectScheme();


  // Properties:
}
