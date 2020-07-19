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

@IID("{0002446C-0001-0000-C000-000000000046}")
public interface IUserAccessList extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int getCount();


  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type excel.UserAccess
   */

  @VTID(8)
  excel.UserAccess getItem(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * @param name Mandatory java.lang.String parameter.
   * @param allowEdit Mandatory boolean parameter.
   * @return  Returns a value of type excel.UserAccess
   */

  @VTID(9)
  excel.UserAccess add(
    java.lang.String name,
    boolean allowEdit);


  /**
   */

  @VTID(10)
  void deleteAll();


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @param index Mandatory java.lang.Object parameter.
   * @return  Returns a value of type excel.UserAccess
   */

  @VTID(11)
  @DefaultMethod
  excel.UserAccess get_Default(
    @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @VTID(12)
  java.util.Iterator<Com4jObject> iterator();

  // Properties:
}
