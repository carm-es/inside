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

package es.mpt.dsic.inside.ws.service.impl;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import es.mpt.dsic.inside.util.ws.security.CredentialUtil;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.operation.InsideOperationWebService;
import es.mpt.dsic.inside.ws.service.DocumentoUserTokenMtomWebService;
import es.mpt.dsic.inside.xml.inside.ExpedienteInsideInfo;
import es.mpt.dsic.inside.xml.inside.ws.documento.conversion.TipoDocumentoConversionInside;
import es.mpt.dsic.inside.xml.inside.ws.objetoEniXMLBytes.ObjetoEniXMLBytes;



@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.DocumentoUserTokenMtomWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
    portName = "DocumentoUserTokenMtomWSPort", serviceName = "DocumentoUserTokenMtomWS")
public class DocumentoUserTokenMtomWebServiceImpl implements DocumentoUserTokenMtomWebService {

  @Autowired
  InsideOperationWebService insideOperationWebService;

  @Autowired
  private CredentialUtil credentialUtil;

  @Resource
  private WebServiceContext wsContext;


  @Override
  public ObjetoEniXMLBytes crearDocumentoEniFileXML(TipoDocumentoConversionInside documento)
      throws InsideWSException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Secured("ROLE_LEER_EXPEDIENTE")
  public ObjetoEniXMLBytes recuperarDocumentoEniFileXML(String documentoIdentificador)
      throws InsideWSException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Secured("ROLE_ALTA_EXPEDIENTE")
  public ObjetoEniXMLBytes insertarDocumentoEniFileXML(ObjetoEniXMLBytes documentoMtom)
      throws InsideWSException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public ObjetoEniXMLBytes eliminarDocumentoEniFileXML(String documentoIdentificador)
      throws InsideWSException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ExpedienteInsideInfo validarDocumentoEniFileXML(ObjetoEniXMLBytes documentoMtom)
      throws InsideWSException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ObjetoEniXMLBytes visualizarDocumento(String documentoIdentificador)
      throws InsideWSException {
    // TODO Auto-generated method stub
    return null;
  }
}
