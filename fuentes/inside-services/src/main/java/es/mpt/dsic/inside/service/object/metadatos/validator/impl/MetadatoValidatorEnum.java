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

import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.inside.service.object.metadatos.metadato.exception.InsideServiceMetadatoValueExtractionException;
import es.mpt.dsic.inside.service.object.metadatos.validator.MetatadatoValidator;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;

public class MetadatoValidatorEnum implements MetatadatoValidator<Enum<?>> {

  protected static final Log logger = LogFactory.getLog(MetadatoValidatorEnum.class);

  private Set<String> valoresAdmitidos;

  // @Override
  // public String clean(String data) throws InSideServiceMetadatoValidationException {
  //
  // if (!valoresAdmitidos.contains(data)) {
  // throw new InSideServiceMetadatoValidationException ("El valor : " + data + " no está en el
  // listado de valores permitidos");
  // }
  //
  // logger.debug("validado correctamente enum con valor '" + data + "'");
  //
  // return data;
  // }

  @Required
  public Set<String> getValoresAdmitidos() {
    return valoresAdmitidos;
  }

  public void setValoresAdmitidos(Set<String> valoresAdmitidos) {
    this.valoresAdmitidos = valoresAdmitidos;
  }

  @Override
  public Enum<?> clean(Enum<?> data)
      throws InSideServiceValidationException, InsideServiceMetadatoValueExtractionException {
    if (!valoresAdmitidos.contains(data.toString())) {
      throw new InSideServiceValidationException(
          "El valor : " + data + " no está en el listado de valores permitidos");
    }

    logger.debug("validado correctamente enum con valor '" + data + "'");

    return data;
  }



}
