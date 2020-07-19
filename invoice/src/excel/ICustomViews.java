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

@IID("{00024422-0001-0000-C000-000000000046}")
public interface ICustomViews extends Com4jObject,Iterable<Com4jObject> {
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
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(10)
  int getCount();


  /**
   * @param viewName Mandatory java.lang.Object parameter.
   * @return  Returns a value of type excel.CustomView
   */

  @VTID(11)
  excel.CustomView item(
    @MarshalAs(NativeType.VARIANT) java.lang.Object viewName);


  /**
   * @param viewName Mandatory java.lang.String parameter.
   * @param printSettings Optional parameter. Default value is com4j.Variant.getMissing()
   * @param rowColSettings Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type excel.CustomView
   */

  @VTID(12)
  excel.CustomView add(
    java.lang.String viewName,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object printSettings,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object rowColSettings);


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @param viewName Mandatory java.lang.Object parameter.
   * @return  Returns a value of type excel.CustomView
   */

  @VTID(13)
  @DefaultMethod
  excel.CustomView get_Default(
    @MarshalAs(NativeType.VARIANT) java.lang.Object viewName);


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @VTID(14)
  java.util.Iterator<Com4jObject> iterator();

  // Properties:
}
