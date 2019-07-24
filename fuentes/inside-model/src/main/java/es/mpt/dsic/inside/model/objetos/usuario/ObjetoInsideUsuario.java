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

package es.mpt.dsic.inside.model.objetos.usuario;

import org.apache.commons.lang.StringUtils;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideRol;
// import es.mpt.dsic.inside.store.hibernate.entity.InsideRol;
import es.mpt.dsic.inside.model.objetos.ObjetoNumeroProcedimiento;

public class ObjetoInsideUsuario implements Comparable<ObjetoInsideUsuario> {

  private String nif;
  private boolean activo;
  private String unidadOrganicaActiva;
  private String nombreUnidadOrganicaActiva;
  private String numeroProcedimiento;
  // private InsideRol rol;
  private ObjetoInsideRol rol;

  private boolean adminMensaje;

  private ObjetoNumeroProcedimiento objNumeroProcedimiento;

  /**
   * Si es usuario invitado tiene rol asignado GUEST_ROL, entra en modo GInside
   */
  public boolean isUsuarioInvitado() {
    return StringUtils.isBlank(unidadOrganicaActiva);
  }

  public String getUnidadOrganicaActivaDir3() {
    return this.getUnidadOrganicaActiva().substring(0,
        this.getUnidadOrganicaActiva().indexOf(" - "));
  }

  public String getNif() {
    return nif;
  }



  public void setNif(String nif) {
    this.nif = nif;
  }



  public boolean isActivo() {
    return activo;
  }



  public void setActivo(boolean activo) {
    this.activo = activo;
  }

  public String getUnidadOrganicaActiva() {
    return unidadOrganicaActiva;
  }



  public void setUnidadOrganicaActiva(String unidadOrganicaActiva) {
    this.unidadOrganicaActiva = unidadOrganicaActiva;
  }

  public String getNombreUnidadOrganicaActiva() {
    return nombreUnidadOrganicaActiva;
  }



  public void setNombreUnidadOrganicaActiva(String nombreUnidadOrganicaActiva) {
    this.nombreUnidadOrganicaActiva = nombreUnidadOrganicaActiva;
  }



  /**
   * Devuelve >0 si el orden del argumento es menor que el del "this".
   */
  public int compareTo(ObjetoInsideUsuario o) {
    int retorno = 0;
    if (!this.getNif().equals(o.getNif())) {
      retorno = 1;
    }
    return retorno;
  }

  public String getNumeroProcedimiento() {
    return numeroProcedimiento;
  }



  public void setNumeroProcedimiento(String numeroProcedimiento) {
    this.numeroProcedimiento = numeroProcedimiento;
  }



  public ObjetoInsideRol getRol() {
    return rol;
  }



  public void setRol(ObjetoInsideRol rol) {
    this.rol = rol;
  }



  public ObjetoNumeroProcedimiento getObjNumeroProcedimiento() {
    return objNumeroProcedimiento;
  }



  public void setObjNumeroProcedimiento(ObjetoNumeroProcedimiento objNumeroProcedimiento) {
    this.objNumeroProcedimiento = objNumeroProcedimiento;
  }



  public boolean isAdminMensaje() {
    return adminMensaje;
  }



  public void setAdminMensaje(boolean adminMensaje) {
    this.adminMensaje = adminMensaje;
  }



}
