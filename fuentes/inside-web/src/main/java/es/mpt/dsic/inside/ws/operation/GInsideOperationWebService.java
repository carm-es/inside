/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.ws.operation;

import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.documento.file.DocumentoEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.conversion.TipoExpedienteConversionInsideWS;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInside;
import es.mpt.dsic.inside.xml.inside.ws.expediente.file.ExpedienteEniFileInsideConMAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.TipoDocumentoValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.documento.resultados.TipoResultadoValidacionDocumentoInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.TipoExpedienteValidacionInside;
import es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.resultados.TipoResultadoValidacionExpedienteInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

public interface GInsideOperationWebService {

	public DocumentoEniFileInside convertirDocumentoAEni(TipoDocumentoConversionInside documento, byte[] contenido,
			Boolean firmar, WSCredentialInside credentialUtil) throws InsideWSException;

	public DocumentoEniFileInsideConMAdicionales convertirDocumentoAEniConMAdicionales(TipoDocumentoConversionInside documento,
			TipoMetadatosAdicionales metadatosAdicionales, byte[] contenido, Boolean firmar, WSCredentialInside credentialUtil) throws InsideWSException;

	public ExpedienteEniFileInside convertirExpedienteAEni(TipoExpedienteConversionInside expediente, String contenidoFirma, WSCredentialInside credentialUtil)
			throws InsideWSException;

	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionales(
			TipoExpedienteConversionInside expediente, TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma, WSCredentialInside credentialUtil)
					throws InsideWSException;

	public ExpedienteEniFileInside convertirExpedienteAEniAutocontenido(TipoExpedienteConversionInsideWS expediente,
			String contenidoFirma, WSCredentialInside credentialUtil) throws InsideWSException;

	public ExpedienteEniFileInsideConMAdicionales convertirExpedienteAEniConMAdicionalesAutocontenido(
			TipoExpedienteConversionInsideWS expediente, TipoMetadatosAdicionales metadatosAdicionales, String contenidoFirma, WSCredentialInside credentialUtil)
					throws InsideWSException;

	public TipoResultadoValidacionExpedienteInside validarExpedienteEniFile(TipoExpedienteValidacionInside expediente, WSCredentialInside credentialUtil)
			throws InsideWSException;

	public TipoResultadoValidacionDocumentoInside validarDocumentoEniFile(TipoDocumentoValidacionInside documento, WSCredentialInside credentialUtil)
			throws InsideWSException;

	public TipoResultadoVisualizacionDocumentoInside visualizarDocumentoEni(TipoDocumentoVisualizacionInside documento, WSCredentialInside credentialUtil)
			throws InsideWSException;

}
