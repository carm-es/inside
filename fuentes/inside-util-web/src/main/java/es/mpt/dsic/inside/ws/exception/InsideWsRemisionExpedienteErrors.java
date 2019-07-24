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

package es.mpt.dsic.inside.ws.exception;

/**
 * Enum para agrupar los distintos errores o clases de errores que se pueden generar en el WS de
 * remisionExpediente, donde nos llama MJU.
 * 
 * @author miguel.ortega
 *
 */
public enum InsideWsRemisionExpedienteErrors {

  /**
   * Rangos de Errores tal como se han puesto en la documentación: E-001 Error de autenticación.
   * Crecenciales erroneas E-002 Error de autenticación. Operación no permitida E-1xx Error de
   * Sintaxis en la llamada WS E-2xx Omisión de alguno de los parámetros de entrada E-3xx Error en
   * el rango de alguno de los parámetros de entrada E-4XX Errores diversos motivados por algún
   * error en la petición. E-5XX Error interno de Inside E-6XX Errores Remisión en la nube E-999
   * Operación no permitida E-998 Aplicación marcada como deshabilitada
   */

  REM_0000(new InsideWSRemisionExpedienteError("REM_0000",
      "Error Técnico: Error de Infraestructura",
      "Error no controlado en procesamiento de notificación de Envío", "TÉCNICO")), REM_0001(
          new InsideWSRemisionExpedienteError("REM_0001",
              "Error Técnico: Error en validación de Petición",
              "Error en la validación de esquema de petición", "TÉCNICO")), REM_0002(
                  new InsideWSRemisionExpedienteError("REM_0002",
                      "Error Funcional: Envío Inexistente",
                      "No existe Envío con el código facilitado", "FUNCIONAL")), REM_0003(
                          new InsideWSRemisionExpedienteError("REM_0003",
                              "Error Funcional: Envío en Estado Finalizado",
                              "Error al actualizar el estado del envío: El envío ya se encuentra en Estado Finalizado",
                              "FUNCIONAL")), REM_0004(
                                  new InsideWSRemisionExpedienteError("REM_0005",
                                      "Error Funcional: Envío en Estado Anulado",
                                      "Error al actualizar el estado del envío: El envío ya se encuentra en Estado Anulado",
                                      "FUNCIONAL")),;



  private final InsideWSRemisionExpedienteError value;

  InsideWsRemisionExpedienteErrors(InsideWSRemisionExpedienteError error) {
    value = error;
  }

  public InsideWSRemisionExpedienteError getValue() {
    return value;
  }

  public static InsideWsRemisionExpedienteErrors valueFromCodigo(String codigo) {
    for (InsideWsRemisionExpedienteErrors c : InsideWsRemisionExpedienteErrors.values()) {
      if (c.value.getCodigo() == codigo) {
        return c;
      }
    }
    throw new IllegalArgumentException("No hay error definido para  el código " + codigo);
  }
}
