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

package es.mpt.dsic.inside.service.object.validators.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;

/**
 * Validador para validar cadenas de caracteres contra la bbdd
 * 
 * @param <E>
 * 
 */
public class InsideMetadatoValidatorDataBase {

  protected static final Log logger = LogFactory.getLog(InsideMetadatoValidatorDataBase.class);

  private String table;

  private String column;

  private SessionFactory sessionFactory;

  private String metodo;

  public void validateEqual(Object dato)
      throws InSideServiceValidationException, InSideServiceException {
    logger.debug("Inicio validateEqual:" + dato);
    Session session = sessionFactory.openSession();
    try {
      if (dato instanceof List<?>) {
        for (Object datoAux : (List<?>) dato) {
          Criteria criteria = getCriteriaEqual(datoAux, session);
          Object value = criteria.uniqueResult();
          if (value == null) {
            if (table.equalsIgnoreCase("UnidadORganica")) {
              throw new InSideServiceValidationException("El dato '" + datoAux
                  + "' no encuentra correspondencia en las unidades organicas del DIR3'");
            } else {
              throw new InSideServiceValidationException("El dato '" + datoAux
                  + "' no se encuentra en la tabla '" + table + "' columna '" + column + "'");
            }
          }
        }
      } else {
        Criteria criteria = getCriteriaEqual(dato, session);
        Object value = criteria.uniqueResult();
        if (value == null) {
          if (table.equalsIgnoreCase("UnidadORganica")) {
            throw new InSideServiceValidationException("El dato '" + dato
                + "' no encuentra correspondencia en las unidades organicas del DIR3'");
          } else {
            throw new InSideServiceValidationException("El dato '" + dato
                + "' no se encuentra en la tabla '" + table + "' columna '" + column + "'");
          }
        }
      }
      logger.debug("Fin clean");
    } finally {
      session.close();
    }
  }

  private Criteria getCriteriaEqual(Object dato, Session session) {
    Criteria retorno = session.createCriteria(getTable(table));
    if (dato instanceof List<?>) {
      for (Object datoAux : ((List<?>) dato)) {
        retorno.add(Restrictions.eq(column, datoAux));
      }
    } else {
      retorno.add(Restrictions.eq(column, dato));
    }
    return retorno;
  }

  private Class<?> getTable(final String table) {
    Class<?> retorno = null;
    if (table.equals("UnidadOrganica")) {
      retorno = UnidadOrganica.class;
    }
    return retorno;
  }

  public String getTable() {
    return table;
  }

  public void setTable(String table) {
    this.table = table;
  }

  public String getColumn() {
    return column;
  }

  public void setColumn(String column) {
    this.column = column;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  public String getMetodo() {
    return metodo;
  }

  public void setMetodo(String method) {
    this.metodo = method;
  }

}
