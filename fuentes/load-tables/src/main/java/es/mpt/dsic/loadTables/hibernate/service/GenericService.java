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

package es.mpt.dsic.loadTables.hibernate.service;

import java.util.List;
import org.hibernate.criterion.Criterion;
import es.mpt.dsic.inside.store.hibernate.entity.Entidad;
import es.mpt.dsic.loadTables.hibernate.dao.GenericDAO;

public interface GenericService<T extends Entidad> {

  public abstract GenericDAO<T> getGenericDao();

  public abstract void setGenericDao(GenericDAO<T> genericDao);

  public abstract void persist(T transientInstance);

  public abstract void saveOrUpdate(T instance);

  public abstract void saveOrUpdateList(List<T> list);

  public abstract void saveOrUpdateListFlush(List<T> list, int countFlush);

  public abstract void delete(T persistentInstance);

  public abstract T merge(T detachedInstance);

  public abstract T findById(java.lang.Integer id, T findInstance);

  public abstract T findById(T findInstance);

  public abstract List<T> findById(List<T> findInstances);

  public abstract List<T> findByCriterias(Class<T> entity, List<Criterion> criterios);

  public List<T> findByCriterias(final int firstResult, final int maxResults, Class<T> entity,
      List<Criterion> criterios);

  public abstract T maxRecord(Class<T> entity, String criterio);

  public abstract String getClavePrimaria(T instance);

}
