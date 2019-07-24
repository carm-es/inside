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

package es.mpt.dsic.inside.ws.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.operation.InsideOperationWebService;
import es.mpt.dsic.inside.ws.service.InsideCredentialWebService;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.contenido.TipoContenido;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.indice.TipoIndice;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.eni.firma.Firmas;
import es.mpt.dsic.inside.xml.inside.DocumentoInsideMetadatos;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideMetadatos;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInside;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInside;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoVersionInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ConsultaWsInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.DocumentoResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ExpedienteResultadoBusqueda;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.DocumentoInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAltaInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAsociadoaExpediente;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.documentos.TipoExpedienteEniFileInsideConDocumentos;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.InsideCredentialWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
    portName = "InsideWSPort", serviceName = "InsideWS")
public class InsideCredentialWebServiceImpl implements InsideCredentialWebService {

  @Autowired
  InsideOperationWebService insideOperationWebService;

  @Resource
  private WebServiceContext wsContext;

  @Override
  @Secured("ROLE_ALTA_EXPEDIENTE")
  public ExpedienteInsideInfo altaExpedienteEni(WSCredentialInside info,
      TipoExpediente expedienteEni, TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException {
    try {
      return insideOperationWebService.altaExpedienteEni(expedienteEni, metadatosAdicionales,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_EXPEDIENTE")
  public ExpedienteInsideInfo altaExpedienteEniXml(WSCredentialInside info,
      TipoExpedienteEniFileInsideConDocumentos expedienteEniFile) throws InsideWSException {
    try {
      return insideOperationWebService.altaExpedienteEniXml(expedienteEniFile,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo cambiaMetadatosExpediente(WSCredentialInside info,
      String identificador, TipoMetadatos metadatosEni,
      TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
    try {
      return insideOperationWebService.cambiaMetadatosExpediente(identificador, metadatosEni,
          metadatosAdicionales, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_DOCUMENTO")
  public DocumentoInsideInfo cambiaMetadatosDocumento(WSCredentialInside info, String identificador,
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni,
      TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
    try {
      return insideOperationWebService.cambiaMetadatosDocumento(identificador, metadatosEni,
          metadatosAdicionales, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo modificaExpedienteEni(WSCredentialInside info,
      TipoExpediente expedienteEni, TipoMetadatosAdicionales metadatosAdicionales)
      throws InsideWSException {
    try {
      return insideOperationWebService.modificaExpedienteEni(expedienteEni, metadatosAdicionales,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteInsideInfo altaCarpetaEnExpediente(WSCredentialInside info,
      String identificadorExpediente, TipoCarpetaIndizada carpetaIndizada, String ruta)
      throws InsideWSException {
    try {
      return insideOperationWebService.altaCarpetaEnExpediente(identificadorExpediente,
          carpetaIndizada, ruta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteInsideInfo borrarCarpetaEnExpediente(WSCredentialInside info,
      String identificadorExpediente, String identificadorCarpeta, String ruta)
      throws InsideWSException {
    try {
      return insideOperationWebService.borrarCarpetaEnExpediente(identificadorExpediente,
          identificadorCarpeta, ruta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo cambiaEstadoExpediente(WSCredentialInside info, String identificador,
      EnumeracionEstados estado) throws InsideWSException {
    try {
      return insideOperationWebService.cambiaEstadoExpediente(identificador, estado,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo importarExpediente(WSCredentialInside info, String identificador,
      String identificadorImportado, String ruta) throws InsideWSException {
    try {
      return insideOperationWebService.importarExpediente(identificador, identificadorImportado,
          ruta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo vincularExpediente(WSCredentialInside info, String identificador,
      String identificadorVinculado, String ruta) throws InsideWSException {
    try {
      return insideOperationWebService.vincularExpediente(identificador, identificadorVinculado,
          ruta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo crearVistaAbiertaExpediente(WSCredentialInside info,
      String identificador) throws InsideWSException {
    try {
      return insideOperationWebService.crearVistaAbiertaExpediente(identificador,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo crearVistaCerradaExpediente(WSCredentialInside info,
      String identificador) throws InsideWSException {
    try {
      return insideOperationWebService.crearVistaCerradaExpediente(identificador,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo vincularDocumentosEnExpediente(WSCredentialInside info,
      String identificador, List<String> identificadoresDocumentos, String ruta)
      throws InsideWSException {
    try {
      return insideOperationWebService.vincularDocumentosEnExpediente(identificador,
          identificadoresDocumentos, ruta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo desvincularDocumentosEnExpediente(WSCredentialInside info,
      String identificador, List<String> identificadoresDocumentos, String ruta)
      throws InsideWSException {
    try {
      return insideOperationWebService.desvincularDocumentosEnExpediente(identificador,
          identificadoresDocumentos, ruta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ExpedienteInsideInfo cambiarUbicacionEnExpediente(WSCredentialInside info,
      String identificadorExpediente, List<String> identificadoresElementos, String rutaOrigen,
      String rutaDestino) throws InsideWSException {
    try {
      return insideOperationWebService.cambiarUbicacionEnExpediente(identificadorExpediente,
          identificadoresElementos, rutaOrigen, rutaDestino, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoExpedienteInside getExpediente(WSCredentialInside info, String identificador,
      Integer version) throws InsideWSException {
    try {
      return insideOperationWebService.getExpediente(identificador, version,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoExpediente getExpedienteEni(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      return insideOperationWebService.getExpedienteEni(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoExpedienteEniFileInside getExpedienteEniFile(WSCredentialInside info,
      String identificador, Boolean visualizacionIndice) throws InsideWSException {
    try {
      return insideOperationWebService.getExpedienteEniFile(identificador, visualizacionIndice,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public TipoIndice getIndiceExpediente(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      return insideOperationWebService.getIndiceExpediente(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public ExpedienteInsideMetadatos getExpedienteMetadatos(WSCredentialInside info,
      String identificador, Integer version) throws InsideWSException {
    try {
      return insideOperationWebService.getExpedienteMetadatos(identificador, version,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public List<TipoVersionInside> getVersionesExpediente(WSCredentialInside info,
      String identificador) throws InsideWSException {
    try {
      return insideOperationWebService.getVersionesExpediente(identificador,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoOriginal(WSCredentialInside info,
      TipoDocumentoAltaInside documentoAlta) throws InsideWSException {
    try {
      return insideOperationWebService.altaDocumentoOriginal(documentoAlta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoDigitalizado(WSCredentialInside info,
      TipoDocumentoAltaInside documentoAlta) throws InsideWSException {
    try {
      return insideOperationWebService.altaDocumentoDigitalizado(documentoAlta,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoCopiaParcial(WSCredentialInside info,
      TipoDocumentoAltaInside documentoAlta, String identificadorDocumentoOrigen)
      throws InsideWSException {
    try {
      return insideOperationWebService.altaDocumentoCopiaParcial(documentoAlta,
          identificadorDocumentoOrigen, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoCambioFormato(WSCredentialInside info,
      TipoDocumentoAltaInside documentoAlta, String identificadorDocumentoOrigen)
      throws InsideWSException {
    try {
      return insideOperationWebService.altaDocumentoCambioFormato(documentoAlta,
          identificadorDocumentoOrigen, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoOtros(WSCredentialInside info,
      TipoDocumentoAltaInside documentoAlta) throws InsideWSException {
    try {
      return insideOperationWebService.altaDocumentoOtros(documentoAlta, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoEni(WSCredentialInside info, TipoDocumento documentoEni,
      TipoDocumentoAsociadoaExpediente asociadoAExpediente,
      TipoMetadatosAdicionales metadatosAdicionales, boolean firmaServidor)
      throws InsideWSException {
    try {
      return insideOperationWebService.altaDocumentoEni(documentoEni, asociadoAExpediente,
          metadatosAdicionales, firmaServidor, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ALTA_DOCUMENTO")
  public DocumentoInsideInfo altaDocumentoEniXml(WSCredentialInside info,
      TipoDocumentoEniFileInside documentoEniFile) throws InsideWSException {
    try {
      return insideOperationWebService.altaDocumentoEniXml(documentoEniFile,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_DOCUMENTO")
  public DocumentoInsideInfo modificaDocumento(WSCredentialInside info, TipoDocumento documentoEni,
      TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
    try {
      return insideOperationWebService.modificaDocumento(documentoEni, metadatosAdicionales,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoDocumentoInside getDocumento(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      return insideOperationWebService.getDocumento(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoDocumento getDocumentoEni(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      return insideOperationWebService.getDocumentoEni(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoDocumentoEniFileInside getDocumentoEniFile(WSCredentialInside info,
      String identificador) throws InsideWSException {
    try {
      return insideOperationWebService.getDocumentoEniFile(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoContenido getDocumentoContenido(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      return insideOperationWebService.getDocumentoContenido(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public DocumentoInsideMetadatos getMetadatosDocumento(WSCredentialInside info,
      String identificador) throws InsideWSException {
    try {
      return insideOperationWebService.getMetadatosDocumento(identificador, null,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public Integer getNumeroBytesDocumentoFirmado(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      return insideOperationWebService.getNumeroBytesDocumentoFirmado(identificador,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public Firmas getFirmasEniCSVDocumento(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      return insideOperationWebService.getFirmasEniCSVDocumento(identificador,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public ExpedienteResultadoBusqueda buscarExpedientes(WSCredentialInside info,
      ConsultaWsInside consulta, int limite, int pagina) throws InsideWSException {
    try {
      return insideOperationWebService.buscarExpedientes(consulta, limite, pagina,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }

  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public DocumentoResultadoBusqueda buscarDocumentos(WSCredentialInside info,
      ConsultaWsInside consulta, int limite, int pagina) throws InsideWSException {
    try {
      return insideOperationWebService.buscarDocumentos(consulta, limite, pagina,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public DocumentoEniFileInside convertirDocumentoAEni(WSCredentialInside info,
      TipoDocumentoConversionInside documentoConversion, byte[] bytesContenido, Boolean firmar)
      throws InsideWSException {
    try {
      return insideOperationWebService.convertirDocumentoAEni(documentoConversion, bytesContenido,
          firmar, info);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public ExpedienteEniFileInside convertirExpedienteAEni(WSCredentialInside info,
      TipoExpedienteConversionInside expedienteConversion, String contenidoFirma)
      throws InsideWSException {
    try {
      return insideOperationWebService.convertirExpedienteAEni(expedienteConversion, contenidoFirma,
          info);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_DOCUMENTO")
  public void eliminarDocumento(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      insideOperationWebService.eliminarDocumento(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public void eliminarExpediente(WSCredentialInside info, String identificador)
      throws InsideWSException {
    try {
      insideOperationWebService.eliminarExpediente(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public List<ExpedienteInsideInfo> expedientesAsociadoDocumento(WSCredentialInside info,
      String identificador) throws InsideWSException {
    try {
      return insideOperationWebService.expedientesAsociadoDocumento(identificador,
          info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_LEER_DOCUMENTO")
  public TipoResultadoVisualizacionDocumentoInside visualizarDocumento(WSCredentialInside info,
      String identificador) throws InsideWSException {
    try {
      return insideOperationWebService.visualizarDocumento(identificador, info.getIdaplicacion());
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }
}
