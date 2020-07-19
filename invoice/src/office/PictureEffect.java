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

@IID("{000C03D1-0000-0000-C000-000000000046}")
public interface PictureEffect extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type office.MsoPictureEffectType
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  office.MsoPictureEffectType getType();


  /**
   * <p>
   * Setter method for the COM property "Position"
   * </p>
   * @param position Mandatory int parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  void setPosition(
    int position);


  /**
   * <p>
   * Getter method for the COM property "Position"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(11)
  int getPosition();


  /**
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(12)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "EffectParameters"
   * </p>
   * @return  Returns a value of type office.EffectParameters
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(13)
  office.EffectParameters getEffectParameters();


  @VTID(13)
  @ReturnValue(defaultPropertyThrough={office.EffectParameters.class})
  office.EffectParameter getEffectParameters(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);

  /**
   * <p>
   * Setter method for the COM property "Visible"
   * </p>
   * @param visible Mandatory office.MsoTriState parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(14)
  void setVisible(
    office.MsoTriState visible);


  /**
   * <p>
   * Getter method for the COM property "Visible"
   * </p>
   * @return  Returns a value of type office.MsoTriState
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(15)
  office.MsoTriState getVisible();


  // Properties:
}
