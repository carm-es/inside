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

package es.mpt.dsic.inside.service.auditoria.impl;

import java.io.File;
import java.util.Date;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoAuditoriaToken;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.auditoria.InSideAuditoriaService;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.web.util.PdfUtils;

@PropertySource("file:${config.path}/temporalData.properties")
@Service("inSideAuditoriaService")
public class InSideAuditoriaServiceImpl implements InSideAuditoriaService {

  protected static final Log logger = LogFactory.getLog(InSideAuditoriaServiceImpl.class);

  @Value("${temporalData.path}")
  private String basePath;

  @Autowired
  private PdfUtils pdfUtils;

  @Autowired
  private InSideService insideService;

  @Override
  public void auditoriaRemisionNube(ObjetoInsideUsuario user, ObjetoExpedienteToken token,
      String sessionId) throws Exception {
    logger.debug(
        "InSideAuditoriaServiceImpl.auditoriaRemisionNube: Inicio registro de auditoria remision en la nube.");

    String temporalDir = basePath + sessionId + File.separator;

    // Si no existe el directorio temporal lo crea
    File dir = new File(temporalDir);
    if (!dir.exists()) {
      dir.mkdir();
    }

    // Las claves para obtener cada pdf del mapa son:
    // Acta = WebConstants.ACTA_DE_INGRESO_SUFIJO
    // Justificante = WebConstants.JUSTIFICANTE_REGISTRO_ELECTRONICO_SUFIJO
    Map<String, byte[]> mapaActaANDJustificante =
        pdfUtils.createPdfReceivedExpedient(user, token, temporalDir);

    ObjetoAuditoriaToken objectAuditoriaToken = new ObjetoAuditoriaToken(user, token,
        mapaActaANDJustificante.get(WebConstants.ACTA_DE_INGRESO_SUFIJO),
        mapaActaANDJustificante.get(WebConstants.JUSTIFICANTE_REGISTRO_ELECTRONICO_SUFIJO),
        new Date(),
        user.getUnidadOrganicaActiva() != null ? user.getUnidadOrganicaActiva().substring(0,
            user.getUnidadOrganicaActiva().lastIndexOf(" -")) : null,
        user.getUnidadOrganicaActiva() != null ? user.getUnidadOrganicaActiva().substring(
            user.getUnidadOrganicaActiva().lastIndexOf("- ") + 2,
            user.getUnidadOrganicaActiva().length()) : null,
        user.getRol() != null ? user.getRol().getId() : null,
        user.getRol() != null ? user.getRol().getDescripcion() : null);

    insideService.saveAuditoriaToken(objectAuditoriaToken);

    logger.debug(
        "InSideAuditoriaServiceImpl.auditoriaRemisionNube: Fin de registro de auditoria remision en la nube.");
  }

}
