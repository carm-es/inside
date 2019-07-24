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

package es.mpt.dsic.inside.ws.exception;

import java.util.List;
import javax.xml.ws.WebFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InSideServiceRequestErrorException;
import es.mpt.dsic.inside.service.exception.InSideServiceSignerException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.exception.InsideServiceOperationNotSupportedException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceMetadatoValidationException;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.service.object.validators.exception.InsideServiceObjectValidationException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectAlreadyExistsException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNoActiveException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectPermisosException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectVersionNotFoundException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectVinculatedException;
import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;
import es.mpt.dsic.inside.xml.inside.ws.error.WSErrorInside;

@WebFault(name = "errorInside", faultBean = "es.mpt.dsic.inside.xml.inside.ws.error.WSErrorInside",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/error")
public class InsideWSException extends Exception {

  protected static final Log logger = LogFactory.getLog(InsideWSException.class);

  protected static final String DESCRIPCION = "Error realizando la petición al servicio Inside";

  private static final long serialVersionUID = -1365882364864420452L;

  private WSErrorInside errorInfo;

  public InsideWSException(InsideWsErrors error) {
    super(DESCRIPCION);
    createErrorInside(error);
  }

  public InsideWSException(InsideWsErrors error, Throwable cause) {
    super(DESCRIPCION, cause);
    createErrorInside(error);
  }

  public InsideWSException(InsideWsErrors error, Throwable cause, String aditionalInfo) {
    super(DESCRIPCION, cause);
    createErrorInside(error, aditionalInfo);
  }

  public InsideWSException(InsideValidationDataException insideex) {
    super(DESCRIPCION, insideex);

    logger.debug("InsideWsErrors.REQUEST_ERROR", insideex);
    createErrorInside(InsideWsErrors.REQUEST_ERROR, insideex.getMessage());

  }

  public InsideWSException(InsideConverterException insideex) {
    super(DESCRIPCION, insideex);

    if (insideex.isReportable()) {
      logger.debug("Error de conversión reportable", insideex);
      createErrorInside(InsideWsErrors.REQUEST_ERROR, insideex.getMessage());
    } else {
      logger.error("Error de conversión no reportable", insideex);
      createErrorInside(InsideWsErrors.INTERNAL_SERVICE_ERROR);
    }

    logger.debug("InsideWsErrors.REQUEST_ERROR", insideex);
    createErrorInside(InsideWsErrors.REQUEST_ERROR, insideex.getMessage());

  }

  public InsideWSException(InSideServiceException insideex) {
    super(DESCRIPCION, insideex);
    if (insideex instanceof InSideServiceRequestErrorException) {
      if (insideex instanceof InsideServiceOperationNotSupportedException) {
        logger.debug("InsideWsErrors.OPERATION_NOT_SUPPORTED", insideex);
        createErrorInside(InsideWsErrors.OPERATION_NOT_SUPPORTED);
      } else if (insideex instanceof InSideServiceValidationException) {
        logger.debug("InsideWsErrors.OBJECT_VALIDATION_ERROR", insideex);
        if (insideex instanceof InsideServiceObjectValidationException) {
          InsideServiceObjectValidationException objectValidationError =
              (InsideServiceObjectValidationException) insideex;

          String descripcion = "Error de validación del objeto ";
          descripcion +=
              StringUtils.delete(
                  StringUtils.delete(
                      objectValidationError.getObjetoInside().getClass().getSimpleName(), "Objeto"),
                  "Inside");
          List<InSideServiceMetadatoValidationException> errors =
              objectValidationError.getValidationErrors();
          if (errors.size() > 1) {
            descripcion += " ; errores: ";
            for (InSideServiceMetadatoValidationException error : errors) {
              descripcion += "campo '" + error.getMetadato().getNombre() + "', error: '"
                  + error.getMessage() + "' ";
            }
          } else {
            descripcion += objectValidationError.getMessage();
          }
          createErrorInside(InsideWsErrors.OBJECT_VALIDATION_ERROR, descripcion);
        } else {
          createErrorInside(InsideWsErrors.OBJECT_VALIDATION_ERROR, insideex.getMessage());
        }
      } else if (insideex instanceof InsideStoreObjectAlreadyExistsException) {
        logger.debug("InsideWsErrors.OBJECT_ALREADY_EXISTS", insideex);
        createErrorInside(InsideWsErrors.OBJECT_ALREADY_EXISTS, insideex.getMessage());

      } else if (insideex instanceof InsideStoreObjectVersionNotFoundException) {
        logger.debug("InsideWsErrors.OBJECT_VERSION_NOT_FOUND", insideex);
        createErrorInside(InsideWsErrors.OBJECT_VERSION_NOT_FOUND, insideex.getMessage());
      } else if (insideex instanceof InsideStoreObjectNotFoundException) {
        logger.debug("InsideWsErrors.OBJECT_NOT_FOUND", insideex);
        createErrorInside(InsideWsErrors.OBJECT_NOT_FOUND, insideex.getMessage());
      } else if (insideex instanceof InsideStoreObjectNoActiveException) {
        logger.debug("InsideWsErrors.OBJECT_NO_ACTIVE", insideex);
        createErrorInside(InsideWsErrors.OBJECT_NO_ACTIVE, insideex.getMessage());
      } else if (insideex instanceof InsideStoreObjectVinculatedException) {
        logger.debug("InsideWsErrors.OBJECT_VINCULATED", insideex);
        createErrorInside(InsideWsErrors.OBJECT_VINCULATED, insideex.getMessage());
      } else if (insideex instanceof InsideStoreObjectPermisosException) {
        logger.debug("InsideWsErrors.NO_PERMISO", insideex);
        createErrorInside(InsideWsErrors.NO_PERMISO, insideex.getMessage());
      } else {
        logger.debug("InsideWsErrors.REQUEST_ERROR", insideex);
        createErrorInside(InsideWsErrors.REQUEST_ERROR, insideex.getMessage());
      }
    } else if (insideex instanceof InSideServiceSignerException) {
      logger.warn("InsideWsErrors.SIGNER_ERROR", insideex);
      createErrorInside(InsideWsErrors.SIGNER_ERROR);
    } else if (insideex instanceof InsideServiceInternalException) {
      logger.warn("InsideWsErrors.INTERNAL_SERVICE_ERROR", insideex);
      String mensaje = insideex.getMessage() != null ? insideex.getMessage() : "";
      createErrorInside(InsideWsErrors.INTERNAL_SERVICE_ERROR, mensaje);
    } else if (insideex.isReportable()) {
      logger.warn("Error reportable no contemplado en ", insideex);
      WSErrorInside errorInfo = new WSErrorInside();
      errorInfo.setCodigo(999);
      errorInfo.setDescripcion(insideex.getMessage());
    } else {
      logger.error("Error interno no reportable no contemplado en ", insideex);
      createErrorInside(InsideWsErrors.INTERNAL_SERVICE_ERROR);
    }
  }

  public WSErrorInside getFaultInfo() {
    return errorInfo;
  }

  private void createErrorInside(InsideWsErrors error) {
    this.errorInfo = new WSErrorInside();
    this.errorInfo.setCodigo(error.getValue().getCodigo());
    this.errorInfo.setDescripcion(error.getValue().getDescripcion());
  }

  private void createErrorInside(InsideWsErrors error, String aditional) {
    this.errorInfo = new WSErrorInside();
    this.errorInfo.setCodigo(error.getValue().getCodigo());
    this.errorInfo.setDescripcion(error.getValue().getDescripcion() + " : " + aditional);
  }
}
