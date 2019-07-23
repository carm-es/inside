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

package es.mpt.dsic.inside.service.store.impl.hibernate.converter;

import java.io.UnsupportedEncodingException;
import org.apache.axiom.om.util.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInside;
import es.mpt.dsic.inside.model.objetos.firmas.FirmaInsideTipoFirmaEnum;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCSVInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoAlmacenableInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoDsSignatureInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoReferenciaInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;


public class InsideServiceStoreHibernateConverterFirma {

  protected static final Log logger =
      LogFactory.getLog(InsideServiceStoreHibernateConverterFirma.class);
  private static final String EXP_REF = "EXP_INDICE";

  public static es.mpt.dsic.inside.store.hibernate.entity.FirmaInside toEntity(FirmaInside firma)
      throws InsideServiceStoreHibernateConverterFirmaException {
    es.mpt.dsic.inside.store.hibernate.entity.FirmaInside entity =
        new es.mpt.dsic.inside.store.hibernate.entity.FirmaInside();

    entity.setTipoFirma(firma.getTipoFirma().value());

    ContenidoFirmaInside contenidoFirma = firma.getContenidoFirma();

    if (contenidoFirma == null) {
      throw new InsideServiceStoreHibernateConverterFirmaException(
          "El contenido de la firma está vacío");
    }

    if (contenidoFirma instanceof ContenidoFirmaCertificadoAlmacenableInside) {
      ContenidoFirmaCertificadoAlmacenableInside contenidofirmaAlmacenable =
          (ContenidoFirmaCertificadoAlmacenableInside) contenidoFirma;
      entity.setIdentificadorRepositorio(contenidofirmaAlmacenable.getIdentificadorRepositorio());
      entity.setTipoMime(contenidofirmaAlmacenable.getMime());
      entity.setSignature(contenidoFirma instanceof ContenidoFirmaCertificadoDsSignatureInside);

      if (contenidofirmaAlmacenable.getIdentificadorRepositorio() == null
          && contenidofirmaAlmacenable.getValorBinario() != null) {
        entity.setContenido(Base64.encode(contenidofirmaAlmacenable.getValorBinario()).getBytes());
      }

    } else if (contenidoFirma instanceof ContenidoFirmaCertificadoReferenciaInside) {
      ContenidoFirmaCertificadoReferenciaInside contenidofirmaReferencia =
          (ContenidoFirmaCertificadoReferenciaInside) contenidoFirma;
      entity.setReferencia(contenidofirmaReferencia.getReferenciaFirma());
    } else if (contenidoFirma instanceof ContenidoFirmaCSVInside) {
      if (firma.getTipoFirma() != FirmaInsideTipoFirmaEnum.TF_01) {
        throw new InsideServiceStoreHibernateConverterFirmaException(
            "El contenido de la firma es de tipo ContenidoFirmaCSVInside pero el tipo no es TF_01");
      }
      ContenidoFirmaCSVInside contenidofirmaCsv = (ContenidoFirmaCSVInside) contenidoFirma;
      entity.setValorCsv(contenidofirmaCsv.getValorCSV());
      entity.setRegulacionCsv(contenidofirmaCsv.getRegulacionGeneracionCSV());
    } else {
      throw new InsideServiceStoreHibernateConverterFirmaException(
          "El tipo de firma es TF_01 pero el contenido no es de tipo ContenidoFirmaCSVInside");
    }

    entity.setOrden(firma.getOrden());
    entity.setIdentificadorEnDocumento(firma.getIdentificadorEnDocumento());
    entity.setReferencia(firma.getRef());

    return entity;
  }

  public static FirmaInside toInside(es.mpt.dsic.inside.store.hibernate.entity.FirmaInside entity) {
    FirmaInside firma = new FirmaInside();

    firma.setTipoFirma(FirmaInsideTipoFirmaEnum.fromValue(entity.getTipoFirma()));

    ContenidoFirmaInside contenidoFirma;

    if (firma.getTipoFirma() == FirmaInsideTipoFirmaEnum.TF_01) {
      ContenidoFirmaCSVInside contenidoFirmaCsv = new ContenidoFirmaCSVInside();
      contenidoFirmaCsv.setValorCSV(entity.getValorCsv());
      contenidoFirmaCsv.setRegulacionGeneracionCSV(entity.getRegulacionCsv());
      contenidoFirma = contenidoFirmaCsv;
    } else {
      if (isDocReference(entity.getReferencia())) {
        ContenidoFirmaCertificadoReferenciaInside contenidofirmaReferencia =
            new ContenidoFirmaCertificadoReferenciaInside();
        contenidofirmaReferencia.setReferenciaFirma(entity.getReferencia());
        contenidoFirma = contenidofirmaReferencia;
      } else {
        if (isExpedienteReference(entity.getReferencia())) {
          firma.setRef(entity.getReferencia());
        }

        ContenidoFirmaCertificadoAlmacenableInside contenidofirmaAlmacenable;

        if (entity.getSignature() != null && entity.getSignature()) {

          contenidofirmaAlmacenable = new ContenidoFirmaCertificadoDsSignatureInside();
        } else {

          contenidofirmaAlmacenable = new ContenidoFirmaCertificadoContenidoBinarioInside();
        }
        contenidofirmaAlmacenable.setIdentificadorRepositorio(entity.getIdentificadorRepositorio());
        contenidofirmaAlmacenable.setMime(entity.getTipoMime());
        if (entity.getContenido() != null) {
          try {
            contenidofirmaAlmacenable
                .setValorBinario(Base64.decode(new String(entity.getContenido(), "UTF-8")));
          } catch (UnsupportedEncodingException e) {
            logger.error("Se ha producido un error al recuperar la firma", e);
          }
        }
        contenidoFirma = contenidofirmaAlmacenable;
      }
    }


    firma.setContenidoFirma(contenidoFirma);
    firma.setIdentificadorEnDocumento(entity.getIdentificadorEnDocumento());
    firma.setOrden(entity.getOrden());

    return firma;
  }

  public static boolean isExpedienteReference(String ref) {
    return StringUtils.isNotBlank(ref) && ref.contains(EXP_REF);
  }

  public static boolean isDocReference(String ref) {
    return StringUtils.isNotBlank(ref) && !ref.contains(EXP_REF);
  }



}
