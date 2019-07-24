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

package es.mpt.dsic.inside.service.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.util.Assert;
import org.w3c.dom.Node;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidacionInfo;
import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaUsuario;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterUtilsException;
import es.mpt.dsic.inside.model.converter.mime.InsideMimeUtils;
import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.model.objetos.ObjetoAuditoriaFirmaServidor;
import es.mpt.dsic.inside.model.objetos.ObjetoCredencialEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoElastic;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideDocumentoUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideExpedienteUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadAplicacionEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersionable;
import es.mpt.dsic.inside.model.objetos.ObjetoNumeroProcedimiento;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion;
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionExpediente;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionExpediente;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaAcceso;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaAccesoDocumento;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaToken;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoComunicacionTokenExpediente;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoEstructuraCarpetaInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoSolicitudAccesoExpAppUrl;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoSolicitudAccesoExpediente;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.model.objetos.filter.ObjetoFilterPageRequest;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoDsSignatureInside;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.enivalidation.InsideEniValidationService;
import es.mpt.dsic.inside.service.exception.InSideServiceContentExtractException;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InSideServiceRequestErrorException;
import es.mpt.dsic.inside.service.exception.InSideServiceSignerException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.object.converter.InsideServiceObjectConverterException;
import es.mpt.dsic.inside.service.object.definitions.InSideServiceObjectDefinitionNotFoundException;
import es.mpt.dsic.inside.service.object.definitions.InsideObjectDefinitionsContainer;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.signer.InsideServiceSigner;
import es.mpt.dsic.inside.service.portales.impl.ConsumidorPortales;
import es.mpt.dsic.inside.service.semanticValidation.InsideSemanticValidationService;
import es.mpt.dsic.inside.service.semanticValidation.exception.InsideSemanticValidationException;
import es.mpt.dsic.inside.service.store.InsideServiceJta;
import es.mpt.dsic.inside.service.store.InsideServiceStore;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNoActiveException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectVinculatedException;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterInsideRol;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.InsideObjectsUtils;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.service.util.UnidadOrganicaRolPortales;
import es.mpt.dsic.inside.service.validacionENI.impl.ConsumidorValidacionENI;
import es.mpt.dsic.inside.service.validacionENIMtom.model.Validaciones;
import es.mpt.dsic.inside.service.visualizacion.InsideServiceVisualizacion;
import es.mpt.dsic.inside.service.visualizacion.OpcionesVisualizacionIndice;
import es.mpt.dsic.inside.service.visualizacion.ResultadoVisualizacionDocumento;
import es.mpt.dsic.inside.store.hibernate.entity.InsideRol;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;
import es.mpt.dsic.inside.util.XMLUtils;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoDocumentoIndizado;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoIndiceContenido;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;

public class InSideServiceImpl implements InSideService {

  protected static final Log logger = LogFactory.getLog(InSideServiceImpl.class);

  @Autowired
  @Qualifier("InsideObjectDefinitions")
  private InsideObjectDefinitionsContainer objectDefinitions;

  @Autowired
  private InsideServiceStore insideStore;

  @Autowired
  private InsideServiceSigner insideSigner;

  @Autowired
  private InfoFirmaService infoFirmaService;

  @Autowired
  private InsideEniValidationService insideEniValidationService;

  @Autowired
  private InsideSemanticValidationService insideSemanticValidationService;

  @Autowired
  private InsideServiceVisualizacion insideVisualizacion;

  @Autowired
  private ConsumidorValidacionENI consumidorValidacionENI;

  // @Autowired
  private ConsumidorPortales consumidorPortales;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  private InsideServiceJta insideServiceJta;


  private String funcionResumenDocumentos;

  private MessageDigest messageDigest;

  private static final String VALIDACION_CORRECTA = "Validación Correcta";

  @Override
  public ObjetoExpedienteInside altaExpediente(ObjetoExpedienteInside expediente, String usuario,
      boolean online) throws InSideServiceException {
    return altaExpediente(expediente, false, usuario, online);
  }

  @Override
  public ObjetoExpedienteInside altaExpediente(ObjetoExpedienteInside expediente,
      boolean signatureServer, String usuario, boolean online) throws InSideServiceException {
    logger.debug("Inicio operacion altaExpediente");

    validateObjetoInsideIdentificador(expediente);
    validateObjetoInsideVersion(expediente);

    if (expediente.getMetadatos().getEstado() == null) {
      expediente.getMetadatos().setEstado(ObjetoExpedienteInsideMetadatosEnumeracionEstados.E_01);
    }

    if (expediente.getMetadatos().getFechaAperturaExpediente() == null) {
      expediente.getMetadatos().setFechaAperturaExpediente(GregorianCalendar.getInstance());
    }

    expediente = validate(expediente);

    if (signatureServer) {
      firmarIndiceExpediente(expediente, null, usuario);
    }

    expediente = converToRepository(expediente);

    ObjetoExpedienteInside expedienteStored = insideStore.storeObject(expediente, usuario, online);

    logger.debug("Fin operacion altaExpediente");

    return expedienteStored;
  }

  @Override
  public ObjetoExpedienteInside getExpediente(String identificador, boolean visualizacionIndice)
      throws InSideServiceException {
    return getExpediente(identificador, null, visualizacionIndice);
  }

  @Override
  public ObjetoExpedienteInside getExpediente(String identificador, Integer version,
      boolean visualizacionIndice) throws InSideServiceException {

    ObjetoExpedienteInside expediente =
        getObjetoInside(ObjetoExpedienteInside.class, identificador, version);
    expediente = convertFromRepository(expediente);

    if (visualizacionIndice) {
      expediente = obtenerVisualizacionIndiceSiActivo(expediente);
    }

    return expediente;
  }

  @Override
  public List<ObjetoInsideVersion> getVersionesExpediente(String identificador)
      throws InSideServiceException {
    return insideStore.getObjectVersions(ObjetoExpedienteInside.class, identificador);
  }

  @Override
  public List<ObjetoInsideVersion> getListaVersionesExpediente(String identificador)
      throws InSideServiceException {
    return insideStore.getListaVersionesExpediente(identificador);
  }

  @Override
  public ObjetoExpedienteInsideMetadatos getMetadatosExpediente(String identificador)
      throws InSideServiceException {
    logger.debug("Inicio operacion getMetadatosExpediente");
    logger.debug("Parametro <identificador> = " + identificador);
    ObjetoExpedienteInsideMetadatos metadatos =
        getObjetoInsideMetadatos(ObjetoExpedienteInside.class, identificador, null);
    logger.debug("metadatos recuperados: " + metadatos.toString());
    logger.debug("Fin operacion getMetadatosExpediente");
    return metadatos;
  }

  @Override
  public ObjetoExpedienteInsideMetadatos getMetadatosExpediente(String identificador,
      Integer version) throws InSideServiceException {
    logger.debug("Inicio operacion getMetadatosExpediente");
    logger.debug("Parametro <identificador>, <version> = " + identificador + ", " + version);
    ObjetoExpedienteInsideMetadatos metadatos =
        getObjetoInsideMetadatos(ObjetoExpedienteInside.class, identificador, version);
    logger.debug("metadatos recuperados: " + metadatos.toString());
    logger.debug("Fin operacion getMetadatosExpediente");
    return metadatos;
  }

  @Override
  public ObjetoExpedienteInside modificaExpedienteInside(ObjetoExpedienteInside expediente,
      String usuario, boolean online) throws InSideServiceException {
    return modificaExpedienteInside(expediente, insideSigner.isActivo(), usuario, online);
  }

  @Override
  public ObjetoExpedienteInside modificaExpedienteInside(ObjetoExpedienteInside expediente,
      boolean signatureServer, String usuario, boolean online) throws InSideServiceException {

    expediente = validate(expediente);

    if (signatureServer) {
      firmarIndiceExpediente(expediente, null, usuario);
    }

    expediente = converToRepository(expediente);
    expediente = insideStore.updateObject(expediente, usuario, online);

    return convertFromRepository(expediente);
  }

  @Override
  public ObjetoExpedienteInside cambiaMetadatosExpediente(String identificador,
      ObjetoExpedienteInsideMetadatos metadatos, String usuario, boolean online)
      throws InSideServiceException {

    ObjetoExpedienteInside expediente = getExpediente(identificador, false);
    expediente.setMetadatos(metadatos);
    expediente = modificaExpedienteInside(expediente, usuario, online);

    return expediente;
  }

  @Override
  public ObjetoExpedienteInside importarExpediente(String identificador,
      String identificadorImportado, String ruta, String usuario, boolean online)
      throws InSideServiceException {
    ObjetoExpedienteInside expediente = getExpediente(identificador, false);
    expediente = importExpedient(expediente, identificadorImportado, ruta);
    return modificaExpedienteInside(expediente, insideSigner.isActivo(), usuario, online);
  }

  @Override
  public ObjetoExpedienteInside importarExpediente(ObjetoExpedienteInside expedienteNew,
      String identificadorImportado, String ruta, boolean signatureServer, String usuario,
      boolean online) throws InSideServiceException {
    ObjetoExpedienteInside expediente =
        importExpedient(expedienteNew, identificadorImportado, ruta);
    return altaExpediente(expediente, signatureServer, usuario, online);
  }

  public ObjetoExpedienteInside importarExpedienteSinAltaExp(ObjetoExpedienteInside expedienteNew,
      String identificadorImportado, String ruta) throws InSideServiceException {
    return importExpedient(expedienteNew, identificadorImportado, ruta);
  }

  public ObjetoExpedienteInside importExpedient(ObjetoExpedienteInside expediente,
      String identificadorImportado, String ruta) throws InSideServiceException {
    ObjetoExpedienteInside expedienteImportado = getExpediente(identificadorImportado, false);
    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos destino =
        getCarpetaOExpedienteDeRuta(expediente, ruta);
    ObjetoExpedienteInsideIndiceContenido indiceImportado =
        new ObjetoExpedienteInsideIndiceContenido();

    indiceImportado.setIdentificadorExpedienteAsociado(identificadorImportado);
    indiceImportado
        .setTipoAsociacion(ObjetoExpedienteInsideIndiceContenido.TipoAsociacion.IMPORTACION);
    indiceImportado.setOrden(destino.getElementosIndizados().size() + 1);
    indiceImportado.setFechaIndiceElectronico(
        expedienteImportado.getIndice().getIndiceContenido().getFechaIndiceElectronico());

    // la version de mysql inferior a 5.6.X no trabaja con milisegundos
    GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
    calendar.set(Calendar.MILLISECOND, 0);
    destino.getElementosIndizados().add(indiceImportado);
    ((ObjetoExpedienteInsideIndiceContenido) destino).setFechaIndiceElectronico(calendar);

    addElementosIndizadosImportados(indiceImportado,
        expedienteImportado.getIndice().getIndiceContenido().getElementosIndizados());
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside vincularExpediente(String identificador,
      String identificadorVinculado, String ruta, String usuario, boolean online)
      throws InSideServiceException {

    ObjetoExpedienteInside expediente = getExpediente(identificador, false);
    expediente = linkExpedient(expediente, identificadorVinculado, ruta);

    return modificaExpedienteInside(expediente, insideSigner.isActivo(), usuario, online);
  }

  @Override
  public ObjetoExpedienteInside vincularExpediente(ObjetoExpedienteInside expedienteNew,
      String identificadorVinculado, String ruta, boolean signatureServer, String usuario,
      boolean online) throws InSideServiceException {

    ObjetoExpedienteInside expediente = linkExpedient(expedienteNew, identificadorVinculado, ruta);

    // return modificaExpedienteInside(expediente, signatureServer, usuario,
    // online);
    return altaExpediente(expediente, signatureServer, usuario, online);
  }

  public ObjetoExpedienteInside vincularExpedienteSinAltaExp(ObjetoExpedienteInside expedienteNew,
      String identificadorVinculado, String ruta) throws InSideServiceException {

    return linkExpedient(expedienteNew, identificadorVinculado, ruta);
  }

  public ObjetoExpedienteInside linkExpedient(ObjetoExpedienteInside expediente,
      String identificadorVinculado, String ruta) throws InSideServiceException {
    ObjetoExpedienteInside expedienteVinculado = getExpediente(identificadorVinculado, false);
    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos destino =
        getCarpetaOExpedienteDeRuta(expediente, ruta);
    ObjetoExpedienteInsideIndiceContenido indiceVinculado =
        new ObjetoExpedienteInsideIndiceContenido();

    indiceVinculado.setIdentificadorExpedienteAsociado(identificadorVinculado);
    indiceVinculado
        .setTipoAsociacion(ObjetoExpedienteInsideIndiceContenido.TipoAsociacion.VINCULACION);
    indiceVinculado.setOrden(destino.getElementosIndizados().size() + 1);
    indiceVinculado.setFechaIndiceElectronico(
        expedienteVinculado.getIndice().getIndiceContenido().getFechaIndiceElectronico());
    destino.getElementosIndizados().add(indiceVinculado);

    // la version de mysql inferior a 5.6.X no trabaja con milisegundos
    GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
    calendar.set(Calendar.MILLISECOND, 0);
    ((ObjetoExpedienteInsideIndiceContenido) destino).setFechaIndiceElectronico(calendar);

    addElementosIndizadosImportados(indiceVinculado,
        expedienteVinculado.getIndice().getIndiceContenido().getElementosIndizados());
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside createOpenViewExpedient(String identificador, String identifierView,
      String usuario, boolean online) throws InSideServiceException {

    ObjetoExpedienteInside nuevoExpediente = createNewViewExp(identificador, identifierView, false);

    return vincularExpediente(nuevoExpediente, identificador, "/", insideSigner.isActivo(), usuario,
        online);
  }

  @Override
  public ObjetoExpedienteInside createOpenViewExpedient(String identificador, String identifierView,
      boolean signatureServer, String usuario, boolean online) throws InSideServiceException {

    ObjetoExpedienteInside nuevoExpediente = createNewViewExp(identificador, identifierView, false);

    if (signatureServer)
      nuevoExpediente =
          vincularExpediente(nuevoExpediente, identificador, "/", signatureServer, usuario, online);
    else
      nuevoExpediente = vincularExpedienteSinAltaExp(nuevoExpediente, identificador, "/");

    return nuevoExpediente;
  }

  @Override
  public ObjetoExpedienteInside createClosedViewExpedient(String identificador,
      String identifierView, String usuario, boolean online) throws InSideServiceException {

    ObjetoExpedienteInside nuevoExpediente = createNewViewExp(identificador, identifierView, true);

    return importarExpediente(nuevoExpediente, identificador, "/", insideSigner.isActivo(), usuario,
        online);
  }

  @Override
  public ObjetoExpedienteInside createClosedViewExpedient(String identificador,
      String identifierView, boolean signatureServer, String usuario, boolean online)
      throws InSideServiceException {

    ObjetoExpedienteInside nuevoExpediente = createNewViewExp(identificador, identifierView, true);

    if (signatureServer)
      nuevoExpediente =
          importarExpediente(nuevoExpediente, identificador, "/", signatureServer, usuario, online);
    else
      nuevoExpediente = importarExpedienteSinAltaExp(nuevoExpediente, identificador, "/");

    return nuevoExpediente;
  }

  public ObjetoExpedienteInside createNewViewExp(String identificador, String identifierView,
      boolean isClosedView) throws InSideServiceException {
    ObjetoExpedienteInsideMetadatos metadatos = getMetadatosExpediente(identificador);
    ObjetoExpedienteInside nuevoExpediente = new ObjetoExpedienteInside();

    if (identifierView != null && !"".equals(identifierView)) {
      nuevoExpediente.setIdentificador(identifierView);
      metadatos.setIdentificadorObjetoInside(identifierView);
    }

    nuevoExpediente.setMetadatos(metadatos);

    if (isClosedView)
      nuevoExpediente.getMetadatos()
          .setEstado(ObjetoExpedienteInsideMetadatosEnumeracionEstados.E_02);

    return nuevoExpediente;
  }

  protected void addElementosIndizadosImportados(
      ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos contenedor,
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementos)
      throws InsideServiceInternalException {
    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado : elementos) {
      if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) {
        ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos contenedorIndizado =
            (ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) elementoIndizado;
        ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos nuevoContenedor;
        if (contenedorIndizado instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
          ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
              (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) contenedorIndizado;
          ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpIndizadaImportada =
              new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();
          carpIndizadaImportada.setIdentificadorCarpeta(carpetaIndizada.getIdentificadorCarpeta());
          nuevoContenedor = carpIndizadaImportada;

        } else if (contenedorIndizado instanceof ObjetoExpedienteInsideIndiceContenido) {
          ObjetoExpedienteInsideIndiceContenido expIndizado =
              (ObjetoExpedienteInsideIndiceContenido) contenedorIndizado;
          ObjetoExpedienteInsideIndiceContenido expIndizadoImportado =
              new ObjetoExpedienteInsideIndiceContenido();
          expIndizadoImportado.setFechaIndiceElectronico(expIndizado.getFechaIndiceElectronico());
          expIndizadoImportado
              .setIdentificadorExpedienteAsociado(expIndizado.getIdentificadorExpedienteAsociado());
          expIndizadoImportado.setTipoAsociacion(expIndizado.getTipoAsociacion());
          nuevoContenedor = expIndizadoImportado;
        } else {
          throw new InsideServiceInternalException("El elementoIndizado es de un tipo no válido "
              + elementoIndizado.getClass().getName());
        }
        addElementosIndizadosImportados(nuevoContenedor,
            contenedorIndizado.getElementosIndizados());
        nuevoContenedor.setOrden(contenedorIndizado.getOrden());
        contenedor.getElementosIndizados().add(nuevoContenedor);
      } else if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
            (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado;
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado docIndizadoImportado =
            new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();
        docIndizadoImportado
            .setOrdenDocumentoExpediente(documentoIndizado.getOrdenDocumentoExpediente());
        docIndizadoImportado.setOrden(documentoIndizado.getOrden());
        docIndizadoImportado.setFuncionResumen(documentoIndizado.getFuncionResumen());
        docIndizadoImportado
            .setFechaIncorporacionExpediente(documentoIndizado.getFechaIncorporacionExpediente());
        docIndizadoImportado
            .setIdentificadorDocumento(documentoIndizado.getIdentificadorDocumento());
        docIndizadoImportado.setValorHuella(documentoIndizado.getValorHuella());
        contenedor.getElementosIndizados().add(docIndizadoImportado);
      } else {
        throw new InsideServiceInternalException(
            "El elementoIndizado es de un tipo no válido " + elementoIndizado.getClass().getName());
      }
    }
  }

  @Override
  public ObjetoExpedienteInside setEstadoExpediente(String identificador,
      ObjetoExpedienteInsideMetadatosEnumeracionEstados estado, boolean signatureServer,
      String usuario, boolean online) throws InSideServiceException {

    ObjetoExpedienteInside expediente = getExpediente(identificador, false);
    expediente.getMetadatos().setEstado(estado);

    expediente = modificaExpedienteInside(expediente, signatureServer, usuario, online);

    return expediente;
  }

  @Override
  public ObjetoExpedienteInside setEstadoExpediente(String identificador,
      ObjetoExpedienteInsideMetadatosEnumeracionEstados estado, String usuario, boolean online)
      throws InSideServiceException {
    return setEstadoExpediente(identificador, estado, insideSigner.isActivo(), usuario, online);
  }

  @Override
  public ObjetoExpedienteInside altaCarpeta(ObjetoExpedienteInside expediente,
      ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada, String ruta,
      String usuario, boolean online) throws InSideServiceException {
    logger.debug("Inicio operacion altaCarpeta");

    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos destino =
        getCarpetaOExpedienteDeRuta(expediente, ruta);
    carpetaIndizada.setOrden(destino.getElementosIndizados().size() + 1);
    destino.getElementosIndizados().add(carpetaIndizada);
    expediente = modificaExpedienteInside(expediente, usuario, online);

    logger.debug("Fin operacion altaCarpeta");
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside borrarCarpeta(String identificadorExpediente,
      String identificadorCarpeta, String ruta) throws InSideServiceException {
    logger.debug("Inicio operacion borrarCarpeta");

    ObjetoExpedienteInside expediente = getExpediente(identificadorExpediente, false);
    logger.debug("expediente tras getExpediente(identificadorExpediente) : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));
    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos destino =
        getCarpetaOExpedienteDeRuta(expediente, ruta);
    logger.debug(
        "destino tras getCarpetaOExpedienteDeRuta(expediente,ruta); : " + destino.toString());

    ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaBorrar = null;

    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado : destino
        .getElementosIndizados()) {
      if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
        ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
            (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elementoIndizado;
        if (identificadorCarpeta.equals(carpetaIndizada.getIdentificadorCarpeta())) {
          carpetaBorrar = carpetaIndizada;
        }
      }
    }

    if (carpetaBorrar == null) {
      throw new InSideServiceRequestErrorException(
          "No existe la carpeta con identificador " + identificadorCarpeta + " en la ruta " + ruta);
    }

    destino.getElementosIndizados().remove(carpetaBorrar);

    int orden = 1;
    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado : destino
        .getElementosIndizados()) {
      elementoIndizado.setOrden(orden++);
    }
    logger.debug("expediente tras modificaExpedienteInside(expediente) : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));
    logger.debug("Fin operacion borrarCarpeta");
    return expediente;
  }

  @Override
  public ObjetoDocumentoInside altaDocumento(ObjetoDocumentoInside documento, String usuario,
      boolean online) throws InSideServiceException {
    logger.debug("Inicio operacion altaDocumento");

    validateObjetoInsideIdentificador(documento);
    validateObjetoInsideVersion(documento);
    documento = validate(documento);
    documento = converToRepository(documento);
    documento = insideStore.storeObject(documento, usuario, online);

    logger.debug("Fin operacion altaDocumento");
    return documento;
  }

  // TODO esto en una transaccion
  @Override
  public ObjetoDocumentoInside altaDocumento(ObjetoDocumentoInside documento,
      String identificadorExpediente, String ruta, String usuario, boolean online)
      throws InSideServiceException {
    logger.debug("Inicio operacion altaDocumento");

    ObjetoExpedienteInside expediente = getExpediente(identificadorExpediente, false);
    documento = altaDocumento(documento, usuario, online);
    ArrayList<ObjetoDocumentoInside> documentos = new ArrayList<ObjetoDocumentoInside>();
    documentos.add(documento);
    vincularDocumentosEnExpediente(expediente, documentos, ruta, usuario, online);

    logger.debug("Fin operacion altaDocumento");
    return documento;
  }

  @Override
  public ObjetoDocumentoInside validarDocumento(ObjetoDocumentoInside documento)
      throws InSideServiceException {
    logger.debug("Inicio operacion validarDocumento");
    logger.debug(
        "Parametro <documento> : " + InsideConverterDocumento.documentoInsideToString(documento));
    documento = validate(documento);
    logger.debug("documento tras validate(documento); : "
        + InsideConverterDocumento.documentoInsideToString(documento));
    logger.debug("Fin operacion validarDocumento");
    return documento;
  }

  @Override
  public ObjetoExpedienteInside validarExpediente(ObjetoExpedienteInside expediente)
      throws InSideServiceException {
    logger.debug("Inicio operacion validarExpediente");
    logger.debug("Parametro <expediente> : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));
    expediente = validate(expediente);
    logger.debug("expediente tras validate(expediente) : " + expediente);
    logger.debug("Fin operacion validarExpediente");
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside obtenerVisualizacionIndiceSiActivo(
      ObjetoExpedienteInside expediente, boolean estamparImagen, boolean estamparNombreOrganismo,
      List<String> lineasNombreOrganismo, boolean estamparPie, String textoPie,
      boolean expedienteExterno) throws InSideServiceException {
    logger.debug("Inicio operacion obtenerVisualizacionIndice");
    logger.debug("Parametro <expediente> : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));
    logger.debug("Parametro <estamparImagen> : " + estamparImagen);
    logger.debug("Parametro <estamparNombreOrganismo> : " + estamparNombreOrganismo);
    logger.debug("Parametro <lineasNombreOrganismo> : " + lineasNombreOrganismo);
    logger.debug("Parametro <estamparPie> : " + estamparPie);
    logger.debug("Parametro <textoPie> : " + textoPie);
    logger.debug("Parametro <expedienteExterno> : " + expedienteExterno);

    if (insideVisualizacion.isActivo()) {
      OpcionesVisualizacionIndice opciones = new OpcionesVisualizacionIndice();
      opciones.setEstamparImagen(estamparImagen);
      opciones.setEstamparNombreOrganismo(estamparNombreOrganismo);
      opciones.setLineasNombreOrganismo(lineasNombreOrganismo);
      opciones.setEstamparPie(estamparPie);
      opciones.setTextoPie(textoPie);
      opciones.setExterno(expedienteExterno);
      expediente = insideVisualizacion.expedienteConVisualizacionIndice(expediente, opciones);
      logger.debug(
          "expediente tras insideVisualizacion.expedienteConVisualizacionIndice(expediente, opciones); : "
              + InsideConverterExpediente.expedienteInsideToString(expediente));
    } else {
      logger.debug("El servicio de visualizacion no esta activo y no se invoca");
    }
    logger.debug("Fin operacion obtenerVisualizacionIndice");
    return expediente;

  }

  @Override
  public ObjetoExpedienteInside obtenerVisualizacionIndiceSiActivo(
      ObjetoExpedienteInside expediente) throws InSideServiceException {
    logger.debug("Inicio operacion obtenerVisualizacionIndice");
    logger.debug("Parametro <expediente> : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));

    if (insideVisualizacion.isActivo()) {
      expediente = insideVisualizacion.expedienteConVisualizacionIndice(expediente);
      logger.debug(
          "expediente tras insideVisualizacion.expedienteConVisualizacionIndice(expediente); : "
              + InsideConverterExpediente.expedienteInsideToString(expediente));
    } else {
      logger.debug("El servicio de visualizacion no esta activo y no se invoca");
    }

    logger.debug("Fin operacion obtenerVisualizacionIndice");
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside vincularDocumentosEnExpediente(String identificadorExpediente,
      List<String> identificadoresDocumentos, String ruta, String usuario, boolean online)
      throws InSideServiceException {
    logger.debug("Inicio operacion vincularDocumentosEnExpediente");

    ObjetoExpedienteInside expediente = getExpediente(identificadorExpediente, false);
    Collection<ObjetoDocumentoInside> documentos = new ArrayList<ObjetoDocumentoInside>();
    for (String indentificadorDocumento : identificadoresDocumentos) {
      documentos.add(getDocumento(indentificadorDocumento));
    }
    expediente = vincularDocumentosEnExpediente(expediente, documentos, ruta, usuario, online);
    logger.debug("Fin operacion vincularDocumentosEnExpediente");
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside vincularDocumentosEnExpedienteWithoutSave(
      ObjetoExpedienteInside expediente, Collection<ObjetoDocumentoInside> documentos, String ruta,
      String usuario, boolean online) throws InSideServiceException {
    logger.debug("Inicio operacion vincularDocumentosEnExpediente");

    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos destino =
        getCarpetaOExpedienteDeRuta(expediente, ruta);
    int orden = destino.getElementosIndizados().size();

    for (ObjetoDocumentoInside documento : documentos) {
      ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
          new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();

      // la version de mysql inferior a 5.6.X no trabaja con milisegundos
      GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
      calendar.set(Calendar.MILLISECOND, 0);

      documentoIndizado.setFechaIncorporacionExpediente(calendar);
      documentoIndizado.setIdentificadorDocumento(documento.getIdentificador());
      byte[] bytesToDigest = (documento.getContenido().getValorBinario() != null)
          ? documento.getContenido().getValorBinario()
          : documento.getContenido().getReferencia().getBytes();

      documentoIndizado
          .setValorHuella(InsideUtils.getStringDigest(this.messageDigest.digest(bytesToDigest)));
      documentoIndizado.setFuncionResumen(this.getFuncionResumenDocumento());
      documentoIndizado.setOrdenDocumentoExpediente(++orden);
      documentoIndizado.setOrden(++orden);
      destino.getElementosIndizados().add(documentoIndizado);
    }

    logger.debug("Fin operacion vincularDocumentosEnExpediente");
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside vincularDocumentosEnExpediente(ObjetoExpedienteInside expediente,
      Collection<ObjetoDocumentoInside> documentos, String ruta, String usuario, boolean online)
      throws InSideServiceException {

    expediente =
        vincularDocumentosEnExpedienteWithoutSave(expediente, documentos, ruta, usuario, online);

    expediente = modificaExpedienteInside(expediente, usuario, online);
    logger.debug("Fin operacion vincularDocumentosEnExpediente");
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside desvincularDocumentosEnExpediente(String identificadorExpediente,
      List<String> identificadoresDocumentos, String ruta, String usuario, boolean online)
      throws InSideServiceException {
    logger.debug("Inicio operacion desvincularDocumentosEnExpediente");
    ObjetoExpedienteInside expediente = getExpediente(identificadorExpediente, false);
    expediente = eliminarContenidoDeExpediente(expediente, identificadoresDocumentos,
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado.class, ruta);
    validarExpediente(expediente);
    logger.debug("Fin operacion desvincularDocumentosEnExpediente");
    return expediente;
  }

  @Override
  public ObjetoExpedienteInside cambiarUbicacionEnExpediente(String identificadorExpediente,
      List<String> identificadoresElementos, String rutaOrigen, String rutaDestino, String usuario,
      boolean online) throws InSideServiceException {
    logger.debug(
        "Inicio operacion cambiarUbicacionEnExpediente (identificadorExpediente, identificadoresDocumentos, ruta)");
    logger.debug("Parametro <identificadorExpediente> : " + identificadorExpediente);
    logger.debug("Parametro <identificadoresElementos> : "
        + InsideUtils.listaToString(identificadoresElementos.toArray(), ",", false, true));
    logger.debug("Parametro <rutaOrigen> : " + rutaOrigen);
    logger.debug("Parametro <rutaDestino> : " + rutaDestino);

    ObjetoExpedienteInside expediente = getExpediente(identificadorExpediente, false);

    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos rutaOrigenContenedora =
        getCarpetaOExpedienteDeRuta(expediente, rutaOrigen);
    logger.debug("rutaContenedora tras getCarpetaOExpedienteDeRuta(expediente,rutaOrigen); : "
        + rutaOrigenContenedora.toString());

    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos rutaDestinoContenedora =
        getCarpetaOExpedienteDeRuta(expediente, rutaDestino);
    logger
        .debug("rutaContenedora tras getCarpetaOExpedienteDeRuta(expediente,rutaDestinoOrigen); : "
            + rutaDestinoContenedora.toString());

    for (String identificadorElemento : identificadoresElementos) {
      logger.debug("buscando elementoIndizado con identificador: " + identificadorElemento);
      ObjetoExpedienteInsideIndiceContenidoElementoIndizado contenidoEncontrado =
          InsideObjectsUtils.getElementoIndizadoInList(identificadorElemento, null,
              rutaOrigenContenedora.getElementosIndizados());
      if (contenidoEncontrado != null) {
        logger.debug("encontrado elementoIndizado con identificador: " + identificadorElemento);
        rutaOrigenContenedora.getElementosIndizados().remove(contenidoEncontrado);
        logger.debug("eliminado elementoIndizado con identificador " + identificadorElemento
            + " de la ruta :" + rutaOrigen);
        rutaDestinoContenedora.getElementosIndizados().add(contenidoEncontrado);
        logger.debug("añadido elementoIndizado con identificador " + identificadorElemento
            + " a la ruta :" + rutaDestino);
      } else {
        throw new InSideServiceRequestErrorException(
            "No existe el elemento del tipo indicado con identificador " + identificadorElemento
                + " en la ruta " + rutaOrigen);
      }
    }

    logger.debug("expediente tras cambiar elementos de ruta: "
        + InsideConverterExpediente.expedienteInsideToString(expediente));

    InsideObjectsUtils
        .asignarOrdenAElementosIndizados(rutaDestinoContenedora.getElementosIndizados());

    logger.debug("expediente tras modificaExpedienteInside(expediente) : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));

    logger.debug("Fin operacion cambiarUbicacionEnExpediente");
    return expediente;
  }

  @Override
  public ObjetoDocumentoInside firmaServidorDocumento(ObjetoDocumentoInside documento,
      String usuario) throws InSideServiceException {
    return sign(documento, usuario);
  }

  @Override
  public ObjetoDocumentoInside getDocumento(String identificador) throws InSideServiceException {
    logger.debug("Inicio operacion getDocumento: " + identificador);
    ObjetoDocumentoInside documento =
        getObjetoInside(ObjetoDocumentoInside.class, identificador, null);
    logger.debug("Fin operacion getDocumento");
    return convertFromRepository(documento);
  }

  @Override
  public ObjetoDocumentoInsideContenido getDocumentoContenido(String identificador,
      byte[] bytesContenido) throws InSideServiceException {
    logger.debug("Inicio operacion getDocumentoContenido");
    ObjetoDocumentoInside documento =
        getObjetoInside(ObjetoDocumentoInside.class, identificador, null);
    documento = convertFromRepository(documento);
    ObjetoDocumentoInsideContenido contenido = null;
    try {
      contenido = InsideConverterDocumento.documentoInsideToObjetoDocumentoInsideContenido(
          documento, infoFirmaService, bytesContenido);
    } catch (InsideConverterException e) {
      throw new InSideServiceContentExtractException(
          "Error al extraer el contenido del documento firmado", e);
    }
    logger.debug("Fin operacion getDocumentoContenido");
    return contenido;
  }

  @Override
  public ObjetoDocumentoInsideMetadatos getMetadatosDocumento(String identificador)
      throws InSideServiceException {
    logger.debug("Inicio operacion getMetadatosDocumento " + identificador);
    ObjetoDocumentoInsideMetadatos metadatos =
        getObjetoInsideMetadatos(ObjetoDocumentoInside.class, identificador, null);
    logger.debug(
        "metadatos tras insideStore.getObjectMetadatos(ObjetoDocumentoInside.class,identificador); : "
            + metadatos.toString());
    logger.debug("Fin operacion getMetadatosDocumento");
    return metadatos;
  }

  @Override
  public Integer getNumeroBytesDocumento(String identificador) throws InSideServiceException {
    logger.debug("Inicio operacion getBytesDocumento");
    logger.debug("Parametro <identificador> : " + identificador);

    ObjetoDocumentoInside documento = getDocumento(identificador);
    logger.debug("documento tras getDocumento(identificador, version): "
        + InsideConverterDocumento.documentoInsideToString(documento));
    Integer bytes = InsideObjectsUtils.getBytesObjetoDocumentoInside(documento);
    logger
        .debug("bytes tras InsideObjectsUtils.getBytesObjetoDocumentoInside(documento): " + bytes);
    logger.debug("Fin operacion getBytesDocumento");
    return bytes;
  }

  @Override
  public ObjetoDocumentoInside cambiaMetadatosDocumento(String identificador,
      ObjetoDocumentoInsideMetadatos metadatos, String usuario, boolean online)
      throws InSideServiceException {
    logger.debug("Inicio operacion cambiaMetadatosDocumento");

    ObjetoDocumentoInside documento = getDocumento(identificador);
    documento.setMetadatos(metadatos);
    documento = modificaDocumento(documento, usuario, online);

    logger.debug("Fin operacion cambiaMetadatosDocumento");
    return documento;
  }

  @Override
  public ObjetoDocumentoInside modificaDocumento(ObjetoDocumentoInside documento, String usuario,
      boolean online) throws InSideServiceException {
    logger.debug("Inicio operacion modificaDocumento");

    documento = validate(documento);
    documento = converToRepository(documento);
    documento = insideStore.updateObject(documento, usuario, online);
    documento = convertFromRepository(documento);

    logger.debug("Fin operacion modificaDocumento");
    return documento;
  }

  @Override
  public ObjetoDocumentoInside modificaDocumento(ObjetoDocumentoInside documento,
      String identificadorExpediente, String ruta, String usuario, boolean online)
      throws InSideServiceException {
    logger.debug("Inicio operacion modificaDocumento");

    documento = validate(documento);
    documento = converToRepository(documento);
    documento = insideStore.updateObject(documento, usuario, online);
    documento = convertFromRepository(documento);
    ObjetoExpedienteInside expediente = getExpediente(identificadorExpediente, false);
    ArrayList<ObjetoDocumentoInside> documentos = new ArrayList<ObjetoDocumentoInside>();
    documentos.add(documento);
    vincularDocumentosEnExpediente(expediente, documentos, ruta, usuario, online);

    logger.debug("Fin operacion modificaDocumento");
    return documento;
  }

  /**
   * Obtiene la lista de firmas de tipo CSV que tiene un documento.
   * 
   * @param identificador identificador del documento
   * @param version versión del documento
   * @return Lista con las firmas CSV que tiene un documento. Si no tiene devuelve una lista vacía.
   */
  @Override
  public List<FirmaInside> getFirmasCSVDocumentoInside(String identificador)
      throws InSideServiceException {
    logger.debug("Inicio operacion getFirmasCSVDocumentoInside");
    logger.debug("Parametro <identificador> : " + identificador);

    ObjetoDocumentoInside documento = getDocumento(identificador);
    logger.debug("documento tras getDocumento(identificador, version): "
        + InsideConverterDocumento.documentoInsideToString(documento));
    List<FirmaInside> firmasCSV = InsideObjectsUtils.getFirmasCSV(documento.getFirmas());
    logger.debug(
        "firmasCSV tras InsideObjectsUtils.getFirmasCSV(documento.getFirmas()): " + firmasCSV);
    logger.debug("Fin operacion getFirmasCSVDocumentoInside");
    return firmasCSV;
  }

  @Override
  public ResultadoBusquedaInside<ObjetoExpedienteInsideMetadatos> buscarExpedientes(
      ConsultaInside consulta, int limite, int pagina) throws InSideServiceException {
    return insideStore.queryObjects(consulta, ObjetoExpedienteInside.class, limite, pagina);
  }

  @Override
  public ResultadoBusquedaInside<ObjetoDocumentoInsideMetadatos> buscarDocumentos(
      ConsultaInside consulta, int limite, int pagina) throws InSideServiceException {
    return insideStore.queryObjects(consulta, ObjetoDocumentoInside.class, limite, pagina);
  }

  @Override
  public List<ResultadoValidacionExpediente> validaExpedienteEniFile(byte[] expediente,
      List<OpcionValidacionExpediente> opciones, ObjetoExpedienteInside expedienteInside)
      throws InSideServiceException {

    // LLAMADA CLIENTEVALIDACIONES SUSTITUYE A LAS CUATRO ANTERIORES
    byte[] expedienteConDSSignature = null;
    try {
      expedienteConDSSignature =
          es.mpt.dsic.inside.service.util.XMLUtils.deFirmaBase64_A_DSSignature(expediente);
    } catch (IOException e) {
      logger.warn("Error validaExpedienteEniFile", e);
    }

    Validaciones validaciones = configurarValidacionesExpediente(false);
    return consumidorValidacionENI.validaExpedienteENI(expedienteConDSSignature, validaciones);
  }

  @Override
  public List<ResultadoValidacionExpediente> validaExpedienteEniFile(byte[] expediente,
      List<OpcionValidacionExpediente> opciones, ObjetoExpedienteInside expedienteInside,
      boolean validarSIA) throws InSideServiceException {
    byte[] expedienteConDSSignature = null;
    try {
      expedienteConDSSignature =
          es.mpt.dsic.inside.service.util.XMLUtils.deFirmaBase64_A_DSSignature(expediente);
    } catch (IOException e) {
      logger.warn("Error validaExpedienteEniFile", e);;
    }

    Validaciones validaciones = configurarValidacionesExpediente(validarSIA);

    return consumidorValidacionENI.validaExpedienteENI(expedienteConDSSignature, validaciones);
  }

  public void validacionClasificacionExp(List<OpcionValidacionExpediente> opciones,
      ObjetoExpedienteInside expedienteInside, List<ResultadoValidacionExpediente> resultados) {
    boolean valido = true;
    String mensaje = VALIDACION_CORRECTA;
    ResultadoValidacionExpediente resTOVE03 = new ResultadoValidacionExpediente();
    resTOVE03.setTipoValidacion(OpcionValidacionExpediente.TOVE_03);
    try {
      if (opciones.contains(OpcionValidacionExpediente.TOVE_03)) {
        insideSemanticValidationService.validarClasificacion(expedienteInside);
      }
    } catch (InsideSemanticValidationException e) {
      logger.warn(e);
      valido = false;
      mensaje = e.getMessage();
    }
    if (opciones.contains(OpcionValidacionExpediente.TOVE_03)) {
      resTOVE03.setValido(valido);
      resTOVE03.setMensaje(mensaje);
      resultados.add(resTOVE03);
    }
  }

  public void validacionUnidadesOrganicasExp(List<OpcionValidacionExpediente> opciones,
      ObjetoExpedienteInside expedienteInside, List<ResultadoValidacionExpediente> resultados) {
    boolean valido = true;
    String mensaje = VALIDACION_CORRECTA;
    ResultadoValidacionExpediente resTOVE02 = new ResultadoValidacionExpediente();
    resTOVE02.setTipoValidacion(OpcionValidacionExpediente.TOVE_02);
    try {
      if (opciones.contains(OpcionValidacionExpediente.TOVE_02)) {
        insideSemanticValidationService.validarUnidadOrganica(expedienteInside);
      }
    } catch (InsideSemanticValidationException e) {
      logger.warn(e);
      valido = false;
      mensaje = e.getMessage();
    }
    if (opciones.contains(OpcionValidacionExpediente.TOVE_02)) {
      resTOVE02.setValido(valido);
      resTOVE02.setMensaje(mensaje);
      resultados.add(resTOVE02);
    }
  }

  public void validacionSchemasExp(byte[] expediente, List<OpcionValidacionExpediente> opciones,
      List<ResultadoValidacionExpediente> resultados) throws TransformerFactoryConfigurationError {
    boolean valido = true;
    String mensaje = VALIDACION_CORRECTA;
    ResultadoValidacionExpediente resTOVE01 = new ResultadoValidacionExpediente();
    resTOVE01.setTipoValidacion(OpcionValidacionExpediente.TOVE_01);
    try {
      if (opciones.contains(OpcionValidacionExpediente.TOVE_01)) {
        Node nodoEni = XMLUtils.getNode(expediente, "ns7:expediente");
        String dataXml;
        if (nodoEni != null) {
          dataXml = XMLUtils.expedienteAdicionalWebToEni(expediente);
        } else {
          dataXml = XMLUtils.expedienteAdicionalWsToEni(expediente);
        }
        valido = insideEniValidationService.validarExpedienteDocumentoEniFile(dataXml.getBytes());
      }
    } catch (Exception e) {
      logger.warn(e);
      valido = false;
      mensaje = e.getMessage();
    }
    if (opciones.contains(OpcionValidacionExpediente.TOVE_01)) {
      resTOVE01.setValido(valido);
      resTOVE01.setMensaje(mensaje);
      resultados.add(resTOVE01);
    }
  }

  /*
   * Si opciones viene vacio es por el proceso de validar documento y desde ahi, si no tiene firma
   * puede ser un eni valido, pero si tiene firma y es distinta de csv hay que validarla. Si
   * opciones viene relleno, procede de otros procesos internos de inside y debe validar que sea
   * firmado y la validacion de la firma siempre que no sea csv
   */
  private boolean validarFirma(ObjetoDocumentoInside documentoInside,
      List<OpcionValidacionDocumento> opciones) throws InSideServiceException {
    boolean validarFirmaEnDocumento = true;

    ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion estadoElaboracionDocumento =
        documentoInside.getMetadatos().getEstadoElaboracion().getValorEstadoElaboracion();
    // si el estado de elaboracion de un documento es OTROS, debe permitirse el documento
    if (estadoElaboracionDocumento.value().equalsIgnoreCase(
        ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_99.value())) {
      if (!documentoInside.getFirmas().isEmpty()) {
        return validarFirmaEnDocumento = true;
      }
      return validarFirmaEnDocumento = false;
    }


    // Procesos internos inside
    if (!CollectionUtils.isEmpty(opciones)) {
      if (CollectionUtils.isEmpty(documentoInside.getFirmas())) {
        throw new InsideServiceInternalException(
            messageSource.getMessage("importarDocumento.error.docNoValido.sin.firmas", null,
                Locale.getDefault()) + " " + documentoInside.getIdentificador());
      } else {
        if (FirmaInsideTipoFirmaEnum.TF_01.value()
            .equals(documentoInside.getFirmas().get(0).getTipoFirma().value())) {
          validarFirmaEnDocumento = false;
        }
      }
    }
    // validar documentoENI
    else {
      validarFirmaEnDocumento = false;

      if (!CollectionUtils.isEmpty(documentoInside.getFirmas()) && !FirmaInsideTipoFirmaEnum.TF_01
          .value().equals(documentoInside.getFirmas().get(0).getTipoFirma().value())) {
        validarFirmaEnDocumento = true;
      }
    }
    return validarFirmaEnDocumento;

  }

  private Validaciones configurarValidacionesDocumento(ObjetoDocumentoInside documentoInside,
      List<OpcionValidacionDocumento> opciones) throws InSideServiceException {
    Validaciones validaciones = new Validaciones();
    validaciones.setValidaSchema(true);
    validaciones.setValidaDir3(true);

    boolean validarFirmaDocumento = validarFirma(documentoInside, opciones);
    validaciones.setValidaFirma(validarFirmaDocumento);

    validaciones.setValidaSIA(false);

    return validaciones;
  }

  private Validaciones configurarValidacionesExpediente(boolean validarSIA)
      throws InSideServiceException {
    Validaciones validaciones = new Validaciones();
    validaciones.setValidaSchema(true);
    validaciones.setValidaDir3(true);
    validaciones.setValidaFirma(true);
    validaciones.setValidaSIA(validarSIA);

    return validaciones;
  }

  @Override
  public List<ResultadoValidacionDocumento> validaDocumentoEniFile(byte[] documento,
      List<OpcionValidacionDocumento> opciones, ObjetoDocumentoInside documentoInside)
      throws InSideServiceException {

    // LLAMADA CLIENTEVALIDACIONES SUSTITUYE A LAS TRES ANTERIORES

    Validaciones validaciones = configurarValidacionesDocumento(documentoInside, opciones);
    List<ResultadoValidacionDocumento> resultados =
        consumidorValidacionENI.validaDocumentoENI(documento, validaciones);

    return resultados;
  }

  public void validacionFirmasDoc(List<OpcionValidacionDocumento> opciones,
      ObjetoDocumentoInside documentoInside, List<ResultadoValidacionDocumento> resultados) {
    boolean valido = true;
    String mensaje = VALIDACION_CORRECTA;

    ResultadoValidacionDocumento resTOVD03 = new ResultadoValidacionDocumento();
    resTOVD03.setTipoValidacion(OpcionValidacionDocumento.TOVD_03);
    try {
      if (opciones.contains(OpcionValidacionDocumento.TOVD_03)
          && checkIfNotCsvSign(documentoInside.getFirmas())) {
        validarFirma(InsideConverterDocumento.getFirmaElectronica(documentoInside));
      }
    } catch (InsideConverterException e) {
      logger.warn(e);
      valido = false;
      mensaje = e.getMessage();
    } catch (InSideServiceException e) {
      logger.warn(e);
      valido = false;
      mensaje = e.getMessage();
    }

    if (opciones.contains(OpcionValidacionDocumento.TOVD_03)) {
      resTOVD03.setValido(valido);
      resTOVD03.setMensaje(mensaje);
      resultados.add(resTOVD03);
    }
  }

  private boolean checkIfNotCsvSign(List<FirmaInside> list) throws InsideServiceInternalException {
    boolean checkIfNotCsvSign = false;
    if (CollectionUtils.isNotEmpty(list)) {
      if (!FirmaInsideTipoFirmaEnum.TF_01.value().equals(list.get(0).getTipoFirma().value()))
        checkIfNotCsvSign = true;
    } else {
      throw new InsideServiceInternalException("El documento no contiene firma");
    }
    return checkIfNotCsvSign;
  }

  public void validacionUnidadesOrganicasDoc(List<OpcionValidacionDocumento> opciones,
      ObjetoDocumentoInside documentoInside, List<ResultadoValidacionDocumento> resultados) {
    boolean valido = true;
    String mensaje = VALIDACION_CORRECTA;

    ResultadoValidacionDocumento resTOVD02 = new ResultadoValidacionDocumento();
    resTOVD02.setTipoValidacion(OpcionValidacionDocumento.TOVD_02);
    try {

      if (opciones.contains(OpcionValidacionDocumento.TOVD_02)) {

        insideSemanticValidationService.validarUnidadOrganica(documentoInside);
      }
    } catch (InsideSemanticValidationException e) {
      logger.error(e);
      valido = false;
      mensaje = e.getMessage();
    }

    if (opciones.contains(OpcionValidacionDocumento.TOVD_02)) {
      resTOVD02.setValido(valido);
      resTOVD02.setMensaje(mensaje);
      resultados.add(resTOVD02);
    }
  }

  public void validacionSchemasDoc(byte[] documento, List<OpcionValidacionDocumento> opciones,
      List<ResultadoValidacionDocumento> resultados) throws TransformerFactoryConfigurationError {
    boolean valido = true;
    String mensaje = VALIDACION_CORRECTA;

    ResultadoValidacionDocumento resTOVD01 = new ResultadoValidacionDocumento();
    resTOVD01.setTipoValidacion(OpcionValidacionDocumento.TOVD_01);
    try {

      if (opciones.contains(OpcionValidacionDocumento.TOVD_01)) {

        Node nodoEni = XMLUtils.getNode(documento, "ns5:documento");
        String dataXml;
        if (nodoEni != null) {
          dataXml = XMLUtils.documentoAdicionalWebToEni(documento);
        } else {
          dataXml = XMLUtils.documentoAdicionalWsToEni(documento);
        }
        valido = insideEniValidationService.validarExpedienteDocumentoEniFile(dataXml.getBytes());

      }
    } catch (Exception e) {
      logger.error(e);
      valido = false;
      mensaje = e.getMessage();
    }

    if (opciones.contains(OpcionValidacionDocumento.TOVD_01)) {
      resTOVD01.setValido(valido);
      resTOVD01.setMensaje(mensaje);
      resultados.add(resTOVD01);
    }
  }

  /**
   * Devuelve la visualización de un documento Inside.
   * 
   * @param documento Documento Inside que se quiere visualizar
   * @param estamparImagen Indica si se desea estampar el logo del Ministerio.
   * @param estamparNombreOrganismo Indica si se quiere estampar el nombre del organismo
   * @param lineasNombreOrganismo Nombre del organismo, separado en las líneas en que se desee que
   *        se estampen.
   * 
   */
  @Override
  public ResultadoVisualizacionDocumento visualizaDocumentoInside(ObjetoDocumentoInside documento,
      boolean estamparImagen, boolean estamparNombreOrganismo, List<String> lineasNombreOrganismo,
      boolean estamparPie, String textoPie, byte[] bytesContenido) throws InSideServiceException {

    logger.debug("Inicio operacion visualizaDocumentoInside");
    logger.debug(
        "Parametro <documento> : " + InsideConverterDocumento.documentoInsideToString(documento));
    logger.debug("Parametro <estamparImagen> : " + estamparImagen);
    logger.debug("Parametro <estamparNombreOrganismo> : " + estamparNombreOrganismo);
    if (lineasNombreOrganismo != null) {
      logger.debug("Parametro <lineasNombreOrganismo> : "
          + InsideUtils.listaToString(lineasNombreOrganismo.toArray(), ",", false, true));
    }

    logger.debug("Parametro <estamparPie> : " + estamparPie);
    logger.debug("Parametro <textoPie> : " + textoPie);

    OpcionesVisualizacionIndice opciones = new OpcionesVisualizacionIndice();
    opciones.setEstamparImagen(estamparImagen);
    opciones.setEstamparNombreOrganismo(estamparNombreOrganismo);
    opciones.setLineasNombreOrganismo(lineasNombreOrganismo);
    opciones.setEstamparPie(estamparPie);
    opciones.setTextoPie(textoPie);

    ResultadoVisualizacionDocumento resultadoVisualizacionDocumento =
        insideVisualizacion.visualizacionDocumento(documento, opciones, bytesContenido);
    logger.debug("resultadoVisualizacionDocumento : " + resultadoVisualizacionDocumento);
    logger.debug("Fin operacion visualizaDocumentoInside");
    return resultadoVisualizacionDocumento;

  }

  /**
   * Devuelve la visualización de un documento Inside.
   * 
   * @param documento Documento Inside que se quiere visualizar
   */
  @Override
  public ResultadoVisualizacionDocumento visualizaDocumentoInside(ObjetoDocumentoInside documento,
      byte[] bytesContenido) throws InSideServiceException {

    logger.debug("Inicio operacion visualizaDocumentoInside");
    logger.debug(
        "Parametro <documento> : " + InsideConverterDocumento.documentoInsideToString(documento));

    ResultadoVisualizacionDocumento resultadoVisualizacionDocumento =
        insideVisualizacion.visualizacionDocumento(documento, bytesContenido);
    logger.debug("resultadoVisualizacionDocumento : " + resultadoVisualizacionDocumento);
    logger.debug("Fin operacion visualizaDocumentoInside");
    return resultadoVisualizacionDocumento;

  }

  /**
   * Elimina una lista de elementos indizados con identificadores determinados de un expediente. La
   * ruta donde se encuentren se ha de pasar como parámetro
   * 
   * @param expediente Expediente del que se quiere eliminar elementos indizados.
   * @param identificadoresContenido identificador del elemento indizado a eliminar: - identificador
   *        del documento en caso del documento - identificador del expediente vinculado o importado
   *        - identificador de la carpeta
   * @param Class <T> clase del elemento a eliminar, si es null no se tendrá en cuenta.
   * @param ruta Ruta donde se encuentra el elemento indizado.
   * @return El expediente modificado
   * @throws InSideServiceException
   */
  private <T extends ObjetoExpedienteInsideIndiceContenidoElementoIndizado> ObjetoExpedienteInside eliminarContenidoDeExpediente(
      ObjetoExpedienteInside expediente, List<String> identificadoresContenido, Class<T> clase,
      String ruta) throws InSideServiceException {
    logger
        .debug("Inicio operacion desvincularDocumentosEnExpediente (expediente, documentos, ruta)");
    logger.debug("Parametro <expediente> : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));
    logger.debug("Parametro <identificadoresContenido> : "
        + InsideUtils.listaToString(identificadoresContenido.toArray(), ",", false, true));
    logger.debug("Parametro <ruta> : " + ruta);

    ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos rutaContenedora =
        getCarpetaOExpedienteDeRuta(expediente, ruta);
    logger.debug("rutaContenedora tras getCarpetaOExpedienteDeRuta(expediente,ruta); : "
        + rutaContenedora.toString());

    for (String identificadorContenido : identificadoresContenido) {
      logger.debug("buscando elementoIndizado con identificador: " + identificadorContenido);
      ObjetoExpedienteInsideIndiceContenidoElementoIndizado contenidoEncontrado =
          InsideObjectsUtils.getElementoIndizadoInList(identificadorContenido, clase,
              rutaContenedora.getElementosIndizados());
      if (contenidoEncontrado != null) {
        logger.debug("encontrado elementoIndizado con identificador: " + identificadorContenido);
        rutaContenedora.getElementosIndizados().remove(contenidoEncontrado);
      } else {
        throw new InSideServiceRequestErrorException(
            "No existe el elemento del tipo indicado con identificador " + identificadorContenido
                + " en la ruta " + ruta);
      }
    }

    logger.debug("expediente tras eliminar elementoIndizado: "
        + InsideConverterExpediente.expedienteInsideToString(expediente));

    InsideObjectsUtils.asignarOrdenAElementosIndizados(rutaContenedora.getElementosIndizados());

    logger.debug("expediente tras InsideObjectsUtils.asignarOrdenAElementosIndizados(expediente) : "
        + InsideConverterExpediente.expedienteInsideToString(expediente));

    return expediente;

  }

  /**
   * Devuelve la carpeta o el expediente indizado de un indice en la ruta pasada como parámetro
   * 
   * @param objetoExpedienteInsideIndice
   * @param ruta
   * @return
   * @throws InSideServiceRequestErrorException
   */
  private ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos getCarpetaOExpedienteDeRuta(
      ObjetoExpedienteInside expediente, String ruta) throws InSideServiceRequestErrorException {
    ObjetoExpedienteInsideIndiceContenidoElementoIndizado ret =
        getElementoIndizado(expediente.getIndice(), ruta);

    if (ret instanceof ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) {
      return (ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) ret;
    }
    throw new InSideServiceRequestErrorException(
        "No se encuentra carpeta o expediente en la ruta " + ruta);

  }

  /**
   * Devueleve el elemento del indice que se corresponde con una ruta
   * 
   * @param objetoExpedienteInsideIndice
   * @param ruta
   * @return
   */
  private ObjetoExpedienteInsideIndiceContenidoElementoIndizado getElementoIndizado(
      ObjetoExpedienteInsideIndice objetoExpedienteInsideIndice, String ruta) {
    if (ruta == null || ruta.contentEquals("")) {
      ruta = "/";
    }
    if (ruta.startsWith("/")) {
      ruta = ruta.substring(1);
    }
    String[] path = ruta.split("/");
    if (StringUtils.isEmpty(ruta) || path.length == 0) {
      return objetoExpedienteInsideIndice.getIndiceContenido();
    }
    return getElementoIndizado(
        objetoExpedienteInsideIndice.getIndiceContenido().getElementosIndizados(), path, 0);
  }

  private ObjetoExpedienteInsideIndiceContenidoElementoIndizado getElementoIndizado(
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados, String[] path,
      int nivel) {
    Assert.notNull(elementosIndizados);
    Assert.notNull(path);
    Assert.notNull(nivel);
    if (nivel > path.length) {
      throw new IndexOutOfBoundsException("El nivel (" + nivel
          + ") no puede ser superior a la longitud de path (" + path.length + ")");
    }
    String elemNombre = path[nivel];
    String compare = "";

    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento : elementosIndizados) {
      if (elemento instanceof ObjetoExpedienteInsideIndiceContenido) {
        ObjetoExpedienteInsideIndiceContenido indiceIndizado =
            (ObjetoExpedienteInsideIndiceContenido) elemento;
        compare = indiceIndizado.getIdentificadorExpedienteAsociado();
      } else if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
        ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
            (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elemento;
        compare = carpetaIndizada.getIdentificadorCarpeta();
      } else if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
            (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elemento;
        compare = documentoIndizado.getIdentificadorDocumento();
      }
      if (StringUtils.equals(compare, elemNombre)) {
        if ((nivel + 1) == path.length) {
          return elemento;
        } else if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) {
          return getElementoIndizado(
              ((ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) elemento)
                  .getElementosIndizados(),
              path, nivel + 1);
        }
      }
    }

    return null;
  }

  /**
   * Nos aseguramos de que los objetos siempre se den de alta con un identificador
   * 
   * @param objeto
   */
  @Override
  public void validateObjetoInsideIdentificador(ObjetoInside<?> objeto)
      throws InSideServiceException {
    if (objeto.getIdentificador() == null) {
      objeto.setIdentificador(
          InsideServiceIdentifierGenerator.generateObjetoInsideIdentificador(objeto));
      logger.debug("El objeto " + objeto.getClass().getSimpleName()
          + " no tenía identificador, le asigno el: " + objeto.getIdentificador());
    }
  }

  /**
   * Valida que un objeto inside tenga versón
   * 
   * @param objeto
   */
  private void validateObjetoInsideVersion(ObjetoInside<?> objeto) {
    if ((objeto instanceof ObjetoInsideVersionable)
        && ((ObjetoInsideVersionable) objeto).getVersion() == null) {
      ((ObjetoInsideVersionable) objeto).setVersion(ObjetoInsideVersion.createFirstVersion());
      logger.debug("Creada primera versión para objeto de tipo " + objeto.getClass().getSimpleName()
          + " con identificador " + objeto.getIdentificador());
    } else if (objeto instanceof ObjetoDocumentoInside) {
      ((ObjetoDocumentoInside) objeto).setVersion(ObjetoInsideVersion.createFirstVersion());
    }

  }

  /**
   * Recupera un objeto del repositorio, si la version es inferior a 1 se llama al getObject (sin
   * version)
   * 
   * @param tipo
   * @param identificador
   * @param version
   * @return
   * @throws InsideServiceStoreException
   * @throws InsideStoreObjectNotFoundException
   * @throws InsideStoreObjectNoActiveException
   */
  @SuppressWarnings("unchecked")
  private <T extends ObjetoInside<?>> T getObjetoInside(final Class<T> tipo,
      final String identificador, final Integer version) throws InsideServiceStoreException,
      InsideStoreObjectNotFoundException, InsideStoreObjectNoActiveException {
    ObjetoInside<?> retorno;
    if (version != null && version >= 1) {
      retorno = insideStore.getObjectVersion(tipo, identificador, version);
    } else {
      retorno = insideStore.getObject(tipo, identificador);
    }
    return (T) retorno;
  }

  private <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> K getObjetoInsideMetadatos(
      Class<T> tipo, String identificador, Integer version)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException {
    if (version != null && version >= 1) {
      return insideStore.getObjectMetadatos(tipo, identificador, version);
    } else {
      return insideStore.getObjectMetadatos(tipo, identificador);
    }
  }

  private <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> T validate(T objeto)
      throws InSideServiceObjectDefinitionNotFoundException, InSideServiceValidationException,
      InSideServiceException {
    @SuppressWarnings("unchecked")
    T objetoValidado = (T) objectDefinitions.getValidator(objeto.getClass()).validate(objeto);
    return objetoValidado;
  }

  private <T extends ObjetoInside<?>> T converToRepository(T objeto)
      throws InSideServiceObjectDefinitionNotFoundException, InsideServiceObjectConverterException {
    @SuppressWarnings("unchecked")
    T objetoConvertido = (T) objectDefinitions.getConverter(objeto.getClass()).toRepository(objeto);
    return objetoConvertido;
  }

  private <T extends ObjetoInside<?>> T convertFromRepository(T objeto)
      throws InSideServiceObjectDefinitionNotFoundException, InsideServiceObjectConverterException {
    @SuppressWarnings("unchecked")
    T objetoConvertido =
        (T) objectDefinitions.getConverter(objeto.getClass()).fromRepository(objeto);
    return objetoConvertido;
  }

  private <T extends ObjetoInside<?>> T sign(T objeto, String usuario)
      throws InSideServiceException {
    @SuppressWarnings("unchecked")
    T objetoFirmado = (T) objectDefinitions.getSigner(objeto.getClass()).sign(objeto, usuario);
    return objetoFirmado;
  }

  public String getFuncionResumenDocumento() {
    return this.funcionResumenDocumentos;
  }

  /**
   * Establece el Algoritmo que se utiliza para calcular la huella digital de los documentos para el
   * indice del Expediente electrónico
   * 
   * @param funcionResumen
   * @throws NoSuchAlgorithmException
   */
  @Required
  public void setFuncionResumenDocumentos(String funcionResumen) throws NoSuchAlgorithmException {
    logger.info(
        "Estableciendo función de Resumen para documentos del índice a '" + funcionResumen + "'");
    this.messageDigest = MessageDigest.getInstance(funcionResumen);
    this.funcionResumenDocumentos = funcionResumen;
  }

  @Override
  public void deleteDocument(String identificador) throws InSideServiceException {
    ObjetoDocumentoInside objeto =
        getObjetoInside(ObjetoDocumentoInside.class, identificador, null);
    checkElementosAsociados(objeto, identificador);
    deleteRepository(objeto);
    insideStore.deleteDocument(identificador);
  }

  @Override
  public void deleteExpedient(String identificador) throws InSideServiceException {
    ObjetoExpedienteInside objeto =
        getObjetoInside(ObjetoExpedienteInside.class, identificador, null);
    checkElementosAsociados(objeto, identificador);
    deleteRepository(objeto);
    insideStore.deleteExpedient(identificador);
  }

  public void checkElementosAsociados(ObjetoInside objeto, String identificador)
      throws InSideServiceException {
    List<ObjetoExpedienteInside> expVinculados =
        getExpedientesAsociadosDocumento(objeto.getClass(), identificador);
    if (!expVinculados.isEmpty()) {
      StringBuilder tmpBuff = new StringBuilder("");
      for (ObjetoExpedienteInside objExpInside : expVinculados) {
        if (!"".equals(tmpBuff.toString())) {
          tmpBuff.append(",");
        }
        tmpBuff.append(objExpInside.getIdentificador());
      }
      throw new InsideStoreObjectVinculatedException(tmpBuff.toString());
    }
  }

  @Override
  public <T extends ObjetoInside<?>> List<ObjetoExpedienteInside> getExpedientesAsociadosDocumento(
      Class<T> tipo, String identificador) throws InSideServiceException {
    return insideStore.getExpedientesAsociados(tipo, identificador);
  }

  @Override
  public void firmarIndiceExpediente(ObjetoExpedienteInside expediente, WSCredentialInside info,
      String usuario) throws InSideServiceException {

    try {
      TipoExpediente tExp = InsideConverterExpediente.expedienteInsideToEni(expediente, null);
      tExp.getIndice().setFirmas(null);
      String data = marshallDataExpedient(tExp);

      data = es.mpt.dsic.inside.service.util.XMLUtils.incluirNamespacesParaValidarFirma(data);

      String idNodeToSign = "EXP_INDICE_CONTENIDO" + expediente.getIdentificador();
      byte[] firmaIndice = insideSigner.firmarFicheroWhitPropertie(data.getBytes(), null,
          "XAdES Enveloped", "IMPLICIT", idNodeToSign, info);
      String nombreUsuario = info != null ? info.getIdaplicacion() : usuario;
      ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor = new ObjetoAuditoriaFirmaServidor(
          Constantes.ELEMENTO_EXPEDIENTE, expediente.getIdentificador(), nombreUsuario);
      saveAuditoriaFirmaServidor(objetoAuditoriaFirmaServidor);
      // extraemos el nodo ds:Signature
      logger.debug("indice firmado:" + Base64.encodeBase64String(firmaIndice));
      // String nodofirma =
      // es.mpt.dsic.inside.service.util.XMLUtils.signatureString("expediente/indice/Signature",
      // firmaIndice);
      String nodofirma = es.mpt.dsic.inside.service.util.XMLUtils
          .signatureString("//*[local-name()='Signature']", firmaIndice);

      logger.debug("incluimos nodo signature:" + nodofirma);
      Base64 bs64 = new Base64();
      byte[] firmaBase64 =
          bs64.encode(nodofirma.getBytes(es.mpt.dsic.inside.service.util.XMLUtils.UTF8_CHARSET));
      // establecerFirmaExpedienteANodoFirmaBase64(expediente, "FIRMA_0",
      // FirmaInsideTipoFirmaEnum.TF_02, firmaBase64, expediente.getIdentificador());
      establecerFirmaExpedienteANodoFirmaBase64(expediente, "FIRMA_0",
          FirmaInsideTipoFirmaEnum.TF_03, firmaBase64, expediente.getIdentificador());
    } catch (InSideServiceSignerException e) {
      throw e;
    } catch (Exception e) {
      throw new InsideServiceInternalException("Se ha producido un error al firmar el índice", e);
    }
  }

  private String marshallDataExpedient(Object datos) throws JAXBException {
    StringWriter sw = new StringWriter();
    if (datos instanceof TipoExpediente) {
      JAXBContext context = JAXBContext.newInstance(TipoExpediente.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

      JAXBElement<TipoExpediente> jx = new JAXBElement<TipoExpediente>(
          new QName("http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e",
              "expediente", "exp"),
          TipoExpediente.class, (TipoExpediente) datos);
      marshaller.marshal(jx, sw);
    }
    if (datos instanceof TipoExpedienteInsideConMAdicionales) {
      JAXBContext context = JAXBContext.newInstance(TipoExpedienteInsideConMAdicionales.class);
      Marshaller marshaller = context.createMarshaller();
      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
      JAXBElement<TipoExpedienteInsideConMAdicionales> jx =
          new JAXBElement<TipoExpedienteInsideConMAdicionales>(
              new QName("https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", "expediente",
                  "insidews"),
              TipoExpedienteInsideConMAdicionales.class,
              (TipoExpedienteInsideConMAdicionales) datos);
      marshaller.marshal(jx, sw);
    }
    return sw.toString();
  }

  @Override
  public void establecerFirmaExpedienteANodoFirmaBase64(ObjetoExpedienteInside expediente,
      String idFirma, FirmaInsideTipoFirmaEnum tipoFirma, byte[] firmaBase64, String ref) {
    FirmaInside firmaInside = new FirmaInside();
    firmaInside.setIdentificadorEnDocumento(idFirma);
    firmaInside.setTipoFirma(tipoFirma);
    firmaInside.setRef("#EXP_INDICE_CONTENIDO" + ref);

    // ContenidoFirmaCertificadoDsSignatureInside firmaConCertificado = new
    // ContenidoFirmaCertificadoDsSignatureInside();
    ContenidoFirmaCertificadoContenidoBinarioInside firmaConCertificado =
        new ContenidoFirmaCertificadoContenidoBinarioInside();
    firmaConCertificado.setValorBinario(firmaBase64);
    firmaConCertificado.setMime("application/xml");

    firmaInside.setContenidoFirma(firmaConCertificado);

    // Sólo guardamos la última firma, que es la que acabamos de hacer.
    checkSignList(expediente);
    expediente.getIndice().getFirmas().add(firmaInside);

  }

  @Override
  public void establecerFirmaExpediente(ObjetoExpedienteInside expediente, String idFirma,
      FirmaInsideTipoFirmaEnum tipoFirma, byte[] firma, String ref) {
    // Metemos la firma en el expediente
    FirmaInside firmaInside = new FirmaInside();
    firmaInside.setIdentificadorEnDocumento(idFirma);
    firmaInside.setTipoFirma(tipoFirma);
    firmaInside.setRef("#EXP_INDICE_CONTENIDO" + ref);

    ContenidoFirmaCertificadoDsSignatureInside firmaConCertificado =
        new ContenidoFirmaCertificadoDsSignatureInside();
    firmaConCertificado.setValorBinario(firma);
    firmaConCertificado.setMime("application/xml");

    firmaInside.setContenidoFirma(firmaConCertificado);

    // Sólo guardamos la última firma, que es la que acabamos de hacer.
    checkSignList(expediente);
    expediente.getIndice().getFirmas().add(firmaInside);
  }

  public void checkSignList(ObjetoExpedienteInside expediente) {
    if (expediente.getIndice().getFirmas() != null)
      expediente.getIndice().getFirmas().clear();
    else
      expediente.getIndice().setFirmas(new ArrayList<FirmaInside>());
  }

  @Override
  public ObjetoDocumentoInside documentoConversionToInside(
      TipoDocumentoConversionInside documentoConversion, InfoFirmaService infoFirmaService,
      byte[] contenido, boolean firmar, WSCredentialInside info) throws InsideConverterException {
    try {
      if (firmar) {
        String formatoFichero = InsideMimeUtils.getMimeType(documentoConversion.getContenido());
        String formatoFirma = InsideUtils.getTipoFirmaByMimeAFirmar(formatoFichero);
        byte[] firma = insideSigner.firmarFichero(documentoConversion.getContenido(), null,
            formatoFirma, null, info);
        ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor =
            new ObjetoAuditoriaFirmaServidor(Constantes.ELEMENTO_DOCUMENTO,
                documentoConversion.getMetadatosEni().getIdentificador(), info.getIdaplicacion());
        saveAuditoriaFirmaServidor(objetoAuditoriaFirmaServidor);
        documentoConversion.setContenido(firma);
        documentoConversion.setFirmadoConCertificado(true);
      }
      return InsideConverterDocumento.documentoConversionToInside(documentoConversion,
          infoFirmaService, contenido);
    } catch (InSideServiceSignerException e) {
      throw new InsideConverterException(e);
    } catch (InsideConverterUtilsException e) {
      throw new InsideConverterException(e);
    }
  }

  @Override
  public boolean permisoObjetoUnidad(ObjetoInside<?> obj, String identificadorUsuario,
      boolean online) throws InSideServiceException {
    return insideStore.validarObjetoUnidad(obj, identificadorUsuario, online);
  }

  @Override
  public List<ObjetoInsideExpedienteUnidad> getExpedientesUnidad(ObjetoInsideUsuario usuario,
      boolean soloUnidadActiva) throws InSideServiceException {
    if (usuario == null || StringUtils.isBlank(usuario.getNif())) {
      throw new InsideServiceInternalException("Debe especificar usuario");
    }
    return insideStore.getExpedientesUnidad(usuario.getNif(), soloUnidadActiva);
  }

  @Override
  public List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadAutocompleter(
      ObjetoInsideUsuario usuario, boolean soloUnidadActiva) throws InSideServiceException {
    if (usuario == null || StringUtils.isBlank(usuario.getNif())) {
      throw new InsideServiceInternalException("Debe especificar usuario");
    }
    return insideStore.getExpedientesUnidadAutocompleter(usuario.getNif(), soloUnidadActiva);
  }

  @Override
  public List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadPorMetadatos(String nif,
      TipoMetadatosAdicionales tipoMetadatosAdicionales) throws InSideServiceException {
    if (nif == null || StringUtils.isBlank(nif)) {
      throw new InsideServiceInternalException("Debe especificar usuario");
    }
    return insideStore.getExpedientesUnidadPorMetadatos(nif, tipoMetadatosAdicionales);
  }

  @Override
  public List<ObjetoInsideDocumentoUnidad> getDocumentosUnidad(ObjetoInsideUsuario usuario,
      boolean soloUnidadActiva) throws InSideServiceException {
    if (usuario == null || StringUtils.isBlank(usuario.getNif())) {
      throw new InsideServiceInternalException("Debe especificar usuario");
    }
    return insideStore.getDocumentosUnidad(usuario.getNif(), soloUnidadActiva);
  }

  @Override
  public ObjetoExpedienteToken getTokenByUsuarioExpediente(ObjetoExpedienteToken usuarioToken)
      throws InSideServiceException {
    return insideStore.getTokenByUsuarioExpediente(usuarioToken);
  }

  @Override
  public ObjetoExpedienteToken getTokenByData(ObjetoExpedienteToken usuarioToken)
      throws InSideServiceException {
    return insideStore.getTokenByData(usuarioToken);
  }

  @Override
  public void saveToken(ObjetoExpedienteToken usuarioToken) throws InSideServiceException {
    insideStore.saveToken(usuarioToken);
  }

  @Override
  public void saveAuditoriaToken(ObjetoAuditoriaToken objectAuditoriaToken)
      throws InSideServiceException {
    insideStore.saveAuditoriaToken(objectAuditoriaToken);
  }

  @Override
  public List<ObjetoInsideUsuario> getUsuarios() throws InSideServiceException {
    return insideStore.getUsuarios();
  }

  @Override
  public ResultadoBusquedaUsuario getUsuarios(ObjetoFilterPageRequest data)
      throws InSideServiceException {
    return insideStore.getUsuarios(data);
  }


  @Override
  public ObjetoInsideUsuario altaUsuario(ObjetoInsideUsuario data) throws InSideServiceException {
    return insideStore.altaUsuario(data);
  }

  @Override
  public ObjetoInsideUsuario getUsuario(String nif) throws InSideServiceException {
    return insideStore.getUsuario(nif);
  }

  @Override
  public ObjetoInsideUsuario bajaUsuario(ObjetoInsideUsuario data) throws InSideServiceException {
    return insideStore.bajaUsuario(data);
  }

  @Override
  public List<ObjetoInsideAplicacion> getAplicaciones() throws InSideServiceException {
    return insideStore.getAplicaciones();
  }

  @Override
  public ObjetoInsideAplicacion altaAplicacion(ObjetoInsideAplicacion data)
      throws InSideServiceException {
    return insideStore.altaAplicacion(data);
  }

  @Override
  public ObjetoInsideAplicacion bajaAplicacion(ObjetoInsideAplicacion data)
      throws InSideServiceException {
    return insideStore.bajaAplicacion(data);
  }

  @Override
  public ObjetoInsideUnidad asociarUnidadAplicacion(String idUnidad, String numeroProcedimiento,
      String idAplicacion) throws InSideServiceException {
    return insideStore.altaUnidadAplicacion(idUnidad, numeroProcedimiento, idAplicacion);
  }

  @Override
  public void eliminarUnidadAplicacion(String idUnidad, String numeroProcedimiento,
      String idAplicacion) throws InSideServiceException {
    insideStore.deleteUnidadAplicacion(idUnidad, numeroProcedimiento, idAplicacion);
  }

  @Override
  public ObjetoInsideUnidad asociarUnidadUsuario(String idUnidad, String numeroProcedimiento,
      String idUsuarios) throws InSideServiceException {
    return insideStore.altaUnidadUsuario(idUnidad, numeroProcedimiento, idUsuarios);
  }

  @Override
  public void eliminarUnidadUsuario(String idUnidad, String numeroProcedimiento, String idUsuarios)
      throws InSideServiceException {
    insideStore.deleteUnidadUsuario(idUnidad, numeroProcedimiento, idUsuarios);
  }

  @Override
  public List<ObjetoInsideUnidad> getUnidadesAplicacion(String idAplicacion, boolean todas)
      throws InSideServiceException {
    return insideStore.getUnidadesAplicacion(idAplicacion, todas);
  }

  @Override
  public List<ObjetoInsideUnidad> getUnidadesUsuario(String idUsuarios, boolean todas)
      throws InSideServiceException {
    return insideStore.getUnidadesUsuario(idUsuarios, todas);
  }


  public ObjetoInsideRol getRolUnidadUsuario(String codigoUnidad, String nif)
      throws InSideServiceException {
    // recupero el usuario
    List<Criterion> criteriasUsuario = new ArrayList<Criterion>();
    criteriasUsuario.add(Restrictions.eq("nif", nif));
    UsuarioInside usuarioInside =
        (UsuarioInside) insideServiceJta.findObjeto(UsuarioInside.class, criteriasUsuario);

    // recupero la unidadorganica
    List<Criterion> criteriasUnidad = new ArrayList<Criterion>();
    criteriasUnidad.add(Restrictions.eq("codigoUnidadOrganica", codigoUnidad));
    UnidadOrganica unidadOrganica =
        (UnidadOrganica) insideServiceJta.findObjeto(UnidadOrganica.class, criteriasUnidad);

    // recupero la unidadusuario
    List<Criterion> criterias = new ArrayList<Criterion>();
    criterias.add(Restrictions.eq("unidad.id", unidadOrganica.getId()));
    criterias.add(Restrictions.eq("idUsuario", usuarioInside.getId()));

    UnidadUsuario usuarioUnidad =
        (UnidadUsuario) insideServiceJta.findObjeto(UnidadUsuario.class, criterias);

    // recupero el rol
    List<Criterion> criteriasRol = new ArrayList<Criterion>();
    criteriasRol.add(Restrictions.eq("id", usuarioUnidad.getIdRol()));
    InsideRol insideRol = (InsideRol) insideServiceJta.findObjeto(InsideRol.class, criteriasRol);

    return InsideServiceStoreHibernateConverterInsideRol.toInside(insideRol);
  }


  @Override
  public boolean comprobarUnidadesOrganicasActivasUsuario(Object usuario)
      throws InSideServiceException {
    return insideStore.comprobarUnidadesOrganicasActivasUsuario(usuario);
  }

  @Override
  public boolean checkAssociatedViewsExpedient(String identificadorExpediente)
      throws InSideServiceException {
    boolean existView = false;
    List<ObjetoInsideExpedienteUnidad> listViews =
        insideStore.getViewsExpedient(identificadorExpediente);

    if (!CollectionUtils.isEmpty(listViews))
      existView = true;

    return existView;
  }

  @Override
  public List<ObjetoInsideExpedienteUnidad> getViewsExpedient(String identificadorExpediente)
      throws InSideServiceException {
    return insideStore.getViewsExpedient(identificadorExpediente);
  }

  @Override
  public ObjetoInsideAplicacion actualizarCredencialesEeetuilApp(String app,
      ObjetoCredencialEeutil credential) throws InSideServiceException {
    return insideStore.actualizarCredencialesEeetuilApp(app, credential);
  }

  @Override
  public void guardarRespuestaRemisionJusticiaExpediente(
      ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia,
      String identificadorExpediente, String version) throws InSideServiceException {
    insideStore.insertRespuestaRemisionJusticia(objectInsideRespuestaEnvioJusticia,
        identificadorExpediente, version);
  }

  @Override
  public void guardarRespuestaRemisionJusticiaExpedienteNoInside(
      ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia,
      String identificadorExpediente, String version) throws InSideServiceException {
    insideStore.insertRespuestaRemisionJusticiaExpedienteNoInside(
        objectInsideRespuestaEnvioJusticia, identificadorExpediente, version);
  }

  @Override
  public ObjetoInsideUnidadAplicacionEeutil getApplicationEeutilByUser(ObjetoInsideUsuario user)
      throws InSideServiceException {
    return insideStore.getApplicationEeutilByUser(user);
  }

  @Override
  public Map<String, String> obtenerDocsExpInside(
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> list, String path) {
    Map<String, String> retorno = new HashMap<String, String>();
    if (CollectionUtils.isNotEmpty(list)) {
      for (Object dato : list) {
        if (dato instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
          retorno.put(((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) dato)
              .getIdentificadorDocumento(), path);
        } else if (dato instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
          String auxPath = path;
          if (!path.endsWith("/")) {
            auxPath += "/";
          }
          auxPath += ((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) dato)
              .getIdentificadorCarpeta();
          retorno.putAll(obtenerDocsExpInside(
              ((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) dato).getElementosIndizados(),
              auxPath));
        } else if (dato instanceof ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) {
          retorno.putAll(obtenerDocsExpInside(
              ((ObjetoExpedienteInsideIndiceContenidoElementoContenedorElementos) dato)
                  .getElementosIndizados(),
              path));
        }
      }
    }
    return retorno;
  }

  @Override
  public Map<String, String> obtenerDocsExpTipo(List<Object> datos, String path) {
    Map<String, String> retorno = new HashMap<String, String>();
    if (CollectionUtils.isNotEmpty(datos)) {
      for (Object dato : datos) {
        if (dato instanceof TipoDocumentoIndizado) {
          retorno.put(((TipoDocumentoIndizado) dato).getIdentificadorDocumento(), path);
        } else if (dato instanceof TipoCarpetaIndizada) {
          String auxPath = path;
          if (!path.endsWith("/")) {
            auxPath += "/";
          }
          auxPath += ((TipoCarpetaIndizada) dato).getIdentificadorCarpeta();
          retorno.putAll(obtenerDocsExpTipo(((TipoCarpetaIndizada) dato)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), auxPath));
        } else if (dato instanceof TipoIndiceContenido) {
          retorno.putAll(obtenerDocsExpTipo(((TipoIndiceContenido) dato)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), path));
        }
      }
    }
    return retorno;
  }

  @Override
  public List<String> checkRepeatDocsExpTipo(List<Object> datosOrigen, List<Object> datosDestino)
      throws InSideServiceException {
    List<String> idsRepetidos = new ArrayList<String>();
    Map<String, String> origen = obtenerDocsExpTipo(datosOrigen, "/");
    Map<String, String> destino = obtenerDocsExpTipo(datosDestino, "/");
    getRepeatIds(idsRepetidos, origen, destino);
    return idsRepetidos;
  }

  public void getRepeatIds(List<String> idsRepetidos, Map<String, String> origen,
      Map<String, String> destino) {
    Iterator itOri = origen.entrySet().iterator();
    Iterator itDes = destino.entrySet().iterator();
    while (itOri.hasNext()) {
      Map.Entry ori = (Map.Entry) itOri.next();
      while (itDes.hasNext()) {
        Map.Entry des = (Map.Entry) itDes.next();
        if (((String) ori.getKey()).equals(des.getKey())) {
          idsRepetidos.add((String) ori.getKey());
        }
      }
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<String> correspondencia(Object expediente,
      List<String> identificadoresDocumento) {
    Map<String, String> idDocumentosExpediente = null;

    if (expediente instanceof ObjetoExpedienteInside)
      idDocumentosExpediente = obtenerDocsExpInside(((ObjetoExpedienteInside) expediente)
          .getIndice().getIndiceContenido().getElementosIndizados(), "/");
    else if (expediente instanceof TipoExpediente)
      idDocumentosExpediente = obtenerDocsExpTipo(((TipoExpediente) expediente).getIndice()
          .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), "/");

    // documentos añadidos en pantalla
    List<String> idDocumentosPantalla = new ArrayList<String>();
    if (CollectionUtils.isNotEmpty(identificadoresDocumento)) {
      for (String identificador : identificadoresDocumento) {
        idDocumentosPantalla.add(identificador);
      }
    }

    if (idDocumentosPantalla.containsAll(idDocumentosExpediente.keySet())) {
      if (idDocumentosExpediente.keySet().containsAll(idDocumentosPantalla)) {
        return null;
      } else {
        return CollectionUtils.subtract(idDocumentosPantalla, idDocumentosExpediente.keySet());
      }
    } else {
      return CollectionUtils.subtract(idDocumentosExpediente.keySet(), idDocumentosPantalla);
    }
  }

  @Override
  public List<String> getListIdentifierDocsInside(List<ObjetoDocumentoInside> documentos) {
    List<String> listIdentifier = new ArrayList<String>();
    for (ObjetoDocumentoInside doc : documentos)
      listIdentifier.add(doc.getIdentificador());

    return listIdentifier;
  }

  @Override
  public List<String> getListIdentifierDocsTipo(
      List<TipoDocumentoInsideConMAdicionales> documentos) {
    List<String> listIdentifier = new ArrayList<String>();
    for (TipoDocumentoInsideConMAdicionales doc : documentos)
      listIdentifier.add(doc.getDocumento().getContenido().getId());

    return listIdentifier;
  }

  @Override
  public boolean checkSignatureServerByUser(ObjetoInsideUsuario objetoInsideUsuario) {
    boolean checkSignatureServerByUser = false;
    try {
      if (objetoInsideUsuario != null) {
        ObjetoInsideUnidadAplicacionEeutil aplicationEeutil =
            getApplicationEeutilByUser(objetoInsideUsuario);

        if (aplicationEeutil != null)
          checkSignatureServerByUser = true;
      }
    } catch (InSideServiceException e) {
      logger.debug("El usuario no tiene asociada firma en servidor", e);
    }
    return checkSignatureServerByUser;
  }

  @Override
  public ObjetoInsideUnidadAplicacionEeutil crearActualizarUnidadAplicacionEeutil(
      String codigoUnidad, String aplicacion, String password) throws InSideServiceException {
    return insideStore.crearActualizarUnidadAplicacionEeutil(codigoUnidad, aplicacion, password);
  }

  @Override
  public List<ObjetoInsideUnidadAplicacionEeutil> getListUnidadAplicacionEeutil()
      throws InSideServiceException {
    return insideStore.getListUnidadAplicacionEeutil();
  }

  @Override
  public ObjetoInsideUnidadAplicacionEeutil getUnidadAplicacionEeutil(String codigoUnidad)
      throws InSideServiceException {
    return insideStore.getUnidadAplicacionEeutil(codigoUnidad);
  }

  @Override
  public ObjetoInsideAplicacion crearActualizarSerialNumberCertificado(String idAplicacion,
      String serialNumberCertificado) throws InsideServiceStoreException {
    return insideStore.crearActualizarSerialNumberCertificado(idAplicacion,
        serialNumberCertificado);
  }

  @Override
  public void guardarSolicitudAccesoExpediente(
      ObjetoSolicitudAccesoExpediente objetoSolicitudAccesoExpediente)
      throws InSideServiceException {
    insideStore.saveSolicitudAccesoExpediente(objetoSolicitudAccesoExpediente);
  }

  @Override
  public List<String> listaNumeroProcedimiento() throws InsideServiceStoreException {
    return insideStore.getNumeroProcedimiento();
  }

  @Override
  public String altaNumeroProcedimiento(String numeroProcedimiento)
      throws InsideServiceStoreException {
    return insideStore.altaNumeroProcedimiento(numeroProcedimiento);
  }

  @Override
  public ObjetoEstructuraCarpetaInside getEstructuraCarpeta(ObjetoInsideUsuario usuario)
      throws InSideServiceException {
    return insideStore.getEstructuraCarpeta(usuario);
  }

  @Override
  public List<ObjetoEstructuraCarpetaInside> listaEstructuraCarpeta(String identificadorEstructura)
      throws InsideServiceStoreException {
    return insideStore.listaEstructuraCarpeta(identificadorEstructura);
  }

  @Override
  public ObjetoEstructuraCarpetaInside altaEstructuraCarpeta(
      ObjetoEstructuraCarpetaInside objetoEstructuraCarpetaInside)
      throws InsideServiceStoreException {
    return insideStore.altaEstructuraCarpeta(objetoEstructuraCarpetaInside);
  }

  @Override
  public void deleteEstructuraCarpeta(String identificadorEstructura)
      throws InsideServiceStoreException {
    insideStore.deleteEstructuraCarpeta(identificadorEstructura);
  }

  @Override
  public void saveComunicacionTokenExpediente(
      ObjetoComunicacionTokenExpediente objetoComunicacionTokenExpediente)
      throws InsideServiceStoreException {
    insideStore.saveComunicacionTokenExpediente(objetoComunicacionTokenExpediente);

  }

  @Override
  public List<ObjetoComunicacionTokenExpediente> getComunicacionesTokenExpedienteActivas(
      int maximoResultados, int numeroMaximoIntentos) throws InsideServiceStoreException {
    return insideStore.getComunicacionesTokenExpedienteActivas(maximoResultados,
        numeroMaximoIntentos);
  }

  @Override
  public void saveSolicitudAccesoExpediente(
      ObjetoSolicitudAccesoExpediente objetoSolicitudAccesoExpediente)
      throws InsideServiceStoreException {
    insideStore.saveSolicitudAccesoExpediente(objetoSolicitudAccesoExpediente);

  }

  @Override
  public String getUrlDestinoPeticionAccesoExpediente(String dir3) throws InSideServiceException {
    return insideStore.getUrlDestinoPeticionAccesoExpediente(dir3);
  }

  @Override
  public List<ObjetoSolicitudAccesoExpediente> getSolicitudesAccesoExpediente(
      ObjetoInsideUsuario objetoInsideUsuario)
      throws InsideServiceStoreException, JAXBException, XMLStreamException {
    return insideStore.getSolicitudesAccesoExpediente(objetoInsideUsuario);
  }

  @Override
  public List<ObjetoAuditoriaAcceso> getAuditoriaAccesoDocumento(
      ObjetoInsideUsuario objetoInsideUsuario) throws InsideServiceStoreException {
    return insideStore.getAuditoriaAccesoDocumento(objetoInsideUsuario);
  }

  @Override
  public ObjetoSolicitudAccesoExpediente getSolicitudAccesoExpediente(String id)
      throws InsideServiceStoreException, JAXBException, XMLStreamException {
    return insideStore.getSolicitudAccesoExpediente(id);
  }

  @Override
  public ObjetoSolicitudAccesoExpediente getSolicitudAccesoExpedientePorIdPeticion(
      String idPeticion) throws JAXBException {
    return insideStore.getSolicitudAccesoExpedientePorIdPeticion(idPeticion);
  }

  @Override
  public void saveAuditoriaAccesoDocumento(
      ObjetoAuditoriaAccesoDocumento objetoAuditoriaAccesoDocumento)
      throws InsideServiceStoreException {
    insideStore.saveAuditoriaAccesoDocumento(objetoAuditoriaAccesoDocumento);
  }

  @Override
  public ObjetoSolicitudAccesoExpAppUrl saveSolicitudAccesoExpAppUrl(
      ObjetoSolicitudAccesoExpAppUrl objetoSolicitudAccesoExpAppUrl)
      throws InsideServiceStoreException {
    return insideStore.saveSolicitudAccesoExpAppUrl(objetoSolicitudAccesoExpAppUrl);
  }

  @Override
  public ObjetoSolicitudAccesoExpAppUrl getSolicitudAccesoExpAppUrlPorDir3(String dir3Padre)
      throws InsideServiceStoreException {
    return insideStore.getSolicitudAccesoExpAppUrlPorDir3(dir3Padre);
  }

  @Override
  public ObjectInsideRespuestaEnvioJusticia getRespuestaEvioJusticaByCodigoEnvio(
      ObjectInsideRespuestaEnvioJusticia respuestaEnvioJusticia) throws InSideServiceException {
    return insideStore.getRespuestaEvioJusticaByCodigoEnvio(respuestaEnvioJusticia);
  }

  @Override
  public ObjectInsideRespuestaEnvioJusticia getRespuestaEnvioJusticiaByCodigoEnvio(
      String codigoEnvio) throws InSideServiceException {
    return insideStore.getRespuestaEnvioJusticiaByCodigoEnvio(codigoEnvio);
  }

  @SuppressWarnings({"unchecked"})
  private <T extends ObjetoInside<?>> T deleteRepository(T objeto)
      throws InSideServiceObjectDefinitionNotFoundException, InsideServiceObjectConverterException {

    objectDefinitions.getConverter(objeto.getClass()).delete(objeto);

    return objeto;
  }

  @Override
  public void validarFirma(byte[] firma) throws InSideServiceException {
    try {
      ResultadoValidacionInfo resultado =
          insideVisualizacion.llamadaValidacionFirmaService(firma, null);
      if (resultado != null && !BooleanUtils.isTrue(resultado.isEstado()))
        throw new InsideServiceInternalException("Firma incorrecta.");
      insideVisualizacion.llamadaInfoFirmaService(firma, true, true, true, null);
    } catch (InfoFirmaServiceException e) {
      logger.debug(e);
      throw new InsideServiceInternalException("Firma incorrecta. " + e.getMessage());
    }
  }

  @Override
  public void validarFirma(byte[] firma, byte[] documento) throws InSideServiceException {
    try {
      String datos = null;
      if (documento != null)
        datos = new String(documento);
      byte[] firmaAValidar = firma;

      ResultadoValidacionInfo resultado =
          insideVisualizacion.llamadaValidacionFirmaService(firmaAValidar, datos);
      if (resultado != null && !BooleanUtils.isTrue(resultado.isEstado()))
        throw new InsideServiceInternalException("Firma incorrecta.");
      insideVisualizacion.llamadaInfoFirmaService(firma, true, true, true, documento);
    } catch (InfoFirmaServiceException e) {
      logger.debug(e);
      throw new InsideServiceInternalException("Firma incorrecta. " + e.getMessage());
    }
  }

  @Override
  public void getDocumentoEnIndiceExpediente(
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados,
      String elemNombre, ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado contenidoDoc) {
    String compare;

    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento : elementosIndizados) {
      if (elemento instanceof ObjetoExpedienteInsideIndiceContenido) {
        getDocumentoEnIndiceExpediente(
            ((ObjetoExpedienteInsideIndiceContenido) elemento).getElementosIndizados(), elemNombre,
            contenidoDoc);
      } else if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
        getDocumentoEnIndiceExpediente(
            ((ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elemento)
                .getElementosIndizados(),
            elemNombre, contenidoDoc);
      } else if (elemento instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
        compare = ((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elemento)
            .getIdentificadorDocumento();
        if (StringUtils.equals(compare, elemNombre)) {
          contenidoDoc.setIdentificadorDocumento(
              ((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elemento)
                  .getIdentificadorDocumento());
          contenidoDoc.setValorHuella(
              ((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elemento).getValorHuella());
        }
      }
    }
  }

  @Override
  public void actualizarAsociacionDocumentoUnidad(ObjetoDocumentoInside documento, String usuario,
      boolean online) throws InsideServiceStoreException {
    insideStore.updateDocumentoUnidad(documento, usuario, online);
  }

  @Override
  public List<ObjetoElastic> getObjetosElastic(String nif) throws InsideServiceStoreException {
    return insideStore.getObjetosElastic(nif);
  }

  @Override
  public int getDefaultIdByDir3(String unidadOrganica, boolean isDocument)
      throws InsideServiceStoreException {
    String elemento = isDocument ? Constantes.ELEMENTO_DOCUMENTO : Constantes.ELEMENTO_EXPEDIENTE;
    return insideStore.consultAndIncreaseDefaultIdByDir3(unidadOrganica, elemento);
  }

  @Override
  public void saveAuditoriaFirmaServidor(
      ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor) {
    insideStore.saveAuditoriaFirmaServidor(objetoAuditoriaFirmaServidor);
  }

  @Override
  public List<ObjetoInsideRol> getInsideRoles() throws InsideServiceStoreException {
    return insideStore.getInsideRoles();
  }


  @Override
  public List<ObjetoInsideUsuario> getUsuariosUnidadOrganica(ObjetoInsideUsuario usuarioEnSesion,
      Locale locale) throws InsideServiceStoreException {
    return insideStore.getUsuariosUnidadOrganica(usuarioEnSesion, locale);
  }

  @Override
  public List<ObjetoNumeroProcedimiento> getNumeroProcedimientoList()
      throws InsideServiceStoreException {
    return insideStore.getNumeroProcedimientoList();
  }


  @Override
  public List<ObjetoComunicacionTokenExpediente> getComunicacionesTokenExpedienteUnidadOrganicaUsuario(
      ObjetoInsideUsuario objetoInsideUsuario) throws InsideServiceStoreException {
    return insideStore.getComunicacionesTokenExpedienteUnidadOrganicaUsuario(objetoInsideUsuario);
  }

  @Override
  public ObjetoComunicacionTokenExpediente getComunicacionTokenExpedientePorId(String id)
      throws InsideServiceStoreException {
    return insideStore.getComunicacionTokenExpedientePorId(id);
  }

  @Override
  public List<UnidadOrganicaRolPortales> getlistaUnidadesRolesPortales(String nif)
      throws InsideServiceStoreException {


    return consumidorPortales.listaUnidadesOrganicasRolesPortales(nif);
  }

}
