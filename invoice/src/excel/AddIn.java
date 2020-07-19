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

@IID("{00020400-0000-0000-C000-000000000046}")
public interface AddIn extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   */

  @DISPID(148)
  @PropGet
  excel._Application getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   */

  @DISPID(149)
  @PropGet
  excel.XlCreator getCreator();


  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   */

  @DISPID(150)
  @PropGet
  com4j.Com4jObject getParent();


  /**
   * <p>
   * Getter method for the COM property "Author"
   * </p>
   */

  @DISPID(574)
  @PropGet
  java.lang.String getAuthor();


  /**
   * <p>
   * Getter method for the COM property "Comments"
   * </p>
   */

  @DISPID(575)
  @PropGet
  java.lang.String getComments();


  /**
   * <p>
   * Getter method for the COM property "FullName"
   * </p>
   */

  @DISPID(289)
  @PropGet
  java.lang.String getFullName();


  /**
   * <p>
   * Getter method for the COM property "Installed"
   * </p>
   */

  @DISPID(550)
  @PropGet
  boolean getInstalled();


  /**
   * <p>
   * Setter method for the COM property "Installed"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(550)
  @PropPut
  void setInstalled(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "Keywords"
   * </p>
   */

  @DISPID(577)
  @PropGet
  java.lang.String getKeywords();


  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   */

  @DISPID(110)
  @PropGet
  java.lang.String getName();


  /**
   * <p>
   * Getter method for the COM property "Path"
   * </p>
   */

  @DISPID(291)
  @PropGet
  java.lang.String getPath();


  /**
   * <p>
   * Getter method for the COM property "Subject"
   * </p>
   */

  @DISPID(953)
  @PropGet
  java.lang.String getSubject();


  /**
   * <p>
   * Getter method for the COM property "Title"
   * </p>
   */

  @DISPID(199)
  @PropGet
  java.lang.String getTitle();


  /**
   * <p>
   * Getter method for the COM property "progID"
   * </p>
   */

  @DISPID(1523)
  @PropGet
  java.lang.String getProgID();


  /**
   * <p>
   * Getter method for the COM property "CLSID"
   * </p>
   */

  @DISPID(2043)
  @PropGet
  java.lang.String getCLSID();


  /**
   * <p>
   * Getter method for the COM property "IsOpen"
   * </p>
   */

  @DISPID(2865)
  @PropGet
  boolean getIsOpen();


  // Properties:
}
