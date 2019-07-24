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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
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
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.xerces.dom.DeferredElementImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;



public class XMLUtils {

  final static String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

  public final static String RUTA_XPATH_FIRMABASE64 =
      "expediente/indice/firmas/firma/ContenidoFirma/FirmaConCertificado/FirmaBase64";

  public static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

  // EXPEDIENTE
  final static String NAMESPACE_EXPEDIENTE =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e";


  // DOCUMENTO
  final static String NAMESPACE_DOCUMENTO =
      "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e";


  public static byte[] deFirmaBase64_A_DSSignature(byte[] data) throws IOException {
    String nodoSignature = "";
    String nodoFirmaBase64 = "";

    String expresionSignature = "//*[local-name()='Signature']";
    try {
      nodoSignature =
          es.mpt.dsic.inside.service.util.XMLUtils.signatureString(expresionSignature, data);
    } catch (Exception e) {
      throw new IOException(e.getMessage());
    }


    // si ya viene con el nodo ds:signature no hacer nada
    if ("".equals(nodoSignature)) {
      // sacar la firma decodificarla e incluirla
      String dataB64 = new String(data);

      String expresionFirmaBase64 = "//*[local-name()='FirmaBase64']";
      try {
        nodoFirmaBase64 =
            es.mpt.dsic.inside.service.util.XMLUtils.signatureString(expresionFirmaBase64, data);
      } catch (Exception e) {
        throw new IOException(e.getMessage());
      }


      String contenidoNodoFirmaBase64;
      try {
        contenidoNodoFirmaBase64 =
            es.mpt.dsic.inside.service.util.XMLUtils.getNodoValue(expresionFirmaBase64, data);
      } catch (Exception e) {
        throw new IOException(e.getMessage());
      }


      byte[] firmaDecode = Base64.decodeBase64(contenidoNodoFirmaBase64.getBytes("UTF-8"));
      String firmaDsSignature = new String(firmaDecode);

      // nos puede llegar varias veces codificado en base64. lo vamos decodificando
      while (Base64.isBase64(firmaDsSignature)) {
        byte[] uncodificadoB64 = Base64.decodeBase64(
            firmaDsSignature.getBytes(es.mpt.dsic.inside.service.util.XMLUtils.UTF8_CHARSET));
        firmaDsSignature = new String(uncodificadoB64);

      }



      /*
       * esto esta porque si nos envian en firmabase64 una firma internally detached pasarla sin
       * cambiar nada por ejemplo los expedientes de junta andalucia
       */

      String nodoAfirma = "";
      try {
        org.w3c.dom.Node nodoPadre =
            XMLUtils.getNode(firmaDsSignature.getBytes(XMLUtils.UTF8_CHARSET), "*");
        String[] partes = nodoPadre.getNodeName().split(":");

        if (partes.length > 1)
          nodoAfirma = partes[1];
        else
          nodoAfirma = partes[0];


      } catch (Exception e) {
        // se comenta porque en caso de expediente con firma cades attached implicit debe devolverse
        // el exp tal cual para validar
        // y no saltar esta excepcion que interrumpe el metodo
        System.out.println(
            "AVISO!!!!!!!! XMLUtils.deFirmaBase64_A_DSSignature: se comenta porque en caso de expediente con firma cades attached implicit debe devolverse el exp tal cual para validar");
        // throw new IOException(e.getMessage());
      }

      if ("Signature".equals(nodoAfirma)) {
        dataB64 = dataB64.replace(nodoFirmaBase64, firmaDsSignature);
      }


      return dataB64.getBytes("UTF-8");

    } else
      return data;

  }



  /**
   * Obtiene un Source por cada schema que haya dentro del directorio "dir"
   * 
   * @param dir
   * @return
   */
  public static Source[] getSchemasSources(String dir) {

    File f = new File(dir);

    List<File> filesIn = FileUtils.getFilesInFolder(f, ".xsd");

    Source[] schemasSources = new Source[filesIn.size()];
    int i = 0;
    for (File schema : filesIn) {
      schemasSources[i] = new StreamSource(schema);
      i++;
    }

    return schemasSources;
  }

  /**
   * Crea un parseador para validar contra unos schemas pasados como parámetro
   * 
   * @param schemasSources schemas contra los que se desea validar
   * @return El parseador
   * @throws SAXException Cuando no puedan parsearse los schemas
   * @throws IOException Cuando no se proporcionen los schemas
   */
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

  public static String getNode(String xml, String tag)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document dom = db.parse(new ByteArrayInputStream(xml.getBytes()));

    Node nodo = (dom.getElementsByTagName(tag).item(0));
    return nodo.getTextContent();
  }

  public static Node getNode(byte[] xml, String tag)
      throws ParserConfigurationException, SAXException, IOException {
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document dom = db.parse(new ByteArrayInputStream(xml));
    Node nodo = (dom.getElementsByTagName(tag).item(0));
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

  // public static byte[] documentoAdicionalToEni(byte[] adicional) throws
  // ParserConfigurationException, SAXException, IOException,
  // TransformerFactoryConfigurationError, TransformerException {
  // Node nodoEni = XMLUtils.getNode(adicional, "ns5:documento");
  // Element nodoEniElem = (Element) nodoEni;
  // nodoEniElem.setAttribute("xmlns:ns2",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos");
  // nodoEniElem.setAttribute("xmlns:ns3",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
  // nodoEniElem.setAttribute("xmlns:ns4", "http://www.w3.org/2000/09/xmldsig#");
  // nodoEniElem.setAttribute("xmlns:ns5",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
  // nodoEniElem.setAttribute("xmlns:ns6",
  // "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
  // nodoEniElem.setAttribute("xmlns:ns7",
  // "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
  // nodoEniElem.setAttribute("xmlns:insidews",
  // "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
  // nodoEniElem.setAttribute("xmlns",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
  // nodoEniElem.setAttribute("xmlns:enidoc",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
  // return XMLUtils.nodeToString(nodoEni).getBytes();
  // }


  // public static byte[] expedienteAdicionalToEni(byte[] adicional) throws
  // ParserConfigurationException, SAXException,
  // IOException, TransformerFactoryConfigurationError, TransformerException {
  // Node nodoEni = XMLUtils.getNode(adicional, "ns7:expediente");
  // Element nodoEniElem = (Element) nodoEni;
  // nodoEniElem.setAttribute("xmlns:ns2",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e");
  // nodoEniElem.setAttribute("xmlns:ns3",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
  // nodoEniElem.setAttribute("xmlns:ns4", "http://www.w3.org/2000/09/xmldsig#");
  // nodoEniElem.setAttribute("xmlns:ns5",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos");
  // nodoEniElem.setAttribute("xmlns:ns6",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
  // nodoEniElem.setAttribute("xmlns:ns7",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e");
  // nodoEniElem.setAttribute("xmlns:ns8",
  // "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
  // nodoEniElem.setAttribute("xmlns:insidews",
  // "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService");
  // nodoEniElem.setAttribute("xmlns",
  // "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido");
  // nodoEniElem.setAttribute("xmlns:insidews",
  // "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService");
  // return XMLUtils.nodeToString(nodoEni).getBytes();
  // }



  public static byte[] documentoAdicionalToEni(byte[] adicional)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {


    // calculamos el prefijo del primer nodo
    String prefijoDocENI = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
    Node nodoEni = null;
    if (prefijoDocENI != null && !prefijoDocENI.trim().equals("")) {
      String pref = prefijoDocENI.split(":")[1];
      nodoEni = getNode(adicional, pref + ":documento");
    } else {
      nodoEni = getNode(adicional, "ns5:documento");
    }


    Element nodoEniElem = (Element) nodoEni;


    // recorre los namespaces para recoger los prefijos que utiliza en el xml
    String prefijo1 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos");
    String prefijo2 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
    String prefijo3 =
        prefijoNamespaceExpediente(new String(adicional), "http://www.w3.org/2000/09/xmldsig#");
    String prefijo4 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
    String prefijo5 = prefijoNamespaceExpediente(new String(adicional),
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
    String prefijo6 = prefijoNamespaceExpediente(new String(adicional),
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
    String prefijo7 = prefijoNamespaceExpediente(new String(adicional),
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
    String prefijo8 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
    String prefijo9 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");



    nodoEniElem.setAttribute("xmlns" + prefijo1,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos");
    nodoEniElem.setAttribute("xmlns" + prefijo2,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
    nodoEniElem.setAttribute("xmlns" + prefijo3, "http://www.w3.org/2000/09/xmldsig#");
    nodoEniElem.setAttribute("xmlns" + prefijo4,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
    nodoEniElem.setAttribute("xmlns" + prefijo5,
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
    nodoEniElem.setAttribute("xmlns" + prefijo6,
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
    nodoEniElem.setAttribute("xmlns:insidews",
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
    nodoEniElem.setAttribute("xmlns" + prefijo8,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
    nodoEniElem.setAttribute("xmlns:enidoc",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");



    return XMLUtils.nodeToString(nodoEni).getBytes();
  }



  public static byte[] expedienteAdicionalToEni(byte[] adicional)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {

    // calculamos el prefijo del primer nodo
    String prefijoEXpENI = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e");
    Node nodoEni = null;
    if (prefijoEXpENI != null && !prefijoEXpENI.trim().equals("")) {
      String pref = prefijoEXpENI.split(":")[1];
      nodoEni = getNode(adicional, pref + ":expediente");
    } else {
      nodoEni = getNode(adicional, "ns7:expediente");
    }

    Element nodoEniElem = (Element) nodoEni;


    // recorre los namespaces para recoger los prefijos que utiliza en el xml
    String prefijo1 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e");
    String prefijo2 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
    String prefijo3 =
        prefijoNamespaceExpediente(new String(adicional), "http://www.w3.org/2000/09/xmldsig#");
    String prefijo4 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos");
    String prefijo5 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
    String prefijo6 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e");
    String prefijo7 = prefijoNamespaceExpediente(new String(adicional),
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
    String prefijo8 = prefijoNamespaceExpediente(new String(adicional),
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService");
    String prefijo9 = prefijoNamespaceExpediente(new String(adicional),
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido");
    String prefijo10 = prefijoNamespaceExpediente(new String(adicional),
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService");

    nodoEniElem.setAttribute("xmlns" + prefijo1,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e");
    nodoEniElem.setAttribute("xmlns" + prefijo2,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
    nodoEniElem.setAttribute("xmlns" + prefijo3, "http://www.w3.org/2000/09/xmldsig#");
    nodoEniElem.setAttribute("xmlns" + prefijo4,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos");
    nodoEniElem.setAttribute("xmlns" + prefijo5,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
    nodoEniElem.setAttribute("xmlns" + prefijo6,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e");
    nodoEniElem.setAttribute("xmlns" + prefijo7,
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
    nodoEniElem.setAttribute("xmlns:insidews",
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService");
    nodoEniElem.setAttribute("xmlns" + prefijo9,
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido");
    nodoEniElem.setAttribute("xmlns:insidews",
        "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService");

    return XMLUtils.nodeToString(nodoEni).getBytes();

  }



  public static byte[] objetoArchiveToEni(byte[] contenido, String etiqueta)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    Node nodoEni = XMLUtils.getNode(contenido, etiqueta);
    Element nodoEniElem = (Element) nodoEni;
    putAttributesArchive(nodoEniElem);
    return XMLUtils.nodeToString(nodoEni).getBytes();
  }

  private static void putAttributesArchive(Element nodoEniElem) {

    nodoEniElem.setAttribute("xmlns:archiveexp",
        "https://es.gob.archive/Archive/XSD/v1.0/expediente-e");
    nodoEniElem.setAttribute("xmlns:archivedoc",
        "https://es.gob.archive/Archive/XSD/v1.0/documento-e");
    nodoEniElem.setAttribute("xmlns:archivemeta",
        "https://es.gob.archive/Archive/XSD/v1.0/metadatos/adicionales");
    nodoEniElem.setAttribute("xmlns:ds", "http://www.w3.org/2000/09/xmldsig#");
    nodoEniElem.setAttribute("xmlns:eniconexpind",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido");
    nodoEniElem.setAttribute("xmlns:enidoc",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
    nodoEniElem.setAttribute("xmlns:enidocmeta",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos");
    nodoEniElem.setAttribute("xmlns:enids",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
    nodoEniElem.setAttribute("xmlns:eniexp",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e");
    nodoEniElem.setAttribute("xmlns:eniexpind",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e");
    nodoEniElem.setAttribute("xmlns:eniexpmeta",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos");
    nodoEniElem.setAttribute("xmlns:enifile",
        "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
  }

  public static String updateNode(String expression, String fileNas, String signedData)
      throws ParserConfigurationException, SAXException, IOException, XPathExpressionException,
      TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new ByteArrayInputStream(fileNas.getBytes()));
    XPathFactory xpf = XPathFactory.newInstance();
    XPath xpath = xpf.newXPath();

    XPathExpression exprFirst = xpath.compile(expression);
    DeferredElementImpl ultimaFirma =
        (DeferredElementImpl) exprFirst.evaluate(doc, XPathConstants.NODE);

    String retorno = fileNas;
    if (ultimaFirma != null) {
      ultimaFirma.setTextContent(signedData);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(doc);

      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      StreamResult result = new StreamResult(baos);
      transformer.transform(source, result);
      retorno = result.getOutputStream().toString();
    }

    return retorno;
  }

  public static String updateSignature(String expression, byte[] data, String ref,
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

    Node nodoReference = nodeParent.getElementsByTagName("ds:Reference").item(0);
    NamedNodeMap atributos = nodoReference.getAttributes();
    if (atributos != null && StringUtils.isNotBlank(ref)) {
      atributos.getNamedItem("URI").setTextContent(ref);
    }

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


  public static String signatureString(String expression, byte[] data)
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
    return nodeToString(nodeParent);
  }


  public static String getNodoValue(String expression, byte[] data) throws IOException {
    String valorNodo = null;

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new ByteArrayInputStream(data));
      XPathFactory xpf = XPathFactory.newInstance();
      XPath xpath = xpf.newXPath();

      XPathExpression exprFirst = xpath.compile(expression);
      DeferredElementImpl nodeParent =
          (DeferredElementImpl) exprFirst.evaluate(doc, XPathConstants.NODE);
      valorNodo = nodeParent.getFirstChild().getNodeValue();
    } catch (XPathExpressionException e) {
      throw new IOException("ERROR Excepcion: XPathExpressionException : " + e.getMessage());
    } catch (DOMException e) {
      throw new IOException("ERROR Excepcion: DOMException : " + e.getMessage());
    } catch (ParserConfigurationException e) {
      throw new IOException("ERROR Excepcion: ParserConfigurationException : " + e.getMessage());
    } catch (SAXException e) {
      throw new IOException("ERROR Excepcion: SAXException : " + e.getMessage());
    } catch (IOException e) {
      throw new IOException("ERROR Excepcion: IOException : " + e.getMessage());
    }

    return valorNodo;
  }


  public static String getNodoConEtiqueta(String expression, byte[] data) throws IOException {

    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new ByteArrayInputStream(data));
      XPathFactory xpf = XPathFactory.newInstance();
      XPath xpath = xpf.newXPath();

      XPathExpression exprFirst = xpath.compile(expression);
      DeferredElementImpl nodeParent =
          (DeferredElementImpl) exprFirst.evaluate(doc, XPathConstants.NODE);
      return nodeToString(nodeParent);

    } catch (XPathExpressionException e) {
      throw new IOException("ERROR Excepcion: XPathExpressionException : " + e.getMessage());
    } catch (ParserConfigurationException e) {
      throw new IOException("ERROR Excepcion: ParserConfigurationException : " + e.getMessage());
    } catch (SAXException e) {
      throw new IOException("ERROR Excepcion: SAXException : " + e.getMessage());
    } catch (IOException e) {
      throw new IOException("ERROR Excepcion: IOException : " + e.getMessage());
    } catch (TransformerFactoryConfigurationError e) {
      throw new IOException(
          "ERROR Excepcion: TransformerFactoryConfigurationError : " + e.getMessage());
    } catch (TransformerException e) {
      throw new IOException("ERROR Excepcion: TransformerException : " + e.getMessage());
    }



  }

  public static String incluirNamespacesParaValidarFirma(String expedienteENIString) {
    return expedienteENIString.replace("<ns7:expediente",
        "<ns7:expediente xmlns:insidews=\"https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService\" xmlns:ns9=\"https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e\" xmlns:ns8=\"https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales\"");
  }


  public static String construirExpedienteENIValido(String data, String dataConFirmaSinIdentar) {

    dataConFirmaSinIdentar = dataConFirmaSinIdentar
        .replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");

    String dataInicio = data.substring(0, data.indexOf("<ns7:expediente"));
    String dataFin =
        data.substring(data.indexOf("</ns7:expediente>") + "</ns7:expediente>".length());

    String dataFinal = dataInicio + dataConFirmaSinIdentar + dataFin;

    // aniadir los namespaces para que firma y validar valgan para expediente con y sin metadatos
    // adicionales
    return XMLUtils.incluirNamespacesParaValidarFirma(dataFinal);
  }



  public static List<String> getNameSpacesNodoROOT(String xml)
      throws ParserConfigurationException, SAXException, IOException {
    org.w3c.dom.Node nodoPadre = XMLUtils.getNode(xml.getBytes(XMLUtils.UTF8_CHARSET), "*");// *
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
    org.w3c.dom.Node nodoPadre = XMLUtils.getNode(xml.getBytes(XMLUtils.UTF8_CHARSET), "*");// *
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


  private static String buscarPrefijoNodoNAMESPACE(List<String> listaNameSpaces,
      String NameSpaceABuscar) {
    String prefijo = "";

    for (int i = 0; i < listaNameSpaces.size(); i++) {

      if (listaNameSpaces.get(i).split("=")[1].equalsIgnoreCase(NameSpaceABuscar)) {
        prefijo = listaNameSpaces.get(i).split("=")[0].split(":")[1] + ":";// le añado el dos puntos
      }

    }

    return prefijo;
  }



  private static String buscarPrefijoNodoNAMESPACEDosPuntosDelante(List<String> listaNameSpaces,
      String NameSpaceABuscar) {
    String prefijo = "";

    for (int i = 0; i < listaNameSpaces.size(); i++) {

      if (listaNameSpaces.get(i).split("=")[1].equalsIgnoreCase(NameSpaceABuscar)) {
        String parte1 = listaNameSpaces.get(i).split("=")[0];
        if (parte1.contains(":")) {
          prefijo = parte1.split(":")[1];// le añado el dos puntos
          return ":" + prefijo;
        } else {
          return prefijo;// vacio no usa ningun prefijo para el namespace
        }
      }

    }
    return prefijo;


  }


  public static String prefijoNamespaceExpediente(String stringXMLExpediente,
      String nameSpaceSearch) throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    // busca el prefijo correspondiente al nodo expediente eni
    String prefijo = "";

    List<String> listaNameSpaces = XMLUtils.getNameSpacesNodoROOT(stringXMLExpediente);

    prefijo = buscarPrefijoNodoNAMESPACEDosPuntosDelante(listaNameSpaces, nameSpaceSearch);

    // // es quer no lo ha encontrado en el nodo root busca en el siguiente
    // if("".equals(prefijo))
    // {
    // listaNameSpaces.clear();
    // listaNameSpaces = XMLUtils.getNameSpacesDelHijoDelROOT(stringXMLExpediente);
    //
    // prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, nameSpaceSearch);
    //
    // }

    return prefijo;

  }

  public static String prefijoNamespaceExpediente(String stringXMLExpediente)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {
    // busca el prefijo correspondiente al nodo expediente eni
    String prefijo = "";

    List<String> listaNameSpaces = XMLUtils.getNameSpacesNodoROOT(stringXMLExpediente);

    prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    // es quer no lo ha encontrado en el nodo root busca en el siguiente
    if ("".equals(prefijo)) {
      listaNameSpaces.clear();
      listaNameSpaces = XMLUtils.getNameSpacesDelHijoDelROOT(stringXMLExpediente);

      prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_EXPEDIENTE);

    }

    return prefijo;

  }


  public static String prefijoNamespaceDocumento(String stringXMLDocumento)
      throws ParserConfigurationException, SAXException, IOException,
      TransformerFactoryConfigurationError, TransformerException {

    List<String> listaNameSpaces = XMLUtils.getNameSpacesNodoROOT(stringXMLDocumento);

    // busca el prefijo correspondiente al nodo expediente eni
    String prefijo = "";

    prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_DOCUMENTO);

    // es quer no lo ha encontrado en el nodo root busca en el siguiente
    if ("".equals(prefijo)) {
      listaNameSpaces.clear();
      listaNameSpaces = XMLUtils.getNameSpacesDelHijoDelROOT(stringXMLDocumento);

      prefijo = buscarPrefijoNodoNAMESPACE(listaNameSpaces, NAMESPACE_DOCUMENTO);

    }

    return prefijo;

  }

}
