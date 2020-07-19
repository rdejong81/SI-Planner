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

@IID("{000C03D5-0000-0000-C000-000000000046}")
public interface IConverterApplicationPreferences extends Com4jObject {
  // Methods:
  /**
   * @return  Returns a value of type int
   */

  @VTID(3)
  int hrGetLcid();


  /**
   * @return  Returns a value of type int
   */

  @VTID(4)
  int hrGetHwnd();


  /**
   * @return  Returns a value of type java.lang.String
   */

  @VTID(5)
  java.lang.String hrGetApplication();


  /**
   * @return  Returns a value of type int
   */

  @VTID(6)
  int hrCheckFormat();


  // Properties:
}
