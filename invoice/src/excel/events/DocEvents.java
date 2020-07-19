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

package excel.events;

import com4j.*;

@IID("{00024411-0000-0000-C000-000000000046}")
public abstract class DocEvents {
  // Methods:
  /**
   * @param target Mandatory excel.Range parameter.
   */

  @DISPID(1543)
  public void selectionChange(
    excel.Range target) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param target Mandatory excel.Range parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @DISPID(1537)
  public void beforeDoubleClick(
    excel.Range target,
    Holder<Boolean> cancel) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param target Mandatory excel.Range parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @DISPID(1534)
  public void beforeRightClick(
    excel.Range target,
    Holder<Boolean> cancel) {
        throw new UnsupportedOperationException();
  }


  /**
   */

  @DISPID(304)
  public void activate() {
        throw new UnsupportedOperationException();
  }


  /**
   */

  @DISPID(1530)
  public void deactivate() {
        throw new UnsupportedOperationException();
  }


  /**
   */

  @DISPID(279)
  public void calculate() {
        throw new UnsupportedOperationException();
  }


  /**
   * @param target Mandatory excel.Range parameter.
   */

  @DISPID(1545)
  public void change(
    excel.Range target) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param target Mandatory excel.Hyperlink parameter.
   */

  @DISPID(1470)
  public void followHyperlink(
    excel.Hyperlink target) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param target Mandatory excel.PivotTable parameter.
   */

  @DISPID(2156)
  public void pivotTableUpdate(
    excel.PivotTable target) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param targetRange Mandatory excel.Range parameter.
   */

  @DISPID(2886)
  public void pivotTableAfterValueChange(
    excel.PivotTable targetPivotTable,
    excel.Range targetRange) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param valueChangeStart Mandatory int parameter.
   * @param valueChangeEnd Mandatory int parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @DISPID(2889)
  public void pivotTableBeforeAllocateChanges(
    excel.PivotTable targetPivotTable,
    int valueChangeStart,
    int valueChangeEnd,
    Holder<Boolean> cancel) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param valueChangeStart Mandatory int parameter.
   * @param valueChangeEnd Mandatory int parameter.
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @DISPID(2892)
  public void pivotTableBeforeCommitChanges(
    excel.PivotTable targetPivotTable,
    int valueChangeStart,
    int valueChangeEnd,
    Holder<Boolean> cancel) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param targetPivotTable Mandatory excel.PivotTable parameter.
   * @param valueChangeStart Mandatory int parameter.
   * @param valueChangeEnd Mandatory int parameter.
   */

  @DISPID(2893)
  public void pivotTableBeforeDiscardChanges(
    excel.PivotTable targetPivotTable,
    int valueChangeStart,
    int valueChangeEnd) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param target Mandatory excel.PivotTable parameter.
   */

  @DISPID(2894)
  public void pivotTableChangeSync(
    excel.PivotTable target) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
