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

package es.mpt.dsic.inside.service.object.metadatos.metadato.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.inside.service.object.metadatos.metadato.Metadato;
import es.mpt.dsic.inside.service.object.metadatos.metadato.exception.InsideServiceMetadatoValueExtractionException;
import es.mpt.dsic.inside.service.object.metadatos.validator.MetatadatoValidator;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceMetadatoValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;

/**
 * Este metadato representa la asociación de metadatos en una propiedad
 * 
 * @author miguel.ortega
 *
 */
public class MetadatoMetadatosMap extends Metadato<Object> {

  private Map<String, Metadato<?>> metadatosDef;

  public Map<String, Metadato<?>> getMetadatosDef() {
    return metadatosDef;
  }

  public MetadatoMetadatosMap() throws InsideServiceMetadatoValueExtractionException {
    InsideServiceMetadatosMapValidator metadatosValidator =
        new InsideServiceMetadatosMapValidator(this);
    super.setValidator(metadatosValidator);
  }

  @Required
  public void setMetadatosDef(Map<String, Metadato<?>> metadatosDef) {
    this.metadatosDef = metadatosDef;
  }

  @Override
  public void setValidator(MetatadatoValidator<Object> validator)
      throws InsideServiceMetadatoValueExtractionException {
    // aqui no hacemos nada porque el validador se crea en la construccion del metadato
  }



  private class InsideServiceMetadatosMapValidator implements MetatadatoValidator<Object> {

    private MetadatoMetadatosMap metadatoMap;

    public InsideServiceMetadatosMapValidator(MetadatoMetadatosMap metadatoMetadatosMap) {
      this.metadatoMap = metadatoMetadatosMap;
    }

    @Override
    public Object clean(Object objeto)
        throws InSideServiceValidationException, InsideServiceMetadatoValueExtractionException {

      for (Metadato<?> metadato : this.metadatoMap.getMetadatosDef().values()) {
        logger.debug("Validando metadato " + metadato.getNombre() + " dentro del metadato "
            + this.metadatoMap.getNombre());

        cleanMetadato(metadato, objeto);

        logger.debug("Validado correctamente el metadato " + metadato.getNombre()
            + " dentro del metadato " + this.metadatoMap.getNombre());
      }

      return objeto;
    }

    private void cleanMetadato(Metadato<?> metadato, Object objeto)
        throws InSideServiceMetadatoValidationException,
        InsideServiceMetadatoValueExtractionException {
      PropertyDescriptor pd =
          BeanUtils.getPropertyDescriptor(objeto.getClass(), metadato.getNombre());
      if (pd == null) {
        throw new InsideServiceMetadatoValueExtractionException(
            "No existe un PropertyDescriptor para el metadato " + metadato.getNombre()
                + " en el objeto " + objeto.getClass());
      }
      Method readMethod = pd.getReadMethod();
      Method writeMethod = pd.getWriteMethod();

      if (readMethod == null || writeMethod == null) {
        throw new InsideServiceMetadatoValueExtractionException(
            "No se puede acceder en lectura o en escritura al metadato " + metadato.getNombre()
                + " en el objeto " + objeto.getClass());
      }

      Object value;
      try {
        value = readMethod.invoke(objeto);
      } catch (Exception e) {
        throw new InsideServiceMetadatoValueExtractionException("Error leyendo metadato "
            + metadato.getNombre() + " en el objeto " + objeto.getClass() + " : " + e.getMessage(),
            e);
      }

      value = metadato.clean(value);

      try {
        writeMethod.invoke(objeto, value);
      } catch (Exception e) {
        throw new InsideServiceMetadatoValueExtractionException(
            "Error estableciendo valor de metadato " + metadato.getNombre() + " en el objeto "
                + objeto.getClass() + " : " + e.getMessage(),
            e);
      }

    }

  }



}
