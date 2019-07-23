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


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import es.gob.utilidades.cliente.rec.exception.RecException;
import es.gob.utilidades.cliente.rec.exception.RecFormatException;
import es.gob.utilidades.cliente.rec.facade.RegistroElectronicoFacade;
import es.gob.utilidades.cliente.rec.model.DocumentoType;
import es.gob.utilidades.cliente.rec.model.InteresadoType;
import es.gob.utilidades.cliente.rec.model.JustificanteType;

@PropertySource("file:${config.path}/clientWSRegistroElectronico.properties")
@Component("RecUtils")
public class RecUtils {

  @SuppressWarnings("unused")
  private static Logger logger = Logger.getLogger(RecUtils.class);

  @Value("${rec.autenticacion.idAplicacion}")
  private String idAplicacion;

  @Value("${rec.autenticacion.password}")
  private String password;

  @Value("${rec.registro.codAsunto}")
  private String codAsunto;

  @Value("${rec.registro.oficinaRegistroOrigen}")
  private String cdOficinaOrigen;

  @Value("${rec.registro.cdTipoRegistro}")
  private String cdTipoRegistro;

  @Value("${rec.registro.unidadTramitacionDestino}")
  private String unidadTramitacionDestino;

  @Value("${rec.registro.cdUnidadDestino}")
  private String cdUnidadDestino;

  @Value("${rec.registro.cdUnidadOrigen}")
  private String cdUnidadOrigen;

  @Value("${rec.registro.tlResumen}")
  private String tlResumen;

  @Value("${rec.registro.cdAsunto}")
  private String cdAsunto;

  @Value("${rec.registro.cdDocumentacionFisicaSoportes}")
  private String cdDocumentacionFisicaSoportes;

  @Value("${rec.documento.cdValidez}")
  private String cdValidez;

  @Value("${rec.documento.cdTipo}")
  private String cdTipo;

  @Value("${rec.documento.cdFirmado}")
  private String cdFirmado;

  @Value("${rec.certificado.public.key}")
  private String certificadoPublicKey;

  public JustificanteType doRegistroElectronico(String dsNombre, String blDocumento, String blHash)
      throws RecFormatException, RecException, Exception {

    try {

      InteresadoType interesados[] = new InteresadoType[0];

      DocumentoType documentos[] = new DocumentoType[1];

      documentos[0] =
          RegistroElectronicoFacade.getDocumentoType(dsNombre, cdValidez, cdTipo, blDocumento, "", // blFirma
              cdFirmado, certificadoPublicKey, "", // blTimeStamp
              "", // blValidacionOCSP
              blHash, "", // dsTipoMIME
              "" // tlObservaciones
          );

      JustificanteType justificante = RegistroElectronicoFacade.registrar(
          RegistroElectronicoFacade.getAutenticacionTypeObject(idAplicacion, password),
          RegistroElectronicoFacade.getRegistroTypeObject(cdOficinaOrigen, cdTipoRegistro, "", // feRegistro
              cdUnidadDestino, cdUnidadOrigen, interesados, documentos, tlResumen, cdAsunto, "", // tlReferenciaExterna
              "", // tlNumeroExpediente
              "", // cdTipoTransporte,
              "", // tlNumeroTransporte,
              "", // tlNombreUsuario,
              "", // tlContactoUsuario,
              cdDocumentacionFisicaSoportes, "", // tlExpone,
              "", // tlSolicita,
              "Uso de Token"// tlObservaciones
          ));

      validaJustificante(justificante);

      return justificante;
    } catch (RecFormatException e) {
      throw e;
    } catch (RecException e) {
      throw e;
    } catch (Exception e) {
      throw e;
    }

  }

  private void validaJustificante(JustificanteType justificante) throws RecException {

    if (justificante == null) {
      throw new RecException();
    }

    if (!"00".equals(justificante.getRespuesta().getCdRespuesta())) {
      throw new RecException(justificante.getRespuesta().getDsRespuesta());
    }

  }

}
