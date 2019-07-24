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

package es.mpt.dsic.inside.model.converter;

import java.util.ArrayList;
import java.util.List;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterCalendarToXmlCalendarException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterException;
import es.mpt.dsic.inside.model.converter.exception.InsideConverterXmlGregorianCalendar;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideVersion;
import es.mpt.dsic.inside.xml.inside.TipoVersionInside;

public class InsideConverterVersion {

  /**
   * Convierte un objeto ObjetoInsideVersion de Inside al mensaje TipoVersionInside del WS de Inside
   * 
   * @param version
   * @return
   * @throws InsideConverterVersionInsideException
   * @throws InsideWSInternalErrorException
   */
  public static TipoVersionInside versionInsideToXml(ObjetoInsideVersion version)
      throws InsideConverterException {
    TipoVersionInside xmlVersion = new TipoVersionInside();
    xmlVersion.setVersion(version.getVersion());
    try {
      xmlVersion.setFechaVersion(
          InsideConverterXmlGregorianCalendar.calendarToXmlCalendar(version.getFechaVersion()));
    } catch (InsideConverterCalendarToXmlCalendarException e) {
      throw new InsideConverterException("No se puede convertir la fecha de versión", e, false);
    }
    return xmlVersion;
  }

  /**
   * Convierte un objeto TipoVersionInside del WS deInside al objeto ObjetoInsideVersion de Inside
   * 
   * @param xmlVersion
   * @return
   */
  public static ObjetoInsideVersion versionXmlToInside(TipoVersionInside xmlVersion) {
    ObjetoInsideVersion version = new ObjetoInsideVersion(xmlVersion.getVersion(),
        xmlVersion.getFechaVersion().toGregorianCalendar());
    return version;
  }

  /**
   * Convierte una lista de objetos ObjetoInsideVersion de Inside a una lista de TipoVersionInside
   * del Inside WS
   * 
   * @param versiones
   * @return
   * @throws InsideConverterVersionInsideException
   * @throws InsideWSInternalErrorException
   */
  public static List<TipoVersionInside> versionesInsideToXml(List<ObjetoInsideVersion> versiones)
      throws InsideConverterException {
    List<TipoVersionInside> versionesXml = new ArrayList<TipoVersionInside>();
    for (ObjetoInsideVersion versionInside : versiones) {
      // try {
      versionesXml.add(versionInsideToXml(versionInside));
      /*
       * } catch (InsideConverterVersionInsideException e) { throw new
       * InsideConverterVersionInsideException(e); }
       */
    }
    return versionesXml;
  }

  /**
   * Convierte una lista de TipoVersionInside del WS de Inside a una lista de ObjetoInsideVersion de
   * Inside
   * 
   * @param versiones
   * @return
   */
  public static List<ObjetoInsideVersion> versionesXmlToInside(List<TipoVersionInside> versiones) {
    List<ObjetoInsideVersion> versionesInside = new ArrayList<ObjetoInsideVersion>();
    for (TipoVersionInside versionXml : versiones) {
      versionesInside.add(versionXmlToInside(versionXml));
    }
    return versionesInside;
  }



}
