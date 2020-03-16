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

package es.mpt.dsic.inside.ws.operation;

import java.util.List;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
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
import es.mpt.dsic.inside.xml.inside.TokenExpediente;
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
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.RespuestaPdfExpediente;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

public interface InsideOperationWebService {

  public ExpedienteInsideInfo altaExpedienteEni(TipoExpediente expedienteEni,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException;

  public ExpedienteInsideInfo altaExpedienteEniXml(
      TipoExpedienteEniFileInsideConDocumentos expedienteEniXml, String aplicacion)
      throws InsideWSException;

  public ExpedienteInsideInfo cambiaMetadatosExpediente(String identificador,
      TipoMetadatos metadatosEni, TipoMetadatosAdicionales metadatosAdicionales, String aplicacion)
      throws InsideWSException;

  public DocumentoInsideInfo cambiaMetadatosDocumento(String identificador,
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException;

  public ExpedienteInsideInfo modificaExpedienteEni(TipoExpediente expediente,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException;

  public ExpedienteInsideInfo altaCarpetaEnExpediente(String identificadorExpediente,
      TipoCarpetaIndizada identificadorCarpeta, String ruta, String aplicacion)
      throws InsideWSException;

  public ExpedienteInsideInfo borrarCarpetaEnExpediente(String identificadorExpediente,
      String identificadorCarpeta, String ruta, String aplicacion) throws InsideWSException;

  public ExpedienteInsideInfo cambiaEstadoExpediente(String identificador,
      EnumeracionEstados estado, String aplicacion) throws InsideWSException;

  public ExpedienteInsideInfo importarExpediente(String identificador,
      String identificadorImportado, String ruta, String aplicacion) throws InsideWSException;

  public ExpedienteInsideInfo vincularExpediente(String identificador,
      String identificadorVinculado, String ruta, String aplicacion) throws InsideWSException;

  public ExpedienteInsideInfo crearVistaAbiertaExpediente(String identificador, String aplicacion)
      throws InsideWSException;

  public ExpedienteInsideInfo crearVistaCerradaExpediente(String identificador, String aplicacion)
      throws InsideWSException;

  public ExpedienteInsideInfo vincularDocumentosEnExpediente(String identificador,
      List<String> identificadoresDocumentos, String ruta, String aplicacion)
      throws InsideWSException;

  public ExpedienteInsideInfo desvincularDocumentosEnExpediente(String identificador,
      List<String> identificadoresDocumentos, String ruta, String aplicacion)
      throws InsideWSException;

  public ExpedienteInsideInfo cambiarUbicacionEnExpediente(String identificadorExpediente,
      List<String> identificadoresElementos, String rutaOrigen, String rutaDestino,
      String aplicacion) throws InsideWSException;

  public TipoExpedienteInside getExpediente(String identificador, Integer version,
      String aplicacion) throws InsideWSException;

  public TipoExpediente getExpedienteEni(String identificador, String aplicacion)
      throws InsideWSException;

  public TipoExpedienteEniFileInside getExpedienteEniFile(String identificador,
      Boolean visualizacionIndice, String aplicacion) throws InsideWSException;

  public TipoIndice getIndiceExpediente(String identificador, String aplicacion)
      throws InsideWSException;

  public ExpedienteInsideMetadatos getExpedienteMetadatos(String identificador, Integer version,
      String aplicacion) throws InsideWSException;

  public List<TipoVersionInside> getVersionesExpediente(String identificador, String aplicacion)
      throws InsideWSException;

  public DocumentoInsideInfo altaDocumentoOriginal(TipoDocumentoAltaInside documento,
      String aplicacion) throws InsideWSException;

  public DocumentoInsideInfo altaDocumentoDigitalizado(TipoDocumentoAltaInside documento,
      String aplicacion) throws InsideWSException;

  public DocumentoInsideInfo altaDocumentoCopiaParcial(TipoDocumentoAltaInside documento,
      String identificadorDocumentoOrigen, String aplicacion) throws InsideWSException;

  public DocumentoInsideInfo altaDocumentoCambioFormato(TipoDocumentoAltaInside documento,
      String identificadorDocumentoOrigen, String aplicacion) throws InsideWSException;

  public DocumentoInsideInfo altaDocumentoOtros(TipoDocumentoAltaInside documento,
      String aplicacion) throws InsideWSException;

  public DocumentoInsideInfo altaDocumentoEni(TipoDocumento documento,
      TipoDocumentoAsociadoaExpediente asociadoaExpediente,
      TipoMetadatosAdicionales metadatosAdicionales, boolean firmaServidor, String aplicacion)
      throws InsideWSException;

  public DocumentoInsideInfo altaDocumentoEniXml(TipoDocumentoEniFileInside documentoEniFile,
      String aplicacion) throws InsideWSException;

  public DocumentoInsideInfo modificaDocumento(TipoDocumento documento,
      TipoMetadatosAdicionales metadatosAdicionales, String aplicacion) throws InsideWSException;

  public TipoDocumentoInside getDocumento(String identificador, String aplicacion)
      throws InsideWSException;

  public TipoDocumento getDocumentoEni(String identificador, String aplicacion)
      throws InsideWSException;

  public TipoDocumentoEniFileInside getDocumentoEniFile(String identificador, String aplicacion)
      throws InsideWSException;

  public TipoContenido getDocumentoContenido(String identificador, String aplicacion)
      throws InsideWSException;

  public DocumentoInsideMetadatos getMetadatosDocumento(String identificador, Integer version,
      String aplicacion) throws InsideWSException;

  public Integer getNumeroBytesDocumentoFirmado(String identificador, String aplicacion)
      throws InsideWSException;

  public Firmas getFirmasEniCSVDocumento(String identificador, String aplicacion)
      throws InsideWSException;

  public ExpedienteResultadoBusqueda buscarExpedientes(ConsultaWsInside consulta, int limite,
      int pagina, String aplicacion) throws InsideWSException;

  public DocumentoResultadoBusqueda buscarDocumentos(ConsultaWsInside consulta, int limite,
      int pagina, String aplicacion) throws InsideWSException;

  public DocumentoEniFileInside convertirDocumentoAEni(TipoDocumentoConversionInside documento,
      byte[] contenido, Boolean firmar, WSCredentialInside info) throws InsideWSException;

  public ExpedienteEniFileInside convertirExpedienteAEni(TipoExpedienteConversionInside expediente,
      String contenidoFirma, WSCredentialInside info) throws InsideWSException;

  public void eliminarDocumento(String identificador, String aplicacion) throws InsideWSException;

  public void eliminarExpediente(String identificador, String aplicacion) throws InsideWSException;

  public List<ExpedienteInsideInfo> expedientesAsociadoDocumento(String identificador,
      String aplicacion) throws InsideWSException;

  public TipoResultadoVisualizacionDocumentoInside visualizarDocumento(String identificador,
      String aplicacion) throws InsideWSException;

  public RespuestaPdfExpediente visualizarExpediente(byte[] expedienteEni) throws InsideWSException;


  public RespuestaPdfExpediente getPdfExpediente(byte[] expedienteEni) throws InsideWSException;

  public RespuestaPdfExpediente getPdfExpedientePorId(String identificador, String version)
      throws InsideWSException;

  public TokenExpediente getCredencialesAccesoExpediente(String aplicacion, String identificador,
      String emails, String asuntoMail, String dir3, String NIFs, String fechaCaducidad)
      throws InsideWSException;

}
