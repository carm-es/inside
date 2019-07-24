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


package es.mpt.dsic.firma.cliente.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.firma.cliente.model package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


  private final static QName _FirmaFicheroResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "firmaFicheroResponse");
  private final static QName _FirmaFichero_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "firmaFichero");


  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.firma.cliente.model
   * 
   */
  public ObjectFactory() {}


  /**
   * Create an instance of {@link FirmaFicheroResponse }
   * 
   */
  public FirmaFicheroResponse createFirmaFicheroResponse() {
    return new FirmaFicheroResponse();
  }


  /**
   * Create an instance of {@link FirmaFichero }
   * 
   */
  public FirmaFichero createFirmaFichero() {
    return new FirmaFichero();
  }

  /**
   * Create an instance of {@link DatosFirmante }
   * 
   */
  public DatosFirmante createDatosFirmante() {
    return new DatosFirmante();
  }

  /**
   * Create an instance of {@link ApplicationLogin }
   * 
   */
  public ApplicationLogin createApplicationLogin() {
    return new ApplicationLogin();
  }


  /**
   * Create an instance of {@link Error }
   * 
   */
  public Error createError() {
    return new Error();
  }

  /**
   * Create an instance of {@link DatosSalida }
   * 
   */
  public DatosSalida createDatosSalida() {
    return new DatosSalida();
  }

  /**
   * Create an instance of {@link ResultadoFirmaFichero }
   * 
   */
  public ResultadoFirmaFichero createResultadoFirmaFichero() {
    return new ResultadoFirmaFichero();
  }


  /**
   * Create an instance of {@link DatosEntradaFichero }
   * 
   */
  public DatosEntradaFichero createDatosEntradaFichero() {
    return new DatosEntradaFichero();
  }


  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link FirmaFicheroResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "firmaFicheroResponse")
  public JAXBElement<FirmaFicheroResponse> createFirmaFicheroResponse(FirmaFicheroResponse value) {
    return new JAXBElement<FirmaFicheroResponse>(_FirmaFicheroResponse_QNAME,
        FirmaFicheroResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link FirmaFichero }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/", name = "firmaFichero")
  public JAXBElement<FirmaFichero> createFirmaFichero(FirmaFichero value) {
    return new JAXBElement<FirmaFichero>(_FirmaFichero_QNAME, FirmaFichero.class, null, value);
  }


}
