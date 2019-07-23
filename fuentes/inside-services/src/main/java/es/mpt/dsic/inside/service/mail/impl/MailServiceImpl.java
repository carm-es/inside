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

package es.mpt.dsic.inside.service.mail.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteToken;
import es.mpt.dsic.inside.service.mail.MailService;


/**
 * Servicio de envío de emails
 */
public class MailServiceImpl implements MailService {

  protected static final Log logger = LogFactory.getLog(MailServiceImpl.class);

  /** wrapper de Spring sobre javax.mail */
  private JavaMailSenderImpl mailSender;

  public void setMailSender(JavaMailSenderImpl mailSender) {
    this.mailSender = mailSender;
  }

  /** correo electrónico del remitente */
  private String from;

  private String asunto;

  private String cabeceraMensaje;

  private String temporal;

  private String texto;
  private String textoXML;
  private String respuesta;
  private String fechaQueCaducaToken;


  public String getTextoXML() {
    return textoXML;
  }

  public void setTextoXML(String textoXML) {
    this.textoXML = textoXML;
  }

  public String getTexto() {
    return texto;
  }

  public void setTexto(String texto) {
    this.texto = texto;
  }

  public String getRespuesta() {
    return respuesta;
  }

  public void setRespuesta(String respuesta) {
    this.respuesta = respuesta;
  }

  public String getFechaQueCaducaToken() {
    return fechaQueCaducaToken;
  }

  public void setFechaQueCaducaToken(String fechaQueCaducaToken) {
    this.fechaQueCaducaToken = fechaQueCaducaToken;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getFrom() {
    return from;
  }

  public String getCabeceraMensaje() {
    return cabeceraMensaje;
  }

  public void setCabeceraMensaje(String cabeceraMensaje) {
    this.cabeceraMensaje = cabeceraMensaje;
  }

  public String getAsunto() {
    return asunto;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public String getTemporal() {
    return temporal;
  }

  public void setTemporal(String temporal) {
    this.temporal = temporal;
  }

  /** flag para indicar si está activo el servicio */
  public boolean active = true;

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  private String getURLEntorno(String URLDeEntornoEjecucion) {
    String urlEntorno = "";
    String[] partesURL = URLDeEntornoEjecucion.split("/");
    urlEntorno = urlEntorno + partesURL[0] + "//" + partesURL[2] + "/" + partesURL[3] + "\n";

    return urlEntorno;
  }

  public String sendToken(ObjetoExpedienteToken objetoExpedienteToken, String idSesion,
      String URLDeEntornoEjecucion) throws Exception {

    String to = objetoExpedienteToken.getMailEnvioToken();
    String noActivoEnvioCorreo = "";
    respuesta = "A: '" + to + "'";
    fechaQueCaducaToken = "";
    texto = getCabeceraMensaje() + "\n" + "Dirección: " + getURLEntorno(URLDeEntornoEjecucion);

    // el servicio esta activo?
    if (!active)
      return noActivoEnvioCorreo;


    // plantilla para el envío de email
    MimeMessage message = mailSender.createMimeMessage();

    try {
      // el flag a true indica que va a ser multipart
      MimeMessageHelper helper = new MimeMessageHelper(message, true);

      construirMensaje(objetoExpedienteToken, to, helper);

      adjuntarFichero(objetoExpedienteToken, idSesion, helper);

      helper.setText(texto);

      // el envío
      this.mailSender.send(message);
      logger.info("MailServiceImpl.sendToken: Envio de correo finalizado");
      return respuesta;


    } catch (Exception e) {
      logger.error("Error en el envio del correo de Credenciales de Acceso" + e);
      throw new Exception("Error en el envio de correo de Credenciales de Acceso");
    }


  }



  // forma el mensaje del correo
  private void construirMensaje(ObjetoExpedienteToken objetoExpedienteToken, String to,
      MimeMessageHelper helper) throws MessagingException {
    // settings de los parámetros del envío

    try {
      String[] direcciones = to.split(";");
      helper.setTo(direcciones);
      helper.setSubject(getAsunto());
      helper.setFrom(getFrom());

      if (objetoExpedienteToken.getFechaCaducidad() != null) {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        fechaQueCaducaToken = format.format(objetoExpedienteToken.getFechaCaducidad());

        texto = texto + "\n\nEl Credencial de Acceso tiene fecha de caducidad en: "
            + fechaQueCaducaToken + ".";
      } else {
        fechaQueCaducaToken = "Este_token_no_caduca";
      }


      textoXML = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" + "<token>\n" + "\t<Identificador>"
          + objetoExpedienteToken.getIdentificador() + "</Identificador>\n" + "\t<CSV>"
          + objetoExpedienteToken.getCsv() + "</CSV>\n" + "\t<UUID>"
          + objetoExpedienteToken.getUuid() + "</UUID>\n" + "\t<FechaCaducidad>"
          + fechaQueCaducaToken + "</FechaCaducidad>\n" + "</token>";

      texto = texto + "\nIdentificador ... " + objetoExpedienteToken.getIdentificador()
          + "\nCSV ............. " + objetoExpedienteToken.getCsv() + "\nUUID ............ "
          + objetoExpedienteToken.getUuid();



      if (objetoExpedienteToken.getAsunto() != null
          && !objetoExpedienteToken.getAsunto().trim().equals("")) {
        texto = texto + "\n\nEl Token tiene asunto: " + objetoExpedienteToken.getAsunto() + ".";
      }

    } catch (MessagingException e) {
      logger.error("Error al construir el mensaje del correo Credenciales de Acceso" + e);
      e.printStackTrace();
      throw new MessagingException(
          "Error al construir el mensaje del correo Credenciales de Acceso" + e);
    }

  }



  // adjunta fichero a correo
  private void adjuntarFichero(ObjetoExpedienteToken objetoExpedienteToken, String idSesion,
      MimeMessageHelper helper) throws IOException {
    // adjuntar informacion en fichero como adjunto
    String filename = objetoExpedienteToken.getIdentificador() + "_" + fechaQueCaducaToken + ".xml";
    try {
      String pathTemp = escribir(textoXML.getBytes(), idSesion, filename, true);
      if (pathTemp != null && !pathTemp.trim().equals("")) {
        FileSystemResource file = new FileSystemResource(new File(pathTemp));
        helper.addAttachment(filename, file);
        respuesta = respuesta + ". Adjunto '" + filename + "'.";
        texto = texto + "\n\n\nSe ha adjuntado un fichero con los Credenciales de Acceso llamado: "
            + filename;
      }

    } catch (Exception e) {
      logger.error("Error al adjuntar fichero en correo de Credenciales de Acceso" + e);
      e.printStackTrace();
      throw new IOException("Error al adjuntar fichero en correo de Credenciales de Acceso" + e);
    }
  }



  // escribir fichero token adjunto en temporal
  private String escribir(byte[] data, String folder, String filename, boolean create)
      throws IOException {
    logger.debug("Inicio TemporalDataBusinessServiceImpl.escribir");

    // creamos el path
    StringBuilder path = new StringBuilder(temporal);
    path.append(folder);
    path.append(File.separator);
    path.append(filename);

    FileOutputStream output = null;
    try {
      File dir = new File(temporal + folder);
      File file = new File(path.toString());
      if (dir.exists()) {
        if (create && file.exists()) {
          file.delete();
        }
      } else {
        dir.mkdir();
      }

      if (create) {
        file.createNewFile();
        output = new FileOutputStream(file);
        output.write(data);
      } else {
        output = new FileOutputStream(path.toString(), true);
        output.write(data);
      }
    } finally {
      output.close();
    }

    logger.debug("Fin MailServiceImpl.escribir");
    return path.toString();
  }


}
