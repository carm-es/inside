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
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterCalendarToXmlCalendarException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterXmlGregorianCalendar;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoEstructuraCarpetaInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceElementoIndizadoOrdenComparator;
import es.mpt.dsic.inside.xml.eni.expediente.indice.TipoIndice;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoDocumentoIndizado;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoIndiceContenido;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoCarpetaIndizadaConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoDocumentoIndizadoConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoIndiceConversion;

public abstract class InsideConverterIndice {

  public static ObjetoExpedienteInsideIndice indiceEniToInside(TipoIndice indiceEni,
      boolean expedienteCerrado) throws InsideConverterException {
    ObjetoExpedienteInsideIndice indice = new ObjetoExpedienteInsideIndice();
    indice.setFirmas(InsideConverterFirmas.firmasEniToInside(indiceEni.getFirmas()));

    indice.setIndiceContenido(InsideConverterIndice
        .contenidoEniToInside(indiceEni.getIndiceContenido(), expedienteCerrado));
    return indice;
  }

  private static ObjetoExpedienteInsideIndiceContenido contenidoEniToInside(
      TipoIndiceContenido indiceContenido, boolean expedienteCerrado)
      throws InsideConverterException {
    ObjetoExpedienteInsideIndiceContenido contenido = new ObjetoExpedienteInsideIndiceContenido();

    List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
        new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();

    int orden = 1;

    for (Object elementoIndizadoEni : indiceContenido
        .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
      ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizadoInside =
          InsideConverterIndice.elementoIndizadoEniToInside(elementoIndizadoEni, expedienteCerrado);
      elementoIndizadoInside.setOrden(orden++);
      elementosIndizados.add(elementoIndizadoInside);
    }
    contenido.setElementosIndizados(elementosIndizados);
    contenido.setFechaIndiceElectronico(
        indiceContenido.getFechaIndiceElectronico().toGregorianCalendar());
    return contenido;
  }

  private static boolean compruebaNoNuloNiValorNull(String valor) {
    return valor != null && !valor.equals("null");
  }

  private static ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizadoEniToInside(
      Object elementoIndizadoEni, boolean expedienteCerrado) throws InsideConverterException {
    ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento;
    if (elementoIndizadoEni instanceof TipoDocumentoIndizado) {
      TipoDocumentoIndizado documentoIndizadoEni = (TipoDocumentoIndizado) elementoIndizadoEni;
      ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
          new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();
      documentoIndizado.setFechaIncorporacionExpediente(
          compruebaNoNuloNiValorNull(documentoIndizadoEni.getFechaIncorporacionExpediente() + "")
              ? InsideConverterXmlGregorianCalendar.calendarToXmlCalendar(
                  documentoIndizadoEni.getFechaIncorporacionExpediente())
              : null);
      documentoIndizado.setFuncionResumen(documentoIndizadoEni.getFuncionResumen());
      documentoIndizado.setIdentificadorDocumento(documentoIndizadoEni.getIdentificadorDocumento());
      documentoIndizado.setValorHuella(documentoIndizadoEni.getValorHuella());
      documentoIndizado.setOrdenDocumentoExpediente(
          compruebaNoNuloNiValorNull(documentoIndizadoEni.getOrdenDocumentoExpediente())
              ? Integer.parseInt(documentoIndizadoEni.getOrdenDocumentoExpediente())
              : 0);
      elemento = documentoIndizado;
    } else if (elementoIndizadoEni instanceof TipoCarpetaIndizada) {
      int contadorCarpeta = 1;
      TipoCarpetaIndizada carpetaIndizadaEni = (TipoCarpetaIndizada) elementoIndizadoEni;
      ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
          new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
          new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();
      for (Object hijoIndizadoEni : carpetaIndizadaEni
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
        ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizadoInside =
            InsideConverterIndice.elementoIndizadoEniToInside(hijoIndizadoEni, expedienteCerrado);
        elementoIndizadoInside.setOrden(contadorCarpeta++);
        elementosIndizados.add(elementoIndizadoInside);
      }
      carpetaIndizada.setElementosIndizados(elementosIndizados);
      carpetaIndizada.setIdentificadorCarpeta(carpetaIndizadaEni.getIdentificadorCarpeta());
      elemento = carpetaIndizada;
    } else if (elementoIndizadoEni instanceof TipoIndiceContenido) {
      int contadorIndiceContenido = 1;
      TipoIndiceContenido indiceIndizadoEni = (TipoIndiceContenido) elementoIndizadoEni;
      ObjetoExpedienteInsideIndiceContenido indiceIndizado =
          new ObjetoExpedienteInsideIndiceContenido();
      List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
          new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();
      for (Object hijoIndizadoEni : indiceIndizadoEni
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
        ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizadoInside =
            InsideConverterIndice.elementoIndizadoEniToInside(hijoIndizadoEni, expedienteCerrado);
        elementoIndizadoInside.setOrden(contadorIndiceContenido++);
        elementosIndizados.add(elementoIndizadoInside);
      }
      indiceIndizado.setElementosIndizados(elementosIndizados);
      indiceIndizado.setFechaIndiceElectronico(InsideConverterXmlGregorianCalendar
          .calendarToXmlCalendar(indiceIndizadoEni.getFechaIndiceElectronico()));
      indiceIndizado.setIdentificadorExpedienteAsociado(indiceIndizadoEni.getId());
      if (!expedienteCerrado)
        indiceIndizado
            .setTipoAsociacion(ObjetoExpedienteInsideIndiceContenido.TipoAsociacion.VINCULACION);
      elemento = indiceIndizado;
    } else {
      throw new InsideConverterException(
          "Tipo de elemento no válido " + elementoIndizadoEni.getClass().toString(), false);
    }

    return elemento;
  }

  public static TipoIndice indiceInsideToEni(ObjetoExpedienteInsideIndice indice,
      String identificadorExpediente) throws InsideConverterException {
    TipoIndice indiceEni = new TipoIndice();
    indiceEni.setId("EXP_INDICE_" + identificadorExpediente);
    TipoIndiceContenido indiceContenido =
        Contenido.indiceContenidoInsideToEni(indice.getIndiceContenido());
    indiceContenido.setId("EXP_INDICE_CONTENIDO" + identificadorExpediente);
    indiceEni.setIndiceContenido(indiceContenido);
    indiceEni.setFirmas(InsideConverterFirmas.firmasInsideToEni(indice.getFirmas(), false));
    return indiceEni;
  }

  public static ObjetoExpedienteInsideIndice indiceConversionToInside(
      TipoIndiceConversion indiceConversion) throws InsideConverterException {
    ObjetoExpedienteInsideIndice indiceInside = new ObjetoExpedienteInsideIndice();
    indiceInside.setIndiceContenido(
        InsideConverterIndice.Contenido.contenidoConversionToInside(indiceConversion));
    return indiceInside;
  }

  public static ObjetoExpedienteInsideIndice indiceConversionToInside(TipoIndice indiceConversion)
      throws InsideConverterException {
    ObjetoExpedienteInsideIndice indiceInside = new ObjetoExpedienteInsideIndice();
    indiceInside.setIndiceContenido(InsideConverterIndice.Contenido
        .contenidoConversionToInside(indiceConversion.getIndiceContenido()));
    return indiceInside;
  }

  public static TipoIndiceContenido estructuraCarpetasInsideToEni(
      ObjetoEstructuraCarpetaInside estructuraCarpeta) throws InsideConverterException {
    return Contenido.estructuraCarpetasInsideToEni(estructuraCarpeta);
  }

  public static ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizadaConversionToInside(
      TipoCarpetaIndizada carpetaIndizada) throws InsideConverterException {
    ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada objCarpetaIndizada =
        new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();
    objCarpetaIndizada.setIdentificadorCarpeta(carpetaIndizada.getIdentificadorCarpeta());
    List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
        new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();
    for (Object hijoIndizadoEni : carpetaIndizada
        .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
      elementosIndizados
          .add(InsideConverterIndice.Contenido.elementoIndizadoConversionToInside(hijoIndizadoEni));
    }
    objCarpetaIndizada.setElementosIndizados(elementosIndizados);
    return objCarpetaIndizada;
  }

  static class Contenido {

    public static TipoIndiceContenido estructuraCarpetasInsideToEni(
        ObjetoEstructuraCarpetaInside estructuraCarpeta) throws InsideConverterException {
      TipoIndiceContenido contenidoIndiceEni = new TipoIndiceContenido();

      // ordenamos la lista por el orden de los elementos, por si alguien
      // no nos la ha dado bien
      Collections.sort(estructuraCarpeta.getElementosIndizados(),
          new ObjetoExpedienteInsideIndiceElementoIndizadoOrdenComparator());

      for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento_indizado : estructuraCarpeta
          .getElementosIndizados()) {
        contenidoIndiceEni.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()
            .add(elementoIndizadoInsideToEni(elemento_indizado));
      }

      return contenidoIndiceEni;
    }

    public static TipoIndiceContenido indiceContenidoInsideToEni(
        ObjetoExpedienteInsideIndiceContenido indiceContenido) throws InsideConverterException {
      TipoIndiceContenido contenidoIndiceEni = new TipoIndiceContenido();
      try {
        contenidoIndiceEni.setFechaIndiceElectronico(InsideConverterXmlGregorianCalendar
            .calendarToXmlCalendar(indiceContenido.getFechaIndiceElectronico()));
        contenidoIndiceEni.setId(indiceContenido.getIdentificadorExpedienteAsociado());
      } catch (InsideConverterCalendarToXmlCalendarException e) {
        throw new InsideConverterException("No se puede convertir la fecha de indice electrónico",
            e, true);
      }

      // ordenamos la lista por el orden de los elementos, por si alguien
      // no nos la ha dado bien
      Collections.sort(indiceContenido.getElementosIndizados(),
          new ObjetoExpedienteInsideIndiceElementoIndizadoOrdenComparator());

      for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento_indizado : indiceContenido
          .getElementosIndizados()) {
        contenidoIndiceEni.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()
            .add(elementoIndizadoInsideToEni(elemento_indizado));
      }

      return contenidoIndiceEni;
    }

    private static Object elementoIndizadoInsideToEni(
        ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado)
        throws InsideConverterException {
      if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenido) {
        return indiceContenidoInsideToEni((ObjetoExpedienteInsideIndiceContenido) elementoIndizado);
      } else if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {
        ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizadaInside =
            (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elementoIndizado;
        TipoCarpetaIndizada carpetaIndizadaEni = new TipoCarpetaIndizada();
        carpetaIndizadaEni.setIdentificadorCarpeta(carpetaIndizadaInside.getIdentificadorCarpeta());
        for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado hijoIndizado : carpetaIndizadaInside
            .getElementosIndizados()) {
          carpetaIndizadaEni.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()
              .add(elementoIndizadoInsideToEni(hijoIndizado));
        }
        return carpetaIndizadaEni;
      } else if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
            (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado;
        TipoDocumentoIndizado documentoIndizadoEni = new TipoDocumentoIndizado();

        if (documentoIndizado.getFechaIncorporacionExpediente() != null) {
          GregorianCalendar fechaIncorporacionExpediente = new GregorianCalendar();
          fechaIncorporacionExpediente.setTimeInMillis(
              documentoIndizado.getFechaIncorporacionExpediente().getTimeInMillis());
          documentoIndizadoEni.setFechaIncorporacionExpediente(InsideConverterXmlGregorianCalendar
              .calendarToXmlCalendar(fechaIncorporacionExpediente));
        }

        documentoIndizadoEni.setFuncionResumen(documentoIndizado.getFuncionResumen());
        documentoIndizadoEni
            .setIdentificadorDocumento(documentoIndizado.getIdentificadorDocumento());
        documentoIndizadoEni.setValorHuella(documentoIndizado.getValorHuella());
        documentoIndizadoEni.setOrdenDocumentoExpediente(Integer
            .toString(((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado)
                .getOrdenDocumentoExpediente()));
        return documentoIndizadoEni;
      } else {
        throw new InsideConverterException("No se como convertir un objeto de tipo "
            + elementoIndizado.getClass() + " a un elemento indizado del Eni", false);
      }
    }

    public static ObjetoExpedienteInsideIndiceContenido contenidoConversionToInside(
        TipoIndiceConversion indiceConversion) throws InsideConverterException {
      ObjetoExpedienteInsideIndiceContenido contenidoInside =
          new ObjetoExpedienteInsideIndiceContenido();

      if (indiceConversion.getFechaIndiceElectronico() != null) {
        contenidoInside.setFechaIndiceElectronico(
            indiceConversion.getFechaIndiceElectronico().toGregorianCalendar());
      }

      int orden = 1;
      if (indiceConversion.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada() != null) {
        for (Object obj : indiceConversion
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
          ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizadoInside =
              elementoIndizadoConversionToInside(obj);
          elementoIndizadoInside.setOrden(orden++);
          contenidoInside.getElementosIndizados().add(elementoIndizadoInside);
        }
      }

      return contenidoInside;
    }

    public static ObjetoExpedienteInsideIndiceContenido contenidoConversionToInside(
        TipoIndiceContenido indiceConversion) throws InsideConverterException {
      ObjetoExpedienteInsideIndiceContenido contenidoInside =
          new ObjetoExpedienteInsideIndiceContenido();

      if (indiceConversion.getFechaIndiceElectronico() != null) {
        contenidoInside.setFechaIndiceElectronico(
            indiceConversion.getFechaIndiceElectronico().toGregorianCalendar());
      }

      int orden = 1;
      if (indiceConversion.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada() != null) {
        for (Object obj : indiceConversion
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
          ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizadoInside =
              elementoIndizadoConversionToInside(obj);
          elementoIndizadoInside.setOrden(orden++);
          contenidoInside.getElementosIndizados().add(elementoIndizadoInside);
        }
      }

      return contenidoInside;
    }

    private static ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizadoConversionToInside(
        Object elementoIndizadoConversion) throws InsideConverterException {

      ObjetoExpedienteInsideIndiceContenidoElementoIndizado elemento;
      if (elementoIndizadoConversion instanceof TipoDocumentoIndizadoConversion) {
        TipoDocumentoIndizadoConversion documentoIndizadoConversion =
            (TipoDocumentoIndizadoConversion) elementoIndizadoConversion;
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
            new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();
        documentoIndizado.setFechaIncorporacionExpediente(InsideConverterXmlGregorianCalendar
            .calendarToXmlCalendar(documentoIndizadoConversion.getFechaIncorporacionExpediente()));
        documentoIndizado.setFuncionResumen(documentoIndizadoConversion.getFuncionResumen());
        documentoIndizado
            .setIdentificadorDocumento(documentoIndizadoConversion.getIdentificadorDocumento());
        documentoIndizado.setValorHuella(documentoIndizadoConversion.getValorHuella());
        if (documentoIndizadoConversion.getOrdenDocumentoExpediente() != null)
          documentoIndizado.setOrdenDocumentoExpediente(
              Integer.parseInt(documentoIndizadoConversion.getOrdenDocumentoExpediente()));
        elemento = documentoIndizado;
      } else if (elementoIndizadoConversion instanceof TipoCarpetaIndizadaConversion) {
        TipoCarpetaIndizadaConversion carpetaIndizadaConversion =
            (TipoCarpetaIndizadaConversion) elementoIndizadoConversion;
        ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
            new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();
        List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
            new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();
        for (Object hijoIndizadoEni : carpetaIndizadaConversion
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
          elementosIndizados.add(
              InsideConverterIndice.Contenido.elementoIndizadoConversionToInside(hijoIndizadoEni));
        }
        carpetaIndizada.setElementosIndizados(elementosIndizados);
        carpetaIndizada
            .setIdentificadorCarpeta(carpetaIndizadaConversion.getIdentificadorCarpeta());
        elemento = carpetaIndizada;
      } else if (elementoIndizadoConversion instanceof TipoIndiceConversion) {
        TipoIndiceConversion indiceIndizadoConversion =
            (TipoIndiceConversion) elementoIndizadoConversion;
        ObjetoExpedienteInsideIndiceContenido indiceIndizado =
            new ObjetoExpedienteInsideIndiceContenido();
        List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
            new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();
        for (Object hijoIndizadoEni : indiceIndizadoConversion
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
          elementosIndizados.add(
              InsideConverterIndice.Contenido.elementoIndizadoConversionToInside(hijoIndizadoEni));
        }
        indiceIndizado.setElementosIndizados(elementosIndizados);
        indiceIndizado.setIdentificadorExpedienteAsociado(indiceIndizadoConversion.getId());
        indiceIndizado.setFechaIndiceElectronico(InsideConverterXmlGregorianCalendar
            .calendarToXmlCalendar(indiceIndizadoConversion.getFechaIndiceElectronico()));
        elemento = indiceIndizado;
      } else if (elementoIndizadoConversion instanceof TipoCarpetaIndizada) {
        TipoCarpetaIndizada carpetaIndizadaConversion =
            (TipoCarpetaIndizada) elementoIndizadoConversion;
        ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
            new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();
        List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
            new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();
        for (Object hijoIndizadoEni : carpetaIndizadaConversion
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
          elementosIndizados.add(
              InsideConverterIndice.Contenido.elementoIndizadoConversionToInside(hijoIndizadoEni));
        }
        carpetaIndizada.setElementosIndizados(elementosIndizados);
        carpetaIndizada
            .setIdentificadorCarpeta(carpetaIndizadaConversion.getIdentificadorCarpeta());
        elemento = carpetaIndizada;
      } else if (elementoIndizadoConversion instanceof TipoDocumentoIndizado) {
        TipoDocumentoIndizado documentoIndizadoConversion =
            (TipoDocumentoIndizado) elementoIndizadoConversion;
        ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
            new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();
        documentoIndizado.setFechaIncorporacionExpediente(InsideConverterXmlGregorianCalendar
            .calendarToXmlCalendar(documentoIndizadoConversion.getFechaIncorporacionExpediente()));
        documentoIndizado.setFuncionResumen(documentoIndizadoConversion.getFuncionResumen());
        documentoIndizado
            .setIdentificadorDocumento(documentoIndizadoConversion.getIdentificadorDocumento());
        documentoIndizado.setValorHuella(documentoIndizadoConversion.getValorHuella());
        if (documentoIndizadoConversion.getOrdenDocumentoExpediente() != null)
          documentoIndizado.setOrdenDocumentoExpediente(
              Integer.parseInt(documentoIndizadoConversion.getOrdenDocumentoExpediente()));
        elemento = documentoIndizado;
      } else {
        throw new InsideConverterException(
            "Tipo de elemento no válido " + elementoIndizadoConversion.getClass().toString(),
            false);
      }

      return elemento;
    }

  }

}
