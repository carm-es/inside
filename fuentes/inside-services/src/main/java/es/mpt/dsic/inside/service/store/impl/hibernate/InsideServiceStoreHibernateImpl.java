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

package es.mpt.dsic.inside.service.store.impl.hibernate;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.util.ClassUtils;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.DocumentoResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ExpedienteResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ItemResultadoBusquedaInside;
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
import es.mpt.dsic.inside.model.objetos.ObjetoInsideUnidadUsuario;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersionComparator;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersionable;
import es.mpt.dsic.inside.model.objetos.ObjetoNumeroProcedimiento;
import es.mpt.dsic.inside.model.objetos.ObjetoUnidadOrganica;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaAccesoDocumento;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaToken;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoEstructuraCarpetaInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.model.objetos.filter.ObjetoFilterPageRequest;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.object.definitions.InsideObjectDefinitionsContainer;
import es.mpt.dsic.inside.service.store.InsideServiceStore;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreQueryNotValidException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectAlreadyExistsException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectVersionNotFoundException;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceHibernateConverterBusqueda;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverter;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterAplicacion;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterAuditoriaAccesoDocumento;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterAuditoriaFirmaServidor;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterDocumento;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterEstructuraCarpeta;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterExpediente;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterExpedienteToken;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterInsideRol;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterNumeroProcedimiento;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUnidad;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUnidadAplicacionEeutil;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUnidadUsuario;
import es.mpt.dsic.inside.service.store.impl.hibernate.converter.InsideServiceStoreHibernateConverterUsuario;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.store.hibernate.entity.CredencialesEeutil;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInside;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideMetadatosAdicionales;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoInsideOrgano;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoMetadatosAdicionales;
import es.mpt.dsic.inside.store.hibernate.entity.DocumentoUnidad;
import es.mpt.dsic.inside.store.hibernate.entity.EstructuraCarpetaInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInside;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideIndice;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideMetadatosAdicionales;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteInsideOrgano;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteToken;
import es.mpt.dsic.inside.store.hibernate.entity.ExpedienteUnidad;
import es.mpt.dsic.inside.store.hibernate.entity.GeneradorIdInside;
import es.mpt.dsic.inside.store.hibernate.entity.InsideRol;
import es.mpt.dsic.inside.store.hibernate.entity.InsideWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.NumeroProcedimiento;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadAplicacionEeutil;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadUsuario;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadWsAplicacion;
import es.mpt.dsic.inside.store.hibernate.entity.UsuarioInside;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;

public class InsideServiceStoreHibernateImpl<I extends ObjetoInside<?>, E extends Object>
    implements InsideServiceStore {

  private static final String FECHA_VERSION = "fechaVersion";

  private static final String ID_PROCEDIMIENTO = "idProcedimiento";

  private static final String IDENTIFICADOR = "identificador";

  private static final String ID_UNIDAD = "idUnidad";

  protected static final Log logger = LogFactory.getLog(InsideServiceStore.class);

  private SessionFactory sessionFactory;

  private InsideServiceStoreHibernatePersister<I, E> persister;

  private InsideServiceStoreHibernateConverter<I, E> converter;

  private InsideServiceStoreHibernateConverterUsuario<E> converterUsuario;

  private InsideServiceStoreHibernateConverterExpedienteToken converterExpedienteToken;

  private InsideServiceStoreHibernateConverterInsideRol converterInsideRol;

  private InsideServiceStoreHibernateConverterNumeroProcedimiento converterNumeroProcedimiento;

  @Autowired
  private MessageSource messageSource;

  @Autowired
  @Qualifier("InsideObjectDefinitions")
  private InsideObjectDefinitionsContainer objectDefinitions;

  @Override
  public <T extends ObjetoInside<?>> T storeObject(T object, String usuario, boolean online)
      throws InsideServiceStoreException, InsideStoreObjectAlreadyExistsException {
    if (StringUtils.isBlank(object.getIdentificador())) {
      throw new InsideServiceStoreException("El identicador no puede estár vacío");
    }

    try {
      getObject(object.getClass(), object.getIdentificador());
      throw new InsideStoreObjectAlreadyExistsException(object);
    } catch (InsideStoreObjectNotFoundException e) {
      logger.debug("Almacenando objeto " + object.getClass() + " con identificador proporcionado "
          + object.getIdentificador(), e);
    }
    Object objectUnidad = asociarObjetoUnidad(object, usuario, online);
    persister.persistExpedienteOrDocumento(object, objectUnidad);

    return object;

  }

  @Override
  public <T extends ObjetoInside<?>> T getObject(Class<T> tipo, String Identifier)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException {

    SortedMap<ObjetoInsideVersion, T> versionesObjeto = getVersionesObjeto(tipo, Identifier, null);

    if (versionesObjeto.size() == 1) {
      return versionesObjeto.values().iterator().next();
    } else {
      return versionesObjeto.get(versionesObjeto.lastKey());
    }
  }

  private <T extends ObjetoInside<?>> Class<?> getEntityClass(Class<T> tipo)
      throws InsideServiceStoreException {
    if (tipo == ObjetoExpedienteInside.class) {
      return ExpedienteInside.class;
    } else if (tipo == ObjetoDocumentoInside.class) {
      return DocumentoInside.class;
    } else {
      throw new InsideServiceStoreException(
          "No se corresponden ningun Entity de BBDD con el tipo " + tipo);
    }
  }

  @Override
  public <T extends ObjetoInside<?>> T getObjectVersion(Class<T> tipo, String Identifier,
      int version) throws InsideServiceStoreException, InsideStoreObjectNotFoundException {
    SortedMap<ObjetoInsideVersion, T> versionesObjeto =
        getVersionesObjeto(tipo, Identifier, version);

    for (ObjetoInsideVersion versionInside : versionesObjeto.keySet()) {
      if (versionInside.getVersion() == version) {
        return versionesObjeto.get(versionInside);
      }
    }

    throw new InsideStoreObjectVersionNotFoundException(tipo, Identifier, version);
  }

  @Override
  public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> K getObjectMetadatos(
      Class<T> tipo, String Identifier)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException {
    return getObjectMetadatos(tipo, Identifier, 0);
  }

  @Override
  public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> K getObjectMetadatos(
      Class<T> tipo, String Identifier, int version)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException {
    T objetoInside;
    if (version >= 1) {
      objetoInside = getObjectVersion(tipo, Identifier, version);
    } else {
      objetoInside = getObject(tipo, Identifier);
    }
    return objetoInside.getMetadatos();
  }

  @Override
  public <T extends ObjetoInside<?>> T updateObject(T object, String usuario, boolean online)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException {

    if (object instanceof ObjetoInsideVersionable) {
      ObjetoInside<?> stored = this.getObject(object.getClass(), object.getIdentificador());
      ((ObjetoInsideVersionable) object).setVersion(
          ObjetoInsideVersion.createNextVersion(((ObjetoInsideVersionable) stored).getVersion()));
      logger.debug("Subida la versión para objeto de tipo " + object.getClass()
          + " con identificador " + object.getIdentificador());
    } else if (object instanceof ObjetoDocumentoInside) {
      ObjetoInside<?> stored = this.getObject(object.getClass(), object.getIdentificador());
      ((ObjetoDocumentoInside) object).setVersion(new ObjetoInsideVersion(
          ((ObjetoDocumentoInside) stored).getVersion().getVersion(), new GregorianCalendar()));
    }
    Object objectUnidad = asociarObjetoUnidad(object, usuario, online);
    persister.persistExpedienteOrDocumento(object, objectUnidad);

    return object;
  }

  @Override
  public <T extends ObjetoInside<?>> List<ObjetoInsideVersion> getObjectVersions(Class<T> tipo,
      String Identifier) throws InsideServiceStoreException, InsideStoreObjectNotFoundException {
    SortedMap<ObjetoInsideVersion, T> versionesObjeto = getVersionesObjeto(tipo, Identifier, null);
    return new ArrayList<ObjetoInsideVersion>(versionesObjeto.keySet());
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends ObjetoInside<?>> SortedMap<ObjetoInsideVersion, T> getVersionesObjeto(
      Class<T> tipo, String Identifier, Integer idVersion)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException {
    Session session = sessionFactory.openSession();

    try {
      SortedMap<ObjetoInsideVersion, T> versionesObjeto =
          new TreeMap<ObjetoInsideVersion, T>(new ObjetoInsideVersionComparator());

      Criteria crit = session.createCriteria(getEntityClass(tipo));
      crit.add(Restrictions.eq(IDENTIFICADOR, Identifier));
      if (idVersion != null) {
        crit.add(Restrictions.eq("version", idVersion));
      } else {
        crit.addOrder(Order.desc("version"));
      }

      List<?> objetos = crit.list();

      if (objetos.isEmpty()) {
        throw new InsideStoreObjectNotFoundException(tipo, Identifier);
      }

      T objetoInside = (T) converter.toInside((E) objetos.get(0), session);

      ObjetoInsideVersion version = null;

      Method getVersionMethod = ClassUtils.getMethod(tipo, "getVersion");
      try {
        version = (ObjetoInsideVersion) getVersionMethod.invoke(objetoInside);
      } catch (Exception e) {
        throw new InsideServiceStoreException(
            "Error llamanado a getVersion en objeto de tipo " + tipo + " : " + e.getMessage(), e);
      }

      versionesObjeto.put(version, objetoInside);

      return versionesObjeto;

    } catch (InsideServiceStoreException e) {
      throw e;
    } finally {
      session.close();
    }

  }

  @Override
  public List<ObjetoInsideVersion> getListaVersionesExpediente(String identificador) {
    Session session = sessionFactory.openSession();
    List<ObjetoInsideVersion> listVersion = new ArrayList<ObjetoInsideVersion>();
    try {
      Criteria crit = session.createCriteria(ExpedienteInside.class);
      crit.add(Restrictions.eq(IDENTIFICADOR, identificador));
      crit.addOrder(Order.desc("version"));

      List<ExpedienteInside> listExpedientes = crit.list();
      if (!listExpedientes.isEmpty()) {
        for (ExpedienteInside expediente : listExpedientes) {
          GregorianCalendar cal = new GregorianCalendar();
          cal.setTime(expediente.getFechaVersion());
          ObjetoInsideVersion version = new ObjetoInsideVersion(expediente.getVersion(), cal);
          listVersion.add(version);
        }
      }
      return listVersion;
    } finally {
      session.close();
    }
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  @Required
  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos> ResultadoBusquedaInside<K> queryObjects(
      ConsultaInside consulta, Class<T> tipo, int limite, int pagina)
      throws InsideServiceStoreException, InsideServiceStoreQueryNotValidException {
    Session session = sessionFactory.openSession();

    try {
      Criteria criteria = session.createCriteria(getEntityClass(tipo));

      InsideServiceHibernateConverterBusqueda converterBusqueda =
          new InsideServiceHibernateConverterBusqueda(objectDefinitions);

      converterBusqueda.addConsultaInsideToCriteria(criteria, consulta, tipo);

      List<?> objetos = null;

      criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
      criteria.addOrder(Order.asc("id"));

      try {
        objetos = criteria.list();
      } catch (ClassCastException e) {
        throw new InsideServiceStoreQueryNotValidException(
            "Alguno de los datos proporcionados no tenía el tipo de datos adecuado para su columna",
            e);
      }

      logger.debug("queryObjects: Obtenidos " + objetos.size() + " resultados ");

      ResultadoBusquedaInside<K> resultado = new ResultadoBusquedaInside<K>();
      List<ItemResultadoBusquedaInside<K>> resultados =
          new ArrayList<ItemResultadoBusquedaInside<K>>();

      if (limite == -1) {
        resultado.setPagina(1);
        resultado.setPorPagina(objetos.size());
        resultado.setTotales(objetos.size());
        // Si se ha establecido limite se configura el resultado.
      } else {
        resultado.setPagina(pagina);
        resultado.setPorPagina(limite);
        resultado.setTotales(objetos.size());
      }

      // Si no hay límite, la sublista será la lista de todos los
      // resultados.
      List<?> subLista =
          (limite == -1) ? objetos : InsideUtils.getSublista(objetos, limite, pagina);

      for (Object entity : subLista) {

        ItemResultadoBusquedaInside<K> res;

        if (entity instanceof DocumentoInside) {
          res = (ItemResultadoBusquedaInside<K>) new DocumentoResultadoBusquedaInside();
        } else {
          res = (ItemResultadoBusquedaInside<K>) new ExpedienteResultadoBusquedaInside();
        }

        T objetoInside = (T) converter.toInside((E) entity, session);

        res.setMetadatos(objetoInside.getMetadatos());
        res.setVersion(((ObjetoInsideVersionable) objetoInside).getVersion());
        res.setIdentificador(objetoInside.getIdentificador());
        resultados.add(res);
      }
      resultado.setResultados(resultados);

      return resultado;

    } catch (InsideServiceStoreQueryNotValidException e) {
      throw new InsideServiceStoreException("No se ha podido realizar la consulta", e);
    } catch (InsideServiceStoreException e) {
      throw new InsideServiceStoreException("No se ha podido realizar la consulta", e);
    } catch (Exception e) {
      throw new InsideServiceStoreException("No se ha podido realizar la consulta", e);
    } finally {
      session.close();
    }

  }

  /**
   * Devuelve el objeto Inside asociado al nombre de una aplicación. Si la consulta devuelve más de
   * un resultado dará un error Si la consulta no devuelve nada devolverá NULL.
   */
  @Override
  public ObjetoInsideAplicacion getAplicacion(String nombreAplicacion)
      throws InsideServiceStoreException {
    ObjetoInsideAplicacion aplicacionInside = null;
    InsideWsAplicacion entity = null;
    Session session = sessionFactory.openSession();
    try {
      entity = getAplicacionEntity(nombreAplicacion, session);

      Criteria criteriaCredential = session.createCriteria(CredencialesEeutil.class);
      criteriaCredential.add(Restrictions.eq("idAplicacionInterna", entity.getId()));
      CredencialesEeutil dataCredential = (CredencialesEeutil) criteriaCredential.uniqueResult();
      entity.setCredencialEeeutil(dataCredential);

      aplicacionInside = InsideServiceStoreHibernateConverterAplicacion.toInside(entity);
    } catch (Exception e) {
      throw new InsideServiceStoreException(
          "Excepción obteniendo la aplicacion con nombre " + nombreAplicacion, e);
    } finally {
      session.close();
    }

    return aplicacionInside;
  }

  public InsideWsAplicacion getAplicacionEntity(String nombreAplicacion, Session session)
      throws InsideServiceStoreException {
    Criteria criteria = session.createCriteria(InsideWsAplicacion.class);
    InsideWsAplicacion retorno = null;
    criteria.add(Restrictions.eq("nombre", nombreAplicacion));
    List<InsideWsAplicacion> aplicaciones = criteria.list();
    if (CollectionUtils.isNotEmpty(aplicaciones)) {
      if (aplicaciones.size() > 1) {
        throw new InsideServiceStoreException(
            "Se ha obtenido más de un resultado para la aplicación " + nombreAplicacion);
      } else {
        retorno = aplicaciones.get(0);
      }
    } else {
      throw new InsideServiceStoreException(
          "No se ha obtenido resultado para la aplicación " + nombreAplicacion);
    }
    return retorno;
  }

  /**
   * Devuelve el objeto Inside asociado al nombre de una aplicación. Si la consulta devuelve más de
   * un resultado dará un error Si la consulta no devuelve nada devolverá NULL.
   */
  @Override
  public ObjetoInsideUsuario getUsuario(String nifUsuario) throws InsideServiceStoreException {
    ObjetoInsideUsuario usuarioInside = null;
    UsuarioInside entity;
    Session session = sessionFactory.openSession();
    try {
      entity = getUsuarioEntity(nifUsuario, session);
      usuarioInside = InsideServiceStoreHibernateConverterUsuario.toInside(entity);
    } catch (Exception e) {
      throw new InsideServiceStoreException("Excepción obteniendo el usuario con nif " + nifUsuario,
          e);
    } finally {
      session.close();
    }

    return usuarioInside;
  }

  public UsuarioInside getUsuarioEntity(String nifUsuario, Session session)
      throws InsideServiceStoreException {
    Criteria criteria = session.createCriteria(UsuarioInside.class);
    criteria.add(Restrictions.eq("nif", nifUsuario));
    List<UsuarioInside> usuarios = criteria.list();
    if (usuarios != null) {
      if (usuarios.isEmpty())
        throw new InsideServiceStoreException(
            "No se ha obtenido resultado para el usuario: " + nifUsuario);
      if (usuarios.size() > 1)
        throw new InsideServiceStoreException(
            "Se ha obtenido más de un resultado para el usuario: " + nifUsuario);
    } else {
      throw new InsideServiceStoreException(
          "No se ha obtenido resultado para el usuario: " + nifUsuario);
    }
    return usuarios.get(0);
  }

  public InsideServiceStoreHibernatePersister<I, E> getPersister() {
    return persister;
  }

  @Required
  public void setPersister(InsideServiceStoreHibernatePersister<I, E> persister) {
    this.persister = persister;
  }

  public InsideServiceStoreHibernateConverter<I, E> getConverter() {
    return converter;
  }

  public void setConverter(InsideServiceStoreHibernateConverter<I, E> converter) {
    this.converter = converter;
  }

  public InsideServiceStoreHibernateConverterUsuario<E> getConverterUsuario() {
    return converterUsuario;
  }

  public void setConverterUsuario(InsideServiceStoreHibernateConverterUsuario<E> converterUsuario) {
    this.converterUsuario = converterUsuario;
  }

  public InsideServiceStoreHibernateConverterExpedienteToken getConverterExpedienteToken() {
    return converterExpedienteToken;
  }

  public void setConverterExpedienteToken(
      InsideServiceStoreHibernateConverterExpedienteToken converterExpedienteToken) {
    this.converterExpedienteToken = converterExpedienteToken;
  }

  @Override
  public <T extends ObjetoInside<?>> List<ObjetoExpedienteInside> getExpedientesAsociados(
      Class<T> objeto, String identificador)
      throws InsideServiceStoreException, InsideStoreObjectNotFoundException {
    Session session = sessionFactory.openSession();
    List<ObjetoExpedienteInside> retorno = new ArrayList<ObjetoExpedienteInside>();
    try {
      if (objeto.isAssignableFrom(ObjetoExpedienteInside.class)) {
        retorno = new InsideServiceStoreHibernateConverterExpediente()
            .getExpedientesAsociados(identificador, session);
      }
      if (objeto.isAssignableFrom(ObjetoDocumentoInside.class)) {
        retorno = new InsideServiceStoreHibernateConverterDocumento()
            .getExpedientesAsociados(identificador, session);
      }
    } catch (InsideServiceStoreException e) {
      throw e;
    } finally {
      session.close();
    }
    return retorno;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ObjetoInsideAplicacion> getAplicacionesActivas() throws InsideServiceStoreException {
    List<ObjetoInsideAplicacion> retorno = new ArrayList<ObjetoInsideAplicacion>();
    Session session = sessionFactory.openSession();
    try {
      Criteria criteria = session.createCriteria(InsideWsAplicacion.class);
      criteria.add(Restrictions.eq("activo", true));
      List<InsideWsAplicacion> aplicaciones = criteria.list();
      if (aplicaciones != null) {
        for (InsideWsAplicacion app : aplicaciones) {
          retorno.add(InsideServiceStoreHibernateConverterAplicacion.toInside(app));
        }
      }
    } catch (Exception e) {
      throw new InsideServiceStoreException("Excepción obteniendo las aplicaciones", e);
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public Object asociarObjetoUnidad(ObjetoInside<?> obj, String usuario, boolean online)
      throws InsideServiceStoreException {
    logger.debug("Inicio asignarObjetoUsuario");
    Session session = sessionFactory.openSession();
    try {
      Integer idUnidadActiva;
      Integer idProcedimientoActivo;
      if (online) {
        UnidadUsuario unidadUsuario = getUsuarioEntity(usuario, session).getUnidadActiva();
        idUnidadActiva = unidadUsuario.getUnidad().getId();
        idProcedimientoActivo = unidadUsuario.getIdProcedimiento();
      } else {
        UnidadWsAplicacion unidadAplicacion =
            getAplicacionEntity(usuario, session).getUnidadActiva();
        idUnidadActiva = unidadAplicacion.getIdUnidad();
        idProcedimientoActivo = unidadAplicacion.getIdProcedimiento();
      }
      return calculateObjetoUnidad(obj, idUnidadActiva, idProcedimientoActivo, session);
    } finally {
      session.close();
    }
  }

  /**
   * Relacionamos un objeto de tipo documento/expediente con una unidad orgánica
   * 
   * @param obj
   * @param usuario
   * @param session
   * @return
   * @throws InsideServiceStoreException
   */
  @SuppressWarnings("unchecked")
  public Object calculateObjetoUnidad(Object obj, Integer unidad, Integer procedimiento,
      Session session) throws InsideServiceStoreException {
    Object dato = null;

    if (unidad != null) {
      if (obj instanceof ObjetoExpedienteInside) {
        dato = generarObjetoExpedienteUnidad(obj, unidad, procedimiento, session);
      } else if (obj instanceof ObjetoDocumentoInside) {
        dato = generarObjetoDocumentoUnidad(obj, unidad, procedimiento, session);
      }

      return dato;
    } else {
      throw new InsideServiceStoreException("El usuario no tiene asociadas dir3 activas");
    }
  }

  public Object generarObjetoDocumentoUnidad(Object obj, Integer unidad, Integer procedimiento,
      Session session) {
    Object dato;
    Calendar time = GregorianCalendar.getInstance();
    Criteria documentoUnidad = session.createCriteria(DocumentoUnidad.class);
    documentoUnidad
        .add(Restrictions.eq(IDENTIFICADOR, ((ObjetoDocumentoInside) obj).getIdentificador()));
    documentoUnidad.add(Restrictions.eq(ID_UNIDAD, unidad));
    if (procedimiento != null)
      documentoUnidad.add(Restrictions.eq(ID_PROCEDIMIENTO, procedimiento));
    DocumentoUnidad documentoUnidadEntity = (DocumentoUnidad) documentoUnidad.uniqueResult();

    if (documentoUnidadEntity != null) {
      documentoUnidadEntity.setFechaVersion(time.getTime());
      dato = documentoUnidadEntity;
    } else {
      dato = new DocumentoUnidad();
      ((DocumentoUnidad) dato).setIdUnidad(unidad);
      ((DocumentoUnidad) dato).setIdProcedimiento(procedimiento);
      ((DocumentoUnidad) dato).setIdentificador(((ObjetoDocumentoInside) obj).getIdentificador());
      ((DocumentoUnidad) dato).setFechaVersion(time.getTime());
    }
    return dato;
  }

  public Object generarObjetoExpedienteUnidad(Object obj, Integer unidad, Integer procedimiento,
      Session session) {
    Object dato;
    Calendar time = GregorianCalendar.getInstance();
    Criteria expedienteUnidad = session.createCriteria(ExpedienteUnidad.class);
    expedienteUnidad
        .add(Restrictions.eq(IDENTIFICADOR, ((ObjetoExpedienteInside) obj).getIdentificador()));
    expedienteUnidad.add(Restrictions.eq(ID_UNIDAD, unidad));
    if (procedimiento != null)
      expedienteUnidad.add(Restrictions.eq(ID_PROCEDIMIENTO, procedimiento));
    ExpedienteUnidad expedienteUnidadEntity = (ExpedienteUnidad) expedienteUnidad.uniqueResult();

    if (expedienteUnidadEntity != null) {
      expedienteUnidadEntity.setFechaVersion(time.getTime());
      expedienteUnidadEntity
          .setEstadoExpediente(((ObjetoExpedienteInside) obj).getMetadatos().getEstado().value());
      dato = expedienteUnidadEntity;
    } else {
      dato = new ExpedienteUnidad();
      ((ExpedienteUnidad) dato).setIdUnidad(unidad);
      ((ExpedienteUnidad) dato).setIdProcedimiento(procedimiento);
      ((ExpedienteUnidad) dato).setIdentificador(((ObjetoExpedienteInside) obj).getIdentificador());
      ((ExpedienteUnidad) dato).setFechaVersion(time.getTime());
      ((ExpedienteUnidad) dato)
          .setEstadoExpediente(((ObjetoExpedienteInside) obj).getMetadatos().getEstado().value());
    }
    return dato;
  }

  /**
   * Desactivar el documento de la unidad es poner la fecha de versión a nulo. Eso nos indica que el
   * documento en si está dado de baja.
   */
  @Override
  public void deleteDocument(String identificador) throws InsideServiceStoreException {
    logger.debug("Inicio eliminarDocumentoUnidad");
    Session session = sessionFactory.openSession();
    try {
      persister.deleteDocument(identificador, session);
    } finally {
      session.close();
    }
  }

  /**
   * Desactivar el documento de la unidad es poner la fecha de versión a nulo. Eso nos indica que el
   * documento en si está dado de baja.
   */
  @Override
  public void deleteExpedient(String identificador) throws InsideServiceStoreException {
    logger.debug("Inicio eliminarDocumentoUnidad");
    Session session = sessionFactory.openSession();
    try {
      persister.deleteExpedient(identificador, session);
    } finally {
      session.close();
    }
  }

  @Override
  public boolean validarObjetoUnidad(ObjetoInside<?> obj, String identificadorUsuario,
      boolean online) throws InsideServiceStoreException {
    logger.debug("Inicio validarObjetoUsuario");
    Session session = sessionFactory.openSession();
    boolean validar = false;
    List<ObjetoInsideUnidadUsuario> unidad;

    try {
      unidad = getUnidadesOrganicasAsociadas(identificadorUsuario, online, false, session);
      if (obj instanceof ObjetoExpedienteInside) {
        validar = validarExpedienteUnidad(obj, session, unidad);
      } else if (obj instanceof ObjetoDocumentoInside) {
        validar = validarDocumentoUnidad(obj, session, unidad);
      }

    } catch (Exception e) {
      throw new InsideServiceStoreException("Excepción validando objeto contra la BBDD", e);
    } finally {
      session.close();
    }

    return validar;
  }

  public boolean validarDocumentoUnidad(ObjetoInside<?> obj, Session session,
      List<ObjetoInsideUnidadUsuario> unidad) {
    boolean validar = false;
    Criteria documentoUnidad = session.createCriteria(DocumentoUnidad.class);
    documentoUnidad.add(Restrictions.eq(IDENTIFICADOR, obj.getIdentificador()));

    List<DocumentoUnidad> docuemntoUnidadEntity = documentoUnidad.list();
    if (docuemntoUnidadEntity != null) {
      for (DocumentoUnidad docUnidad : docuemntoUnidadEntity) {
        validar = validarUnidadesCompatibles(unidad, docUnidad.getIdUnidad(),
            docUnidad.getIdProcedimiento());
        if (validar)
          break;
      }
    }
    return validar;
  }

  public boolean validarExpedienteUnidad(ObjetoInside<?> obj, Session session,
      List<ObjetoInsideUnidadUsuario> unidad) {
    boolean validar = false;
    Criteria expedienteUnidad = session.createCriteria(ExpedienteUnidad.class);
    expedienteUnidad.add(Restrictions.eq(IDENTIFICADOR, obj.getIdentificador()));

    ExpedienteUnidad expedienteUnidadEntity = (ExpedienteUnidad) expedienteUnidad.uniqueResult();
    if (expedienteUnidadEntity != null) {
      validar = validarUnidadesCompatibles(unidad, expedienteUnidadEntity.getIdUnidad(),
          expedienteUnidadEntity.getIdProcedimiento());
    }
    return validar;
  }

  private List<ObjetoInsideUnidadUsuario> getUnidadesOrganicasAsociadas(String identificadorUsuario,
      boolean online, boolean soloUnidadActiva, Session session)
      throws InsideServiceStoreException {
    List<ObjetoInsideUnidadUsuario> lista = new ArrayList<ObjetoInsideUnidadUsuario>();
    if (online) {
      UsuarioInside usu = getUsuarioEntity(identificadorUsuario, session);
      if (soloUnidadActiva) {
        lista
            .add(InsideServiceStoreHibernateConverterUnidadUsuario.toInside(usu.getUnidadActiva()));
      } else {
        for (UnidadUsuario unidad : usu.getListaUnidades())
          lista.add(InsideServiceStoreHibernateConverterUnidadUsuario.toInside(unidad));
      }
    } else {
      InsideWsAplicacion usu = getAplicacionEntity(identificadorUsuario, session);
      if (soloUnidadActiva) {
        lista
            .add(InsideServiceStoreHibernateConverterUnidadUsuario.toInside(usu.getUnidadActiva()));
      } else {
        for (UnidadWsAplicacion unidad : usu.getListaUnidades())
          lista.add(InsideServiceStoreHibernateConverterUnidadUsuario.toInside(unidad));
      }
    }
    return lista;
  }

  /**
   * Comprueba si la unidad es igual que idUnidad o si es padre de esta
   * 
   * @param unidad
   * @param idUnidad
   * @param idProcedimiento
   * @return
   */
  private boolean validarUnidadesCompatibles(List<ObjetoInsideUnidadUsuario> unidades,
      Integer idUnidad, Integer idProcedimiento) {
    boolean validar = false;

    for (ObjetoInsideUnidadUsuario unidad : unidades) {
      // Si la unidad coincide la compatibilidad es correcta
      if (unidad.getIdUnidad().intValue() == idUnidad.intValue()) {
        if (unidad.getIdProcedimiento() == null) {
          validar = true;
        } else if (idProcedimiento != null
            && unidad.getIdProcedimiento().intValue() == idProcedimiento.intValue()) {
          validar = true;
        }
      }
    }

    return validar;
  }

  @Override
  public List<ObjetoInsideExpedienteUnidad> getExpedientesUnidad(String nif,
      boolean soloUnidadActiva) throws InsideServiceStoreException {
    List<ObjetoInsideExpedienteUnidad> retorno = null;
    Session session = sessionFactory.openSession();
    try {
      List<ExpedienteUnidad> datos = getListExpedientesUnidad(nif, soloUnidadActiva, session);
      if (CollectionUtils.isNotEmpty(datos)) {
        retorno = obtenerExpedientesUnidadInformandoRemisionMJU(datos);
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  public List<ExpedienteUnidad> getListExpedientesUnidad(String nif, boolean soloUnidadActiva,
      Session session) throws InsideServiceStoreException {
    List<ExpedienteUnidad> datos = new ArrayList<ExpedienteUnidad>();
    List<ObjetoInsideUnidadUsuario> unidades =
        getUnidadesOrganicasAsociadas(nif, true, soloUnidadActiva, session);

    if (!unidades.isEmpty()) {
      Criteria crit = session.createCriteria(ExpedienteUnidad.class);
      Disjunction disjunction = getElementToFind(unidades);
      crit.add(disjunction);
      crit.add(Restrictions.isNotNull(FECHA_VERSION));
      crit.addOrder(Order.desc(FECHA_VERSION));
      datos = crit.list();
    }
    return datos;
  }

  @Override
  public List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadAutocompleter(String nif,
      boolean soloUnidadActiva) throws InsideServiceStoreException {
    List<ObjetoInsideExpedienteUnidad> retorno = null;
    Session session = sessionFactory.openSession();

    try {
      List<ExpedienteUnidad> datos = getListExpedientesUnidad(nif, soloUnidadActiva, session);
      if (CollectionUtils.isNotEmpty(datos)) {
        retorno = obtenerExpedientesUnidad(datos);
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ObjetoInsideExpedienteUnidad> getExpedientesUnidadPorMetadatos(String nif,
      TipoMetadatosAdicionales tipoMetadatosAdicionales) throws InsideServiceStoreException {

    List<ObjetoInsideExpedienteUnidad> retorno = null;
    Session session = sessionFactory.openSession();
    List<ObjetoInsideUnidadUsuario> unidades;
    unidades = getUnidadesOrganicasAsociadas(nif, true, true, session);

    if (!unidades.isEmpty()) {

      Criteria crit = session.createCriteria(ExpedienteInsideMetadatosAdicionales.class);
      Criterion impossibleCriterion = Restrictions.sqlRestriction("(1=0)");
      LogicalExpression expresionOr = Restrictions.or(impossibleCriterion, impossibleCriterion);

      for (MetadatoAdicional metadato : tipoMetadatosAdicionales.getMetadatoAdicional()) {

        expresionOr = Restrictions.or(expresionOr, Restrictions.eq("nombre", metadato.getNombre()));
        org.w3c.dom.Element e = (org.w3c.dom.Element) metadato.getValor();
        expresionOr = Restrictions.or(expresionOr, Restrictions.eq("valor", e.getTextContent()));
      }
      crit.add(expresionOr);

      List<ExpedienteInsideMetadatosAdicionales> expdienteMetAdic = crit.list();
      List<String> idsExps = new ArrayList<String>();
      if (expdienteMetAdic.isEmpty()) {
        return retorno;
      }
      for (ExpedienteInsideMetadatosAdicionales expInMetAds : expdienteMetAdic) {
        idsExps.add(expInMetAds.getExpedienteInside().getIdentificador());
      }

      crit = session.createCriteria(ExpedienteUnidad.class);
      Disjunction disjunction = getElementToFind(unidades);
      crit.add(disjunction);
      crit.add(Restrictions.in("identificador", idsExps));
      crit.add(Restrictions.isNotNull(FECHA_VERSION));
      crit.addOrder(Order.desc(FECHA_VERSION));
      List<ExpedienteUnidad> datos = crit.list();

      if (CollectionUtils.isNotEmpty(datos)) {
        retorno = obtenerExpedientesUnidad(datos);
      }
    }

    return retorno;
  }

  private List<ObjetoInsideExpedienteUnidad> obtenerExpedientesUnidad(List<ExpedienteUnidad> datos)
      throws InsideServiceStoreException {
    List<ObjetoInsideExpedienteUnidad> retorno = new ArrayList<ObjetoInsideExpedienteUnidad>();
    for (ExpedienteUnidad dato : datos) {
      ObjetoInsideExpedienteUnidad expedienteUnidad = new ObjetoInsideExpedienteUnidad();
      expedienteUnidad.setIdentificador(dato.getIdentificador());
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(dato.getFechaVersion());
      expedienteUnidad.setFechaVersion(cal);
      expedienteUnidad.setEstadoExpediente(
          ObjetoExpedienteInsideMetadatosEnumeracionEstados.fromValue(dato.getEstadoExpediente()));
      expedienteUnidad.setIdUnidad(dato.getIdUnidad());
      retorno.add(expedienteUnidad);
    }
    return retorno;
  }

  private List<ObjetoInsideExpedienteUnidad> obtenerExpedientesUnidadInformandoRemisionMJU(
      List<ExpedienteUnidad> datos) throws InsideServiceStoreException {
    List<ObjetoInsideExpedienteUnidad> retorno = new ArrayList<ObjetoInsideExpedienteUnidad>();
    for (ExpedienteUnidad dato : datos) {
      ObjetoInsideExpedienteUnidad expedienteUnidad = new ObjetoInsideExpedienteUnidad();
      expedienteUnidad.setIdentificador(dato.getIdentificador());
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(dato.getFechaVersion());
      expedienteUnidad.setFechaVersion(cal);
      expedienteUnidad.setEstadoExpediente(
          ObjetoExpedienteInsideMetadatosEnumeracionEstados.fromValue(dato.getEstadoExpediente()));
      expedienteUnidad.setIdUnidad(dato.getIdUnidad());

      retorno.add(expedienteUnidad);
    }
    return retorno;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ObjetoInsideDocumentoUnidad> getDocumentosUnidad(String nif, boolean soloUnidadActiva)
      throws InsideServiceStoreException {
    List<ObjetoInsideDocumentoUnidad> retorno = null;
    Session session = sessionFactory.openSession();
    List<ObjetoInsideUnidadUsuario> unidades;

    try {
      // buscamos las unidades asociadas al usuario
      unidades = getUnidadesOrganicasAsociadas(nif, true, soloUnidadActiva, session);

      if (!unidades.isEmpty()) {
        Criteria crit = session.createCriteria(DocumentoUnidad.class);
        Disjunction disjunction = getElementToFind(unidades);
        crit.add(disjunction);
        crit.add(Restrictions.isNotNull(FECHA_VERSION));
        crit.addOrder(Order.desc(FECHA_VERSION));
        List<DocumentoUnidad> datos = crit.list();

        if (CollectionUtils.isNotEmpty(datos)) {
          retorno = obtenerDocumento(datos);
        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ObjetoInsideDocumentoUnidad> getDocumentosMetadatosUnidad(String nif,
      boolean soloUnidadActiva) throws InsideServiceStoreException {
    List<ObjetoInsideDocumentoUnidad> retorno = null;
    Session session = sessionFactory.openSession();
    List<ObjetoInsideUnidadUsuario> unidades;

    try {
      // buscamos las unidades asociadas al usuario
      unidades = getUnidadesOrganicasAsociadas(nif, true, soloUnidadActiva, session);

      if (!unidades.isEmpty()) {
        Criteria crit = session.createCriteria(DocumentoMetadatosAdicionales.class);
        Disjunction disjunction = getElementToFind(unidades);
        crit.add(disjunction);
        crit.add(Restrictions.isNotNull(FECHA_VERSION));
        crit.addOrder(Order.desc(FECHA_VERSION));
        List<DocumentoMetadatosAdicionales> datos = crit.list();

        if (CollectionUtils.isNotEmpty(datos)) {
          retorno = obtenerDocumentoMetadatos(datos);
        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  public Disjunction getElementToFind(List<ObjetoInsideUnidadUsuario> unidades) {
    Disjunction disjunction = Restrictions.disjunction();
    for (ObjetoInsideUnidadUsuario unidad : unidades) {
      if (unidad.getIdProcedimiento() != null) {
        disjunction.add(Restrictions.and(Restrictions.eq(ID_UNIDAD, unidad.getIdUnidad()),
            Restrictions.eq(ID_PROCEDIMIENTO, unidad.getIdProcedimiento())));
      } else {
        disjunction.add(Restrictions.eq(ID_UNIDAD, unidad.getIdUnidad()));
      }
    }
    return disjunction;
  }

  public List<ObjetoInsideDocumentoUnidad> obtenerDocumentoMetadatos(
      List<DocumentoMetadatosAdicionales> datos) throws InsideServiceStoreException {
    List<ObjetoInsideDocumentoUnidad> retorno = new ArrayList<ObjetoInsideDocumentoUnidad>();
    List<String> eliminarIdsDuplicados = new ArrayList<String>();
    for (DocumentoMetadatosAdicionales dato : datos) {
      ObjetoInsideDocumentoUnidad documentoUnidad = new ObjetoInsideDocumentoUnidad();
      String identificadorDocumento = dato.getIdentificador();
      if (!eliminarIdsDuplicados.contains(identificadorDocumento)) {
        documentoUnidad.setIdentificador(dato.getIdentificador());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dato.getFechaVersion());
        documentoUnidad.setFechaVersion(cal);
        documentoUnidad.setIdUnidad(dato.getIdUnidad());
        documentoUnidad.setMetAdicionales(dato.getNombreValor());
        eliminarIdsDuplicados.add(identificadorDocumento);
        retorno.add(documentoUnidad);
      }
    }
    return retorno;
  }

  public List<ObjetoInsideDocumentoUnidad> obtenerDocumento(List<DocumentoUnidad> datos)
      throws InsideServiceStoreException {
    List<ObjetoInsideDocumentoUnidad> retorno = new ArrayList<ObjetoInsideDocumentoUnidad>();
    List<String> eliminarIdsDuplicados = new ArrayList<String>();
    for (DocumentoUnidad dato : datos) {
      ObjetoInsideDocumentoUnidad documentoUnidad = new ObjetoInsideDocumentoUnidad();
      String identificadorDocumento = dato.getIdentificador();
      if (!eliminarIdsDuplicados.contains(identificadorDocumento)) {
        documentoUnidad.setIdentificador(dato.getIdentificador());
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dato.getFechaVersion());
        documentoUnidad.setFechaVersion(cal);
        documentoUnidad.setIdUnidad(dato.getIdUnidad());
        eliminarIdsDuplicados.add(identificadorDocumento);
        retorno.add(documentoUnidad);
      }
    }
    return retorno;
  }

  @Override
  public ObjetoExpedienteToken getTokenByUsuarioExpediente(ObjetoExpedienteToken data)
      throws InsideServiceStoreException {
    ObjetoExpedienteToken retorno = null;
    Session session = sessionFactory.openSession();
    try {
      ExpedienteToken token = converterExpedienteToken.toEntity(data, session);

      Criteria crit = session.createCriteria(ExpedienteToken.class);
      crit.add(Restrictions.eq("usuario", token.getUsuario()));
      crit.add(Restrictions.eq(IDENTIFICADOR, token.getIdentificador()));
      ExpedienteToken datos = (ExpedienteToken) crit.uniqueResult();

      if (datos != null) {
        retorno = converterExpedienteToken.toInside(datos);
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public void saveToken(ObjetoExpedienteToken usuarioToken) throws InsideServiceStoreException {
    logger.debug("Inicio saveToken");
    Session session = sessionFactory.openSession();
    try {
      persister.saveToken(usuarioToken, session);
    } finally {
      session.close();
    }
  }

  @Override
  public void saveAuditoriaToken(ObjetoAuditoriaToken objectAuditoriaToken)
      throws InsideServiceStoreException {
    logger.debug("Inicio saveAuditoriaToken");
    Session session = sessionFactory.openSession();
    try {
      persister.saveAuditoriaToken(objectAuditoriaToken, session);
    } finally {
      session.close();
    }
  }

  @Override
  public void saveAuditoriaAccesoDocumento(
      ObjetoAuditoriaAccesoDocumento objetoAuditoriaAccesoDocumento)
      throws InsideServiceStoreException {
    logger.debug("Inicio saveAuditoriaAccesoDocumentoe");
    Session session = sessionFactory.openSession();
    try {
      persister
          .saveAuditoriaAccesoDocumento(InsideServiceStoreHibernateConverterAuditoriaAccesoDocumento
              .toEntity(objetoAuditoriaAccesoDocumento), session);
    } finally {
      session.close();
    }
  }

  private List<String> getOrganosPadres(String dir3, Session session) {
    Query query = session.createSQLQuery("CALL DevuelveDir3Padres(:id)").setParameter("id", dir3);

    List<String> result = query.list();
    return result;
  }

  @Override
  public ObjetoExpedienteToken getTokenByData(ObjetoExpedienteToken usuarioToken)
      throws InsideServiceStoreException {
    ObjetoExpedienteToken retorno = null;
    Session session = sessionFactory.openSession();
    try {
      ExpedienteToken token = converterExpedienteToken.toEntity(usuarioToken, session);

      Criteria crit = session.createCriteria(ExpedienteToken.class);
      crit.add(Restrictions.eq("csv", token.getCsv()));
      crit.add(Restrictions.eq("uuid", token.getUuid()));
      crit.add(Restrictions.eq(IDENTIFICADOR, token.getIdentificador()));
      ExpedienteToken datos = (ExpedienteToken) crit.uniqueResult();

      if (datos != null) {
        retorno = converterExpedienteToken.toInside(datos);
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ObjetoInsideUsuario> getUsuarios() throws InsideServiceStoreException {
    logger.debug("Inicio getUsuarios");
    Session session = sessionFactory.openSession();
    List<ObjetoInsideUsuario> retorno = new ArrayList<ObjetoInsideUsuario>();
    try {
      Criteria crit = session.createCriteria(UsuarioInside.class);
      List<UsuarioInside> datos = crit.list();
      if (CollectionUtils.isNotEmpty(datos)) {
        for (UsuarioInside user : datos) {
          retorno.add(InsideServiceStoreHibernateConverterUsuario.toInside(user));
        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @SuppressWarnings("unchecked")
  @Override
  public ResultadoBusquedaUsuario getUsuarios(ObjetoFilterPageRequest data)
      throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    ResultadoBusquedaUsuario resultadoBusqueda = new ResultadoBusquedaUsuario();
    try {
      Criteria criteria = session.createCriteria(UsuarioInside.class, "usuario");
      criteria.createAlias("usuario.listaUnidades", "listaUnidades");
      criteria.add(Restrictions.eq("listaUnidades.activo", true));
      criteria.createAlias("listaUnidades.unidad", "unidad", Criteria.LEFT_JOIN);
      criteria.createAlias("listaUnidades.procedimiento", "procedimiento", Criteria.LEFT_JOIN);

      if (StringUtils.isNotEmpty(data.getFilter())) {
        StringTokenizer token = new StringTokenizer(data.getFilter(), " ");

        Disjunction disjunction = Restrictions.disjunction();

        while (token.hasMoreTokens()) {
          String filter = token.nextToken();
          if (filter.equalsIgnoreCase("true") || filter.equalsIgnoreCase("false")) {
            disjunction
                .add(Restrictions.eq("usuario.activo", Boolean.parseBoolean(filter.toUpperCase())));
          }

          String filterLike = "%" + filter + "%";
          disjunction.add(Restrictions.ilike("nif", filterLike));
          disjunction.add(Restrictions.ilike("unidad.codigoUnidadOrganica", filterLike));
          disjunction.add(Restrictions.ilike("unidad.nombreUnidadOrganica", filterLike));
          disjunction.add(Restrictions.ilike("procedimiento.numProcedimiento", filterLike));

          criteria.add(disjunction);

          if (token.hasMoreTokens()) {
            disjunction = Restrictions.disjunction();
          }
        }

      }
      // criteria.setFirstResult(data.getOffset());
      // criteria.setMaxResults(data.getPageSize())

      List<?> objetos = null;

      try {
        objetos = criteria.list();
      } catch (ClassCastException e) {
        throw new InsideServiceStoreQueryNotValidException(
            "Alguno de los datos proporcionados no tenía el tipo de datos adecuado para su columna",
            e);
      } ;

      if (data.getPageSize() == -1) {
        resultadoBusqueda.setPagina(1);
        resultadoBusqueda.setPorPagina(objetos.size());
        resultadoBusqueda.setTotales(objetos.size());
        // Si se ha establecido limite se configura el resultado.
      } else {
        resultadoBusqueda.setPagina(data.getPageNumber());
        resultadoBusqueda.setPorPagina(data.getPageSize());
        resultadoBusqueda.setTotales(objetos.size());
      }

      List<?> subLista = (data.getPageSize() == -1) ? objetos
          : InsideUtils.getSublista(objetos, data.getPageSize(), data.getPageNumber());

      // Si no hay límite, la sublista será la lista de todos los
      // resultados.
      // List<?> subLista = criteria.list();
      logger.debug("queryObjects: Obtenidos " + subLista.size() + " resultados ");

      List<ObjetoInsideUsuario> resultados = new ArrayList<ObjetoInsideUsuario>();
      for (Object entity : subLista) {

        // if (entity instanceof UsuarioInside) {
        UsuarioInside usuarioInside = (UsuarioInside) entity;
        String numeroProcedimiento = null;
        if (usuarioInside.contieneUnidadActiva()) {
          UnidadUsuario unidadUsuario = usuarioInside.getUnidadActiva();
          numeroProcedimiento = unidadUsuario.getIdProcedimiento() != null
              ? unidadUsuario.getProcedimiento().getNumProcedimiento()
              : null;

        }

        ObjetoInsideUsuario res = InsideServiceStoreHibernateConverterUsuario
            .toInsideUnidad(usuarioInside, numeroProcedimiento);
        resultados.add(res);
        // }

      }
      resultadoBusqueda.setResultados(resultados);

      return resultadoBusqueda;

    } catch (Exception e) {
      throw new InsideServiceStoreException("No se ha podido realizar la consulta", e);
    } finally {
      session.close();
    }

  }

  @Override
  public ObjetoInsideUsuario altaUsuario(ObjetoInsideUsuario data)
      throws InsideServiceStoreException {
    logger.debug("Inicio altaUsuario");
    Session session = sessionFactory.openSession();
    try {
      NumeroProcedimiento numeroProcedimiento = null;
      data.setActivo(true);
      UnidadOrganica unidad = getUnidadEntity(data.getUnidadOrganicaActiva(), session);

      ObjetoInsideUsuario usuario = persister.saveUser(data, unidad, null, session);
      usuario.setUnidadOrganicaActiva(unidad.getCodigoUnidadOrganica());

      return usuario;
    } finally {
      session.close();
    }
  }

  @Override
  public ObjetoInsideUsuario bajaUsuario(ObjetoInsideUsuario data)
      throws InsideServiceStoreException {
    logger.debug("Inicio bajaUsuario");
    Session session = sessionFactory.openSession();
    try {
      UsuarioInside usuario = getUsuarioEntity(data.getNif(), session);
      usuario.setActivo(false);
      ObjetoInsideUsuario usu = persister.saveUser(usuario, session);
      deleteUnidadUsuario(usuario, null, null, session);
      return usu;
    } finally {
      session.close();
    }
  }

  private void deleteUnidadUsuario(UsuarioInside usuario, Integer idUnidad, Integer idProcedimiento,
      Session session) throws InsideServiceStoreException {

    Criteria criUnidadUsuario = session.createCriteria(UnidadUsuario.class);
    criUnidadUsuario.add(Restrictions.eq("idUsuario", usuario.getId()));
    if (idUnidad != null) {
      criUnidadUsuario.add(Restrictions.eq("unidad.id", idUnidad));
      if (idProcedimiento != null) {
        criUnidadUsuario.add(Restrictions.eq("procedimiento.id", idProcedimiento));
      }
    }
    compruebaRestoUnidades(usuario, idUnidad, session);
    List<UnidadUsuario> listUnidadUsuario = criUnidadUsuario.list();
    if (!listUnidadUsuario.isEmpty()) {
      for (UnidadUsuario uniUsu : listUnidadUsuario) {
        persister.deleteObjetoUnidad(uniUsu, session);
      }
    }
  }

  // Si al eliminar la unidad del usuario no tiene mas asignadas, ponemos
  // inactivo al usuario
  private void compruebaRestoUnidades(UsuarioInside usuario, Integer idUnidad, Session session) {
    Criteria criUnidadUsuario = session.createCriteria(UnidadUsuario.class);
    criUnidadUsuario.add(Restrictions.eq("idUsuario", usuario.getId()));
    criUnidadUsuario.add(Restrictions.ne("unidad.id", idUnidad));
    List<UnidadUsuario> listUnidadUsuario = criUnidadUsuario.list();
    if (listUnidadUsuario.isEmpty()) {
      usuario.setActivo(false);
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ObjetoInsideAplicacion> getAplicaciones() throws InsideServiceStoreException {
    logger.debug("Inicio getAplicaciones");
    Session session = sessionFactory.openSession();
    List<ObjetoInsideAplicacion> retorno = new ArrayList<ObjetoInsideAplicacion>();
    try {
      Criteria crit = session.createCriteria(InsideWsAplicacion.class);
      List<InsideWsAplicacion> datos = crit.list();
      if (CollectionUtils.isNotEmpty(datos)) {
        for (InsideWsAplicacion app : datos) {

          Criteria criteriaCredential = session.createCriteria(CredencialesEeutil.class);
          criteriaCredential.add(Restrictions.eq("idAplicacionInterna", app.getId()));
          CredencialesEeutil dataCredential =
              (CredencialesEeutil) criteriaCredential.uniqueResult();
          app.setCredencialEeeutil(dataCredential);

          retorno.add(InsideServiceStoreHibernateConverterAplicacion.toInside(app));
        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public ObjetoInsideAplicacion altaAplicacion(ObjetoInsideAplicacion data)
      throws InsideServiceStoreException {
    logger.debug("Inicio altaAplicacion");
    Session session = sessionFactory.openSession();
    try {
      Integer idProcedimiento = null;
      NumeroProcedimiento numeroProcedimiento = null;
      data.setActiva(true);
      UnidadOrganica unidad = getUnidadEntity(data.getUnidadOrganicaActiva(), session);
      if (StringUtils.isNotEmpty(data.getNumeroProcedimiento())) {
        numeroProcedimiento = getNumeroProcesoEntity(data.getNumeroProcedimiento(), session);
        idProcedimiento = numeroProcedimiento.getId();
      }
      ObjetoInsideAplicacion aplicacion = persister.saveApp(data, unidad, idProcedimiento, session);
      aplicacion.setUnidadOrganicaActiva(unidad.getCodigoUnidadOrganica());
      if (numeroProcedimiento != null)
        aplicacion.setNumeroProcedimiento(numeroProcedimiento.getNumProcedimiento());
      return aplicacion;
    } finally {
      session.close();
    }
  }

  @Override
  public ObjetoInsideAplicacion bajaAplicacion(ObjetoInsideAplicacion data)
      throws InsideServiceStoreException {
    logger.debug("Inicio bajaAplicacion");
    Session session = sessionFactory.openSession();
    try {
      InsideWsAplicacion aplicacion = getAplicacionEntity(data.getAplicacion(), session);
      aplicacion.setActivo(false);
      ObjetoInsideAplicacion apli = persister.saveApp(aplicacion, session);
      deleteUnidadWSAplicacion(aplicacion.getId(), session);
      return apli;
    } finally {
      session.close();
    }
  }

  private void deleteUnidadWSAplicacion(Integer idAplicacion, Session session)
      throws InsideServiceStoreException {
    Criteria criUnidadWsAplicacion = session.createCriteria(UnidadWsAplicacion.class);
    criUnidadWsAplicacion.add(Restrictions.eq("idAplicacion", idAplicacion));
    List<UnidadWsAplicacion> listUnidadWsAplicacion = criUnidadWsAplicacion.list();
    if (!listUnidadWsAplicacion.isEmpty()) {
      for (UnidadWsAplicacion uniApli : listUnidadWsAplicacion)
        persister.deleteObjetoUnidad(uniApli, session);
    }
  }

  @Override
  public ObjetoInsideUnidad altaUnidadAplicacion(String idUnidad, String numeroProcedimiento,
      String idAplicacion) throws InsideServiceStoreException {
    logger.debug("Inicio altaUnidadAplicacion");
    Session session = sessionFactory.openSession();
    try {
      InsideWsAplicacion aplicacion =
          cambiarUnidadAplicacion(idUnidad, numeroProcedimiento, idAplicacion, session);
      persister.saveApp(aplicacion, session);
      UnidadOrganica unidad = getUnidadEntity(aplicacion.getUnidadActiva().getIdUnidad(), session);
      return InsideServiceStoreHibernateConverterUnidad.toInside(unidad, numeroProcedimiento, true);
    } finally {
      session.close();
    }
  }

  @Override
  public void deleteUnidadAplicacion(String codigoUnidad, String numeroProcedimiento,
      String idAplicacion) throws InsideServiceStoreException {
    logger.debug("Inicio altaUnidadAplicacion");
    Session session = sessionFactory.openSession();
    try {
      Integer idUnidad = null;
      Integer idProcedimiento = null;
      InsideWsAplicacion aplicacion = getAplicacionEntity(idAplicacion, session);
      if (StringUtils.isNotEmpty(codigoUnidad)) {
        UnidadOrganica unidadOrganiza = getUnidadEntity(codigoUnidad, session);
        idUnidad = unidadOrganiza.getId();
        if (StringUtils.isNotEmpty(numeroProcedimiento)) {
          NumeroProcedimiento numProcedimiento =
              getNumeroProcesoEntity(numeroProcedimiento, session);
          idProcedimiento = numProcedimiento.getId();
        }
      }
      deleteUnidadAplicacion(aplicacion.getId(), idUnidad, idProcedimiento, session);
    } finally {
      session.close();
    }
  }

  private void deleteUnidadAplicacion(Integer idAplicacion, Integer idUnidad,
      Integer idProcedimiento, Session session) throws InsideServiceStoreException {
    Criteria criUnidadUsuario = session.createCriteria(UnidadWsAplicacion.class);
    criUnidadUsuario.add(Restrictions.eq("idAplicacion", idAplicacion));
    if (idUnidad != null) {
      criUnidadUsuario.add(Restrictions.eq(ID_UNIDAD, idUnidad));
      if (idProcedimiento != null) {
        criUnidadUsuario.add(Restrictions.eq(ID_PROCEDIMIENTO, idProcedimiento));
      }
    }
    List<UnidadWsAplicacion> listUnidadWSAplicacion = criUnidadUsuario.list();
    if (!listUnidadWSAplicacion.isEmpty()) {
      for (UnidadWsAplicacion uniUsu : listUnidadWSAplicacion)
        persister.deleteObjetoUnidad(uniUsu, session);
    }
  }

  @Override
  public ObjetoInsideUnidad altaUnidadUsuario(String idUnidad, String numeroProcedimiento,
      String idUsuario) throws InsideServiceStoreException {
    logger.debug("Inicio asociarUnidadUsuario");
    Session session = sessionFactory.openSession();
    try {
      UsuarioInside usuario =
          cambiarUnidadUsuario(idUnidad, numeroProcedimiento, idUsuario, session);
      persister.saveUser(usuario, session);
      UnidadOrganica unidad =
          getUnidadEntity(usuario.getUnidadActiva().getUnidad().getId(), session);
      return InsideServiceStoreHibernateConverterUnidad.toInside(unidad, numeroProcedimiento, true);
    } finally {
      session.close();
    }
  }

  @Override
  public void deleteUnidadUsuario(String codigoUnidad, String numeroProcedimiento, String idUsuario)
      throws InsideServiceStoreException {
    logger.debug("Inicio deleteUnidadUsuario");
    Session session = sessionFactory.openSession();
    try {
      Integer idUnidad = null;
      Integer idProcedimiento = null;
      UsuarioInside usuario = getUsuarioEntity(idUsuario, session);
      if (StringUtils.isNotEmpty(codigoUnidad)) {
        UnidadOrganica unidadOrganica = getUnidadEntity(codigoUnidad, session);
        idUnidad = unidadOrganica.getId();
        if (StringUtils.isNotEmpty(numeroProcedimiento)) {
          NumeroProcedimiento numProcedimiento =
              getNumeroProcesoEntity(numeroProcedimiento, session);
          idProcedimiento = numProcedimiento.getId();
        }
      }
      deleteUnidadUsuario(usuario, idUnidad, idProcedimiento, session);
    } finally {
      session.close();
    }
  }

  public UsuarioInside cambiarUnidadUsuario(String idUnidad, String numeroProcedimiento,
      String idUsuario, Session session) throws InsideServiceStoreException {

    UsuarioInside usuario = getUsuarioEntity(idUsuario, session);
    UnidadOrganica unidad;
    NumeroProcedimiento procedimiento = null;
    Integer idProcedimiento = null;
    if (usuario != null) {
      unidad = getUnidadEntity(idUnidad, session);
      if (StringUtils.isNotEmpty(numeroProcedimiento)) {
        procedimiento = getNumeroProcesoEntity(numeroProcedimiento, session);
        idProcedimiento = procedimiento.getId();
      }
    } else
      throw new InsideServiceStoreException("Proceso incompleto. El usuario no existe en BBDD");

    desactivarDir3Usuario(usuario.getListaUnidades());
    UnidadUsuario unidadUsu = usuario.getUnidad(unidad.getId(), idProcedimiento, usuario.getId());
    if (unidadUsu != null) {
      unidadUsu.setActivo(true);
    } else {
      unidadUsu = new UnidadUsuario();
      unidadUsu.setUnidad(new UnidadOrganica());
      unidadUsu.getUnidad().setId(unidad.getId());
      unidadUsu.setProcedimiento(procedimiento);
      unidadUsu.setIdUsuario(usuario.getId());
      unidadUsu.setIdRol(0);
      unidadUsu.setActivo(true);
      usuario.getListaUnidades().add(unidadUsu);
    }
    return usuario;
  }

  public InsideWsAplicacion cambiarUnidadAplicacion(String idUnidad, String numeroProcedimiento,
      String idAplicacion, Session session) throws InsideServiceStoreException {

    InsideWsAplicacion aplicacion = getAplicacionEntity(idAplicacion, session);
    UnidadOrganica unidad;
    Integer idProcedimiento = null;
    if (aplicacion != null) {
      unidad = getUnidadEntity(idUnidad, session);
      if (StringUtils.isNotEmpty(numeroProcedimiento)) {
        NumeroProcedimiento procedimiento = getNumeroProcesoEntity(numeroProcedimiento, session);
        idProcedimiento = procedimiento.getId();
      }
    } else
      throw new InsideServiceStoreException("Proceso incompleto. La aplicación no existe en BBDD");

    // Pasamos a estado desactivado sus anteriores dir3
    desactivarDir3Aplicacion(aplicacion.getListaUnidades());
    UnidadWsAplicacion unidadAplicacion =
        aplicacion.getUnidad(unidad.getId(), idProcedimiento, aplicacion.getId());
    if (unidadAplicacion != null) {
      unidadAplicacion.setActivo(true);
    } else {
      unidadAplicacion = new UnidadWsAplicacion();
      unidadAplicacion.setIdUnidad(unidad.getId());
      unidadAplicacion.setIdProcedimiento(idProcedimiento);
      unidadAplicacion.setIdAplicacion(aplicacion.getId());
      unidadAplicacion.setActivo(true);
      aplicacion.getListaUnidades().add(unidadAplicacion);
    }
    return aplicacion;
  }

  public UnidadOrganica getUnidadEntity(Object idUnidad, Session session)
      throws InsideServiceStoreException {
    UnidadOrganica unidad;
    // Recuperamos la unidad orgánica
    Criteria critUnidad = session.createCriteria(UnidadOrganica.class);
    if (idUnidad instanceof String)
      critUnidad.add(Restrictions.eq("codigoUnidadOrganica", idUnidad));
    else if (idUnidad instanceof Integer)
      critUnidad.add(Restrictions.eq("id", idUnidad));
    else
      throw new InsideServiceStoreException(
          "Proceso incompleto. No se pudo obtener unidad orgánica no existe en BBDD");
    unidad = (UnidadOrganica) critUnidad.uniqueResult();

    if (unidad == null)
      throw new InsideServiceStoreException(
          "Proceso incompleto. La unidad orgánica no existe en BBDD");

    return unidad;
  }

  public InsideRol getInsideRolEntity(Integer idRol, Session session)
      throws InsideServiceStoreException {
    InsideRol rolEntity;
    // Recuperamos la unidad orgánica
    Criteria critRol = session.createCriteria(InsideRol.class);
    critRol.add(Restrictions.eq("id", idRol));
    rolEntity = (InsideRol) critRol.uniqueResult();

    return rolEntity;
  }

  public NumeroProcedimiento getNumeroProcesoEntity(Object numeroProc, Session session)
      throws InsideServiceStoreException {
    NumeroProcedimiento numeroProcedimiento;
    Criteria critUnidad = session.createCriteria(NumeroProcedimiento.class);

    if (numeroProc instanceof String)
      critUnidad.add(Restrictions.eq("numProcedimiento", numeroProc));
    else
      critUnidad.add(Restrictions.eq("id", numeroProc));

    numeroProcedimiento = (NumeroProcedimiento) critUnidad.uniqueResult();

    if (numeroProcedimiento == null)
      throw new InsideServiceStoreException(
          "Proceso incompleto. El número de procedimiento no existe en BBDD");

    return numeroProcedimiento;
  }

  private void desactivarDir3Aplicacion(Set<UnidadWsAplicacion> listaUnidades) {
    for (UnidadWsAplicacion unidad : listaUnidades) {
      unidad.setActivo(false);
    }
  }

  private void desactivarDir3Usuario(Set<UnidadUsuario> listaUnidades) {
    for (UnidadUsuario unidad : listaUnidades) {
      unidad.setActivo(false);
    }
  }

  @Override
  public List<ObjetoInsideUnidad> getUnidadesAplicacion(String idAplicacion, boolean todas)
      throws InsideServiceStoreException {
    logger.debug("Inicio getUnidadesAplicacion");
    Session session = sessionFactory.openSession();
    try {
      if (StringUtils.isNotEmpty(idAplicacion))
        return getUnidadesAplicacion(idAplicacion, todas, session);
      else
        return getTodasUnidadesAplicacion(session);
    } finally {
      session.close();
    }
  }

  private List<ObjetoInsideUnidad> getTodasUnidadesAplicacion(Session session)
      throws InsideServiceStoreException {
    List<ObjetoInsideUnidad> retorno = new ArrayList<ObjetoInsideUnidad>();
    List<ObjetoInsideAplicacion> aplicaciones = getAplicaciones();
    for (ObjetoInsideAplicacion aplicacion : aplicaciones) {
      InsideWsAplicacion aplicacionEntity =
          getAplicacionEntity(aplicacion.getAplicacion(), session);
      for (UnidadWsAplicacion unidadAplicacion : aplicacionEntity.getListaUnidades()) {
        getUnidadProcedimiento(session, retorno, unidadAplicacion);
      }
    }
    return retorno;
  }

  private List<ObjetoInsideUnidad> getUnidadesAplicacion(String idAplicacion, boolean todas,
      Session session) throws InsideServiceStoreException {
    List<ObjetoInsideUnidad> retorno = new ArrayList<ObjetoInsideUnidad>();

    // Recuperamos la aplicacion de Inside
    InsideWsAplicacion aplicacion = getAplicacionEntity(idAplicacion, session);

    if (aplicacion != null) {
      if (todas) {
        for (UnidadWsAplicacion unidad : aplicacion.getListaUnidades()) {
          getUnidadProcedimiento(session, retorno, unidad);
        }
      } else {
        UnidadWsAplicacion unidad = aplicacion.getUnidadActiva();
        getUnidadProcedimiento(session, retorno, unidad);
      }
    } else {
      throw new InsideServiceStoreException("No existe la aplicación en BBDD");
    }
    return retorno;
  }

  public void getUnidadProcedimiento(Session session, List<ObjetoInsideUnidad> retorno,
      UnidadWsAplicacion unidad) throws InsideServiceStoreException {
    if (unidad != null) {
      String numeroProcedimiento = unidad.getIdProcedimiento() != null
          ? getNumeroProcesoEntity(unidad.getIdProcedimiento(), session).getNumProcedimiento()
          : null;
      retorno.add(InsideServiceStoreHibernateConverterUnidad.toInside(
          getUnidadOrganicaEntity(session, unidad.getIdUnidad()), numeroProcedimiento,
          unidad.isActivo()));
    }
  }

  public UnidadOrganica getUnidadOrganicaEntity(Session session, Integer idUnidad) {
    Criteria criteria = session.createCriteria(UnidadOrganica.class);
    criteria.add(Restrictions.eq("id", idUnidad));
    return (UnidadOrganica) criteria.uniqueResult();
  }

  @Override
  public List<ObjetoInsideUnidad> getUnidadesUsuario(String idUsuario, boolean todas)
      throws InsideServiceStoreException {
    logger.debug("Inicio getUnidadesUsuario");
    Session session = sessionFactory.openSession();
    try {
      if (StringUtils.isNotEmpty(idUsuario))
        return getUnidadesUsuario(idUsuario, todas, session);
      else
        return getTodasUnidadesUsuario(session);
    } finally {
      session.close();
    }
  }

  private List<ObjetoInsideUnidad> getTodasUnidadesUsuario(Session session)
      throws InsideServiceStoreException {
    List<ObjetoInsideUnidad> retorno = new ArrayList<ObjetoInsideUnidad>();
    List<ObjetoInsideUsuario> usuarios = getUsuarios();
    for (ObjetoInsideUsuario usuario : usuarios) {
      UsuarioInside usuarioEntity = getUsuarioEntity(usuario.getNif(), session);
      for (UnidadUsuario unidadUsuario : usuarioEntity.getListaUnidades()) {
        getUnidadProcedimiento(session, retorno, unidadUsuario);
      }
    }
    return retorno;
  }

  private List<ObjetoInsideUnidad> getUnidadesUsuario(String idUsuario, boolean todas,
      Session session) throws InsideServiceStoreException {
    List<ObjetoInsideUnidad> retorno = new ArrayList<ObjetoInsideUnidad>();

    // Recuperamos el usuario de Inside
    UsuarioInside usuario = getUsuarioEntity(idUsuario, session);

    if (usuario != null) {
      if (todas) {
        for (UnidadUsuario unidad : usuario.getListaUnidades()) {
          getUnidadProcedimiento(session, retorno, unidad);
        }
      } else {
        UnidadUsuario unidad = usuario.getUnidadActiva();
        getUnidadProcedimiento(session, retorno, unidad);
      }
    } else {
      throw new InsideServiceStoreException("No existe el usuario en BBDD");
    }
    return retorno;
  }

  public void getUnidadProcedimiento(Session session, List<ObjetoInsideUnidad> retorno,
      UnidadUsuario unidad) throws InsideServiceStoreException {
    if (unidad != null) {
      String numeroProcedimiento = unidad.getIdProcedimiento() != null
          ? getNumeroProcesoEntity(unidad.getIdProcedimiento(), session).getNumProcedimiento()
          : null;
      retorno.add(InsideServiceStoreHibernateConverterUnidad.toInside(
          getUnidadOrganicaEntity(session, unidad.getUnidad().getId()), numeroProcedimiento,
          unidad.isActivo()));
    }
  }

  public Integer extraerUnidadUsuario(Object usuario) {
    Integer unidad = null;
    if (usuario instanceof UsuarioInside) {
      unidad = ((UsuarioInside) usuario).getUnidadActiva().getUnidad().getId();
    } else if (usuario instanceof InsideWsAplicacion) {
      unidad = ((InsideWsAplicacion) usuario).getUnidadActiva().getIdUnidad();
    }
    return unidad;
  }

  @Override
  public boolean comprobarUnidadesOrganicasActivasUsuario(Object usuario)
      throws InsideServiceStoreException {
    logger.debug("Inicio comprobarUnidadesOrganicasActivasUsuario");
    boolean validar = false;
    Session session = sessionFactory.openSession();
    try {
      if (usuario instanceof ObjetoInsideUsuario) {
        UsuarioInside usuEntity =
            getUsuarioEntity(((ObjetoInsideUsuario) usuario).getNif(), session);
        if (usuEntity.contieneUnidadActiva())
          validar = true;
      } else if (usuario instanceof ObjetoInsideAplicacion) {
        InsideWsAplicacion usuEntity =
            getAplicacionEntity(((ObjetoInsideAplicacion) usuario).getAplicacion(), session);
        if (usuEntity.contieneUnidadActiva())
          validar = true;
      }
    } finally {
      session.close();
    }
    return validar;
  }

  public List<ExpedienteInsideIndice> indicesAsociadosExpediente(String identificadorExpediente,
      Session session) {
    Criteria crit = session.createCriteria(ExpedienteInsideIndice.class);
    crit.add(Restrictions.eq("identificadorExpediente", identificadorExpediente));
    return crit.list();
  }

  @Override
  public List<ObjetoInsideExpedienteUnidad> getViewsExpedient(String identificadorExpediente)
      throws InsideServiceStoreException {
    logger.debug("Inicio getVistasExpediente");
    List<ObjetoInsideExpedienteUnidad> devolver = null;
    Session session = sessionFactory.openSession();
    try {
      List<ExpedienteInsideIndice> expedienteIndice =
          indicesAsociadosExpediente(identificadorExpediente, session);
      Set<String> listaExpediente = new TreeSet();
      if (!CollectionUtils.isEmpty(expedienteIndice)) {

        for (ExpedienteInsideIndice indice : expedienteIndice) {
          Criteria crit = session.createCriteria(ExpedienteInside.class)
              .createAlias("expedienteInsideIndice", "indice");
          crit.add(Restrictions.eq("indice.id", indice.getExpedienteInsideIndice().getId()));
          ExpedienteInside exp = (ExpedienteInside) crit.uniqueResult();
          if (exp != null)
            listaExpediente.add(exp.getIdentificador());
        }

        Criteria crit2 = session.createCriteria(ExpedienteUnidad.class);
        crit2.add(Restrictions.in(IDENTIFICADOR, listaExpediente));
        crit2.add(Restrictions.isNotNull(FECHA_VERSION));
        crit2.addOrder(Order.desc(FECHA_VERSION));
        List<ExpedienteUnidad> listaExpedienteUnidad = crit2.list();
        devolver = obtenerExpedientesUnidad(listaExpedienteUnidad);
      }
    } finally {
      session.close();
    }
    return devolver;
  }

  @Override
  public ObjetoInsideAplicacion actualizarCredencialesEeetuilApp(String app,
      ObjetoCredencialEeutil credential) throws InsideServiceStoreException {
    logger.debug("Inicio altaAplicacion");
    Session session = sessionFactory.openSession();
    try {
      return persister.actualizarCredencialesEeetuilApp(app, credential, session);
    } finally {
      session.close();
    }
  }

  @Override
  public ObjetoInsideAplicacion getAplicacionBySerialNumber(String serialNumberCertificado)
      throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    ObjetoInsideAplicacion aplicacionInside = null;

    try {
      Criteria crit = session.createCriteria(InsideWsAplicacion.class);
      crit.add(Restrictions.eq("serialNumberCertificado", serialNumberCertificado));
      InsideWsAplicacion app = (InsideWsAplicacion) crit.uniqueResult();

      if (app != null) {
        aplicacionInside = InsideServiceStoreHibernateConverterAplicacion.toInside(app);
      } else {
        throw new InsideServiceStoreException(
            "Se ha obtenido más de un resultado para la aplicación " + serialNumberCertificado);
      }
    } finally {
      session.close();
    }

    return aplicacionInside;
  }

  @Override
  public ObjetoInsideUnidadAplicacionEeutil getApplicationEeutilByUser(ObjetoInsideUsuario user)
      throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    ObjetoInsideUnidadAplicacionEeutil retorno = null;
    UnidadAplicacionEeutil unidadApp = null;
    try {
      List<ObjetoInsideUnidad> unidadesUsuario = getUnidadesUsuario(user.getNif(), false, session);
      if (CollectionUtils.isNotEmpty(unidadesUsuario)) {
        ObjetoInsideUnidad unidad = unidadesUsuario.get(0);
        UnidadOrganica unidadEntity = getUnidadEntity(unidad.getCodigo(), session);
        Criteria crit = session.createCriteria(UnidadAplicacionEeutil.class);
        crit.add(Restrictions.eq(ID_UNIDAD, unidadEntity.getId()));
        unidadApp = (UnidadAplicacionEeutil) crit.uniqueResult();
      }

      retorno =
          InsideServiceStoreHibernateConverterUnidadAplicacionEeutil.toInside(unidadApp, null);
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public ObjetoInsideUnidadAplicacionEeutil crearActualizarUnidadAplicacionEeutil(
      String codigoUnidad, String aplicacion, String password) throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    ObjetoInsideUnidadAplicacionEeutil objetoUnidadAplicacionEeutil = null;
    UnidadOrganica unidad = getUnidadEntity(codigoUnidad, session);

    UnidadAplicacionEeutil unidadAplicacionEeutil = getUnidadAplicacionEeutilEntity(codigoUnidad);

    // Sino existe creo uno nuevo
    if (unidadAplicacionEeutil == null)
      unidadAplicacionEeutil = new UnidadAplicacionEeutil();

    unidadAplicacionEeutil.setIdUnidad(unidad.getId());
    unidadAplicacionEeutil.setIdAplicacion(aplicacion);
    unidadAplicacionEeutil.setPassword(password);

    try {
      objetoUnidadAplicacionEeutil =
          persister.saveUnidadAplicacionEeutil(unidadAplicacionEeutil, session);
      objetoUnidadAplicacionEeutil.setCodigoUnidadOrganica(unidad.getCodigoUnidadOrganica());
      return objetoUnidadAplicacionEeutil;
    } finally {
      session.close();
    }
  }

  @Override
  public ObjetoInsideUnidadAplicacionEeutil getUnidadAplicacionEeutil(String codigoUnidad)
      throws InsideServiceStoreException {
    ObjetoInsideUnidadAplicacionEeutil objetoInsideUnidadAplicacionEeutil = null;
    List<UnidadAplicacionEeutil> listaUnidadAplicacionEeutil =
        getListUnidadAplicacionEeutilEntity(codigoUnidad);
    Session session = sessionFactory.openSession();
    try {
      if (CollectionUtils.isNotEmpty(listaUnidadAplicacionEeutil)) {
        UnidadOrganica unidad =
            getUnidadEntity(listaUnidadAplicacionEeutil.get(0).getIdUnidad(), session);
        objetoInsideUnidadAplicacionEeutil =
            InsideServiceStoreHibernateConverterUnidadAplicacionEeutil
                .toInside(listaUnidadAplicacionEeutil.get(0), unidad.getCodigoUnidadOrganica());
      }

      return objetoInsideUnidadAplicacionEeutil;
    } finally {
      session.close();
    }

  }

  @Override
  public List<ObjetoInsideUnidadAplicacionEeutil> getListUnidadAplicacionEeutil()
      throws InsideServiceStoreException {
    List<UnidadAplicacionEeutil> listUnidadAplicacionEeutil =
        getListUnidadAplicacionEeutilEntity(null);
    List<ObjetoInsideUnidadAplicacionEeutil> listObjetoInsideUnidadAplicacionEeutil =
        new ArrayList<ObjetoInsideUnidadAplicacionEeutil>();
    Session session = sessionFactory.openSession();
    try {
      for (UnidadAplicacionEeutil unidadAplicacionEeutil : listUnidadAplicacionEeutil) {
        UnidadOrganica unidad = getUnidadEntity(unidadAplicacionEeutil.getIdUnidad(), session);
        listObjetoInsideUnidadAplicacionEeutil
            .add(InsideServiceStoreHibernateConverterUnidadAplicacionEeutil
                .toInside(unidadAplicacionEeutil, unidad.getCodigoUnidadOrganica()));
      }

      return listObjetoInsideUnidadAplicacionEeutil;
    } finally {
      session.close();
    }
  }

  public UnidadAplicacionEeutil getUnidadAplicacionEeutilEntity(String codigoUnidad)
      throws InsideServiceStoreException {
    UnidadAplicacionEeutil unidadAplicacionEeutil = null;
    List<UnidadAplicacionEeutil> listUnidadAplicacionEeutil =
        getListUnidadAplicacionEeutilEntity(codigoUnidad);
    if (CollectionUtils.isNotEmpty(listUnidadAplicacionEeutil))
      unidadAplicacionEeutil = listUnidadAplicacionEeutil.get(0);

    return unidadAplicacionEeutil;
  }

  public List<UnidadAplicacionEeutil> getListUnidadAplicacionEeutilEntity(String codigoUnidad)
      throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    try {
      Criteria criteria = session.createCriteria(UnidadAplicacionEeutil.class);
      if (codigoUnidad != null && !codigoUnidad.isEmpty()) {
        UnidadOrganica unidad = getUnidadEntity(codigoUnidad, session);
        if (unidad != null) {
          criteria.add(Restrictions.eq(ID_UNIDAD, unidad.getId()));
        }
      }
      return criteria.list();
    } finally {
      session.close();
    }
  }

  @Override
  public ObjetoInsideAplicacion crearActualizarSerialNumberCertificado(String idAplicacion,
      String serialNumberCertificado) throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    try {
      InsideWsAplicacion data = getAplicacionEntity(idAplicacion, session);
      data.setSerialNumberCertificado(serialNumberCertificado);
      return persister.saveApp(data, session);
    } finally {
      session.close();
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<String> getNumeroProcedimiento() throws InsideServiceStoreException {
    logger.debug("Inicio getNumeroProcedimiento");
    Session session = sessionFactory.openSession();
    List<String> retorno = new ArrayList<String>();
    try {
      Criteria crit = session.createCriteria(NumeroProcedimiento.class);
      List<NumeroProcedimiento> datos = crit.list();
      if (CollectionUtils.isNotEmpty(datos)) {
        for (NumeroProcedimiento procedimiento : datos) {
          retorno.add(procedimiento.getNumProcedimiento());
        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public String altaNumeroProcedimiento(String numeroProcedimiento)
      throws InsideServiceStoreException {
    logger.debug("Inicio getNumeroProcedimiento");
    Session session = sessionFactory.openSession();
    try {
      List<NumeroProcedimiento> lista = getNumeroProcedimientoEntity(numeroProcedimiento, session);
      if (lista != null && !lista.isEmpty()) {
        throw new InsideServiceStoreException(
            "Ya existe el número de procedimiento: " + numeroProcedimiento);
      }
      NumeroProcedimiento numProcedimiento = new NumeroProcedimiento();
      numProcedimiento.setNumProcedimiento(numeroProcedimiento);
      return persister.saveNumeroProcedimiento(numProcedimiento, session);
    } finally {
      session.close();
    }
  }

  public List<NumeroProcedimiento> getNumeroProcedimientoEntity(String numeroProcedimiento,
      Session session) throws InsideServiceStoreException {
    Criteria criteria = session.createCriteria(NumeroProcedimiento.class);
    criteria.add(Restrictions.eq("numProcedimiento", numeroProcedimiento.trim()));
    List<NumeroProcedimiento> numeroProcedimientoEnTabla = criteria.list();
    return numeroProcedimientoEnTabla;
  }

  @Override
  public ObjetoEstructuraCarpetaInside getEstructuraCarpeta(ObjetoInsideUsuario user)
      throws InsideServiceStoreException {
    logger.debug("Inicio getEstructuraCarpeta");
    ObjetoEstructuraCarpetaInside estructuraCarpeta = null;
    Session session = sessionFactory.openSession();
    UsuarioInside usuario = null;
    boolean obtenerEstructuraGenerica = true;

    try {
      usuario = getUsuarioEntity(user.getNif(), session);
    } catch (InsideServiceStoreException e) {
      logger.debug("Usuario no registrado", e);
    }

    try {
      Criteria crit = session.createCriteria(EstructuraCarpetaInside.class);
      if (usuario != null) {
        UnidadUsuario unidadUsuario = usuario.getUnidadActiva();
        crit.add(Restrictions.eq("unidad.id", unidadUsuario.getUnidad().getId()));

        if (unidadUsuario.getIdProcedimiento() != null)
          crit.add(Restrictions.eq("numeroProcedimiento.id", unidadUsuario.getIdProcedimiento()));
        else
          crit.add(Restrictions.isNull("numeroProcedimiento.id"));

        List<EstructuraCarpetaInside> datosEstructura = crit.list();
        if (CollectionUtils.isNotEmpty(datosEstructura)) {
          estructuraCarpeta = InsideServiceStoreHibernateConverterEstructuraCarpeta
              .toInside(datosEstructura.get(0));
          obtenerEstructuraGenerica = false;
        }
      }

      if (obtenerEstructuraGenerica) {
        crit = session.createCriteria(EstructuraCarpetaInside.class);
        crit.add(Restrictions.eq("identificadorEstructura", "estructuraGenerica"));
        EstructuraCarpetaInside estructuraEntity = (EstructuraCarpetaInside) crit.uniqueResult();
        estructuraCarpeta =
            InsideServiceStoreHibernateConverterEstructuraCarpeta.toInside(estructuraEntity);
      }
    } finally {
      session.close();
    }
    return estructuraCarpeta;
  }

  @Override
  public List<ObjetoEstructuraCarpetaInside> listaEstructuraCarpeta(String identificadorEstructura)
      throws InsideServiceStoreException {
    logger.debug("Inicio listaEstructuraCarpeta");
    List<ObjetoEstructuraCarpetaInside> listaEstructuraCarpeta =
        new ArrayList<ObjetoEstructuraCarpetaInside>();
    Session session = sessionFactory.openSession();
    try {
      List<EstructuraCarpetaInside> datos =
          getEstructuraCarpetaEntity(identificadorEstructura, session);
      for (EstructuraCarpetaInside es : datos) {
        listaEstructuraCarpeta
            .add(InsideServiceStoreHibernateConverterEstructuraCarpeta.toInside(es));
      }
    } finally {
      session.close();
    }
    return listaEstructuraCarpeta;
  }

  public List<EstructuraCarpetaInside> getEstructuraCarpetaEntity(String identificadorEstructura,
      Session session) {
    Criteria crit = session.createCriteria(EstructuraCarpetaInside.class);
    if (StringUtils.isNotBlank(identificadorEstructura)) {
      crit.add(Restrictions.eq("identificadorEstructura", identificadorEstructura));
    }
    List<EstructuraCarpetaInside> datos = crit.list();
    return datos;
  }

  @Override
  public ObjetoEstructuraCarpetaInside altaEstructuraCarpeta(
      ObjetoEstructuraCarpetaInside objetoEstructuraCarpetaInside)
      throws InsideServiceStoreException {
    logger.debug("Inicio altaEstructuraCarpeta");
    EstructuraCarpetaInside estructuraCarpeta = new EstructuraCarpetaInside();
    Session session = sessionFactory.openSession();
    try {
      estructuraCarpeta = InsideServiceStoreHibernateConverterEstructuraCarpeta
          .toEntity(objetoEstructuraCarpetaInside);
      if (StringUtils.isNotBlank(objetoEstructuraCarpetaInside.getCodigoUnidadOrganica())) {
        estructuraCarpeta.setUnidad(persister
            .getUnidadEntity(objetoEstructuraCarpetaInside.getCodigoUnidadOrganica(), session));
      }
      if (StringUtils.isNotBlank(objetoEstructuraCarpetaInside.getNumeroProcedimiento())) {
        estructuraCarpeta.setNumeroProcedimiento(persister.getNumeroProcesoEntity(
            objetoEstructuraCarpetaInside.getNumeroProcedimiento(), session));
      }
      return InsideServiceStoreHibernateConverterEstructuraCarpeta
          .toInside(persister.saveEstructuraCarpeta(estructuraCarpeta, session));
    } finally {
      session.close();
    }
  }

  @Override
  public void deleteEstructuraCarpeta(String identificadorEstructura)
      throws InsideServiceStoreException {
    logger.debug("Inicio altaEstructuraCarpeta");
    Session session = sessionFactory.openSession();
    try {
      List<EstructuraCarpetaInside> datos =
          getEstructuraCarpetaEntity(identificadorEstructura, session);
      persister.deleteEstructuraCarpeta(datos.get(0), session);
    } finally {
      session.close();
    }
  }

  @Override
  public void updateDocumentoUnidad(ObjetoDocumentoInside object, String usuario, boolean online)
      throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    try {
      Object objectUnidad = asociarObjetoUnidad(object, usuario, online);
      persister.saveObjetoUnidad(objectUnidad, session);
    } finally {
      session.close();
    }
  }

  @Override
  public List<ObjetoElastic> getObjetosElastic(String nif) throws InsideServiceStoreException {

    return getExpedientesByOrgano(nif);

  }

  private List<ObjetoElastic> getExpedientesByOrgano(String organo)
      throws InsideServiceStoreException {

    Session session = sessionFactory.openSession();
    List<ObjetoElastic> objetosElastic = new ArrayList<ObjetoElastic>();
    try {
      objetosElastic = getExpedientesInsideOrgano(organo, session);
      objetosElastic.addAll(getDocumentosInsideOrgano(organo, session));
    } finally {
      session.close();
    }
    return objetosElastic;
  }

  private List<ObjetoElastic> getExpedientesInsideOrgano(String organo, Session session) {
    List<ExpedienteInsideOrgano> datos;
    List<ObjetoElastic> objetosElastic = new ArrayList<ObjetoElastic>();

    Criteria crit = session.createCriteria(ExpedienteInsideOrgano.class);
    crit.add(Restrictions.eq("organo", organo));
    crit.addOrder(Order.desc("expedienteInside.id"));
    datos = crit.list();

    for (ExpedienteInsideOrgano expedienteInsideOrgano : datos) {
      ObjetoElastic objetoElastic = new ObjetoElastic();
      objetoElastic.setTipoObjeto("Expediente");
      objetoElastic
          .setIdentificador(expedienteInsideOrgano.getExpedienteInside().getIdentificador());
      objetoElastic.setMetadatos(new HashMap<String, String>());
      objetoElastic.getMetadatos().put("VersionNTI",
          expedienteInsideOrgano.getExpedienteInside().getVersionNti());
      objetoElastic.getMetadatos().put("Identificador",
          expedienteInsideOrgano.getExpedienteInside().getIdentificador());
      objetoElastic.getMetadatos().put("Organo", expedienteInsideOrgano.getOrgano());
      objetoElastic.getMetadatos().put("FechaAperturaExpediente", new SimpleDateFormat("dd/MM/yyyy")
          .format(expedienteInsideOrgano.getExpedienteInside().getFechaAperturaExpediente()));
      objetoElastic.getMetadatos().put("Clasificacion",
          expedienteInsideOrgano.getExpedienteInside().getClasificacion());
      objetoElastic.getMetadatos().put("Estado",
          expedienteInsideOrgano.getExpedienteInside().getEstado());
      objetosElastic.add(objetoElastic);

      objetoElastic.setMetadatosAdicionales(new HashMap<String, String>());
      for (ExpedienteInsideMetadatosAdicionales metadatosAdicional : expedienteInsideOrgano
          .getExpedienteInside().getExpedienteInsideMetadatosAdicionaleses()) {
        objetoElastic.getMetadatosAdicionales().put(metadatosAdicional.getNombre(),
            metadatosAdicional.getValor());
      }

    }

    return objetosElastic;
  }

  @SuppressWarnings("unchecked")
  private List<ObjetoElastic> getDocumentosInsideOrgano(String organo, Session session) {
    List<DocumentoInsideOrgano> datos;
    List<String> data = new ArrayList<>();
    List<ObjetoElastic> objetosElastic = new ArrayList<ObjetoElastic>();
    Criteria crit = session.createCriteria(DocumentoInsideOrgano.class);
    crit.add(Restrictions.eq("organo", organo));
    crit.addOrder(Order.desc("documentoInside.id"));
    datos = crit.list();

    for (DocumentoInsideOrgano documentoInsideOrgano : datos) {
      if (!data.contains(documentoInsideOrgano.getDocumentoInside().getIdentificador())) {
        ObjetoElastic objetoElastic = new ObjetoElastic();
        objetoElastic.setTipoObjeto("Documento");
        objetoElastic
            .setIdentificador(documentoInsideOrgano.getDocumentoInside().getIdentificador());
        objetoElastic.setMetadatos(new HashMap<String, String>());
        objetoElastic.getMetadatos().put("VersionNTI",
            documentoInsideOrgano.getDocumentoInside().getVersionNti());
        objetoElastic.getMetadatos().put("Identificador",
            documentoInsideOrgano.getDocumentoInside().getIdentificador());
        objetoElastic.getMetadatos().put("Organo", documentoInsideOrgano.getOrgano());
        objetoElastic.getMetadatos().put("FechaCaptura", new SimpleDateFormat("dd/MM/yyyy")
            .format(documentoInsideOrgano.getDocumentoInside().getFechaCaptura()));
        objetoElastic.getMetadatos().put("OrigenCiudadanoAdministracion",
            documentoInsideOrgano.getDocumentoInside().getOrigenCiudadanoAdministracion()
                ? "Ciudadano"
                : "Admon");
        objetoElastic.getMetadatos().put("EstadoElaboracion",
            documentoInsideOrgano.getDocumentoInside().getEstadoElaboracion());

        objetoElastic.setMetadatosAdicionales(new HashMap<String, String>());
        for (DocumentoInsideMetadatosAdicionales metadatosAdicional : documentoInsideOrgano
            .getDocumentoInside().getDocumentoInsideMetadatosAdicionaleses()) {
          objetoElastic.getMetadatosAdicionales().put(metadatosAdicional.getNombre(),
              metadatosAdicional.getValor());
        }

        objetosElastic.add(objetoElastic);
        data.add(documentoInsideOrgano.getDocumentoInside().getIdentificador());
      }
    }

    return objetosElastic;
  }

  @Override
  public int consultAndIncreaseDefaultIdByDir3(String unidadOrganica, String elemento)
      throws InsideServiceStoreException {
    Session session = sessionFactory.openSession();
    int numeroId;
    try {
      Criteria crit = session.createCriteria(GeneradorIdInside.class);
      crit.add(Restrictions.eq("elemento", elemento));
      UnidadOrganica unidad = getUnidadEntity(unidadOrganica, session);
      crit.add(Restrictions.eq("idUnidad", unidad.getId()));
      GeneradorIdInside resultGeneradorId = (GeneradorIdInside) crit.uniqueResult();
      if (resultGeneradorId != null) {
        numeroId = resultGeneradorId.getNumeroId() + 1;
        resultGeneradorId.setNumeroId(numeroId);
      } else {
        numeroId = 0;
        resultGeneradorId = createDefaultIdByDir3(unidad.getId(), elemento, numeroId);
      }
      persister.saveDefaultId(resultGeneradorId, session);
    } finally {
      session.close();
    }
    return numeroId;
  }

  private GeneradorIdInside createDefaultIdByDir3(int idUnidad, String elemento, int numeroId) {
    GeneradorIdInside idInside = new GeneradorIdInside();
    idInside.setElemento(elemento);
    idInside.setIdUnidad(idUnidad);
    idInside.setNumeroId(numeroId);
    return idInside;
  }

  @Override
  public void saveAuditoriaFirmaServidor(
      ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor) {
    logger.debug("Inicio saveAuditoriaFirmaServidor");
    Session session = sessionFactory.openSession();
    try {
      persister
          .saveAuditoriaFirmaServidor(InsideServiceStoreHibernateConverterAuditoriaFirmaServidor
              .toEntity(objetoAuditoriaFirmaServidor), session);
    } catch (InsideServiceStoreException e) {
      logger.error("Error en saveAuditoriaFirmaServidor al almacenar: "
          + objetoAuditoriaFirmaServidor.toString(), e);
    } finally {
      session.close();
    }
  }

  @Override
  public List<ObjetoInsideRol> getInsideRoles() throws InsideServiceStoreException {

    List<ObjetoInsideRol> respuesta = new ArrayList<ObjetoInsideRol>();
    Session session = sessionFactory.openSession();
    try {

      Criteria crit = session.createCriteria(InsideRol.class);

      List<InsideRol> datosRecogidos = crit.list();

      if (datosRecogidos != null) {
        for (InsideRol insideRol : datosRecogidos) {
          respuesta.add(converterInsideRol.toInside(insideRol));
        }
      }

    } finally {
      session.close();
    }
    return respuesta;
  }

  public InsideServiceStoreHibernateConverterInsideRol getConverterInsideRol() {
    return converterInsideRol;
  }

  public void setConverterInsideRol(
      InsideServiceStoreHibernateConverterInsideRol converterInsideRol) {
    this.converterInsideRol = converterInsideRol;
  }

  // private boolean contieneUnidadOrganica(Object[] listaUnidades,
  // UnidadOrganica unidad)
  // {
  // boolean contiene = false;
  //
  // for (int i = 0; i < listaUnidades.length; i++)
  // {
  // UnidadUsuario aux = (UnidadUsuario) listaUnidades[i];
  // if(aux.getIdUnidad().intValue() == unidad.getId())
  // {
  // contiene=true;
  // return contiene;
  // }
  //
  // }
  //
  // return contiene;
  // }

  private UnidadUsuario contieneUnidadOrganica(Object[] listaUnidades, UnidadOrganica unidad) {
    for (int i = 0; i < listaUnidades.length; i++) {
      UnidadUsuario aux = (UnidadUsuario) listaUnidades[i];
      if (aux.getUnidad().getId().intValue() == unidad.getId()) {
        return aux;
      }

    }

    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<ObjetoInsideUsuario> getUsuariosUnidadOrganica(ObjetoInsideUsuario usuarioEnSesion,
      Locale locale) throws InsideServiceStoreException {
    logger.debug("Inicio getUsuariosUnidadOrganica");
    Session session = sessionFactory.openSession();

    String unidadOrganicaActivaConDescripcionDelUsuarioLogado =
        usuarioEnSesion.getUnidadOrganicaActiva();
    String unidadOrganicaActivaDelUsuarioLogadoCodigo =
        unidadOrganicaActivaConDescripcionDelUsuarioLogado.split("-")[0].trim();
    String unidadOrganicaActivaDelUsuarioLogadoDescripcion =
        unidadOrganicaActivaConDescripcionDelUsuarioLogado.split("-")[1].trim();

    UnidadOrganica unidad = getUnidadEntity(unidadOrganicaActivaDelUsuarioLogadoCodigo, session);

    List<ObjetoInsideUsuario> retorno = new ArrayList<ObjetoInsideUsuario>();
    try {
      Criteria crit = session.createCriteria(UsuarioInside.class);
      List<UsuarioInside> datos = crit.list();
      if (CollectionUtils.isNotEmpty(datos)) {
        for (UsuarioInside user : datos) {
          Object[] listaUnidadesDelUsuario = user.getListaUnidades().toArray();

          UnidadUsuario unidadBuscada = contieneUnidadOrganica(listaUnidadesDelUsuario, unidad);

          if (unidadBuscada != null
              && !usuarioEnSesion.getNif().trim().equalsIgnoreCase(user.getNif().trim())) {
            ObjetoInsideUsuario obj = InsideServiceStoreHibernateConverterUsuario.toInside(user);
            obj.setUnidadOrganicaActiva(unidadOrganicaActivaDelUsuarioLogadoCodigo);
            obj.setNombreUnidadOrganicaActiva(unidadOrganicaActivaDelUsuarioLogadoDescripcion);

            if (unidadBuscada.getIdProcedimiento() != null) {
              NumeroProcedimiento numProc =
                  getNumeroProcesoEntity(unidadBuscada.getIdProcedimiento(), session);
              obj.setNumeroProcedimiento(numProc.getNumProcedimiento());
              obj.setObjNumeroProcedimiento(converterNumeroProcedimiento.toInside(numProc));
            } else {
              obj.setObjNumeroProcedimiento(new ObjetoNumeroProcedimiento());
            }

            InsideRol insideRol = getInsideRolEntity(unidadBuscada.getIdRol(), session);
            obj.setRol(InsideServiceStoreHibernateConverterInsideRol.toInside(insideRol));
            obj.getRol().setDescripcionLargaTraducida(
                messageSource.getMessage(insideRol.getDescripcionLarga(), null, locale));

            retorno.add(obj);
          }

        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public List<ObjetoNumeroProcedimiento> getNumeroProcedimientoList()
      throws InsideServiceStoreException {
    logger.debug("Inicio getNumeroProcedimiento");
    Session session = sessionFactory.openSession();
    List<ObjetoNumeroProcedimiento> retorno = new ArrayList<ObjetoNumeroProcedimiento>();
    try {
      Criteria crit = session.createCriteria(NumeroProcedimiento.class);
      List<NumeroProcedimiento> datos = crit.list();
      if (CollectionUtils.isNotEmpty(datos)) {
        for (NumeroProcedimiento procedimiento : datos) {
          retorno.add(converterNumeroProcedimiento.toInside(procedimiento));
        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public List<ObjetoUnidadOrganica> getUnidadesOrganicasUsuariosInside(String texto)
      throws InsideServiceStoreException {
    logger.debug("Inicio getUnidadesOrganicasUsuariosInside");
    Session session = sessionFactory.openSession();
    List<ObjetoUnidadOrganica> retorno = new ArrayList<ObjetoUnidadOrganica>();
    try {
      Criteria crit = session.createCriteria(UnidadUsuario.class);
      List<Integer> results = session.createCriteria(UnidadUsuario.class).setProjection(
          Projections.projectionList().add(Projections.distinct(Projections.property("unidad.id"))))
          .list();
      if (CollectionUtils.isNotEmpty(results)) {
        List<UnidadOrganica> unidades = session.createCriteria(UnidadOrganica.class)
            .add(Restrictions.and(Restrictions.in("id", results),
                Restrictions.or(Restrictions.like("nombreUnidadOrganica", "%" + texto + "%"),
                    Restrictions.like("codigoUnidadOrganica", "%" + texto + "%"))))
            .list();
        for (UnidadOrganica unidadUsuario : unidades) {
          ObjetoUnidadOrganica objetoUnidadOrganica = new ObjetoUnidadOrganica();
          objetoUnidadOrganica.setCodigoUnidadOrganica(unidadUsuario.getCodigoUnidadOrganica());
          objetoUnidadOrganica.setNombreUnidadOrganica(unidadUsuario.getNombreUnidadOrganica());
          retorno.add(objetoUnidadOrganica);
        }
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public boolean existeUsuarioInsideConDir3(String dir3) {
    logger.debug("Inicio getUnidadOrganica");
    Session session = sessionFactory.openSession();
    boolean retorno = false;
    try {
      List results = session.createQuery(
          "select a.id from UnidadUsuario a where a.unidad.codigoUnidadOrganica like :parametro")
          .setParameter("parametro", dir3).list();

      if (CollectionUtils.isNotEmpty(results)) {
        retorno = true;
      } else {
        retorno = false;
      }
    } finally {
      session.close();
    }
    return retorno;
  }

  public ObjetoInsideDocumentoUnidad getDocumentoUnidad(String identificadorDocumento)
      throws InsideServiceStoreException {

    ObjetoInsideDocumentoUnidad retorno = new ObjetoInsideDocumentoUnidad();
    Session session = sessionFactory.openSession();
    try {
      Criteria crit = session.createCriteria(DocumentoUnidad.class);
      crit.add(Restrictions.eq("identificador", identificadorDocumento));
      DocumentoUnidad datos = (DocumentoUnidad) crit.uniqueResult();
      retorno.setIdentificador(datos.getIdentificador());
      retorno.setIdUnidad(datos.getIdUnidad());
    } finally {
      session.close();
    }
    return retorno;
  }

  public ObjetoInsideExpedienteUnidad getExpedienteUnidad(String identificadorExpediente)
      throws InsideServiceStoreException {

    ObjetoInsideExpedienteUnidad retorno = new ObjetoInsideExpedienteUnidad();
    Session session = sessionFactory.openSession();
    try {
      Criteria crit = session.createCriteria(ExpedienteUnidad.class);
      crit.add(Restrictions.eq("identificador", identificadorExpediente));
      ExpedienteUnidad datos = (ExpedienteUnidad) crit.uniqueResult();
      retorno.setIdentificador(datos.getIdentificador());
      retorno.setIdUnidad(datos.getIdUnidad());
    } finally {
      session.close();
    }
    return retorno;
  }

  @Override
  public ObjetoInsideUnidad getUnidadOrganica(Object idUnidadOrganica)
      throws InsideServiceStoreException {
    UnidadOrganica unidad;
    // Recuperamos la unidad orgánica
    Session session = sessionFactory.openSession();
    try {
      Criteria critUnidad = session.createCriteria(UnidadOrganica.class);
      if (idUnidadOrganica instanceof String) {
        critUnidad.add(Restrictions.eq("codigoUnidadOrganica", idUnidadOrganica));
      } else if (idUnidadOrganica instanceof Integer) {
        critUnidad.add(Restrictions.eq("id", idUnidadOrganica));
      } else {
        throw new InsideServiceStoreException(
            "Proceso incompleto. No se pudo obtener unidad orgánica no existe en BBDD");
      }
      unidad = (UnidadOrganica) critUnidad.uniqueResult();

      if (unidad == null)
        throw new InsideServiceStoreException(
            "Proceso incompleto. La unidad orgánica no existe en BBDD");
    } finally {
      session.close();
    }

    return InsideServiceStoreHibernateConverterUnidad.toInside(unidad, null, true);
  }

}
