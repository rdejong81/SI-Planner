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

@IID("{0002442E-0001-0000-C000-000000000046}")
public interface IDummy extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(7)
  void _ActiveSheetOrChart();


  /**
   */

  @VTID(8)
  void rgb();


  /**
   */

  @VTID(9)
  void chDir();


  /**
   */

  @VTID(10)
  void doScript();


  /**
   */

  @VTID(11)
  void directObject();


  /**
   */

  @VTID(12)
  void refreshDocument();


  /**
   * @param sigProv Mandatory java.lang.Object parameter.
   * @return  Returns a value of type office.Signature
   */

  @VTID(13)
  office.Signature addSignatureLine(
    @MarshalAs(NativeType.VARIANT) java.lang.Object sigProv);


  /**
   * @param sigProv Mandatory java.lang.Object parameter.
   * @return  Returns a value of type office.Signature
   */

  @VTID(14)
  office.Signature addNonVisibleSignature(
    @MarshalAs(NativeType.VARIANT) java.lang.Object sigProv);


  /**
   * <p>
   * Getter method for the COM property "ShowSignaturesPane"
   * </p>
   * @return  Returns a value of type boolean
   */

  @VTID(15)
  boolean getShowSignaturesPane();


  /**
   * <p>
   * Setter method for the COM property "ShowSignaturesPane"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @VTID(16)
  void setShowSignaturesPane(
    boolean rhs);


  /**
   */

  @VTID(17)
  void themeFontScheme();


  /**
   */

  @VTID(18)
  void themeColorScheme();


  /**
   */

  @VTID(19)
  void themeEffectScheme();


  /**
   */

  @VTID(20)
  void load();


  // Properties:
}
