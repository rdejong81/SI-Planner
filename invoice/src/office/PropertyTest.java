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

@IID("{000C0333-0000-0000-C000-000000000046}")
public interface PropertyTest extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  java.lang.String getName();


  /**
   * <p>
   * Getter method for the COM property "Condition"
   * </p>
   * @return  Returns a value of type office.MsoCondition
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  office.MsoCondition getCondition();


  /**
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(11)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getValue();


  /**
   * <p>
   * Getter method for the COM property "SecondValue"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(12)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getSecondValue();


  /**
   * <p>
   * Getter method for the COM property "Connector"
   * </p>
   * @return  Returns a value of type office.MsoConnector
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(13)
  office.MsoConnector getConnector();


  // Properties:
}
