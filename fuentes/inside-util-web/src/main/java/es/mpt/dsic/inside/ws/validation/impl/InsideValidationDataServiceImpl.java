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

package es.mpt.dsic.inside.ws.validation.impl;

import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterXmlGregorianCalendar;
import es.mpt.dsic.inside.ws.validation.InsideValidationDataService;
import es.mpt.dsic.inside.ws.validation.exception.InsideValidationDataException;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoCarpetaIndizadaConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoDocumentoIndizadoConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoIndiceConversion;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;

public class InsideValidationDataServiceImpl implements InsideValidationDataService {

  private static String VERSION_NTI_EXP =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e";
  private static String VERSION_NTI_DOC =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e";

  @Override
  public TipoDocumentoConversionInside validaTipoDocumentoConversionInside(
      TipoDocumentoConversionInside documento, boolean fillEmptyFields)
      throws InsideValidationDataException {
    // VALIDACIÓN
    if (documento.getMetadatosEni() == null) {
      throw new InsideValidationDataException("No se han enviado los metadatos del documento");
    }

    if (documento.getContenido() == null) {
      throw new InsideValidationDataException("No se ha enviado el contenido del documento");
    }

    if (documento.getMetadatosEni().getIdentificador() == null) {
      throw new InsideValidationDataException("El metadato Identificador es obligatorio");
    }

    if (documento.getMetadatosEni().getEstadoElaboracion() == null) {
      throw new InsideValidationDataException("El metadato Estado Elaboración es obligatorio");
    }

    if (documento.getMetadatosEni().getEstadoElaboracion().getValorEstadoElaboracion() == null) {
      throw new InsideValidationDataException(
          "El valor del metadato Estado Elaboración es incorrecto");
    }

    if (documento.getMetadatosEni().getOrgano() == null
        || documento.getMetadatosEni().getOrgano().size() == 0) {
      throw new InsideValidationDataException("Al menos un Órgano es obligatorio");
    }

    if (documento.getMetadatosEni().getTipoDocumental() == null) {
      throw new InsideValidationDataException(
          "El valor del metadato Tipo Documental es incorrecto");
    }

    // SE RELLENAN LOS CAMPOS EN BLANCO SI ASÍ LO SE HA INDICADO EN EL PARÁMETRO
    if (fillEmptyFields) {
      if (documento.getMetadatosEni().getFechaCaptura() == null) {
        try {
          documento.getMetadatosEni().setFechaCaptura(
              InsideConverterXmlGregorianCalendar.calendarToXmlCalendar(new GregorianCalendar()));
        } catch (InsideConverterException e) {
          //
        }
      }

      if (documento.getMetadatosEni().getVersionNTI() == null) {
        documento.getMetadatosEni().setVersionNTI(VERSION_NTI_DOC);
      }
    }
    return documento;

  }

  @Override
  public TipoExpedienteConversionInside validaTipoExpedienteConversionInside(
      TipoExpedienteConversionInside expediente, boolean fillEmptyFields)
      throws InsideValidationDataException {

    // VALIDACIÓN
    if (expediente.getMetadatosEni() == null) {
      throw new InsideValidationDataException("No se han enviado los Metadatos del expediente");
    }

    if (expediente.getIndice() == null) {
      throw new InsideValidationDataException("No se ha enviado el Índice del expediente");
    }

    if (expediente.getOpcionesVisualizacion() == null) {
      throw new InsideValidationDataException(
          "No se han enviado las Opciones de Visualización del índice del expediente");
    }

    if (expediente.getMetadatosEni().getIdentificador() == null) {
      throw new InsideValidationDataException("El metadato Identificador es obligatorio");
    }

    if (expediente.getMetadatosEni().getClasificacion() == null) {
      throw new InsideValidationDataException("El metadato Clasificación es obligatorio");
    }

    if (expediente.getMetadatosEni().getEstado() == null) {
      throw new InsideValidationDataException("El metadato Estado es obligatorio");
    }

    if (expediente.getMetadatosEni().getEstado().getValue() == null) {
      throw new InsideValidationDataException("El valor del metadato Estado es incorrecto");
    }

    if (expediente.getMetadatosEni().getOrgano() == null) {
      throw new InsideValidationDataException("Al menos un Órgano es obligatorio");
    }

    if (expediente.getMetadatosEni().getFechaAperturaExpediente() == null) {
      throw new InsideValidationDataException("El metadato Fecha de Apertura es obligatorio");
    }

    validaTipoIndiceConversion(expediente.getIndice(), true);

    // SE RELLENAN LOS CAMPOS EN BLANCO SI ASÍ SE HA INDICADO EN EL PARÁMETRO
    if (fillEmptyFields) {
      if (expediente.getMetadatosEni().getVersionNTI() == null) {
        expediente.getMetadatosEni().setVersionNTI(VERSION_NTI_EXP);
      }
    }

    return expediente;
  }

  private void validaTipoIndiceConversion(Object obj, boolean fillEmptyFields)
      throws InsideValidationDataException {

    if (obj instanceof TipoIndiceConversion) {
      TipoIndiceConversion indiceConversion = (TipoIndiceConversion) obj;

      List<Object> hijos =
          indiceConversion.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada();

      for (Object hijo : hijos) {
        validaTipoIndiceConversion(hijo, fillEmptyFields);
      }

      if (fillEmptyFields) {
        if (indiceConversion.getFechaIndiceElectronico() == null) {
          try {
            indiceConversion.setFechaIndiceElectronico(
                InsideConverterXmlGregorianCalendar.calendarToXmlCalendar(new GregorianCalendar()));
          } catch (InsideConverterException e) {
            //
          }
        }
      }

    } else if (obj instanceof TipoDocumentoIndizadoConversion) {

      TipoDocumentoIndizadoConversion documentoConversion = (TipoDocumentoIndizadoConversion) obj;

      if (documentoConversion.getIdentificadorDocumento() == null) {
        throw new InsideValidationDataException("No se ha enviado el identificador del documento");
      }

      if (documentoConversion.getFuncionResumen() == null) {
        throw new InsideValidationDataException("No se ha enviado la funcion resumen del documento "
            + documentoConversion.getIdentificadorDocumento());
      }

      if (documentoConversion.getValorHuella() == null) {
        throw new InsideValidationDataException("No se ha enviado la funcion resumen del documento "
            + documentoConversion.getIdentificadorDocumento());
      }

      try {
        Integer.parseInt(documentoConversion.getOrdenDocumentoExpediente());
      } catch (NumberFormatException e) {
        throw new InsideValidationDataException(
            "El orden del documento debe ser un numero. Documento:  "
                + documentoConversion.getIdentificadorDocumento());
      }

      if (fillEmptyFields) {
        if (documentoConversion.getOrdenDocumentoExpediente() == null) {
          documentoConversion.setOrdenDocumentoExpediente("1");
        }
      }


    } else if (obj instanceof TipoCarpetaIndizadaConversion) {

      TipoCarpetaIndizadaConversion carpetaConversion = (TipoCarpetaIndizadaConversion) obj;

      if (carpetaConversion.getIdentificadorCarpeta() == null) {
        throw new InsideValidationDataException("No se ha enviado el identificador de la carpeta");
      }
    }

  }

  public TipoDocumentoValidacionInside validaTipoDocumentoValidacionInside(
      TipoDocumentoValidacionInside documento, boolean fillEmptFileds)
      throws InsideValidationDataException {
    if (documento.getContenido() == null) {
      throw new InsideValidationDataException("No se han enviado los bytes del documento ENI");
    }

    if (documento.getOpcionesValidacionDocumento() == null) {
      throw new InsideValidationDataException(
          "No se han enviado las opciones de validación del documento");
    }

    return documento;
  }



  public TipoExpedienteValidacionInside validaTipoExpedienteValidacionInside(
      TipoExpedienteValidacionInside expediente, boolean fillEmptFileds)
      throws InsideValidationDataException {
    if (expediente.getContenido() == null) {
      throw new InsideValidationDataException("No se han enviado los bytes del expediente ENI");
    }

    if (expediente.getOpcionesValidacionExpediente() == null) {
      throw new InsideValidationDataException(
          "No se han enviado las opciones de validación del expediente");
    }

    return expediente;
  }

  public TipoDocumentoVisualizacionInside validaTipoDocumentoVisualizacionInside(
      TipoDocumentoVisualizacionInside documento, boolean fillEmptyFields)
      throws InsideValidationDataException {
    if (documento.getDocumentoEni() == null) {
      throw new InsideValidationDataException("No se ha enviado el documento ENI");
    }
    if (documento.getDocumentoEni().getDocumentoEniBinario() == null
        && documento.getDocumentoEni().getDocumentoEniTipo() == null) {
      throw new InsideValidationDataException(
          "No se ha enviado el documento ENI ni como binario ni como tipo");
    }
    if (documento.getOpcionesVisualizacionDocumento() == null) {
      throw new InsideValidationDataException(
          "No se han enviado las Opciones de Visualización del documento");
    }
    return documento;
  }
}
