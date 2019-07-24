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


import java.util.Map;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;
import es.mpt.dsic.inside.service.impl.InSideServiceImpl;
import es.mpt.dsic.inside.service.object.converter.impl.InsideServiceAdapterException;
import es.mpt.dsic.inside.service.object.converter.impl.csvstorage.InsideServiceCsvStorageAdapter;



public class UtilidadDigestInsideImpl implements UtilidadDigestInside {


  protected static final Log logger = LogFactory.getLog(InSideServiceImpl.class);


  @Autowired
  protected InsideServiceCsvStorageAdapter csvStorageAdapter;


  @Override
  public String getValorHuellaContenidoAlgoritmo(ObjetoDocumentoInside documentoInside,
      Map<String, String> idDocIndiceExpAndAlgoritmoHuella) throws InSideServiceException {
    try {
      byte[] bytesToDigest = null;
      String retorno = null;
      // Si tiene valorbinario
      if (documentoInside.getContenido().getValorBinario() != null) {
        bytesToDigest = documentoInside.getContenido().getValorBinario();
        retorno =
            calcularHuellaFichero(documentoInside, idDocIndiceExpAndAlgoritmoHuella, bytesToDigest);
      } else if (StringUtils.isNotEmpty(documentoInside.getContenido().getReferencia())
          && (CollectionUtils.isEmpty(documentoInside.getFirmas()) || FirmaInsideTipoFirmaEnum.TF_01
              .value().equals(documentoInside.getFirmas().get(0).getTipoFirma().value()))) {
        // fichero de gran tamaño no está firmado o gran tamaño con firma csv
        String[] referenciaSplit = documentoInside.getContenido().getReferencia().split("/");
        String referencia = referenciaSplit[referenciaSplit.length - 1];
        String algoritmo = encontrarAlgoritmo(documentoInside, idDocIndiceExpAndAlgoritmoHuella);
        retorno = csvStorageAdapter.getValorHuellaDocumento(referencia, algoritmo);
      } else {
        // Calculamos el hash de la firma
        ContenidoFirmaCertificadoContenidoBinarioInside firmaBase64 =
            (ContenidoFirmaCertificadoContenidoBinarioInside) documentoInside.getFirmas().get(0)
                .getContenidoFirma();
        bytesToDigest = Base64.encodeBase64(firmaBase64.getValorBinario());
        retorno =
            calcularHuellaFichero(documentoInside, idDocIndiceExpAndAlgoritmoHuella, bytesToDigest);
      }
      return retorno;
    } catch (InsideServiceAdapterException e) {
      logger.error("Error al calcular el hash del documento:" + e.getMessage());
      throw new InsideServiceInternalException(
          "Error al calcular el hash del documento: " + documentoInside.getIdentificador());
    }
  }

  @Override
  public String calcularHuellaFichero(ObjetoDocumentoInside documentoInside,
      Map<String, String> idDocIndiceExpAndAlgoritmoHuella, byte[] bytesToDigest) {
    String algoritmo = idDocIndiceExpAndAlgoritmoHuella.get(documentoInside.getIdentificador());

    if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA512_LITERAL_URI.equalsIgnoreCase(algoritmo)
        || algoritmo == null) {
      return org.apache.commons.codec.digest.DigestUtils.sha512Hex(bytesToDigest);
    } else if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA384_LITERAL_URI
        .equalsIgnoreCase(algoritmo)) {
      return org.apache.commons.codec.digest.DigestUtils.sha384Hex(bytesToDigest);
    } else if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA256_LITERAL_URI
        .equalsIgnoreCase(algoritmo)) {
      return org.apache.commons.codec.digest.DigestUtils.sha256Hex(bytesToDigest);
    } else {
      return DigestUtils.md5DigestAsHex(bytesToDigest);
    }


  }

  // devuelve el algoritmo de huella dependiendo del valor del nodo en xml.antiguamente todo era md5
  @Override
  public String encontrarAlgoritmo(ObjetoDocumentoInside documentoInside,
      Map<String, String> idDocIndiceExpAndAlgoritmoHuella) {
    String algoritmo = idDocIndiceExpAndAlgoritmoHuella.get(documentoInside.getIdentificador());

    if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA512_LITERAL_URI.equalsIgnoreCase(algoritmo)
        || algoritmo == null) {
      return es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA512_ALGORITMO;
    } else if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA384_LITERAL_URI
        .equalsIgnoreCase(algoritmo)) {
      return es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA384_ALGORITMO;
    } else if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA256_LITERAL_URI
        .equalsIgnoreCase(algoritmo)) {
      return es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA256_ALGORITMO;
    } else {
      return "MD5";
    }

  }

  // devuelve el algoritmo de huella dependiendo del valor del nodo en xml.antiguamente todo era md5
  @Override
  public String encontrarURI_Algoritmo(ObjetoDocumentoInside documentoInside,
      Map<String, String> idDocIndiceExpAndAlgoritmoHuella) {
    String algoritmo = idDocIndiceExpAndAlgoritmoHuella.get(documentoInside.getIdentificador());

    if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA512_ALGORITMO.equalsIgnoreCase(algoritmo)
        || algoritmo == null) {
      return es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA512_LITERAL_URI;
    } else if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA384_ALGORITMO
        .equalsIgnoreCase(algoritmo)) {
      return es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA384_LITERAL_URI;
    } else if (es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA256_ALGORITMO
        .equalsIgnoreCase(algoritmo)) {
      return es.mpt.dsic.inside.util.InsideUtils.HUELLA_SHA256_LITERAL_URI;
    } else {
      return "MD5";
    }

  }


}
