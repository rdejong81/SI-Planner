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
public interface CalculatedMember extends Com4jObject {
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
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   */

  @DISPID(110)
  @PropGet
  java.lang.String getName();


  /**
   * <p>
   * Getter method for the COM property "Formula"
   * </p>
   */

  @DISPID(261)
  @PropGet
  java.lang.String getFormula();


  /**
   * <p>
   * Getter method for the COM property "SourceName"
   * </p>
   */

  @DISPID(721)
  @PropGet
  java.lang.String getSourceName();


  /**
   * <p>
   * Getter method for the COM property "SolveOrder"
   * </p>
   */

  @DISPID(2187)
  @PropGet
  int getSolveOrder();


  /**
   * <p>
   * Getter method for the COM property "IsValid"
   * </p>
   */

  @DISPID(2188)
  @PropGet
  boolean getIsValid();


  /**
   * <p>
   * Getter method for the COM property "_Default"
   * </p>
   */

  @DISPID(0)
  @PropGet
  @DefaultMethod
  java.lang.String get_Default();


  /**
   */

  @DISPID(117)
  void delete();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   */

  @DISPID(108)
  @PropGet
  excel.XlCalculatedMemberType getType();


  /**
   * <p>
   * Getter method for the COM property "Dynamic"
   * </p>
   */

  @DISPID(2926)
  @PropGet
  boolean getDynamic();


  /**
   * <p>
   * Getter method for the COM property "DisplayFolder"
   * </p>
   */

  @DISPID(2927)
  @PropGet
  java.lang.String getDisplayFolder();


  /**
   * <p>
   * Getter method for the COM property "HierarchizeDistinct"
   * </p>
   */

  @DISPID(2925)
  @PropGet
  boolean getHierarchizeDistinct();


  /**
   * <p>
   * Setter method for the COM property "HierarchizeDistinct"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(2925)
  @PropPut
  void setHierarchizeDistinct(
    boolean rhs);


  /**
   * <p>
   * Getter method for the COM property "FlattenHierarchies"
   * </p>
   */

  @DISPID(2924)
  @PropGet
  boolean getFlattenHierarchies();


  /**
   * <p>
   * Setter method for the COM property "FlattenHierarchies"
   * </p>
   * @param rhs Mandatory boolean parameter.
   */

  @DISPID(2924)
  @PropPut
  void setFlattenHierarchies(
    boolean rhs);


  // Properties:
}
