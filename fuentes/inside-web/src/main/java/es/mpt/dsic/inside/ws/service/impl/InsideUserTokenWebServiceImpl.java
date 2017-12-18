/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.ws.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import es.mpt.dsic.inside.justicia.service.PuntoUnicoJusticiaService;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.remisionnube.service.PuntoUnicoRemisionNubeService;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InSideServicePermisos;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.util.ws.security.CredentialUtil;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.operation.InsideOperationWebService;
import es.mpt.dsic.inside.ws.service.InsideUserTokenWebService;
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
import es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube.PeticionRemisionAJusticiaType;
import es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube.RespuestaRemisionAJusticiaType;
import es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube.RespuestaType;
import es.mpt.dsic.inside.xml.inside.ws.respuestaConsultaEnvioJusticia.RespuestaConsultaEnvioJusticia;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.EnviarAJusticiaResponse;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.ParametrosEnvioJusticia;

@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.InsideUserTokenWebService", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService", portName = "InsideUserTokenWSPort", serviceName = "InsideUserTokenWS")
public class InsideUserTokenWebServiceImpl implements InsideUserTokenWebService {
	protected static final Log logger = LogFactory.getLog(InsideUserTokenWebServiceImpl.class);

	@Autowired
	InsideOperationWebService insideOperationWebService;

	@Autowired
	private InSideService service;

	@Autowired
	private InSideServicePermisos servicePermisos;

	@Autowired
	private CredentialUtil credentialUtil;

	@Resource
	private WebServiceContext wsContext;

	@Autowired
	private InsideUtilService insideUtilService;

	@Autowired
	private PuntoUnicoJusticiaService puntoUnicoJusticiaService;

	@Autowired
	private PuntoUnicoRemisionNubeService servicioRemisionNube;

	@Override
	@Secured("ROLE_ALTA_EXPEDIENTE")
	public ExpedienteInsideInfo altaExpedienteEni(TipoExpediente expedienteEni, TipoMetadatosAdicionales metadatosAdicionales)
			throws InsideWSException {
		try {
			return insideOperationWebService.altaExpedienteEni(expedienteEni, metadatosAdicionales,
					credentialUtil.getAppLogged());
		} catch (InSideServiceException e) {
			throw new InsideWSException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_EXPEDIENTE")
	public ExpedienteInsideInfo altaExpedienteEniXml(TipoExpedienteEniFileInsideConDocumentos expedienteEniFile)
			throws InsideWSException {
		try {
			return insideOperationWebService.altaExpedienteEniXml(expedienteEniFile, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo cambiaMetadatosExpediente(String identificador, TipoMetadatos metadatosEni,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
		try {
			return insideOperationWebService.cambiaMetadatosExpediente(identificador, metadatosEni, metadatosAdicionales,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_DOCUMENTO")
	public DocumentoInsideInfo cambiaMetadatosDocumento(String identificador,
			es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni,
			TipoMetadatosAdicionales metadatosAdicionales) throws InsideWSException {
		try {
			return insideOperationWebService.cambiaMetadatosDocumento(identificador, metadatosEni, metadatosAdicionales,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo modificaExpedienteEni(TipoExpediente expedienteEni, TipoMetadatosAdicionales metadatosAdicionales)
			throws InsideWSException {
		try {
			return insideOperationWebService.modificaExpedienteEni(expedienteEni, metadatosAdicionales,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteInsideInfo altaCarpetaEnExpediente(String identificadorExpediente, TipoCarpetaIndizada carpetaIndizada,
			String ruta) throws InsideWSException {
		try {
			return insideOperationWebService.altaCarpetaEnExpediente(identificadorExpediente, carpetaIndizada, ruta,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteInsideInfo borrarCarpetaEnExpediente(String identificadorExpediente, String identificadorCarpeta,
			String ruta) throws InsideWSException {
		try {
			return insideOperationWebService.borrarCarpetaEnExpediente(identificadorExpediente, identificadorCarpeta, ruta,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo cambiaEstadoExpediente(String identificador, EnumeracionEstados estado) throws InsideWSException {
		try {
			return insideOperationWebService.cambiaEstadoExpediente(identificador, estado, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo importarExpediente(String identificador, String identificadorImportado, String ruta)
			throws InsideWSException {
		try {
			return insideOperationWebService.importarExpediente(identificador, identificadorImportado, ruta,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo vincularExpediente(String identificador, String identificadorVinculado, String ruta)
			throws InsideWSException {
		try {
			return insideOperationWebService.vincularExpediente(identificador, identificadorVinculado, ruta,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo crearVistaAbiertaExpediente(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.crearVistaAbiertaExpediente(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo crearVistaCerradaExpediente(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.crearVistaCerradaExpediente(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo vincularDocumentosEnExpediente(String identificador, List<String> identificadoresDocumentos,
			String ruta) throws InsideWSException {
		try {
			return insideOperationWebService.vincularDocumentosEnExpediente(identificador, identificadoresDocumentos, ruta,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo desvincularDocumentosEnExpediente(String identificador, List<String> identificadoresDocumentos,
			String ruta) throws InsideWSException {
		try {
			return insideOperationWebService.desvincularDocumentosEnExpediente(identificador, identificadoresDocumentos, ruta,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public ExpedienteInsideInfo cambiarUbicacionEnExpediente(String identificadorExpediente,
			List<String> identificadoresElementos, String rutaOrigen, String rutaDestino) throws InsideWSException {
		try {
			return insideOperationWebService.cambiarUbicacionEnExpediente(identificadorExpediente, identificadoresElementos,
					rutaOrigen, rutaDestino, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public TipoExpedienteInside getExpediente(String identificador, Integer version) throws InsideWSException {
		try {
			return insideOperationWebService.getExpediente(identificador, version, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public TipoExpediente getExpedienteEni(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getExpedienteEni(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public TipoExpedienteEniFileInside getExpedienteEniFile(String identificador, Boolean visualizacionIndice)
			throws InsideWSException {
		try {
			return insideOperationWebService.getExpedienteEniFile(identificador, visualizacionIndice,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public TipoIndice getIndiceExpediente(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getIndiceExpediente(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public ExpedienteInsideMetadatos getExpedienteMetadatos(String identificador, Integer version) throws InsideWSException {
		try {
			return insideOperationWebService.getExpedienteMetadatos(identificador, version, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public List<TipoVersionInside> getVersionesExpediente(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getVersionesExpediente(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoOriginal(TipoDocumentoAltaInside documentoAlta) throws InsideWSException {
		try {
			return insideOperationWebService.altaDocumentoOriginal(documentoAlta, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoDigitalizado(TipoDocumentoAltaInside documentoAlta) throws InsideWSException {
		try {
			return insideOperationWebService.altaDocumentoDigitalizado(documentoAlta, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoCopiaParcial(TipoDocumentoAltaInside documentoAlta,
			String identificadorDocumentoOrigen) throws InsideWSException {
		try {
			return insideOperationWebService.altaDocumentoCopiaParcial(documentoAlta, identificadorDocumentoOrigen,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoCambioFormato(TipoDocumentoAltaInside documentoAlta,
			String identificadorDocumentoOrigen) throws InsideWSException {
		try {
			return insideOperationWebService.altaDocumentoCambioFormato(documentoAlta, identificadorDocumentoOrigen,
					credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoOtros(TipoDocumentoAltaInside documentoAlta) throws InsideWSException {
		try {
			return insideOperationWebService.altaDocumentoOtros(documentoAlta, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoEni(TipoDocumento documentoEni, TipoDocumentoAsociadoaExpediente asociadoAExpediente,
			TipoMetadatosAdicionales metadatosAdicionales, boolean firmaServidor) throws InsideWSException {
		try {
			return insideOperationWebService.altaDocumentoEni(documentoEni, asociadoAExpediente, metadatosAdicionales,
					firmaServidor, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_ALTA_DOCUMENTO")
	public DocumentoInsideInfo altaDocumentoEniXml(TipoDocumentoEniFileInside documentoEniFile) throws InsideWSException {
		try {
			return insideOperationWebService.altaDocumentoEniXml(documentoEniFile, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_DOCUMENTO")
	public DocumentoInsideInfo modificaDocumento(TipoDocumento documentoEni, TipoMetadatosAdicionales metadatosAdicionales)
			throws InsideWSException {
		try {
			return insideOperationWebService.modificaDocumento(documentoEni, metadatosAdicionales, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoDocumentoInside getDocumento(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getDocumento(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoDocumento getDocumentoEni(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getDocumentoEni(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoDocumentoEniFileInside getDocumentoEniFile(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getDocumentoEniFile(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoContenido getDocumentoContenido(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getDocumentoContenido(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public DocumentoInsideMetadatos getMetadatosDocumento(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getMetadatosDocumento(identificador, null, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public Integer getNumeroBytesDocumentoFirmado(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getNumeroBytesDocumentoFirmado(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public Firmas getFirmasEniCSVDocumento(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.getFirmasEniCSVDocumento(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public ExpedienteResultadoBusqueda buscarExpedientes(ConsultaWsInside consulta, int limite, int pagina)
			throws InsideWSException {
		try {
			return insideOperationWebService.buscarExpedientes(consulta, limite, pagina, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public DocumentoResultadoBusqueda buscarDocumentos(ConsultaWsInside consulta, int limite, int pagina)
			throws InsideWSException {
		try {
			return insideOperationWebService.buscarDocumentos(consulta, limite, pagina, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public DocumentoEniFileInside convertirDocumentoAEni(TipoDocumentoConversionInside documentoConversion, byte[] bytesContenido,
			Boolean firmar) throws InsideWSException {
		try {
			return insideOperationWebService.convertirDocumentoAEni(documentoConversion, bytesContenido, firmar,
					credentialUtil.getCredentialEeutilUserToken(wsContext));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public ExpedienteEniFileInside convertirExpedienteAEni(TipoExpedienteConversionInside expedienteConversion,
			String contenidoFirma) throws InsideWSException {
		try {
			return insideOperationWebService.convertirExpedienteAEni(expedienteConversion, contenidoFirma,
					credentialUtil.getCredentialEeutilUserToken(wsContext));
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_DOCUMENTO")
	public void eliminarDocumento(String identificador) throws InsideWSException {
		try {
			insideOperationWebService.eliminarDocumento(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_MODIFICA_EXPEDIENTE")
	public void eliminarExpediente(String identificador) throws InsideWSException {
		try {
			insideOperationWebService.eliminarExpediente(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public List<ExpedienteInsideInfo> expedientesAsociadoDocumento(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.expedientesAsociadoDocumento(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	@Secured("ROLE_LEER_DOCUMENTO")
	public TipoResultadoVisualizacionDocumentoInside visualizarDocumento(String identificador) throws InsideWSException {
		try {
			return insideOperationWebService.visualizarDocumento(identificador, credentialUtil.getAppLogged());
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

	@Override
	public RespuestaRemisionAJusticiaType remisionAJusticia(PeticionRemisionAJusticiaType peticionRemisionAJusticiaType)
			throws InsideWSException {

		try {
			if (peticionRemisionAJusticiaType == null) {
				throw new InsideWSException(InsideWsErrors.PETICION_NULA);
			}
			// Control Permisos
			servicePermisos.checkPermisos(peticionRemisionAJusticiaType.getIdexpEni(), null, ObjetoExpedienteInside.class,
					credentialUtil.getAppLogged());

			RespuestaType respuestaType = new RespuestaType();

			ParametrosEnvioJusticia parametroMJU = servicioRemisionNube.getParametrosMJU(
					peticionRemisionAJusticiaType.getDatosRemisionJusticia(), peticionRemisionAJusticiaType.getDir3Juzgado());

			EnviarAJusticiaResponse resultadoEnvioJusticia = puntoUnicoJusticiaService.enviarExpedienteMJU(
					insideUtilService.getMapaExpedienteYDocumentos(peticionRemisionAJusticiaType.getIdexpEni(), null),
					parametroMJU);
			String resultado = insideUtilService.guardarResultadoEnvioJusticia(resultadoEnvioJusticia,
					peticionRemisionAJusticiaType.getIdexpEni(), null, null, parametroMJU.getPresentadorcodigoOrganoRemitente());
			if (!resultadoEnvioJusticia.getResultadoEnvioToJusticia().isACK()) {
				String[] partesDelMensajeError = resultado.split("@");
				if (partesDelMensajeError.length > 1) {
					respuestaType.setDescripcion("ERROR: " + partesDelMensajeError[1]);
				} else {
					respuestaType.setDescripcion("ERROR: desconocido");
				}

			} else {
				respuestaType.setDescripcion(resultado);
			}

			RespuestaRemisionAJusticiaType respuesta = new RespuestaRemisionAJusticiaType();
			respuesta.setRespuesta(respuestaType);
			return respuesta;

		} catch (Exception e) {
			logger.error("Error al remitir expediente a justicia");
			throw InsideExceptionConverter.convertToException(e);
		}

	}

	@Override
	public RespuestaConsultaEnvioJusticia consultaEstadoRemisionAJusticia(String codigoEnvioATEA) throws InsideWSException {

		return this.servicioRemisionNube.consultaEstadoRemisionAJusticia(codigoEnvioATEA);

	}
	
	@Override
	@Secured("ROLE_LEER_EXPEDIENTE")
	public RespuestaPdfExpediente getPdfExpediente(byte[] expedienteEni) throws InsideWSException {
		try {
			return insideOperationWebService.getPdfExpediente(expedienteEni);
		} catch (Exception e) {
			throw InsideExceptionConverter.convertToException(e);
		}
	}

}
