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
import org.apache.commons.io.IOUtils;
import org.springframework.util.DigestUtils;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.util.XMLUtils;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoCarpetaIndizadaConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoCarpetaIndizadaConversionWSMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoDocumentoIndizadoConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoDocumentoIndizadoConversionWSMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInsideWSMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoIndiceConversion;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoIndiceConversionWSMtom;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoOpcionesVisualizacionIndice;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoOpcionesVisualizacionIndiceWS;

/**
 * 
 * @author miguel.ortega
 * 
 */
public abstract class InsideConverterTipoExpedienteMtomConversion {

  public static TipoExpedienteConversionInside convertTipoExpedienteFromWS(
      TipoExpedienteConversionInsideWSMtom data) throws InsideConverterException {

    TipoExpedienteConversionInside retorno = new TipoExpedienteConversionInside();

    retorno.setIndice(convertTipoIndiceFromWS(data.getIndice()));
    retorno.setMetadatosEni(convertMetadataEnifromWS(data.getMetadatosEni()));
    retorno.setOpcionesVisualizacion(
        convertOpcionesVisualizacionFromWS(data.getOpcionesVisualizacion()));

    return retorno;

  }

  public static TipoIndiceConversion convertTipoIndiceFromWS(TipoIndiceConversionWSMtom data)
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
        if (element instanceof TipoDocumentoIndizadoConversionWSMtom) {
          retorno.add(convertTipoDocumentoFromWS((TipoDocumentoIndizadoConversionWSMtom) element));
        }
        if (element instanceof TipoCarpetaIndizadaConversionWSMtom) {
          TipoCarpetaIndizadaConversion carpeta = new TipoCarpetaIndizadaConversion();
          carpeta.setIdentificadorCarpeta(
              ((TipoCarpetaIndizadaConversionWSMtom) element).getIdentificadorCarpeta());
          carpeta.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()
              .addAll(convertIndicefromWS(((TipoCarpetaIndizadaConversionWSMtom) element)
                  .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()));
          retorno.add(carpeta);
        }
      }
    }
    return retorno;
  }

  @SuppressWarnings("deprecation")
  public static TipoDocumentoIndizadoConversion convertTipoDocumentoFromWS(
      TipoDocumentoIndizadoConversionWSMtom data) throws InsideConverterException {
    TipoDocumentoIndizadoConversion retorno;
    try {
      retorno = new TipoDocumentoIndizadoConversion();
      retorno.setFechaIncorporacionExpediente(data.getFechaIncorporacionExpediente());
      retorno.setOrdenDocumentoExpediente(data.getOrdenDocumentoExpediente());

      retorno.setFuncionResumen("md5");
      byte[] datos = IOUtils.toByteArray(data.getContenido().getInputStream());
      String valorHuella = DigestUtils.md5DigestAsHex(datos);
      retorno.setValorHuella(valorHuella);
      try {
        TipoDocumento tDoc = unmarshallRootElement(datos, TipoDocumento.class);
        retorno.setIdentificadorDocumento(tDoc.getId());
      } catch (JAXBException e) {
        try {
          Node nodoAdicionales = XMLUtils.getNode(datos, "ns7:metadatosAdicionales");
          if (nodoAdicionales != null) {
            String nodoEniString = XMLUtils.documentoAdicionalWebToEni(datos);
            TipoDocumento tDoc =
                unmarshallRootElement(IOUtils.toByteArray(nodoEniString), TipoDocumento.class);
            retorno.setIdentificadorDocumento(tDoc.getId());
          } else {
            String nodoEniString = XMLUtils.documentoAdicionalWsToEni(datos);
            TipoDocumento tDoc =
                unmarshallRootElement(IOUtils.toByteArray(nodoEniString), TipoDocumento.class);
            retorno.setIdentificadorDocumento(tDoc.getId());
          }
        } catch (JAXBException e1) {
          throw new InsideConverterException("Error documento adjunto al indice no valido", e1);
        } catch (ParserConfigurationException e1) {
          throw new InsideConverterException("Error documento adjunto al indice no valido", e1);
        } catch (SAXException e1) {
          throw new InsideConverterException("Error documento adjunto al indice no valido", e1);
        } catch (TransformerException e1) {
          throw new InsideConverterException("Error documento adjunto al indice no valido", e1);
        }
      }
    } catch (IOException e) {
      throw new InsideConverterException("Error en fichero de entrada", e);
    }
    return retorno;
  }

  /**
   * Método para convertir un String XML a un objeto JAXB.
   * 
   * @param s Cadena con el XML a convertir.
   * @param clase Class java para saber el tipo.
   * @return Objeto java convertido.
   * @throws JAXBException
   */
  @SuppressWarnings("unchecked")
  public static <T> T unmarshallRootElement(byte[] data, Class<T> clase) throws JAXBException {
    T objeto = null;
    JAXBContext jc = JAXBContext.newInstance(clase.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    objeto = (T) unmarshaller.unmarshal(new ByteArrayInputStream(data));
    if (objeto instanceof JAXBElement<?>) {
      JAXBElement<?> element =
          (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(data));
      objeto = (T) element.getValue();
    }
    return objeto;
  }

  public static TipoExpedienteConversionInside.MetadatosEni convertMetadataEnifromWS(
      TipoExpedienteConversionInsideWSMtom.MetadatosEni data) {
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
      TipoExpedienteConversionInsideWSMtom.MetadatosEni.Estado data) {
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
