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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.StringTokenizer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.impl.MetadatoValidatorStringRegex;
import es.mpt.dsic.inside.service.sia.impl.ConsumidorSIA;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;

/**
 * Validador para validar cadenas de caracteres contra servicios web
 * 
 * @param <E>
 * 
 */
public class InsideMetadatoValidatorService {

  protected static final Log logger = LogFactory.getLog(InsideMetadatoValidatorService.class);

  private ConsumidorSIA consumidorSIA;
  private String metodo;
  private MetadatoValidatorStringRegex validatorString;
  private SessionFactory sessionFactory;

  public void validateClasificacion(Object clasificacion, Map<String, Object> dataDepends)
      throws InSideServiceValidationException {
    logger.debug("Inicio validateClasificacion:" + clasificacion.toString());
    Session session = sessionFactory.openSession();
    try {
      Integer anio =
          ((GregorianCalendar) dataDepends.get("fechaAperturaExpediente")).get(Calendar.YEAR);
      boolean exists = consumidorSIA.existClasificacion(clasificacion.toString(), anio.toString());

      if (!exists) {
        logger.debug("Valor de clasificación no corresponde en SIA");
        validatorString.clean(clasificacion.toString());
        String[] dataSplit = clasificacion.toString().split("_PRO_");
        if (dataSplit[dataSplit.length - 1].length() > 30) {
          throw new InSideServiceValidationException(
              "El dato '" + clasificacion.toString() + "' el identificador supera 30 caracteres'");
        }

        Criteria criteria = session.createCriteria(UnidadOrganica.class);
        StringTokenizer tmpToken = new StringTokenizer(clasificacion.toString(), "_PRO_");
        String data = (String) tmpToken.nextElement();
        criteria.add(Restrictions.eq("codigoUnidadOrganica", data));
        Object value = criteria.uniqueResult();
        if (value == null) {
          throw new InSideServiceValidationException("Clasificación: El dato '" + data
              + "' no encuentra correspondencia en las unidades organicas del SIA ni cumple con la expresión regular '"
              + validatorString.getExpresionRegular() + "'");
        }
      }
    } catch (InSideServiceValidationException e) {
      throw new InSideServiceValidationException("El dato '" + clasificacion.toString()
          + "' no encuentra correspondencia en SIA ni cumple con la expresión regular '"
          + validatorString.getExpresionRegular() + "'");
    } finally {
      session.close();
    }

    logger.debug("Fin validateClasificacion");
  }

  public String getMetodo() {
    return metodo;
  }

  public void setMetodo(String metodo) {
    this.metodo = metodo;
  }

  public ConsumidorSIA getConsumidorSIA() {
    return consumidorSIA;
  }

  public void setConsumidorSIA(ConsumidorSIA consumidorSIA) {
    this.consumidorSIA = consumidorSIA;
  }

  public MetadatoValidatorStringRegex getValidatorString() {
    return validatorString;
  }

  public void setValidatorString(MetadatoValidatorStringRegex validatorString) {
    this.validatorString = validatorString;
  }

  public SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

}
