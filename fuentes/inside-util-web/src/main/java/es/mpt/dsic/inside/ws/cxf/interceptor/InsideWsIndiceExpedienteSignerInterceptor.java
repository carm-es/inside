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

package es.mpt.dsic.inside.ws.cxf.interceptor;

import java.io.OutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.interceptor.MessageSenderInterceptor;
import org.apache.cxf.phase.Phase;

public class InsideWsIndiceExpedienteSignerInterceptor extends AbstractSoapInterceptor {

  protected static final Log logger =
      LogFactory.getLog(InsideWsIndiceExpedienteSignerInterceptor.class);

  public InsideWsIndiceExpedienteSignerInterceptor() {
    super(Phase.POST_MARSHAL);
    // super(Phase.PREPARE_SEND);
    addBefore(MessageSenderInterceptor.class.getName());
  }

  @Override
  public void handleMessage(SoapMessage message) throws Fault {
    logger.debug("Entrando en " + InsideWsIndiceExpedienteSignerInterceptor.class.getSimpleName());
    message.getExchange().getOutMessage();
    OutputStream os = message.getContent(OutputStream.class);
    logger.debug("El contenido de respuesta es " + os);
  }

}
