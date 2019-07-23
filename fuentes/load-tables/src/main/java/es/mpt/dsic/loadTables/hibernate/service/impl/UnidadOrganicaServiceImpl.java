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

import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.loadTables.converter.UnidadOrganicaConverter;
import es.mpt.dsic.loadTables.objects.Organismo;

public class UnidadOrganicaServiceImpl extends GenericServiceImpl<UnidadOrganica> {

  protected static final Log log = LogFactory.getLog(UnidadOrganicaServiceImpl.class);

  public void saveList(List<Organismo> organimos, Date syncDate) {
    log.debug("Inicio saveList");
    if (organimos != null) {
      List<UnidadOrganica> datos = UnidadOrganicaConverter.toEntities(organimos, syncDate);
      saveOrUpdateListFlush(datos, 100);
    }
    log.debug("Fin saveList");
  }

  public Date geLastSync() {
    Date retorno = null;
    UnidadOrganica unidad = maxRecord(UnidadOrganica.class, "timestamp");
    if (unidad != null) {
      retorno = unidad.getTimestamp();
    }
    return retorno;
  }

  @Override
  public String getClavePrimaria(UnidadOrganica instance) {
    return instance.getCodigoUnidadOrganica();
  }

  /*
   * (non-Javadoc)
   * 
   * @see es.mpt.dsic.loadTables.hibernate.service.GenericService# saveOrUpdateListFlush
   * (java.lang.Class)
   */
  public void saveOrUpdateListFlush(List<UnidadOrganica> list, int countFlush) {
    log.debug("Inicio saveOrUpdate");
    Session session = getSessionFactory().openSession();
    Transaction tx = session.beginTransaction();
    try {
      int i = 1;
      UnidadOrganica objectAux = null;
      for (UnidadOrganica data : list) {
        try {
          objectAux = getGenericDao().findById(getClavePrimaria(data), session);
          if (objectAux != null) {
            data.setId(objectAux.getId());
            getGenericDao().merge(data, session);
          } else {
            getGenericDao().saveOrUpdate(data, session);
          }

          if (++i % countFlush == 0) {
            session.flush();
            session.clear();
          }
        } catch (Exception e) {
          log.debug("Error al procesar el registro: " + data.toString());
        }
      }
      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      log.debug("Error saveOrUpdate:" + e.getMessage());
    } finally {
      session.close();
    }
    log.debug("Fin saveOrUpdate");
  }

}
