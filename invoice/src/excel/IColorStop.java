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

@IID("{000244AD-0001-0000-C000-000000000046}")
public interface IColorStop extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type excel._Application
   */

  @VTID(7)
  excel._Application getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   * @return  Returns a value of type excel.XlCreator
   */

  @VTID(8)
  excel.XlCreator getCreator();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(9)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   */

  @VTID(10)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "Color"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(11)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getColor();


  /**
   * <p>
   * Setter method for the COM property "Color"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(12)
  void setColor(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "ThemeColor"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(13)
  int getThemeColor();


  /**
   * <p>
   * Setter method for the COM property "ThemeColor"
   * </p>
   * @param rhs Mandatory int parameter.
   */

  @VTID(14)
  void setThemeColor(
    int rhs);


  /**
   * <p>
   * Getter method for the COM property "TintAndShade"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(15)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getTintAndShade();


  /**
   * <p>
   * Setter method for the COM property "TintAndShade"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(16)
  void setTintAndShade(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "Position"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(17)
  double getPosition();


  /**
   * <p>
   * Setter method for the COM property "Position"
   * </p>
   * @param rhs Mandatory double parameter.
   */

  @VTID(18)
  void setPosition(
    double rhs);


  // Properties:
}
