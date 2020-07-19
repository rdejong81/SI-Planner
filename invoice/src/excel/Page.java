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
public interface Page extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "LeftHeader"
   * </p>
   */

  @DISPID(1018)
  @PropGet
  excel.HeaderFooter getLeftHeader();


  /**
   * <p>
   * Getter method for the COM property "CenterHeader"
   * </p>
   */

  @DISPID(1011)
  @PropGet
  excel.HeaderFooter getCenterHeader();


  /**
   * <p>
   * Getter method for the COM property "RightHeader"
   * </p>
   */

  @DISPID(1026)
  @PropGet
  excel.HeaderFooter getRightHeader();


  /**
   * <p>
   * Getter method for the COM property "LeftFooter"
   * </p>
   */

  @DISPID(1017)
  @PropGet
  excel.HeaderFooter getLeftFooter();


  /**
   * <p>
   * Getter method for the COM property "CenterFooter"
   * </p>
   */

  @DISPID(1010)
  @PropGet
  excel.HeaderFooter getCenterFooter();


  /**
   * <p>
   * Getter method for the COM property "RightFooter"
   * </p>
   */

  @DISPID(1025)
  @PropGet
  excel.HeaderFooter getRightFooter();


  // Properties:
}
