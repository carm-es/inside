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

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaCertificadoContenidoBinarioInside;
import es.mpt.dsic.inside.model.objetos.firmas.contenido.ContenidoFirmaInside;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.object.definitions.InsideObjectDefinitionsContainer;
import es.mpt.dsic.inside.service.object.signer.InsideServiceObjectSignerException;
import es.mpt.dsic.inside.test.unitarios.InSideServiceTestBase;

public class InsideObjectSignerTest<T extends ObjetoInside<?>> extends InSideServiceTestBase {

  @Autowired
  @Qualifier("InsideObjectDefinitions")
  private InsideObjectDefinitionsContainer objectDefinitions;

  @Test
  public void firmaDocumento() {

    ObjetoDocumentoInside documento =
        UtilsTest.createObjetoDocumentoInside(true, true, true, false);
    String usuario = "11111111H";

    try {
      documento = (ObjetoDocumentoInside) firmaObjetoInside(documento, usuario);
    } catch (Exception e) {
      e.printStackTrace();
      Assert.assertTrue(false);
    }
    ContenidoFirmaInside contenidoFirma = documento.getFirmas().get(0).getContenidoFirma();
    if (contenidoFirma instanceof ContenidoFirmaCertificadoContenidoBinarioInside) {
      Assert.assertNotNull(
          ((ContenidoFirmaCertificadoContenidoBinarioInside) contenidoFirma).getValorBinario());
    }

  }


  @SuppressWarnings("unchecked")
  private ObjetoInside<?> firmaObjetoInside(@SuppressWarnings("rawtypes") ObjetoInside objetoInside,
      String usuario) throws InSideServiceException {
    @SuppressWarnings("rawtypes")
    ObjetoInside salida = null;
    try {
      salida = objectDefinitions.getSigner(objetoInside.getClass()).sign((T) objetoInside, usuario);
      logger.info("Objeto " + objetoInside.getClass() + " firmado correctamente");
    } catch (InsideServiceObjectSignerException e) {
      logger.error("InsideServiceObjectSignerException: " + e.getMessage(), e);
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return salida;
  }
}


