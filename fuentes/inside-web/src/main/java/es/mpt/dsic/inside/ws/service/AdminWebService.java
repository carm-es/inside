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

package es.mpt.dsic.inside.ws.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;

import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsErrorInterceptor;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsIndiceExpedienteSignerInterceptor;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.xml.inside.ws.aplicacion.Aplicacion;
import es.mpt.dsic.inside.xml.inside.ws.aplicacion.InfAdicional;
import es.mpt.dsic.inside.xml.inside.ws.estructuraCarpeta.EstructuraCarpeta;
import es.mpt.dsic.inside.xml.inside.ws.filter.FilterPageRequest;
import es.mpt.dsic.inside.xml.inside.ws.numeroProcedimiento.NumeroProcedimiento;
import es.mpt.dsic.inside.xml.inside.ws.respuestaEnvioJusticia.RespuestaEnvioJusticia;
import es.mpt.dsic.inside.xml.inside.ws.solicitudAccesoExpAppUrl.SolicitudAccesoExpAppUrl;
import es.mpt.dsic.inside.xml.inside.ws.unidad.Unidad;
import es.mpt.dsic.inside.xml.inside.ws.unidadAplicacionEeutil.UnidadAplicacionEeutil;
import es.mpt.dsic.inside.xml.inside.ws.usuario.Usuario;
import es.mpt.dsic.inside.xml.inside.ws.usuario.UsuarioAdminMensajes;
import es.mpt.dsic.inside.xml.inside.ws.usuario.UsuarioResultadoBusqueda;

@WebService(name = "AdminWebService", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService")
@Logging
@GZIP
@OutInterceptors(interceptors = {}, classes = { InsideWsIndiceExpedienteSignerInterceptor.class })
@OutFaultInterceptors(interceptors = {}, classes = { InsideWsErrorInterceptor.class })
public interface AdminWebService {

	@WebMethod(operationName = "listaUsuarios", action = "urn:listaUsuarios")
	@WebResult(name = "Usuario", partName = "Usuario", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario")
	public List<Usuario> listaUsuarios() throws InsideWSException;
	
	@WebMethod(operationName = "respuestaEnvioJusticia", action = "urn:respuestaEnvioJusticia")
	@WebResult(name = "RespuestaEnvioJusticia", partName = "RespuestaEnvioJusticia", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/respuestaEnvioJusticia")
	public RespuestaEnvioJusticia respuestaEnvioJusticia(
			@WebParam(name = "codigoEnvio") @XmlElement(required = true, name = "codigoEnvio") String codigoEnvio
			) throws InsideWSException;

	@WebMethod(operationName = "altaUsuario", action = "urn:altaUsuario")
	@WebResult(name = "Usuario", partName = "Usuario", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario")
	public Usuario altaUsuario(
			@WebParam(name = "usuario", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario") @XmlElement(required = true, name = "usuario") Usuario data)
			throws InsideWSException;

	@WebMethod(operationName = "bajaUsuario", action = "urn:bajaUsuario")
	@WebResult(name = "Usuario", partName = "Usuario", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario")
	public Usuario bajaUsuario(
			@WebParam(name = "usuario", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario") @XmlElement(required = true, name = "usuario") Usuario data)
			throws InsideWSException;

	@WebMethod(operationName = "listaAplicaciones", action = "urn:listaAplicaciones")
	@WebResult(name = "Aplicacion", partName = "Aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion")
	public List<Aplicacion> listaAplicaciones() throws InsideWSException;

	@WebMethod(operationName = "altaAplicacion", action = "urn:altaAplicacion")
	@WebResult(name = "Aplicacion", partName = "Aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion")
	public Aplicacion altaAplicacion(
			@WebParam(name = "aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion") @XmlElement(required = true, name = "aplicacion") Aplicacion data)
			throws InsideWSException;

	@WebMethod(operationName = "bajaAplicacion", action = "urn:bajaAplicacion")
	@WebResult(name = "Aplicacion", partName = "Aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion")
	public Aplicacion bajaAplicacion(
			@WebParam(name = "aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion") @XmlElement(required = true, name = "aplicacion") Aplicacion data)
			throws InsideWSException;

	@WebMethod(operationName = "infAdicionalAltaApp", action = "urn:infAdicionalAltaApp")
	@WebResult(name = "InformacionAdicionalAltaApp", partName = "InformacionAdicionalAltaApp", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion")
	public List<InfAdicional> infAdicionalAltaApp() throws InsideWSException;

	@WebMethod(operationName = "altaUnidadAplicacion", action = "urn:altaUnidadAplicacion")
	@WebResult(name = "Unidad", partName = "Unidad", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidad")
	public Unidad altaUnidadAplicacion(
			@WebParam(name = "nombreAplicacion") @XmlElement(required = true, name = "nombreAplicacion") String identificadorAplicacion,
			@WebParam(name = "codigoUnidadOrganica") @XmlElement(required = true, name = "codigoUnidadOrganica") String identificadorUnidad,
			@WebParam(name = "numeroProcedimiento") @XmlElement(required = false, name = "numeroProcedimiento") String numeroProcedimiento)
			throws InsideWSException;
	

	@WebMethod(operationName = "eliminarUnidadAplicacion", action = "urn:eliminarUnidadAplicacion")
	public void eliminarUnidadAplicacion(
			@WebParam(name = "nombreAplicacion") @XmlElement(required = true, name = "nombreAplicacion") String identificadorAplicacion,
			@WebParam(name = "codigoUnidadOrganica") @XmlElement(required = true, name = "codigoUnidadOrganica") String identificadorUnidad,
			@WebParam(name = "numeroProcedimiento") @XmlElement(required = false, name = "numeroProcedimiento") String numeroProcedimiento)
			throws InsideWSException;

	@WebMethod(operationName = "altaUnidadUsuario", action = "urn:altaUnidadUsuario")
	@WebResult(name = "Unidad", partName = "Unidad", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidad")
	public Unidad altaUnidadUsuario(
			@WebParam(name = "nifUsuario") @XmlElement(required = true, name = "nifUsuario") String identificadorUsuario,
			@WebParam(name = "codigoUnidadOrganica") @XmlElement(required = true, name = "codigoUnidadOrganica") String identificadorUnidad,
			@WebParam(name = "numeroProcedimiento") @XmlElement(required = false, name = "numeroProcedimiento") String numeroProcedimiento)
			throws InsideWSException;
	
	@WebMethod(operationName = "eliminarUnidadUsuario", action = "urn:eliminarUnidadUsuario")
	public void eliminarUnidadUsuario(
			@WebParam(name = "nifUsuario") @XmlElement(required = true, name = "nifUsuario") String identificadorUsuario,
			@WebParam(name = "codigoUnidadOrganica") @XmlElement(required = true, name = "codigoUnidadOrganica") String identificadorUnidad,
			@WebParam(name = "numeroProcedimiento") @XmlElement(required = false, name = "numeroProcedimiento") String numeroProcedimiento)
			throws InsideWSException;

	@WebMethod(operationName = "getUnidadesAplicacion", action = "urn:getUnidadesAplicacion")
	@WebResult(name = "Unidad", partName = "Unidad", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidad")
	public List<Unidad> getUnidadesAplicacion(
	@WebParam(name = "nombreAplicacion") @XmlElement(required = true, name = "nombreAplicacion") String identificadorAplicacion)
			throws InsideWSException;

	@WebMethod(operationName = "getUnidadesUsuario", action = "urn:getUnidadesUsuario")
	@WebResult(name = "Unidad", partName = "Unidad", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidad")
	public List<Unidad> getUnidadesUsuario(
	@WebParam(name = "nifUsuario") @XmlElement(required = true, name = "nifUsuario") String identificadorUsuario)
			throws InsideWSException;

	@WebMethod(operationName = "limpiarDirectorioTemporal", action = "urn:limpiarDirectorioTemporal")
	public void limpiarDirectorioTemporal() throws InsideWSException;

	@WebMethod(operationName = "comprobarDirectorioTemporal", action = "urn:comprobarDirectorioTemporal")
	public void comprobarDirectorioTemporal() throws InsideWSException;

	@WebMethod(operationName = "actualizarUnidadesDir3", action = "urn:comprobarDirectorioTemporal")
	public void actualizarUnidadesDir3() throws InsideWSException;
	
	@WebMethod(operationName = "actualizarCredencialesEeetuilApp", action = "urn:actualizarCredencialesEeetuilApp")
	@WebResult(name = "Aplicacion", partName = "Aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion")
	public Aplicacion actualizarCredencialesEeetuilApp(
			@WebParam(name = "aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion") @XmlElement(required = true, name = "aplicacion") Aplicacion data)
			throws InsideWSException;
	
	
	@WebMethod(operationName = "crearActualizarUnidadAplicacionEeutil", action = "urn:crearActualizarUnidadAplicacionEeutil")
	@WebResult(name = "UnidadAplicacionEeutil", partName = "UnidadAplicacionEeutil", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidadAplicacionEeutil")
	public UnidadAplicacionEeutil crearActualizarUnidadAplicacionEeutil(
			@WebParam(name = "unidadAplicacionEeutil", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidadAplicacionEeutil") @XmlElement(required = true, name = "unidadAplicacionEeutil") UnidadAplicacionEeutil data)
			throws InsideWSException;
	
	@WebMethod(operationName = "getUnidadAplicacionEeutil", action = "urn:getUnidadAplicacionEeutil")
	@WebResult(name = "UnidadAplicacionEeutil", partName = "UnidadAplicacionEeutil", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/unidadAplicacionEeutil")
	public List<UnidadAplicacionEeutil> getUnidadAplicacionEeutil(
			@WebParam(name = "codigoUnidadOrganica") @XmlElement(required = true, name = "codigoUnidadOrganica") String codigoUnidadOrganica)
			throws InsideWSException;
	
	@WebMethod(operationName = "crearActualizarSerialNumberCertificado", action = "urn:crearActualizarSerialNumberCertificado")
	@WebResult(name = "Aplicacion", partName = "Aplicacion", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/aplicacion")
	public Aplicacion crearActualizarSerialNumberCertificado(
			@WebParam(name = "idAplicacion") @XmlElement(required = true, name = "idAplicacion") String idAplicacion,
			@WebParam(name = "serialNumberCertificado") @XmlElement(required = true, name = "serialNumberCertificado") String serialNumberCertificado) 
			throws InsideWSException;
	
	@WebMethod(operationName = "listaNumeroProcedimiento", action = "urn:listaNumeroProcedimiento")
	@WebResult(name = "numeroProcedimiento", partName = "numeroProcedimiento", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/numeroProcedimiento")
	public List<NumeroProcedimiento> listaNumeroProcedimiento() 
			throws InsideWSException;
	
	@WebMethod(operationName = "altaNumeroProcedimiento", action = "urn:altaNumeroProcedimiento")
	@WebResult(name = "numeroProcedimiento", partName = "numeroProcedimiento", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/numeroProcedimiento")
	public NumeroProcedimiento altaNumeroProcedimiento(
			@WebParam(name = "numeroProcedimiento") @XmlElement(required = true, name = "numeroProcedimiento") String numeroProcedimiento) 
			throws InsideWSException;
	
	@WebMethod(operationName = "listaEstructuraCarpeta", action = "urn:listaEstructuraCarpeta")
	@WebResult(name = "estructuraCarpeta", partName = "estructuraCarpeta", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/estructuraCarpeta")
	public List<EstructuraCarpeta> listaEstructuraCarpeta(
			@WebParam(name = "identificadorEstructura") @XmlElement(required = false, name = "identificadorEstructura") String identificadorEstructura) 
			throws InsideWSException;	
	
	@WebMethod(operationName = "altaEstructuraCarpeta", action = "urn:altaEstructuraCarpeta")
	@WebResult(name = "estructuraCarpeta", partName = "estructuraCarpeta", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/estructuraCarpeta")
	public EstructuraCarpeta altaEstructuraCarpeta(
			@WebParam(name = "estructuraCarpeta") @XmlElement(required = true, name = "estructuraCarpeta") EstructuraCarpeta estructuraCarpeta) 
			throws InsideWSException;
	
	@WebMethod(operationName = "deleteEstructuraCarpeta", action = "urn:deleteEstructuraCarpeta")
	public void deleteEstructuraCarpeta(
			@WebParam(name = "identificadorEstructura") @XmlElement(required = true, name = "identificadorEstructura") String identificadorEstructura) 
			throws InsideWSException;
	
	@WebMethod(operationName = "altaSolicitudAccesoExpAppUrl", action = "urn:altaSolicitudAccesoExpAppUrl")
	@WebResult(name = "SolicitudAccesoExpAppUrl", partName = "SolicitudAccesoExpAppUrl", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/solicitudAccesoExpAppUrl")
	public SolicitudAccesoExpAppUrl altaSolicitudAccesoExpAppUrl(
			@WebParam(name = "solicitudAccesoExpAppUrl", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/solicitudAccesoExpAppUrl") @XmlElement(required = true, name = "solicitudAccesoExpAppUrl") SolicitudAccesoExpAppUrl data)
			throws InsideWSException;
	
	@WebMethod(operationName = "actualizarSolicitudAccesoExpAppUrl", action = "urn:actualizarSolicitudAccesoExpAppUrl")
	@WebResult(name = "SolicitudAccesoExpAppUrl", partName = "SolicitudAccesoExpAppUrl", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/solicitudAccesoExpAppUrl")
	public SolicitudAccesoExpAppUrl actualizarSolicitudAccesoExpAppUrl(
			@WebParam(name = "solicitudAccesoExpAppUrl", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/solicitudAccesoExpAppUrl") @XmlElement(required = true, name = "solicitudAccesoExpAppUrl") SolicitudAccesoExpAppUrl data)
			throws InsideWSException;
	
	@WebMethod(operationName = "listaUsuariosPaginado", action = "urn:listaUsuariosPaginado")
	@WebResult(name = "UsuarioResultadoBusqueda", partName = "UsuarioResultadoBusqueda", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario")
	public UsuarioResultadoBusqueda listaUsuariosPaginado(
			@WebParam(name = "filterPageRequest", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/filter") @XmlElement(required = false, name = "filterPageRequest") FilterPageRequest data
			) throws InsideWSException;
	
	@WebMethod(operationName = "actualizarUsuarioAdminMensajes", action = "urn:actualizarUsuarioAdminMensajes")
	@WebResult(name = "UsuarioAdminMensajes", partName = "UsuarioAdminMensajes", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario")
	public UsuarioAdminMensajes actualizarUsuarioAdminMensajes(
			@WebParam(name = "usuarioAdminMensajes", targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/usuario") @XmlElement(required = true, name = "usuarioAdminMensajes") UsuarioAdminMensajes usuarioAdminMensajes) throws InsideWSException;

}
