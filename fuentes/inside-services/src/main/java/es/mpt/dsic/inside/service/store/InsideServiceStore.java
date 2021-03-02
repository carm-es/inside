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

package es.mpt.dsic.inside.service.store;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.SortedMap;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaUsuario;
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
import es.mpt.dsic.inside.model.objetos.ObjetoNumeroProcedimiento;
import es.mpt.dsic.inside.model.objetos.ObjetoUnidadOrganica;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaAccesoDocumento;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaToken;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoEstructuraCarpetaInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.filter.ObjetoFilterPageRequest;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreQueryNotValidException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectAlreadyExistsException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;

public interface InsideServiceStore {

  public <T extends ObjetoInside<?>> T storeObject(T object, String usuario, boolean online)
      throws InsideServiceStoreException, InsideStoreObjectAlreadyExistsException;

  public <T extends ObjetoInside<?>> T getObject(Class<T> tipo, final String Identifier)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  public <T extends ObjetoInside<?>> T getObjectVersion(Class<T> tipo, final String Identifier,
      int version) throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> K getObjectMetadatos(
      Class<T> tipo, final String Identifier)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> K getObjectMetadatos(
      Class<T> tipo, final String Identifier, int version)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  public <T extends ObjetoInside<?>> T updateObject(T object, String usuario, boolean online)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  // TODO ¿ updateObjectMetadatos ??

  public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> ResultadoBusquedaInside<K> queryObjects(
      ConsultaInside consulta, Class<T> tipo, int limite, int pagina)
      throws InsideServiceStoreException, InsideServiceStoreQueryNotValidException;

  public <T extends ObjetoInside<?>> List<ObjetoInsideVersion> getObjectVersions(Class<T> tipo,
      String Identifier) throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  public <T extends ObjetoInside<?>> SortedMap<ObjetoInsideVersion, T> getVersionesObjeto(
      Class<T> tipo, String Identifier, Integer version)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  public List<ObjetoInsideVersion> getListaVersionesExpediente(String identificador);

  public ObjetoInsideAplicacion getAplicacion(String nombre) throws InsideServiceStoreException;

  public ObjetoInsideUsuario getUsuario(String nombre) throws InsideServiceStoreException;

  public <T extends ObjetoInside<?>> List<ObjetoExpedienteInside> getExpedientesAsociados(
      Class<T> objeto, String identificador)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException;

  public List<ObjetoInsideAplicacion> getAplicacionesActivas() throws InsideServiceStoreException;

  Object asociarObjetoUnidad(ObjetoInside<?> obj, String usuario, boolean online)
      throws InsideServiceStoreException;

  void deleteExpedient(String identificador) throws InsideServiceStoreException;

  void deleteDocument(String identificador) throws InsideServiceStoreException;

  boolean validarObjetoUnidad(ObjetoInside<?> obj, String usuario, boolean online)
      throws InsideServiceStoreException;

  List<ObjetoInsideExpedienteUnidad> getExpedientesUnidad(String nif, boolean soloUnidadActiva)
      throws InsideServiceStoreException;

  List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadAutocompleter(String nif,
      boolean soloUnidadActiva) throws InsideServiceStoreException;

  List<ObjetoInsideDocumentoUnidad> getDocumentosUnidad(String nif, boolean soloUnidadActiva)
      throws InsideServiceStoreException;

  List<ObjetoInsideDocumentoUnidad> getDocumentosMetadatosUnidad(String nif,
      boolean soloUnidadActiva) throws InsideServiceStoreException;

  ObjetoExpedienteToken getTokenByUsuarioExpediente(ObjetoExpedienteToken data)
      throws InsideServiceStoreException;

  ObjetoExpedienteToken getTokenByData(ObjetoExpedienteToken usuarioToken)
      throws InsideServiceStoreException;

  void saveToken(ObjetoExpedienteToken usuarioToken) throws InsideServiceStoreException;

  void saveAuditoriaToken(ObjetoAuditoriaToken objectAuditoriaToken)
      throws InsideServiceStoreException;

  public void saveAuditoriaAccesoDocumento(
      ObjetoAuditoriaAccesoDocumento objetoAuditoriaAccesoDocumento)
      throws InsideServiceStoreException;

  List<ObjetoInsideUsuario> getUsuarios() throws InsideServiceStoreException;

  ResultadoBusquedaUsuario getUsuarios(ObjetoFilterPageRequest data)
      throws InsideServiceStoreException;

  ObjetoInsideUsuario altaUsuario(ObjetoInsideUsuario data) throws InsideServiceStoreException;

  ObjetoInsideUsuario bajaUsuario(ObjetoInsideUsuario data) throws InsideServiceStoreException;

  List<ObjetoInsideAplicacion> getAplicaciones() throws InsideServiceStoreException;

  ObjetoInsideAplicacion altaAplicacion(ObjetoInsideAplicacion data)
      throws InsideServiceStoreException;

  ObjetoInsideAplicacion bajaAplicacion(ObjetoInsideAplicacion data)
      throws InsideServiceStoreException;

  ObjetoInsideUnidad altaUnidadAplicacion(String idUnidad, String numeroProcedimiento,
      String idAplicacion) throws InsideServiceStoreException;

  void deleteUnidadAplicacion(String idUnidad, String numeroProcedimiento, String idAplicacion)
      throws InsideServiceStoreException;

  ObjetoInsideUnidad altaUnidadUsuario(String idUnidad, String numeroProcedimiento,
      String idUsuario) throws InsideServiceStoreException;

  void deleteUnidadUsuario(String idUnidad, String numeroProcedimiento, String idUsuario)
      throws InsideServiceStoreException;

  List<ObjetoInsideUnidad> getUnidadesAplicacion(String idAplicacion, boolean todas)
      throws InsideServiceStoreException;

  List<ObjetoInsideUnidad> getUnidadesUsuario(String idUsuario, boolean todas)
      throws InsideServiceStoreException;

  boolean comprobarUnidadesOrganicasActivasUsuario(Object usuario)
      throws InsideServiceStoreException;

  List<ObjetoInsideExpedienteUnidad> getViewsExpedient(String identificadorExpediente)
      throws InsideServiceStoreException;

  ObjetoInsideAplicacion actualizarCredencialesEeetuilApp(String app,
      ObjetoCredencialEeutil credential) throws InsideServiceStoreException;

  ObjetoInsideAplicacion getAplicacionBySerialNumber(String serialNumberCertificado)
      throws InsideServiceStoreException;

  ObjetoInsideUnidadAplicacionEeutil getApplicationEeutilByUser(ObjetoInsideUsuario user)
      throws InsideServiceStoreException;

  ObjetoInsideUnidadAplicacionEeutil crearActualizarUnidadAplicacionEeutil(String codigoUnidad,
      String aplicacion, String password) throws InsideServiceStoreException;

  ObjetoInsideUnidadAplicacionEeutil getUnidadAplicacionEeutil(String codigoUnidad)
      throws InsideServiceStoreException;

  List<ObjetoInsideUnidadAplicacionEeutil> getListUnidadAplicacionEeutil()
      throws InsideServiceStoreException;

  public ObjetoInsideAplicacion crearActualizarSerialNumberCertificado(String idAplicacion,
      String serialNumberCertificado) throws InsideServiceStoreException;

  public List<String> getNumeroProcedimiento() throws InsideServiceStoreException;

  public String altaNumeroProcedimiento(String numeroProcedimiento)
      throws InsideServiceStoreException;

  public ObjetoEstructuraCarpetaInside getEstructuraCarpeta(ObjetoInsideUsuario user)
      throws InsideServiceStoreException;

  public List<ObjetoEstructuraCarpetaInside> listaEstructuraCarpeta(String identificadorEstructura)
      throws InsideServiceStoreException;

  public ObjetoEstructuraCarpetaInside altaEstructuraCarpeta(
      ObjetoEstructuraCarpetaInside objetoEstructuraCarpetaInside)
      throws InsideServiceStoreException;

  public void deleteEstructuraCarpeta(String identificadorEstructura)
      throws InsideServiceStoreException;

  public void updateDocumentoUnidad(ObjetoDocumentoInside object, String usuario, boolean online)
      throws InsideServiceStoreException;

  public List<ObjetoElastic> getObjetosElastic(String unidadOrganica)
      throws InsideServiceStoreException;

  public int consultAndIncreaseDefaultIdByDir3(String unidadOrganica, String elemento)
      throws InsideServiceStoreException;

  public void saveAuditoriaFirmaServidor(ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor);

  List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadPorMetadatos(String nif,
      TipoMetadatosAdicionales tipoMetadatosAdicionales) throws InsideServiceStoreException;

  public List<ObjetoInsideRol> getInsideRoles() throws InsideServiceStoreException;

  public List<ObjetoNumeroProcedimiento> getNumeroProcedimientoList()
      throws InsideServiceStoreException;

  public List<ObjetoInsideUsuario> getUsuariosUnidadOrganica(ObjetoInsideUsuario usuarioEnSesion,
      Locale locale) throws InsideServiceStoreException;

  public List<ObjetoUnidadOrganica> getUnidadesOrganicasUsuariosInside(String texto)
      throws InsideServiceStoreException;

  public boolean existeUsuarioInsideConDir3(String dir3);

  public Map<String, String> getMetadatoAdicionalDocumentos(List<String> idsEniDocumentos,
      String nombreMetaAdic) throws InsideServiceStoreException;

  public ObjetoInsideDocumentoUnidad getDocumentoUnidad(String identificadorDocumento)
      throws InsideServiceStoreException;

  public ObjetoInsideExpedienteUnidad getExpedienteUnidad(String identificadorExpediente)
      throws InsideServiceStoreException;

  public ObjetoInsideUnidad getUnidadOrganica(Object idUnidadOrganica)
      throws InsideServiceStoreException;

}
