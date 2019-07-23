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


package es.mpt.dsic.eeutil.client.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the es.mpt.dsic.eeutil.client.model package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation
 * for XML content. The Java representation of XML content can consist of schema derived interfaces
 * and classes representing the binding of schema type definitions, element declarations and model
 * groups. Factory methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

  private final static QName _FoliarResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "foliarResponse");
  private final static QName _ErrorTest_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "ErrorTest");
  private final static QName _Foliar_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "foliar");
  private final static QName _ObtenerInformacionFirma_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "obtenerInformacionFirma");
  private final static QName _ObtenerInformacionFirmaResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "obtenerInformacionFirmaResponse");
  private final static QName _Visualizar_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "visualizar");
  private final static QName _VisualizarResponse_QNAME =
      new QName("http://service.ws.inside.dsic.mpt.es/", "visualizarResponse");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: es.mpt.dsic.eeutil.client.model
   * 
   */
  public ObjectFactory() {}

  /**
   * Create an instance of {@link ListaFirmaInfo }
   * 
   */
  public ListaFirmaInfo createListaFirmaInfo() {
    return new ListaFirmaInfo();
  }

  /**
   * Create an instance of {@link ListaCadenas }
   * 
   */
  public ListaCadenas createListaCadenas() {
    return new ListaCadenas();
  }

  /**
   * Create an instance of {@link ListaItem }
   * 
   */
  public ListaItem createListaItem() {
    return new ListaItem();
  }

  /**
   * Create an instance of {@link ListaPropiedades }
   * 
   */
  public ListaPropiedades createListaPropiedades() {
    return new ListaPropiedades();
  }

  /**
   * Create an instance of {@link Foliar }
   * 
   */
  public Foliar createFoliar() {
    return new Foliar();
  }


  /**
   * Create an instance of {@link EstadoInfo }
   * 
   */
  public EstadoInfo createEstadoInfo() {
    return new EstadoInfo();
  }

  /**
   * Create an instance of {@link FoliarResponse }
   * 
   */
  public FoliarResponse createFoliarResponse() {
    return new FoliarResponse();
  }


  /**
   * Create an instance of {@link VisualizarResponse }
   * 
   */
  public VisualizarResponse createVisualizarResponse() {
    return new VisualizarResponse();
  }



  /**
   * Create an instance of {@link ObtenerInformacionFirmaResponse }
   * 
   */
  public ObtenerInformacionFirmaResponse createObtenerInformacionFirmaResponse() {
    return new ObtenerInformacionFirmaResponse();
  }


  /**
   * Create an instance of {@link Visualizar }
   * 
   */
  public Visualizar createVisualizar() {
    return new Visualizar();
  }


  /**
   * Create an instance of {@link ObtenerInformacionFirma }
   * 
   */
  public ObtenerInformacionFirma createObtenerInformacionFirma() {
    return new ObtenerInformacionFirma();
  }

  /**
   * Create an instance of {@link Atributos }
   * 
   */
  public Atributos createAtributos() {
    return new Atributos();
  }


  /**
   * Create an instance of {@link OpcionesObtenerInformacionFirma }
   * 
   */
  public OpcionesObtenerInformacionFirma createOpcionesObtenerInformacionFirma() {
    return new OpcionesObtenerInformacionFirma();
  }

  /**
   * Create an instance of {@link OpcionesIndice }
   * 
   */
  public OpcionesIndice createOpcionesIndice() {
    return new OpcionesIndice();
  }

  /**
   * Create an instance of {@link ApplicationLogin }
   * 
   */
  public ApplicationLogin createApplicationLogin() {
    return new ApplicationLogin();
  }


  /**
   * Create an instance of {@link SalidaFoliado }
   * 
   */
  public SalidaFoliado createSalidaFoliado() {
    return new SalidaFoliado();
  }


  /**
   * Create an instance of {@link InformacionFirma }
   * 
   */
  public InformacionFirma createInformacionFirma() {
    return new InformacionFirma();
  }

  /**
   * Create an instance of {@link OpcionesPortada }
   * 
   */
  public OpcionesPortada createOpcionesPortada() {
    return new OpcionesPortada();
  }

  /**
   * Create an instance of {@link SalidaVisualizacion }
   * 
   */
  public SalidaVisualizacion createSalidaVisualizacion() {
    return new SalidaVisualizacion();
  }



  /**
   * Create an instance of {@link OpcionesVisualizacion }
   * 
   */
  public OpcionesVisualizacion createOpcionesVisualizacion() {
    return new OpcionesVisualizacion();
  }

  /**
   * Create an instance of {@link FirmaInfo }
   * 
   */
  public FirmaInfo createFirmaInfo() {
    return new FirmaInfo();
  }

  /**
   * Create an instance of {@link TipoDeFirma }
   * 
   */
  public TipoDeFirma createTipoDeFirma() {
    return new TipoDeFirma();
  }

  /**
   * Create an instance of {@link DatosFirmados }
   * 
   */
  public DatosFirmados createDatosFirmados() {
    return new DatosFirmados();
  }

  /**
   * Create an instance of {@link OpcionesFoliado }
   * 
   */
  public OpcionesFoliado createOpcionesFoliado() {
    return new OpcionesFoliado();
  }

  /**
   * Create an instance of {@link ContenidoInfo }
   * 
   */
  public ContenidoInfo createContenidoInfo() {
    return new ContenidoInfo();
  }

  /**
   * Create an instance of {@link Propiedad }
   * 
   */
  public Propiedad createPropiedad() {
    return new Propiedad();
  }

  /**
   * Create an instance of {@link OpcionesLogo }
   * 
   */
  public OpcionesLogo createOpcionesLogo() {
    return new OpcionesLogo();
  }

  /**
   * Create an instance of {@link Item }
   * 
   */
  public Item createItem() {
    return new Item();
  }

  /**
   * Create an instance of {@link OpcionesNumerosPagina }
   * 
   */
  public OpcionesNumerosPagina createOpcionesNumerosPagina() {
    return new OpcionesNumerosPagina();
  }

  /**
   * Create an instance of {@link DocumentoContenido }
   * 
   */
  public DocumentoContenido createDocumentoContenido() {
    return new DocumentoContenido();
  }

  /**
   * Create an instance of {@link ListaFirmaInfo.InformacionFirmas }
   * 
   */
  public ListaFirmaInfo.InformacionFirmas createListaFirmaInfoInformacionFirmas() {
    return new ListaFirmaInfo.InformacionFirmas();
  }

  /**
   * Create an instance of {@link ListaCadenas.Cadenas }
   * 
   */
  public ListaCadenas.Cadenas createListaCadenasCadenas() {
    return new ListaCadenas.Cadenas();
  }

  /**
   * Create an instance of {@link ListaItem.Items }
   * 
   */
  public ListaItem.Items createListaItemItems() {
    return new ListaItem.Items();
  }

  /**
   * Create an instance of {@link ListaPropiedades.Propiedades }
   * 
   */
  public ListaPropiedades.Propiedades createListaPropiedadesPropiedades() {
    return new ListaPropiedades.Propiedades();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link FoliarResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/", name = "foliarResponse")
  public JAXBElement<FoliarResponse> createFoliarResponse(FoliarResponse value) {
    return new JAXBElement<FoliarResponse>(_FoliarResponse_QNAME, FoliarResponse.class, null,
        value);
  }


  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link EstadoInfo }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/", name = "ErrorTest")
  public JAXBElement<EstadoInfo> createErrorTest(EstadoInfo value) {
    return new JAXBElement<EstadoInfo>(_ErrorTest_QNAME, EstadoInfo.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Foliar }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/", name = "foliar")
  public JAXBElement<Foliar> createFoliar(Foliar value) {
    return new JAXBElement<Foliar>(_Foliar_QNAME, Foliar.class, null, value);
  }


  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerInformacionFirma }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "obtenerInformacionFirma")
  public JAXBElement<ObtenerInformacionFirma> createObtenerInformacionFirma(
      ObtenerInformacionFirma value) {
    return new JAXBElement<ObtenerInformacionFirma>(_ObtenerInformacionFirma_QNAME,
        ObtenerInformacionFirma.class, null, value);
  }


  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link ObtenerInformacionFirmaResponse
   * }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/",
      name = "obtenerInformacionFirmaResponse")
  public JAXBElement<ObtenerInformacionFirmaResponse> createObtenerInformacionFirmaResponse(
      ObtenerInformacionFirmaResponse value) {
    return new JAXBElement<ObtenerInformacionFirmaResponse>(_ObtenerInformacionFirmaResponse_QNAME,
        ObtenerInformacionFirmaResponse.class, null, value);
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Visualizar }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/", name = "visualizar")
  public JAXBElement<Visualizar> createVisualizar(Visualizar value) {
    return new JAXBElement<Visualizar>(_Visualizar_QNAME, Visualizar.class, null, value);
  }


  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link VisualizarResponse }{@code >}}
   * 
   */
  @XmlElementDecl(namespace = "http://service.ws.inside.dsic.mpt.es/", name = "visualizarResponse")
  public JAXBElement<VisualizarResponse> createVisualizarResponse(VisualizarResponse value) {
    return new JAXBElement<VisualizarResponse>(_VisualizarResponse_QNAME, VisualizarResponse.class,
        null, value);
  }

}
