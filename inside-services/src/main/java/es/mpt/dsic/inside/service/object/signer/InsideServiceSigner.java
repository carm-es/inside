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

package es.mpt.dsic.inside.service.object.signer;

import es.mpt.dsic.inside.service.exception.InSideServiceSignerException;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;

public interface InsideServiceSigner {
	
	/**
	 * Firma una cadena con el algoritmo, formato y modo indicados en los parámetros.
	 * @param cadena
	 * @param algoritmoFirma
	 * @param formatoFirma
	 * @param modoFirma
	 * @return bytes de la firma.
	 * @throws InSideServiceSignerException Si no se puede realizar la firma.
	 */
	//public String firmarCadena (String cadena, String algoritmoFirma, String formatoFirma, String modoFirma) throws InSideServiceSignerException;
	
	/**
	 * Firma bytes
	 * @param bytesFichero
	 * @param algoritmoFirma
	 * @param formatoFirma
	 * @param modoFirma
	 * @return bytes de la firma
	 * @throws InSideServiceSignerException Si no se ha podido realizar la firma.
	 */
	public byte [] firmarFichero (byte[] bytesFichero, String algoritmoFirma, String formatoFirma, String modoFirma, WSCredentialInside info) throws InSideServiceSignerException;
	public byte [] firmarFicheroWhitPropertie (byte[] bytesFichero, String algoritmoFirma, String formatoFirma, String modoFirma, String nodeToSign, WSCredentialInside info) throws InSideServiceSignerException;
	
	//public String dameAlgo ();
	
	public boolean isActivo ();
	
}
