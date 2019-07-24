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

package es.mpt.dsic.inside.web.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import es.mpt.dsic.inside.web.object.ComboItem;

public class ComboUtils {

  public static List<ComboItem> getEstadosElaboracion(MessageSource messageSource, Locale locale) {
    List<ComboItem> retorno = new ArrayList<ComboItem>();
    retorno.add(new ComboItem(messageSource.getMessage("estadoElaboracion.EE01", null, locale),
        "EE_01", null));
    retorno.add(new ComboItem(messageSource.getMessage("estadoElaboracion.EE02", null, locale),
        "EE_02", null));
    retorno.add(new ComboItem(messageSource.getMessage("estadoElaboracion.EE03", null, locale),
        "EE_03", null));
    retorno.add(new ComboItem(messageSource.getMessage("estadoElaboracion.EE04", null, locale),
        "EE_04", null));
    retorno.add(new ComboItem(messageSource.getMessage("estadoElaboracion.EE99", null, locale),
        "EE_99", null));
    return retorno;
  }

  public static List<ComboItem> getTiposDocumentales(MessageSource messageSource, Locale locale) {
    List<ComboItem> retorno = new ArrayList<ComboItem>();
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD01", null, locale),
        "TD_01", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD02", null, locale),
        "TD_02", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD03", null, locale),
        "TD_03", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD04", null, locale),
        "TD_04", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD05", null, locale),
        "TD_05", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD06", null, locale),
        "TD_06", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD07", null, locale),
        "TD_07", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD08", null, locale),
        "TD_08", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD09", null, locale),
        "TD_09", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD10", null, locale),
        "TD_10", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD11", null, locale),
        "TD_11", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD12", null, locale),
        "TD_12", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD13", null, locale),
        "TD_13", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD14", null, locale),
        "TD_14", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD15", null, locale),
        "TD_15", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD16", null, locale),
        "TD_16", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD17", null, locale),
        "TD_17", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD18", null, locale),
        "TD_18", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD19", null, locale),
        "TD_19", null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD20", null, locale),
        "TD_20", null));


    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD51", null, locale),
    // "TD_51", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD52", null, locale),
    // "TD_52", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD53", null, locale),
    // "TD_53", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD54", null, locale),
    // "TD_54", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD55", null, locale),
    // "TD_55", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD56", null, locale),
    // "TD_56", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD57", null, locale),
    // "TD_57", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD58", null, locale),
    // "TD_58", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD59", null, locale),
    // "TD_59", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD60", null, locale),
    // "TD_60", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD61", null, locale),
    // "TD_61", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD62", null, locale),
    // "TD_62", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD63", null, locale),
    // "TD_63", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD64", null, locale),
    // "TD_64", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD65", null, locale),
    // "TD_65", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD66", null, locale),
    // "TD_66", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD67", null, locale),
    // "TD_67", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD68", null, locale),
    // "TD_68", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD69", null, locale),
    // "TD_69", null));
    // retorno.add(new ComboItem(messageSource.getMessage("tipoDocumental.TD99", null, locale),
    // "TD_99", null));

    return retorno;
  }

  public static List<ComboItem> getOrigen(MessageSource messageSource, Locale locale) {
    List<ComboItem> retorno = new ArrayList<ComboItem>();
    retorno
        .add(new ComboItem(messageSource.getMessage("origen.ciudadano", null, locale), "0", null));
    retorno.add(
        new ComboItem(messageSource.getMessage("origen.administracion", null, locale), "1", null));
    return retorno;
  }

  public static List<ComboItem> getEstadosExpediente(MessageSource messageSource, Locale locale) {
    List<ComboItem> retorno = new ArrayList<ComboItem>();
    retorno.add(new ComboItem(messageSource.getMessage("estadoExpediente.E01", null, locale),
        "E_01", null));
    retorno.add(new ComboItem(messageSource.getMessage("estadoExpediente.E02", null, locale),
        "E_02", null));
    retorno.add(new ComboItem(messageSource.getMessage("estadoExpediente.E03", null, locale),
        "E_03", null));
    return retorno;
  }

  public static List<ComboItem> getTiposCertificados(MessageSource messageSource, Locale locale) {
    List<ComboItem> retorno = new ArrayList<ComboItem>();
    retorno.add(new ComboItem(messageSource.getMessage("tipoCertificado.DEFAULT", null, locale),
        WebConstants.TYPE_CERTIFICATE_DEFAULT, null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoCertificado.TF06", null, locale),
        WebConstants.TYPE_CERTIFICATE_PADES, null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoCertificado.TF02", null, locale),
        WebConstants.TYPE_CERTIFICATE_XADES_DETACHED, null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoCertificado.TF03", null, locale),
        WebConstants.TYPE_CERTIFICATE_XADES_ENVELOPED, null));
    retorno.add(new ComboItem(messageSource.getMessage("tipoCertificado.TF05", null, locale),
        WebConstants.TYPE_CERTIFICATE_CADES, null));
    return retorno;
  }

  public static List<String> getOrganosKeys(List<String> organos, HttpServletRequest request) {
    List<String> retorno = new ArrayList<String>();
    for (String organo : organos) {
      if (organo.indexOf("-") != -1) {
        retorno.add(organo.split("-")[0].trim());
      } else {
        retorno.add(organo);
      }
    }
    if (StringUtils.isNotBlank(request.getParameter("organos"))) {
      String organo = request.getParameter("organos");
      if (organo.indexOf("-") != -1) {
        retorno.add(organo.split("-")[0].trim());
      } else {
        retorno.add(organo);
      }
    }
    return retorno;
  }

  public static List<String> getInteresadosKeys(List<String> interesados,
      HttpServletRequest request) {
    List<String> retorno = new ArrayList<String>();
    for (String interesado : interesados) {
      if (interesado.indexOf("-") != -1) {
        retorno.add(interesado.split("-")[0].trim());
      } else {
        retorno.add(interesado);
      }
    }
    if (StringUtils.isNotBlank(request.getParameter("interesados"))) {
      String interesado = request.getParameter("interesados");
      if (interesado.indexOf("-") != -1) {
        retorno.add(interesado.split("-")[0].trim());
      } else {
        retorno.add(interesado);
      }
    }
    return retorno;
  }

}
