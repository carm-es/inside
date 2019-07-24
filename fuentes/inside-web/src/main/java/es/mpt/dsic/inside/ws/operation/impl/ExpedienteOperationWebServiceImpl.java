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

package es.mpt.dsic.inside.ws.operation.impl;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.converter.InsideConverterExpediente;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InSideServicePermisos;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.util.xml.JAXBMarshaller;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.operation.ExpedienteOperationWebService;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;

@Service("ExpedienteOperationWebService")
public class ExpedienteOperationWebServiceImpl implements ExpedienteOperationWebService {

  protected static final Log logger = LogFactory.getLog(GInsideOperationWebServiceImpl.class);

  @Autowired
  private InsideUtilService insideUtilService;

  @Autowired
  private InSideService service;

  @Autowired
  private InSideServicePermisos servicePermisos;

  @Autowired
  private JAXBMarshaller marshaller;


  // convertirExpedienteAEni
  @Override
  public TipoExpedienteEniFileInside crearExpedienteEniFileXMLServicio(
      TipoExpedienteConversionInside expediente, TipoMetadatosAdicionales metadatosAdicionales,
      WSCredentialInside info) throws InsideWSException {

    try {
      logger.debug("Inicio ExpedienteOperationWebServiceImpl.crearExpedienteEniFileXMLServicio");

      // Validar id de expediente.
      if (!insideUtilService
          .esLongitudIdentificadorValido(expediente.getMetadatosEni().getIdentificador())) {
        throw InsideExceptionConverter.convertToException(
            new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_LONGITUD, null, ""));
      }

      // Validar si tiene carpetas vacias
      insideUtilService.contieneCarpetasVacias(expediente);

      // esto es sin metadatosadicionales
      // TipoExpediente tExp = insideUtilService.convertirExpedienteAEni(expediente, null, true,
      // info);

      // Contenidofirma a null para que firme en servidor: Si tiene su clave dada de alta se firmara
      // con su clave, si no se firma con la clave de inside.
      TipoExpedienteInsideConMAdicionales tExp =
          insideUtilService.convertirExpedienteAEniConMAdicionales(expediente, metadatosAdicionales,
              null, true, info);

      // Obtener byte[] de expediente.
      byte[] expediente_Despues_Transformacion = transformarExpedienteParaValidarFirma(tExp);

      // Instancia TipoExpedienteEniFileInside objeto a devolver
      TipoExpedienteEniFileInside tipoExpedienteEniFileInside = new TipoExpedienteEniFileInside();
      tipoExpedienteEniFileInside.setExpedienteEniBytes(expediente_Despues_Transformacion);

      logger.debug("Fin ExpedienteOperationWebServiceImpl.crearExpedienteEniFileXMLServicio");

      return tipoExpedienteEniFileInside;


    } catch (InsideWSException e) {
      throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_EXPEDIENTE_LONGITUD, null, "");
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }


  // getExpedienteEniFile
  @Override
  public TipoExpedienteEniFileInside recuperarExpedienteEniFileXMLServicio(
      String expedienteIdentificador, WSCredentialInside info) throws InsideWSException {

    try {
      logger
          .debug("Inicio ExpedienteOperationWebServiceImpl.recuperarExpedienteEniFileXMLServicio");

      // Por defecto le decimops que si
      boolean visualizacionIndice = true;

      // Control Permisos
      servicePermisos.checkPermisos(expedienteIdentificador, null, ObjetoExpedienteInside.class,
          info.getIdaplicacion());

      ObjetoExpedienteInside expediente =
          service.getExpediente(expedienteIdentificador, visualizacionIndice);

      TipoExpediente tExp = InsideConverterExpediente.expedienteInsideToEni(expediente, null);
      logger.info("Devuelto Expediente ENI con identificador " + expedienteIdentificador);

      // Obtener byte[] de expediente.
      byte[] expediente_Despues_Transformacion = transformarExpedienteParaValidarFirma(tExp);

      // Instancia TipoExpedienteEniFileInside objeto a devolver
      TipoExpedienteEniFileInside tipoExpedienteEniFileInside = new TipoExpedienteEniFileInside();
      tipoExpedienteEniFileInside.setExpedienteEniBytes(expediente_Despues_Transformacion);

      // TipoExpedienteEniFileInside expedienteEniFile =
      // InsideConverterExpediente.expedienteEniToTipoExpedienteEniFileInside(tExp, true);

      logger.debug("Fin ExpedienteOperationWebServiceImpl.recuperarExpedienteEniFileXMLServicio");

      return tipoExpedienteEniFileInside;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }



  // altaExpedienteEniXml
  @Override
  public TipoExpedienteEniFileInside insertarExpedienteEniFileXMLServicio(
      TipoExpedienteEniFileInside expedienteEniXml, WSCredentialInside credenciales)
      throws InsideWSException {

    try {
      logger.debug("Inicio ExpedienteOperationWebServiceImpl.insertarExpedienteEniFileXMLServicio");

      // Valida el expediente
      ObjetoExpedienteInside objExp =
          insideUtilService.validateExpedientImport(expedienteEniXml.getExpedienteEniBytes());

      // valida el identificador
      if (!insideUtilService.esLongitudIdentificadorValido(objExp.getIdentificador())) {
        throw new InsideWSException(InsideWsErrors.IDENTIFICADOR_DOCUMENTO_LONGITUD, null, "");
      }

      // obtener los documentos del indice
      List<String> listaIdentificadoresDoc =
          listaIdentificadoresDocumentosIndizados(expedienteEniXml);
      List<TipoDocumentoEniFileInside> listaDocumentos =
          getListaDocumentoEniFile(listaIdentificadoresDoc, credenciales.getIdaplicacion());

      // recupera los documentoseni
      List<ObjetoDocumentoInside> documentos =
          insideUtilService.recoverDocumentXml(listaDocumentos);
      Collection<String> resultado =
          service.correspondencia(objExp, service.getListIdentifierDocsInside(documentos));
      if (resultado == null) {
        Map<String, Object> result = insideUtilService.crearNuevoExpediente(objExp, documentos,
            credenciales.getIdaplicacion(), false);
        objExp = (ObjetoExpedienteInside) result.get("expediente");
        String info =
            result.get("docsNoCreate") != null
                ? InsideWsErrors.DOCS_SIN_ACTUALIZAR.getValue().getDescripcion()
                    + result.get("docsNoCreate")
                : "";
      } else {
        throw new InsideWSException(InsideWsErrors.CORRESPONDENCIA_DOCUMENTOS_EXPEDIENTE, null,
            resultado.toString());
      }

      return expedienteEniXml;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }



  // eliminarExpedienteEniFileXML
  @Override
  public TipoExpedienteEniFileInside eliminarExpedienteEniFileXMLServicio(
      String expedienteIdentificador, WSCredentialInside info) throws InsideWSException {

    try {
      logger.debug("Inicio ExpedienteOperationWebServiceImpl.eliminarExpedienteEniFileXMLServicio");
      // Control Permisos
      servicePermisos.checkPermisos(expedienteIdentificador, null, ObjetoExpedienteInside.class,
          info.getIdaplicacion());

      TipoExpedienteEniFileInside tipoExpedienteEniFileInside =
          recuperarExpedienteEniFileXMLServicio(expedienteIdentificador, info);
      service.deleteExpedient(expedienteIdentificador);

      return tipoExpedienteEniFileInside;


    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }


  }



  // validarExpedienteEniFileXML
  @Override
  public TipoResultadoValidacionExpedienteInside validarExpedienteEniFileXMLServicio(
      TipoExpedienteEniFileInside expedienteEniXml, WSCredentialInside info)
      throws InsideWSException {
    try {
      // Valida el expediente
      TipoResultadoValidacionExpedienteInside tipoResultadoValidacionExpedienteInside =
          insideUtilService
              .validateExpedientOperacionWebService(expedienteEniXml.getExpedienteEniBytes());

      return tipoResultadoValidacionExpedienteInside;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }



  private byte[] transformarExpedienteParaValidarFirma(Object tExp) throws Exception {
    TipoExpedienteInsideConMAdicionales tExpAdi = null;
    if (tExp instanceof TipoExpedienteInsideConMAdicionales) {
      tExpAdi = (TipoExpedienteInsideConMAdicionales) tExp;
    } else {
      tExpAdi = new TipoExpedienteInsideConMAdicionales();
      tExpAdi.setExpediente((TipoExpediente) tExp);
    }


    // expediente con nodo firma en base64 hay que transformarlo a ds signature
    byte[] expediente_Antes_Transformacion = insideUtilService.generateExpXml(tExpAdi);

    // Para validar la firma recoger solo el ns7:expediente sin metadatosadicionales
    byte[] dataConFirmaSinIdentar =
        insideUtilService.generateExpXmlParaValidar(tExpAdi.getExpediente());

    // sustituye el nodo <ns7:expediente por el dataConFirmaSinIdentar que esta sin identar y bien
    // formado
    String data = XMLUtils.construirExpedienteENIValido(new String(expediente_Antes_Transformacion),
        new String(dataConFirmaSinIdentar));

    // terminar la transformacion de cambio firmabase64 a dsSignature
    byte[] expediente_Despues_Transformacion =
        XMLUtils.deFirmaBase64_A_DSSignature(data.getBytes("UTF-8"));

    return expediente_Despues_Transformacion;
  }



  private List<TipoDocumentoEniFileInside> getListaDocumentoEniFile(List<String> listaIdentificador,
      String aplicacion) throws InsideWSException {

    List<TipoDocumentoEniFileInside> listaDocumentosIndice =
        new ArrayList<TipoDocumentoEniFileInside>();
    try {
      for (int i = 0; i < listaIdentificador.size(); i++) {

        // Control Permisos
        servicePermisos.checkPermisos(listaIdentificador.get(i), null, ObjetoDocumentoInside.class,
            aplicacion);

        ObjetoDocumentoInside documento = service.getDocumento(listaIdentificador.get(i));

        TipoDocumento documentoEni = InsideConverterDocumento.documentoInsideToEni(documento, null);

        logger.info("Obtenido Documento ENI con identificador " + documento.getIdentificador());

        TipoDocumentoEniFileInside documentoEniFile =
            InsideConverterDocumento.documentoEniToTipoDocumentoEniFileInside(documentoEni, true);

        listaDocumentosIndice.add(documentoEniFile);

      }

      return listaDocumentosIndice;

    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }


  private List<String> listaIdentificadoresDocumentosIndizados(
      TipoExpedienteEniFileInside expedienteEniXml)
      throws JAXBException, ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    // obtener los documentos del indice
    List<String> listaIdentificadoresDoc = new ArrayList<String>();
    TipoExpedienteInsideConMAdicionales tipoExpedienteInsideConMAdicionales =
        marshaller.unmarshallDataExpedientAditional(expedienteEniXml.getExpedienteEniBytes());
    if (tipoExpedienteInsideConMAdicionales.getExpediente() == null) {
      tipoExpedienteInsideConMAdicionales =
          marshaller.unmarshallDataExpedientArchive(expedienteEniXml.getExpedienteEniBytes());
    }
    Map<String, String> docsExp =
        service.obtenerDocsExpTipo(tipoExpedienteInsideConMAdicionales.getExpediente().getIndice()
            .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), "/");

    if (CollectionUtils.isNotEmpty(docsExp.entrySet())) {
      for (String idDoc : docsExp.keySet()) {
        listaIdentificadoresDoc.add(idDoc);
      }

    }
    return listaIdentificadoresDoc;
  }

}
