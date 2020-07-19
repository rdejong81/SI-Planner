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

@IID("{0002447E-0001-0000-C000-000000000046}")
public interface IXPath extends Com4jObject {
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
   * Getter method for the COM property "_Default"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(10)
  @DefaultMethod
  java.lang.String get_Default();


  /**
   * <p>
   * Getter method for the COM property "Value"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(11)
  java.lang.String getValue();


  /**
   * <p>
   * Getter method for the COM property "Map"
   * </p>
   * @return  Returns a value of type excel.XmlMap
   */

  @VTID(12)
  excel.XmlMap getMap();


  /**
   * @param map Mandatory excel.XmlMap parameter.
   * @param xPath Mandatory java.lang.String parameter.
   * @param selectionNamespace Optional parameter. Default value is com4j.Variant.getMissing()
   * @param repeating Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @VTID(13)
  void setValue(
    excel.XmlMap map,
    java.lang.String xPath,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object selectionNamespace,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object repeating);


  /**
   */

  @VTID(14)
  void clear();


  /**
   * <p>
   * Getter method for the COM property "Repeating"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(15)
  boolean getRepeating();


  // Properties:
}
