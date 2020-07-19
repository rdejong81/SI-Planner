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

@IID("{919AA22C-B9AD-11D3-8D59-0050048384E3}")
public interface ILicValidator extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Products"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getProducts();


  /**
   * <p>
   * Getter method for the COM property "Selection"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  int getSelection();


  /**
   * <p>
   * Setter method for the COM property "Selection"
   * </p>
   * @param piSel Mandatory int parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(9)
  void setSelection(
    int piSel);


  // Properties:
}
