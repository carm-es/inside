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

package es.mpt.dsic.inside.service.validacionENI.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import javax.xml.validation.ValidatorHandler;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import javax.xml.xpath.XPathFactoryConfigurationException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.xerces.dom.DeferredElementImpl;
import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XMLUtil {

  protected static final Log logger = LogFactory.getLog(XMLUtil.class);

  public static final String KEY_MAPA_LISTAEXPEDIENTE = "listaExpediente";
  public static final String KEY_MAPA_LISTADOCUMENTO = "listaDocumento";

  final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

  // EXPEDIENTE
  final static String NAMESPACE_EXPEDIENTE =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e";
  final static String TAG_EXPEDIENTE = "expediente";

  // DOCUMENTO
  final static String NAMESPACE_DOCUMENTO =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e";
  final static String TAG_DOCUMENTO = "documento";

  final static String NAMESPACE_INDICE =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e";
  final static String TAG_INDICECONTENIDO = "IndiceContenido";

  // NAMESPACES POR AHORA OBLIGATORIOS PARA VALIDAR FIRMA DE EXPEDIENTEENI
  // PROCEDENTES DE ARCHIVE
  final static String NAMESPACE_INSIDEWS =
      "xmlns:insidews=https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService";
  final static String NAMESPACE_NS9 =
      "xmlns:ns9=https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e";
  final static String NAMESPACE_NS8 =
      "xmlns:ns8=https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales";

  public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

  /**
   * Obtiene el árbol XML de una fuente, que puede ser File, InputStream o array de bytes.
   * 
   * @param source fuente de la que se quiere obtener el árbol XML.
   * @return árbol XML.
   * @throws ParserConfigurationException
   * @throws SAXException
   * @throws IOException
   */
  public static Document getDOMDocument(Object source, boolean namespaceAware)
      throws ParserConfigurationException, SAXException, IOException {
    logger.debug("getDOMDocument init");
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    dbf.setNamespaceAware(namespaceAware);
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document doc = null;
    if (source instanceof File) {
      File f = (File) source;
      doc = db.parse(f);
    } else if (source instanceof InputStream) {
      InputStream is = (InputStream) source;
      doc = db.parse(is);
    } else if (source instanceof byte[]) {
      byte[] bytes = (byte[]) source;
      ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
      doc = db.parse(bis);
    }
    logger.debug("getDOMDocument end");
    return doc;

  }

  /**
   * Comprueba si se cumplen una serie de expresiones xpath.
   * 
   * @param doc Documento DOM
   * @param xpathExpressions array de expresiones xpath.
   * @param xpath instancia de xpath
   * @return true si se cumplen todas, false en caso contrario.
   * @throws XPathExpressionException si alguna de las expresiones no es correcta.
   */
  public static boolean seCumplen(Document doc, String[] xpathExpressions,
      javax.xml.xpath.XPath xpath) throws XPathExpressionException {
    Boolean seCumplen = true;

    int i = 0;

    while (i < xpathExpressions.length && seCumplen) {
      String expr = xpathExpressions[i];
      seCumplen = (Boolean) xpath.evaluate(expr, doc.getDocumentElement(),
          javax.xml.xpath.XPathConstants.BOOLEAN);
      xpath.reset();
      i++;
    }

    return seCumplen;
  }

  public static NodeList getNodeListByXpathExpression(Object obj, String xpathExpression)
      throws XPathFactoryConfigurationException, XPathExpressionException {

    javax.xml.xpath.XPath xpath =
        javax.xml.xpath.XPathFactory.newInstance("http://java.sun.com/jaxp/xpath/dom").newXPath();

    NodeList nodeList =
        (NodeList) xpath.evaluate(xpathExpression, obj, javax.xml.xpath.XPathConstants.NODESET);

    return nodeList;

  }

  public static Node getNodeByXpathExpression(Document doc, String xpathExpression)
      throws XPathFactoryConfigurationException, XPathExpressionException {

    javax.xml.xpath.XPath xpath =
        javax.xml.xpath.XPathFactory.newInstance("http://java.sun.com/jaxp/xpath/dom").newXPath();

    Node node = (Node) xpath.evaluate(xpathExpression, doc, javax.xml.xpath.XPathConstants.NODE);

    return node;
  }

  /**
   * Comprueba si un nodo tiene un atributo "MimeType" que contenga la cadena "hash"
   * 
   * @param node
   * @return
   */
  public static boolean isHashMimeType(Node node) {
    boolean ret = false;
    if (node.getAttributes() != null && node.getAttributes().getNamedItem("MimeType") != null
        && node.getAttributes().getNamedItem("MimeType").getTextContent() != null
        && node.getAttributes().getNamedItem("MimeType").getTextContent().contains("hash")) {
      ret = true;
    }
    return ret;
  }

  /**
   * Devuelve el valor del atributo "Encoding" del nodo.
   * 
   * @param node
   * @return
   */
  public static String getEncoding(Node node) {
    String encoding = null;
    encoding = getAttribute(node, "Encoding");
    if (encoding == null) {
      encoding = getAttribute(node, "encoding");
    }
    return encoding;
  }

  public static String getHashAlgorithm(Node node) {
    return getAttribute(node, "hashAlgorithm");
  }

  /*
   * public static String getEncoding (Node node) { return getAttribute(node, "Encoding"); }
   */

  public static String getAttribute(Node node, String attName) {
    String attValue = null;

    if (node.getAttributes() != null && node.getAttributes().getNamedItem(attName) != null) {
      attValue = node.getAttributes().getNamedItem(attName).getTextContent();
    }

    return attValue;
  }

  public static boolean isXMLMimeType(Node node) {
    String mime = null;
    boolean ret = false;

    if (node.getAttributes() != null && node.getAttributes().getNamedItem("MimeType") != null) {
      mime = node.getAttributes().getNamedItem("MimeType").getTextContent();
    }

    if ("text/xml".equalsIgnoreCase(mime) || "application/xml".equalsIgnoreCase(mime)) {
      ret = true;
    }

    return ret;
  }

  /**
   * Convierte un documento XML de DOM a un ByteArrayOutputStream.
   * 
   * @param doc
   * @return
   * @throws UnsupportedEncodingException
   * @throws TransformerException
   */
  public static ByteArrayOutputStream getBytesFromNode(Node node, String encoding)
      throws UnsupportedEncodingException, TransformerException {
    /*
     * TransformerFactory tf = TransformerFactory.newInstance(); Transformer t =
     * tf.newTransformer();
     * 
     * t.setOutputProperty(OutputKeys.INDENT, "yes"); t.setOutputProperty(OutputKeys.ENCODING,
     * encoding); t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
     */

    Transformer t = getGenericTransformer("yes", encoding, "2");

    ByteArrayOutputStream bout = new ByteArrayOutputStream();
    t.transform(new DOMSource(node), new StreamResult(new OutputStreamWriter(bout, encoding)));

    return bout;
  }

  public static String getStringFromNode(Node node, String encoding) throws TransformerException {
    /*
     * TransformerFactory tf = TransformerFactory.newInstance(); Transformer t =
     * tf.newTransformer();
     * 
     * t.setOutputProperty(OutputKeys.INDENT, "yes"); t.setOutputProperty(OutputKeys.ENCODING,
     * encoding); t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
     */

    Transformer t = getGenericTransformer("yes", encoding, "2");
    StringWriter sw = new StringWriter();

    t.transform(new DOMSource(node), new StreamResult(sw));
    return sw.toString();
  }

  private static Transformer getGenericTransformer(String indent, String encoding,
      String indent_amount) throws TransformerConfigurationException {
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer t = tf.newTransformer();

    t.setOutputProperty(OutputKeys.INDENT, indent);
    t.setOutputProperty(OutputKeys.ENCODING, encoding);
    t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", indent_amount);

    return t;

  }

  /**
   * Elimina los nodos de un documento que cumplan una determinada expresión XPATH
   * 
   * @param xpathExpr
   * @param doc
   * @throws XPathFactoryConfigurationException
   * @throws XPathExpressionException
   */
  public static void removeNodesFromDocument(String xpathExpr, Document doc)
      throws XPathFactoryConfigurationException, XPathExpressionException {
    NodeList nodes = XMLUtil.getNodeListByXpathExpression(doc, xpathExpr);

    // Los eliminamos
    if (nodes != null) {
      for (int i = 0; i < nodes.getLength(); i++) {
        Node n = nodes.item(i);
        n.getParentNode().removeChild(n);
      }
    }

    doc.normalize();
  }

  /**
   * Comprueba que un nodo XML tenga el atributo Id y el atributo Encoding con valor Base64.
   * 
   * @param nodo
   * @return
   */
  public static boolean contieneIdEncoding(Node nodo) {

    NamedNodeMap atributos = nodo.getAttributes();

    // Comprobamos que tenga el atributo Id y el atributo Encoding:
    int i = 0;
    boolean encontrados = false;
    boolean idFound = false;
    boolean encodingFound = false;

    while (atributos != null && i < atributos.getLength() && !encontrados) {
      if (atributos.item(i).getNodeName().equalsIgnoreCase("id")) {
        idFound = true;
      } else if (atributos.item(i).getNodeName().equalsIgnoreCase("encoding")
          && atributos.item(i).getNodeValue().contains("base64")) {
        encodingFound = true;
      }

      encontrados = idFound && encodingFound;
      i++;
    }

    return encontrados;
  }

  public static Source[] getSchemasSources(String dir) {

    File f = new File(dir);

    List<File> filesIn = FileUtil.getFilesInFolder(f, ".xsd");

    Source[] schemasSources = new Source[filesIn.size()];
    int i = 0;
    for (File schema : filesIn) {
      schemasSources[i] = new StreamSource(schema);
      i++;
    }

    return schemasSources;
  }

  public static XMLReader createParserForValidation(Source[] schemasSources)
      throws SAXException, IOException {

    SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA);

    if (schemasSources.length == 0) {
      throw new IOException("No se han proporcionado los schemas");
    }

    Schema schemaGrammar = schemaFactory.newSchema(schemasSources);

    Validator schemaValidator = schemaGrammar.newValidator();
    ValidatorHandler vHandler = schemaGrammar.newValidatorHandler();

    DefaultHandler validationHandler = new DefaultHandler();
    schemaValidator.setErrorHandler(validationHandler);

    ContentHandler cHandler = validationHandler;
    vHandler.setContentHandler(cHandler);

    XMLReader parser = XMLReaderFactory.createXMLReader();

    parser.setContentHandler(vHandler);

    return parser;
  }

  public static List<String> getNameSpacesNodoROOT(String xml)
      throws ParserConfigurationException, SAXException, IOException {
    org.w3c.dom.Node nodoPadre = XMLUtil.getNode(xml.getBytes(XMLUtil.UTF8_CHARSET), "*");// *
                                                                                          // recoge
                                                                                          // el
                                                                                          // primer
                                                                                          // nodo

    List<String> listaNameSpaces = new ArrayList<String>();
    for (int i = 0; i < nodoPadre.getAttributes().getLength(); i++) {
      org.w3c.dom.Node nodoTemp = nodoPadre.getAttributes().item(i);
      String nameSpace = nodoTemp.getNodeName() + "=" + nodoTemp.getNodeValue();
      listaNameSpaces.add(nameSpace);
    }
    return listaNameSpaces;
  }

  public static List<String> getNameSpacesDelHijoDelROOT(String xml)
      throws ParserConfigurationException, SAXException, IOException {
    org.w3c.dom.Node nodoPadre = XMLUtil.getNode(xml.getBytes(XMLUtil.UTF8_CHARSET), "*");// *
                                                                                          // recoge
                                                                                          // el
                                                                                          // primer
                                                                                          // nodo
    org.w3c.dom.Node nodoPrimerHijo = nodoPadre.getFirstChild();

    List<String> listaNameSpaces = new ArrayList<String>();
    for (int i = 0; i < nodoPrimerHijo.getAttributes().getLength(); i++) {
      org.w3c.dom.Node nodoTemp = nodoPrimerHijo.getAttributes().item(i);
      String nameSpace = nodoTemp.getNodeName() + "=" + nodoTemp.getNodeValue();
      listaNameSpaces.add(nameSpace);
    }
    return listaNameSpaces;
  }

  public static String addNameSpacesToExpedienteENIXML(Node nodoEni, List<String> listaNameSpaces)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    Element nodoEniElem = (Element) nodoEni;
    for (int i = 0; i < listaNameSpaces.size(); i++) {
      nodoEniElem.setAttribute(listaNameSpaces.get(i).split("=")[0],
          listaNameSpaces.get(i).split("=")[1]);
    }

    if (!listaNameSpaces.contains(NAMESPACE_INSIDEWS))
      nodoEniElem.setAttribute(NAMESPACE_INSIDEWS.split("=")[0], NAMESPACE_INSIDEWS.split("=")[1]);

    if (!listaNameSpaces.contains(NAMESPACE_NS8))
      nodoEniElem.setAttribute(NAMESPACE_NS8.split("=")[0], NAMESPACE_NS8.split("=")[1]);

    if (!listaNameSpaces.contains(NAMESPACE_NS9))
      nodoEniElem.setAttribute(NAMESPACE_NS9.split("=")[0], NAMESPACE_NS9.split("=")[1]);

    return nodeToString(nodoEni);
  }

  public static String deleteNameSpacesToExpedienteENIXML(Node nodoEni,
      List<String> listaNameSpaces) throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {

    Element nodoEniElem = (Element) nodoEni;
    nodoEniElem.removeAttribute(NAMESPACE_INSIDEWS.split("=")[0]);
    nodoEniElem.removeAttribute(NAMESPACE_NS8.split("=")[0]);
    nodoEniElem.removeAttribute(NAMESPACE_NS9.split("=")[0]);

    return nodeToString(nodoEni);
  }

  private static String buscarPrefijoNodoNAMESPACE(List<String> listaNameSpaces,
      String NameSpaceABuscar) {
    String prefijo = "";

    for (int i = 0; i < listaNameSpaces.size(); i++) {

      if (listaNameSpaces.get(i).split("=")[1].equalsIgnoreCase(NameSpaceABuscar)) {
        // obtiene la/s parte/s del prefijo y se queda si tiene dos
        // partes con la segunda y si tiene una parte con esa.
        String[] arrayAux = listaNameSpaces.get(i).split("=")[0].split(":");
        String aux = arrayAux.length > 1 ? arrayAux[1] : arrayAux[0];

        if (!aux.equalsIgnoreCase("schemaLocation"))
          prefijo = aux + ":";// le a�ado el dos puntos
      }

    }

    return prefijo;
  }

  /* TIENE QUE EXISTIR SI O SI EN EL PRIMER NODO Y SI NO EN EL SEGUNDO NODO */
  public static String obtenerExpedienteENIXML(String stringXMLExpediente)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    // busca el prefijo correspondiente al nodo expediente eni
    String prefijo = "";

    List<String> listaNameSpaces = XMLUtil.getNameSpacesNodoROOT(stringXMLExpediente);

    prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    // es quer no lo ha encontrado en el nodo root busca en el siguiente
    if ("".equals(prefijo)) {
      listaNameSpaces.clear();
      listaNameSpaces = XMLUtil.getNameSpacesDelHijoDelROOT(stringXMLExpediente);

      prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    }

    return XMLUtil.addNameSpacesToExpedienteENIXML(XMLUtil
        .getNode(stringXMLExpediente.getBytes(XMLUtil.UTF8_CHARSET), prefijo + TAG_EXPEDIENTE),
        listaNameSpaces);

  }

  public static String obtenerExpedienteENIXMLNoProcedenteInside(String stringXMLExpediente)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    String prefijo = "";

    List<String> listaNameSpaces = XMLUtil.getNameSpacesNodoROOT(stringXMLExpediente);

    prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    // es quer no lo ha encontrado en el nodo root busca en el siguiente
    if ("".equals(prefijo)) {
      listaNameSpaces.clear();
      listaNameSpaces = XMLUtil.getNameSpacesDelHijoDelROOT(stringXMLExpediente);

      prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    }

    return nodeToString(XMLUtil.getNode(stringXMLExpediente.getBytes(XMLUtil.UTF8_CHARSET),
        prefijo + TAG_EXPEDIENTE));

  }

  public static String obtenerExpedienteENIXMLDELETENAMESPACES(String stringXMLExpediente)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    // busca el prefijo correspondiente al nodo expediente eni
    String prefijo = "";

    List<String> listaNameSpaces = XMLUtil.getNameSpacesNodoROOT(stringXMLExpediente);

    prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    // es quer no lo ha encontrado en el nodo root busca en el siguiente
    if ("".equals(prefijo)) {
      listaNameSpaces.clear();
      listaNameSpaces = XMLUtil.getNameSpacesDelHijoDelROOT(stringXMLExpediente);

      prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    }

    return XMLUtil.deleteNameSpacesToExpedienteENIXML(XMLUtil
        .getNode(stringXMLExpediente.getBytes(XMLUtil.UTF8_CHARSET), prefijo + TAG_EXPEDIENTE),
        listaNameSpaces);

  }

  public static org.w3c.dom.Node obtenerIndiceContenidoExpediente(String stringXMLExpediente)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {

    List<String> listaNameSpaces = XMLUtil.getNameSpacesNodoROOT(stringXMLExpediente);

    // busca el prefijo correspondiente al nodo indicecontenido
    String prefijo = "";
    for (int i = 0; i < listaNameSpaces.size(); i++) {

      if (listaNameSpaces.get(i).split("=")[1].equalsIgnoreCase(NAMESPACE_INDICE)) {
        prefijo = listaNameSpaces.get(i).split("=")[0].split(":")[1] + ":";// le
                                                                           // a�ado
                                                                           // el
                                                                           // dos
                                                                           // puntos
      }

    }

    return XMLUtil.getNode(stringXMLExpediente.getBytes(XMLUtil.UTF8_CHARSET),
        prefijo + TAG_INDICECONTENIDO);

  }

  public static org.w3c.dom.Node obtenerNodoByNamespaceAndTag(String stringXMLExpediente,
      String nameSpace, String tag) throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {

    List<String> listaNameSpaces = XMLUtil.getNameSpacesNodoROOT(stringXMLExpediente);

    // busca el prefijo correspondiente al nodo indicecontenido
    String prefijo = "";
    for (int i = 0; i < listaNameSpaces.size(); i++) {

      if (listaNameSpaces.get(i).split("=")[1].equalsIgnoreCase(nameSpace)) {
        prefijo = listaNameSpaces.get(i).split("=")[0].split(":")[1] + ":";// le
                                                                           // a�ado
                                                                           // el
                                                                           // dos
                                                                           // puntos
      }

    }

    return XMLUtil.getNode(stringXMLExpediente.getBytes(XMLUtil.UTF8_CHARSET), prefijo + tag);

  }

  public static String obtenerDocumentoENIXML(String stringXMLDocumento)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {

    List<String> listaNameSpaces = XMLUtil.getNameSpacesNodoROOT(stringXMLDocumento);

    // busca el prefijo correspondiente al nodo expediente eni
    String prefijo = "";

    prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_DOCUMENTO);

    // es quer no lo ha encontrado en el nodo root busca en el siguiente
    if ("".equals(prefijo)) {
      listaNameSpaces.clear();
      listaNameSpaces = XMLUtil.getNameSpacesDelHijoDelROOT(stringXMLDocumento);

      prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_DOCUMENTO);

    }

    return XMLUtil.addNameSpacesToExpedienteENIXML(
        XMLUtil.getNode(stringXMLDocumento.getBytes(XMLUtil.UTF8_CHARSET), prefijo + TAG_DOCUMENTO),
        listaNameSpaces);

  }

  public static Node getNode(byte[] xml, String tag)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document dom = db.parse(new ByteArrayInputStream(xml));
    Node nodo = null;

    // El * es comodin e indicamos que coja el primer nodo y recoge el
    // nombre y se lo asigna al tag.
    if ("*".equals(tag)) {
      tag = dom.getDocumentElement().getNodeName();

    }

    nodo = ((Node) dom.getElementsByTagName(tag).item(0));
    return nodo;
  }

  public static String nodeToString(Node node)
      throws TransformerFactoryConfigurationError, TransformerException {
    Transformer transformer = TransformerFactory.newInstance().newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    Source source = new DOMSource(node);
    StringWriter sw = new StringWriter();
    StreamResult result = new StreamResult(sw);
    transformer.transform(source, result);
    return sw.toString();
  }

  public static String getNodeContentSignature(String expression, byte[] data,
      boolean ommitDeclaration) throws ParserConfigurationException, SAXException, IOException,
      XPathExpressionException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new ByteArrayInputStream(data));
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();

    XPathExpression exprFirst = xpath.compile(expression);
    DeferredElementImpl nodeParent =
        (DeferredElementImpl) exprFirst.evaluate(doc, XPathConstants.NODE);

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    if (ommitDeclaration) {
      transformer.setOutputProperty("omit-xml-declaration", "yes");
    }
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    DOMSource source = new DOMSource(nodeParent);

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    StreamResult result = new StreamResult(baos);
    transformer.transform(source, result);
    return result.getOutputStream().toString();
  }

  public static String getContentNode(byte[] data, String path) throws Exception {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new ByteArrayInputStream(data));
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();

    XPathExpression exprFirst = xpath.compile(path);

    return (String) exprFirst.evaluate(doc, XPathConstants.STRING);

  }

  public static List<String> getContentNodeList(byte[] data, String path) throws Exception {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new ByteArrayInputStream(data));
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();

    XPathExpression exprFirst = xpath.compile(path);

    NodeList listaNodos = (NodeList) exprFirst.evaluate(doc, XPathConstants.NODESET);

    List<String> listaValores = new ArrayList<String>();
    for (int i = 0; i < listaNodos.getLength(); i++) {
      Node nodo = listaNodos.item(i);
      listaValores.add(((Node) nodo.getChildNodes().item(0)).getNodeValue());
    }
    return listaValores;

  }

  public static String getvalorNodoDatosXML(String dataXml, String expresionXPath)
      throws Exception {
    return XMLUtil.getContentNode(dataXml.getBytes(UTF8_CHARSET), expresionXPath);

  }

  public static String getvalorNodoDatosXMLByTag(String dataXml, String tag) throws Exception {
    org.w3c.dom.Node nodo_Tag = XMLUtil.getNode(dataXml.getBytes(XMLUtil.UTF8_CHARSET), tag);
    Element nodoElem = (Element) nodo_Tag;
    return XMLUtil.getCharacterDataFromElement(nodoElem);

  }

  public static String getCharacterDataFromElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      return cd.getData();
    }
    return "";
  }

  public static String decodeUTF8(byte[] bytes) {
    return new String(bytes, UTF8_CHARSET);
  }

  public static byte[] encodeUTF8(String string) {
    return string.getBytes(UTF8_CHARSET);
  }

  public static boolean getNodeRoot(byte[] xml)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document dom = db.parse(new ByteArrayInputStream(xml));

    // Indicamos que coja el primer nodo y recoge el nombre y se lo asigna
    // al tag.
    return dom.getDocumentElement().getNodeName().contains("Expediente");

  }

  public static Node getNodoByXpathExpresion(String expression, byte[] data)
      throws ParserConfigurationException, SAXException, IOException, XPathExpressionException,
      TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new ByteArrayInputStream(data));
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();

    XPathExpression exprFirst = xpath.compile(expression);
    DeferredElementImpl nodeParent =
        (DeferredElementImpl) exprFirst.evaluate(doc, XPathConstants.NODE);
    return nodeParent;
  }

  /*
   * devuelve true si existe el nodo con el tag tagNodo del parametro false si no existe
   */
  public static boolean isNodoBuscadoENI(byte[] ficheroEniBytes, String tagNodo) {
    boolean isExpediente = true;

    String expresionNodoBuscado = "//*[local-name()='" + tagNodo + "']";

    try {
      Node nodo = getNodoByXpathExpresion(expresionNodoBuscado, ficheroEniBytes);
      String nameNodo = nodo.getNodeName();
      logger.debug("Nombre nodo encontrado en isNodoBuscadoENI: " + nameNodo);
      getContentNodeList(ficheroEniBytes, "//*[local-name()='IdentificadorDocumento']");

    } catch (Exception e) {
      isExpediente = false;
    }

    return isExpediente;
  }

  /*
   * devuelve la lista de los valores de un nodo (tagNodo) que puede estar varias veces
   * 
   */
  public static List<String> listaValoresNodo(byte[] ficheroEniBytes, String tagNodo) {
    List<String> listaValores;

    String expresionNodoBuscado = "//*[local-name()='" + tagNodo + "']";

    try {
      listaValores = getContentNodeList(ficheroEniBytes, expresionNodoBuscado);

    } catch (Exception e) {
      logger.error("ERROR en listaValoresNodo al obtener la lista de valores de la etiqueta nodo: "
          + tagNodo);
      listaValores = new ArrayList<String>();
    }

    return listaValores;
  }

  public static HashMap<String, List<byte[]>> unZipFileExpYDocs(byte[] expAndDocsZIPBase64)
      throws Exception {

    byte[] buffer = new byte[1024];
    ArrayList<byte[]> listaExpediente = new ArrayList<byte[]>();
    ArrayList<byte[]> listaDocumento = new ArrayList<byte[]>();

    HashMap<String, List<byte[]>> expYDocs = new HashMap<String, List<byte[]>>();

    try {
      logger.debug("unZipFile inicio");

      ZipInputStream zis = new ZipInputStream(new ByteArrayInputStream(expAndDocsZIPBase64));
      ZipEntry ze = zis.getNextEntry();

      while (ze != null) {
        ByteArrayOutputStream arrayBytesFichero = new ByteArrayOutputStream();

        int len;
        while ((len = zis.read(buffer)) > 0) {
          arrayBytesFichero.write(buffer, 0, len);
        }

        arrayBytesFichero.close();

        System.out.println(ze.getName());
        logger.debug("Nombre ficheroENI encontrado en el ZIP: " + ze.getName());

        if (isNodoBuscadoENI(arrayBytesFichero.toByteArray(), TAG_EXPEDIENTE)) {
          listaExpediente.add(arrayBytesFichero.toByteArray());
        } else {
          listaDocumento.add(arrayBytesFichero.toByteArray());
        }

        ze = zis.getNextEntry();
      }

      zis.closeEntry();
      zis.close();

      if (listaExpediente.size() < 1)
        throw new Exception("No se ha detectado fichero expedienteENI en el zip");

      expYDocs.put(KEY_MAPA_LISTAEXPEDIENTE, listaExpediente);
      expYDocs.put(KEY_MAPA_LISTADOCUMENTO, listaDocumento);

      logger.debug("unZipFile fin");

      return expYDocs;

    } catch (IOException ex) {
      ex.printStackTrace();
      logger.error("Error al procesar el fichero zip en unZipFileExpYDocs. " + ex.getMessage());
      return expYDocs;
    }

  }

}
