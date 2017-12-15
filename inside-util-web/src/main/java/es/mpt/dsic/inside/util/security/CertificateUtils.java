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

package es.mpt.dsic.inside.util.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CertificateUtils {

	public static String getCertificateFromSignXML (byte[] sign) throws Exception {
        String certificado = null;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new Exception ("No se puede parsear la firma " + e.getMessage(), e);
        }
        Document dom = null;
        try {
            dom = db.parse(new ByteArrayInputStream(sign));
        } catch (SAXException e) {
            throw new Exception ("No se puede parsear la firma " + e.getMessage(), e);
        } catch (IOException e) {
            throw new Exception ("No se puede parsear la firma " + e.getMessage(), e);
        }

        certificado = getCertificate(dom);
        return certificado;
    }


   private static String getCertificate (Document dom) throws Exception {
        String certificate = null;
        Element elementRoot = dom.getDocumentElement();

        NodeList list = elementRoot.getElementsByTagName("ds:X509Certificate");

        if (list == null) {
            throw new Exception ("No se ha encontrado ning�n nodo ds:X509Certificate");
        } else {
            Node node = list.item(0);
            certificate = node.getFirstChild().getNodeValue();
        }
        return certificate;
    }
	
}
