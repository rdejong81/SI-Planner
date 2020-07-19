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

@IID("{0002445E-0001-0000-C000-000000000046}")
public interface ISmartTagAction extends Com4jObject {
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
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(10)
  java.lang.String getName();


  /**
   */

  @VTID(11)
  void execute();


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(12)
  @DefaultMethod
  java.lang.String get_Default();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type excel.XlSmartTagControlType
   */

  @VTID(13)
  excel.XlSmartTagControlType getType();


  /**
   * <p>
   * Getter method for the COM property "PresentInPane"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(14)
  boolean getPresentInPane();


  /**
   * <p>
   * Getter method for the COM property "ExpandHelp"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(15)
  boolean getExpandHelp();


  /**
   * <p>
   * Setter method for the COM property "ExpandHelp"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(16)
  void setExpandHelp(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "CheckboxState"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(17)
  boolean getCheckboxState();


  /**
   * <p>
   * Setter method for the COM property "CheckboxState"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(18)
  void setCheckboxState(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "TextboxText"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(19)
  java.lang.String getTextboxText();


  /**
   * <p>
   * Setter method for the COM property "TextboxText"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @VTID(20)
  void setTextboxText(
    java.lang.String rhs);


  /**
   * <p>
   * Getter method for the COM property "ListSelection"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(21)
  int getListSelection();


  /**
   * <p>
   * Setter method for the COM property "ListSelection"
   * </p>
   * @param rhs Mandatory int parameter.
   */

  @VTID(22)
  void setListSelection(
    int rhs);


  /**
   * <p>
   * Getter method for the COM property "RadioGroupSelection"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(23)
  int getRadioGroupSelection();


  /**
   * <p>
   * Setter method for the COM property "RadioGroupSelection"
   * </p>
   * @param rhs Mandatory int parameter.
   */

  @VTID(24)
  void setRadioGroupSelection(
    int rhs);


  /**
   * <p>
   * Getter method for the COM property "ActiveXControl"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(25)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getActiveXControl();


  // Properties:
}
