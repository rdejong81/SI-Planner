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

@IID("{000244B2-0001-0000-C000-000000000046}")
public interface IChartFormat extends Com4jObject {
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
   * Getter method for the COM property "Fill"
   * </p>
   * @return  Returns a value of type excel.FillFormat
   */

  @VTID(10)
  excel.FillFormat getFill();


  /**
   * <p>
   * Getter method for the COM property "Glow"
   * </p>
   * @return  Returns a value of type office.GlowFormat
   */

  @VTID(11)
  office.GlowFormat getGlow();


  /**
   * <p>
   * Getter method for the COM property "Line"
   * </p>
   * @return  Returns a value of type excel.LineFormat
   */

  @VTID(12)
  excel.LineFormat getLine();


  /**
   * <p>
   * Getter method for the COM property "PictureFormat"
   * </p>
   * @return  Returns a value of type excel.PictureFormat
   */

  @VTID(13)
  excel.PictureFormat getPictureFormat();


  /**
   * <p>
   * Getter method for the COM property "Shadow"
   * </p>
   * @return  Returns a value of type excel.ShadowFormat
   */

  @VTID(14)
  excel.ShadowFormat getShadow();


  /**
   * <p>
   * Getter method for the COM property "SoftEdge"
   * </p>
   * @return  Returns a value of type office.SoftEdgeFormat
   */

  @VTID(15)
  office.SoftEdgeFormat getSoftEdge();


  /**
   * <p>
   * Getter method for the COM property "TextFrame2"
   * </p>
   * @return  Returns a value of type excel.TextFrame2
   */

  @VTID(16)
  excel.TextFrame2 getTextFrame2();


  /**
   * <p>
   * Getter method for the COM property "ThreeD"
   * </p>
   * @return  Returns a value of type excel.ThreeDFormat
   */

  @VTID(17)
  excel.ThreeDFormat getThreeD();


  // Properties:
}
