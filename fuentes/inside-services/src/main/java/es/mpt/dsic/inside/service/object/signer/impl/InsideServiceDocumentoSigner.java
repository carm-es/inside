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

package es.mpt.dsic.inside.service.object.signer.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import es.mpt.dsic.inside.model.objetos.ObjetoAuditoriaFirmaServidor;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoDsSignatureInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoReferenciaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.exception.InSideServiceSignerException;
import es.mpt.dsic.inside.service.object.signer.InsideServiceObjectSigner;
import es.mpt.dsic.inside.service.object.signer.InsideServiceObjectSignerException;
import es.mpt.dsic.inside.service.object.signer.InsideServiceSigner;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.InsideUtils;

public class InsideServiceDocumentoSigner
    implements InsideServiceObjectSigner<ObjetoDocumentoInside, ObjetoDocumentoInsideMetadatos> {

  protected static final Log logger = LogFactory.getLog(InsideServiceDocumentoSigner.class);

  protected InsideServiceSigner signer;

  @Autowired
  private InSideService insideService;

  @Override
  /**
   * Firma un objeto de tipo ObjetoDocumentoInside. Aquí asumimos que ya tenemos el mime del
   * contenido. Se firmarán los bytes del valorBinario del documento. Los bytes y el mime de la
   * firma se colocarán en el atributo valorBinario del objeto FirmaInside.
   * 
   * @param documento El documento a ser firmado
   * @return El mismo documento con una firma añadida
   * 
   */
  public ObjetoDocumentoInside sign(ObjetoDocumentoInside documento, String usuario)
      throws InSideServiceException {

    if (documento.getContenido() == null) {
      throw new InsideServiceObjectSignerException(
          "El contenido del documento no puede estar vacío");
    }

    byte[] bytesAFirmar;
    String sTipoFirma;
    byte[] bytesFirma;

    FirmaInside nuevaFirma = null;

    // No tiene firmas con certificado, firmamos el contenido. Dependiendo
    // del mime del contenido utilizaremos una firma u otra.
    if (documento.getFirmas() == null || documento.getFirmas().isEmpty()
        || getFirstFirmaInsideCertificado(documento.getFirmas()) == null) {
      bytesAFirmar = documento.getContenido().getValorBinario();
      sTipoFirma = InsideUtils.getTipoFirmaByMimeAFirmar(documento.getContenido().getMime());

      FirmaInsideTipoFirmaEnum eTipoFirma =
          FirmaInside.getTipoFirmaByMimeAFirmar(documento.getContenido().getMime());

      try {
        bytesFirma = signer.firmarFichero(bytesAFirmar, null, sTipoFirma, null, null);
        ObjetoAuditoriaFirmaServidor objetoAuditoriaFirmaServidor =
            new ObjetoAuditoriaFirmaServidor(Constantes.ELEMENTO_DOCUMENTO,
                documento.getIdentificador(), usuario);
        insideService.saveAuditoriaFirmaServidor(objetoAuditoriaFirmaServidor);
      } catch (InSideServiceSignerException ise) {
        String mensajeError =
            String.format("No se ha podido firmar el documento con identificador %s. %s",
                documento.getMetadatos().getIdentificadorObjetoInside(), ise.getMessage());
        throw new InsideServiceObjectSignerException(mensajeError, ise);
      }
      // XAdES Detached y XAdES Enveloped. Firma en contenido. Las firmas
      if (eTipoFirma == FirmaInsideTipoFirmaEnum.TF_02
          || eTipoFirma == FirmaInsideTipoFirmaEnum.TF_03) {
        documento.getContenido().setValorBinario(bytesFirma);
        documento.getContenido().setMime("application/xml");
        documento.getContenido().setReferencia("CONTENIDO_DOCUMENTO");

        ContenidoFirmaCertificadoReferenciaInside contenidoFirma =
            new ContenidoFirmaCertificadoReferenciaInside();
        contenidoFirma
            .setReferenciaFirma("#" + documento.getContenido().getIdentificadorEnDocumento());

        nuevaFirma = new FirmaInside(eTipoFirma, contenidoFirma,
            "FIRMA_" + documento.getFirmas().size(), documento.getFirmas().size());

        // CAdES Detached y Attached
      } else if (eTipoFirma == FirmaInsideTipoFirmaEnum.TF_04
          || eTipoFirma == FirmaInsideTipoFirmaEnum.TF_05) {

        ContenidoFirmaCertificadoContenidoBinarioInside contenidoFirma =
            new ContenidoFirmaCertificadoContenidoBinarioInside();
        contenidoFirma.setValorBinario(bytesFirma);
        contenidoFirma.setMime("application/octet-stream");

        nuevaFirma = new FirmaInside(eTipoFirma, contenidoFirma,
            "FIRMA_" + documento.getFirmas().size(), documento.getFirmas().size());

        // Si es attached, el documento apuntará a la firma.

        if (eTipoFirma == FirmaInsideTipoFirmaEnum.TF_05) {
          documento.getContenido().setValorBinario(null);
          documento.getContenido().setMime(null);
          documento.getContenido().setReferencia(nuevaFirma.getIdentificadorEnDocumento());
        }

        // PAdES, firma en contenido y firma apuntando al contenido.
      } else if (eTipoFirma == FirmaInsideTipoFirmaEnum.TF_06) {
        documento.getContenido().setValorBinario(bytesFirma);
        documento.getContenido().setMime("application/pdf");
        documento.getContenido().setReferencia("CONTENIDO_DOCUMENTO");

        ContenidoFirmaCertificadoReferenciaInside contenidoFirma =
            new ContenidoFirmaCertificadoReferenciaInside();
        contenidoFirma.setReferenciaFirma("#CONTENIDO_DOCUMENTO");

        nuevaFirma = new FirmaInside(eTipoFirma, contenidoFirma,
            "FIRMA_" + documento.getFirmas().size(), documento.getFirmas().size());
      }

      // Está firmado
    } else {
      // Obtenemos el primer contenido de firma que sea con certificado.
      FirmaInside firmaInside = getFirstFirmaInsideCertificado(documento.getFirmas());
      ContenidoFirmaInside contenidoFirma = firmaInside.getContenidoFirma();

      // Si la firma tiene contenido binario, entonces firmaremos este
      // contenido binario.
      if (contenidoFirma instanceof ContenidoFirmaCertificadoContenidoBinarioInside) {
        ContenidoFirmaCertificadoContenidoBinarioInside contenidoBinario =
            (ContenidoFirmaCertificadoContenidoBinarioInside) contenidoFirma;
        bytesAFirmar = contenidoBinario.getValorBinario();
        sTipoFirma = getTipoFirmaByTipoFirmaInside(firmaInside.getTipoFirma());

        try {
          bytesFirma = signer.firmarFichero(bytesAFirmar, null, sTipoFirma, null, null);
        } catch (InSideServiceSignerException ise) {
          String mensajeError = String.format(
              "No se ha podido refirmar la firma del documento con identificador %s. El identificadorEnDocumento de la firma es: %s,  %s",
              documento.getMetadatos().getIdentificadorObjetoInside(),
              firmaInside.getIdentificadorEnDocumento(), ise.getMessage());
          throw new InsideServiceObjectSignerException(mensajeError, ise);
        }

        // Reemplazamos el antiguo contenido de la firma, por el nuevo.
        contenidoBinario.setValorBinario(bytesFirma);

        // Apuntamos la referencia de la nueva firma apuntando al
        // identificador de la firma que hemos refirmado.
        ContenidoFirmaCertificadoReferenciaInside contenidoReferencia =
            new ContenidoFirmaCertificadoReferenciaInside();
        contenidoReferencia.setReferenciaFirma("#" + firmaInside.getIdentificadorEnDocumento());
        // Añadimos un nuevo nodo de firma:
        // Mismo tipo que la firma que hemos refirmado.
        // Identificador y orden dependiendo del orden de la última
        // firma.
        nuevaFirma = new FirmaInside(firmaInside.getTipoFirma(), contenidoReferencia,
            "FIRMA_" + documento.getFirmas().size(), documento.getFirmas().size());

      } else if (contenidoFirma instanceof ContenidoFirmaCertificadoDsSignatureInside) {

        throw new InsideServiceObjectSignerException(
            "Todavía no soportamos ContenidoFirmaCertificadoDsSignatureInside");

        // Si la firma es una referencia, buscaremos esa referencia,
        // primero en el contenido del documento y después en las
        // firmas.
      } else if (contenidoFirma instanceof ContenidoFirmaCertificadoReferenciaInside) {

        String ref =
            ((ContenidoFirmaCertificadoReferenciaInside) contenidoFirma).getReferenciaFirma();
        // Quitamos el #
        String id = ref.substring(1);

        // Miramos si el identificador se corresponde con el del
        // contenido
        if (documento.getContenido().getIdentificadorEnDocumento().equalsIgnoreCase(id)) {

          bytesAFirmar = documento.getContenido().getValorBinario();
          sTipoFirma = InsideUtils.getTipoFirmaByMimeAFirmar(documento.getContenido().getMime());
          try {
            bytesFirma = signer.firmarFichero(bytesAFirmar, null, sTipoFirma, null, null);
          } catch (InSideServiceSignerException ise) {
            String mensajeError = String.format(
                "No se ha podido refirmar la firma del documento con identificador %s. La firma estaba en el contenido del documento. %s",
                documento.getMetadatos().getIdentificadorObjetoInside(), ise.getMessage());
            throw new InsideServiceObjectSignerException(mensajeError, ise);
          }

          // Reemplazamos el contenido del documento por la nueva
          // firma
          documento.getContenido().setValorBinario(bytesFirma);

          // Apuntamos la referencia de la nueva firma apuntando al
          // identificador del contenido del documento.
          ContenidoFirmaCertificadoReferenciaInside contenidoReferencia =
              new ContenidoFirmaCertificadoReferenciaInside();
          contenidoReferencia
              .setReferenciaFirma("#" + documento.getContenido().getIdentificadorEnDocumento());
          // Añadimos un nuevo nodo de firma:
          // Mismo tipo que la firma que hemos refirmado.
          // Identificador y orden dependiendo del orden de la última
          // firma.
          nuevaFirma = new FirmaInside(firmaInside.getTipoFirma(), contenidoReferencia,
              "FIRMA_" + documento.getFirmas().size(), documento.getFirmas().size());

          // Si no, tiene que corresponderse con el Id del contenido
          // de alguna de las firmas.
        } else {
          firmaInside = getFirmaInsideById(documento.getFirmas(), id);

          ContenidoFirmaCertificadoContenidoBinarioInside contenidoBinario =
              (ContenidoFirmaCertificadoContenidoBinarioInside) firmaInside.getContenidoFirma();

          bytesAFirmar = contenidoBinario.getValorBinario();
          sTipoFirma = getTipoFirmaByTipoFirmaInside(firmaInside.getTipoFirma());

          try {
            bytesFirma = signer.firmarFichero(bytesAFirmar, null, sTipoFirma, null, null);
          } catch (InSideServiceSignerException ise) {
            String mensajeError = String.format(
                "No se ha podido refirmar la firma del documento con identificador %s. La firma estaba en el contenido del documento. %s",
                documento.getMetadatos().getIdentificadorObjetoInside(), ise.getMessage());
            throw new InsideServiceObjectSignerException(mensajeError, ise);
          }

          contenidoBinario.setValorBinario(bytesFirma);

          // Apuntamos la referencia de la nueva firma apuntando al
          // identificador de la firma que hemos refirmado.
          ContenidoFirmaCertificadoReferenciaInside contenidoReferencia =
              new ContenidoFirmaCertificadoReferenciaInside();
          contenidoReferencia.setReferenciaFirma("#" + firmaInside.getIdentificadorEnDocumento());
          // Añadimos un nuevo nodo de firma:
          // Mismo tipo que la firma que hemos refirmado.
          // Identificador y orden dependiendo del orden de la última
          // firma.
          nuevaFirma = new FirmaInside(firmaInside.getTipoFirma(), contenidoReferencia,
              "FIRMA_" + documento.getFirmas().size(), documento.getFirmas().size());

        }
      }

    }

    documento.getFirmas().add(nuevaFirma);

    return documento;
  }

  /**
   * Obtiene la firma que tenga un determinado identificador. Null si no lo encuentra
   * 
   * @param firmas Lista de firmas.
   * @param id Identificador de la firma que queremos buscar.
   * @return firma cuyo identificador coincide con el pasado en el parámetro.
   */
  private static FirmaInside getFirmaInsideById(List<FirmaInside> firmas, String id) {
    int i = 0;
    FirmaInside firma = null;
    while (i < firmas.size() && firma == null) {
      if (firmas.get(i).getIdentificadorEnDocumento().equalsIgnoreCase(id)) {
        firma = firmas.get(i);
      }
      i++;
    }
    return firma;
  }

  /**
   * Devuelve la primera firma cuyo contenido sea instancia de ContenidoFirmaCertificadoInside
   * 
   * @param firmas Lista de firmas.
   * @return
   */
  private static FirmaInside getFirstFirmaInsideCertificado(List<FirmaInside> firmas) {
    int i = 0;
    FirmaInside firma = null;
    while (i < firmas.size() && firma == null) {
      if (firmas.get(i).getContenidoFirma() instanceof ContenidoFirmaCertificadoAlmacenableInside
          || firmas.get(i)
              .getContenidoFirma() instanceof ContenidoFirmaCertificadoReferenciaInside) {
        firma = firmas.get(i);
      }
      i++;
    }
    return firma;
  }

  /**
   * Devuelve el tipo de firma con que habrá que invocar al servicio de firma dependiendo del tipo
   * de firma Inside
   * 
   * @return
   */
  private static String getTipoFirmaByTipoFirmaInside(FirmaInsideTipoFirmaEnum tipoFirmaInside) {
    String ret = null;

    switch (tipoFirmaInside) {
      case TF_02: {
        ret = "XAdES Detached";
        break;
      }
      case TF_03: {
        ret = "XAdES Enveloped";
        break;
      }
      case TF_04: {
        ret = "CAdES";
        break;
      }
      case TF_05: {
        ret = "CAdES";
        break;
      }
      case TF_06: {
        ret = "Adobe PDF";
        break;
      }
      default: {
        break;
      }
    }

    return ret;

  }

  @Required
  public InsideServiceSigner getSigner() {
    return signer;
  }

  public void setSigner(InsideServiceSigner signer) {
    this.signer = signer;
  }

}
