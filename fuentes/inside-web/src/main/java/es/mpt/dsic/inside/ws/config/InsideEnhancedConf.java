package es.mpt.dsic.inside.ws.config;

// CARM ### v2.0.8.1
public class InsideEnhancedConf {

  private static String activoCadena = "S";

  private String altaDocumentoEniAutogenerarIdentificadorActivo = "N";

  private String altaExpedientEniAutogenerarIdentificadorActivo = "N";

  private String altaExpedientEniDocumentoIndizadoAceptaVacioActivo = "N";

  public String getAltaDocumentoEniAutogenerarIdentificadorActivo() {
    return altaDocumentoEniAutogenerarIdentificadorActivo;
  }

  public void setAltaDocumentoEniAutogenerarIdentificadorActivo(
      String altaDocumentoEniAutogenerarIdentificadorActivo) {
    this.altaDocumentoEniAutogenerarIdentificadorActivo =
        altaDocumentoEniAutogenerarIdentificadorActivo;
  }

  public boolean isActivoAltaDocumentoEniAutogenerarIdentificador() {
    return this.altaDocumentoEniAutogenerarIdentificadorActivo.equals(activoCadena);
  }

  public String getAltaExpedientEniAutogenerarIdentificadorActivo() {
    return altaExpedientEniAutogenerarIdentificadorActivo;
  }

  public void setAltaExpedientEniAutogenerarIdentificadorActivo(
      String altaExpedientEniAutogenerarIdentificadorActivo) {
    this.altaExpedientEniAutogenerarIdentificadorActivo =
        altaExpedientEniAutogenerarIdentificadorActivo;
  }

  public boolean isActivoAltaExpedientEniAutogenerarIdentificador() {
    return this.altaExpedientEniAutogenerarIdentificadorActivo.equals(activoCadena);
  }

  public String getAltaExpedientEniDocumentoIndizadoAceptaVacioActivo() {
    return altaExpedientEniDocumentoIndizadoAceptaVacioActivo;
  }

  public void setAltaExpedientEniDocumentoIndizadoAceptaVacioActivo(
      String altaExpedientEniDocumentoIndizadoAceptaVacioActivo) {
    this.altaExpedientEniDocumentoIndizadoAceptaVacioActivo =
        altaExpedientEniDocumentoIndizadoAceptaVacioActivo;
  }

  public boolean isActivoAltaExpedientEniDocumentoIndizadoAceptaVacio() {
    return this.altaExpedientEniDocumentoIndizadoAceptaVacioActivo.equals(activoCadena);
  }

}
// CARM 2.0.8.1 ###