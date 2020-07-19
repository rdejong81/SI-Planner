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

@IID("{000C033C-0000-0000-C000-000000000046}")
public abstract class _CustomTaskPaneEvents {
  // Methods:
  /**
   * @param customTaskPaneInst Mandatory office._CustomTaskPane parameter.
   */

  @DISPID(1)
  public void visibleStateChange(
    office._CustomTaskPane customTaskPaneInst) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param customTaskPaneInst Mandatory office._CustomTaskPane parameter.
   */

  @DISPID(2)
  public void dockPositionStateChange(
    office._CustomTaskPane customTaskPaneInst) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
