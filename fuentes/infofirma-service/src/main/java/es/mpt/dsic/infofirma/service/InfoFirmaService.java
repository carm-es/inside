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

package es.mpt.dsic.infofirma.service;

import es.mpt.dsic.eeutil.client.model.DatosFirmados;
import es.mpt.dsic.eeutil.client.operFirma.model.InSideException;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidacionInfo;
import es.mpt.dsic.eeutil.client.operFirma.model.ResultadoValidarCertificado;
import es.mpt.dsic.infofirma.model.FirmaElectronica;
import es.mpt.dsic.infofirma.model.InfoFirmaElectronica;
import es.mpt.dsic.infofirma.model.OpcionesInfoFirma;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceException;
import es.mpt.dsic.infofirma.service.exception.InfoFirmaServiceNoEsFirmaException;

public interface InfoFirmaService {

  public InfoFirmaElectronica getInfoFirma(FirmaElectronica firma, OpcionesInfoFirma opciones,
      byte[] bytesContenido) throws InfoFirmaServiceNoEsFirmaException, InfoFirmaServiceException;

  public ResultadoValidacionInfo validacionFirma(FirmaElectronica firma, String tipoFirma,
      DatosFirmados datosFirmados) throws InfoFirmaServiceException;

  public ResultadoValidarCertificado getInfoCertificate(String certificate)
      throws InfoFirmaServiceException, InSideException;
}
