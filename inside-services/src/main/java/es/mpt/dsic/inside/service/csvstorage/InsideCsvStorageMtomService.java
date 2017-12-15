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

package es.mpt.dsic.inside.service.csvstorage;

import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.CSVStorageException;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.DocumentoEniMtomResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.DocumentoMtomResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.EliminarDocumentoResponse;
import csvstorage.es.gob.aapp.csvstorage.webservices.documentmtom.v1.model.GuardarDocumentoResponse;



/**
 * The Interface InsideCsvStorageService.
 */
public interface InsideCsvStorageMtomService {

	
	/**
	 * Guardar documento csv storage.
	 *
	 * @param documento the documento
	 * @return true, if successful
	 * @throws CSVStorageException the CSV storage exception
	 */
	public GuardarDocumentoResponse guardarDocumentoCsvStorage (String dir3, String idEni, byte[] contenido, String mimeType) throws CSVStorageException;
	
	/**
	 * Modificar documento csv storage.
	 *
	 * @param documento the documento
	 * @param dir3 the dir3
	 * @return true, if successful
	 * @throws CSVStorageException the CSV storage exception
	 */
	public GuardarDocumentoResponse modificarDocumentoCsvStorage (String dir3, String idEni, byte[] contenido, String mimeType) throws CSVStorageException;
	
	
	/**
	 * Obtener documento eni csv storage.
	 *
	 * @param identificador the identificador
	 * @param dir3 the dir3
	 * @return the obtener documento eni
	 * @throws CSVStorageException the CSV storage exception
	 */
	public DocumentoMtomResponse obtenerDocumentoCsvStorage(String identificador, String dir3) throws CSVStorageException;
	
	/**
	 * Guardar firma csv storage.
	 * @param contenidoFirmaAlmacenado ContenidoFirmaCertificadoAlmacenableInside
	 * @param dir3 the dir3
	 * @param idEni Identificador de la firma
	 * @return the GuardarDocumentoResponse
	 * @throws CSVStorageException
	 */
	public GuardarDocumentoResponse guardarFirmaCsvStorage (String dir3, String idEni, byte[] contenido, String mimeType) throws CSVStorageException;
	
	/**
	 * Modificar firma csv storage.
	 *
	 * @param id Identificador de la firma
	 * @param contenido contenido de la firma
	 * @param mimetype mimetype de la firma
	 * @return the GuardarDocumentoResponse
	 * @throws CSVStorageException the CSV storage exception
	 */
	
	/**
	 * 
	 * @param contenidoFirmaAlmacenado ContenidoFirmaCertificadoAlmacenableInside
	 * @param dir3 the dir3
	 * @param idEni Identificador de la firma
	 * @return the GuardarDocumentoResponse
	 * @throws CSVStorageException
	 */
	public GuardarDocumentoResponse modificarFirmaCsvStorage (String dir3, String idEni, byte[] contenido, String mimeType) throws CSVStorageException;
	
	/**
	 * Obtener la firma csv storage.
	 *
	 * @param nombreFirma nombre de la firma
	 * @param dir3 the dir3 
	 * @return the DocumentoMtomResponse
	 * @throws CSVStorageException the CSV storage exception
	 */
	public DocumentoMtomResponse obtenerFirmaCsvStorage (String nombreFirma, String dir3) throws CSVStorageException;
	
	DocumentoEniMtomResponse obtenerDocumentoEniCsvStorage(String identificador, String dir3)
			throws CSVStorageException;
	
	EliminarDocumentoResponse eliminarDocumento(String identificador, String dir3)
			throws CSVStorageException;
}
