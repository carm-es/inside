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
package es.mpt.dsic.inside.ws.security.implementacion;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;

public class SpringSecurityExceptionMapper implements ExceptionMapper<RuntimeException> {
	public Response toResponse(RuntimeException exception) {
		Response.Status status;
		if (exception instanceof AccessDeniedException) {
			// This means that the principal doesn't have the required authority and we should return 403.
			status = Response.Status.FORBIDDEN;
		} else if (exception instanceof AuthenticationException) {
			// This means that the client could not be authenticated. In this case the client may want to
			// send (new) credentials and we should return 401.
			status = Response.Status.UNAUTHORIZED;
		} else {
			// Everything else is a server problem.
			status = Response.Status.INTERNAL_SERVER_ERROR;
		}
		return Response.status(status).build();
	}
}
