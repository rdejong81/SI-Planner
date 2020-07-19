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
public interface FreeformBuilder extends Com4jObject {
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
   * @param segmentType Mandatory office.MsoSegmentType parameter.
   * @param editingType Mandatory office.MsoEditingType parameter.
   * @param x1 Mandatory float parameter.
   * @param y1 Mandatory float parameter.
   * @param x2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param y2 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param x3 Optional parameter. Default value is com4j.Variant.getMissing()
   * @param y3 Optional parameter. Default value is com4j.Variant.getMissing()
   */

  @DISPID(1762)
  void addNodes(
    office.MsoSegmentType segmentType,
    office.MsoEditingType editingType,
    float x1,
    float y1,
    @Optional java.lang.Object x2,
    @Optional java.lang.Object y2,
    @Optional java.lang.Object x3,
    @Optional java.lang.Object y3);


  /**
   */

  @DISPID(1766)
  excel.Shape convertToShape();


  // Properties:
}
