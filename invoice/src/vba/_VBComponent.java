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

@IID("{EEE00921-E393-11D1-BB03-00C04FB6C4A6}")
public interface _VBComponent extends vba._VBComponent_Old {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "DesignerID"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(64) //= 0x40. The runtime will prefer the VTID if present
  @VTID(20)
  java.lang.String getDesignerID();


  // Properties:
}
