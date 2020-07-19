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

@IID("{000CDB06-0000-0000-C000-000000000046}")
public interface ICustomXMLPartEvents extends Com4jObject {
  // Methods:
  /**
   * @param newNode Mandatory office.CustomXMLNode parameter.
   * @param inUndoRedo Mandatory boolean parameter.
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(7)
  void nodeAfterInsert(
    office.CustomXMLNode newNode,
    boolean inUndoRedo);


  /**
   * @param oldNode Mandatory office.CustomXMLNode parameter.
   * @param oldParentNode Mandatory office.CustomXMLNode parameter.
   * @param oldNextSibling Mandatory office.CustomXMLNode parameter.
   * @param inUndoRedo Mandatory boolean parameter.
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(8)
  void nodeAfterDelete(
    office.CustomXMLNode oldNode,
    office.CustomXMLNode oldParentNode,
    office.CustomXMLNode oldNextSibling,
    boolean inUndoRedo);


  /**
   * @param oldNode Mandatory office.CustomXMLNode parameter.
   * @param newNode Mandatory office.CustomXMLNode parameter.
   * @param inUndoRedo Mandatory boolean parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(9)
  void nodeAfterReplace(
    office.CustomXMLNode oldNode,
    office.CustomXMLNode newNode,
    boolean inUndoRedo);


  // Properties:
}
