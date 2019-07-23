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

package es.mpt.dsic.inside.service.object.validators.exception;

import java.util.ArrayList;
import java.util.List;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceMetadatoValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;

/**
 * Error de validación en un objeto de Inside Si el error está producido por la validación de alguna
 * o varias de sus propiedades, estas estarán almacenadas en la propiedad validationErrors de esta
 * excepción
 * 
 * @author miguel.ortega
 *
 */
public class InsideServiceObjectValidationException extends InSideServiceValidationException {

  private static final long serialVersionUID = 1877862325616351878L;

  private List<InSideServiceMetadatoValidationException> validationErrors;

  private ObjetoInside<?> objetoInside;

  public InsideServiceObjectValidationException(ObjetoInside<?> objeto, String mensaje,
      Throwable e) {
    super(generateMessage(objeto) + " : " + mensaje, e);
    this.objetoInside = objeto;
  }

  public InsideServiceObjectValidationException(ObjetoInside<?> objeto, String mensaje) {
    super(generateMessage(objeto) + " : " + mensaje);
    this.objetoInside = objeto;
  }

  public InsideServiceObjectValidationException(ObjetoInside<?> objeto,
      List<InSideServiceMetadatoValidationException> validationErrors) {
    super(generateMessage(objeto, validationErrors));
    this.validationErrors = validationErrors;
    this.objetoInside = objeto;
  }

  public List<InSideServiceMetadatoValidationException> getValidationErrors() {
    if (validationErrors == null) {
      validationErrors = new ArrayList<InSideServiceMetadatoValidationException>();
    }
    return validationErrors;
  }

  private static String generateMessage(ObjetoInside<?> objeto) {
    String mensaje = "Error validando objeto '" + objeto.getClass().getSimpleName() + "'";
    if (objeto.getIdentificador() != null) {
      mensaje += " con identificador '" + objeto.getIdentificador() + "'.";
    } else {
      mensaje += " sin identificador.";
    }
    return mensaje;
  }

  private static String generateMessage(ObjetoInside<?> objeto,
      List<InSideServiceMetadatoValidationException> validationErrors) {
    String mensaje = generateMessage(objeto) + " : Ha fallado la validación de los campos :";
    for (InSideServiceMetadatoValidationException error : validationErrors) {
      mensaje += " [" + error.getMessage() + "] ";
    }
    return mensaje;
  }

  public ObjetoInside<?> getObjetoInside() {
    return objetoInside;
  }


}
