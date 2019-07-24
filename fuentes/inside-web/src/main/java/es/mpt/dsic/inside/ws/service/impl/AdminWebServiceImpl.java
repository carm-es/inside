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

import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.annotation.Secured;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaUsuario;
import es.mpt.dsic.inside.model.converter.InsideConverterBusquedaResultado;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.model.objetos.ObjetoCredencialEeutil;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadAplicacionEeutil;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoEstructuraCarpetaInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoSolicitudAccesoExpAppUrl;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.filter.ObjetoFilterPageRequest;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.messages.InsideMessageService;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.service.AdminWebService;
import es.mpt.dsic.inside.xml.inside.ws.aplicacion.Aplicacion;
import es.mpt.dsic.inside.xml.inside.ws.aplicacion.CredencialEeutil;
import es.mpt.dsic.inside.xml.inside.ws.aplicacion.InfAdicional;
import es.mpt.dsic.inside.xml.inside.ws.aplicacion.Roles;
import es.mpt.dsic.inside.xml.inside.ws.estructuraCarpeta.EstructuraCarpeta;
import es.mpt.dsic.inside.xml.inside.ws.estructuraCarpeta.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.inside.ws.filter.FilterPageRequest;
import es.mpt.dsic.inside.xml.inside.ws.numeroProcedimiento.NumeroProcedimiento;
import es.mpt.dsic.inside.xml.inside.ws.respuestaEnvioJusticia.RespuestaEnvioJusticia;
import es.mpt.dsic.inside.xml.inside.ws.solicitudAccesoExpAppUrl.SolicitudAccesoExpAppUrl;
import es.mpt.dsic.inside.xml.inside.ws.unidad.Unidad;
import es.mpt.dsic.inside.xml.inside.ws.unidadAplicacionEeutil.UnidadAplicacionEeutil;
import es.mpt.dsic.inside.xml.inside.ws.usuario.Usuario;
import es.mpt.dsic.inside.xml.inside.ws.usuario.UsuarioAdminMensajes;
import es.mpt.dsic.inside.xml.inside.ws.usuario.UsuarioResultadoBusqueda;
import es.mpt.dsic.loadTables.controller.UnidadOrganicaController;

@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.AdminWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
    portName = "InsideWSPort", serviceName = "AdminWS")
public class AdminWebServiceImpl implements AdminWebService {
  protected static final Log logger = LogFactory.getLog(AdminWebServiceImpl.class);

  @Autowired
  private InSideService inSideService;

  @Autowired
  private TemporalDataBusinessService temporalDataBusinessService;

  @Autowired
  private UnidadOrganicaController unidadOrganicaController;

  @Autowired
  private ApplicationContext appContext;

  @Autowired
  private InsideMessageService insideMessageService;

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public List<Usuario> listaUsuarios() throws InsideWSException {
    try {

      List<Usuario> retorno = new ArrayList<Usuario>();

      List<ObjetoInsideUsuario> datos = inSideService.getUsuarios();

      if (CollectionUtils.isNotEmpty(datos)) {
        for (ObjetoInsideUsuario dato : datos) {
          extraerUnidadProcedimiento(dato);
          retorno.add(convertDataToWSUser(dato));
        }
      }


      return retorno;
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private void extraerUnidadProcedimiento(ObjetoInsideUsuario dato) throws InSideServiceException {
    List<ObjetoInsideUnidad> unidades = inSideService.getUnidadesUsuario(dato.getNif(), false);
    if (!unidades.isEmpty()) {
      dato.setUnidadOrganicaActiva(unidades.get(0).getCodigo());
      dato.setNombreUnidadOrganicaActiva(unidades.get(0).getNombre());
      dato.setNumeroProcedimiento(unidades.get(0).getNumeroProcedimiento());
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public RespuestaEnvioJusticia respuestaEnvioJusticia(String codigoEnvio)
      throws InsideWSException {
    try {
      ObjectInsideRespuestaEnvioJusticia aux = new ObjectInsideRespuestaEnvioJusticia();
      aux.setCodigoEnvio(codigoEnvio);

      ObjectInsideRespuestaEnvioJusticia dato =
          inSideService.getRespuestaEvioJusticaByCodigoEnvio(aux);
      RespuestaEnvioJusticia respuesta = convertDataToWSRespuestaEnvioJusticia(dato);
      return respuesta;

    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private RespuestaEnvioJusticia convertDataToWSRespuestaEnvioJusticia(
      ObjectInsideRespuestaEnvioJusticia respuestaObject) {
    RespuestaEnvioJusticia respuestaEnvioJusticia = new RespuestaEnvioJusticia();
    respuestaEnvioJusticia.setAuditoriaEsbAplicacion(respuestaObject.getAuditoriaEsb_aplicacion());
    respuestaEnvioJusticia
        .setAuditoriaEsbMarcaTiempo(respuestaObject.getAuditoriaEsb_marcaTiempo());
    respuestaEnvioJusticia.setAuditoriaEsbModulo(respuestaObject.getAuditoriaEsb_modulo());
    respuestaEnvioJusticia.setAuditoriaEsbServicio(respuestaObject.getAuditoriaEsb_servicio());
    respuestaEnvioJusticia.setCodigoEnvio(respuestaObject.getCodigoEnvio());
    respuestaEnvioJusticia
        .setCodigoUnidadOrganoRemitente(respuestaObject.getCodigoUnidadOrganoRemitente());
    respuestaEnvioJusticia.setMensaje(respuestaObject.getMensaje());
    respuestaEnvioJusticia.setAck(respuestaObject.getAck());

    return respuestaEnvioJusticia;
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public Usuario altaUsuario(Usuario data) throws InsideWSException {
    try {
      ObjetoInsideUsuario user = inSideService.altaUsuario(convertWsToDataUser(data));
      return convertDataToWSUser(user);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private ObjetoInsideUsuario convertWsToDataUser(Usuario data) {
    ObjetoInsideUsuario user = new ObjetoInsideUsuario();
    user.setNif(data.getNif());
    user.setActivo(data.isActivo());
    user.setUnidadOrganicaActiva(data.getCodigoUnidadOrganica());
    user.setNumeroProcedimiento(data.getNumeroProcedimiento());
    int idRol = 0;
    try {
      idRol = Integer.valueOf(data.getIdRol());
    } catch (Exception e) {
      logger.info("El id de rol no es correcto, se asigna idRol 0");
    }

    user.setRol(new ObjetoInsideRol(idRol));
    return user;
  }

  private Usuario convertDataToWSUser(ObjetoInsideUsuario altaUsuario) {
    Usuario user = new Usuario();
    user.setNif(altaUsuario.getNif());
    user.setActivo(altaUsuario.isActivo());
    user.setCodigoUnidadOrganica(altaUsuario.getUnidadOrganicaActiva());
    user.setNombreUnidadOrganica(altaUsuario.getNombreUnidadOrganicaActiva());
    user.setNumeroProcedimiento(altaUsuario.getNumeroProcedimiento());
    return user;
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public Usuario bajaUsuario(Usuario data) throws InsideWSException {
    try {
      Usuario retorno = data;
      ObjetoInsideUsuario user = new ObjetoInsideUsuario();
      user.setNif(data.getNif());
      user = inSideService.bajaUsuario(user);
      retorno.setActivo(user.isActivo());
      return retorno;
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public List<Aplicacion> listaAplicaciones() throws InsideWSException {
    try {
      List<ObjetoInsideAplicacion> datos = inSideService.getAplicaciones();
      List<Aplicacion> retorno = new ArrayList<Aplicacion>();
      if (CollectionUtils.isNotEmpty(datos)) {
        for (ObjetoInsideAplicacion dato : datos) {
          extraerUnidadProcedimiento(dato);
          retorno.add(convertDataToWS(dato));
        }
      }
      return retorno;
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private void extraerUnidadProcedimiento(ObjetoInsideAplicacion dato)
      throws InSideServiceException {
    List<ObjetoInsideUnidad> unidades =
        inSideService.getUnidadesAplicacion(dato.getAplicacion(), false);
    ObjetoInsideUnidad unidad;
    if (!unidades.isEmpty()) {
      unidad = unidades.get(0);
      dato.setUnidadOrganicaActiva(unidad.getCodigo());
      dato.setNumeroProcedimiento(unidad.getNumeroProcedimiento());
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public Aplicacion altaAplicacion(Aplicacion data) throws InsideWSException {
    try {
      ObjetoInsideAplicacion app = inSideService.altaAplicacion(convertDataToInside(data));
      return convertDataToWS(app);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public List<InfAdicional> infAdicionalAltaApp() throws InsideWSException {
    List<InfAdicional> retorno = new ArrayList<InfAdicional>();

    retorno.add(generateData(ObjetoInsideAplicacion.ROLE_ALTA_EXPEDIENTE, Boolean.TRUE.toString()));
    retorno.add(
        generateData(ObjetoInsideAplicacion.ROLE_MODIFICA_EXPEDIENTE, Boolean.TRUE.toString()));
    retorno.add(generateData(ObjetoInsideAplicacion.ROLE_LEER_EXPEDIENTE, Boolean.TRUE.toString()));
    retorno.add(generateData(ObjetoInsideAplicacion.ROLE_ALTA_DOCUMENTO, Boolean.TRUE.toString()));
    retorno
        .add(generateData(ObjetoInsideAplicacion.ROLE_MODIFICA_DOCUMENTO, Boolean.TRUE.toString()));
    retorno.add(generateData(ObjetoInsideAplicacion.ROLE_LEER_DOCUMENTO, Boolean.TRUE.toString()));
    retorno.add(generateData(ObjetoInsideAplicacion.EEUTIL_IDENTIFICADOR, ""));
    retorno.add(generateData(ObjetoInsideAplicacion.EEUTIL_PASSWORD, ""));
    return retorno;
  }

  private InfAdicional generateData(String key, String value) {
    InfAdicional retorno = new InfAdicional();
    retorno.setKey(key);
    retorno.setValue(value);
    return retorno;
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public Aplicacion bajaAplicacion(Aplicacion data) throws InsideWSException {
    try {
      ObjetoInsideAplicacion app = inSideService.bajaAplicacion(convertDataToInside(data));
      return convertDataToWS(app);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public Unidad altaUnidadAplicacion(String identificadorAplicacion, String identificadorUnidad,
      String numeroProcedimiento) throws InsideWSException {
    try {
      ObjetoInsideUnidad retorno = inSideService.asociarUnidadAplicacion(identificadorUnidad,
          numeroProcedimiento, identificadorAplicacion);

      return convertDataToWSUnidad(retorno);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public void eliminarUnidadAplicacion(String identificadorAplicacion, String identificadorUnidad,
      String numeroProcedimiento) throws InsideWSException {
    try {
      if (StringUtils.isBlank(identificadorAplicacion.trim())
          || StringUtils.isBlank(identificadorUnidad.trim())) {
        throw new InsideWSException(InsideWsErrors.CAMPOS_OBLIGATORIOS);
      }
      inSideService.eliminarUnidadAplicacion(identificadorUnidad, numeroProcedimiento,
          identificadorAplicacion);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public Unidad altaUnidadUsuario(String identificadorUsuario, String identificadorUnidad,
      String numeroProcedimiento) throws InsideWSException {
    try {
      ObjetoInsideUnidad retorno = inSideService.asociarUnidadUsuario(identificadorUnidad,
          numeroProcedimiento, identificadorUsuario);

      return convertDataToWSUnidad(retorno);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public void eliminarUnidadUsuario(String identificadorUsuario, String identificadorUnidad,
      String numeroProcedimiento) throws InsideWSException {
    try {
      if (StringUtils.isBlank(identificadorUsuario.trim())
          || StringUtils.isBlank(identificadorUnidad.trim())) {
        throw new InsideWSException(InsideWsErrors.CAMPOS_OBLIGATORIOS);
      }
      inSideService.eliminarUnidadUsuario(identificadorUnidad, numeroProcedimiento,
          identificadorUsuario);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public List<Unidad> getUnidadesAplicacion(String identificadorAplicacion)
      throws InsideWSException {
    try {
      List<Unidad> retorno = new ArrayList<Unidad>();
      List<ObjetoInsideUnidad> insideUnidad =
          inSideService.getUnidadesAplicacion(identificadorAplicacion, true);

      if (CollectionUtils.isNotEmpty(insideUnidad)) {
        for (ObjetoInsideUnidad dato : insideUnidad) {
          retorno.add(convertDataToWSUnidad(dato));
        }
      }

      return retorno;
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public List<Unidad> getUnidadesUsuario(String identificadorUsuario) throws InsideWSException {
    try {
      List<Unidad> retorno = new ArrayList<Unidad>();
      List<ObjetoInsideUnidad> insideUnidad =
          inSideService.getUnidadesUsuario(identificadorUsuario, true);

      if (CollectionUtils.isNotEmpty(insideUnidad)) {
        for (ObjetoInsideUnidad dato : insideUnidad) {
          retorno.add(convertDataToWSUnidad(dato));
        }
      }

      return retorno;
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private ObjetoInsideAplicacion convertDataToInside(Aplicacion data) {
    ObjetoInsideAplicacion retorno = new ObjetoInsideAplicacion();
    retorno.setActiva(data.isActivo());
    retorno.setAplicacion(data.getNombre());
    retorno.setPasswordHash(data.getPassword());
    retorno.setEmail(data.getEmail());
    retorno.setTelefono(data.getTelefono());
    retorno.setResponsable(data.getResponsable());
    List<String> roles = new ArrayList<String>();
    if (data.getRoles() != null) {
      if (data.getRoles().isAltaDocumento()) {
        roles.add(ObjetoInsideAplicacion.ROLE_ALTA_DOCUMENTO);
      }
      if (data.getRoles().isAltaExpediente()) {
        roles.add(ObjetoInsideAplicacion.ROLE_ALTA_EXPEDIENTE);
      }
      if (data.getRoles().isLeerDocumento()) {
        roles.add(ObjetoInsideAplicacion.ROLE_LEER_DOCUMENTO);
      }
      if (data.getRoles().isLeerExpediente()) {
        roles.add(ObjetoInsideAplicacion.ROLE_LEER_EXPEDIENTE);
      }
      if (data.getRoles().isModificarDocumento()) {
        roles.add(ObjetoInsideAplicacion.ROLE_MODIFICA_DOCUMENTO);
      }
      if (data.getRoles().isModificarExpediente()) {
        roles.add(ObjetoInsideAplicacion.ROLE_MODIFICA_EXPEDIENTE);
      }
    }
    retorno.setRoles(roles);
    retorno.setUnidadOrganicaActiva(data.getCodigoUnidadOrganica());
    retorno.setNumeroProcedimiento(data.getNumeroProcedimiento());

    ObjetoCredencialEeutil credential = new ObjetoCredencialEeutil();
    credential.setAplicacion(data.getCredencialEeutil().getAplicacion());
    credential.setPassword(data.getCredencialEeutil().getPassword());
    retorno.setCredencialEeutil(credential);
    retorno.setSerialNumerCert(data.getSerialNumberCertificado());

    return retorno;
  }

  private Aplicacion convertDataToWS(ObjetoInsideAplicacion dato) { // NOSONAR
    Aplicacion app = new Aplicacion();
    app.setActivo(dato.isActiva());
    app.setNombre(dato.getAplicacion());
    app.setEmail(dato.getEmail());
    app.setTelefono(dato.getTelefono());
    app.setResponsable(dato.getResponsable());
    Roles roles = new Roles();
    if (CollectionUtils.isNotEmpty(dato.getRoles())) {
      for (String rol : dato.getRoles()) {
        if (rol.equals(ObjetoInsideAplicacion.ROLE_ADMINISTRAR_PERMISOS)) {
          roles.setAdministrarPermisos(true);
        }
        if (rol.equals(ObjetoInsideAplicacion.ROLE_ALTA_DOCUMENTO)) {
          roles.setAltaDocumento(true);
        }
        if (rol.equals(ObjetoInsideAplicacion.ROLE_ALTA_EXPEDIENTE)) {
          roles.setAltaExpediente(true);
        }
        if (rol.equals(ObjetoInsideAplicacion.ROLE_LEER_DOCUMENTO)) {
          roles.setLeerDocumento(true);
        }
        if (rol.equals(ObjetoInsideAplicacion.ROLE_LEER_EXPEDIENTE)) {
          roles.setLeerExpediente(true);
        }
        if (rol.equals(ObjetoInsideAplicacion.ROLE_MODIFICA_DOCUMENTO)) {
          roles.setModificarDocumento(true);
        }
        if (rol.equals(ObjetoInsideAplicacion.ROLE_MODIFICA_EXPEDIENTE)) {
          roles.setModificarExpediente(true);
        }
      }
    }
    app.setRoles(roles);
    app.setCodigoUnidadOrganica(dato.getUnidadOrganicaActiva());
    app.setNumeroProcedimiento(dato.getNumeroProcedimiento());
    app.setSerialNumberCertificado(dato.getSerialNumerCert());
    CredencialEeutil credencial = new CredencialEeutil();
    if (dato.getCredencialEeutil() != null) {
      credencial.setAplicacion(dato.getCredencialEeutil().getAplicacion());
      credencial.setPassword(dato.getCredencialEeutil().getPassword());
      app.setCredencialEeutil(credencial);
    }
    return app;
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public void limpiarDirectorioTemporal() throws InsideWSException {
    temporalDataBusinessService.cleanDirectory();
  }

  private Unidad convertDataToWSUnidad(ObjetoInsideUnidad dato) {
    Unidad unidad = new Unidad();
    unidad.setCodigo(dato.getCodigo());
    unidad.setNombre(dato.getNombre());
    unidad.setNumeroProcedimiento(dato.getNumeroProcedimiento());
    unidad.setActivo(dato.isActivo());
    return unidad;
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public void comprobarDirectorioTemporal() throws InsideWSException {
    long free = temporalDataBusinessService.getFreeSpace() / FileUtils.ONE_GB;
    long total = temporalDataBusinessService.getTotalSpace() / FileUtils.ONE_GB;
    logger.error("Espacio Libre " + free + "GB de " + total + "GB");
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public void actualizarUnidadesDir3() throws InsideWSException {
    unidadOrganicaController.loadUnidadOrganica();
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public Aplicacion actualizarCredencialesEeetuilApp(Aplicacion data) throws InsideWSException {
    try {
      String app = data.getNombre();
      ObjetoCredencialEeutil credential = new ObjetoCredencialEeutil();
      credential.setAplicacion(data.getCredencialEeutil().getAplicacion());
      credential.setPassword(data.getCredencialEeutil().getPassword());
      ObjetoInsideAplicacion objetoInsideAplicacion =
          inSideService.actualizarCredencialesEeetuilApp(app, credential);
      return convertDataToWS(objetoInsideAplicacion);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public UnidadAplicacionEeutil crearActualizarUnidadAplicacionEeutil(UnidadAplicacionEeutil data)
      throws InsideWSException {
    try {

      if (StringUtils.isBlank(data.getCodigoUnidad().trim())
          || StringUtils.isBlank(data.getAplicacion().trim())
          || StringUtils.isBlank(data.getPassword().trim())) {
        throw new InsideWSException(InsideWsErrors.CAMPOS_OBLIGATORIOS);
      }

      ObjetoInsideUnidadAplicacionEeutil objetoInsideUnidadAplicacionEeutil =
          inSideService.crearActualizarUnidadAplicacionEeutil(data.getCodigoUnidad(),
              data.getAplicacion(), data.getPassword());
      return convertDataToWSUnidadAplicacionEeutil(objetoInsideUnidadAplicacionEeutil);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private UnidadAplicacionEeutil convertDataToWSUnidadAplicacionEeutil(
      ObjetoInsideUnidadAplicacionEeutil objetoInsideUnidadAplicacionEeutil) {
    UnidadAplicacionEeutil unidadAplicacionEeutil = new UnidadAplicacionEeutil();
    unidadAplicacionEeutil.setAplicacion(objetoInsideUnidadAplicacionEeutil.getIdAplicacion());
    unidadAplicacionEeutil
        .setCodigoUnidad(objetoInsideUnidadAplicacionEeutil.getCodigoUnidadOrganica());
    unidadAplicacionEeutil.setPassword(objetoInsideUnidadAplicacionEeutil.getPassword());
    return unidadAplicacionEeutil;
  }

  @Override
  public List<UnidadAplicacionEeutil> getUnidadAplicacionEeutil(String codigoUnidadOrganica)
      throws InsideWSException {
    List<UnidadAplicacionEeutil> listUnidadAplicacionEeutil =
        new ArrayList<UnidadAplicacionEeutil>();

    try {
      if (StringUtils.isNotBlank(codigoUnidadOrganica.trim())) {
        ObjetoInsideUnidadAplicacionEeutil objetoInsideUnidadAplicacionEeutil =
            inSideService.getUnidadAplicacionEeutil(codigoUnidadOrganica);
        listUnidadAplicacionEeutil
            .add(convertDataToWSUnidadAplicacionEeutil(objetoInsideUnidadAplicacionEeutil));
      } else {
        List<ObjetoInsideUnidadAplicacionEeutil> listObjetoInsideUnidadAplicacionEeutil =
            inSideService.getListUnidadAplicacionEeutil();
        for (ObjetoInsideUnidadAplicacionEeutil objetoInsideUnidadAplicacionEeutil : listObjetoInsideUnidadAplicacionEeutil) {
          listUnidadAplicacionEeutil
              .add(convertDataToWSUnidadAplicacionEeutil(objetoInsideUnidadAplicacionEeutil));
        }
      }
      return listUnidadAplicacionEeutil;
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public Aplicacion crearActualizarSerialNumberCertificado(String idAplicacion,
      String serialNumberCertificado) throws InsideWSException {
    try {
      if (StringUtils.isBlank(idAplicacion.trim())
          || StringUtils.isBlank(serialNumberCertificado.trim())) {
        throw new InsideWSException(InsideWsErrors.CAMPOS_OBLIGATORIOS);
      }
      return convertDataToWS(inSideService.crearActualizarSerialNumberCertificado(idAplicacion,
          serialNumberCertificado));
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  public List<NumeroProcedimiento> listaNumeroProcedimiento() throws InsideWSException {
    try {
      return convertDataToWSListProcedimiento(inSideService.listaNumeroProcedimiento());
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private List<NumeroProcedimiento> convertDataToWSListProcedimiento(
      List<String> listaNumeroProcedimiento) {
    List<NumeroProcedimiento> retorno = new ArrayList<NumeroProcedimiento>();

    for (String procedimiento : listaNumeroProcedimiento)
      retorno.add(convertDataToWSProcedimiento(procedimiento));

    return retorno;
  }

  @Override
  public NumeroProcedimiento altaNumeroProcedimiento(String numeroProcedimiento)
      throws InsideWSException {
    try {
      return convertDataToWSProcedimiento(
          inSideService.altaNumeroProcedimiento(numeroProcedimiento));
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private NumeroProcedimiento convertDataToWSProcedimiento(String numeroProcedimiento) {
    NumeroProcedimiento procedimiento = new NumeroProcedimiento();
    procedimiento.setNumeroProcedimiento(numeroProcedimiento);
    return procedimiento;
  }

  @Override
  public List<EstructuraCarpeta> listaEstructuraCarpeta(String identificadorEstructura)
      throws InsideWSException {
    try {
      return convertDataToWSListEstructuraCarpeta(
          inSideService.listaEstructuraCarpeta(identificadorEstructura));
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private List<EstructuraCarpeta> convertDataToWSListEstructuraCarpeta(
      List<ObjetoEstructuraCarpetaInside> listaEstructuraCarpeta) {
    List<EstructuraCarpeta> retorno = new ArrayList<EstructuraCarpeta>();

    for (ObjetoEstructuraCarpetaInside estructuraCarpeta : listaEstructuraCarpeta)
      retorno.add(convertDataToWSEstructuraCarpeta(estructuraCarpeta));

    return retorno;
  }

  private EstructuraCarpeta convertDataToWSEstructuraCarpeta(
      ObjetoEstructuraCarpetaInside estructuraCarpetaInside) {
    EstructuraCarpeta estructuraCarpeta = new EstructuraCarpeta();
    estructuraCarpeta
        .setIdentificadorEstructura(estructuraCarpetaInside.getIdentificadorEstructura());
    estructuraCarpeta.setCodigoUnidadOrganica(estructuraCarpetaInside.getCodigoUnidadOrganica());
    estructuraCarpeta.setNumeroProcedimiento(estructuraCarpetaInside.getNumeroProcedimiento());
    List<TipoCarpetaIndizada> carpetasIndizados =
        convertEstructuraCarpeta(estructuraCarpetaInside.getElementosIndizados());
    estructuraCarpeta.getCarpetaIndizada().addAll(carpetasIndizados);
    return estructuraCarpeta;
  }

  private List<TipoCarpetaIndizada> convertEstructuraCarpeta(
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> carpetasIndizadaInside) {
    List<TipoCarpetaIndizada> carpetasIndizados = new ArrayList<TipoCarpetaIndizada>();

    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado carpetaIndizada : carpetasIndizadaInside) {
      TipoCarpetaIndizada carpetaInside = convertDataToWSCarpeta(
          (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) carpetaIndizada);
      carpetasIndizados.add(carpetaInside);
    }
    return carpetasIndizados;
  }

  private TipoCarpetaIndizada convertDataToWSCarpeta(
      ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada) {
    TipoCarpetaIndizada tipoCarpetaIndizada = new TipoCarpetaIndizada();
    tipoCarpetaIndizada.setIdentificadorCarpeta(carpetaIndizada.getIdentificadorCarpeta());
    tipoCarpetaIndizada.getCarpetaIndizada()
        .addAll(convertEstructuraCarpeta(carpetaIndizada.getElementosIndizados()));
    return tipoCarpetaIndizada;
  }

  @Override
  public EstructuraCarpeta altaEstructuraCarpeta(EstructuraCarpeta estructuraCarpeta)
      throws InsideWSException {
    try {
      List<ObjetoEstructuraCarpetaInside> estructura =
          inSideService.listaEstructuraCarpeta(estructuraCarpeta.getIdentificadorEstructura());
      if (CollectionUtils.isNotEmpty(estructura)) {
        throw new InsideWSException(InsideWsErrors.OBJECT_ALREADY_EXISTS);
      }
      return convertDataToWSEstructuraCarpeta(inSideService
          .altaEstructuraCarpeta(convertWSToDataListEstructuraCarpeta(estructuraCarpeta)));
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  private ObjetoEstructuraCarpetaInside convertWSToDataListEstructuraCarpeta(
      EstructuraCarpeta estructuraCarpeta) {
    ObjetoEstructuraCarpetaInside objetoEstructuraCarpetaInside =
        new ObjetoEstructuraCarpetaInside();
    objetoEstructuraCarpetaInside
        .setIdentificadorEstructura(estructuraCarpeta.getIdentificadorEstructura());
    objetoEstructuraCarpetaInside
        .setCodigoUnidadOrganica(estructuraCarpeta.getCodigoUnidadOrganica());
    objetoEstructuraCarpetaInside
        .setNumeroProcedimiento(estructuraCarpeta.getNumeroProcedimiento());
    List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> carpetasIndizados =
        convertEstructuraCarpetaWS(estructuraCarpeta.getCarpetaIndizada());
    objetoEstructuraCarpetaInside.getElementosIndizados().addAll(carpetasIndizados);
    return objetoEstructuraCarpetaInside;
  }

  private List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> convertEstructuraCarpetaWS(
      List<TipoCarpetaIndizada> carpetaIndizadaWS) {
    List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> carpetasIndizados =
        new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();

    int orden = 0;
    for (TipoCarpetaIndizada carpetaIndizada : carpetaIndizadaWS) {
      ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaInside =
          convertWSToDataCarpeta(carpetaIndizada, orden);
      carpetasIndizados.add(carpetaInside);
      orden++;
    }
    return carpetasIndizados;
  }

  private ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada convertWSToDataCarpeta(
      TipoCarpetaIndizada carpetaIndizada, int orden) {
    ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada objCarpetaIndizada =
        new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();
    objCarpetaIndizada.setIdentificadorCarpeta(carpetaIndizada.getIdentificadorCarpeta());
    objCarpetaIndizada.setOrden(orden);
    if (CollectionUtils.isNotEmpty(carpetaIndizada.getCarpetaIndizada()))
      objCarpetaIndizada
          .setElementosIndizados(convertEstructuraCarpetaWS(carpetaIndizada.getCarpetaIndizada()));
    return objCarpetaIndizada;
  }

  @Override
  public void deleteEstructuraCarpeta(String identificadorEstructura) throws InsideWSException {
    try {
      if (StringUtils.isBlank(identificadorEstructura)) {
        throw new InsideWSException(InsideWsErrors.CAMPOS_OBLIGATORIOS);
      }
      List<ObjetoEstructuraCarpetaInside> estructura =
          inSideService.listaEstructuraCarpeta(identificadorEstructura);
      if (CollectionUtils.isEmpty(estructura)) {
        throw new InsideWSException(InsideWsErrors.OBJECT_NOT_FOUND);
      }
      inSideService.deleteEstructuraCarpeta(identificadorEstructura);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public SolicitudAccesoExpAppUrl altaSolicitudAccesoExpAppUrl(SolicitudAccesoExpAppUrl data)
      throws InsideWSException {
    try {
      if (!validaSolicitudAccesoExpAppUrl(data)) {
        throw new InsideWSException(InsideWsErrors.CAMPOS_OBLIGATORIOS);
      }

      if (inSideService.getSolicitudAccesoExpAppUrlPorDir3(data.getDir3Padre()) != null) {
        throw new InsideWSException(InsideWsErrors.SOLICITUD_ACCESO_EXP_APP_URL_ALREADY_EXISTS);
      }

      return this.toSolicitudAccesoExpAppUrl(
          inSideService.saveSolicitudAccesoExpAppUrl(this.toObjetoSolicitudAccesoExpAppUrl(data)));

    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public SolicitudAccesoExpAppUrl actualizarSolicitudAccesoExpAppUrl(SolicitudAccesoExpAppUrl data)
      throws InsideWSException {
    try {
      if (!validaSolicitudAccesoExpAppUrl(data)) {
        throw new InsideWSException(InsideWsErrors.CAMPOS_OBLIGATORIOS);
      }
      ObjetoSolicitudAccesoExpAppUrl objeto =
          inSideService.getSolicitudAccesoExpAppUrlPorDir3(data.getDir3Padre());
      if (objeto == null) {
        throw new InsideWSException(InsideWsErrors.OBJECT_NOT_FOUND);
      }
      int id = objeto.getId();
      objeto = this.toObjetoSolicitudAccesoExpAppUrl(data);
      objeto.setId(id);
      return this.toSolicitudAccesoExpAppUrl(inSideService.saveSolicitudAccesoExpAppUrl(objeto));

    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }

  /**
   * Al no validar por xsd según: inside-ws-context.xml
   * <entry key="schema-validation-enabled" value= "${schema-validation-enabled}" /> se hace aquí.
   */
  private boolean validaSolicitudAccesoExpAppUrl(SolicitudAccesoExpAppUrl data) {

    if (StringUtils.isBlank(data.getUrlDestinoPeticion().trim())
        || StringUtils.isBlank(data.getDir3Padre().trim())) {
      return false;
    }
    return true;
  }

  private ObjetoSolicitudAccesoExpAppUrl toObjetoSolicitudAccesoExpAppUrl(
      SolicitudAccesoExpAppUrl data) {

    ObjetoSolicitudAccesoExpAppUrl respuesta = new ObjetoSolicitudAccesoExpAppUrl();
    respuesta.setDir3Padre(data.getDir3Padre());
    respuesta.setUrlDestinoPeticion(data.getUrlDestinoPeticion());
    return respuesta;

  }


  private ObjetoFilterPageRequest toObjetoFilterPageRequest(FilterPageRequest data) {

    ObjetoFilterPageRequest respuesta =
        new ObjetoFilterPageRequest(data.getFiltro(), data.getPagina(), data.getLimite(), null);

    return respuesta;

  }

  private SolicitudAccesoExpAppUrl toSolicitudAccesoExpAppUrl(ObjetoSolicitudAccesoExpAppUrl data) {

    SolicitudAccesoExpAppUrl respuesta = new SolicitudAccesoExpAppUrl();
    respuesta.setDir3Padre(data.getDir3Padre());
    respuesta.setUrlDestinoPeticion(data.getUrlDestinoPeticion());
    return respuesta;

  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public UsuarioResultadoBusqueda listaUsuariosPaginado(FilterPageRequest filterPageRequest)
      throws InsideWSException {
    UsuarioResultadoBusqueda resultado = new UsuarioResultadoBusqueda();
    try {

      ResultadoBusquedaUsuario resultadoBusqueda =
          inSideService.getUsuarios(toObjetoFilterPageRequest(filterPageRequest));
      resultado =
          InsideConverterBusquedaResultado.respuestaBusquedaUsuarioInside(resultadoBusqueda);

    } catch (InsideConverterException e) {
      throw InsideExceptionConverter.convertToException(e);
    } catch (InSideServiceException e) {
      throw InsideExceptionConverter.convertToException(e);
    }

    return resultado;
  }

  @Override
  @Secured("ROLE_ADMINISTRAR_PERMISOS")
  public UsuarioAdminMensajes actualizarUsuarioAdminMensajes(
      UsuarioAdminMensajes usuarioAdminMensajes) throws InsideWSException {
    ObjetoInsideUsuario user = insideMessageService
        .actualizarUsuarioAdminMensajes(convertWsUserAdminMensajeToDataUser(usuarioAdminMensajes));
    return convertUserDataToWSUserAdminMensajes(user);
  }

  private ObjetoInsideUsuario convertWsUserAdminMensajeToDataUser(UsuarioAdminMensajes data) {
    ObjetoInsideUsuario retorno = new ObjetoInsideUsuario();
    retorno.setNif(data.getNif());
    retorno.setAdminMensaje(data.isAdminMensajes());
    return retorno;
  }

  private UsuarioAdminMensajes convertUserDataToWSUserAdminMensajes(
      ObjetoInsideUsuario altaUsuario) {
    UsuarioAdminMensajes retorno = new UsuarioAdminMensajes();
    retorno.setNif(altaUsuario.getNif());
    retorno.setAdminMensajes(altaUsuario.isAdminMensaje());
    return retorno;
  }

}
