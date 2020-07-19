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
public interface SmartTag extends Com4jObject {
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
   * Getter method for the COM property "DownloadURL"
   * </p>
   */

  @DISPID(2212)
  @PropGet
  java.lang.String getDownloadURL();


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
   * Getter method for the COM property "_Default"
   * </p>
   */

  @DISPID(0)
  @PropGet
  @DefaultMethod
  java.lang.String get_Default();


  /**
   * <p>
   * Getter method for the COM property "XML"
   * </p>
   */

  @DISPID(2213)
  @PropGet
  java.lang.String getXML();


  /**
   * <p>
   * Getter method for the COM property "Range"
   * </p>
   */

  @DISPID(197)
  @PropGet
  excel.Range getRange();


  /**
   */

  @DISPID(117)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "SmartTagActions"
   * </p>
   */

  @DISPID(2214)
  @PropGet
  excel.SmartTagActions getSmartTagActions();


  /**
   * <p>
   * Getter method for the COM property "Properties"
   * </p>
   */

  @DISPID(2135)
  @PropGet
  excel.CustomProperties getProperties();


  // Properties:
}
