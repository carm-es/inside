package es.mpt.dsic.inside.service.validacionENI.exception;

import es.mpt.dsic.inside.service.exception.InsideServiceInternalException;

// CARM ### v2.0.8.1
public class InsideServiceValidacionException extends InsideServiceInternalException {

  private static final long serialVersionUID = -7001895413825602757L;

  public InsideServiceValidacionException(String mensaje, Throwable e) {
    super(mensaje, e);
  }

  public InsideServiceValidacionException(String mensaje) {
    super(mensaje);
  }
}
// CARM 2.0.8.1 ###