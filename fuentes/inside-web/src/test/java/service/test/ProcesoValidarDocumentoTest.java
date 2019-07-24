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

package service.test;

import java.io.File;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.util.FileUtils;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.web.security.authentication.UserAuthentication;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionValidacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoOpcionesValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:es/mpt/dsic/inside/context/inside-ws-context.xml",
    "classpath:es/mpt/dsic/inside/context/inside-security-context.xml",
    "classpath:es/mpt/dsic/inside/context/inside-context.xml"})
public class ProcesoValidarDocumentoTest extends TestCase

{

  @Autowired
  private InsideUtilService insideUtilService;

  @Autowired
  private InSideService insideService;

  @Autowired
  FileUtils fileUtils;

  /********************************************************************************************************************************************************
   * NECESARIO PARA VALIDAR CONEXIONES HTTPS
   ********************************************************************************************************************************************************/

  public static WSCredentialInside INFO_CREDENCIALES;

  public static String CONFIG_PATH =
      "C:\\desarrollo\\workspaceInside\\inside-web\\src\\config\\local";
  public static final String TRUSTSTORE_FILE = "trustStore.jks";

  static {
    INFO_CREDENCIALES = new WSCredentialInside();
    INFO_CREDENCIALES.setIdaplicacion("prueba");
    INFO_CREDENCIALES.setPassword("test");

    UserAuthentication authActualizado = new UserAuthentication(INFO_CREDENCIALES.getIdaplicacion(),
        null, null, null, INFO_CREDENCIALES.getIdaplicacion(), null);
    SecurityContextHolder.getContext().setAuthentication(authActualizado);

    System.setProperty("javax.net.ssl.trustStore", CONFIG_PATH + File.separator + TRUSTSTORE_FILE);
    System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
    System.setProperty("javax.net.ssl.trustStoreType", "JKS");

    System.setProperty("config.path",
        "C:\\desarrollo\\workspaceInside\\inside-web\\src\\config\\local");
    System.setProperty("local_home_app", "C\\Temp\\inside");

  }

  /********************************************************************************************************************************************************
   * NECESARIO PARA VALIDAR CONEXIONES HTTPS
   ********************************************************************************************************************************************************/

  @Test
  public void testValidarDocumento() {
    System.out.println("Inicio testValidarDocumento");

    TipoDocumentoValidacionInside documentoValidacion = new TipoDocumentoValidacionInside();
    TipoResultadoValidacionDocumentoInside resultado = null;

    documentoValidacion.setOpcionesValidacionDocumento(new TipoOpcionesValidacionDocumentoInside());

    byte[] data = null;

    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File input = new File(classLoader.getResource("ProcesoValidarDocumento_Doc1.xml").getPath());
      data = org.apache.commons.io.FileUtils.readFileToByteArray(input);
      documentoValidacion.setContenido(data);
      resultado = insideUtilService.validarDocumentoEniFile(documentoValidacion);
      assertNotNull(resultado);
      // valida tres cosas: shema,dir3 y firma
      assertTrue(resultado.getValidacionDetalle().size() == 3);

      boolean validaFirma = false;
      for (int i = 0; i < resultado.getValidacionDetalle().size(); i++) {
        validaFirma = validaFirma || resultado.getValidacionDetalle().get(i).getTipoValidacion()
            .equals(TipoOpcionValidacionDocumento.fromValue("TOVD03"));

      }
      assertTrue(validaFirma);

    } catch (Exception e1) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e1.getMessage()));

    }

  }

  @Test
  public void testValidarDocumentoConReferenciaNoURIInterna() {
    System.out.println("Inicio testValidarDocumentoConReferenciaInterna");

    TipoDocumentoValidacionInside documentoValidacion = new TipoDocumentoValidacionInside();
    TipoResultadoValidacionDocumentoInside resultado = null;

    documentoValidacion.setOpcionesValidacionDocumento(new TipoOpcionesValidacionDocumentoInside());

    byte[] data = null;

    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File input =
          new File(classLoader.getResource("ProcesoValidarDocumento_DocRefInterna.xml").getPath());
      data = org.apache.commons.io.FileUtils.readFileToByteArray(input);
      documentoValidacion.setContenido(data);
      resultado = insideUtilService.validarDocumentoEniFile(documentoValidacion);
      assertNotNull(resultado);
      // valida tres cosas: shema,dir3 y firma
      assertTrue(resultado.getValidacionDetalle().size() == 3);

      boolean validaFirma = false;
      for (int i = 0; i < resultado.getValidacionDetalle().size(); i++) {
        validaFirma = validaFirma || resultado.getValidacionDetalle().get(i).getTipoValidacion()
            .equals(TipoOpcionValidacionDocumento.fromValue("TOVD03"));

      }
      assertTrue(validaFirma);

    } catch (Exception e1) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e1.getMessage()));

    }

  }

  @Test
  public void testValidarDocumento_BIGFILE() {
    System.out.println("Inicio testValidarDocumento_BIGFILE");

    TipoDocumentoValidacionInside documentoValidacion = new TipoDocumentoValidacionInside();
    TipoResultadoValidacionDocumentoInside resultado = null;

    documentoValidacion.setOpcionesValidacionDocumento(new TipoOpcionesValidacionDocumentoInside());

    byte[] data = null;

    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File input =
          new File(classLoader.getResource("ProcesoValidarDocumento_BigFile.xml").getPath());
      data = org.apache.commons.io.FileUtils.readFileToByteArray(input);
      documentoValidacion.setContenido(data);
      resultado = insideUtilService.validarDocumentoEniFile(documentoValidacion);
      assertNotNull(resultado);

      // valida dos cosas: shema,dir3
      assertTrue(resultado.getValidacionDetalle().size() == 2);

      boolean validaFirma = false;
      for (int i = 0; i < resultado.getValidacionDetalle().size(); i++) {
        validaFirma = validaFirma || resultado.getValidacionDetalle().get(i).getTipoValidacion()
            .equals(TipoOpcionValidacionDocumento.fromValue("TOVD03"));

      }
      assertTrue(!validaFirma);
    } catch (Exception e1) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e1.getMessage()));

    }

  }

  public MessageObject getErrorMessage(String message) {
    return new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, message);
  }

}
