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

@IID("{0002085F-0001-0000-C000-000000000046}")
public interface IToolbarButtons extends Com4jObject,Iterable<Com4jObject> {
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
   * @param button Optional parameter. Default value is com4j.Variant.getMissing()
   * @param before Optional parameter. Default value is com4j.Variant.getMissing()
   * @param onAction Optional parameter. Default value is com4j.Variant.getMissing()
   * @param pushed Optional parameter. Default value is com4j.Variant.getMissing()
   * @param enabled Optional parameter. Default value is com4j.Variant.getMissing()
   * @param statusBar Optional parameter. Default value is com4j.Variant.getMissing()
   * @param helpFile Optional parameter. Default value is com4j.Variant.getMissing()
   * @param helpContextID Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type excel.ToolbarButton
   */

  @VTID(10)
  excel.ToolbarButton add(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object button,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object before,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object onAction,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object pushed,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object enabled,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object statusBar,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object helpFile,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object helpContextID);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(11)
  int getCount();


  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory int parameter.
   * @return  Returns a value of type excel.ToolbarButton
   */

  @VTID(12)
  excel.ToolbarButton getItem(
    int index);


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @VTID(13)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @param index Mandatory int parameter.
   * @return  Returns a value of type excel.ToolbarButton
   */

  @VTID(14)
  @DefaultMethod
  excel.ToolbarButton get_Default(
    int index);


  // Properties:
}
