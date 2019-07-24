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

package es.mpt.dsic.inside.web.util;

import java.util.Locale;

public class WebConstants {

  public static final String VERSION_NTI =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e";

  public static final String KEY_ERROR_LOGIN_APP = "error.label.loginApp";

  public static final String KEY_ERROR_LOGIN = "error.login";

  public static final String KEY_ERROR_ACCESS_DENIED = "error.access.denied";

  public static final String KEY_TIPO_RELACION_RANGO = "combo.opcion.rango";

  public static final String KEY_TIPO_RELACION_IGUAL = "combo.opcion.igual";

  public static final String KEY_TIPO_RELACION_EXPRESION = "combo.opcion.expresion";

  public static final String KEY_TIPO_RELACION_MAYOR = "combo.opcion.mayor";

  public static final String KEY_TIPO_RELACION_MENOR = "combo.opcion.menor";

  public static final String KEY_ERROR_DATOS_NO_VALIDOS = "error.msg.datosNoValidos";

  public static final String KEY_ERROR_DATOS_NO_VALIDOS_PARAMETERS = "error.msg.datosNoValidos2";

  public static final String MSG_CONTENT_DOC_NO_VALID = "error.msg.contenidoDocError";

  // locale por defecto
  public static final Locale LOCALE_DEFAULT = new Locale("es", "ES");
  public static final String LANGUAGE_TAG_DEFAULT = "es-ES";
  public static final String ENCODING_DEFAULT = "UTF-8";

  public static final String USUARIO_SESSION = "usuarioSesion";
  public static final String USUARIO_SESSION_EXPIRED = "usuarioSesion.expired";
  public static final String CONTEXT_PATH_INSIDE = "/inside";

  // FORMATO FECHA
  public static final String FORMATO_FECHA_DEFECTO = "dd-MM-yyyy";
  public static final String FORMATO_FECHA_DEFECTO2 = "yyyy-MM-dd";

  /** maximo de registros a devolver en autocomplete */
  public static final int MAX_RESULTS_AUTOCOMPLETE = 10;

  /** niveles para mostrar mensajes por pantalla a los usuarios */
  public static final int MESSAGE_LEVEL_SUCCESS = 1;
  public static final int MESSAGE_LEVEL_INFO = 2;
  public static final int MESSAGE_LEVEL_WARNING = 3;
  public static final int MESSAGE_LEVEL_ERROR = 4;

  /** Nivel de mensaje para errores en dialog. */
  public static final int MESSAGE_LEVEL_ERROR_DIALOG = 5;

  // Keys de los mensajes
  public static final String MSG_KEY_EXPED_ERROR_FECHA_INICIO = "expediente.error.fecha.inicio";
  public static final String MSG_KEY_ERROR_GENERIC = "error.generic";
  public static final String MSG_KEY_DOC_VISUALIZAR_NOMBRE_DOCENI =
      "visualizarDocumento.nombre.doceni";

  public static final String MSG_KEY_DOC_DOCENI_NO_VALIDO = "expediente.error.documento.novalido";
  public static final String MSG_KEY_DOC_DOCENI_ID_REPETIDO =
      "expediente.error.documento.idrepetido";
  public static final String MSG_KEY_EXP_EXPENI_NO_VALIDO = "expediente.error.expediente.novalido";
  public static final String MSG_KEY_EXP_ADJUNTAR_NO_VALIDO = "expediente.error.adjuntar.novalido";

  public static final String MSG_KEY_DOC_DOCENI_MAX_SIZE = "expediente.error.documento.limit";

  // generar expediente
  public static final String MSG_GENERAR_EXP_OK = "generarExpediente.ok";
  public static final String MSG_GENERAR_EXP_CARPETAS_MISMO_ID =
      "generarExpediente.carpetas.mismoId";

  // Mensajes validacion expediente
  public static final String MSG_ERROR_VALIDACION_EXPE_SIN_FIRMA =
      "Validación incorrecta : cvc-complex-type.2.4.b: The content of element 'eniconexpind:CarpetaIndizada' is not complete";
  public static final String MSG_ERROR_VALIDACION_EXPE_SIN_FIRMA_ADITIONAL =
      "Validación incorrecta : cvc-complex-type.2.4.b: The content of element 'ns2:indice' is not complete";
  public static final String MSG_VALIDACION_EXP_CORRECTA = "Validacion correcta";
  public static final String MSG_VALIDACION_EXP_FORMATO_ERROR = "validarExpedient.formatoError";
  public static final String MSG_VALIDACION_EXP_CARPETA_INCOMPLETA =
      "validarExpedient.carpetaIncompleta";
  public static final String MSG_VALIDACION_EXP_CARPETA_INCOMPLETA_MENSAJE =
      "validarExpedient.carpetaIncompleta.mensaje";
  public static final String MSG_VALIDACION_EXP_INDICE_INCOMPLETO =
      "validarExpedient.indiceImcompleto";
  public static final String MSG_VALIDACION_EXP_INDICE_INCOMPLETO_MENSAJE =
      "validarExpedient.indiceImcompleto.mensaje";

  // importar expediente
  public static final String MSG_IMPORTAR_EXP_FALTA_EXP = "importarExpediente.error.faltaExp";
  public static final String MSG_IMPORTAR_EXP_EXP_NO_VALIDO =
      "importarExpediente.error.expNoValido";
  public static final String MSG_IMPORTAR_EXP_CORRESP_NO_VALIDA =
      "importarExpediente.error.correspNoValida";
  public static final String MSG_IMPORTAR_EXP_SIN_FIRMAS = "importarExpediente.error.sinFirmas";
  public static final String MSG_IMPORTAR_EXP_OK = "importarExpediente.ok";
  public static final String MSG_IMPORTAR_EXP_GUARDAR_OK = "importarExpediente.guardar.ok";
  public static final String MSG_IMPORTAR_EXP_ACTUALIZAR_OK = "importarExpediente.actualizar.ok";
  public static final String MSG_IMPORTAR_EXP_NO_VALIDO_SIN_FIRMAS_DOC =
      "importarExpediente.error.expNoValido.sin.firmas.doc";
  public static final String MSG_IMPORTAR_EXP_IMPORTAR_EXP_EXISTE =
      "importarExpediente.error.expImportarExpExiste";
  public static final String MSG_IMPORTAR_EXP_ID_NO_VALIDO =
      "importarExpediente.error.identificador.expNoValido";

  // generar documento
  public static final String MSG_GENERAR_DOC_OK = "generarDocumento.ok";
  public static final String MSG_GENERAR_DOC_FIRMADO_OK = "generarDocumento.firmado.ok";
  public static final String MSG_GENERAR_DOC_ERROR_METADATOS = "generarDocumento.error.metadatos";
  public static final String MSG_GENERAR_DOC_ERROR_XML = "generarDocumento.error.xml";

  // importar documento
  public static final String MSG_IMPORTAR_DOC_EXP_NO_ENCONTRADO =
      "importarDocumento.error.expNoEncontrado";
  public static final String MSG_IMPORTAR_DOC_NO_VALIDO = "importarDocumento.error.docNoValido";
  public static final String MSG_IMPORTAR_DOC_ID_NO_VALIDO =
      "importarDocumento.error.identificador.docNoValido";
  public static final String MSG_IMPORTAR_DOC_EXISTE_EN_INDICE =
      "importarDocumento.error.existeEnIndice";
  public static final String MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_EXP =
      "importarDocumento.error.docUsuarioNoAsociadoExp";
  public static final String MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_DOC =
      "importarDocumento.error.docUsuarioNoAsociadoDoc";
  public static final String MSG_IMPORTAR_DOC_USUARIO_NO_ASOCIADO_DOC_IMPORTAR =
      "importarDocumento.error.docUsuarioNoAsociadoDocImportar";
  public static final String MSG_IMPORTAR_DOC_IMPORTAR_DOC_EXISTE =
      "importarDocumento.error.docImportarDocExiste";
  public static final String MSG_IMPORTAR_DOC_USUARIO_NO_UNIDADES_ORGANICAS =
      "importarDocumento.error.docUsuarioNoUnidadesOrganicas";
  public static final String MSG_IMPORTAR_DOC_OK = "importarDocumento.ok";
  public static final String MSG_IMPORTAR_DOC_EXP_OK = "importarDocumento.enExpediente.ok";
  public static final String MSG_GUARDAR_DOC_OK = "guardarDocumento.ok";
  public static final String MSG_ACTUALIZAR_DOC_OK = "actualizarDocumento.ok";
  public static final String MSG_IMPORTAR_DOC_ERROR = "importarDocumento.error";
  public static final String MSG_BAJA_DOC_VINCULADO =
      "documentosAlmacenados.bajdoc.vinculado.error";
  public static final String MSG_BAJA_DOC_OK = "documentosAlmacenados.bajdoc.ok";
  public static final String MSG_TAMANIO_MAS_2_MB = "documentosAlmacenados.tamanio.mas2MEGAS";
  public static final String MSG_PREVISUALIZACION_NO_DISPONIBLE =
      "documentosAlmacenados.previsualizacion.nodisponible";
  public static final String MSG_IMPORTAR_DOCUMENTO_NO_EXISTE =
      "importarDocumento.error.docNoExise";
  public static final String MSG_IMPORTAR_DOCUMENTO_DISTINTO_HASH =
      "importarDocumento.error.distintoHash";

  // validar documento
  public static final String MSG_VALIDACION_DOC_ADICIONALES = "validacionDocumento.conAdicionales";

  public static final String MSG_FILE_UPLOAD_ERROR = "error.fileUpload";

  public static final String MSG_EXP_ALMACENADOS_GENERATE_TOKEN =
      "expedientesAlmacenados.generarToken.error";
  public static final String MSG_BAJA_EXP_OK = "expedientesAlmacenados.bajexp.ok";

  public static final String MSG_EXP_PUESTADISPOSICION_NO_VALIDO =
      "puestaDisposicion.token.novalido";

  public static final String MSG_EXP_PUESTADISPOSICION_NO_VALIDO_FECHACADUCIDAD =
      "puestaDisposicion.token.novalidofechacaducidad";

  public static final String MSG_EXP_PUESTADISPOSICION_NO_VALIDO_NIFNOPERMITIDO =
      "puestaDisposicion.token.novalidonifnopermitido";

  public static final String MSG_EXP_PUESTADISPOSICION_NO_VALIDO_DIR3NOPERMITIDO =
      "puestaDisposicion.token.novalidodir3nopermitido";

  public static final String MSG_EXP_PUESTADISPOSICION_NO_VALIDO_USUARIO_NO_ALTA =
      "puestaDisposicion.token.novalidodir3usuarionoalta";

  public static final String TYPE_CERTIFICATE_DEFAULT = "Default";
  public static final String TYPE_CERTIFICATE_PADES = "PAdEStri";
  public static final String TYPE_CERTIFICATE_XADES_DETACHED = "XAdEStri Detached";
  public static final String TYPE_CERTIFICATE_XADES_ENVELOPED = "XAdEStri Enveloped";
  public static final String TYPE_CERTIFICATE_CADES = "CAdEStri";

  // SUFIJO FICHERO DE ACTA DE INGRESO
  public static final String ACTA_DE_INGRESO_SUFIJO = "_actaDeIngreso";

  // SUFIJO FICHERO DE JUSTIFICANTE DE REGISTRO ELECTRONICO
  public static final String JUSTIFICANTE_REGISTRO_ELECTRONICO_SUFIJO = "_justificanteREC";

  public static final String FORMAT_PDF = ".pdf";
  public static final String FORMAT_XML = ".xml";
  public static final String FORMAT_XSIG = ".xsig";
  public static final String FORMAT_ZIP = ".zip";
  public static final String FORMAT_TIF = ".tif";
  public static final String MIME_TXT = "text/plain";
  public static final String ALGORITMO_MD5 = "MD5";

  // REMISION EN LA NUBE
  public static final String MSG_REMISION_TOKEN_SOLICITUD_NUBE_EXP_ERROR_BUSCAR_SOLICITUDES =
      "solicitudAccesoExpediente.remitirToken.mensaje.error.buscar.solicitudes";
  public static final String MSG_REMISION_TOKEN_SOLICITUD_NUBE_EXP_INFO_BUSCAR_SOLICITUDES =
      "solicitudAccesoExpediente.remitirToken.mensaje.info.buscar.solicitudes";
  public static final String MSG_REMISION_TOKEN_SOLICITUD_NUBE_EXP_OK =
      "solicitudAccesoExpediente.remitirToken.mensaje.ok";
  public static final String MSG_REMISION_TOKEN_SOLICITUD_NUBE_EXP_ERROR_GENERAR_TOKEN =
      "solicitudAccesoExpediente.remitirToken.mensaje.error.generar.token";
  public static final String MSG_REMISION_TOKEN_SOLICITUD_NUBE_EXP_WARNING_FECHA_CADUCIDAD =
      "solicitudAccesoExpediente.remitirToken.mensaje.warning.fecha.anterior";
  public static final String MSG_REMISION_TOKEN_SOLICITUD_NUBE_EXP_ENVIADO_A_FECHA =
      "solicitudAccesoExpediente.remitirToken.mensaje.enviado.ok.fecha";
  public static final String MSG_REMISION_AUDITORIA_ACCESO_DOCUMENTO_ERROR =
      "auditoria.acceso.documento.error";
  public static final String MSG_REMISION_AUDITORIA_ACCESO_DOCUMENTO_NO_RESULTADOS =
      "auditoria.acceso.documento.no.resultados";
  public static final String MSG_REMISION_TOKEN_SOLICITUD_NUBE_EXP_WARNING_FALTA_ID_EXPEDIENTE =
      "solicitudAccesoExpediente.remitirToken.warn.falta.id.expediente";
  public static final String MSG_REMISION_COMUNICACIONES_TOKEN_EXPEDIENTE_ERROR =
      "comunicaciones.token.expediente.usuario.error";
  public static final String MSG_REMISION_COMUNICACIONES_TOKEN_EXPEDIENTE_NO_RESULTADOS =
      "comunicaciones.token.expediente.usuario.no.resultados";
  public static final String MSG_REMISION_COMUNICACIONES_TOKEN_EXPEDIENTE_ERROR_RECUPERAR_EXPEDIENTE =
      "comunicaciones.token.expediente.usuario.error.sw.remitente";
  public static final String MSG_REMISION_COMUNICACIONES_TOKEN_EXPEDIENTE_ERROR_RECUPERAR_DOCUMENTO =
      "comunicaciones.token.expediente.usuario.error.sw.remitente.documento";
  public static final String MSG_REMISION_COMUNICACIONES_TOKEN_EXPEDIENTE_ERROR_DESTINO_NOURL =
      "comunicaciones.token.expediente.usuario.error.sw.destino.nourl";
  public static final String MSG_REMISION_COMUNICACIONES_TOKEN_EXPEDIENTE_ERROR_DESTINO_ERROR_SW_DESTINO =
      "comunicaciones.token.expediente.usuario.error.sw.destino.error.sw.destino";


  private WebConstants() {}
}
