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

@IID("{00020870-0001-0000-C000-000000000046}")
public interface IInterior extends Com4jObject {
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
   * <p>
   * Getter method for the COM property "Color"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(10)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getColor();


  /**
   * <p>
   * Setter method for the COM property "Color"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(11)
  void setColor(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "ColorIndex"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(12)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getColorIndex();


  /**
   * <p>
   * Setter method for the COM property "ColorIndex"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(13)
  void setColorIndex(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "InvertIfNegative"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(14)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getInvertIfNegative();


  /**
   * <p>
   * Setter method for the COM property "InvertIfNegative"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(15)
  void setInvertIfNegative(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "Pattern"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(16)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getPattern();


  /**
   * <p>
   * Setter method for the COM property "Pattern"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(17)
  void setPattern(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "PatternColor"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(18)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getPatternColor();


  /**
   * <p>
   * Setter method for the COM property "PatternColor"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(19)
  void setPatternColor(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "PatternColorIndex"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(20)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getPatternColorIndex();


  /**
   * <p>
   * Setter method for the COM property "PatternColorIndex"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(21)
  void setPatternColorIndex(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "ThemeColor"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(22)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getThemeColor();


  /**
   * <p>
   * Setter method for the COM property "ThemeColor"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(23)
  void setThemeColor(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "TintAndShade"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(24)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getTintAndShade();


  /**
   * <p>
   * Setter method for the COM property "TintAndShade"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(25)
  void setTintAndShade(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "PatternThemeColor"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(26)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getPatternThemeColor();


  /**
   * <p>
   * Setter method for the COM property "PatternThemeColor"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(27)
  void setPatternThemeColor(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "PatternTintAndShade"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(28)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getPatternTintAndShade();


  /**
   * <p>
   * Setter method for the COM property "PatternTintAndShade"
   * </p>
   * @param rhs Mandatory java.lang.Object parameter.
   */

  @VTID(29)
  void setPatternTintAndShade(
    @MarshalAs(NativeType.VARIANT) java.lang.Object rhs);


  /**
   * <p>
   * Getter method for the COM property "Gradient"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(30)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getGradient();


  // Properties:
}
