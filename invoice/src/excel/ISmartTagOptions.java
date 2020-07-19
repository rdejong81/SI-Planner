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

@IID("{00024464-0001-0000-C000-000000000046}")
public interface ISmartTagOptions extends Com4jObject {
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
   * Getter method for the COM property "DisplaySmartTags"
   * </p>
   * @return  Returns a value of type excel.XlSmartTagDisplayMode
   */

  @VTID(10)
  excel.XlSmartTagDisplayMode getDisplaySmartTags();


  /**
   * <p>
   * Setter method for the COM property "DisplaySmartTags"
   * </p>
   * @param rhs Mandatory excel.XlSmartTagDisplayMode parameter.
   */

  @VTID(11)
  void setDisplaySmartTags(
    excel.XlSmartTagDisplayMode rhs);


  /**
   * <p>
   * Getter method for the COM property "EmbedSmartTags"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(12)
  boolean getEmbedSmartTags();


  /**
   * <p>
   * Setter method for the COM property "EmbedSmartTags"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(13)
  void setEmbedSmartTags(
    boolean rhs);


  // Properties:
}
