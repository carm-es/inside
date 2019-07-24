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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.apache.xerces.dom.ElementNSImpl;
import org.springframework.util.Assert;
import org.w3c.dom.Element;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterCalendarToXmlCalendarException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterXmlGregorianCalendar;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosTipoDocumental;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.EnumeracionEstadoElaboracion;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoDocumental;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoEstadoElaboracion;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.EnumeracionEstados;
import es.mpt.dsic.inside.xml.eni.expediente.metadatos.TipoMetadatos;
import es.mpt.dsic.inside.xml.inside.DocumentoInsideMetadatos;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideMetadatos;
// import es.mpt.dsic.inside.xml.inside.MetadatoValor;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.documento.alta.TipoDocumentoAltaInside.MetadatosEni;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;

public abstract class InsideConverterMetadatos {

  public static class Expediente {

    /**
     * Convierte los metadatos adicionales desde la entrada del WS a un tipo válido para el
     * InsideService
     * 
     * @param metadatosEni
     * @param metadatosAdicionales
     * @return
     */
    public static ObjetoExpedienteInsideMetadatos metadatosXmlToMetadatosInside(
        TipoMetadatos metadatosEni, TipoMetadatosAdicionales metadatosAdicionales) {
      Assert.notNull(metadatosEni);
      ObjetoExpedienteInsideMetadatos metadatos = metadatosEniToMetadatosInside(metadatosEni);
      metadatos.setMetadatosAdicionales(metadatosAdicionalesXmlToInside(metadatosAdicionales));


      return metadatos;
    }


    /**
     * Convierte los metadatos del ENI a metadatos de Inside
     * 
     * @param metadatosExp
     * @return
     */
    public static ObjetoExpedienteInsideMetadatos metadatosEniToMetadatosInside(
        TipoMetadatos metadatosExp) {
      Assert.notNull(metadatosExp);
      ObjetoExpedienteInsideMetadatos metadatos = new ObjetoExpedienteInsideMetadatos();
      metadatos.setClasificacion(metadatosExp.getClasificacion());
      if (metadatosExp.getEstado() != null && metadatosExp.getEstado().getValue() != null) {
        metadatos.setEstado(ObjetoExpedienteInsideMetadatosEnumeracionEstados
            .fromValue(metadatosExp.getEstado().getValue().value()));
      }
      if (metadatosExp.getFechaAperturaExpediente() != null) {
        metadatos.setFechaAperturaExpediente(
            metadatosExp.getFechaAperturaExpediente().toGregorianCalendar());
      }
      metadatos.setIdentificadorObjetoInside(metadatosExp.getIdentificador());
      metadatos.setVersionNTI(metadatosExp.getVersionNTI());
      metadatos.setOrgano(metadatosExp.getOrgano());
      metadatos.setInteresado(metadatosExp.getInteresado());
      return metadatos;
    }

    /**
     * Convierte todos los metadatos de un expediente al mensaje metadatos del WS de Inside
     * 
     * @param metadatos
     * @return
     * @throws InsideConverterMetadatosInsideToEniException
     * @throws InsideWSInternalErrorException
     */
    public static ExpedienteInsideMetadatos metadatosInsideToXml(
        ObjetoExpedienteInsideMetadatos metadatos) throws InsideConverterException {
      Assert.notNull(metadatos);
      ExpedienteInsideMetadatos metadatosXml = new ExpedienteInsideMetadatos();
      metadatosXml.setMetadatosAdicionales(
          metadatosAdicionalesInsideToXml(metadatos.getMetadatosAdicionales()));
      metadatosXml.setMetadatosExpedienteENI(metadatosInsideToEni(metadatos));
      return metadatosXml;
    }

    /**
     * Convierte los metadatos del Expediente en MetadatosEni
     * 
     * @param metadatosInside
     * @return
     * @throws InsideConverterMetadatosInsideToEniException
     * @throws InsideWSInternalErrorException
     */
    public static TipoMetadatos metadatosInsideToEni(
        ObjetoExpedienteInsideMetadatos metadatosInside) throws InsideConverterException {
      Assert.notNull(metadatosInside);
      TipoMetadatos metadatosEni = new TipoMetadatos();
      metadatosEni.setClasificacion(metadatosInside.getClasificacion());
      TipoMetadatos.Estado estado = new TipoMetadatos.Estado();
      estado.setValue(EnumeracionEstados.fromValue(metadatosInside.getEstado().value()));
      metadatosEni.setEstado(estado);
      try {
        metadatosEni.setFechaAperturaExpediente(InsideConverterXmlGregorianCalendar
            .calendarToXmlCalendar(metadatosInside.getFechaAperturaExpediente()));
      } catch (InsideConverterCalendarToXmlCalendarException e) {
        throw new InsideConverterException(
            "No se puede convertir la fecha: " + metadatosInside.getFechaAperturaExpediente(), e,
            true);
      }
      metadatosEni.setId("EXP_" + metadatosInside.getIdentificadorObjetoInside() + "_METADATOS");
      metadatosEni.setIdentificador(metadatosInside.getIdentificadorObjetoInside());
      metadatosEni.setVersionNTI(metadatosInside.getVersionNTI());
      metadatosEni.getInteresado().addAll(metadatosInside.getInteresado());
      metadatosEni.getOrgano().addAll(metadatosInside.getOrgano());
      return metadatosEni;
    }

    public static ObjetoExpedienteInsideMetadatos metadatosPrimitivoToInside(String clasificacion,
        String estado, Object fechaApertura, List<String> interesados, List<String> organos,
        String versionNTI, String identificadorObjetoInside) throws InsideConverterException {
      ObjetoExpedienteInsideMetadatos metadatosInside = new ObjetoExpedienteInsideMetadatos();
      metadatosInside.setClasificacion(clasificacion);
      if (estado != null) {
        metadatosInside
            .setEstado(ObjetoExpedienteInsideMetadatosEnumeracionEstados.fromValue(estado));
      }

      if (fechaApertura instanceof Calendar) {
        metadatosInside.setFechaAperturaExpediente((Calendar) fechaApertura);
      } else if (fechaApertura instanceof XMLGregorianCalendar) {
        metadatosInside.setFechaAperturaExpediente(
            ((XMLGregorianCalendar) fechaApertura).toGregorianCalendar());
      } else if (fechaApertura instanceof GregorianCalendar) {
        metadatosInside.setFechaAperturaExpediente((GregorianCalendar) fechaApertura);
      } else {
        throw new InsideConverterException("El metadato fechaApertura no se puede convertir", true);
      }

      metadatosInside.setInteresado(interesados);
      metadatosInside.setOrgano(organos);
      metadatosInside.setVersionNTI(versionNTI);
      metadatosInside.setIdentificadorObjetoInside(identificadorObjetoInside);

      return metadatosInside;
    }

  }

  public static class Documento {


    public static ObjetoDocumentoInsideMetadatos metadatosXmlToMetadatosInside(
        es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni,
        TipoMetadatosAdicionales metadatosAdicionales) throws InsideConverterException {
      Assert.notNull(metadatosEni);
      ObjetoDocumentoInsideMetadatos metadatos =
          metadatosEniToInside(metadatosEni, metadatosAdicionales);
      metadatos.setMetadatosAdicionales(metadatosAdicionalesXmlToInside(metadatosAdicionales));


      return metadatos;
    }

    /**
     * Convierte los metadatos de un objeto Eni y los Adicionales del WS de Inside en un objeto
     * ObjetoDocumentoInsideMetadatos de Inside
     * 
     * @param metadatosEni
     * @param metadatosAdicionales
     * @return
     * @throws InsideWsRequestErrorException
     */
    public static ObjetoDocumentoInsideMetadatos metadatosEniToInside(
        es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni,
        TipoMetadatosAdicionales metadatosAdicionales) throws InsideConverterException {
      Assert.notNull(metadatosEni);
      ObjetoDocumentoInsideMetadatos metadatosInside = new ObjetoDocumentoInsideMetadatos();
      ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracion =
          new ObjetoDocumentoInsideMetadatosEstadoElaboracion();
      if (metadatosEni.getEstadoElaboracion() != null) {
        if (metadatosEni.getEstadoElaboracion().getValorEstadoElaboracion() != null) {
          estadoElaboracion.setValorEstadoElaboracion(
              ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.fromValue(
                  metadatosEni.getEstadoElaboracion().getValorEstadoElaboracion().value()));
        }
        estadoElaboracion.setIdentificadorDocumentoOrigen(
            metadatosEni.getEstadoElaboracion().getIdentificadorDocumentoOrigen());
      }
      metadatosInside.setEstadoElaboracion(estadoElaboracion);
      if (metadatosEni.getFechaCaptura() != null) {
        metadatosInside.setFechaCaptura(metadatosEni.getFechaCaptura().toGregorianCalendar());
      }
      metadatosInside.setIdentificadorObjetoInside(metadatosEni.getIdentificador());
      metadatosInside.setMetadatosAdicionales(
          InsideConverterMetadatos.metadatosAdicionalesXmlToInside(metadatosAdicionales));
      metadatosInside
          .setOrigenCiudadanoAdministracion(metadatosEni.isOrigenCiudadanoAdministracion());
      if (metadatosEni.getTipoDocumental() != null) {
        metadatosInside.setTipoDocumental(ObjetoDocumentoInsideMetadatosTipoDocumental
            .fromValue(metadatosEni.getTipoDocumental().value()));
      } else {
        throw new InsideConverterException("Error en el tipo documental de los metadatos", true);
      }

      metadatosInside.setVersionNTI(metadatosEni.getVersionNTI());
      metadatosInside.getOrgano().addAll(metadatosEni.getOrgano());
      return metadatosInside;
    }

    public static es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosInsideToEni(
        ObjetoDocumentoInsideMetadatos metadatosInside) throws InsideConverterException {
      // TODO Llamar a metadatosPrimitivosToEni
      Assert.notNull(metadatosInside);
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni =
          new es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos();
      TipoEstadoElaboracion estadoElaboracion = new TipoEstadoElaboracion();
      if (metadatosInside.getEstadoElaboracion() != null) {
        estadoElaboracion.setValorEstadoElaboracion(EnumeracionEstadoElaboracion
            .fromValue(metadatosInside.getEstadoElaboracion().getValorEstadoElaboracion().value()));
        estadoElaboracion.setIdentificadorDocumentoOrigen(
            metadatosInside.getEstadoElaboracion().getIdentificadorDocumentoOrigen());
      }
      metadatosEni.setEstadoElaboracion(estadoElaboracion);
      try {
        metadatosEni.setFechaCaptura(InsideConverterXmlGregorianCalendar
            .calendarToXmlCalendar(metadatosInside.getFechaCaptura()));
      } catch (InsideConverterCalendarToXmlCalendarException e) {
        throw new InsideConverterException("Error al convertir la fecha de captura", e, false);
      }
      metadatosEni.setIdentificador(metadatosInside.getIdentificadorObjetoInside());
      metadatosEni.setId("DOC_" + metadatosInside.getIdentificadorObjetoInside() + "_METADATOS");
      metadatosEni
          .setOrigenCiudadanoAdministracion(metadatosInside.isOrigenCiudadanoAdministracion());
      metadatosEni
          .setTipoDocumental(TipoDocumental.fromValue(metadatosInside.getTipoDocumental().value()));
      metadatosEni.setVersionNTI(metadatosInside.getVersionNTI());
      metadatosEni.getOrgano().addAll(metadatosInside.getOrgano());
      return metadatosEni;
    }

    public static es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosPrimitivoToEni(
        String identificador, String id, String estadoElaboracion,
        String identificadorDocumentoOrigen, Calendar fechaCaptura,
        boolean origenCiudadanoAdministracion, String tipoDocumental, String versionNTI,
        List<String> organos) throws InsideConverterException {
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni =
          new es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos();
      metadatosEni.setIdentificador(identificador);
      metadatosEni.setEstadoElaboracion(
          estadoElaboracionPrimitivoToEni(estadoElaboracion, identificadorDocumentoOrigen));
      try {
        metadatosEni.setFechaCaptura(
            InsideConverterXmlGregorianCalendar.calendarToXmlCalendar(fechaCaptura));
      } catch (InsideConverterCalendarToXmlCalendarException e) {
        throw new InsideConverterException("Formato inválido en la fecha de captura", e, true);

      }
      metadatosEni.setId(id);
      metadatosEni.setOrigenCiudadanoAdministracion(origenCiudadanoAdministracion);
      metadatosEni.setTipoDocumental(TipoDocumental.fromValue(tipoDocumental));
      if (organos != null) {
        metadatosEni.getOrgano().addAll(organos);
      }

      return metadatosEni;

    }

    public static es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoEstadoElaboracion estadoElaboracionPrimitivoToEni(
        String estadoElaboracion, String identificadorDocumentoOrigen) {
      TipoEstadoElaboracion estadoElaboracionEni = new TipoEstadoElaboracion();
      if (estadoElaboracion != null) {
        estadoElaboracionEni
            .setValorEstadoElaboracion(EnumeracionEstadoElaboracion.fromValue(estadoElaboracion));
      }
      estadoElaboracionEni.setIdentificadorDocumentoOrigen(identificadorDocumentoOrigen);
      return estadoElaboracionEni;
    }

    public static ObjetoDocumentoInsideMetadatos metadatosPrimitivoToInside(
        String estadoElaboracion, String identificadorDocumentoOrigen, Object fechaCaptura,
        boolean origenCiudadanoAdministracion, String tipoDocumental, String versionNTI,
        List<String> organos, String identificadorObjetoInside) throws InsideConverterException {

      ObjetoDocumentoInsideMetadatos metadatosInside = new ObjetoDocumentoInsideMetadatos();

      if (estadoElaboracion != null) {
        metadatosInside.setEstadoElaboracion(
            estadoElaboracionPrimitivoToInside(estadoElaboracion, identificadorDocumentoOrigen));
      }

      if (fechaCaptura instanceof Calendar) {
        metadatosInside.setFechaCaptura(InsideConverterXmlGregorianCalendar
            .calendarToGregorianCalendar((Calendar) fechaCaptura));
      } else if (fechaCaptura instanceof XMLGregorianCalendar) {
        metadatosInside
            .setFechaCaptura(((XMLGregorianCalendar) fechaCaptura).toGregorianCalendar());
      } else if (fechaCaptura instanceof GregorianCalendar) {
        metadatosInside.setFechaCaptura((GregorianCalendar) fechaCaptura);
      }

      metadatosInside.setOrgano(organos);
      metadatosInside.setOrigenCiudadanoAdministracion(origenCiudadanoAdministracion);
      if (tipoDocumental != null) {
        ObjetoDocumentoInsideMetadatosTipoDocumental tDocumental =
            ObjetoDocumentoInsideMetadatosTipoDocumental.fromValue(tipoDocumental);
        metadatosInside.setTipoDocumental(tDocumental);
      }
      metadatosInside.setVersionNTI(versionNTI);
      metadatosInside.setIdentificadorObjetoInside(identificadorObjetoInside);
      return metadatosInside;

    }

    public static TipoDocumentoConversionInside.MetadatosEni metadatosInsideToPrimitivo(
        ObjetoDocumentoInsideMetadatos metadatosInside) throws InsideConverterException {

      TipoDocumentoConversionInside.MetadatosEni metadatosEni =
          new TipoDocumentoConversionInside.MetadatosEni();

      // Estado Elaboración
      metadatosEni.setEstadoElaboracion(
          estadoElaboracionInsideToPrimitivo(metadatosInside.getEstadoElaboracion()));

      // Fecha Captura
      metadatosEni.setFechaCaptura(InsideConverterXmlGregorianCalendar
          .calendarToXmlCalendar(metadatosInside.getFechaCaptura()));

      // Organos
      metadatosEni.getOrgano().addAll(metadatosInside.getOrgano());

      // Origen Ciudadadano
      metadatosEni
          .setOrigenCiudadanoAdministracion(metadatosInside.isOrigenCiudadanoAdministracion());

      // Tipo Documental
      metadatosEni
          .setTipoDocumental(TipoDocumental.fromValue(metadatosInside.getTipoDocumental().value()));

      metadatosEni.setVersionNTI(metadatosInside.getVersionNTI());
      metadatosEni.setIdentificador(metadatosInside.getIdentificadorObjetoInside());
      return metadatosEni;

    }

    public static ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracionPrimitivoToInside(
        String estadoElaboracion, String identificadorDocumentoOrigen) {
      ObjetoDocumentoInsideMetadatosEstadoElaboracion eElaboracion =
          new ObjetoDocumentoInsideMetadatosEstadoElaboracion();
      eElaboracion.setValorEstadoElaboracion(
          ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.fromValue(estadoElaboracion));
      eElaboracion.setIdentificadorDocumentoOrigen(identificadorDocumentoOrigen);
      return eElaboracion;
    }

    public static TipoEstadoElaboracion estadoElaboracionInsideToPrimitivo(
        ObjetoDocumentoInsideMetadatosEstadoElaboracion objElaboracion) {
      TipoEstadoElaboracion eElaboracion = new TipoEstadoElaboracion();
      eElaboracion.setValorEstadoElaboracion(EnumeracionEstadoElaboracion
          .fromValue(objElaboracion.getValorEstadoElaboracion().value()));
      eElaboracion
          .setIdentificadorDocumentoOrigen(objElaboracion.getIdentificadorDocumentoOrigen());
      return eElaboracion;
    }

    public static DocumentoInsideMetadatos metadatosInsideToXml(
        ObjetoDocumentoInsideMetadatos metadatos) throws InsideConverterException {
      Assert.notNull(metadatos);
      DocumentoInsideMetadatos metadatosXml = new DocumentoInsideMetadatos();
      metadatosXml.setMetadatosAdicionales(
          metadatosAdicionalesInsideToXml(metadatos.getMetadatosAdicionales()));
      metadatosXml.setMetadatosDocumentoENI(metadatosInsideToEni(metadatos));
      return metadatosXml;
    }

    public static ObjetoDocumentoInsideMetadatos metadatosEniToInside(MetadatosEni metadatosAltaEni,
        TipoMetadatosAdicionales metadatosAdicionales) throws InsideConverterException {
      es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos metadatosEni =
          new es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoMetadatos();
      metadatosEni.setFechaCaptura(metadatosAltaEni.getFechaCaptura());
      metadatosEni
          .setOrigenCiudadanoAdministracion(metadatosAltaEni.isOrigenCiudadanoAdministracion());
      metadatosEni.setTipoDocumental(metadatosAltaEni.getTipoDocumental());
      metadatosEni.getOrgano().addAll(metadatosAltaEni.getOrgano());
      return metadatosEniToInside(metadatosEni, metadatosAdicionales);
    }

    public static ObjetoDocumentoInsideMetadatos metadatosConversionToInside(
        es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside.MetadatosEni metadatosConversion)
        throws InsideConverterException {

      ObjetoDocumentoInsideMetadatos metadatosInside = metadatosPrimitivoToInside(
          metadatosConversion.getEstadoElaboracion().getValorEstadoElaboracion().value(),
          metadatosConversion.getEstadoElaboracion().getIdentificadorDocumentoOrigen(),
          metadatosConversion.getFechaCaptura(),
          metadatosConversion.isOrigenCiudadanoAdministracion(),
          metadatosConversion.getTipoDocumental().value(), metadatosConversion.getVersionNTI(),
          metadatosConversion.getOrgano(), metadatosConversion.getIdentificador());

      return metadatosInside;
    }


  }



  /**
   * Convierte los metadatos adicionales desde la entrada del WS a un tipo válido para el
   * InsideService
   * 
   * @param metadatosAdicionales
   * @return
   */
  public static List<ObjetoInsideMetadatoAdicional> metadatosAdicionalesXmlToInside(
      TipoMetadatosAdicionales metadatosAdicionales) {

    List<ObjetoInsideMetadatoAdicional> adicionales =
        new ArrayList<ObjetoInsideMetadatoAdicional>();

    if (metadatosAdicionales != null) {
      for (MetadatoAdicional metadatoAdicional : metadatosAdicionales.getMetadatoAdicional()) {
        ObjetoInsideMetadatoAdicional adicional = metadatoAdicionalXmlToInside(metadatoAdicional);
        if (adicional != null)
          adicionales.add(adicional);
      }
    }

    return adicionales;
  }


  /**
   * Convierte un MetadatoAdicional de la entrada del WS a un tipo válido para el InsideService
   * 
   * @param metadatoAdicional
   * @return
   */
  private static ObjetoInsideMetadatoAdicional metadatoAdicionalXmlToInside(
      MetadatoAdicional metadatoAdicional) {
    ObjetoInsideMetadatoAdicional adicional = new ObjetoInsideMetadatoAdicional();
    adicional.setNombre(metadatoAdicional.getNombre());

    // MetadatoValor valor = metadatoAdicional.getValor();
    //
    // Object objValor = null;
    //
    // if(valor.getMetadatoId() != null){
    // objValor = valor.getMetadatoId();
    // }else if(valor.getMetadatoInt() != null){
    // objValor = valor.getMetadatoInt();
    // }else if(valor.getMetadatoString() != null){
    // objValor = valor.getMetadatoString();
    // }else if(valor.getMetadatoXML() != null){
    // objValor = valor.getMetadatoXML();
    // }
    //
    // adicional.setValor(objValor);


    // if
    // (metadatoAdicional.getTipo().getNamespaceURI().equalsIgnoreCase("http://www.w3.org/2001/XMLSchema")
    // &&
    // metadatoAdicional.getTipo().getLocalPart().equalsIgnoreCase("string")) {

    // TODO Esto no funcionará para cuando no sea un String, mirarlo más adelante
    // if (metadatoAdicional.getTipo().getLocalPart().equalsIgnoreCase("string")) {
    // Element e = (Element) metadatoAdicional.getValor();
    // adicional.setValor(e.getTextContent());
    //
    // } else {
    //
    // adicional.setValor(metadatoAdicional.getValor());
    //
    // }


    String valor = null;
    String VACIO = "";
    // Lo de arriba comentado porque se quita el tipo de forma QName y se pone string y se sustituye
    // por la linea de abajo
    if ((metadatoAdicional.getValor() instanceof org.apache.xerces.dom.ElementNSImpl)) {
      org.apache.xerces.dom.ElementNSImpl ele = (ElementNSImpl) metadatoAdicional.getValor();
      valor = ele.getFirstChild() != null ? ele.getFirstChild().getNodeValue() : VACIO;

      adicional.setValor(valor);
    } else {
      adicional.setValor(metadatoAdicional.getValor());
    }

    // si viene sin valor, retornamos null para eliminarlo de la lista de adicionales
    if (VACIO.equals(valor))
      return null;

    return adicional;
  }


  /**
   * Convierte los metadatos Adicionales a un mensaje MetadatosAdicionales del WS de Inside
   * 
   * @param metadatosAdicionales
   * @return
   */
  public static TipoMetadatosAdicionales metadatosAdicionalesInsideToXml(
      List<ObjetoInsideMetadatoAdicional> metadatosAdicionales) {
    TipoMetadatosAdicionales metadatosAdicionalesXml = new TipoMetadatosAdicionales();
    for (ObjetoInsideMetadatoAdicional metadatoAdicional : metadatosAdicionales) {
      metadatosAdicionalesXml.getMetadatoAdicional().add(metadatoInsideToXml(metadatoAdicional));
    }

    return metadatosAdicionalesXml;
  }

  /**
   * Convierte los metadatos Adicionales a un mensaje MetadatosAdicionales del WS de Inside
   * 
   * @param metadatosAdicionales
   * @return
   */
  public static List<MetadatoAdicional> metadatosAdicionalesInsideToPrimitivo(
      List<ObjetoInsideMetadatoAdicional> metadatosAdicionales) {
    List<MetadatoAdicional> listMetadatosAdicionales = new ArrayList<MetadatoAdicional>();
    for (ObjetoInsideMetadatoAdicional metadatoAdicional : metadatosAdicionales) {
      MetadatoAdicional metadatoAdi = new MetadatoAdicional();
      metadatoAdi.setNombre(metadatoAdicional.getNombre());
      metadatoAdi.setValor(metadatoAdicional.getValor());
      metadatoAdi.setTipo(metadatoAdicional.getTipo());
      listMetadatosAdicionales.add(metadatoAdi);
    }
    return listMetadatosAdicionales;
  }



  public static String tipoMetadatosToString(TipoMetadatos metadatosEni) {
    String coma = ", ";
    StringBuffer sb = new StringBuffer("TipoMetadatos=[");
    if (metadatosEni == null) {
      sb.append("null");
    } else {
      sb.append("Id=" + metadatosEni.getId() + coma);
      sb.append("Identificador=" + metadatosEni.getIdentificador() + coma);
      sb.append("VersionNTI=" + metadatosEni.getVersionNTI() + coma);
      sb.append("Clasificacion=" + metadatosEni.getClasificacion() + coma);
      sb.append("Estado=" + metadatosEni.getEstado() + coma);
      sb.append("Fecha Apertura Exp=" + metadatosEni.getFechaAperturaExpediente() + coma);
      sb.append("Organo=" + InsideConverterUtils.listaToString(metadatosEni.getOrgano()) + coma);
      sb.append("Interesado=" + InsideConverterUtils.listaToString(metadatosEni.getInteresado()));
    }
    sb.append("]");
    return sb.toString();
  }

  public static String tipoMetadatosAdicionalesToString(
      TipoMetadatosAdicionales metadatosAdicionales) {
    String coma = ", ";
    StringBuffer sb = new StringBuffer("TipoMetadatosAdicionales=[");
    if (metadatosAdicionales == null) {
      sb.append("null");
    } else {

      List<MetadatoAdicional> lista = metadatosAdicionales.getMetadatoAdicional();
      if (lista == null) {
        sb.append("lista de metadatos adicionales es null");
      } else {
        for (MetadatoAdicional adic : lista) {
          sb.append(metadatoAdicionalToString(adic) + coma);
        }
      }
    }
    sb.append("]");

    return sb.toString();
  }

  public static String metadatoAdicionalToString(MetadatoAdicional metadatoAdicional) {

    StringBuffer sb = new StringBuffer("MetadatoAdicional=[");

    if (metadatoAdicional == null) {
      sb.append("null");
    } else {
      sb.append("Nombre=" + metadatoAdicional.getNombre() + " , ");
      sb.append("Tipo=" + metadatoAdicional.getTipo() + " , ");
      sb.append("Valor=" + metadatoAdicional.getValor() + " , ");
    }
    sb.append("]");
    return sb.toString();
  }

  public static String objetoInsideMetadatoAdicionalToString(
      ObjetoInsideMetadatoAdicional metadatoAdicional) {
    StringBuffer sb = new StringBuffer("ObjetoInsideMetadatoAdicional=[");
    if (metadatoAdicional == null) {
      sb.append("null");
    } else {
      sb.append("Nombre=" + metadatoAdicional.getNombre() + " , ");
      sb.append("Tipo=" + metadatoAdicional.getTipo() + " , ");
      sb.append("Valor=" + metadatoAdicional.getValor() + " , ");
    }
    sb.append("]");
    return sb.toString();
  }

  /**
   * Convierte un metadatoAdicional del modelo de Inside a un mensaje MetadatoAdicional del WS de
   * Inside
   * 
   * @param metadatoAdicional
   * @return
   */
  private static MetadatoAdicional metadatoInsideToXml(
      ObjetoInsideMetadatoAdicional metadatoAdicional) {
    MetadatoAdicional metadatoAdicionalXml = new MetadatoAdicional();
    metadatoAdicionalXml.setNombre(metadatoAdicional.getNombre());
    if (metadatoAdicional.getTipo() != null)
      metadatoAdicionalXml.setTipo(metadatoAdicional.getTipo());
    else {
      // QName qname = new QName("xsd:string");
      // metadatoAdicionalXml.setTipo(qname);
      metadatoAdicionalXml.setTipo("string");
    }
    metadatoAdicionalXml.setValor(metadatoAdicional.getValor());
    return metadatoAdicionalXml;
  }



}
