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

@IID("{000244A2-0001-0000-C000-000000000046}")
public interface IPage extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "LeftHeader"
   * </p>
   * @return  Returns a value of type excel.HeaderFooter
   */

  @VTID(7)
  excel.HeaderFooter getLeftHeader();


  /**
   * <p>
   * Getter method for the COM property "CenterHeader"
   * </p>
   * @return  Returns a value of type excel.HeaderFooter
   */

  @VTID(8)
  excel.HeaderFooter getCenterHeader();


  /**
   * <p>
   * Getter method for the COM property "RightHeader"
   * </p>
   * @return  Returns a value of type excel.HeaderFooter
   */

  @VTID(9)
  excel.HeaderFooter getRightHeader();


  /**
   * <p>
   * Getter method for the COM property "LeftFooter"
   * </p>
   * @return  Returns a value of type excel.HeaderFooter
   */

  @VTID(10)
  excel.HeaderFooter getLeftFooter();


  /**
   * <p>
   * Getter method for the COM property "CenterFooter"
   * </p>
   * @return  Returns a value of type excel.HeaderFooter
   */

  @VTID(11)
  excel.HeaderFooter getCenterFooter();


  /**
   * <p>
   * Getter method for the COM property "RightFooter"
   * </p>
   * @return  Returns a value of type excel.HeaderFooter
   */

  @VTID(12)
  excel.HeaderFooter getRightFooter();


  // Properties:
}
