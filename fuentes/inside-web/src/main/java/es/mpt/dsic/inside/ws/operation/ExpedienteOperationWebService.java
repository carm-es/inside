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

package es.mpt.dsic.inside.ws.operation;


import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.TipoExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;

public interface ExpedienteOperationWebService {

  // convertirExpedienteAEni
  public TipoExpedienteEniFileInside crearExpedienteEniFileXMLServicio(
      TipoExpedienteConversionInside expediente, TipoMetadatosAdicionales metadatosAdicionales,
      WSCredentialInside info) throws InsideWSException;


  // getExpedienteEniFile
  public TipoExpedienteEniFileInside recuperarExpedienteEniFileXMLServicio(
      String expedienteIdentificador, WSCredentialInside info) throws InsideWSException;


  // altaExpedienteEniXml
  public TipoExpedienteEniFileInside insertarExpedienteEniFileXMLServicio(
      TipoExpedienteEniFileInside expedienteEniXml, WSCredentialInside info)
      throws InsideWSException;


  // eliminarExpedienteEniFileXML
  public TipoExpedienteEniFileInside eliminarExpedienteEniFileXMLServicio(
      String expedienteIdentificador, WSCredentialInside info) throws InsideWSException;



  // validarExpedienteEniFileXML
  public TipoResultadoValidacionExpedienteInside validarExpedienteEniFileXMLServicio(
      TipoExpedienteEniFileInside expedienteEniXml, WSCredentialInside info)
      throws InsideWSException;

}
