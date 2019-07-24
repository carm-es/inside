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

package es.mpt.dsic.inside.web.util;

import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.xpath.XPathExpressionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import es.mpt.dsic.inside.util.xml.JAXBMarshaller;
import es.mpt.dsic.inside.xml.eni.firma.Firmas;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirma;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas.ContenidoFirma;
import es.mpt.dsic.inside.xml.eni.firma.TipoFirmasElectronicas.ContenidoFirma.FirmaConCertificado;

@Component
public class SignatureUtils {

  @Autowired
  private JAXBMarshaller marshaller;

  public Firmas setSignaturesExp(byte[] signature, String ref)
      throws XPathExpressionException, ParserConfigurationException, SAXException, IOException,
      TransformerException, JAXBException, TransformerFactoryConfigurationError {
    Firmas retorno = new Firmas();
    TipoFirmasElectronicas firma = new TipoFirmasElectronicas();
    ContenidoFirma contenidoFirma = new ContenidoFirma();

    FirmaConCertificado firmaConCertificado = new FirmaConCertificado();

    // String nodofirma =
    // es.mpt.dsic.inside.service.util.XMLUtils.updateSignature("AFIRMA/Signature", signature,
    // "#EXP_INDICE_CONTENIDO" + ref, false);
    // firmaConCertificado.setSignature(marshaller.unmarshallDataSignature(nodofirma.getBytes()));

    // pasamos todo el eni para hacer la firma y guardo la firma(el nodo ds:signature) original en
    // base64 en firmaBase64
    // para al descargar el eni decodificarla y quitar el nodo firmabase64 y poner el ds:signature
    // String nodofirma =
    // es.mpt.dsic.inside.service.util.XMLUtils.signatureString("expediente/indice/Signature",
    // signature);
    String nodofirma = es.mpt.dsic.inside.service.util.XMLUtils
        .signatureString("//*[local-name()='Signature']", signature);

    firmaConCertificado.setFirmaBase64(
        Base64.encode(nodofirma.getBytes(es.mpt.dsic.inside.service.util.XMLUtils.UTF8_CHARSET)));

    contenidoFirma.setFirmaConCertificado(firmaConCertificado);
    firma.setContenidoFirma(contenidoFirma);
    firma.setId("FIRMA_0");
    // firma.setTipoFirma(TipoFirma.TF_02);
    firma.setTipoFirma(TipoFirma.TF_03);
    firma.setRef("#EXP_INDICE_CONTENIDO" + ref);

    retorno.getFirma().add(firma);

    return retorno;
  }

}
