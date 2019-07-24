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

import javax.xml.ws.WebFault;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.xml.inside.ws.remisionExpedienteCallback.ErrorEsbType;


@WebFault(name = "ErrorEsb",
    faultBean = "es.mpt.dsic.inside.xml.inside.ws.error.remisionExpedienteCallback",
    targetNamespace = "http://justicia.es/esb/error/xsd-schemas/V1")
public class InsideWSRemisionExpedienteException extends Exception {

  protected static final Log logger = LogFactory.getLog(InsideWSRemisionExpedienteException.class);

  protected static final String DESCRIPCION = "Error realizando la petición al servicio Inside";

  private static final long serialVersionUID = -1365882364864420452L;

  private ErrorEsbType errorInfo;

  public InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors error, Exception e) {
    super(DESCRIPCION);
    createErrorInside(error);
  }

  public InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors error) {
    super(DESCRIPCION);
    createErrorInside(error);
  }

  public InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors error,
      Throwable cause) {
    super(DESCRIPCION, cause);
    createErrorInside(error);
  }

  public InsideWSRemisionExpedienteException(InsideWsRemisionExpedienteErrors error, String cause) {
    super(DESCRIPCION);
    createErrorInside(error, cause);
  }

  public ErrorEsbType getFaultInfo() {
    return errorInfo;
  }

  private void createErrorInside(InsideWsRemisionExpedienteErrors error) {
    this.errorInfo = new ErrorEsbType();
    this.errorInfo.setCodigo(error.getValue().getCodigo());
    this.errorInfo.setDescripcion(error.getValue().getDescripcion());
    this.errorInfo.setTipoError(error.getValue().getTipoError());
  }

  private void createErrorInside(InsideWsRemisionExpedienteErrors error, String causa) {
    this.errorInfo = new ErrorEsbType();
    this.errorInfo.setCodigo(error.getValue().getCodigo());
    this.errorInfo.setDescripcion(error.getValue().getDescripcion());
    this.errorInfo.setCausa(causa);
    this.errorInfo.setTipoError(error.getValue().getTipoError());
  }

  private void createErrorInside(InsideWsRemisionExpedienteErrors error, Exception e) {
    this.errorInfo = new ErrorEsbType();
    this.errorInfo.setCodigo(error.getValue().getCodigo());
    this.errorInfo.setDescripcion(error.getValue().getDescripcion());
    this.errorInfo.setCausa(e.getCause() + "");
    this.errorInfo.setTipoError(error.getValue().getTipoError());
  }

}
