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
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatos;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.validators.InsideServiceObjectValidator;

/**
 * Un validador de objetos de Inside que se compone de una serie de validaciones en serie
 * 
 * @author miguel.ortega
 *
 */
public class InsideServiceChainValidator<T extends ObjetoInside<K>, K extends ObjetoInsideMetadatos>
    implements InsideServiceObjectValidator<T, K> {

  private List<InsideServiceObjectValidator<T, K>> validadores;

  protected static final Log logger = LogFactory.getLog(InsideServiceChainValidator.class);

  @Override
  public T validate(T objeto) throws InSideServiceException, InSideServiceValidationException {
    if (this.validadores == null) {
      logger.debug("No hay validadores asignados al objeto " + objeto.getClass());
    } else {
      for (InsideServiceObjectValidator<T, K> validador : this.validadores) {
        logger.debug("Validando objeto " + objeto.getClass() + " mediante validador "
            + validador.getClass());
        objeto = validador.validate(objeto);
      }
    }
    return objeto;
  }

  @Required
  public void setValidadores(List<InsideServiceObjectValidator<T, K>> validadores) {
    this.validadores = validadores;
  }

}
