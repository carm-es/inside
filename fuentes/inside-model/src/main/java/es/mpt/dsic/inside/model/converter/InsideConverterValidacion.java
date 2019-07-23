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
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.OpcionValidacionExpediente;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionDocumento;
import es.mpt.dsic.inside.model.objetos.enivalidation.ResultadoValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionValidacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionesValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDetalleDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionesValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionDetalleExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;

public class InsideConverterValidacion {

  public static List<OpcionValidacionExpediente> tipoOpcionesValidacionExpedienteInsideToListOpcionValidacionExpediente(
      TipoOpcionesValidacionExpedienteInside opciones) {
    List<OpcionValidacionExpediente> opc = new ArrayList<OpcionValidacionExpediente>();
    if (opciones != null) {
      for (TipoOpcionValidacionExpediente opcion : opciones.getOpcionValidacionExpediente()) {
        OpcionValidacionExpediente o = OpcionValidacionExpediente.fromValue(opcion.value());
        opc.add(o);
      }
    }
    return opc;
  }

  public static List<OpcionValidacionDocumento> tipoOpcionesValidacionDocumentoInsideToListOpcionValidacionDocumento(
      TipoOpcionesValidacionDocumentoInside opciones) {
    List<OpcionValidacionDocumento> opc = new ArrayList<OpcionValidacionDocumento>();
    if (opciones != null) {
      for (TipoOpcionValidacionDocumento opcion : opciones.getOpcionValidacionDocumento()) {
        OpcionValidacionDocumento o = OpcionValidacionDocumento.fromValue(opcion.value());
        opc.add(o);
      }
    }
    return opc;
  }

  public static TipoResultadoValidacionDocumentoInside listResultadoValidacionDocumentoToTipoResultadoValidacionDocumentoInside(
      List<ResultadoValidacionDocumento> resultados) {

    TipoResultadoValidacionDocumentoInside res = new TipoResultadoValidacionDocumentoInside();

    if (resultados != null) {
      for (ResultadoValidacionDocumento resultado : resultados) {
        TipoResultadoValidacionDetalleDocumentoInside detalle =
            new TipoResultadoValidacionDetalleDocumentoInside();
        detalle.setResultadoValidacion(resultado.isValido());
        detalle.setDetalleValidacion(resultado.getMensaje());
        detalle.setTipoValidacion(
            TipoOpcionValidacionDocumento.fromValue(resultado.getTipoValidacion().value()));
        res.getValidacionDetalle().add(detalle);
      }
    }
    return res;
  }

  public static TipoResultadoValidacionExpedienteInside listResultadoValidacionExpedienteToTipoResultadoValidacionExpedienteInside(
      List<ResultadoValidacionExpediente> resultados) {

    TipoResultadoValidacionExpedienteInside res = new TipoResultadoValidacionExpedienteInside();

    if (resultados != null) {
      for (ResultadoValidacionExpediente resultado : resultados) {
        TipoResultadoValidacionDetalleExpedienteInside detalle =
            new TipoResultadoValidacionDetalleExpedienteInside();
        detalle.setResultadoValidacion(resultado.isValido());
        detalle.setDetalleValidacion(resultado.getMensaje());
        detalle.setTipoValidacion(
            TipoOpcionValidacionExpediente.fromValue(resultado.getTipoValidacion().value()));
        res.getValidacionDetalle().add(detalle);
      }
    }
    return res;
  }



}
