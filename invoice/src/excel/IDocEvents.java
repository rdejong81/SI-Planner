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

@IID("{00024411-0001-0000-C000-000000000046}")
public interface IDocEvents extends Com4jObject {
  // Methods:
  /**
   * @param target Mandatory excel.Range parameter.
   */

  @VTID(7)
  void selectionChange(
    excel.Range target);


  /**
   * @param target Mandatory excel.Range parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @VTID(8)
  void beforeDoubleClick(
    excel.Range target,
    Holder<Boolean> cancel);


  /**
   * @param target Mandatory excel.Range parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @VTID(9)
  void beforeRightClick(
    excel.Range target,
    Holder<Boolean> cancel);


  /**
   */

  @VTID(10)
  void activate();


  /**
   */

  @VTID(11)
  void deactivate();


  /**
   */

  @VTID(12)
  void calculate();


  /**
   * @param target Mandatory excel.Range parameter.
   */

  @VTID(13)
  void change(
    excel.Range target);


  /**
   * @param target Mandatory excel.Hyperlink parameter.
   */

  @VTID(14)
  void followHyperlink(
    excel.Hyperlink target);


  /**
   * @param target Mandatory excel.PivotTable parameter.
   */

  @VTID(15)
  void pivotTableUpdate(
    excel.PivotTable target);


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param targetRange Mandatory excel.Range parameter.
   */

  @VTID(16)
  void pivotTableAfterValueChange(
    excel.PivotTable targetPivotTable,
    excel.Range targetRange);


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param valueChangeStart Mandatory int parameter.
   * @param valueChangeEnd Mandatory int parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @VTID(17)
  void pivotTableBeforeAllocateChanges(
    excel.PivotTable targetPivotTable,
    int valueChangeStart,
    int valueChangeEnd,
    Holder<Boolean> cancel);


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param valueChangeStart Mandatory int parameter.
   * @param valueChangeEnd Mandatory int parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @VTID(18)
  void pivotTableBeforeCommitChanges(
    excel.PivotTable targetPivotTable,
    int valueChangeStart,
    int valueChangeEnd,
    Holder<Boolean> cancel);


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param valueChangeStart Mandatory int parameter.
   * @param valueChangeEnd Mandatory int parameter.
   */

  @VTID(19)
  void pivotTableBeforeDiscardChanges(
    excel.PivotTable targetPivotTable,
    int valueChangeStart,
    int valueChangeEnd);


  /**
   * @param target Mandatory excel.PivotTable parameter.
   */

  @VTID(20)
  void pivotTableChangeSync(
    excel.PivotTable target);


  // Properties:
}
