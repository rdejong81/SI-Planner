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

@IID("{000C038A-0000-0000-C000-000000000046}")
public interface MsoDebugOptions_UTs extends office._IMsoDispObj,Iterable<Com4jObject> {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Item"
   * </p>
   * @param index Mandatory int parameter.
   * @return  Returns a value of type office.MsoDebugOptions_UT
   */

  @DISPID(0) //= 0x0. The runtime will prefer the VTID if present
  @VTID(9)
  @DefaultMethod
  office.MsoDebugOptions_UT getItem(
    int index);


  /**
   * <p>
   * Getter method for the COM property "Count"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(10)
  int getCount();


  /**
   * <p>
   * Getter method for the COM property "_NewEnum"
   * </p>
   */

  @DISPID(-4) //= 0xfffffffc. The runtime will prefer the VTID if present
  @VTID(11)
  java.util.Iterator<Com4jObject> iterator();

  /**
   * @param bstrCollectionName Mandatory java.lang.String parameter.
   * @return  Returns a value of type office.MsoDebugOptions_UTs
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(12)
  office.MsoDebugOptions_UTs getUnitTestsInCollection(
    java.lang.String bstrCollectionName);


  /**
   * @param bstrCollectionName Mandatory java.lang.String parameter.
   * @param bstrUnitTestName Mandatory java.lang.String parameter.
   * @return  Returns a value of type office.MsoDebugOptions_UT
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(13)
  office.MsoDebugOptions_UT getUnitTest(
    java.lang.String bstrCollectionName,
    java.lang.String bstrUnitTestName);


  /**
   * @param bstrCollectionName Mandatory java.lang.String parameter.
   * @param bstrUnitTestNameFilter Mandatory java.lang.String parameter.
   * @return  Returns a value of type office.MsoDebugOptions_UTs
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(14)
  office.MsoDebugOptions_UTs getMatchingUnitTestsInCollection(
    java.lang.String bstrCollectionName,
    java.lang.String bstrUnitTestNameFilter);


  // Properties:
}
