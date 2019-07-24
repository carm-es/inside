/*
 * Copyright (C) 2016 MINHAP, Gobierno de España This program is licensed and may be used, modified
 * and redistributed under the terms of the European Public License (EUPL), either version 1.1 or
 * (at your option) any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and more details. You
 * should have received a copy of the EUPL1.1 license along with this program; if not, you may find
 * it at http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 */


package es.mpt.dsic.inside.service.validacionENI.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.service.validacionENI.model package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _ValidarFirmaExpedienteENI_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarFirmaExpedienteENI");
  private final static QName _ValidarExpedienteENIResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarExpedienteENIResponse");
  private final static QName _ValidarFirmaExpedienteENIResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarFirmaExpedienteENIResponse");
  private final static QName _ValidarExpedienteENI_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarExpedienteENI");
  private final static QName _ValidarDocumentoENIResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarDocumentoENIResponse");
  private final static QName _ValidarFirmaDocumentoENI_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarFirmaDocumentoENI");
  private final static QName _ValidarDocumentoENI_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarDocumentoENI");
  private final static QName _ValidarFirmaDocumentoENIResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "validarFirmaDocumentoENIResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.service.validacionENI.model
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link ValidarFirmaDocumentoENIResponse }
   * 
   */
  public ValidarFirmaDocumentoENIResponse createValidarFirmaDocumentoENIResponse() {
    return new ValidarFirmaDocumentoENIResponse();
  }

  /**
   * Create an instance of {@link ValidarFirmaDocumentoENI }
   * 
   */
  public ValidarFirmaDocumentoENI createValidarFirmaDocumentoENI() {
    return new ValidarFirmaDocumentoENI();
  }

  /**
   * Create an instance of {@link ValidarDocumentoENIResponse }
   * 
   */
  public ValidarDocumentoENIResponse createValidarDocumentoENIResponse() {
    return new ValidarDocumentoENIResponse();
  }

  /**
   * Create an instance of {@link ValidarDocumentoENI }
   * 
   */
  public ValidarDocumentoENI createValidarDocumentoENI() {
    return new ValidarDocumentoENI();
  }

  /**
   * Create an instance of {@link ValidarFirmaExpedienteENIResponse }
   * 
   */
  public ValidarFirmaExpedienteENIResponse createValidarFirmaExpedienteENIResponse() {
    return new ValidarFirmaExpedienteENIResponse();
  }

  /**
   * Create an instance of {@link ValidarExpedienteENI }
   * 
   */
  public ValidarExpedienteENI createValidarExpedienteENI() {
    return new ValidarExpedienteENI();
  }

  /**
   * Create an instance of {@link ValidarFirmaExpedienteENI }
   * 
   */
  public ValidarFirmaExpedienteENI createValidarFirmaExpedienteENI() {
    return new ValidarFirmaExpedienteENI();
  }

  /**
   * Create an instance of {@link ValidarExpedienteENIResponse }
   * 
   */
  public ValidarExpedienteENIResponse createValidarExpedienteENIResponse() {
    return new ValidarExpedienteENIResponse();
  }

  /**
   * Create an instance of {@link ApplicationLogin }
   * 
   */
  public ApplicationLogin createApplicationLogin() {
    return new ApplicationLogin();
  }

  /**
   * Create an instance of {@link RespuestaValidacionENI }
   * 
   */
  public RespuestaValidacionENI createRespuestaValidacionENI() {
    return new RespuestaValidacionENI();
  }

  /**
   * Create an instance of {@link Detalle }
   * 
   */
  public Detalle createDetalle() {
    return new Detalle();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarFirmaExpedienteENI }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "validarFirmaExpedienteENI")
  public JAXBElement<ValidarFirmaExpedienteENI> createValidarFirmaExpedienteENI(
      ValidarFirmaExpedienteENI value) {
    return new JAXBElement<ValidarFirmaExpedienteENI>(_ValidarFirmaExpedienteENI_QNAME,
        ValidarFirmaExpedienteENI.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarExpedienteENIResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "validarExpedienteENIResponse")
  public JAXBElement<ValidarExpedienteENIResponse> createValidarExpedienteENIResponse(
      ValidarExpedienteENIResponse value) {
    return new JAXBElement<ValidarExpedienteENIResponse>(_ValidarExpedienteENIResponse_QNAME,
        ValidarExpedienteENIResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarFirmaExpedienteENIResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "validarFirmaExpedienteENIResponse")
  public JAXBElement<ValidarFirmaExpedienteENIResponse> createValidarFirmaExpedienteENIResponse(
      ValidarFirmaExpedienteENIResponse value) {
    return new JAXBElement<ValidarFirmaExpedienteENIResponse>(
        _ValidarFirmaExpedienteENIResponse_QNAME, ValidarFirmaExpedienteENIResponse.class, null,
        value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarExpedienteENI }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "validarExpedienteENI")
  public JAXBElement<ValidarExpedienteENI> createValidarExpedienteENI(ValidarExpedienteENI value) {
    return new JAXBElement<ValidarExpedienteENI>(_ValidarExpedienteENI_QNAME,
        ValidarExpedienteENI.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarDocumentoENIResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "validarDocumentoENIResponse")
  public JAXBElement<ValidarDocumentoENIResponse> createValidarDocumentoENIResponse(
      ValidarDocumentoENIResponse value) {
    return new JAXBElement<ValidarDocumentoENIResponse>(_ValidarDocumentoENIResponse_QNAME,
        ValidarDocumentoENIResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarFirmaDocumentoENI }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "validarFirmaDocumentoENI")
  public JAXBElement<ValidarFirmaDocumentoENI> createValidarFirmaDocumentoENI(
      ValidarFirmaDocumentoENI value) {
    return new JAXBElement<ValidarFirmaDocumentoENI>(_ValidarFirmaDocumentoENI_QNAME,
        ValidarFirmaDocumentoENI.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarDocumentoENI }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/", name = "validarDocumentoENI")
  public JAXBElement<ValidarDocumentoENI> createValidarDocumentoENI(ValidarDocumentoENI value) {
    return new JAXBElement<ValidarDocumentoENI>(_ValidarDocumentoENI_QNAME,
        ValidarDocumentoENI.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ValidarFirmaDocumentoENIResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "validarFirmaDocumentoENIResponse")
  public JAXBElement<ValidarFirmaDocumentoENIResponse> createValidarFirmaDocumentoENIResponse(
      ValidarFirmaDocumentoENIResponse value) {
    return new JAXBElement<ValidarFirmaDocumentoENIResponse>(
        _ValidarFirmaDocumentoENIResponse_QNAME, ValidarFirmaDocumentoENIResponse.class, null,
        value);
  }

}
