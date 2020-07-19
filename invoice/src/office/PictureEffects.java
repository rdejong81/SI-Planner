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

@IID("{000C03D2-0000-0000-C000-000000000046}")
public interface PictureEffects extends office._IMsoDispObj,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory int parameter.
   * @return  Returns a value of type office.PictureEffect
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  office.PictureEffect getItem(
    int index);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(11)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * @param effectType Mandatory office.MsoPictureEffectType parameter.
   * @param position Optional parameter. Default value is -1
   * @return  Returns a value of type office.PictureEffect
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(12)
  office.PictureEffect insert(
    office.MsoPictureEffectType effectType,
    @Optional @DefaultValue("-1") int position);


  /**
   * @param index Optional parameter. Default value is -1
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(13)
  void delete(
    @Optional @DefaultValue("-1") int index);


  // Properties:
}
