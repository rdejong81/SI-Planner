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

package office  ;

import com4j.*;

@IID("{000C1722-0000-0000-C000-000000000046}")
public interface Trendlines extends Com4jObject,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Parent"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(7)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getParent();


  /**
   * @param type Optional parameter. Default value is -4132
   * @param order Optional parameter. Default value is com4j.Variant.getMissing()
   * @param period Optional parameter. Default value is com4j.Variant.getMissing()
   * @param forward Optional parameter. Default value is com4j.Variant.getMissing()
   * @param backward Optional parameter. Default value is com4j.Variant.getMissing()
   * @param intercept Optional parameter. Default value is com4j.Variant.getMissing()
   * @param displayEquation Optional parameter. Default value is com4j.Variant.getMissing()
   * @param displayRSquared Optional parameter. Default value is com4j.Variant.getMissing()
   * @param name Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type office.IMsoTrendline
   */

  @VTID(8)
  office.IMsoTrendline add(
    @Optional @DefaultValue("-4132") office.XlTrendlineType type,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object order,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object period,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object forward,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object backward,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object intercept,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object displayEquation,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object displayRSquared,
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object name);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(9)
  int getCount();


  /**
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type office.IMsoTrendline
   */

  @VTID(10)
  office.IMsoTrendline item(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  /**
   */

  @VTID(11)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * <p>
   * Getter method for the COM property "Application"
   * </p>
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(12)
  @ReturnValue(type=NativeType.Dispatch)
  com4j.Com4jObject getApplication();


  /**
   * <p>
   * Getter method for the COM property "Creator"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(13)
  int getCreator();


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @param index Optional parameter. Default value is com4j.Variant.getMissing()
   * @return  Returns a value of type office.IMsoTrendline
   */

  @VTID(14)
  @DefaultMethod
  office.IMsoTrendline get_Default(
    @Optional @MarshalAs(NativeType.VARIANT) java.lang.Object index);


  // Properties:
}
