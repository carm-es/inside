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

package es.mpt.dsic.inside.test.unitarios.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.DigestUtils;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion;
import es.mpt.dsic.inside.model.objetos.documento.metadatos.ObjetoDocumentoInsideMetadatosTipoDocumental;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.object.definitions.InsideObjectDefinitionsContainer;
import es.mpt.dsic.inside.service.object.metadatos.validator.Exception.InSideServiceValidationException;
import es.mpt.dsic.inside.test.unitarios.InSideServiceTestBase;
import es.mpt.dsic.inside.util.InsideUtils;

/**
 * Test para probar las validaciones de los objetos
 * 
 * @author miguel.ortega
 *
 */
public class InsideObjectValidationTest<T extends ObjetoInside<?>> extends InSideServiceTestBase {

  protected static final Log logger = LogFactory.getLog(InSideServiceTestBase.class);

  @Autowired
  @Qualifier("InsideObjectDefinitions")
  private InsideObjectDefinitionsContainer objectDefinitions;

  @Test
  public void testObjetoExpedienteInside() throws InSideServiceException {
    logger.info("Iniciando test de validación de ObjetoExpedienteInside");
    ObjetoExpedienteInside ObjetoExpedienteInside = new ObjetoExpedienteInside();
    ObjetoExpedienteInside.getMetadatos().setVersionNTI("versión 1.0");
    List<String> organos = new ArrayList<String>();
    organos.add("Órgano de prueba");
    ObjetoExpedienteInside.getMetadatos().setOrgano(organos);
    ObjetoExpedienteInside.getMetadatos().setClasificacion("Clasificación");
    ObjetoExpedienteInside.getMetadatos()
        .setEstado(ObjetoExpedienteInsideMetadatosEnumeracionEstados.E_01);

    ObjetoExpedienteInsideIndiceContenido contenidoIndice =
        ObjetoExpedienteInside.getIndice().getIndiceContenido();
    List<ObjetoExpedienteInsideIndiceContenidoElementoIndizado> elementosIndizados =
        new ArrayList<ObjetoExpedienteInsideIndiceContenidoElementoIndizado>();

    ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
        new ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada();
    carpetaIndizada.setOrden(1);
    carpetaIndizada.setIdentificadorCarpeta(UUID.randomUUID().toString());
    elementosIndizados.add(carpetaIndizada);

    ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
        new ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado();
    documentoIndizado.setOrden(2);
    documentoIndizado.setFechaIncorporacionExpediente(GregorianCalendar.getInstance());
    documentoIndizado.setFuncionResumen(InsideUtils.HUELLA_SHA512_LITERAL_URI);
    documentoIndizado.setIdentificadorDocumento(UUID.randomUUID().toString());
    documentoIndizado
        .setValorHuella(org.apache.commons.codec.digest.DigestUtils.sha512Hex("Huella".getBytes()));

    elementosIndizados.add(documentoIndizado);

    contenidoIndice.setElementosIndizados(elementosIndizados);

    validaObjetoInside(ObjetoExpedienteInside);

  }

  @Test
  public void testObjetoDocumentoInside() throws InSideServiceException {
    logger.info("Iniciando test de validación de ObjetoDocumentoInside");
    ObjetoDocumentoInside ObjetoDocumentoInside = new ObjetoDocumentoInside();
    ObjetoDocumentoInside.getMetadatos().setVersionNTI("versión 1.0");
    ObjetoDocumentoInsideMetadatosEstadoElaboracion estadoElaboracion =
        new ObjetoDocumentoInsideMetadatosEstadoElaboracion();
    estadoElaboracion.setIdentificadorDocumentoOrigen("Origen de prueba");
    estadoElaboracion.setValorEstadoElaboracion(
        ObjetoDocumentoInsideMetadatosEstadoElaboracionEnumeracion.EE_01);
    ObjetoDocumentoInside.getMetadatos().setEstadoElaboracion(estadoElaboracion);
    ObjetoDocumentoInside.getMetadatos()
        .setTipoDocumental(ObjetoDocumentoInsideMetadatosTipoDocumental.TD_01);
    validaObjetoInside(ObjetoDocumentoInside);

  }

  @SuppressWarnings("unchecked")
  private void validaObjetoInside(ObjetoInside<?> objetoInside) throws InSideServiceException {
    try {
      objectDefinitions.getValidator(objetoInside.getClass()).validate((T) objetoInside);
      logger.info("Objeto " + objetoInside.getClass() + " validado correctamente");
    } catch (InSideServiceValidationException e) {
      logger.error("InSideServiceValidationException: " + e.getMessage(), e);
      e.printStackTrace();
    }
  }

}
