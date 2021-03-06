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

@IID("{0002441B-0000-0000-C000-000000000046}")
public abstract class RefreshEvents {
  // Methods:
  /**
   * @param cancel Mandatory Holder<Boolean> parameter.
   */

  @DISPID(1596)
  public void beforeRefresh(
    Holder<Boolean> cancel) {
        throw new UnsupportedOperationException();
  }


  /**
   * @param success Mandatory boolean parameter.
   */

  @DISPID(1597)
  public void afterRefresh(
    boolean success) {
        throw new UnsupportedOperationException();
  }


  // Properties:
}
