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
package es.mpt.dsic.inside.ws.security.exception;

import java.io.IOException;
import org.springframework.security.core.AuthenticationException;


/**
 * Exception used by {@link javax.security.auth.callback.CallbackHandler} implementations in this
 * package.
 */
public class SecurityException extends IOException {
  private static final long serialVersionUID = 4859993641237505549L;

  /**
   * Wrap the given {@link AuthenticationException}.
   * 
   * @param cause the Spring Security exception
   */
  public SecurityException(AuthenticationException cause) {
    super(cause.getClass().getSimpleName() + ": " + cause.getMessage());
    initCause(cause);
  }

  /**
   * Construct an exception with the specified detail message.
   *
   * @param msg the detail message.
   */
  public SecurityException(String msg) {
    super(msg);
  }
}
