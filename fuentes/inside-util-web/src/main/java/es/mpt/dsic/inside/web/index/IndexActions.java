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

package es.mpt.dsic.inside.web.index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import es.mpt.dsic.inside.xml.eni.expediente.TipoExpediente;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoCarpetaIndizada;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoDocumentoIndizado;
import es.mpt.dsic.inside.xml.eni.expediente.indice.contenido.TipoIndiceContenido;

public class IndexActions {

  public static TipoExpediente createFolder(TipoExpediente exp, String path, String name) {
    TipoExpediente retorno = exp;
    TipoCarpetaIndizada carpeta = new TipoCarpetaIndizada();
    carpeta.setIdentificadorCarpeta(name);
    if (!"".equals(path)) {
      String[] splitPath = path.split("/");
      TipoCarpetaIndizada carpetaPadre = getPath(retorno.getIndice().getIndiceContenido()
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), splitPath);
      carpetaPadre.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(carpeta);
    } else {
      retorno.getIndice().getIndiceContenido()
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(carpeta);
    }
    return retorno;
  }

  public static TipoExpediente createFolderComplete(TipoExpediente exp, TipoIndiceContenido indice,
      String path) {
    TipoExpediente retorno = exp;
    if (!"".equals(path)) {
      String[] splitPath = path.split("/");
      TipoCarpetaIndizada carpetaPadre = getPath(retorno.getIndice().getIndiceContenido()
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), splitPath);
      for (Object objt : indice.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada())
        carpetaPadre.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(objt);
    } else {
      for (Object objt : indice.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada())
        retorno.getIndice().getIndiceContenido()
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(objt);
    }
    return retorno;
  }

  public static TipoExpediente createDocument(TipoExpediente exp, String path, String id,
      String valorHuella, String funcionResumen, XMLGregorianCalendar fechaIncorpExp,
      String ordenDocExp) {
    TipoExpediente retorno = exp;
    TipoDocumentoIndizado document = new TipoDocumentoIndizado();
    document.setIdentificadorDocumento(id);
    document.setValorHuella(valorHuella);
    document.setFuncionResumen(funcionResumen);
    document.setFechaIncorporacionExpediente(fechaIncorpExp);
    document.setOrdenDocumentoExpediente(ordenDocExp);

    if (StringUtils.isNotBlank(path)) {
      String[] splitPath = path.split("/");
      TipoCarpetaIndizada carpetaPadre = getPath(retorno.getIndice().getIndiceContenido()
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), splitPath);
      if (carpetaPadre != null) {
        document.setOrdenDocumentoExpediente(calculateOrdenExpediente(retorno.getIndice()
            .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()));
        carpetaPadre.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(document);
      }
    } else {
      document.setOrdenDocumentoExpediente(calculateOrdenExpediente(retorno.getIndice()
          .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()));
      retorno.getIndice().getIndiceContenido()
          .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada().add(document);
    }
    return retorno;
  }

  public static String calculateOrdenExpediente(List<Object> list) {
    int orden = 1;
    for (Object obj : list) {
      if (obj instanceof TipoDocumentoIndizado) {
        orden++;
      }
    }
    return Integer.toString(orden);
  }

  public static TipoCarpetaIndizada getPath(List<Object> objectos, String[] path) {
    String actualPath = path[0];
    TipoCarpetaIndizada retorno = null;
    for (Object obj : objectos) {
      if (obj instanceof TipoCarpetaIndizada) {
        if (((TipoCarpetaIndizada) obj).getIdentificadorCarpeta().equals(actualPath)) {
          if (path.length == 1) {
            retorno = (TipoCarpetaIndizada) obj;
          } else {
            retorno = getPath(
                ((TipoCarpetaIndizada) obj)
                    .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(),
                Arrays.copyOfRange(path, 1, path.length));
          }
        }
      }
    }
    return retorno;
  }

  public static TipoExpediente moveElement(TipoExpediente exp, String pathOrigen,
      String identificador, String pathDestino, boolean onlyDeleteElement, Integer orden,
      HttpSession session) {
    TipoExpediente retorno = exp;

    List<Object> objetosOrigen;
    List<Object> objetosDestino;

    String vPath = pathOrigen + identificador + "/";
    if (onlyDeleteElement || !vPath.equals(pathDestino)) {
      if (!pathOrigen.equals("")) {
        String[] splitPath = pathOrigen.split("/");
        objetosOrigen = getPath(retorno.getIndice().getIndiceContenido()
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), splitPath)
                .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada();
      } else {
        objetosOrigen = retorno.getIndice().getIndiceContenido()
            .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada();
      }

      if (CollectionUtils.isNotEmpty(objetosOrigen)) {
        Object elementoeliminar = null;
        for (Object objeto : objetosOrigen) {
          String idObjeto = null;
          if (objeto instanceof TipoCarpetaIndizada) {
            idObjeto = ((TipoCarpetaIndizada) objeto).getIdentificadorCarpeta();
          }
          if (objeto instanceof TipoDocumentoIndizado) {
            idObjeto = ((TipoDocumentoIndizado) objeto).getIdentificadorDocumento();
          }
          // Si es TipoIndiceContenido, es heredado y no lo tendremos
          // en cuenta
          if (identificador.equals(idObjeto)) {
            elementoeliminar = objeto;
          }
        }

        int ordenOriginal = objetosOrigen.indexOf(elementoeliminar);
        objetosOrigen.remove(elementoeliminar);

        if (!onlyDeleteElement) {
          if (!pathDestino.equals("")) {
            String[] splitPath = pathDestino.split("/");
            objetosDestino = getPath(retorno.getIndice().getIndiceContenido()
                .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), splitPath)
                    .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada();
          } else {
            objetosDestino = retorno.getIndice().getIndiceContenido()
                .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada();
          }
          if (orden != null) {
            int ordenFinal =
                calculateOrdenFinal(ordenOriginal, orden, pathOrigen.equalsIgnoreCase(pathDestino));
            objetosDestino.add(ordenFinal, elementoeliminar);
            calculateOrdenElemento(objetosDestino);
          } else {
            objetosDestino.add(elementoeliminar);
            calculateOrdenElemento(objetosDestino);
          }
        } else {
          modificarDocumentoSession(session, elementoeliminar, true);
        }
      }
    }
    return retorno;
  }

  private static int calculateOrdenFinal(int ordenOriginal, Integer orden,
      boolean movimientoAlMismoNivel) {
    int restarPosicion = ordenOriginal < orden && movimientoAlMismoNivel ? 2 : 1;
    int ordenFinal = orden - restarPosicion;
    return ordenFinal > 0 ? ordenFinal : 0;
  }

  private static void calculateOrdenElemento(List<Object> objetosDestino) {
    int orden = 1;
    for (Object obj : objetosDestino) {
      if (obj instanceof TipoDocumentoIndizado) {
        ((TipoDocumentoIndizado) obj).setOrdenDocumentoExpediente(Integer.toString(orden));
        orden++;
      }
    }
  }

  public static void modificarDocumentoSession(HttpSession session, Object elementoeliminar,
      boolean eliminar) {
    if (elementoeliminar instanceof TipoCarpetaIndizada) {
      removeDocCascade((TipoCarpetaIndizada) elementoeliminar, session);
    }
    if (elementoeliminar instanceof TipoDocumentoIndizado) {
      String dataDoc = ((TipoDocumentoIndizado) elementoeliminar).getIdentificadorDocumento();
      if (eliminar) {
        deteleElement(session, dataDoc);
      } else if (session.getAttribute("docs") != null) {
        ((List<String>) session.getAttribute("docs")).add(dataDoc);
      }
    }
  }

  private static void removeDocCascade(TipoCarpetaIndizada carpeta, HttpSession session) {
    if (CollectionUtils
        .isNotEmpty(carpeta.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada())) {
      for (Object obj : carpeta.getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada()) {
        if (obj instanceof TipoCarpetaIndizada) {
          removeDocCascade((TipoCarpetaIndizada) obj, session);
        } else if (obj instanceof TipoDocumentoIndizado) {
          String dataDoc = ((TipoDocumentoIndizado) obj).getIdentificadorDocumento();
          deteleElement(session, dataDoc);
        }
      }
    }
  }

  public static void deteleElement(HttpSession session, String dataDoc) {
    if (session != null && session.getAttribute("docs") != null
        && session.getAttribute("newDocs") != null) {
      ((List<String>) session.getAttribute("docs")).remove(dataDoc);
      ((HashMap<String, String>) session.getAttribute("newDocs")).remove(dataDoc);
    }
  }

  public static TipoExpediente renameElement(TipoExpediente exp, String path, String actualName,
      String newName) {

    TipoExpediente expedientType = exp;

    path += actualName + "/";

    String[] splitPath = path.split("/");

    TipoCarpetaIndizada carpeta = getPath(expedientType.getIndice().getIndiceContenido()
        .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), splitPath);
    carpeta.setIdentificadorCarpeta(newName);

    return expedientType;
  }

  public static boolean checkMoveElementNoRepeatInLevel(TipoExpediente expedientType,
      String identificador, String path) {

    String[] splitPath = StringUtils.isNotBlank(path) ? path.split("/") : new String[0];

    List<Object> listElementSameLevel = new ArrayList<Object>();
    findElementsSameLevel(listElementSameLevel, splitPath, expedientType.getIndice()
        .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), 0);
    boolean checkMoveElementNoRepeatInLevel = true;

    for (Object obj : listElementSameLevel) {
      if (obj instanceof TipoCarpetaIndizada
          && ((TipoCarpetaIndizada) obj).getIdentificadorCarpeta().equals(identificador)) {
        checkMoveElementNoRepeatInLevel = false;
        break;
      }
    }

    return checkMoveElementNoRepeatInLevel;
  }

  public static boolean checkNewNameNoRepeatInLevel(TipoExpediente expedientType, String path,
      String actualName, String newName) {

    path += actualName + "/";

    String[] splitPath = path.split("/");

    List<Object> listElementSameLevel = new ArrayList<Object>();
    findElementsSameLevel(listElementSameLevel, splitPath, expedientType.getIndice()
        .getIndiceContenido().getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), 1);
    boolean checkNewNameNoRepeatInLevel = true;

    for (Object obj : listElementSameLevel) {
      if (obj instanceof TipoCarpetaIndizada
          && ((TipoCarpetaIndizada) obj).getIdentificadorCarpeta().equals(newName)) {
        checkNewNameNoRepeatInLevel = false;
        break;
      }
    }

    return checkNewNameNoRepeatInLevel;
  }

  private static List<Object> findElementsSameLevel(List<Object> listElementSameLevel,
      String[] splitPath, List<Object> list, int level) {
    if (splitPath.length == level) {
      listElementSameLevel.addAll(list);
    } else {
      String[] splitPathNew = Arrays.copyOfRange(splitPath, 1, splitPath.length);
      for (Object obj : list) {
        if (obj instanceof TipoCarpetaIndizada
            && ((TipoCarpetaIndizada) obj).getIdentificadorCarpeta().equals(splitPath[0])) {
          findElementsSameLevel(listElementSameLevel, splitPathNew, ((TipoCarpetaIndizada) obj)
              .getDocumentoIndizadoOrExpedienteIndizadoOrCarpetaIndizada(), level);
        }
      }
    }
    return listElementSameLevel;
  }

}
