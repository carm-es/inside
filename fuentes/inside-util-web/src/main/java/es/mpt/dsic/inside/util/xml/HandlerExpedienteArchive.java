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

package es.mpt.dsic.inside.util.xml;

import java.io.IOException;
import java.io.InputStream;
import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;

/**
 * Handler para expedientes con origen Archive
 * 
 */
public class HandlerExpedienteArchive extends DefaultHandler {

  private StringBuilder value;
  private String anterior = "";
  private TipoMetadatosAdicionales metadatosAdicionales;
  private MetadatoAdicional metadatoAdicional;

  /**
   * Parse de documento
   * 
   * @param is inputStream
   * @throws SAXException
   * @throws ParserConfigurationException
   * @throws IOException
   * @throws UtilException en caso de error
   */
  public HandlerExpedienteArchive(InputStream is)
      throws ParserConfigurationException, SAXException, IOException {
    SAXParserFactory factory = SAXParserFactory.newInstance();
    SAXParser parser;
    parser = factory.newSAXParser();
    parser.parse(is, this);
  }

  public void startElement(String uri, String localName, String qName, Attributes attributes)
      throws SAXException {
    value = new StringBuilder();
    String[] elements = qName.split(":");
    String element = elements[elements.length - 1];
    if (element.equals("metadatosAdicionales")) {
      metadatosAdicionales = new TipoMetadatosAdicionales();
    } else if (element.equals("valor")) {
      metadatoAdicional = new MetadatoAdicional();
      metadatoAdicional.setNombre(anterior);
      metadatoAdicional.setTipo("string");
    } else {
      anterior = element;
    }
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    String[] elements = qName.split(":");
    String element = elements[elements.length - 1];
    if (element.equals("valor")) {
      metadatoAdicional.setValor(value.toString());
      metadatosAdicionales.getMetadatoAdicional().add(metadatoAdicional);
    }
  }

  @Override
  public void characters(char[] ch, int start, int length) {
    if (length == 0)
      return;
    int end = (start + length) - 1;
    while (ch[start] <= '\u0020') {
      if (start == end)
        return;
      start++;
      length--;
    }
    while (ch[end] <= '\u0020') {
      if (end == start)
        return;
      length--;
      end--;
    }
    value.append(ch, start, length);
  }

  public TipoMetadatosAdicionales getMetadatosAdicionales() {
    return metadatosAdicionales;
  }

}
