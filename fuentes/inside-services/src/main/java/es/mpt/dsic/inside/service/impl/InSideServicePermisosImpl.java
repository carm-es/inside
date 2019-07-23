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

package es.mpt.dsic.inside.service.impl;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import es.mpt.dsic.inside.model.busqueda.resultado.DocumentoResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ExpedienteResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ItemResultadoBusquedaInside;
import es.mpt.dsic.inside.model.busqueda.resultado.ResultadoBusquedaInside;
import es.mpt.dsic.inside.model.objetos.ObjetoInside;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoExpedienteInside;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InSideServicePermisos;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.store.exception.InsideStoreObjectPermisosException;
import es.mpt.dsic.inside.service.users.InsideUsersService;

public class InSideServicePermisosImpl implements InSideServicePermisos {

  protected static final Log logger = LogFactory.getLog(InSideServicePermisosImpl.class);

  @Autowired
  private InSideService service;

  @Autowired
  private InsideUsersService usersService;

  @Override
  public <T extends ObjetoInside<?>> void checkPermisos(String identificador, Integer version,
      Class<T> tipo, String nombreAplicacion) throws InSideServiceException {
    checkPermisosWithValidateFlags(identificador, version, tipo, nombreAplicacion, true);
  }

  @Override
  public <T extends ObjetoInside<?>> void checkPermisosWithValidateFlags(String identificador,
      Integer version, Class<T> tipo, String nombreAplicacion, boolean validateFlags)
      throws InSideServiceException {
    ObjetoInside<?> objeto = null;
    if (tipo == ObjetoExpedienteInside.class) {
      objeto = service.getExpediente(identificador, version, true);
    }
    if (tipo == ObjetoDocumentoInside.class) {
      objeto = service.getDocumento(identificador);
    }
    checkPermiso(nombreAplicacion, objeto, false);
  }

  @Override
  public <T extends ObjetoInside<?>> void checkPermisos(List<String> identificadores,
      Integer version, Class<T> tipo, String nombreAplicacion) throws InSideServiceException {
    for (String identificador : identificadores) {
      ObjetoInside<?> objeto = null;
      if (tipo == ObjetoExpedienteInside.class) {
        objeto = service.getExpediente(identificador, version, true);
      }
      if (tipo == ObjetoDocumentoInside.class) {
        objeto = service.getDocumento(identificador);
      }
      checkPermiso(nombreAplicacion, objeto, false);
    }
  }

  @Override
  public <T extends ObjetoInside<?>> void checkPermisos(ResultadoBusquedaInside<?> datos,
      String nombreAplicacion) throws InSideServiceException {
    ObjetoInside<?> objeto = null;
    for (ItemResultadoBusquedaInside<?> resultado : datos.getResultados()) {
      if (resultado instanceof DocumentoResultadoBusquedaInside) {
        objeto = service.getDocumento(resultado.getIdentificador());
      }
      if (resultado instanceof ExpedienteResultadoBusquedaInside) {
        objeto = service.getExpediente(resultado.getIdentificador(), true);
      }
      checkPermiso(nombreAplicacion, objeto, false);
    }
  }

  private void checkPermiso(String nombreAplicacion, ObjetoInside<?> objeto, boolean online)
      throws InSideServiceException {
    logger.debug("Inicio checkPermiso:" + nombreAplicacion + "," + objeto.getIdentificador() + ",");

    boolean validacion = service.permisoObjetoUnidad(objeto, nombreAplicacion, online);

    if (!validacion)
      throw new InsideStoreObjectPermisosException(nombreAplicacion, objeto.getIdentificador(),
          "Objeto sin permisos");
  }
}
