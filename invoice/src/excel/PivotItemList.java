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
public interface PivotItemList extends Com4jObject,Iterable<Com4jObject> {
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
   * Getter method for the COM property "Count"
   * </p>
   */

  @DISPID(118)
  @PropGet
  int getCount();


  /**
   * @param index Mandatory java.lang.Object parameter.
   */

  @DISPID(170)
  excel.PivotItem item(
    java.lang.Object index);


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   * @param field Mandatory java.lang.Object parameter.
   */

  @DISPID(0)
  @PropGet
  @DefaultMethod
  excel.PivotItem get_Default(
    java.lang.Object field);


  /**
   */

  @DISPID(-4)
  java.util.Iterator<Com4jObject> iterator();

  // Properties:
}
