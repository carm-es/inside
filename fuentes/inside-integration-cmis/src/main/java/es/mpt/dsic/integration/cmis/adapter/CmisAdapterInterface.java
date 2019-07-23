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

package es.mpt.dsic.integration.cmis.adapter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.commons.exceptions.CmisBaseException;
import es.mpt.dsic.integration.cmis.exception.RepositoryCmisException;
import es.mpt.dsic.integration.cmis.model.ContenidoDocumento;

public interface CmisAdapterInterface {

  //
  public Folder altaCarpeta(final String folderName, final String[] ruta, final String objectTypeId,
      final Map<String, Object> metadatos, boolean crearRuta) throws RepositoryCmisException;

  public Folder getInfoCarpeta(final String folderId) throws RepositoryCmisException;

  public List<Map<String, Object>> getDocsCarpeta(final String folderId)
      throws RepositoryCmisException;

  public Folder modificaCarpeta(final String folderId, final Map<String, Object> metadatos)
      throws RepositoryCmisException;

  //
  public Document altaDocumento(final String documentName, final String[] ruta,
      final String objectTypeId, final byte[] contenido, final String tipoMIME,
      final Map<String, Object> metadatos, boolean crearRuta) throws RepositoryCmisException;

  public Document altaDocumento(final String documentName, Folder folder, String objectTypeId,
      byte[] contenido, String tipoMIME, Map<String, Object> metadatos, boolean crearRuta)
      throws RepositoryCmisException;

  //
  public Map<String, Object> getInfoDocumento(final String documentId)
      throws RepositoryCmisException;

  public Map<String, Object> getInfoDocumento(final String documentId, final String version)
      throws RepositoryCmisException;

  public Collection<String> getVersionesDocumento(final String documentId)
      throws RepositoryCmisException;

  //
  public ContenidoDocumento getContenidoDoc(final String documentId) throws RepositoryCmisException;

  public ContenidoDocumento getContenidoDoc(final String documentId, final String version)
      throws RepositoryCmisException;

  //
  public Map<String, Object> modificarDocumento(final String documentId,
      final Map<String, Object> metadatos, final ContenidoDocumento contenido)
      throws RepositoryCmisException;

  //
  public void borrarDocumento(final String documentId, final boolean allVersions)
      throws RepositoryCmisException;

  Folder altaCarpeta(String[] path, String objectTypeId, Map<String, Object> metadatos)
      throws RepositoryCmisException;

  Folder createFolder(String[] ruta) throws RepositoryCmisException;

  Folder getFolder(String[] ruta, boolean crearRuta) throws RepositoryCmisException;

  List<Map<String, Object>> getFoldersCarpeta(String folderId) throws RepositoryCmisException;

  public CmisObject getCmisObjectById(String objectId)
      throws CmisBaseException, RepositoryCmisException;

  CmisObject getCmisObject(String objectId, String version)
      throws CmisBaseException, RepositoryCmisException;


}
