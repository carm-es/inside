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

package es.mpt.dsic.inside.service.object.metadatos.validator.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.inside.service.object.metadatos.metadato.exception.InsideServiceMetadatoValueExtractionException;
import es.mpt.dsic.inside.service.object.metadatos.validator.MetatadatoValidator;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;

/**
 * Validador para metadatos que sean un objeto con propiedades de propiedades Cada propiedad se
 * validará con un MetatadatoValidator definido en la propiedad "elementValidators" de este
 * validador
 * 
 * @author miguel.ortega
 *
 */
public class MetadatoValidatorPojo implements MetatadatoValidator<Object> {
  protected static final Log logger = LogFactory.getLog(MetadatoValidatorPojo.class);

  protected Map<String, MetatadatoValidator<Object>> elementValidators;

  @Override
  public Object clean(Object pojo)
      throws InsideServiceMetadatoValueExtractionException, InSideServiceValidationException {
    for (String propertyToValidate : elementValidators.keySet()) {
      PropertyDescriptor propertyDescriptor =
          BeanUtils.getPropertyDescriptor(pojo.getClass(), propertyToValidate);

      if (propertyDescriptor == null) {
        throw new InsideServiceMetadatoValueExtractionException("No se encuentra la propiedad "
            + propertyToValidate + " el el objeto " + pojo.getClass());
      }

      Method readMethod = propertyDescriptor.getReadMethod();
      if (readMethod == null) {
        throw new InsideServiceMetadatoValueExtractionException("La propiedad " + propertyToValidate
            + " del objeto " + pojo.getClass() + " no es accesible en lectura");
      }
      Object value = null;
      try {
        value = readMethod.invoke(pojo);
        logger.debug("Leida correctamente la propiedad " + propertyToValidate + " en el 'pojo' "
            + pojo.getClass() + " con el valor '" + value + "'");
      } catch (Exception e) {
        throw new InsideServiceMetadatoValueExtractionException(
            "Error invocando el metodo de lectura de la propiedad " + propertyToValidate
                + " del objeto " + pojo.getClass() + " : " + e.getMessage(),
            e);
      }
      MetatadatoValidator<Object> propertyValidator = elementValidators.get(propertyToValidate);
      value = propertyValidator.clean(value);

      Method writeMethod = propertyDescriptor.getWriteMethod();
      try {
        writeMethod.invoke(pojo, value);
      } catch (Exception e) {
        throw new InsideServiceMetadatoValueExtractionException(
            "Error invocando el metodo de escritura de la propiedad " + propertyDescriptor
                + " del objeto " + pojo.getClass() + " : " + e.getMessage(),
            e);
      }
    }
    return pojo;
  }

  public Map<String, MetatadatoValidator<Object>> getElementValidators() {
    return elementValidators;
  }

  @Required
  public void setElementValidators(Map<String, MetatadatoValidator<Object>> elementValidator) {
    this.elementValidators = elementValidator;
  }


}
