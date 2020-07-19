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

package excel  ;

import com4j.*;

@IID("{0002444B-0000-0000-C000-000000000046}")
public interface TreeviewControl extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type excel._Application
   */

  @DISPID(148) //= 0x94. The runtime will prefer the VTID if present
  @VTID(7)
  excel._Application getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   * @return  Returns a value of type excel.XlCreator
   */

  @DISPID(149) //= 0x95. The runtime will prefer the VTID if present
  @VTID(8)
  excel.XlCreator getCreator();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @DISPID(150) //= 0x96. The runtime will prefer the VTID if present
  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Hidden"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(268) //= 0x10c. The runtime will prefer the VTID if present
  @VTID(10)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getHidden();


  /**
   * <p>
   * Setter method for the COM property "Hidden"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @DISPID(268) //= 0x10c. The runtime will prefer the VTID if present
  @VTID(11)
  void setHidden(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "Drilled"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(1917) //= 0x77d. The runtime will prefer the VTID if present
  @VTID(12)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getDrilled();


  /**
   * <p>
   * Setter method for the COM property "Drilled"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @DISPID(1917) //= 0x77d. The runtime will prefer the VTID if present
  @VTID(13)
  void setDrilled(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  // Properties:
}
