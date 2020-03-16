package es.mpt.dsic.inside.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import org.apache.cxf.annotations.GZIP;
import org.apache.cxf.annotations.Logging;
import org.apache.cxf.interceptor.OutFaultInterceptors;
import org.apache.cxf.interceptor.OutInterceptors;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsErrorInterceptor;
import es.mpt.dsic.inside.ws.cxf.interceptor.InsideWsIndiceExpedienteSignerInterceptor;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.xml.inside.TokenExpediente;
import es.mpt.dsic.inside.xml.inside.ws.credential.WSCredentialInside;

@WebService(name = "INSIDECARMWebService",
    targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebServiceFiles")
@Logging(limit = -1)
@GZIP
@OutInterceptors(interceptors = {}, classes = {InsideWsIndiceExpedienteSignerInterceptor.class})
@OutFaultInterceptors(interceptors = {}, classes = {InsideWsErrorInterceptor.class})
public interface INSIDECARMWebService {


  @WebMethod(operationName = "getCredencialesAccesoExpediente",
      action = "urn:getCredencialesAccesoExpediente")
  @WebResult(name = "TokenExpediente", partName = "TokenExpediente",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/expediente-e/tokenExpediente")
  public TokenExpediente getCredencialesAccesoExpediente(@WebParam(name = "credential",
      targetNamespace = "https://ssweb.seap.minhap.es/Inside/XSD/v1.0/WebService/types/credential") @XmlElement(
          required = true, name = "credential") WSCredentialInside credenciales,
      @WebParam(name = "identificador") @XmlElement(required = true,
          name = "identificador") String identificador,
      @WebParam(name = "emails") @XmlElement(required = true, name = "emails") String emails,
      @WebParam(name = "asuntoMail") @XmlElement(required = true,
          name = "asuntoMail") String asuntoMail,
      @WebParam(name = "dir3") @XmlElement(required = true, name = "dir3") String dir3,
      @WebParam(name = "NIFs") @XmlElement(required = true, name = "NIFs") String NIFs,
      @WebParam(name = "fechaCaducidad") @XmlElement(required = true,
          name = "fechaCaducidad") String fechaCaducidad)
      throws InsideWSException;
}
