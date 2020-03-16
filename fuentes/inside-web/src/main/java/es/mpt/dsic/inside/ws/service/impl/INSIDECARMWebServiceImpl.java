package es.mpt.dsic.inside.ws.service.impl;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import es.mpt.dsic.inside.ws.exception.InsideExceptionConverter;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.operation.InsideOperationWebService;
import es.mpt.dsic.inside.ws.service.INSIDECARMWebService;
import es.mpt.dsic.inside.xml.inside.TokenExpediente;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;

@WebService(endpointInterface = "es.mpt.dsic.inside.ws.service.INSIDECARMWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService",
    portName = "insideCarmWsPort", serviceName = "insideCarmWs")
public class INSIDECARMWebServiceImpl implements INSIDECARMWebService {

  @Autowired
  InsideOperationWebService insideOperationWebService;

  @Resource
  private WebServiceContext wsContext;

  @Override
  @Secured("ROLE_MODIFICA_EXPEDIENTE")
  public TokenExpediente getCredencialesAccesoExpediente(WSCredentialInside credenciales,
      String identificador, String emails, String asuntoMail, String dir3, String NIFs,
      String fechaCaducidad) throws InsideWSException {
    try {
      return insideOperationWebService.getCredencialesAccesoExpediente(
          credenciales.getIdaplicacion(), identificador, emails, asuntoMail, dir3, NIFs,
          fechaCaducidad);
    } catch (Exception e) {
      throw InsideExceptionConverter.convertToException(e);
    }
  }


}
