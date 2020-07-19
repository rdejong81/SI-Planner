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

package vba  ;

import com4j.*;

@IID("{0002E17E-0000-0000-C000-000000000046}")
public interface Reference extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Collection"
   * </p>
   * @return  Returns a value of type vba._References
   */

  @DISPID(1610743808) //= 0x60020000. The runtime will prefer the VTID if present
  @VTID(7)
  vba._References getCollection();


  /**
   * <p>
   * Getter method for the COM property "VBE"
   * </p>
   * @return  Returns a value of type vba.VBE
   */

  @DISPID(1610743809) //= 0x60020001. The runtime will prefer the VTID if present
  @VTID(8)
  vba.VBE getVBE();


  /**
   * <p>
   * Getter method for the COM property "Name"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610743810) //= 0x60020002. The runtime will prefer the VTID if present
  @VTID(9)
  java.lang.String getName();


  /**
   * <p>
   * Getter method for the COM property "Guid"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610743811) //= 0x60020003. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String getGuid();


  /**
   * <p>
   * Getter method for the COM property "Major"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1610743812) //= 0x60020004. The runtime will prefer the VTID if present
  @VTID(11)
  int getMajor();


  /**
   * <p>
   * Getter method for the COM property "Minor"
   * </p>
   * @return  Returns a value of type int
   */

  @DISPID(1610743813) //= 0x60020005. The runtime will prefer the VTID if present
  @VTID(12)
  int getMinor();


  /**
   * <p>
   * Getter method for the COM property "FullPath"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610743814) //= 0x60020006. The runtime will prefer the VTID if present
  @VTID(13)
  java.lang.String getFullPath();


  /**
   * <p>
   * Getter method for the COM property "BuiltIn"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(1610743815) //= 0x60020007. The runtime will prefer the VTID if present
  @VTID(14)
  boolean getBuiltIn();


  /**
   * <p>
   * Getter method for the COM property "IsBroken"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(1610743816) //= 0x60020008. The runtime will prefer the VTID if present
  @VTID(15)
  boolean getIsBroken();


  /**
   * <p>
   * Getter method for the COM property "Type"
   * </p>
   * @return  Returns a value of type vba.vbext_RefKind
   */

  @DISPID(1610743817) //= 0x60020009. The runtime will prefer the VTID if present
  @VTID(16)
  vba.vbext_RefKind getType();


  /**
   * <p>
   * Getter method for the COM property "Description"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(1610743818) //= 0x6002000a. The runtime will prefer the VTID if present
  @VTID(17)
  java.lang.String getDescription();


  // Properties:
}
