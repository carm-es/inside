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

import java.util.ArrayList;
import java.util.List;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;

public class UnidadOrganicaDAOImpl extends GenericDAOImpl<UnidadOrganica> {

  public UnidadOrganica findById(String id, Session session) {
    UnidadOrganica retorno = null;
    List<Criterion> criterios = new ArrayList<Criterion>();
    criterios.add(Restrictions.eq("codigoUnidadOrganica", id));
    List<UnidadOrganica> unidades = findByCriterias(UnidadOrganica.class, criterios, session);
    if (unidades != null && unidades.size() > 0) {
      retorno = unidades.get(0);
    }
    return retorno;
  }

}
