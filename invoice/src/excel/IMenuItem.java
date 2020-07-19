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

@IID("{00020868-0001-0000-C000-000000000046}")
public interface IMenuItem extends Com4jObject {
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
   * Getter method for the COM property "Caption"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(10)
  java.lang.String getCaption();


  /**
   * <p>
   * Setter method for the COM property "Caption"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @VTID(11)
  void setCaption(
    java.lang.String rhs);


  /**
   * <p>
   * Getter method for the COM property "Checked"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(12)
  boolean getChecked();


  /**
   * <p>
   * Setter method for the COM property "Checked"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(13)
  void setChecked(
    boolean rhs);


  /**
   */

  @VTID(14)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "Enabled"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(15)
  boolean getEnabled();


  /**
   * <p>
   * Setter method for the COM property "Enabled"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(16)
  void setEnabled(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "HelpContextID"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(17)
  int getHelpContextID();


  /**
   * <p>
   * Setter method for the COM property "HelpContextID"
   * </p>
   * @param rhs Mandatory int parameter.
   */

  @VTID(18)
  void setHelpContextID(
    int rhs);


  /**
   * <p>
   * Getter method for the COM property "HelpFile"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(19)
  java.lang.String getHelpFile();


  /**
   * <p>
   * Setter method for the COM property "HelpFile"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @VTID(20)
  void setHelpFile(
    java.lang.String rhs);


  /**
   * <p>
   * Getter method for the COM property "Index"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(21)
  int getIndex();


  /**
   * <p>
   * Getter method for the COM property "OnAction"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(22)
  java.lang.String getOnAction();


  /**
   * <p>
   * Setter method for the COM property "OnAction"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @VTID(23)
  void setOnAction(
    java.lang.String rhs);


  /**
   * <p>
   * Getter method for the COM property "StatusBar"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(24)
  java.lang.String getStatusBar();


  /**
   * <p>
   * Setter method for the COM property "StatusBar"
   * </p>
   * @param rhs Mandatory java.lang.String parameter.
   */

  @VTID(25)
  void setStatusBar(
    java.lang.String rhs);


  // Properties:
}
