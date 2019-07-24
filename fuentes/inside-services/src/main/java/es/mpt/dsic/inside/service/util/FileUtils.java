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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;
import org.apache.axiom.om.util.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.xml.eni.documento.TipoDocumento;

@Component
@PropertySource("file:${config.path}/csvstorage.properties")
public class FileUtils {

  @Value("${csvstorage.bigData.active}")
  private Boolean bigDataServiceActive;

  /**
   * Obtiene una lista de los ficheros con una extensión dentro de un directorio. Si el directorio
   * no se puede acceder, no existe o no contiene ficheros devuelve una lista vacía.
   * 
   * @param f Directorio donde se desea buscar
   * @param extension extensión de los ficheros.
   * @return
   */
  public static List<File> getFilesInFolder(File f, String extension) {
    List<File> files = new ArrayList<File>();

    if (!f.isDirectory() || !f.canRead() || f.listFiles().length == 0) {
      return files;
    }

    File[] filesIn = f.listFiles();

    for (File file : filesIn) {
      if (file.getName().endsWith(extension)) {
        files.add(file);
      }
    }

    return files;
  }

  public boolean isBigFile(byte[] fileBytes) {
    long fileSizeInMB = fileBytes.length / 1024 / 1024;
    if (fileSizeInMB > 8 && bigDataServiceActive) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isBigFileKb(byte[] fileBytes) {
    long fileSizeInMB = fileBytes.length / 1024;
    if (fileSizeInMB > 8000 && bigDataServiceActive) {
      return true;
    } else {
      return false;
    }
  }

  public boolean isBigFile(String path) {
    if (StringUtils.isNotEmpty(path)) {
      File file = new File(path);
      long tamanio = file.length() / 1024;
      if (tamanio > 8000 && bigDataServiceActive) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  public byte[] getXmlBytesFromTipoDocumento(TipoDocumento tipoDocumento)
      throws XPathExpressionException, JAXBException, ParserConfigurationException, SAXException,
      IOException, TransformerException, XMLStreamException {
    byte[] valorBinario = tipoDocumento.getContenido().getValorBinario();
    tipoDocumento.getContenido().setValorBinario(null);

    JAXBMarshallerDocument marshaller = new JAXBMarshallerDocument();
    String docMarshall = marshaller.marshallDataDocument(tipoDocumento,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e", "documento", "enidoc");

    String searchString = "<contenido Id=\"CONTENIDO_DOCUMENTO\">";
    StringBuilder stringReplace = new StringBuilder(searchString);
    stringReplace.append("<ValorBinario>");
    stringReplace.append(Base64.encode(valorBinario));
    stringReplace.append("</ValorBinario>");
    docMarshall = StringUtils.replace(docMarshall, searchString, stringReplace.toString());

    return docMarshall.getBytes(XMLUtils.UTF8_CHARSET);
  }

  public static TipoDocumento getTipoDocumentoFromXmlFile(byte[] contenido,
      ObjetoDocumentoInside documento) throws JAXBException, ParserConfigurationException,
      SAXException, IOException, TransformerFactoryConfigurationError, TransformerException {

    String contentString = new String(contenido);
    int initialPos = contentString.indexOf("<ValorBinario>");
    int finalPos = contentString.indexOf("</ValorBinario>") + 15;
    StringBuilder contentBuild = new StringBuilder(contentString.substring(0, initialPos));
    contentBuild.append(contentString.substring(finalPos, contentString.length()));
    String content = contentString.substring(initialPos + 14, finalPos - 15);
    String mime = documento.getContenido().getMime();

    JAXBMarshallerDocument marshaller = new JAXBMarshallerDocument();
    TipoDocumento tipoDocumento =
        marshaller.unmarshallDataDocument(contentBuild.toString().getBytes(XMLUtils.UTF8_CHARSET));

    tipoDocumento.getContenido().setValorBinario(Base64.decode(content));
    tipoDocumento.getContenido().setNombreFormato(mime);

    return tipoDocumento;
  }

}
