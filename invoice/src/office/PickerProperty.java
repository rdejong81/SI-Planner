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

@IID("{000C03E2-0000-0000-C000-000000000046}")
public interface PickerProperty extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Id"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String getId();


  /**
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getValue();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type office.MsoPickerField
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(11)
  office.MsoPickerField getType();


  // Properties:
}
