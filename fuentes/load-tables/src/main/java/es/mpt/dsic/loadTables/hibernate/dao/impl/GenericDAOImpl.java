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

package es.mpt.dsic.loadTables.hibernate.dao.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.loadTables.hibernate.dao.GenericDAO;
import es.mpt.dsic.inside.store.hibernate.entity.Entidad;

public abstract class GenericDAOImpl<T extends Entidad> implements GenericDAO<T> {

  private static final Log log = LogFactory.getLog(GenericDAOImpl.class);

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.dao.GenericDAO#persist(java.lang .Class)
   */
  @Override
  public void persist(T transientInstance, Session session) {
    log.debug("persist instance");
    try {
      session.persist(transientInstance);
      log.debug("persist successful");
    } catch (RuntimeException re) {
      log.error("persist failed", re);
      throw re;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.dao.GenericDAO#saveOrUpdate(java .lang.Class)
   */
  @Override
  public void saveOrUpdate(T instance, Session session) {
    try {
      log.debug(instance.toString());
      session.saveOrUpdate(instance);
    } catch (RuntimeException re) {
      log.error("saveOrUpdate failed: " + instance.toString(), re);
      throw re;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.dao.GenericDAO#delete(java.lang .Class)
   */
  @Override
  public void delete(T persistentInstance, Session session) {
    log.debug("deleting instance");
    try {
      session.delete(persistentInstance);
      log.debug("delete successful");
    } catch (RuntimeException re) {
      log.error("delete failed", re);
      throw re;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.dao.GenericDAO#merge(java.lang .Class)
   */
  @Override
  @SuppressWarnings("unchecked")
  public T merge(T detachedInstance, Session session) {
    log.debug("merging instance");
    try {
      T result = (T) session.merge(detachedInstance);
      log.debug("merge successful");
      return result;
    } catch (RuntimeException re) {
      log.error("merge failed", re);
      throw re;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.dao.GenericDAO#findById(java. lang.Integer,
   * java.lang.Class)
   */
  @SuppressWarnings("unchecked")
  @Override
  public T findById(java.lang.Integer id, T findInstance, Session session) {
    log.debug("getting instance with id: " + id);
    try {
      T instance = (T) session.get(findInstance.getClass(), id);
      if (instance == null) {
        log.debug("get successful, no instance found");
      } else {
        log.debug("get successful, instance found");
      }
      return instance;
    } catch (RuntimeException re) {
      log.error("get failed", re);
      throw re;
    }
  }

  @SuppressWarnings({"unchecked"})
  @Override
  public List<T> findByCriterias(Class<T> entity, List<Criterion> criterios, Session session) {
    List<T> retorno = null;
    Criteria crit = session.createCriteria(getEntityClass(entity));
    if (criterios != null) {
      for (Criterion criterio : criterios) {
        crit.add(criterio);
      }
    }
    retorno = crit.list();
    return retorno;
  }

  /**
   * Use this inside subclasses as a convenience method.
   */
  @SuppressWarnings("unchecked")
  public List<T> findByCriterias(final int firstResult, final int maxResults, Class<T> entity,
      List<Criterion> criterios, Session session) {
    Criteria crit = session.createCriteria(getEntityClass(entity));

    for (final Criterion c : criterios) {
      crit.add(c);
    }

    if (firstResult > 0) {
      crit.setFirstResult(firstResult);
    }

    if (maxResults > 0) {
      crit.setMaxResults(maxResults);
    }

    final List<T> result = crit.list();
    return result;
  }

  @SuppressWarnings("unchecked")
  public T maxRecord(Class<T> entity, String criterio, Session session) {
    log.debug("Inicio maxRecord");
    Criteria crit = session.createCriteria(getEntityClass(entity));
    crit.addOrder(Order.desc(criterio));
    crit.setMaxResults(1);
    return (T) crit.uniqueResult();
  }

  @SuppressWarnings("unchecked")
  private Class<T> getEntityClass(Class<T> entity) {
    Class<T> retorno = null;
    if (entity.isAssignableFrom(UnidadOrganica.class)) {
      retorno = (Class<T>) UnidadOrganica.class;
    }
    return retorno;
  }

}
