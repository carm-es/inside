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

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package es.mpt.dsic.inside.ws.security.interceptor;

import java.security.cert.X509Certificate;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class CertificateRemisionNubeInterceptor extends AbstractPhaseInterceptor<SoapMessage>
    implements InitializingBean {

  public CertificateRemisionNubeInterceptor() {
    super(Phase.PRE_INVOKE);
  }

  private static Logger logger = Logger.getLogger(CertificateRemisionNubeInterceptor.class);

  private X509Certificate extractClientCertificate(ServletRequest request) {
    X509Certificate[] certs =
        (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");

    if (certs != null && certs.length > 0) {
      if (logger.isDebugEnabled()) {
        logger.debug("X.509 client authentication certificate:" + certs[0]);
      }

      return certs[0];
    }

    if (logger.isDebugEnabled()) {
      logger.debug("No client certificate found in request.");
    }

    return null;
  }

  @Override
  public void handleMessage(SoapMessage message) throws Fault {
    logger.warn("Se activa CertificateRemisionNubeInterceptor---------------------------");
    HttpServletRequest request =
        (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
    String ip = request.getRemoteAddr();
    logger.warn("Se ha llamado a " + request.getPathInfo());
    logger.warn("La ip que nos llama " + ip);
    X509Certificate cert = extractClientCertificate(request);

    if (cert == null) {
      logger.warn("No hay certificado de cliente");
      throw new SoapFault("Se necesita aportar certificado de cliente",
          message.getVersion().getSender());
    } else {
      logger.warn("Viene certificado de cliente:");
      logger.warn("Serial Number: " + cert.getSerialNumber());
      logger.warn("IssuerDN Name: " + cert.getIssuerDN().getName());
      logger.warn("SubjectDN: " + cert.getSubjectDN().getName());
    }
    message.put("serialNumberCliente", cert.getSerialNumber());

  }

  @Override
  public void afterPropertiesSet() throws Exception {}


}
