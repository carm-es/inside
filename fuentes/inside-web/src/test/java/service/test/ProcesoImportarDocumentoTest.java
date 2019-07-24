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
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.util.FileUtils;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.web.security.authentication.UserAuthentication;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:es/mpt/dsic/inside/context/inside-ws-context.xml",
    "classpath:es/mpt/dsic/inside/context/inside-security-context.xml",
    "classpath:es/mpt/dsic/inside/context/inside-context.xml"})
public class ProcesoImportarDocumentoTest extends TestCase

{

  String idDocumentoBorrar = "ES_E04975701_2017_0000_PARA_TEST";

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
  public void testImportarDocumento() {
    System.out.println("Inicio testImportarDocumento");

    try {
      // primero borramos por si esta dado de alta
      boorarSiExisteDocumento(idDocumentoBorrar);

      // se importa
      ClassLoader classLoader = getClass().getClassLoader();
      File input = new File(classLoader.getResource("ProcesoImportarDocumento_Doc1.xml").getPath());
      byte[] documentoByte = org.apache.commons.io.FileUtils.readFileToByteArray(input);
      ObjetoDocumentoInside doc = insideUtilService.validateDocumentImport(documentoByte);
      ObjetoDocumentoInside documentoImportado =
          insideService.altaDocumento(doc, "11111111H", true);
      assertNotNull(documentoImportado);

    } catch (InSideServiceException e3) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e3.getMessage()));

    } catch (Exception e1) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e1.getMessage()));

    }

  }

  @Test
  public void testImportarDocumento_YaExiste() {
    System.out.println("Inicio testImportarDocumento_YaExiste");
    boolean existe = false;

    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File input = new File(classLoader.getResource("ProcesoImportarDocumento_Doc1.xml").getPath());
      byte[] documentoByte = org.apache.commons.io.FileUtils.readFileToByteArray(input);
      ObjetoDocumentoInside doc = insideUtilService.validateDocumentImport(documentoByte);

      // primero borramos por si existe y le damos de alta para que ya
      // exista
      boorarSiExisteDocumento(idDocumentoBorrar);

      insideService.altaDocumento(doc, "11111111H", true);

      // se checkea que exista en el sistema
      existe = insideUtilService.checkNoExistIdDocumentInside(doc.getIdentificador(), true);
      assertTrue(existe);

    } catch (InSideServiceException e3) {
      if (e3.getMessage()
          .contains("No puede importar este documento al existir ya uno dado de alta en Inside"))
        existe = true;

      assertTrue(existe);

    } catch (Exception e1) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e1.getMessage()));

    }

  }

  // @Test
  // public void testImportarDocumento_Mayor_De_8MB()
  // {
  // System.out.println("Inicio testImportarDocumento_Mayor_De_8MB");
  // boolean tamanio_Mayor_8MB = false;
  //
  //
  // byte[] documentoByte = Base64.decode(DocEnBase64);
  // tamanio_Mayor_8MB = fileUtils.isBigFileKb(documentoByte);
  // if(tamanio_Mayor_8MB)
  // assertTrue(tamanio_Mayor_8MB);
  // else
  // throw new AssertionError("Error testImportarDocumento_Mayor_De_8MB");
  //
  //
  //
  // }

  /*
   * Se borra si existe
   */
  private void boorarSiExisteDocumento(String idDocumentoBorrar) {
    try {
      insideService.deleteDocument(idDocumentoBorrar);
    } catch (Exception e) {
      System.out.println("no existe por lo tanto no se borra");
    }

  }

  public MessageObject getErrorMessage(String message) {
    return new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, message);
  }

}
