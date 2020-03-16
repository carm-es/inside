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

package es.mpt.dsic.inside.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacion;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InSideServiceTemporalDataException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.util.xml.JAXBMarshaller;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.EnumeracionEstadoElaboracion;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoExpedienteInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.TipoDocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

public interface InsideUtilService {

  public TipoDocumento convertirDocumentoAEni(TipoDocumentoConversionInside documentoConversion,
      byte[] bytesContenido, boolean firmar, WSCredentialInside info) throws Exception;

  public TipoDocumentoInsideConMAdicionales convertirDocumentoAEniConMAdicionales(
      TipoDocumentoConversionInside documentoConversion,
      TipoMetadatosAdicionales metadatosAdicionales, byte[] bytesContenido, boolean firmar,
      WSCredentialInside info) throws Exception;

  public TipoDocumentoInsideConMAdicionales convertirDocumentoAEniConMAdicionales(
      TipoDocumentoConversionInside documentoConversion,
      TipoMetadatosAdicionales metadatosAdicionales, byte[] bytesContenido, boolean firmar,
      WSCredentialInside info, ObjetoDocumentoInside docInside) throws Exception;

  public TipoExpediente convertirExpedienteAEni(TipoExpedienteConversionInside expedienteConversion,
      String contenidoFirma, boolean firmar, WSCredentialInside info) throws Exception;

  public TipoExpedienteInsideConMAdicionales convertirExpedienteAEniConMAdicionales(
      TipoExpedienteConversionInside expedienteConversion,
      TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma, boolean firmar,
      WSCredentialInside info) throws Exception;

  public TipoResultadoValidacionDocumentoInside validarDocumentoEniFile(
      TipoDocumentoValidacionInside documentoValidacion) throws InSideServiceException;

  public String getStringExpedienteMarshall(Object expediente) throws InSideServiceException;

  public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(
      TipoExpedienteValidacionInside expedienteValidacion, boolean isOnline)
      throws InSideServiceException;

  public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(
      TipoExpedienteValidacionInside expedienteValidacion, boolean isOnline, boolean validarSIA)
      throws InSideServiceException;

  public TipoResultadoVisualizacionDocumentoInside visualizarDocumentoEni(
      TipoDocumentoVisualizacionInside documentoVisualizacion) throws Exception;

  public ObjetoDocumentoInside validateDocumentImport(byte[] documentoEniBytes)
      throws InSideServiceException;

  public ObjetoExpedienteInside validateExpedientImport(byte[] expedienteEniBytes)
      throws InSideServiceException;

  public Map<String, Object> crearNuevoExpediente(ObjetoExpedienteInside objExp,
      List<ObjetoDocumentoInside> documentos, String idUsuario, boolean online)
      throws InSideServiceException;

  public Map<String, Object> actualizarExpediente(ObjetoExpedienteInside objExp,
      List<ObjetoDocumentoInside> documentos, String idUsuario, boolean online)
      throws InSideServiceException;

  public boolean checkNoExistIdDocumentInside(String identificador, boolean throwException)
      throws InSideServiceException;

  public List<ObjetoDocumentoInside> recoverDocumentXml(List<TipoDocumentoEniFileInside> list)
      throws InSideServiceException;

  public Map<String, Object> checkToken(String nifConsultaPuestaDisposicion, String identificador,
      String csv, String uuid) throws InSideServiceException;

  public byte[] lazyLoadXmlFile(String idSession, String id, boolean addXmlExtension)
      throws Exception;

  ObjetoDocumentoInsideContenido lazyLoadContentFile(String idSession, String id) throws Exception;

  public Map<String, byte[]> unzip(String carpeta, String fichero)
      throws IOException, InSideServiceTemporalDataException;

  public Map<String, Map<String, String>> getMapaExpedienteYDocumentos(
      String identificadorExpediente, String versionExpediente) throws Exception;

  public byte[] generateDocXml(TipoDocumentoInsideConMAdicionales docInside)
      throws InSideServiceException;

  public byte[] generateExpXml(TipoExpedienteInsideConMAdicionales expInside)
      throws InSideServiceException;

  public byte[] generateExpXmlParaValidar(TipoExpediente expInside) throws InSideServiceException;

  public ResultadoValidacion comprobarDocumentosIndiceExpediente(ObjetoExpedienteInside expediente,
      List<ObjetoDocumentoInside> listaDocumentos) throws InSideServiceException;

  public String generateDefaultId(String unidadOrganica, boolean isDocument);

  public boolean esLongitudIdentificadorValido(String identificador) throws InsideWSException;

  public void contieneCarpetasVacias(Object expediente) throws InsideValidationDataException;

  public ObjetoDocumentoInside convertXmlDocumentToDocumentObject(
      TipoDocumentoInsideConMAdicionales docConvertido) throws InSideServiceException;

  public boolean aceptarFicheroTamanioYFirma(String pathContenido, String signId,
      EnumeracionEstadoElaboracion enumeracionEstadoElaboracion) throws InSideServiceException;

  ObjetoDocumentoInsideContenido getExternalContent(String sistema, String uuid, String idSession);

  ObjetoDocumentoInsideContenido getExternalContent(String idDocumentoInside, String idSession)
      throws InSideServiceException;

  public TipoResultadoValidacionExpedienteInside validateExpedientOperacionWebService(
      byte[] expedienteEniBytes) throws InSideServiceException;

  public ObjetoDocumentoInside validateDocumentImport(Map<String, Object> retorno,
      String firmapreviamente, byte[] generateDocXml) throws InSideServiceException;

  public byte[] generateZipFicherosFisicos(Map<String, byte[]> ficheros, JAXBMarshaller marshaller,
      InSideService insideService, String sessionId) throws Exception;

  public byte[] generateZipFicherosFisicosNoInside(Map<String, byte[]> ficheros,
      JAXBMarshaller marshaller, InSideService insideService) throws Exception;

  public byte[] transformarExpedienteDescargaCompletoParaValidarFirma(TipoExpediente tExpAdi,
      String contenido) throws InsideServiceInternalException;

  public void trataAmpliacionFirmaDocumento(TipoDocumentoInsideConMAdicionales docConvertido)
      throws InfoFirmaServiceException, IOException;

  public byte[] tratarFirmaLongevaExpediente(byte[] firmaIndice)
      throws InfoFirmaServiceException, IOException;

  public void controlNIFyDIR3(String NIFs, String dir3) throws InsideWSException;

  public void controlMail(String emails) throws InsideWSException;

  public String tokenXmlBase64(ObjetoExpedienteToken objetoExpedienteToken);

}
