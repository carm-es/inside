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

package es.mpt.dsic.inside.service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;

import es.mpt.dsic.infofirma.service.InfoFirmaService;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaUsuario;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.model.objetos.ObjetoAuditoriaFirmaServidor;
import es.mpt.dsic.inside.model.objetos.ObjetoCredencialEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoElastic;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideDocumentoUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideExpedienteUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadAplicacionEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.ObjetoNumeroProcedimiento;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInsideContenido;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
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
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.model.objetos.filter.ObjetoFilterPageRequest;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.util.UnidadOrganicaRolPortales;
import es.mpt.dsic.inside.service.visualizacion.ResultadoVisualizacionDocumento;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;

public interface InSideService {

	public ObjetoExpedienteInside altaExpediente(ObjetoExpedienteInside expediente, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoExpedienteInside altaExpediente(ObjetoExpedienteInside expediente, boolean signatureServer, String usuario,
			boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside getExpediente(String identificador, boolean visualizacionIndice) throws InSideServiceException;

	public ObjetoExpedienteInside getExpediente(String identificador, Integer version, boolean visualizacionIndice)
			throws InSideServiceException;

	public List<ObjetoInsideVersion> getVersionesExpediente(String identificador) throws InSideServiceException;

	public List<ObjetoInsideVersion> getListaVersionesExpediente(String identificador) throws InSideServiceException;

	public ObjetoExpedienteInsideMetadatos getMetadatosExpediente(String identificador) throws InSideServiceException;

	public ObjetoExpedienteInsideMetadatos getMetadatosExpediente(String identificador, Integer version)
			throws InSideServiceException;

	public ObjetoExpedienteInside modificaExpedienteInside(ObjetoExpedienteInside expediente, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoExpedienteInside modificaExpedienteInside(ObjetoExpedienteInside expediente, boolean signatureServer,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside cambiaMetadatosExpediente(String identificador, ObjetoExpedienteInsideMetadatos metadatos,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside importarExpediente(String identificador, String identificadorImportado, String ruta,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside importarExpediente(ObjetoExpedienteInside expedienteNuevo, String identificadorImportado,
			String ruta, boolean signatureServer, String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside vincularExpediente(String identificador, String identificadorVinculado, String ruta,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside vincularExpediente(ObjetoExpedienteInside expedienteNuevo, String identificadorVinculado,
			String ruta, boolean signatureServer, String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside createOpenViewExpedient(String identifier, String identifierView, String usuario,
			boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside createOpenViewExpedient(String identifier, String identifierView, boolean signatureServer,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside createClosedViewExpedient(String identifier, String identifierView, String usuario,
			boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside createClosedViewExpedient(String identifier, String identifierView, boolean signatureServer,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside vincularDocumentosEnExpediente(String identificadorExpediente,
			List<String> identificadoresDocumentos, String ruta, String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside vincularDocumentosEnExpediente(ObjetoExpedienteInside expediente,
			Collection<ObjetoDocumentoInside> documentos, String ruta, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoExpedienteInside vincularDocumentosEnExpedienteWithoutSave(ObjetoExpedienteInside expediente,
			Collection<ObjetoDocumentoInside> documentos, String ruta, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoExpedienteInside desvincularDocumentosEnExpediente(String identificadorExpediente,
			List<String> identificadoresDocumentos, String ruta, String usuario, boolean online) throws InSideServiceException;

	public ObjetoExpedienteInside cambiarUbicacionEnExpediente(String identificadorExpediente,
			List<String> identificadoresElementos, String rutaOrigen, String rutaDestino, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoExpedienteInside altaCarpeta(ObjetoExpedienteInside identificadorExpediente,
			ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada, String ruta, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoExpedienteInside borrarCarpeta(String identificadorExpediente, String identificadorCarpeta, String ruta)
			throws InSideServiceException;

	public ObjetoExpedienteInside setEstadoExpediente(String identificador,
			ObjetoExpedienteInsideMetadatosEnumeracionEstados estado, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoExpedienteInside setEstadoExpediente(String identificador,
			ObjetoExpedienteInsideMetadatosEnumeracionEstados estado, boolean signatureServer, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoDocumentoInside altaDocumento(ObjetoDocumentoInside documento, String usuario, boolean online)
			throws InSideServiceException;

	public ObjetoDocumentoInside altaDocumento(ObjetoDocumentoInside documento, String identificadorExpediente, String ruta,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoDocumentoInside validarDocumento(ObjetoDocumentoInside documento) throws InSideServiceException;

	public ObjetoExpedienteInside validarExpediente(ObjetoExpedienteInside expediente) throws InSideServiceException;

	public ObjetoExpedienteInside obtenerVisualizacionIndiceSiActivo(ObjetoExpedienteInside expediente, boolean estamparImagen,
			boolean estamparNombreOrganismo, List<String> lineasNombreOrganismo, boolean estamparPie, String textoPie,
			boolean expedienteExterno) throws InSideServiceException;

	public ObjetoExpedienteInside obtenerVisualizacionIndiceSiActivo(ObjetoExpedienteInside expediente)
			throws InSideServiceException;

	public ObjetoDocumentoInside getDocumento(String identificador) throws InSideServiceException;

	public ObjetoDocumentoInsideContenido getDocumentoContenido(String identificador, byte[] bytesContenido)
			throws InSideServiceException;

	public ObjetoDocumentoInsideMetadatos getMetadatosDocumento(String identificador) throws InSideServiceException;

	public Integer getNumeroBytesDocumento(String identificador) throws InSideServiceException;

	public ObjetoDocumentoInside cambiaMetadatosDocumento(String identificador, ObjetoDocumentoInsideMetadatos metadatos,
			String usuario, boolean online) throws InSideServiceException;

	public ObjetoDocumentoInside modificaDocumento(ObjetoDocumentoInside documento, String usuario, boolean online)
			throws InSideServiceException;

	ObjetoDocumentoInside modificaDocumento(ObjetoDocumentoInside documento, String identificadorExpediente, String ruta,
			String usuario, boolean online) throws InSideServiceException;

	public List<FirmaInside> getFirmasCSVDocumentoInside(String identificador) throws InSideServiceException;

	public ObjetoDocumentoInside firmaServidorDocumento(ObjetoDocumentoInside documento, String usuario)
			throws InSideServiceException;

	public ResultadoBusquedaInside<ObjetoExpedienteInsideMetadatos> buscarExpedientes(ConsultaInside consulta, int limite,
			int pagina) throws InSideServiceException;

	public ResultadoBusquedaInside<ObjetoDocumentoInsideMetadatos> buscarDocumentos(ConsultaInside consulta, int limite,
			int pagina) throws InSideServiceException;

	public List<ResultadoValidacionExpediente> validaExpedienteEniFile(byte[] expediente,
			List<OpcionValidacionExpediente> opciones, ObjetoExpedienteInside expedienteInside) throws InSideServiceException;

	public List<ResultadoValidacionExpediente> validaExpedienteEniFile(byte[] expediente,
			List<OpcionValidacionExpediente> opciones, ObjetoExpedienteInside expedienteInside, boolean validarSIA)
			throws InSideServiceException;

	public List<ResultadoValidacionDocumento> validaDocumentoEniFile(byte[] documento, List<OpcionValidacionDocumento> opciones,
			ObjetoDocumentoInside documentoInside) throws InSideServiceException;

	public ResultadoVisualizacionDocumento visualizaDocumentoInside(ObjetoDocumentoInside documento, boolean estamparImagen,
			boolean estamparNombreOrganismo, List<String> lineasNombreOrganismo, boolean estamparPie, String textoPie,
			byte[] bytesContenido) throws InSideServiceException;

	public ResultadoVisualizacionDocumento visualizaDocumentoInside(ObjetoDocumentoInside documento, byte[] bytesContenido)
			throws InSideServiceException;

	public void deleteDocument(String identificador) throws InSideServiceException;

	public void deleteExpedient(String identificador) throws InSideServiceException;

	public <T extends ObjetoInside<?>> List<ObjetoExpedienteInside> getExpedientesAsociadosDocumento(Class<T> tipo,
			String identificador) throws InSideServiceException;

	public void firmarIndiceExpediente(ObjetoExpedienteInside expediente, WSCredentialInside info, String usuario)
			throws InSideServiceException;

	public void establecerFirmaExpediente(ObjetoExpedienteInside expediente, String idFirma, FirmaInsideTipoFirmaEnum tipoFirma,
			byte[] firma, String ref);

	public void establecerFirmaExpedienteANodoFirmaBase64(ObjetoExpedienteInside expediente, String idFirma,
			FirmaInsideTipoFirmaEnum tipoFirma, byte[] firma, String ref);

	public ObjetoDocumentoInside documentoConversionToInside(TipoDocumentoConversionInside documentoConversion,
			InfoFirmaService infoFirmaService, byte[] contenido, boolean firmar, WSCredentialInside info)
			throws InsideConverterException;

	/**
	 * Evalua si el usuario tiene permiso sobre el objeto documento/expediente
	 * 
	 * @param obj
	 *            Un objeto de tipo documento/expediente
	 * @param usuario
	 *            El usuario gestionando ya sea Online o Web Service
	 * @return true si el usuario tiene permiso sobre el objeto
	 * @throws InSideServiceException
	 */
	boolean permisoObjetoUnidad(ObjetoInside<?> obj, String identificadorUsuario, boolean online) throws InSideServiceException;

	/**
	 * Obtenemos todos los expedientes asociados al dir3 del usuario
	 * 
	 * @param usuario
	 *            Usuario de la aplicación
	 * @param soloUnidadActiva
	 *            solo queremos los expedientes asociados a su unidad activa
	 * @return Lista de expediente
	 * @throws InSideServiceException
	 */
	List<ObjetoInsideExpedienteUnidad> getExpedientesUnidad(ObjetoInsideUsuario usuario, boolean soloUnidadActiva)
			throws InSideServiceException;

	/**
	 * Obtenemos todos los expedientes asociados al dir3 del usuario
	 * 
	 * @param usuario
	 *            Usuario de la aplicación
	 * @param soloUnidadActiva
	 *            solo queremos los expedientes asociados a su unidad activa
	 * @return Lista de expediente
	 * @throws InSideServiceException
	 */
	List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadAutocompleter(ObjetoInsideUsuario usuario, boolean soloUnidadActiva)
			throws InSideServiceException;

	/**
	 * Objetenos todos los documentos asociados al dir3 del usuarios
	 * 
	 * @param usuario
	 *            Usuario de la aplicación
	 * @return Lista de documentos
	 * @throws InSideServiceException
	 */
	List<ObjetoInsideDocumentoUnidad> getDocumentosUnidad(ObjetoInsideUsuario usuario, boolean soloUnidadActiva)
			throws InSideServiceException;

	ObjetoExpedienteToken getTokenByUsuarioExpediente(ObjetoExpedienteToken usuarioToken) throws InSideServiceException;

	ObjetoExpedienteToken getTokenByData(ObjetoExpedienteToken usuarioToken) throws InSideServiceException;

	void saveToken(ObjetoExpedienteToken usuarioToken) throws InSideServiceException;

	void saveAuditoriaToken(ObjetoAuditoriaToken objectAuditoriaToken) throws InSideServiceException;

	List<ObjetoInsideUsuario> getUsuarios() throws InSideServiceException;
	
	ResultadoBusquedaUsuario getUsuarios(ObjetoFilterPageRequest data) throws InSideServiceException;

	ObjetoInsideUsuario altaUsuario(ObjetoInsideUsuario data) throws InSideServiceException;

	ObjetoInsideUsuario getUsuario(String nif) throws InSideServiceException;

	ObjetoInsideUsuario bajaUsuario(ObjetoInsideUsuario data) throws InSideServiceException;

	List<ObjetoInsideAplicacion> getAplicaciones() throws InSideServiceException;

	ObjetoInsideAplicacion altaAplicacion(ObjetoInsideAplicacion data) throws InSideServiceException;

	ObjetoInsideAplicacion bajaAplicacion(ObjetoInsideAplicacion data) throws InSideServiceException;

	/**
	 * Asociamos una unidad orgánica (dir3) a una aplicacion (Usuario Web
	 * Service)
	 * 
	 * @param idUnidad
	 *            Código Unidad Orgánica
	 * @param idAplicacion
	 *            Nombre de la aplicación
	 * @return Mensaje informando del resultado de la operación
	 * @throws InSideServiceException
	 */
	ObjetoInsideUnidad asociarUnidadAplicacion(String idUnidad, String numeroProcedimiento, String idAplicacion)
			throws InSideServiceException;

	/**
	 * Eliminamos una unidad orgánica (dir3) y su procedimiento con una
	 * aplicacion (Usuario Web Service)
	 * 
	 * @param idUnidad
	 *            Código Unidad Orgánica
	 * @param idAplicacion
	 *            Nombre de la aplicación
	 * @return Mensaje informando del resultado de la operación
	 * @throws InSideServiceException
	 */
	void eliminarUnidadAplicacion(String idUnidad, String numeroProcedimiento, String idAplicacion) throws InSideServiceException;

	/**
	 * Aosciamos una unidad orgánica (dir3) a un usuario (Usuario Online)
	 * 
	 * @param idUnidad
	 *            Código Unidad Orgánica
	 * @param idUsuario
	 *            Nif del usuario
	 * @return Mensaje informando del resultado de la operación
	 * @throws InSideServiceException
	 */
	ObjetoInsideUnidad asociarUnidadUsuario(String idUnidad, String numeroProcedimiento, String idUsuario)
			throws InSideServiceException;

	/**
	 * Eliminamos una unidad orgánica (dir3) y su procedimiento a un usuario
	 * (Usuario Online)
	 * 
	 * @param idUnidad
	 *            Código Unidad Orgánica
	 * @param idUsuario
	 *            Nif del usuario
	 * @return Mensaje informando del resultado de la operación
	 * @throws InSideServiceException
	 */
	void eliminarUnidadUsuario(String idUnidad, String numeroProcedimiento, String idUsuario) throws InSideServiceException;

	/**
	 * Obtenemos las unidades orgánica de una aplicación (Usuario Web Service),
	 * todas a true nos recoge las activas e inactivas, todas a false solo la
	 * activa
	 * 
	 * @param idAplicacion
	 *            Nombre de la aplicación
	 * @return Lista de Unidades Orgánicas
	 * @throws InSideServiceException
	 */
	List<ObjetoInsideUnidad> getUnidadesAplicacion(String idAplicacion, boolean todas) throws InSideServiceException;

	/**
	 * Obtenemos las unidades orgánicas de un usuario (Usuario Online), todas a
	 * true nos recoge las activas e inactivas, todas a false solo la activa
	 * 
	 * @param idUsuario
	 *            Nif del usuario
	 * @return Lista de Unidades Orgánicas
	 * @throws InSideServiceException
	 */
	List<ObjetoInsideUnidad> getUnidadesUsuario(String idUsuario, boolean todas) throws InSideServiceException;

	/**
	 * Comprueba que el usuario posee unidades orgánicas activas
	 * 
	 * @param usuario
	 * @return
	 * @throws InSideServiceException
	 */
	boolean comprobarUnidadesOrganicasActivasUsuario(Object usuario) throws InSideServiceException;

	/**
	 * Comprueba que si el expediente tiene vistas asociadas
	 * 
	 * @param usuario
	 * @return
	 * @throws InSideServiceException
	 */
	boolean checkAssociatedViewsExpedient(String identificadorExpediente) throws InSideServiceException;

	/**
	 * Devuelve las vistas asociadas a un expediente
	 * 
	 * @param usuario
	 * @return
	 * @throws InSideServiceException
	 */
	List<ObjetoInsideExpedienteUnidad> getViewsExpedient(String identificadorExpediente) throws InSideServiceException;

	ObjetoInsideAplicacion actualizarCredencialesEeetuilApp(String app, ObjetoCredencialEeutil credential)
			throws InSideServiceException;

	public void guardarRespuestaRemisionJusticiaExpediente(ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia,
			String identificadorExpediente, String version) throws InSideServiceException;

	ObjetoInsideUnidadAplicacionEeutil getApplicationEeutilByUser(ObjetoInsideUsuario user) throws InSideServiceException;

	public Map<String, String> obtenerDocsExpInside(List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> list,
			String path);

	public Map<String, String> obtenerDocsExpTipo(List<Object> datos, String path);

	public List<String> checkRepeatDocsExpTipo(List<Object> datosOrigen, List<Object> datosDestino) throws InSideServiceException;

	public Collection<String> correspondencia(Object objExp, List<String> identificadoresDocumento);

	public List<String> getListIdentifierDocsInside(List<ObjetoDocumentoInside> documentos);

	public List<String> getListIdentifierDocsTipo(List<TipoDocumentoInsideConMAdicionales> documentos);

	public boolean checkSignatureServerByUser(ObjetoInsideUsuario objetoInsideUsuario);

	public ObjetoInsideUnidadAplicacionEeutil crearActualizarUnidadAplicacionEeutil(String codigoUnidad, String aplicacion,
			String password) throws InSideServiceException;

	public List<ObjetoInsideUnidadAplicacionEeutil> getListUnidadAplicacionEeutil() throws InSideServiceException;

	public ObjetoInsideUnidadAplicacionEeutil getUnidadAplicacionEeutil(String codigoUnidad) throws InSideServiceException;

	public ObjetoInsideAplicacion crearActualizarSerialNumberCertificado(String idAplicacion, String serialNumberCertificado)
			throws InsideServiceStoreException;

	public void guardarSolicitudAccesoExpediente(ObjetoSolicitudAccesoExpediente objetoSolicitudAccesoExpediente)
			throws InSideServiceException;

	void guardarRespuestaRemisionJusticiaExpedienteNoInside(ObjectInsideRespuestaEnvioJusticia objectInsideRespuestaEnvioJusticia,
			String identificadorExpediente, String version) throws InSideServiceException;

	public List<String> listaNumeroProcedimiento() throws InsideServiceStoreException;

	public String altaNumeroProcedimiento(String numeroProcedimiento) throws InsideServiceStoreException;

	public ObjetoEstructuraCarpetaInside getEstructuraCarpeta(ObjetoInsideUsuario usuario) throws InSideServiceException;

	public List<ObjetoEstructuraCarpetaInside> listaEstructuraCarpeta(String identificadorEstructura)
			throws InsideServiceStoreException;

	public ObjetoEstructuraCarpetaInside altaEstructuraCarpeta(ObjetoEstructuraCarpetaInside objetoEstructuraCarpetaInside)
			throws InsideServiceStoreException;

	public void deleteEstructuraCarpeta(String identificadorEstructura) throws InsideServiceStoreException;

	void saveComunicacionTokenExpediente(ObjetoComunicacionTokenExpediente objetoComunicacionTokenExpediente)
			throws InsideServiceStoreException;

	List<ObjetoComunicacionTokenExpediente> getComunicacionesTokenExpedienteActivas(int maximoResultados,
			int numeroMaximoIntentos) throws InsideServiceStoreException;

	void saveSolicitudAccesoExpediente(ObjetoSolicitudAccesoExpediente objetoSolicitudAccesoExpediente)
			throws InsideServiceStoreException;

	String getUrlDestinoPeticionAccesoExpediente(String dir3) throws InSideServiceException;

	List<ObjetoSolicitudAccesoExpediente> getSolicitudesAccesoExpediente(ObjetoInsideUsuario objetoInsideUsuario)
			throws InsideServiceStoreException, JAXBException, XMLStreamException;

	ObjetoSolicitudAccesoExpediente getSolicitudAccesoExpediente(String id)
			throws InsideServiceStoreException, JAXBException, XMLStreamException;

	public void saveAuditoriaAccesoDocumento(ObjetoAuditoriaAccesoDocumento objetoAuditoriaAccesoDocumento)
			throws InsideServiceStoreException;

	public ObjetoSolicitudAccesoExpAppUrl saveSolicitudAccesoExpAppUrl(
			ObjetoSolicitudAccesoExpAppUrl objetoSolicitudAccesoExpAppUrl) throws InsideServiceStoreException;

	public ObjetoSolicitudAccesoExpAppUrl getSolicitudAccesoExpAppUrlPorDir3(String dir3Padre) throws InsideServiceStoreException;

	public ObjectInsideRespuestaEnvioJusticia getRespuestaEvioJusticaByCodigoEnvio(
			ObjectInsideRespuestaEnvioJusticia respuestaEnvioJusticia) throws InSideServiceException;

	public ObjectInsideRespuestaEnvioJusticia getRespuestaEnvioJusticiaByCodigoEnvio(String codigoEnvio)
			throws InSideServiceException;

	public void validarFirma(byte[] firma) throws InSideServiceException;

	public void validarFirma(byte[] firma, byte[] contenido) throws InSideServiceException;

	public void getDocumentoEnIndiceExpediente(List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados,
			String elemNombre, ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado contenidoDoc);

	public void actualizarAsociacionDocumentoUnidad(ObjetoDocumentoInside documento, String usuario, boolean online)
			throws InsideServiceStoreException;

	public void validateObjetoInsideIdentificador(ObjetoInside<?> objeto) throws InSideServiceException;

	public List<ObjetoElastic> getObjetosElastic(String unidadOrganica) throws InsideServiceStoreException;

	public int getDefaultIdByDir3(String unidadOrganica, boolean isDocument) throws InsideServiceStoreException;

	public void saveAuditoriaFirmaServidor(ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor);

	public List<ObjetoAuditoriaAcceso> getAuditoriaAccesoDocumento(ObjetoInsideUsuario objetoInsideUsuario)
			throws InsideServiceStoreException;

	List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadPorMetadatos(String nif,
			TipoMetadatosAdicionales tipoMetadatosAdicionales) throws InSideServiceException;

	public List<ObjetoInsideRol> getInsideRoles() throws InsideServiceStoreException;
	
	public List<ObjetoNumeroProcedimiento> getNumeroProcedimientoList() throws InsideServiceStoreException;

	public List<ObjetoInsideUsuario> getUsuariosUnidadOrganica(	ObjetoInsideUsuario usuarioEnSesion, Locale locale)throws InsideServiceStoreException;

	public List<ObjetoComunicacionTokenExpediente> getComunicacionesTokenExpedienteUnidadOrganicaUsuario(
			ObjetoInsideUsuario objetoInsideUsuario) throws InsideServiceStoreException;

	public ObjetoComunicacionTokenExpediente getComunicacionTokenExpedientePorId(String id) throws InsideServiceStoreException;
	
	public List<UnidadOrganicaRolPortales> getlistaUnidadesRolesPortales(String nif) throws InsideServiceStoreException;

	public ObjetoInsideRol getRolUnidadUsuario(String codigoUnidad, String nif) throws InSideServiceException;

	ObjetoSolicitudAccesoExpediente getSolicitudAccesoExpedientePorIdPeticion(String idPeticion)
			throws JAXBException;


}
