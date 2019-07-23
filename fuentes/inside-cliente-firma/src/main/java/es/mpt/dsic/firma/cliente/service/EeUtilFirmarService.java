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

package es.mpt.dsic.firma.cliente.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import es.mpt.dsic.firma.cliente.model.ObjectFactory;

/**
 * This class was generated by Apache CXF 3.0.3 2016-10-25T08:33:00.055+02:00 Generated source
 * version: 3.0.3
 * 
 */
@WebService(targetNamespace = "http://service.ws.inside.dsic.mpt.es/", name = "EeUtilFirmarService")
@XmlSeeAlso({ObjectFactory.class})
public interface EeUtilFirmarService {

  @WebResult(name = "datosSalida", targetNamespace = "")
  @RequestWrapper(localName = "firmaFicheroWithPropertie",
      targetNamespace = "http://service.ws.inside.dsic.mpt.es/",
      className = "es.mpt.dsic.firma.cliente.model.FirmaFicheroWithPropertie")
  @WebMethod(action = "urn:firmaFicheroWithPropertie")
  @ResponseWrapper(localName = "firmaFicheroResponse",
      targetNamespace = "http://service.ws.inside.dsic.mpt.es/",
      className = "es.mpt.dsic.firma.cliente.model.FirmaFicheroResponse")
  public es.mpt.dsic.firma.cliente.model.DatosSalida firmaFicheroWithPropertie(
      @WebParam(name = "aplicacionInfo",
          targetNamespace = "") es.mpt.dsic.firma.cliente.model.ApplicationLogin aplicacionInfo,
      @WebParam(name = "datosEntrada",
          targetNamespace = "") es.mpt.dsic.firma.cliente.model.DatosEntradaFichero datosEntrada);

  @WebResult(name = "datosSalida", targetNamespace = "")
  @RequestWrapper(localName = "firmaFichero",
      targetNamespace = "http://service.ws.inside.dsic.mpt.es/",
      className = "es.mpt.dsic.firma.cliente.model.FirmaFichero")
  @WebMethod(action = "urn:FirmaFichero")
  @ResponseWrapper(localName = "firmaFicheroResponse",
      targetNamespace = "http://service.ws.inside.dsic.mpt.es/",
      className = "es.mpt.dsic.firma.cliente.model.FirmaFicheroResponse")
  public es.mpt.dsic.firma.cliente.model.DatosSalida firmaFichero(
      @WebParam(name = "aplicacionInfo",
          targetNamespace = "") es.mpt.dsic.firma.cliente.model.ApplicationLogin aplicacionInfo,
      @WebParam(name = "datosEntrada",
          targetNamespace = "") es.mpt.dsic.firma.cliente.model.DatosEntradaFichero datosEntrada);
}
