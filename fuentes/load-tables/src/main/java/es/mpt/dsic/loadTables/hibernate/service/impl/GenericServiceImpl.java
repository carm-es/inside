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

package es.mpt.dsic.loadTables.hibernate.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import es.mpt.dsic.inside.store.hibernate.entity.Entidad;
import es.mpt.dsic.loadTables.hibernate.dao.GenericDAO;
import es.mpt.dsic.loadTables.hibernate.service.GenericService;

public abstract class GenericServiceImpl<T extends Entidad> implements GenericService<T> {

  private static final Log log = LogFactory.getLog(GenericServiceImpl.class);

  @Autowired
  private GenericDAO<T> genericDao;

  @Autowired
  private SessionFactory sessionFactory;

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#getGenericDao ()
   */
  @Override
  public GenericDAO<T> getGenericDao() {
    return genericDao;
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#setGenericDao
   * (es.mpt.dsic.loadTables.hibernate.dao.GenericDAO)
   */
  @Override
  public void setGenericDao(GenericDAO<T> genericDao) {
    this.genericDao = genericDao;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#persist (java.lang.Class)
   */
  @Override
  public void persist(T transientInstance) {
    log.debug("Inicio persist");
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      getGenericDao().persist(transientInstance, session);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      log.error("Error persist:" + e.getMessage());
    } finally {
      session.close();
    }
    log.debug("Fin persist");
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#saveOrUpdate (java.lang.Class)
   */
  @Override
  public void saveOrUpdate(T instance) {
    log.debug("Inicio saveOrUpdate");
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      getGenericDao().saveOrUpdate(instance, session);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      log.error("Error saveOrUpdate:" + e.getMessage());
    } finally {
      session.close();
    }
    log.debug("Fin saveOrUpdate");
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService# saveOrUpdateList
   * (java.lang.Class)
   */
  @Override
  public void saveOrUpdateList(List<T> list) {
    log.debug("Inicio saveOrUpdate");
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      for (T data : list) {
        getGenericDao().saveOrUpdate(data, session);
      }
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      log.error("Error saveOrUpdate:" + e.getMessage());
    } finally {
      session.close();
    }
    log.debug("Fin saveOrUpdate");
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService# saveOrUpdateListFlush
   * (java.lang.Class)
   */
  @Override
  public void saveOrUpdateListFlush(List<T> list, int countFlush) {
    log.debug("Inicio saveOrUpdate");
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      int i = 1;
      for (T data : list) {
        try {
          getGenericDao().saveOrUpdate(data, session);
          if (++i % countFlush == 0) {
            session.flush();
            session.clear();
          }
        } catch (Exception e) {
          log.error("Error al procesar el registro: " + data.toString());
        }
      }
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      log.error("Error saveOrUpdate:" + e.getMessage());
    } finally {
      session.close();
    }
    log.debug("Fin saveOrUpdate");
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#delete (java.lang.Class)
   */
  @Override
  public void delete(T persistentInstance) {
    log.debug("Inicio delete");
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      getGenericDao().delete(persistentInstance, session);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      log.error("Error delete:" + e.getMessage());
    } finally {
      session.close();
    }
    log.debug("Fin delete");
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#merge( java.lang.Class)
   */
  @Override
  public T merge(T detachedInstance) {
    log.debug("Inicio merge");
    Session session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    T retorno = null;
    try {
      retorno = getGenericDao().merge(detachedInstance, session);
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      log.error("Error merge:" + e.getMessage());
    } finally {
      session.close();
    }
    log.debug("Fin merge");
    return retorno;
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#findById (java.lang.Integer,
   * java.lang.Class)
   */
  @Override
  public T findById(java.lang.Integer id, T findInstance) {
    Session session = sessionFactory.openSession();
    try {
      log.debug("Inicio findById");
      return getGenericDao().findById(id, findInstance, session);
    } finally {
      session.close();
    }
  }

  @Override
  public List<T> findById(List<T> findInstances) {
    Session session = sessionFactory.openSession();
    try {
      log.debug("Inicio findById");
      List<T> retorno = new ArrayList<T>();
      for (T findInstance : findInstances) {
        retorno.add(getGenericDao().findById(getClavePrimaria(findInstance), session));
      }
      return retorno;
    } finally {
      session.close();
    }
  }

  @Override
  public T findById(T findInstance) {
    Session session = sessionFactory.openSession();
    try {
      log.debug("Inicio findById");
      return getGenericDao().findById(getClavePrimaria(findInstance), session);
    } finally {
      session.close();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService#findById (java.lang.Integer,
   * java.lang.Class)
   */
  @Override
  public List<T> findByCriterias(Class<T> entity, List<Criterion> criterios) {
    Session session = sessionFactory.openSession();
    try {
      log.debug("Inicio findByCriteria");
      return getGenericDao().findByCriterias(entity, criterios, session);
    } finally {
      session.close();
    }
  }

  @Override
  public List<T> findByCriterias(final int firstResult, final int maxResults, Class<T> entity,
      List<Criterion> criterios) {
    Session session = sessionFactory.openSession();
    try {
      return getGenericDao().findByCriterias(firstResult, maxResults, entity, criterios, session);
    } finally {
      session.close();
    }
  }

  public T maxRecord(Class<T> entity, String criterio) {
    Session session = sessionFactory.openSession();
    try {
      log.debug("Inicio maxRecord");
      return getGenericDao().maxRecord(entity, criterio, session);
    } finally {
      session.close();
    }
  }

}
