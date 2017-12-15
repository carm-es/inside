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

package es.mpt.dsic.eeutil.client.visDocExp.service;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 3.0.3
 * 2016-10-13T16:43:28.347+02:00
 * Generated source version: 3.0.3
 * 
 */
@WebServiceClient(name = "EeUtilServiceImplService", 
                  wsdlLocation = "http://127.0.0.1:8091/eeutil-vis-docexp/ws/EeUtilService?wsdl",
                  targetNamespace = "http://impl.service.ws.inside.dsic.mpt.es/") 
public class EeUtilServiceImplService extends Service {

    public final static QName SERVICE = new QName("http://impl.service.ws.inside.dsic.mpt.es/", "EeUtilServiceImplService");
    public final static QName EeUtilServiceImplPort = new QName("http://impl.service.ws.inside.dsic.mpt.es/", "EeUtilServiceImplPort");

    public EeUtilServiceImplService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public EeUtilServiceImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     *
     * @return
     *     returns EeUtilService
     */
    @WebEndpoint(name = "EeUtilServiceImplPort")
    public EeUtilService getEeUtilServiceImplPort() {
        return super.getPort(EeUtilServiceImplPort, EeUtilService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EeUtilService
     */
    @WebEndpoint(name = "EeUtilServiceImplPort")
    public EeUtilService getEeUtilServiceImplPort(WebServiceFeature... features) {
        return super.getPort(EeUtilServiceImplPort, EeUtilService.class, features);
    }

}
