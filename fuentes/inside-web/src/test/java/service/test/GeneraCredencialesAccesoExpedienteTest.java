package service.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.TestingAuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.ws.operation.impl.InsideOperationWebServiceImpl;
import es.mpt.dsic.inside.xml.inside.TokenExpediente;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    locations = {"classpath:es/mpt/dsic/inside/context/inside-service-context.xml",
        "classpath:es/mpt/dsic/inside/context/inside-ws-context.xml",
        "classpath:es/mpt/dsic/inside/context/inside-security-context.xml",
        "classpath:es/mpt/dsic/inside/context/inside-context.xml"})
public class GeneraCredencialesAccesoExpedienteTest extends TestCase {


  public static WSCredentialInside INFO_CREDENCIALES;

  @Autowired
  private InsideOperationWebServiceImpl insideOperationWebServiceImpl;

  public String aplicacion = "prueba";
  public String password = "test";


  @Before
  public void prepararDatos() {

    INFO_CREDENCIALES = new WSCredentialInside();
    INFO_CREDENCIALES.setIdaplicacion(aplicacion);
    INFO_CREDENCIALES.setPassword(password);

    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(
        INFO_CREDENCIALES.getIdaplicacion(), INFO_CREDENCIALES.getPassword());
    TestingAuthenticationProvider authManagerTest = new TestingAuthenticationProvider();
    Authentication auth = authManagerTest.authenticate(authReq);

    SecurityContextHolder.getContext().setAuthentication(auth);

  }

  @Test
  public void generarCredencialesAccesoExpedienteTest() {
    String apli = aplicacion;
    String identificador = "ES_A14002961_2020_EXP_162";
    String emails = "alejandro.paredes-romera@capgemini.com;alexjandro877@gmail.com";
    String asuntoMail = "test credenciales";
    String dir3 = "E04975701";
    String NIFs = "48556839K";
    String fechaCaducidad = "2020-04-01";
    System.out.println(
        "-------------------------------------------Inicio test credenciales todo bien-------------------------------------------");

    try {
      TokenExpediente respuesta = insideOperationWebServiceImpl.getCredencialesAccesoExpediente(
          apli, identificador, emails, asuntoMail, dir3, NIFs, fechaCaducidad);

      assertNotNull(respuesta);
      System.out.println("identificador -->" + respuesta.getIdentificador());
      System.out.println("csv -->" + respuesta.getCsv());
      System.out.println("uuid -->" + respuesta.getUuid());
      System.out.println("fichero -->" + respuesta.getFichero());
      System.out.println("resultado correo -->" + respuesta.getResultadoEnvioCorreo());

    } catch (InsideWSException e) {
      System.out.println(e.getMessage());
      assertFalse(true);
    }
  }

  @Test
  public void generarCredencialesAccesoExpedienteSinNIFniDIR3Test() {
    String apli = aplicacion;
    String identificador = "ES_A14002961_2020_EXP_162";
    String emails = "alejandro.paredes-romera@capgemini.com";
    String asuntoMail =
        "-------------------------------------------inicio test credenciales sin nif ni dir3-------------------------------------------";
    String dir3 = "";
    String NIFs = "";
    String fechaCaducidad = "2020-04-01";
    System.out.println("Inicio test sin NIF ni DIR3");

    try {
      TokenExpediente respuesta = insideOperationWebServiceImpl.getCredencialesAccesoExpediente(
          apli, identificador, emails, asuntoMail, dir3, NIFs, fechaCaducidad);

      System.out.println("Debería salir una excepcion " + respuesta.getIdentificador());
      assertFalse(true);

    } catch (InsideWSException e) {
      System.out.println("código de error: " + e.getFaultInfo().getCodigo());
      System.out.println("descripcion: " + e.getFaultInfo().getDescripcion());
      if ((e.getFaultInfo().getDescripcion())
          .equals(InsideWsErrors.DIR3_NIF_NECESARIO.getValue().getDescripcion())
          && e.getFaultInfo().getCodigo() == 417)
        assertTrue(true);
      else
        assertFalse(true);
    }
  }

  @Test
  public void generarCredencialesAccesoExpedienteMalNIFTest() {
    String apli = aplicacion;
    String identificador = "ES_A14002961_2020_EXP_162";
    String emails = "alejandro.paredes-romera@capgemini.com";
    String asuntoMail = "test credenciales";
    String dir3 = "E04975701";
    String NIFs = "48556839K;1123jjek;niffalso";
    String fechaCaducidad = "2020-04-01";
    System.out.println(
        "-------------------------------------------Inicio test credeniales nif mal puesto-------------------------------------------");

    try {
      TokenExpediente respuesta = insideOperationWebServiceImpl.getCredencialesAccesoExpediente(
          apli, identificador, emails, asuntoMail, dir3, NIFs, fechaCaducidad);

      System.out.println("Debería salir una excepcion " + respuesta.getIdentificador());
      assertFalse(true);

    } catch (InsideWSException e) {
      System.out.println("código de error: " + e.getFaultInfo().getCodigo());
      System.out.println("descripcion: " + e.getFaultInfo().getDescripcion());
      if ((e.getFaultInfo().getDescripcion()).equals(
          InsideWsErrors.NIF_MALFORMADO.getValue().getDescripcion() + " : 1123jjek, niffalso")
          && e.getFaultInfo().getCodigo() == 415)
        assertTrue(true);
      else
        assertFalse(true);
    }
  }

  @Test
  public void generarCredencialesAccesoExpedienteMalMailTest() {
    String apli = aplicacion;
    String identificador = "ES_A14002961_2020_EXP_162";
    String emails = "emailMalo;alejandro.paredes-romera@capgemini.com;emailMalo@;@emailMalo.com";
    String asuntoMail = "test credenciales";
    String dir3 = "E04975701";
    String NIFs = "48556839K";
    String fechaCaducidad = "2020-04-01";
    System.out.println(
        "-------------------------------------------Inicio test credenciales mail mal puesto-------------------------------------------");

    try {
      TokenExpediente respuesta = insideOperationWebServiceImpl.getCredencialesAccesoExpediente(
          apli, identificador, emails, asuntoMail, dir3, NIFs, fechaCaducidad);

      System.out.println("Debería salir una excepcion " + respuesta.getIdentificador());
      assertFalse(true);

    } catch (InsideWSException e) {
      System.out.println("código de error: " + e.getFaultInfo().getCodigo());
      System.out.println("descripcion: " + e.getFaultInfo().getDescripcion());
      if ((e.getFaultInfo().getDescripcion())
          .equals(InsideWsErrors.MAIL_MALFORMADO.getValue().getDescripcion()
              + " : emailMalo, emailMalo@, @emailMalo.com")
          && e.getFaultInfo().getCodigo() == 416)
        assertTrue(true);
      else
        assertFalse(true);
    }
  }

  @Test
  public void generarCredencialesAccesoExpedienteMalFechaTest() {
    String apli = aplicacion;
    String identificador = "ES_A14002961_2020_EXP_162";
    String emails = "alejandro.paredes-romera@capgemini.com";
    String asuntoMail = "test credenciales";
    String dir3 = "E04975701";
    String NIFs = "48556839K";
    String fechaCaducidad = "01/01/2020";
    System.out.println(
        "-------------------------------------------Inicio test credenciales fecha mal puesta-------------------------------------------");

    try {
      TokenExpediente respuesta = insideOperationWebServiceImpl.getCredencialesAccesoExpediente(
          apli, identificador, emails, asuntoMail, dir3, NIFs, fechaCaducidad);

      System.out.println("Debería salir una excepcion " + respuesta.getIdentificador());
      assertFalse(true);

    } catch (InsideWSException e) {
      System.out.println("código de error: " + e.getFaultInfo().getCodigo());
      System.out.println("descripcion: " + e.getFaultInfo().getDescripcion());
      if ((e.getFaultInfo().getDescripcion())
          .equals(InsideWsErrors.FECHA_MALFORMATO.getValue().getDescripcion() + " : 01/01/2020")
          && e.getFaultInfo().getCodigo() == 418)
        assertTrue(true);
      else
        assertFalse(true);
    }
  }
}
