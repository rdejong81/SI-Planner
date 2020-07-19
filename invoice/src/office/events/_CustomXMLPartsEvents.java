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

@IID("{000CDB0B-0000-0000-C000-000000000046}")
public abstract class _CustomXMLPartsEvents {
  // Methods:
  /**
   * @param newPart Mandatory office._CustomXMLPart parameter.
   */

  @DISPID(1)
  public void partAfterAdd(
    office._CustomXMLPart newPart) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param oldPart Mandatory office._CustomXMLPart parameter.
   */

  @DISPID(2)
  public void partBeforeDelete(
    office._CustomXMLPart oldPart) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param part Mandatory office._CustomXMLPart parameter.
   */

  @DISPID(3)
  public void partAfterLoad(
    office._CustomXMLPart part) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
