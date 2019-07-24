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

package es.mpt.dsic.inside.remisionnube.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.soap.SOAPFaultException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import es.mpt.dsic.inside.justicia.service.PuntoUnicoJusticiaService;
import es.mpt.dsic.inside.justicia.service.exception.PuntoUnicoJusticiaMJUException;
import es.mpt.dsic.inside.model.objetos.ObjectInsideRespuestaEnvioJusticia;
import es.mpt.dsic.inside.remisionnube.service.PuntoUnicoRemisionNubeService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.ws.exception.InsideWSException;
import es.mpt.dsic.inside.ws.exception.InsideWsErrors;
import es.mpt.dsic.inside.xml.inside.ws.remisionEnLaNube.DatosRemisionJusticiaType;
import es.mpt.dsic.inside.xml.inside.ws.respuestaConsultaEnvioJusticia.RespuestaConsultaEnvioJusticia;
import es.mpt.dsic.loadTables.hibernate.service.impl.UnidadOrganicaServiceImpl;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.ClaseProcedimientoType;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.ConsultarRemisionAJusticiaResponse;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.OrdenType;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.OrganoType;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.ParametrosEnvioJusticia;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.RespuestaObtenerClasesProcedimientoType;
import es.mpt.dsic.puntoUnicoJusticia.client.modelo.RespuestaObtenerOrdenType;

public class PuntoUnicoRemisionNubeServiceImpl implements PuntoUnicoRemisionNubeService {

  protected static final Log logger = LogFactory.getLog(PuntoUnicoRemisionNubeServiceImpl.class);

  @Autowired
  private es.mpt.dsic.inside.service.InSideService service;

  @Autowired
  private PuntoUnicoJusticiaService puntoUnicoJusticiaService;

  @Autowired
  private UnidadOrganicaServiceImpl unidadOrganicaService;

  @Override
  public RespuestaConsultaEnvioJusticia consultaEstadoRemisionAJusticia(String codigoEnvioATEA)
      throws InsideWSException {

    ConsultarRemisionAJusticiaResponse consulta;
    ObjectInsideRespuestaEnvioJusticia dato = null;
    try {
      dato = service.getRespuestaEnvioJusticiaByCodigoEnvio(codigoEnvioATEA);
    } catch (InSideServiceException e) {
      logger.error(
          "Error al buscar código de envío en ExpedienteInsideREspuestaEnvioJusticia y ExpedienteNoInsideRespuestaEnvioJusticia "
              + e);
      throw new InsideWSException(InsideWsErrors.INTERNAL_SERVICE_ERROR);
    }
    if (dato != null) {
      try {
        consulta = puntoUnicoJusticiaService.consultaRemisionExpedienteMJU(
            dato.getAuditoriaEsb_aplicacion(), dato.getAuditoriaEsb_modulo(),
            dato.getAuditoriaEsb_servicio(), dato.getAuditoriaEsb_marcaTiempo(), codigoEnvioATEA,
            dato.getCodigoUnidadOrganoRemitente());
        return this.toRespuestaConsultaEnvioJusticia(consulta);
      } catch (PuntoUnicoJusticiaMJUException e) {
        logger.error("La consulta a MJU no devolvió resultado " + e);
        throw new InsideWSException(InsideWsErrors.CONSULTA_MJU_CODIGO_NO_RESULTADO);
      } catch (SOAPFaultException e) {
        logger.error("La consulta a MJU no se pudo realizar " + e);
        throw new InsideWSException(InsideWsErrors.CONSULTA_MJU_CODIGO_ERROR);
      }
    } else {
      throw new InsideWSException(InsideWsErrors.CONSULTA_MJU_CODIGO_ENVIO_NO_ENCONTRADO);
    }
  }

  private RespuestaConsultaEnvioJusticia toRespuestaConsultaEnvioJusticia(
      ConsultarRemisionAJusticiaResponse consulta) {

    RespuestaConsultaEnvioJusticia respuesta = new RespuestaConsultaEnvioJusticia();
    respuesta.setIdentificadorEstado(
        consulta.getResultadoConsultaRemisionToJusticia().getDatosEnvio().getIdentificadorEstado());
    respuesta.setDescripcionEstado(
        consulta.getResultadoConsultaRemisionToJusticia().getDatosEnvio().getDescripcionEstado());
    respuesta.setComentario(
        consulta.getResultadoConsultaRemisionToJusticia().getDatosEnvio().getComentario());
    respuesta.setResguardo(
        consulta.getResultadoConsultaRemisionToJusticia().getDatosEnvio().getResguardo());
    return respuesta;

  }

  public ParametrosEnvioJusticia getParametrosMJU(
      DatosRemisionJusticiaType datosRemisionJusticiaType, String dir3Juzgado)
      throws InsideWSException {

    String nig = datosRemisionJusticiaType.getNig();
    String nigCodigoPoblacion = nig.substring(0, 5);
    String nigTipoOrganoJudicial = nig.substring(5, 7);
    String nigNumeroOrden = nig.substring(7, 8);
    String nigAnyoAsunto = nig.substring(8, 12);
    String nigNumeroAsunto = nig.substring(12, 19);

    String rjClaseProcedimiento = datosRemisionJusticiaType.getClaseProcedimiento();
    String dir3Remitente = datosRemisionJusticiaType.getDir3Remitente();
    String descripcion = datosRemisionJusticiaType.getDescripcion();
    String rjNumeroProcedimiento = datosRemisionJusticiaType.getNumeroProcedimiento();
    String rjAnyoProcedimiento = datosRemisionJusticiaType.getAnyoProcedimiento();

    return getParametrosEnvioJusticia(nigCodigoPoblacion, nigTipoOrganoJudicial, nigNumeroOrden,
        nigAnyoAsunto, nigNumeroAsunto, dir3Juzgado, rjClaseProcedimiento, dir3Remitente,
        descripcion, rjNumeroProcedimiento, rjAnyoProcedimiento);

  }

  public ParametrosEnvioJusticia getParametrosEnvioJusticia(String nigCodigoPoblacion,
      String nigTipoOrganoJudicial, String nigNumeroOrden, String nigAnyoAsunto,
      String nigNumeroAsunto, String rjDir3Juzgado, String rjClaseProcedimiento,
      String dir3Remitente, String descripcion, String rjNumeroProcedimiento,
      String rjAnyoProcedimiento) throws InsideWSException {

    OrganoType organoType = obtenerOrganoParaParametrosMJU(nigCodigoPoblacion,
        nigTipoOrganoJudicial, nigNumeroOrden, rjDir3Juzgado);

    OrdenType orden = new OrdenType();
    orden.setCodigo(nigNumeroOrden);

    RespuestaObtenerClasesProcedimientoType datosClasesProcedimiento =
        puntoUnicoJusticiaService.listadoClasesProcedimientosByOrdenAndOrganos(organoType, orden);

    if (datosClasesProcedimiento != null
        && datosClasesProcedimiento.getClasesProcedimiento() != null
        && CollectionUtils
            .isNotEmpty(datosClasesProcedimiento.getClasesProcedimiento().getClaseProcedimiento())
        && !validaClaseProcedimiento(rjClaseProcedimiento,
            datosClasesProcedimiento.getClasesProcedimiento().getClaseProcedimiento())) {
      logger.error("La clase de procedimiento especificado no se encuentra para el organo "
          + organoType.getNumeroOrgano() + " y orden especificado " + nigNumeroOrden);
      throw new InsideWSException(InsideWsErrors.CLASE_PROCEDIMIENTO_NO_ENCONTRADO);
    }

    ParametrosEnvioJusticia parametroMJU = new ParametrosEnvioJusticia();
    parametroMJU.setPresentadorcodigoOrganoRemitente(dir3Remitente);
    parametroMJU.setPresentadordescripcionPresentadorExpediente(descripcion);
    parametroMJU.setProcedimientocodigoOrden(nigNumeroOrden);
    parametroMJU.setProcedimientoclase(rjClaseProcedimiento);
    parametroMJU.setProcedimientonumero(rjNumeroProcedimiento);
    parametroMJU.setProcedimientoanio(rjAnyoProcedimiento);
    parametroMJU.setOrganocodigoPoblacion(nigCodigoPoblacion);
    parametroMJU.setOrganonumeroOrgano(organoType.getNumeroOrgano());
    parametroMJU.setOrganotipoOrgano(organoType.getTipoOrgano());
    parametroMJU.setNignumeroAsunto(nigNumeroAsunto);
    parametroMJU.setNiganioAsunto(nigAnyoAsunto);
    parametroMJU.setNigcodigoOrden(orden.getCodigo());
    parametroMJU.setNigcodigoPoblacion(nigCodigoPoblacion);
    parametroMJU.setNigtipoOrgano(nigTipoOrganoJudicial);
    parametroMJU.setPresentadortipoPresentador("");
    parametroMJU.setPresentadoridentificadorUsuarioPresentador("");
    parametroMJU.setProcedimientocodigoInstancia("");
    parametroMJU.setProcedimientotipologia("");
    parametroMJU.setPiezanumeroPieza("");
    parametroMJU.setPiezaclaseProcedimiento("");
    parametroMJU.setPiezanumeroProcedimiento("");
    parametroMJU.setPiezaanioProcedimiento("");

    return parametroMJU;
  }

  private boolean validaClaseProcedimiento(String claseProcedimiento,
      List<ClaseProcedimientoType> listaClaseProcedimiento) {

    for (ClaseProcedimientoType clase : listaClaseProcedimiento) {
      if (clase.getCodigo().equals(claseProcedimiento)) {
        return true;
      }
    }
    return false;
  }

  private OrganoType obtenerOrganoParaParametrosMJU(String nigCodigoPoblacion,
      String nigTipoOrganoJudicial, String nigNumeroOrden, String rjDir3Juzgado)
      throws InsideWSException {
    OrganoType organoType = new OrganoType();
    organoType.setCodigoPoblacion(nigCodigoPoblacion);

    List<Criterion> criterios = new ArrayList<Criterion>();
    java.lang.Byte nivelAdministracion = 6;
    criterios.add(Restrictions.and(
        Restrictions.and(Restrictions.isNotNull("codigoExterno"),
            Restrictions.eq("nivelAdministracion", nivelAdministracion)),
        Restrictions.like("codigoUnidadOrganica", rjDir3Juzgado)));

    List<UnidadOrganica> unidades =
        unidadOrganicaService.findByCriterias(0, 10, UnidadOrganica.class, criterios);
    if (CollectionUtils.isEmpty(unidades)) {
      logger
          .error("No se encuentra el código de UNIDAD ORGANICA remitente para el dir3 destinatario "
              + rjDir3Juzgado);
      throw new InsideWSException(InsideWsErrors.UNIDAD_ORGANICA_NO_ENCONTRADA);
    }
    organoType.setNumeroOrgano(unidades.get(0).getCodigoExterno().substring(7, 10));
    organoType.setTipoOrgano(nigTipoOrganoJudicial);

    RespuestaObtenerOrdenType datosOrden =
        puntoUnicoJusticiaService.listadoOrdenesByOrgano(organoType);

    if (datosOrden != null && datosOrden.getOrdenes() != null
        && CollectionUtils.isNotEmpty(datosOrden.getOrdenes().getOrden())
        && !validaNumeroOrden(nigNumeroOrden, datosOrden.getOrdenes().getOrden())) {
      logger.error(
          "El número de orden especificado en el Nig no se encuentra para el órgano especificado "
              + organoType.getNumeroOrgano());
      throw new InsideWSException(InsideWsErrors.NUMERO_ORDEN_NO_ENCONTRADO);
    }
    return organoType;
  }

  private boolean validaNumeroOrden(String numeroOrden, List<OrdenType> listaOrdenes) {

    for (OrdenType orden : listaOrdenes) {
      if (orden.getCodigo().equals(numeroOrden)) {
        return true;
      }
    }
    return false;
  }

}
