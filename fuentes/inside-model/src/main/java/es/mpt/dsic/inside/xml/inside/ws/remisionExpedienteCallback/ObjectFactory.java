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


package es.mpt.dsic.inside.xml.inside.ws.remisionExpedienteCallback;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.inside.xml.inside.ws.remisionExpedienteCallback package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _ErrorEsb_QNAME =
      new QName("http://justicia.es/esb/error/xsd-schemas/V1", "ErrorEsb");
  private final static QName _AuditoriaEsb_QNAME =
      new QName("http://justicia.es/esb/comun/xsd-schemas/V1", "AuditoriaEsb");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.inside.xml.inside.ws.remisionExpedienteCallback
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link PresentadorType }
   * 
   */
  public PresentadorType createPresentadorType() {
    return new PresentadorType();
  }

  /**
   * Create an instance of {@link DatosEnvioType }
   * 
   */
  public DatosEnvioType createDatosEnvioType() {
    return new DatosEnvioType();
  }

  /**
   * Create an instance of {@link AuditoriaEsbType }
   * 
   */
  public AuditoriaEsbType createAuditoriaEsbType() {
    return new AuditoriaEsbType();
  }

  /**
   * Create an instance of {@link AsuntoType }
   * 
   */
  public AsuntoType createAsuntoType() {
    return new AsuntoType();
  }

  /**
   * Create an instance of {@link PeticionNotificarEstadoEnvioType }
   * 
   */
  public PeticionNotificarEstadoEnvioType createPeticionNotificarEstadoEnvioType() {
    return new PeticionNotificarEstadoEnvioType();
  }

  /**
   * Create an instance of {@link PiezaType }
   * 
   */
  public PiezaType createPiezaType() {
    return new PiezaType();
  }

  /**
   * Create an instance of {@link RespuestaNotificarEstadoEnvioType }
   * 
   */
  public RespuestaNotificarEstadoEnvioType createRespuestaNotificarEstadoEnvioType() {
    return new RespuestaNotificarEstadoEnvioType();
  }

  /**
   * Create an instance of {@link ErrorEsbType }
   * 
   */
  public ErrorEsbType createErrorEsbType() {
    return new ErrorEsbType();
  }

  /**
   * Create an instance of {@link ProcedimientoType }
   * 
   */
  public ProcedimientoType createProcedimientoType() {
    return new ProcedimientoType();
  }

  /**
   * Create an instance of {@link DatosIdEnvioType }
   * 
   */
  public DatosIdEnvioType createDatosIdEnvioType() {
    return new DatosIdEnvioType();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ErrorEsbType }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://justicia.es/esb/error/xsd-schemas/V1", name = "ErrorEsb")
  public JAXBElement<ErrorEsbType> createErrorEsb(ErrorEsbType value) {
    return new JAXBElement<ErrorEsbType>(_ErrorEsb_QNAME, ErrorEsbType.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link AuditoriaEsbType }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://justicia.es/esb/comun/xsd-schemas/V1", name = "AuditoriaEsb")
  public JAXBElement<AuditoriaEsbType> createAuditoriaEsb(AuditoriaEsbType value) {
    return new JAXBElement<AuditoriaEsbType>(_AuditoriaEsb_QNAME, AuditoriaEsbType.class, null,
        value);
  }

}
