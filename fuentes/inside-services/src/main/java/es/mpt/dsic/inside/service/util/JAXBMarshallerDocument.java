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

package es.mpt.dsic.inside.service.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;

/**
 * Clase �til de transformaciones XML y objetos java.
 * 
 */
@Component
public class JAXBMarshallerDocument {

  protected static final Log logger = LogFactory.getLog(JAXBMarshallerDocument.class);

  /**
   * M�todo para convertir un objeto Java al string XML equivalente.
   * 
   * @param object Objeto java a convertir.
   * @param clase Class java para saber el tipo.
   * @return String con el XML equivalente.
   * @throws JAXBException
   */
  public <T> String marshallRootElement(T object, Class<T> clase) throws JAXBException {

    StringWriter sw = null;

    JAXBContext jc = JAXBContext.newInstance(clase);
    Marshaller marshaller = jc.createMarshaller();
    sw = new StringWriter();
    marshaller.marshal(object, sw);

    return sw.toString();

  }// fin marshallRootElement

  /**
   * M�todo para convertir un String XML a un objeto JAXB.
   * 
   * @param s Cadena con el XML a convertir.
   * @param clase Class java para saber el tipo.
   * @return Objeto java convertido.
   * @throws JAXBException
   */
  @SuppressWarnings("unchecked")
  public <T> T unmarshallRootElement(String s, Class<T> clase) throws JAXBException {

    JAXBContext jc = JAXBContext.newInstance(clase);
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    T objeto = (T) unmarshaller.unmarshal(new StreamSource(new StringReader(s)));

    if (objeto instanceof JAXBElement<?>) {
      JAXBElement<T> jaxbElement = (JAXBElement<T>) objeto;
      objeto = jaxbElement.getValue();
    }

    return objeto;

  }// fin unmarshallRootElement



  public String marshallDataDocument(Object doc, String nameSapce, String localPart, String prefix)
      throws JAXBException, XPathExpressionException, ParserConfigurationException, SAXException,
      IOException, TransformerException {
    JAXBContext context = JAXBContext.newInstance(doc.getClass());
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
    StringWriter sw = new StringWriter();
    if (doc instanceof TipoDocumento) {

      if (((TipoDocumento) doc).getContenido() != null
          && ((TipoDocumento) doc).getContenido().getDatosXML() != null) {
        marshaller.setProperty(CharacterEscapeHandler.class.getName(),
            new CharacterEscapeHandler() {
              @Override
              public void escape(char[] ac, int i, int j, boolean flag, Writer writer)
                  throws IOException {
                writer.write(ac, i, j);
              }
            });
      }

      if (((TipoDocumento) doc).getContenido() != null
          && ((TipoDocumento) doc).getContenido().getDatosXML() != null) {
        Object data = ((TipoDocumento) doc).getContenido().getDatosXML();
        if (data instanceof String) {
          // Validamos que no tenga ya un CDATA
          if (!String.valueOf(data).startsWith("<![CDATA[")) {
            StringBuilder tmpBuild = new StringBuilder("<![CDATA[");
            tmpBuild.append(data);
            tmpBuild.append("]]>");
            ((TipoDocumento) doc).getContenido().setDatosXML(new String(tmpBuild.toString()));
          }
        }
      }
      JAXBElement<TipoDocumento> jx = new JAXBElement<TipoDocumento>(
          new QName(nameSapce, localPart, prefix), TipoDocumento.class, (TipoDocumento) doc);
      marshaller.marshal(jx, sw);
    }
    if (doc instanceof TipoDocumentoInsideConMAdicionales) {

      if (((TipoDocumentoInsideConMAdicionales) doc).getDocumento().getContenido() != null
          && ((TipoDocumentoInsideConMAdicionales) doc).getDocumento().getContenido()
              .getDatosXML() != null) {
        marshaller.setProperty(CharacterEscapeHandler.class.getName(),
            new CharacterEscapeHandler() {
              @Override
              public void escape(char[] ac, int i, int j, boolean flag, Writer writer)
                  throws IOException {
                writer.write(ac, i, j);
              }
            });
      }

      if (((TipoDocumentoInsideConMAdicionales) doc).getDocumento().getContenido() != null
          && ((TipoDocumentoInsideConMAdicionales) doc).getDocumento().getContenido()
              .getDatosXML() != null) {
        Object data =
            ((TipoDocumentoInsideConMAdicionales) doc).getDocumento().getContenido().getDatosXML();
        if (data instanceof String) {
          StringBuilder tmpBuild = new StringBuilder("<![CDATA[");
          tmpBuild.append(data);
          tmpBuild.append("]]>");
          ((TipoDocumentoInsideConMAdicionales) doc).getDocumento().getContenido()
              .setDatosXML(new String(tmpBuild.toString()));
        }
      }
      JAXBElement<TipoDocumentoInsideConMAdicionales> jx =
          new JAXBElement<TipoDocumentoInsideConMAdicionales>(
              new QName(nameSapce, localPart, prefix), TipoDocumentoInsideConMAdicionales.class,
              (TipoDocumentoInsideConMAdicionales) doc);
      marshaller.marshal(jx, sw);
    }

    return sw.toString();
  }


  public TipoDocumento unmarshallDataDocument(byte[] data)
      throws JAXBException, ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    TipoDocumento retorno = null;
    try {
      JAXBContext jc = JAXBContext.newInstance(TipoDocumento.class.getPackage().getName());

      Unmarshaller unmarshaller = jc.createUnmarshaller(); //
      JAXBElement<?> element =
          (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(data));
      retorno = (TipoDocumento) element.getValue();
    } catch (JAXBException e) {
      Node nodoAdicionales = XMLUtils.getNode(data, "ns7:metadatosAdicionales");
      if (nodoAdicionales != null) {
        byte[] nodoEniString = XMLUtils.documentoAdicionalToEni(data);

        JAXBContext jc = JAXBContext.newInstance(TipoDocumento.class.getPackage().getName());

        Unmarshaller unmarshaller = jc.createUnmarshaller(); //
        JAXBElement<?> element =
            (JAXBElement<?>) unmarshaller.unmarshal(new ByteArrayInputStream(nodoEniString));
        retorno = (TipoDocumento) element.getValue();
      }
    }
    return retorno;
  }

}
