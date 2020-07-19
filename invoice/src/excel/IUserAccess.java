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

@IID("{0002446D-0001-0000-C000-000000000046}")
public interface IUserAccess extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(7)
  java.lang.String getName();


  /**
   * <p>
   * Getter method for the COM property "AllowEdit"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(8)
  boolean getAllowEdit();


  /**
   * <p>
   * Setter method for the COM property "AllowEdit"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(9)
  void setAllowEdit(
    boolean rhs);


  /**
   */

  @VTID(10)
  void delete();


  // Properties:
}
