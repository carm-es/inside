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

import java.util.Calendar;
import java.util.GregorianCalendar;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.apache.xerces.impl.dv.util.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterXmlGregorianCalendar;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.util.WebConstants;
import es.mpt.dsic.inside.web.object.MessageObject;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.EnumeracionEstadoElaboracion;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoDocumental;
import es.mpt.dsic.inside.xml.eni.documento.metadatos.TipoEstadoElaboracion;
import es.mpt.dsic.inside.xml.inside.TipoDocumentoInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside.MetadatosEni;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:es/mpt/dsic/inside/context/inside-ws-context.xml",
    "classpath:es/mpt/dsic/inside/context/inside-security-context.xml",
    "classpath:es/mpt/dsic/inside/context/inside-context.xml"})
public class GenerarDocumentoTest extends TestCase

{

  String idDocumentoLittle = "ES_E04975701_2017_LITTLE_PARA_TEST";

  @Autowired
  private InsideUtilService insideUtilService;

  public static WSCredentialInside INFO_CREDENCIALES;

  static {
    INFO_CREDENCIALES = new WSCredentialInside();
    INFO_CREDENCIALES.setIdaplicacion("prueba");
    INFO_CREDENCIALES.setPassword("test");

  }

  /********************************************************************************************************************************************************
   * NECESARIO PARA VALIDAR CONEXIONES HTTPS
   ********************************************************************************************************************************************************/

  @Test
  public void testConvertirDocumentoLittle() {
    System.out.println("Inicio testGenerarDocumentoLittle");
    TipoDocumentoConversionInside tDocConversion = null;
    TipoMetadatosAdicionales metadatosAdicionales = null;
    byte[] bytesContenido = null;
    boolean firmar = true;
    try {
      tDocConversion = new TipoDocumentoConversionInside();
      tDocConversion.setContenido(bytesContenido);
      tDocConversion.setFirmadoConCertificado(false);

      MetadatosEni metadatosEni = new MetadatosEni();
      TipoEstadoElaboracion estadoElaboracion = new TipoEstadoElaboracion();
      estadoElaboracion.setValorEstadoElaboracion(EnumeracionEstadoElaboracion.EE_01);
      metadatosEni.setEstadoElaboracion(estadoElaboracion);

      Calendar actualDate = new GregorianCalendar(2017, 5, 22);
      metadatosEni.setFechaCaptura(
          InsideConverterXmlGregorianCalendar.dateToXmlCalendar(actualDate.getTime()));

      metadatosEni.setIdentificador(idDocumentoLittle);

      metadatosEni.setOrigenCiudadanoAdministracion(false);

      metadatosEni.getOrgano().add("E04975701");

      metadatosEni.setTipoDocumental(TipoDocumental.TD_01);

      tDocConversion.setMetadatosEni(metadatosEni);
      TipoDocumentoInsideConMAdicionales docInside =
          insideUtilService.convertirDocumentoAEniConMAdicionales(tDocConversion,
              metadatosAdicionales, bytesContenido, firmar, INFO_CREDENCIALES);

      String xml = Base64.encode(insideUtilService.generateDocXml(docInside));

      Assert.assertEquals(xml, "1");
    } catch (InSideServiceException e3) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e3.getMessage()));

    } catch (Exception e1) {
      throw new AssertionError(
          "Error testImportarDocumento_YaExiste: " + getErrorMessage(e1.getMessage()));

    }
  }

  public MessageObject getErrorMessage(String message) {
    return new MessageObject(WebConstants.MESSAGE_LEVEL_ERROR, message);
  }

}
