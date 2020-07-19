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

package office.events;

import com4j.*;

@IID("{000CDB07-0000-0000-C000-000000000046}")
public abstract class _CustomXMLPartEvents {
  // Methods:
  /**
   * @param newNode Mandatory office.CustomXMLNode parameter.
   * @param inUndoRedo Mandatory boolean parameter.
   */

  @DISPID(1)
  public void nodeAfterInsert(
    office.CustomXMLNode newNode,
    boolean inUndoRedo) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param oldNode Mandatory office.CustomXMLNode parameter.
   * @param oldParentNode Mandatory office.CustomXMLNode parameter.
   * @param oldNextSibling Mandatory office.CustomXMLNode parameter.
   * @param inUndoRedo Mandatory boolean parameter.
   */

  @DISPID(2)
  public void nodeAfterDelete(
    office.CustomXMLNode oldNode,
    office.CustomXMLNode oldParentNode,
    office.CustomXMLNode oldNextSibling,
    boolean inUndoRedo) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param oldNode Mandatory office.CustomXMLNode parameter.
   * @param newNode Mandatory office.CustomXMLNode parameter.
   * @param inUndoRedo Mandatory boolean parameter.
   */

  @DISPID(3)
  public void nodeAfterReplace(
    office.CustomXMLNode oldNode,
    office.CustomXMLNode newNode,
    boolean inUndoRedo) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
