/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

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
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.xerces.dom.DeferredElementImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XMLUtils {

	private static String nodoARemplazar=null;
	
	public static Node getNode(byte[] xml, String tag) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document dom = db.parse(new ByteArrayInputStream(xml));
		Node nodo = null;
		
		// No se ha pasado ningun tag que tenga match desde 'documentoAdicionalWsToEni(byte[] adicional)'. 
		// El * es comodin e indicamos que coja el primer nodo y recoge el nombre y se lo asigna al tag.
		// asi nos aseguramos que hay match.
		if("*".equals(tag))
		{
			tag = dom.getDocumentElement().getNodeName();
			nodoARemplazar=tag;
			
		}
				
		nodo = (dom.getElementsByTagName(tag).item(0));
		return nodo;
	}

	public static String documentoAdicionalWebToEni(byte[] adicional) throws ParserConfigurationException, SAXException,
			IOException, TransformerFactoryConfigurationError, TransformerException {
		Node nodoEni = getNode(adicional, "ns5:documento");
		Element nodoEniElem = (Element) nodoEni;
		nodoEniElem.setAttribute("xmlns:ns2", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos");
		nodoEniElem.setAttribute("xmlns:ns3", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
		nodoEniElem.setAttribute("xmlns:ns4", "http://www.w3.org/2000/09/xmldsig#");
		nodoEniElem.setAttribute("xmlns:ns5", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
		nodoEniElem.setAttribute("xmlns:ns6", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
		nodoEniElem.setAttribute("xmlns:ns7", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
		nodoEniElem.setAttribute("xmlns:insidews", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
		nodoEniElem.setAttribute("xmlns", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
		nodoEniElem.setAttribute("xmlns:enidoc", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
		return nodeToString(nodoEni);
	}

	public static String documentoAdicionalWsToEni(byte[] adicional) throws ParserConfigurationException, SAXException,
			IOException, TransformerFactoryConfigurationError, TransformerException {
		Node nodoEni = getNode(adicional, "ns15:documento");
		String replaceFirstNode = null;
		if (nodoEni == null) {
			nodoEni = getNode(adicional, "ns15:documentoMtom");
		}
		if (nodoEni == null) {
			nodoEni = getNode(adicional, "enidoc:documento");
		}
		if (nodoEni == null) {
			nodoEni = getNode(adicional, "insidews:documento");
			if(nodoEni != null)
				replaceFirstNode = "enidoc:documento";
		}
		//si no cumple ninguno de los anteriores, le pasamos * que es comodin
		//para que pregunte por el nombre de la etiqueta del elemento raiz y asignar ese al tag.
		if (nodoEni == null) {
			nodoEni = getNode(adicional , "*");
			
		}
		
		
		Element nodoEniElem = (Element) nodoEni;
		nodoEniElem
				.setAttribute("xmlns:enidocmeta", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/metadatos");
		nodoEniElem.setAttribute("xmlns:enids", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
		nodoEniElem.setAttribute("xmlns:enidoc", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e");
		nodoEniElem.setAttribute("xmlns:insidemeta", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
		nodoEniElem.setAttribute("xmlns:insidedoc", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
		nodoEniElem.setAttribute("xmlns:insidews", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");
		nodoEniElem.setAttribute("xmlns:enifile", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
		nodoEniElem.setAttribute("xmlns", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");

		String retorno = nodeToString(nodoEni);

		if (replaceFirstNode != null) {
			retorno = StringUtils.replace(retorno, "insidews:documento", "enidoc:documento");
		}
		
		if (nodoARemplazar != null) {
			retorno = StringUtils.replace(retorno, nodoARemplazar, "enidoc:documento");
		}
		return retorno;
	}

	public static String nodeToString(Node node) throws TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		Source source = new DOMSource(node);
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		transformer.transform(source, result);
		return sw.toString();
	}

	public static String expedienteAdicionalWebToEni(byte[] adicional) throws ParserConfigurationException, SAXException,
			IOException, TransformerFactoryConfigurationError, TransformerException {
		Node nodoEni = getNode(adicional, "ns7:expediente");
		Element nodoEniElem = (Element) nodoEni;
		nodoEniElem.setAttribute("xmlns:ns2", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e");
		nodoEniElem.setAttribute("xmlns", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido");
		nodoEniElem.setAttribute("xmlns:ns4", "http://www.w3.org/2000/09/xmldsig#");
		nodoEniElem.setAttribute("xmlns:ns3", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
		nodoEniElem.setAttribute("xmlns:insidews", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService");
		nodoEniElem.setAttribute("xmlns:ns9", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e");
		nodoEniElem.setAttribute("xmlns:ns5", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos");
		nodoEniElem.setAttribute("xmlns:ns6", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
		nodoEniElem.setAttribute("xmlns:ns7", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e");
		nodoEniElem.setAttribute("xmlns:ns8", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
		return nodeToString(nodoEni);
	}

	public static String expedienteAdicionalWsToEni(byte[] adicional) throws ParserConfigurationException, SAXException,
			IOException, TransformerFactoryConfigurationError, TransformerException {
		Node nodoEni = getNode(adicional, "eniexp:expediente");
		String replaceFirstNode = null;
		if (nodoEni == null) {
			nodoEni = getNode(adicional, "insidews:expediente");
			if (nodoEni == null) {
				nodoEni = getNode(adicional, "ns7:expediente");
			}
			replaceFirstNode = "eniexp:expediente";
		}
		
		if (nodoEni == null) {
			nodoEni = getNode(adicional , "*");
			
		}
		
		Element nodoEniElem = (Element) nodoEni;
		nodoEniElem.setAttribute("xmlns:eniexp", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e");
		nodoEniElem.setAttribute("xmlns:eniexpind", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e");
		nodoEniElem.setAttribute("xmlns:eniconexpind",
				"http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/indice-e/contenido");
		nodoEniElem.setAttribute("xmlns:enids", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/firma");
		nodoEniElem.setAttribute("xmlns:eniexpmeta",
				"http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e/metadatos");
		nodoEniElem.setAttribute("xmlns:enifile", "http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e/contenido");
		nodoEniElem.setAttribute("xmlns:insideexp", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e");
		nodoEniElem.setAttribute("xmlns:insidemeta", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/metadatosAdicionales");
		nodoEniElem.setAttribute("xmlns:insidews", "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/documento-e");

		String retorno = nodeToString(nodoEni);

		if (replaceFirstNode != null) {
			retorno = StringUtils.replace(retorno, "insidews:expediente", "eniexp:expediente");
		}
		return retorno;
	}

	public static String updateSignature(String expression, byte[] data, String ref, boolean ommitDeclaration) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new ByteArrayInputStream(data));
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();

		XPathExpression exprFirst = xpath.compile(expression);
		DeferredElementImpl nodeParent = (DeferredElementImpl) exprFirst.evaluate(doc, XPathConstants.NODE);

		if (StringUtils.isNotBlank(ref)) {
			Node nodoReference = nodeParent.getElementsByTagName("ds:Reference").item(0);
			NamedNodeMap atributos = nodoReference.getAttributes();
			if (atributos != null) {
				atributos.getNamedItem("URI").setTextContent(ref);
			}
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

	public static String getNodeContentSignature(String expression, byte[] data, boolean ommitDeclaration) throws ParserConfigurationException,
			SAXException, IOException, XPathExpressionException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new ByteArrayInputStream(data));
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();

		XPathExpression exprFirst = xpath.compile(expression);
		DeferredElementImpl nodeParent = (DeferredElementImpl) exprFirst.evaluate(doc, XPathConstants.NODE);
		
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

}
