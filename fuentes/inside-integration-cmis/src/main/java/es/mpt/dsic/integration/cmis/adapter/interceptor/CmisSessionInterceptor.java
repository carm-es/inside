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

package es.mpt.dsic.integration.cmis.adapter.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import es.mpt.dsic.integration.cmis.session.CmisSessionManager;

@Aspect
public class CmisSessionInterceptor {

  static final Log logger = LogFactory.getLog(CmisSessionInterceptor.class);

  private CmisSessionManager sessionManager;

  @Around("execution(* es.mpt..*.adapter..*.*(..)) && @annotation(es.mpt.dsic.integration.cmis.adapter.interceptor.annotation.CmisSession)")
  public Object doConcurrentOperation(final ProceedingJoinPoint pjp) throws Throwable {
    sessionManager.authenticate();
    return pjp.proceed();
  }

  public void setSessionManager(final CmisSessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }
}
