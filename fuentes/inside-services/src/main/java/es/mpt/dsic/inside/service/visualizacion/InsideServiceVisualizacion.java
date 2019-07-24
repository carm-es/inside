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

package es.mpt.dsic.inside.service.visualizacion;

import java.util.List;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidacionInfo;
import es.mpt.dsic.eeutil.client.visDocExp.model.Item;
import es.mpt.dsic.infofirma.model.InfoFirmaElectronica;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.visualizacion.exception.InsideServiceVisualizacionException;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

public interface InsideServiceVisualizacion {

  public ObjetoExpedienteInside expedienteConVisualizacionIndice(ObjetoExpedienteInside expediente)
      throws InsideServiceVisualizacionException;

  public ObjetoExpedienteInside expedienteConVisualizacionIndice(ObjetoExpedienteInside expediente,
      OpcionesVisualizacionIndice opcionesVisualizacion) throws InsideServiceVisualizacionException;

  public ResultadoVisualizacionDocumento visualizacionDocumento(ObjetoDocumentoInside documento,
      byte[] bytesContenido) throws InsideServiceVisualizacionException;

  public ResultadoVisualizacionDocumento visualizacionDocumento(ObjetoDocumentoInside documento,
      OpcionesVisualizacionIndice opcionesVisualizacion, byte[] bytesContenido)
      throws InsideServiceVisualizacionException;

  public boolean isActivo();

  byte[] visualizarContenidoOriginal(ObjetoDocumentoInside documento)
      throws InsideServiceVisualizacionException;

  Item documentoInsideToItemVisualizacion(ObjetoDocumentoInside documento, byte[] bytesContenido)
      throws InsideServiceVisualizacionException;

  public InfoFirmaElectronica llamadaInfoFirmaService(byte[] firma, boolean obtenerDatosFirmados,
      boolean obtenerFirmantes, boolean obtenerTipoFirma, byte[] bytesContenido)
      throws InfoFirmaServiceException;

  public ResultadoValidacionInfo llamadaValidacionFirmaService(byte[] firma,
      String datosFirmadosBase64) throws InfoFirmaServiceException;

  List<String> obtenerPlantillas() throws InsideServiceVisualizacionException;

  TipoResultadoVisualizacionDocumentoInside visualizacionConPlantilla(String plantilla,
      TipoDocumentoVisualizacionInside tipoDocumentoVisualizacionInside)
      throws InsideServiceVisualizacionException;
}
