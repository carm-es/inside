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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.apache.commons.io.IOUtils;
import org.springframework.util.DigestUtils;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.util.XMLUtils;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoCarpetaIndizadaConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoCarpetaIndizadaConversionWS;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoDocumentoIndizadoConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoDocumentoIndizadoConversionWS;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInsideWS;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoIndiceConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoIndiceConversionWS;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoOpcionesVisualizacionIndice;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoOpcionesVisualizacionIndiceWS;

/**
 * 
 * @author miguel.ortega
 * 
 */
public abstract class InsideConverterTipoExpedienteConversion {

  public static TipoExpedienteConversionInside convertTipoExpedienteFromWS(
      TipoExpedienteConversionInsideWS data) throws InsideConverterException {

    TipoExpedienteConversionInside retorno = new TipoExpedienteConversionInside();

    retorno.setIndice(convertTipoIndiceFromWS(data.getIndice()));
    retorno.setMetadatosEni(convertMetadataEnifromWS(data.getMetadatosEni()));
    retorno.setOpcionesVisualizacion(
        convertOpcionesVisualizacionFromWS(data.getOpcionesVisualizacion()));

    return retorno;

  }

  public static TipoIndiceConversion convertTipoIndiceFromWS(TipoIndiceConversionWS data)
      throws InsideConverterException {
    TipoIndiceConversion retorno = new TipoIndiceConversion();
    retorno.setFechaIndiceElectronico(data.getFechaIndiceElectronico());
    retorno.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().addAll(
        convertIndicefromWS(data.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()));
    return retorno;
  }

  public static List<Object> convertIndicefromWS(List<Object> data)
      throws InsideConverterException {
    List<Object> retorno = new ArrayList<Object>();
    if (data != null) {
      for (Object element : data) {
        if (element instanceof TipoDocumentoIndizadoConversionWS) {
          retorno.add(convertTipoDocumentoFromWS((TipoDocumentoIndizadoConversionWS) element));
        }
        if (element instanceof TipoCarpetaIndizadaConversionWS) {
          TipoCarpetaIndizadaConversion carpeta = new TipoCarpetaIndizadaConversion();
          carpeta.setIdentificadorCarpeta(
              ((TipoCarpetaIndizadaConversionWS) element).getIdentificadorCarpeta());
          carpeta.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()
              .addAll(convertIndicefromWS(((TipoCarpetaIndizadaConversionWS) element)
                  .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()));
          retorno.add(carpeta);
        }
      }
    }
    return retorno;
  }

  public static TipoDocumentoIndizadoConversion convertTipoDocumentoFromWS(
      TipoDocumentoIndizadoConversionWS data) throws InsideConverterException {
    TipoDocumentoIndizadoConversion retorno = new TipoDocumentoIndizadoConversion();
    retorno.setFechaIncorporacionExpediente(data.getFechaIncorporacionExpediente());
    retorno.setOrdenDocumentoExpediente(data.getOrdenDocumentoExpediente());

    retorno.setFuncionResumen("md5");
    String valorHuella = DigestUtils.md5DigestAsHex(data.getContenido());
    retorno.setValorHuella(valorHuella);

    JAXBContext jc = null;
    Unmarshaller unmarshaller = null;
    JAXBElement<?> element = null;
    try {
      jc = JAXBContext.newInstance(TipoDocumento.class.getPackage().getName());
      unmarshaller = jc.createUnmarshaller(); //
      element =
          (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(data.getContenido()));
      retorno.setIdentificadorDocumento(((TipoDocumento) element.getValue()).getId());
    } catch (JAXBException e) {
      try {
        Node nodoEni = XMLUtils.getNode(data.getContenido(), "ns5:documento");
        if (nodoEni != null) {
          String nodoEniString = XMLUtils.documentoAdicionalWebToEni(data.getContenido());
          element = (JAXBElement<?>) unmarshaller
              .unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
          retorno.setIdentificadorDocumento(
              ((TipoDocumento) element.getValue()).getMetadatos().getIdentificador());
        } else {
          String nodoEniString = XMLUtils.documentoAdicionalWsToEni(data.getContenido());
          element = (JAXBElement<?>) unmarshaller
              .unmarshal(new ByteArrayInputStream(IOUtils.toByteArray(nodoEniString)));
          retorno.setIdentificadorDocumento(
              ((TipoDocumento) element.getValue()).getMetadatos().getIdentificador());
        }
      } catch (JAXBException e1) {
        throw new InsideConverterException("Documento adjunto al indice no valido", e1);
      } catch (ParserConfigurationException e1) {
        throw new InsideConverterException("Documento adjunto al indice no valido", e1);
      } catch (SAXException e1) {
        throw new InsideConverterException("Documento adjunto al indice no valido", e1);
      } catch (IOException e1) {
        throw new InsideConverterException("Documento adjunto al indice no valido", e1);
      } catch (TransformerFactoryConfigurationError e1) {
        throw new InsideConverterException("Documento adjunto al indice no valido", e1);
      } catch (TransformerException e1) {
        throw new InsideConverterException("Documento adjunto al indice no valido", e1);
      }
    }
    return retorno;
  }

  public static TipoExpedienteConversionInside.MetadatosEni convertMetadataEnifromWS(
      TipoExpedienteConversionInsideWS.MetadatosEni data) {
    TipoExpedienteConversionInside.MetadatosEni retorno =
        new TipoExpedienteConversionInside.MetadatosEni();
    retorno.setClasificacion(data.getClasificacion());
    retorno.setEstado(convertEstadoFromWS(data.getEstado()));
    retorno.setFechaAperturaExpediente(data.getFechaAperturaExpediente());
    retorno.setIdentificador(data.getIdentificador());
    retorno.setVersionNTI(data.getVersionNTI());
    retorno.getInteresado().addAll(data.getInteresado());
    retorno.getOrgano().addAll(data.getOrgano());
    return retorno;
  }

  public static TipoExpedienteConversionInside.MetadatosEni.Estado convertEstadoFromWS(
      TipoExpedienteConversionInsideWS.MetadatosEni.Estado data) {
    TipoExpedienteConversionInside.MetadatosEni.Estado retorno =
        new TipoExpedienteConversionInside.MetadatosEni.Estado();
    retorno.setValue(data.getValue());
    return retorno;
  }

  public static TipoOpcionesVisualizacionIndice convertOpcionesVisualizacionFromWS(
      TipoOpcionesVisualizacionIndiceWS data) {
    TipoOpcionesVisualizacionIndice retorno = new TipoOpcionesVisualizacionIndice();
    retorno.setEstamparImagen(data.isEstamparImagen());
    retorno.setEstamparNombreOrganismo(data.isEstamparNombreOrganismo());
    retorno.setEstamparPie(data.isEstamparPie());
    retorno.setFilasNombreOrganismo(convertFilasOrganismoFromWS(data.getFilasNombreOrganismo()));
    retorno.setTextoPie(data.getTextoPie());
    return retorno;
  }

  public static TipoOpcionesVisualizacionIndice.FilasNombreOrganismo convertFilasOrganismoFromWS(
      TipoOpcionesVisualizacionIndiceWS.FilasNombreOrganismo data) {
    TipoOpcionesVisualizacionIndice.FilasNombreOrganismo retorno =
        new TipoOpcionesVisualizacionIndice.FilasNombreOrganismo();
    if (data != null) {
      retorno.getFila().addAll(data.getFila());
    }
    return retorno;
  }

}
