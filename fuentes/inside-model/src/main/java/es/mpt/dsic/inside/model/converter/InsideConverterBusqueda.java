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

package es.mpt.dsic.inside.model.converter;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaInside;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaMetadatoInside;
import es.mpt.dsic.inside.model.busqueda.consulta.ConsultaSubInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorBetweenInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorDateRange;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorInside;
import es.mpt.dsic.inside.model.busqueda.metadato.MetadatoValorSimpleInside;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterXmlGregorianCalendar;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.ConsultaWsInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.SubConsultaWsInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.TipoSubconsulta;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.MetadatoBusquedaWsInside;
import es.mpt.dsic.inside.xml.inside.ws.busqueda.MetadatoBusquedaWsInside.Valor;

public class InsideConverterBusqueda {

  protected static final Log logger = LogFactory.getLog(InsideConverterBusqueda.class);

  public static ConsultaInside consultaXmlToInside(ConsultaWsInside consultaXml)
      throws InsideConverterException {
    ConsultaInside consulta = null;
    if (consultaXml.getMetadato() != null) {
      consulta = new ConsultaMetadatoInside(
          InsideConverterBusquedaMetadato.metadatoXmlToInside(consultaXml.getMetadato()));
    } else if (consultaXml.getSubConsulta() != null) {
      consulta = InsideConverterSubConsulta.consultaSubXmlToInside(consultaXml.getSubConsulta());
    }

    return consulta;
  }

  static class InsideConverterBusquedaMetadato {

    public static MetadatoBusquedaInside metadatoXmlToInside(MetadatoBusquedaWsInside metadatoXml)
        throws InsideConverterException {

      Valor valorXml = metadatoXml.getValor();

      MetadatoValorInside valor = null;

      if (StringUtils.isNotBlank(valorXml.getEqual())) {
        if (InsideConverterUtils.isBooleanValue(valorXml.getEqual())) {
          valor = new MetadatoValorSimpleInside(MetadatoValorSimpleInside.TipoBusqueda.equal,
              Boolean.valueOf(valorXml.getEqual()));
        } else {
          valor = new MetadatoValorSimpleInside(MetadatoValorSimpleInside.TipoBusqueda.equal,
              valorXml.getEqual());
        }
      } else if (StringUtils.isNotBlank(valorXml.getLike())) {
        valor = new MetadatoValorSimpleInside(MetadatoValorSimpleInside.TipoBusqueda.like,
            valorXml.getLike());
      } else if (valorXml.getMinusThan() != null) {
        valor = new MetadatoValorSimpleInside(MetadatoValorSimpleInside.TipoBusqueda.lessThan,
            valorXml.getMinusThan());
      } else if (valorXml.getMoreThan() != null) {
        valor = new MetadatoValorSimpleInside(MetadatoValorSimpleInside.TipoBusqueda.greaterThan,
            valorXml.getMoreThan());
      } else if (valorXml.getBetween() != null) {
        valor = new MetadatoValorBetweenInside(valorXml.getBetween().getInfRange(),
            valorXml.getBetween().getSupRange());
      } else if (valorXml.getDateRange() != null) {
        valor = new MetadatoValorDateRange(
            InsideConverterXmlGregorianCalendar
                .calendarToXmlCalendar(valorXml.getDateRange().getFrom()),
            InsideConverterXmlGregorianCalendar
                .calendarToXmlCalendar(valorXml.getDateRange().getTo()));
      } else {
        throw new InsideConverterException("El valor de la condición de busqueda está vacío", true);
      }

      return new MetadatoBusquedaInside(metadatoXml.getNombre(), valor);
    }
  }

  static class InsideConverterSubConsulta {

    public static ConsultaSubInside consultaSubXmlToInside(SubConsultaWsInside subconsultaXml)
        throws InsideConverterException {
      ConsultaSubInside subConsulta = new ConsultaSubInside();
      subConsulta.setTipoSubConsulta(tipoSubConsultaXmlToInside(subconsultaXml.getTipo()));

      List<ConsultaInside> subconsultasInside = new ArrayList<ConsultaInside>();


      for (SubConsultaWsInside subSubConsulta : subconsultaXml.getSubConsulta()) {
        subconsultasInside.add(consultaSubXmlToInside(subSubConsulta));
      }
      for (MetadatoBusquedaWsInside consultaMetadato : subconsultaXml.getMetadato()) {
        subconsultasInside.add(new ConsultaMetadatoInside(
            InsideConverterBusquedaMetadato.metadatoXmlToInside(consultaMetadato)));
      }

      if (subconsultasInside.size() < 1) {
        throw new InsideConverterException("La subconsulta está vacía", true);
      }

      subConsulta.setSubconsultas(subconsultasInside);

      return subConsulta;

    }

    public static ConsultaSubInside.TipoSubConsulta tipoSubConsultaXmlToInside(
        TipoSubconsulta tipoSubConsultaXml) throws InsideConverterException {
      ConsultaSubInside.TipoSubConsulta tipo = null;
      switch (tipoSubConsultaXml) {
        case OR:
          tipo = ConsultaSubInside.TipoSubConsulta.OR;
          break;
        case AND:
          tipo = ConsultaSubInside.TipoSubConsulta.AND;
          break;
        default:
          throw new InsideConverterException(
              "Tipo de subconsulta no admitido: " + tipoSubConsultaXml.value(), true);
      }
      return tipo;
    }
  }

}
