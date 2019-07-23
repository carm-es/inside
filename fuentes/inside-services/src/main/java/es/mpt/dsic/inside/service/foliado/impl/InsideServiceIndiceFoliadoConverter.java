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

package es.mpt.dsic.inside.service.foliado.impl;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndice;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenido;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado;
import es.mpt.dsic.inside.model.objetos.expediente.indice.ObjetoExpedienteInsideIndiceContenidoElementoIndizado;
import es.mpt.dsic.inside.model.objetos.foliado.FoliadoNodo;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectNotFoundException;

public class InsideServiceIndiceFoliadoConverter {

  protected static final Log logger = LogFactory.getLog(InsideServiceIndiceFoliadoConverter.class);

  protected static final byte[] paginaDocumentoNoEncontrado = Base64.decodeBase64(
      "JVBERi0xLjQKJcfsj6IKNSAwIG9iago8PC9MZW5ndGggNiAwIFIvRmlsdGVyIC9GbGF0ZURlY29kZT4+CnN0cmVhbQp4nIWRzU4DIRSF9zwFS1j0en+gXLYmjdGdZnbGhZmqMXGmadQH9k0Ep5RZNBFCcnO+y+EAR4tAbLHOVoyTQXtT1ps5GgWp4w+s63Gy14O5ekiWETDY4dUg5JwDLZysRkC1KTIktsNkHt3OE2TRTO7TbwRYc2D35RmKhupe/IaASSQ5u4ghun0XD70cC0/IQd13FydfrpBiFClWAVJSSm5eOG9DO6mYHi6ddOoMRI3XTQvXxC1eFVv8lJspifu5tOm5ix9+C4Ih6jroio/d9BQ/r9OvOvcN/5dzbo2d3p6r+eJ93ks2IUE5P31taF/D6qAaZCQtpk/DndkN5r7MX4CNd/hlbmRzdHJlYW0KZW5kb2JqCjYgMCBvYmoKMjU4CmVuZG9iago0IDAgb2JqCjw8L1R5cGUvUGFnZS9NZWRpYUJveCBbMCAwIDU5NSA4NDJdCi9Sb3RhdGUgMC9QYXJlbnQgMyAwIFIKL1Jlc291cmNlczw8L1Byb2NTZXRbL1BERiAvVGV4dF0KL0ZvbnQgOCAwIFIKPj4KL0NvbnRlbnRzIDUgMCBSCj4+CmVuZG9iagozIDAgb2JqCjw8IC9UeXBlIC9QYWdlcyAvS2lkcyBbCjQgMCBSCl0gL0NvdW50IDEKL1JvdGF0ZSAwPj4KZW5kb2JqCjEgMCBvYmoKPDwvVHlwZSAvQ2F0YWxvZyAvUGFnZXMgMyAwIFIKL01ldGFkYXRhIDEwIDAgUgo+PgplbmRvYmoKOCAwIG9iago8PC9SNwo3IDAgUj4+CmVuZG9iago3IDAgb2JqCjw8L0Jhc2VGb250L0hlbHZldGljYS9UeXBlL0ZvbnQKL0VuY29kaW5nIDkgMCBSL1N1YnR5cGUvVHlwZTE+PgplbmRvYmoKOSAwIG9iago8PC9UeXBlL0VuY29kaW5nL0RpZmZlcmVuY2VzWwoyMjUvYWFjdXRlXT4+CmVuZG9iagoxMCAwIG9iago8PC9UeXBlL01ldGFkYXRhCi9TdWJ0eXBlL1hNTC9MZW5ndGggMTM2OT4+c3RyZWFtCjw/eHBhY2tldCBiZWdpbj0n77u/JyBpZD0nVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkJz8+Cjw/YWRvYmUteGFwLWZpbHRlcnMgZXNjPSJDUkxGIj8+Cjx4OnhtcG1ldGEgeG1sbnM6eD0nYWRvYmU6bnM6bWV0YS8nIHg6eG1wdGs9J1hNUCB0b29sa2l0IDIuOS4xLTEzLCBmcmFtZXdvcmsgMS42Jz4KPHJkZjpSREYgeG1sbnM6cmRmPSdodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjJyB4bWxuczppWD0naHR0cDovL25zLmFkb2JlLmNvbS9pWC8xLjAvJz4KPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9J2VlYzNmZmFlLWM2YWUtMTFlMS0wMDAwLWFjZDAxNGYxYTcxZScgeG1sbnM6cGRmPSdodHRwOi8vbnMuYWRvYmUuY29tL3BkZi8xLjMvJyBwZGY6UHJvZHVjZXI9J0dQTCBHaG9zdHNjcmlwdCA4LjYxJy8+CjxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSdlZWMzZmZhZS1jNmFlLTExZTEtMDAwMC1hY2QwMTRmMWE3MWUnIHhtbG5zOnhhcD0naHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLycgeGFwOk1vZGlmeURhdGU9JzIwMTItMDctMDJUMTY6Mzc6MDArMDI6MDAnIHhhcDpDcmVhdGVEYXRlPScyMDEyLTA3LTAyVDE2OjM3OjAwKzAyOjAwJz48eGFwOkNyZWF0b3JUb29sPlBERkNyZWF0b3IgVmVyc2lvbiAwLjkuNTwveGFwOkNyZWF0b3JUb29sPjwvcmRmOkRlc2NyaXB0aW9uPgo8cmRmOkRlc2NyaXB0aW9uIHJkZjphYm91dD0nZWVjM2ZmYWUtYzZhZS0xMWUxLTAwMDAtYWNkMDE0ZjFhNzFlJyB4bWxuczp4YXBNTT0naHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLycgeGFwTU06RG9jdW1lbnRJRD0nZWVjM2ZmYWUtYzZhZS0xMWUxLTAwMDAtYWNkMDE0ZjFhNzFlJy8+CjxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSdlZWMzZmZhZS1jNmFlLTExZTEtMDAwMC1hY2QwMTRmMWE3MWUnIHhtbG5zOmRjPSdodHRwOi8vcHVybC5vcmcvZGMvZWxlbWVudHMvMS4xLycgZGM6Zm9ybWF0PSdhcHBsaWNhdGlvbi9wZGYnPjxkYzp0aXRsZT48cmRmOkFsdD48cmRmOmxpIHhtbDpsYW5nPSd4LWRlZmF1bHQnPkRvY3VtZW50bzE8L3JkZjpsaT48L3JkZjpBbHQ+PC9kYzp0aXRsZT48ZGM6Y3JlYXRvcj48cmRmOlNlcT48cmRmOmxpPm1pZ3VlbC5vcnRlZ2E8L3JkZjpsaT48L3JkZjpTZXE+PC9kYzpjcmVhdG9yPjwvcmRmOkRlc2NyaXB0aW9uPgo8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCjw/eHBhY2tldCBlbmQ9J3cnPz4KZW5kc3RyZWFtCmVuZG9iagoyIDAgb2JqCjw8L1Byb2R1Y2VyKEdQTCBHaG9zdHNjcmlwdCA4LjYxKQovQ3JlYXRpb25EYXRlKEQ6MjAxMjA3MDIxNjM3MDArMDInMDAnKQovTW9kRGF0ZShEOjIwMTIwNzAyMTYzNzAwKzAyJzAwJykKL1RpdGxlKERvY3VtZW50bzEpCi9DcmVhdG9yKFBERkNyZWF0b3IgVmVyc2lvbiAwLjkuNSkKL0F1dGhvcihtaWd1ZWwub3J0ZWdhKQovS2V5d29yZHMoKQovU3ViamVjdCgpPj5lbmRvYmoKeHJlZgowIDExCjAwMDAwMDAwMDAgNjU1MzUgZiAKMDAwMDAwMDU3MSAwMDAwMCBuIAowMDAwMDAyMjQ5IDAwMDAwIG4gCjAwMDAwMDA1MDMgMDAwMDAgbiAKMDAwMDAwMDM2MiAwMDAwMCBuIAowMDAwMDAwMDE1IDAwMDAwIG4gCjAwMDAwMDAzNDMgMDAwMDAgbiAKMDAwMDAwMDY2NSAwMDAwMCBuIAowMDAwMDAwNjM2IDAwMDAwIG4gCjAwMDAwMDA3NDQgMDAwMDAgbiAKMDAwMDAwMDgwMyAwMDAwMCBuIAp0cmFpbGVyCjw8IC9TaXplIDExIC9Sb290IDEgMCBSIC9JbmZvIDIgMCBSCi9JRCBbPEVGNEVCQ0ZFRDBFNUYxQTdBM0ZBRTI3RTMyNDQwQTJGPjxFRjRFQkNGRUQwRTVGMUE3QTNGQUUyN0UzMjQ0MEEyRj5dCj4+CnN0YXJ0eHJlZgoyNDcyCiUlRU9G"
          .getBytes());

  /**
   * Construye la estructura jerárquica que corresponde a un índice
   * 
   * @param identificador
   * @param indiceInside
   * @param service El servicio para recuperar los documentos
   * @return
   * @throws InSideServiceException
   */
  public static FoliadoNodo getEstructuraFoliadoNodo(String identificador,
      ObjetoExpedienteInsideIndice indiceInside, InSideService service, boolean validateFlags)
      throws InSideServiceException {
    FoliadoNodo padre = new FoliadoNodo();
    padre.setNombre("EXPEDIENTE_" + identificador);

    for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado : indiceInside
        .getIndiceContenido().getElementosIndizados()) {

      FoliadoNodo nodoHijo =
          getFoliadoNodoElementoIndizado(elementoIndizado, service, validateFlags);
      nodoHijo.setPadre(padre);
      padre.addHijo(nodoHijo);

    }

    return padre;
  }


  /**
   * Contruye la estructura jerárquica que corresponda a un elemento del índice
   * 
   * @param elementoIndizado Elemento del índice
   * @param service El servicio para recuperar los documentos
   * @return
   * @throws InSideServiceException
   */
  public static FoliadoNodo getFoliadoNodoElementoIndizado(
      ObjetoExpedienteInsideIndiceContenidoElementoIndizado elementoIndizado, InSideService service,
      boolean validateFlags) throws InSideServiceException {

    FoliadoNodo nodo = new FoliadoNodo();

    if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) {

      ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado documentoIndizado =
          (ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado;
      ObjetoDocumentoInside documentoInside = null;
      try {
        documentoInside = service.getDocumento(documentoIndizado.getIdentificadorDocumento());
        nodo.setContenido(documentoInside.getContenido().getValorBinario());
        nodo.setMimeContenido(documentoInside.getContenido().getMime());
      } catch (InsideStoreObjectNotFoundException e) {
        // TODO: Este documento no está almacenado en Inside, poner una página avidando de esto
        logger.warn("No se puede añadir el documento con identificador "
            + ((ObjetoExpedienteInsideIndiceContenidoDocumentoIndizado) elementoIndizado)
                .getIdentificadorDocumento()
            + " al foliado del expediente " + documentoIndizado.getIdentificadorDocumento()
            + " porque no existe en Inside");
        nodo.setContenido(paginaDocumentoNoEncontrado);
        nodo.setMimeContenido("application/pdf");
      }
      nodo.setNombre("D_" + documentoIndizado.getIdentificadorDocumento());

      // nodo.setNombreFormato(documentoInside.getContenido().getNombreFormato());


    } else if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) {

      ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada carpetaIndizada =
          (ObjetoExpedienteInsideIndiceContenidoCarpetaIndizada) elementoIndizado;
      nodo.setNombre("C_" + carpetaIndizada.getIdentificadorCarpeta());

      if (carpetaIndizada.getElementosIndizados() != null) {

        for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado hijoIndizado : carpetaIndizada
            .getElementosIndizados()) {

          FoliadoNodo nodoHijo =
              getFoliadoNodoElementoIndizado(hijoIndizado, service, validateFlags);

          nodoHijo.setPadre(nodo);
          nodo.addHijo(nodoHijo);

        }

      }

    } else if (elementoIndizado instanceof ObjetoExpedienteInsideIndiceContenido) {

      ObjetoExpedienteInsideIndiceContenido indiceContenido =
          (ObjetoExpedienteInsideIndiceContenido) elementoIndizado;
      nodo.setNombre("INDICE");

      if (indiceContenido.getElementosIndizados() != null) {

        for (ObjetoExpedienteInsideIndiceContenidoElementoIndizado hijoIndizado : indiceContenido
            .getElementosIndizados()) {

          FoliadoNodo nodoHijo =
              getFoliadoNodoElementoIndizado(hijoIndizado, service, validateFlags);

          nodoHijo.setPadre(nodo);
          nodo.addHijo(nodoHijo);

        }
      }
    }

    return nodo;

  }
}
