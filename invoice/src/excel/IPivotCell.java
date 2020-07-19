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

@IID("{00024458-0001-0000-C000-000000000046}")
public interface IPivotCell extends Com4jObject {
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
   * Getter method for the COM property "PivotCellType"
   * </p>
   * @return  Returns a value of type excel.XlPivotCellType
   */

  @VTID(10)
  excel.XlPivotCellType getPivotCellType();


  /**
   * <p>
   * Getter method for the COM property "PivotTable"
   * </p>
   * @return  Returns a value of type excel.PivotTable
   */

  @VTID(11)
  excel.PivotTable getPivotTable();


  /**
   * <p>
   * Getter method for the COM property "DataField"
   * </p>
   * @return  Returns a value of type excel.PivotField
   */

  @VTID(12)
  excel.PivotField getDataField();


  /**
   * <p>
   * Getter method for the COM property "PivotField"
   * </p>
   * @return  Returns a value of type excel.PivotField
   */

  @VTID(13)
  excel.PivotField getPivotField();


  /**
   * <p>
   * Getter method for the COM property "PivotItem"
   * </p>
   * @return  Returns a value of type excel.PivotItem
   */

  @VTID(14)
  excel.PivotItem getPivotItem();


  /**
   * <p>
   * Getter method for the COM property "RowItems"
   * </p>
   * @return  Returns a value of type excel.PivotItemList
   */

  @VTID(15)
  excel.PivotItemList getRowItems();


  /**
   * <p>
   * Getter method for the COM property "ColumnItems"
   * </p>
   * @return  Returns a value of type excel.PivotItemList
   */

  @VTID(16)
  excel.PivotItemList getColumnItems();


  /**
   * <p>
   * Getter method for the COM property "Range"
   * </p>
   * @return  Returns a value of type excel.Range
   */

  @VTID(17)
  excel.Range getRange();


  /**
   * <p>
   * Getter method for the COM property "Dummy18"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(18)
  java.lang.String getDummy18();


  /**
   * <p>
   * Getter method for the COM property "CustomSubtotalFunction"
   * </p>
   * @return  Returns a value of type excel.XlConsolidationFunction
   */

  @VTID(19)
  excel.XlConsolidationFunction getCustomSubtotalFunction();


  /**
   * <p>
   * Getter method for the COM property "PivotRowLine"
   * </p>
   * @return  Returns a value of type excel.PivotLine
   */

  @VTID(20)
  excel.PivotLine getPivotRowLine();


  /**
   * <p>
   * Getter method for the COM property "PivotColumnLine"
   * </p>
   * @return  Returns a value of type excel.PivotLine
   */

  @VTID(21)
  excel.PivotLine getPivotColumnLine();


  /**
   */

  @VTID(22)
  void allocateChange();


  /**
   */

  @VTID(23)
  void discardChange();


  /**
   * <p>
   * Getter method for the COM property "DataSourceValue"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(24)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getDataSourceValue();


  /**
   * <p>
   * Getter method for the COM property "CellChanged"
   * </p>
   * @return  Returns a value of type excel.XlCellChangedState
   */

  @VTID(25)
  excel.XlCellChangedState getCellChanged();


  /**
   * <p>
   * Getter method for the COM property "MDX"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(26)
  java.lang.String getMDX();


  // Properties:
}
