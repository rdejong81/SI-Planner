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

@IID("{000C03D7-0000-0000-C000-000000000046}")
public interface IConverter extends Com4jObject {
  // Methods:
  /**
   * @param pcap Mandatory office.IConverterApplicationPreferences parameter.
   * @param pcuic Mandatory office.IConverterUICallback parameter.
   * @return  Returns a value of type office.IConverterPreferences
   */

  @VTID(3)
  @ReturnValue(index=1)
  office.IConverterPreferences hrInitConverter(
    office.IConverterApplicationPreferences pcap,
    office.IConverterUICallback pcuic);


  /**
   * @param pcuic Mandatory office.IConverterUICallback parameter.
   */

  @VTID(4)
  void hrUninitConverter(
    office.IConverterUICallback pcuic);


  /**
   * @param bstrSourcePath Mandatory java.lang.String parameter.
   * @param bstrDestPath Mandatory java.lang.String parameter.
   * @param pcap Mandatory office.IConverterApplicationPreferences parameter.
   * @param pcuic Mandatory office.IConverterUICallback parameter.
   * @return  Returns a value of type office.IConverterPreferences
   */

  @VTID(5)
  @ReturnValue(index=3)
  office.IConverterPreferences hrImport(
    java.lang.String bstrSourcePath,
    java.lang.String bstrDestPath,
    office.IConverterApplicationPreferences pcap,
    office.IConverterUICallback pcuic);


  /**
   * @param bstrSourcePath Mandatory java.lang.String parameter.
   * @param bstrDestPath Mandatory java.lang.String parameter.
   * @param bstrClass Mandatory java.lang.String parameter.
   * @param pcap Mandatory office.IConverterApplicationPreferences parameter.
   * @param pcuic Mandatory office.IConverterUICallback parameter.
   * @return  Returns a value of type office.IConverterPreferences
   */

  @VTID(6)
  @ReturnValue(index=4)
  office.IConverterPreferences hrExport(
    java.lang.String bstrSourcePath,
    java.lang.String bstrDestPath,
    java.lang.String bstrClass,
    office.IConverterApplicationPreferences pcap,
    office.IConverterUICallback pcuic);


  /**
   * @param bstrPath Mandatory java.lang.String parameter.
   * @param pbstrClass Mandatory Holder<java.lang.String> parameter.
   * @param pcap Mandatory office.IConverterApplicationPreferences parameter.
   * @param ppcp Mandatory Holder<office.IConverterPreferences> parameter.
   * @param pcuic Mandatory office.IConverterUICallback parameter.
   */

  @VTID(7)
  void hrGetFormat(
    java.lang.String bstrPath,
    Holder<java.lang.String> pbstrClass,
    office.IConverterApplicationPreferences pcap,
    Holder<office.IConverterPreferences> ppcp,
    office.IConverterUICallback pcuic);


  /**
   * @param hrErr Mandatory int parameter.
   * @param pcap Mandatory office.IConverterApplicationPreferences parameter.
   * @return  Returns a value of type java.lang.String
   */

  @VTID(8)
  @ReturnValue(index=1)
  java.lang.String hrGetErrorString(
    int hrErr,
    office.IConverterApplicationPreferences pcap);


  // Properties:
}
