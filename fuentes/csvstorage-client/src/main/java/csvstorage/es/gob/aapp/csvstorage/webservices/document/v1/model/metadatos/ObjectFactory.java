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


package csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model.metadatos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model.metadatos
 * package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _Metadatos_QNAME = new QName(
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos", "metadatos");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: csvstorage.es.gob.aapp.csvstorage.webservices.document.v1.model.metadatos
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link TipoMetadatos }
   * 
   */
  public TipoMetadatos createTipoMetadatos() {
    return new TipoMetadatos();
  }

  /**
   * Create an instance of {@link TipoEstadoElaboracion }
   * 
   */
  public TipoEstadoElaboracion createTipoEstadoElaboracion() {
    return new TipoEstadoElaboracion();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link TipoMetadatos }{@code >}}
   * 
   */
  @XmlElementDecl(
      namespace = "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos",
      name = "metadatos")
  public JAXBElement<TipoMetadatos> createMetadatos(TipoMetadatos value) {
    return new JAXBElement<TipoMetadatos>(_Metadatos_QNAME, TipoMetadatos.class, null, value);
  }

}
