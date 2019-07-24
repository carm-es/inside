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

package es.mpt.dsic.inside.service.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang.StringUtils;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoOpcionValidacionExpediente;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionDetalleExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;

public class InsideWSUtils {

  public static XMLGregorianCalendar stringToXmlCalendar(String fecha)
      throws ParseException, DatatypeConfigurationException {
    XMLGregorianCalendar retorno = null;
    GregorianCalendar calender = new GregorianCalendar();
    calender.setTime(stringToJavaDate(fecha));
    retorno = DatatypeFactory.newInstance().newXMLGregorianCalendar(calender);
    return retorno;
  }

  public static XMLGregorianCalendar stringToXmlCalendar(String fecha, String format)
      throws ParseException, DatatypeConfigurationException {
    XMLGregorianCalendar retorno = null;
    GregorianCalendar calender = new GregorianCalendar();

    // 4 digitos un guion dos digitos un guion dos digitos
    if (fecha.matches("\\d{4}-\\d{2}-\\d{2}"))
      format = WebConstants.FORMATO_FECHA_DEFECTO2;
    else
      format = WebConstants.FORMATO_FECHA_DEFECTO;

    calender.setTime(stringToJavaDate(fecha, format));
    retorno = DatatypeFactory.newInstance().newXMLGregorianCalendar(calender);
    return retorno;
  }

  public static Date stringToJavaDate(String sDate) throws ParseException {
    Date date = null;
    date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(sDate);
    return date;
  }

  public static Date stringToJavaDate(String sDate, String format) throws ParseException {
    Date date = null;
    date = new SimpleDateFormat(format).parse(sDate);
    return date;
  }


  public static Date stringToJavaDateToken(String sDate) throws ParseException {
    Date date = null;
    String format;
    // 4 digitos un guion dos digitos un guion dos digitos
    if (sDate.matches("\\d{4}-\\d{2}-\\d{2}"))
      format = WebConstants.FORMATO_FECHA_DEFECTO2;
    else
      format = WebConstants.FORMATO_FECHA_DEFECTO;

    date = new SimpleDateFormat(format).parse(sDate);
    return date;
  }


  // Cuando un expediente no tiene firma, la validación indica que no es
  // correcta, pero se debe considerar válida debido
  // a que GInside no firma el índice de los expedientes al no poder comprobar
  // la veracidad del índice dado. Este método
  // comprueba si la validación de un expediente contra los xsd es incorrecta
  // por no estar firmado el índice, en cuyo caso
  // cambia el resultado de dicha validación a correcto
  public static void setExpedienteSinFirmaAsValid(
      TipoResultadoValidacionExpedienteInside expedientiEniFile) {
    ArrayList<TipoResultadoValidacionDetalleExpedienteInside> results =
        (ArrayList<TipoResultadoValidacionDetalleExpedienteInside>) expedientiEniFile
            .getValidacionDetalle();
    boolean encontrado = false;
    for (int i = 0; ((i < results.size()) && (!encontrado)); i++) {
      TipoResultadoValidacionDetalleExpedienteInside tipoResult = results.get(i);
      if (tipoResult.getTipoValidacion().value()
          .equals(TipoOpcionValidacionExpediente.TOVE_01.value())
          && !tipoResult.isResultadoValidacion()
          && (tipoResult.getDetalleValidacion()
              .contains(WebConstants.MSG_ERROR_VALIDACION_EXPE_SIN_FIRMA)
              || tipoResult.getDetalleValidacion()
                  .contains(WebConstants.MSG_ERROR_VALIDACION_EXPE_SIN_FIRMA_ADITIONAL))) {
        tipoResult.setResultadoValidacion(true);
        tipoResult.setDetalleValidacion(WebConstants.MSG_VALIDACION_EXP_CORRECTA);
        encontrado = true;
      }

    }
  }

  /**
   * Leer fichero
   * 
   * @param filePath fichero
   * @return datos del fichero
   * @throws IOException error
   */
  public static String readFile(String filePath) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line = null;
    StringBuilder stringBuilder = new StringBuilder();
    String ls = System.getProperty("line.separator");
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
      stringBuilder.append(ls);
    }
    reader.close();
    return stringBuilder.toString();
  }



  /* devuelve true si today es despues de date */
  public static boolean todayAfterDate(Date today, Date date) {

    return today.after(date);
  }


  /* devuelve true si today es antes de dateCaducidad */
  public static boolean todayBeforDate(Date today, Date dateCaducidad) {
    if (dateCaducidad == null)
      return false;
    else
      return today.before(dateCaducidad);
  }


  /* devuelve true si string2 esta contenida en string1 */
  public static boolean containsString1ToString2(String string1, String string2) {
    if (string1 == null || string1.trim().equals("") || string2 == null
        || string2.trim().equals(""))
      return false;
    else
      return string1.toLowerCase().contains(string2.toLowerCase());
  }

  public static String dateToString(Date fecha, String formato) {
    DateFormat df = null;
    if (!StringUtils.isBlank(formato)) {
      df = new SimpleDateFormat(formato);
    } else {
      df = new SimpleDateFormat(WebConstants.FORMATO_FECHA_DEFECTO);
    }
    return df.format(fecha);
  }

  public static String hashData(byte[] data, String algoritm) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance(algoritm);
    messageDigest.update(data, 0, data.length);
    String output = new BigInteger(1, messageDigest.digest()).toString(16);
    // completar hasta 32 digitos
    output = StringUtils.leftPad(output, 32, "0");
    return output;
  }


  /**
   * Escribir datos en fichero
   * 
   * @param data datos
   * @param path ruta
   * @param file fichero
   * @throws IOException error
   */
  public static void writeFile(File path, String file, byte[] data) throws Exception {

    try {
      if (path.exists()) {
        if (path.canWrite()) {
          FileOutputStream fileOut = new FileOutputStream(file);
          fileOut.write(data);
          fileOut.close();
        }
      }
    } catch (FileNotFoundException e) {

      throw e;
    } catch (IOException e) {

      throw e;
    }
  }

}
