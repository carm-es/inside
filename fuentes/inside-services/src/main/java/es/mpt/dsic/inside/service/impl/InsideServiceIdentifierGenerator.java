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

package es.mpt.dsic.inside.service.impl;

import java.math.BigInteger;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;

public class InsideServiceIdentifierGenerator {

  public static String generateObjetoInsideIdentificador(ObjetoInside<?> objeto)
      throws InSideServiceException {
    if (objeto.getIdentificador() != null) {
      return objeto.getIdentificador();
    }
    String identificador = "ES_";
    List<String> organo = null;
    if (objeto instanceof ObjetoExpedienteInside) {
      organo = ((ObjetoExpedienteInside) objeto).getMetadatos().getOrgano();
    } else if (objeto instanceof ObjetoDocumentoInside) {
      organo = ((ObjetoDocumentoInside) objeto).getMetadatos().getOrgano();
    }
    if (organo == null || organo.size() < 1) {
      // TODO ¿Excepción?
      identificador += "E00010207";
    } else {
      identificador += organo.get(0);
    }
    identificador += "_" + GregorianCalendar.getInstance().get(Calendar.YEAR);

    String secuencia = "";
    if (objeto instanceof ObjetoExpedienteInside) {
      secuencia += "EXP_";
    } else if (objeto instanceof ObjetoDocumentoInside) {
      secuencia += "DOC";
    }

    try {
      // Añadimos las partes H (Hash del host) M (Tiempo en milisegundos de la máquina) + R
      // (aleatorio alfanumérico hasta completar 30)
      secuencia += "H" + java.net.InetAddress.getLocalHost().hashCode() + "M"
          + System.currentTimeMillis() + "R";
    } catch (UnknownHostException e) {
      throw new InsideServiceInternalException("Error obteniendo Hashcode del MAC del HOST", e);
    }

    // añadimos las posiciones que falten a la secuencia para completar los 30 del ENI
    secuencia += new BigInteger(130, new SecureRandom()).toString(32).toUpperCase().substring(0,
        30 - secuencia.length());

    identificador += "_" + secuencia;

    return identificador;

  }

}
