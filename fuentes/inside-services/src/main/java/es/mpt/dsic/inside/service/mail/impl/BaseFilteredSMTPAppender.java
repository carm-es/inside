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

package es.mpt.dsic.inside.service.mail.impl;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.apache.log4j.net.SMTPAppender;

/**
 * Esta clase permite meter la propiedad mail.smtp.starttls.enable = true y que SMTPAppender no lo
 * permite mediante el fichero log4j.properties.
 * 
 */
public abstract class BaseFilteredSMTPAppender extends SMTPAppender {

  private boolean tls = false;

  @Override
  protected Session createSession() {

    Properties props = null;

    try {

      props = new Properties(System.getProperties());

    } catch (SecurityException ex) {

      props = new Properties();

    }

    if (getSMTPHost() != null) {

      props.put("mail.smtp.host", getSMTPHost());

    }

    if (getSMTPPort() > 0) {

      props.put("mail.smtp.port", getSMTPPort() + "");

    }

    Authenticator auth = null;

    if (getSMTPUsername() != null && getSMTPPassword() != null) {

      props.put("mail.smtp.auth", "true");
      auth = new Authenticator() {

        protected PasswordAuthentication getPasswordAuthentication() {

          return new PasswordAuthentication(getSMTPUsername(), getSMTPPassword());

        }

      };

    }

    if (this.tls) {
      // Metemos la propiedad para el envío con protocolo starttls
      props.put("mail.smtp.starttls.enable", "true");
    }

    Session session = Session.getInstance(props, auth);


    if (getSMTPDebug()) {

      session.setDebug(getSMTPDebug());

    }

    return session;

  }

  public void setTLS(final boolean tls) {

    this.tls = tls;

  }

}
