package es.mpt.dsic.eeutil.client.util;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class CxfClientUtil {

  private CxfClientUtil() {
    throw new IllegalStateException("Utility class");
  }

  public static void setupLoggingCxf(Client client, boolean loggingCxfEnabled,
      int loggingCxfLimit) {
    if (loggingCxfEnabled) {
      client.getInInterceptors().add(loggingCxfLimit == 0 ? new LoggingInInterceptor()
          : new LoggingInInterceptor(loggingCxfLimit));
      client.getOutInterceptors().add(loggingCxfLimit == 0 ? new LoggingOutInterceptor()
          : new LoggingOutInterceptor(loggingCxfLimit));
    }
  }

  public static void setupTimeouts(Client client, Long connectionTimeout, Long receiveTimeout) {
    HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
    HTTPClientPolicy policy = httpConduit.getClient();
    // set time to wait for response in milliseconds. zero means unlimited
    if (receiveTimeout != null)
      policy.setReceiveTimeout(receiveTimeout);
    if (connectionTimeout != null)
      policy.setConnectionTimeout(connectionTimeout);
  }

}
