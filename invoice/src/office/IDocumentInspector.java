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

@IID("{000CD706-0000-0000-C000-000000000046}")
public interface IDocumentInspector extends Com4jObject {
  // Methods:
  /**
   * @param name Mandatory Holder<java.lang.String> parameter.
   * @param desc Mandatory Holder<java.lang.String> parameter.
   */

  @VTID(3)
  void getInfo(
    Holder<java.lang.String> name,
    Holder<java.lang.String> desc);


  /**
   * @param doc Mandatory com4j.Com4jObject parameter.
   * @param status Mandatory Holder<office.MsoDocInspectorStatus> parameter.
   * @param result Mandatory Holder<java.lang.String> parameter.
   * @param action Mandatory Holder<java.lang.String> parameter.
   */

  @VTID(4)
  void inspect(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject doc,
    Holder<office.MsoDocInspectorStatus> status,
    Holder<java.lang.String> result,
    Holder<java.lang.String> action);


  /**
   * @param doc Mandatory com4j.Com4jObject parameter.
   * @param hwnd Mandatory int parameter.
   * @param status Mandatory Holder<office.MsoDocInspectorStatus> parameter.
   * @param result Mandatory Holder<java.lang.String> parameter.
   */

  @VTID(5)
  void fix(
    @MarshalAs(NativeType.Dispatch) com4j.Com4jObject doc,
    int hwnd,
    Holder<office.MsoDocInspectorStatus> status,
    Holder<java.lang.String> result);


  // Properties:
}
