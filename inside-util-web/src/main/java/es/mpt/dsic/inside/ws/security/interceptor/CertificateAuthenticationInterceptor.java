/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package es.mpt.dsic.inside.ws.security.interceptor;

import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.cxf.binding.soap.SoapFault;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.helpers.CastUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSSecurityEngineResult;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.ws.security.handler.WSHandlerResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.service.users.InsideUsersService;
import es.mpt.dsic.inside.service.users.exception.InsideUsersServiceException;

public class CertificateAuthenticationInterceptor extends AbstractPhaseInterceptor<SoapMessage> implements InitializingBean {
	
	private static Logger logger = Logger.getLogger(CertificateAuthenticationInterceptor.class);
	
	private InsideUsersService insideUsersService;
	
	private AuthenticationManager authenticationManager;

	public CertificateAuthenticationInterceptor() {
		super(Phase.PRE_INVOKE);
	}

	public InsideUsersService getInsideUsersService() {
		return insideUsersService;
	}

	public void setInsideUsersService(InsideUsersService insideUsersService) {
		this.insideUsersService = insideUsersService;
	}
	
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	public void afterPropertiesSet() throws Exception {
		if (authenticationManager == null) {
			throw new IllegalStateException("No authentication manager has been configured");
		}
	}

	public void handleMessage(SoapMessage message) throws Fault {
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
				
		try {
			List<WSHandlerResult> results = CastUtils.cast((List<?>) message.get(WSHandlerConstants.RECV_RESULTS));
			for (WSHandlerResult wshr : results) {
				for (WSSecurityEngineResult wsser : wshr.getResults()) {
					X509Certificate publicKey = (X509Certificate) wsser.get(WSSecurityEngineResult.TAG_X509_CERTIFICATE);
					logger.warn("recibida petición ws por certificado:" + publicKey.getSerialNumber().toString());
					ObjetoInsideAplicacion app = insideUsersService.getAplicacionBySerialNumber(publicKey.getSerialNumber().toString());
					if (app == null) {
						securityContext.setAuthentication(null);
						throw new SoapFault("El certificado no asociado con ninguna aplicación", message.getVersion().getSender());
					} else {
						Authentication authentication = new UsernamePasswordAuthenticationToken(app.getAplicacion(), app.getPasswordHash());
						
						try {
							authentication = authenticationManager.authenticate(authentication);
							message.getExchange().put(Authentication.class, authentication);
							
							securityContext.setAuthentication(authentication);
							
						} catch (AuthenticationException ex) {
							securityContext.setAuthentication(null);
							throw new SoapFault("Credenciales Erroneas. compruebe id de aplicación y password",ex, message.getVersion().getSender());
						}
					}
				}
			}
		} catch (InsideUsersServiceException e) {
			securityContext.setAuthentication(null);
			throw new SoapFault("Credenciales Erroneas. compruebe certificado",e, message.getVersion().getSender());
		}

	}

	

}
