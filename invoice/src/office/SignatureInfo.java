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

@IID("{000CD6A2-0000-0000-C000-000000000046}")
public interface SignatureInfo extends office._IMsoDispObj {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "ReadOnly"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(1) //= 0x1. The runtime will prefer the VTID if present
  @VTID(9)
  boolean getReadOnly();


  /**
   * <p>
   * Getter method for the COM property "SignatureProvider"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(2) //= 0x2. The runtime will prefer the VTID if present
  @VTID(10)
  java.lang.String getSignatureProvider();


  /**
   * <p>
   * Getter method for the COM property "SignatureText"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(11)
  java.lang.String getSignatureText();


  /**
   * <p>
   * Setter method for the COM property "SignatureText"
   * </p>
   * @param pbstr Mandatory java.lang.String parameter.
   */

  @DISPID(3) //= 0x3. The runtime will prefer the VTID if present
  @VTID(12)
  void setSignatureText(
    java.lang.String pbstr);


  /**
   * <p>
   * Getter method for the COM property "SignatureImage"
   * </p>
   * @return  Returns a value of type Holder<com4j.stdole.IPictureDisp>
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(13)
  Holder<com4j.stdole.IPictureDisp> getSignatureImage();


  /**
   * <p>
   * Setter method for the COM property "SignatureImage"
   * </p>
   * @param ppipictdisp Mandatory Holder<com4j.stdole.IPictureDisp> parameter.
   */

  @DISPID(4) //= 0x4. The runtime will prefer the VTID if present
  @VTID(14)
  void setSignatureImage(
    Holder<com4j.stdole.IPictureDisp> ppipictdisp);


  /**
   * <p>
   * Getter method for the COM property "SignatureComment"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(15)
  java.lang.String getSignatureComment();


  /**
   * <p>
   * Setter method for the COM property "SignatureComment"
   * </p>
   * @param pbstr Mandatory java.lang.String parameter.
   */

  @DISPID(5) //= 0x5. The runtime will prefer the VTID if present
  @VTID(16)
  void setSignatureComment(
    java.lang.String pbstr);


  /**
   * @param sigdet Mandatory office.SignatureDetail parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(6) //= 0x6. The runtime will prefer the VTID if present
  @VTID(17)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getSignatureDetail(
    office.SignatureDetail sigdet);


  /**
   * @param certdet Mandatory office.CertificateDetail parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @DISPID(7) //= 0x7. The runtime will prefer the VTID if present
  @VTID(18)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getCertificateDetail(
    office.CertificateDetail certdet);


  /**
   * <p>
   * Getter method for the COM property "ContentVerificationResults"
   * </p>
   * @return  Returns a value of type office.ContentVerificationResults
   */

  @DISPID(8) //= 0x8. The runtime will prefer the VTID if present
  @VTID(19)
  office.ContentVerificationResults getContentVerificationResults();


  /**
   * <p>
   * Getter method for the COM property "CertificateVerificationResults"
   * </p>
   * @return  Returns a value of type office.CertificateVerificationResults
   */

  @DISPID(9) //= 0x9. The runtime will prefer the VTID if present
  @VTID(20)
  office.CertificateVerificationResults getCertificateVerificationResults();


  /**
   * <p>
   * Getter method for the COM property "IsValid"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(10) //= 0xa. The runtime will prefer the VTID if present
  @VTID(21)
  boolean getIsValid();


  /**
   * <p>
   * Getter method for the COM property "IsCertificateExpired"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(11) //= 0xb. The runtime will prefer the VTID if present
  @VTID(22)
  boolean getIsCertificateExpired();


  /**
   * <p>
   * Getter method for the COM property "IsCertificateRevoked"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(12) //= 0xc. The runtime will prefer the VTID if present
  @VTID(23)
  boolean getIsCertificateRevoked();


  /**
   * <p>
   * Getter method for the COM property "IsCertificateUntrusted"
   * </p>
   * @return  Returns a value of type boolean
   */

  @DISPID(13) //= 0xd. The runtime will prefer the VTID if present
  @VTID(24)
  boolean getIsCertificateUntrusted();


  /**
   * @param parentWindow Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(14) //= 0xe. The runtime will prefer the VTID if present
  @VTID(25)
  void showSignatureCertificate(
    com4j.Com4jObject parentWindow);


  /**
   * @param parentWindow Mandatory com4j.Com4jObject parameter.
   */

  @DISPID(15) //= 0xf. The runtime will prefer the VTID if present
  @VTID(26)
  void selectSignatureCertificate(
    com4j.Com4jObject parentWindow);


  /**
   * @param bstrThumbprint Mandatory java.lang.String parameter.
   */

  @DISPID(16) //= 0x10. The runtime will prefer the VTID if present
  @VTID(27)
  void selectCertificateDetailByThumbprint(
    java.lang.String bstrThumbprint);


  // Properties:
}
