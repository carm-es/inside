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

package es.mpt.dsic.inside.service.portales.impl;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.xml.ws.Holder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import es.mpt.dsic.inside.service.portales.model.AuthHeader;
import es.mpt.dsic.inside.service.portales.model.CargoEntidadWsObject;
import es.mpt.dsic.inside.service.portales.model.CargoEntidadWsObjectArray;
import es.mpt.dsic.inside.service.portales.model.CargoInstitucionWsObject;
import es.mpt.dsic.inside.service.portales.model.CargoInstitucionWsObjectArray;
import es.mpt.dsic.inside.service.portales.model.CargoOrganismoWsObject;
import es.mpt.dsic.inside.service.portales.model.CargoOrganismoWsObjectArray;
import es.mpt.dsic.inside.service.portales.model.CargoUnidadDirWsObject;
import es.mpt.dsic.inside.service.portales.model.CargoUnidadDirWsObjectArray;
import es.mpt.dsic.inside.service.portales.model.CargosWsObject;
import es.mpt.dsic.inside.service.portales.model.ConsultaCargosPortalEELLWSPortType;
import es.mpt.dsic.inside.service.portales.model.ConsultaCargosPortalEELLWSService;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.UnidadOrganicaRolPortales;



public class ConsumidorPortales {


  private ConsultaCargosPortalEELLWSPortType clientePort;

  private String user;
  private String password;
  private String url;

  private Holder<AuthHeader> authHeader;


  protected static final Log logger = LogFactory.getLog(ConsumidorPortales.class);

  // @PostConstruct
  public void configure() {

    AuthHeader userYpass = new AuthHeader();
    userYpass.setUsername(user);
    userYpass.setPassword(password);
    authHeader = new Holder<AuthHeader>(userYpass);


    URL wsdlURL = null;
    try {
      wsdlURL = new URL(url);
    } catch (MalformedURLException e) {
      logger.error("Error al obtener la URL del properties para servicio portales");
    }
    ConsultaCargosPortalEELLWSService ss = new ConsultaCargosPortalEELLWSService(wsdlURL);
    clientePort = ss.getConsultaCargosPortalEELLWSPort();

  }


  // devuelve lista objetos con campos "unidadOrganica y rol"
  public List<UnidadOrganicaRolPortales> listaUnidadesOrganicasRolesPortales(String nif) {
    logger.debug("Inicio listaUnidadesOrganicasRolesPortales");

    // lista resultado final
    List<UnidadOrganicaRolPortales> listaResultado = new ArrayList<UnidadOrganicaRolPortales>();;

    // sublistas de cada tipo de objeto
    List<UnidadOrganicaRolPortales> listaUnidades = null;
    List<UnidadOrganicaRolPortales> listaEntidades = null;
    List<UnidadOrganicaRolPortales> listaOrganismos = null;
    List<UnidadOrganicaRolPortales> listaInstitucioneso = null;

    CargosWsObject cargos = clientePort.comprobarCargosUsuario(authHeader, nif);
    CargoUnidadDirWsObjectArray listaUnidadesRolPortales = cargos.getUnidades();
    CargoEntidadWsObjectArray listaEntidadesRolPortales = cargos.getEntidades();
    CargoOrganismoWsObjectArray listaOrganismosRolPortales = cargos.getOrganismos();
    CargoInstitucionWsObjectArray listaInstitucionesRolPortales = cargos.getInstituciones();

    if (listaUnidadesRolPortales.getItem() != null
        && listaUnidadesRolPortales.getItem().size() > 0) {
      listaUnidades = unidadesOrganicasRolesINSIDE(listaUnidadesRolPortales.getItem());
      listaResultado.addAll(listaUnidades);
    }

    if (listaEntidadesRolPortales.getItem() != null
        && listaEntidadesRolPortales.getItem().size() > 0) {
      listaEntidades = entidadesRolesINSIDE(listaEntidadesRolPortales.getItem());
      listaResultado.addAll(listaEntidades);
    }

    if (listaOrganismosRolPortales.getItem() != null
        && listaOrganismosRolPortales.getItem().size() > 0) {
      listaOrganismos = organismosRolesINSIDE(listaOrganismosRolPortales.getItem());
      listaResultado.addAll(listaOrganismos);
    }

    if (listaInstitucionesRolPortales.getItem() != null
        && listaInstitucionesRolPortales.getItem().size() > 0) {
      listaInstitucioneso = institucionesRolesINSIDE(listaInstitucionesRolPortales.getItem());
      listaResultado.addAll(listaInstitucioneso);
    }


    logger.debug("Fin listaUnidadesOrganicasRolesPortales");

    return listaResultado;
  }



  public ConsultaCargosPortalEELLWSPortType getClientePort() {
    return clientePort;
  }

  public void setClientePort(ConsultaCargosPortalEELLWSPortType clientePort) {
    this.clientePort = clientePort;
  }

  // Atributos desde properties pasando por xml
  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }



  // Devuelve true si es un cargo de los considerados especiales
  private boolean isCargoEspecial(String cargoDescripcionId) {
    boolean esCargoEspecial = false;

    if (cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_ALCALDE)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_ENTIDAD)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_GESTOR_DESIGNADOE)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_INTERV_TESORERO_ESTATAL)
        || cargoDescripcionId
            .equalsIgnoreCase(Constantes.PORTALES_INTERVENCION_TESORERIA_HAB_ESTATAL)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_SECRETARIO)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_SECRETARIO_ACCIDENTAL)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_SECRETARIO_ESTATAL)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_SECRETARIO_INTERINO)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_TECNICO)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_PRESIDENTE)
        || cargoDescripcionId.equalsIgnoreCase(Constantes.PORTALES_DIRECTOR_FUNCION_PUBLICA)) {
      esCargoEspecial = true;

    }

    return esCargoEspecial;
  }



  // roles portal - inside
  // INSIDE-AdministradorOrganismo - 1
  // INSIDE-Gestor - 0
  // INSIDE-Redactor - 4
  // INSIDE-Consulta - 3
  // INSIDE-Remisor - 2
  private List<UnidadOrganicaRolPortales> unidadesOrganicasRolesINSIDE(
      List<CargoUnidadDirWsObject> lista) {
    ArrayList<UnidadOrganicaRolPortales> resultado = new ArrayList<UnidadOrganicaRolPortales>();

    for (int i = 0; i < lista.size(); i++) {
      CargoUnidadDirWsObject aux = lista.get(i);

      if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_GESTOR_ID)
          || isCargoEspecial(aux.getCargoTipo())) {
        UnidadOrganicaRolPortales obj0 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_GESTOR_ROL);
        resultado.add(obj0);

      } else if (aux.getCargoTipo()
          .equalsIgnoreCase(Constantes.PORTALES_ADMINISTRADORORGANISMO_ID)) {
        UnidadOrganicaRolPortales obj1 = new UnidadOrganicaRolPortales(aux.getCodigoDir(),
            Constantes.INSIDE_ADMINISTRADORORGANISMO_ROL);
        resultado.add(obj1);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_REDACTOR_ID)) {
        UnidadOrganicaRolPortales obj2 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_REDACTOR_ROL);
        resultado.add(obj2);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_CONSULTA_ID)) {
        UnidadOrganicaRolPortales obj3 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_CONSULTA_ROL);
        resultado.add(obj3);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_REDACTOR_ID)) {
        UnidadOrganicaRolPortales obj4 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_REDACTOR_ROL);
        resultado.add(obj4);

      }

    }

    return resultado;
  }



  // roles portal - inside
  // INSIDE-AdministradorOrganismo - 1
  // INSIDE-Gestor - 0
  // INSIDE-Redactor - 4
  // INSIDE-Consulta - 3
  // INSIDE-Remisor - 2
  private List<UnidadOrganicaRolPortales> entidadesRolesINSIDE(List<CargoEntidadWsObject> lista) {
    ArrayList<UnidadOrganicaRolPortales> resultado = new ArrayList<UnidadOrganicaRolPortales>();

    for (int i = 0; i < lista.size(); i++) {
      CargoEntidadWsObject aux = lista.get(i);

      if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_GESTOR_ID)
          || isCargoEspecial(aux.getCargoTipo())) {
        UnidadOrganicaRolPortales obj0 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_GESTOR_ROL);
        resultado.add(obj0);

      } else if (aux.getCargoTipo()
          .equalsIgnoreCase(Constantes.PORTALES_ADMINISTRADORORGANISMO_ID)) {
        UnidadOrganicaRolPortales obj1 = new UnidadOrganicaRolPortales(aux.getCodigoDir(),
            Constantes.INSIDE_ADMINISTRADORORGANISMO_ROL);
        resultado.add(obj1);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_REDACTOR_ID)) {
        UnidadOrganicaRolPortales obj2 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_REDACTOR_ROL);
        resultado.add(obj2);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_CONSULTA_ID)) {
        UnidadOrganicaRolPortales obj3 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_CONSULTA_ROL);
        resultado.add(obj3);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_REDACTOR_ID)) {
        UnidadOrganicaRolPortales obj4 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_REDACTOR_ROL);
        resultado.add(obj4);

      }

    }

    return resultado;
  }



  // roles portal - inside
  // INSIDE-AdministradorOrganismo - 1
  // INSIDE-Gestor - 0
  // INSIDE-Redactor - 4
  // INSIDE-Consulta - 3
  // INSIDE-Remisor - 2
  private List<UnidadOrganicaRolPortales> organismosRolesINSIDE(
      List<CargoOrganismoWsObject> lista) {
    ArrayList<UnidadOrganicaRolPortales> resultado = new ArrayList<UnidadOrganicaRolPortales>();

    for (int i = 0; i < lista.size(); i++) {
      CargoOrganismoWsObject aux = lista.get(i);

      if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_GESTOR_ID)
          || isCargoEspecial(aux.getCargoTipo())) {
        UnidadOrganicaRolPortales obj0 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_GESTOR_ROL);
        resultado.add(obj0);

      } else if (aux.getCargoTipo()
          .equalsIgnoreCase(Constantes.PORTALES_ADMINISTRADORORGANISMO_ID)) {
        UnidadOrganicaRolPortales obj1 = new UnidadOrganicaRolPortales(aux.getCodigoDir(),
            Constantes.INSIDE_ADMINISTRADORORGANISMO_ROL);
        resultado.add(obj1);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_REDACTOR_ID)) {
        UnidadOrganicaRolPortales obj2 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_REDACTOR_ROL);
        resultado.add(obj2);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_CONSULTA_ID)) {
        UnidadOrganicaRolPortales obj3 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_CONSULTA_ROL);
        resultado.add(obj3);

      } else if (aux.getCargoTipo().equalsIgnoreCase(Constantes.PORTALES_REDACTOR_ID)) {
        UnidadOrganicaRolPortales obj4 =
            new UnidadOrganicaRolPortales(aux.getCodigoEell(), Constantes.INSIDE_REDACTOR_ROL);
        resultado.add(obj4);

      }

    }

    return resultado;
  }



  // roles portal - inside
  // INSIDE-AdministradorOrganismo - 1
  // INSIDE-Gestor - 0
  // INSIDE-Redactor - 4
  // INSIDE-Consulta - 3
  // INSIDE-Remisor - 2
  private List<UnidadOrganicaRolPortales> institucionesRolesINSIDE(
      List<CargoInstitucionWsObject> lista) {
    ArrayList<UnidadOrganicaRolPortales> resultado = new ArrayList<UnidadOrganicaRolPortales>();

    for (int i = 0; i < lista.size(); i++) {
      CargoInstitucionWsObject aux = lista.get(i);

      if (aux.getPerfil().equalsIgnoreCase(Constantes.PORTALES_GESTOR_ID)
          || isCargoEspecial(aux.getPerfil())) {
        UnidadOrganicaRolPortales obj0 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_GESTOR_ROL);
        resultado.add(obj0);

      } else if (aux.getPerfil().equalsIgnoreCase(Constantes.PORTALES_ADMINISTRADORORGANISMO_ID)) {
        UnidadOrganicaRolPortales obj1 = new UnidadOrganicaRolPortales(aux.getCodigoDir(),
            Constantes.INSIDE_ADMINISTRADORORGANISMO_ROL);
        resultado.add(obj1);

      } else if (aux.getPerfil().equalsIgnoreCase(Constantes.PORTALES_REMISOR_ID)) {
        UnidadOrganicaRolPortales obj2 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_REMISOR_ROL);
        resultado.add(obj2);

      } else if (aux.getPerfil().equalsIgnoreCase(Constantes.PORTALES_CONSULTA_ID)) {
        UnidadOrganicaRolPortales obj3 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_CONSULTA_ROL);
        resultado.add(obj3);

      } else if (aux.getPerfil().equalsIgnoreCase(Constantes.PORTALES_REDACTOR_ID)) {
        UnidadOrganicaRolPortales obj4 =
            new UnidadOrganicaRolPortales(aux.getCodigoDir(), Constantes.INSIDE_REDACTOR_ROL);
        resultado.add(obj4);

      }

    }

    return resultado;
  }



}
