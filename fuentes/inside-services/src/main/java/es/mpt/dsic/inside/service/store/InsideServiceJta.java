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
import org.hibernate.criterion.Criterion;


public interface InsideServiceJta {

  public <T> Object getObjeto(Class<T> bean, Object id);

  public <T> List<T> getAllObjetos(Class<T> bean);

  public <T> List<T> getAllObjetosCriteria(Class<T> bean, List<Criterion> criterias);

  public <T> Object findObjeto(Class<T> bean, List<Criterion> criterias);

  <T> Object saveOrUpdate(T bean);

  <T> Object delete(T bean);

}
