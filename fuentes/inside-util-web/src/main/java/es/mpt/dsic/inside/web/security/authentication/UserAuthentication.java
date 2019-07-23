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

package es.mpt.dsic.inside.web.security.authentication;

import java.util.Collection;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

  private static final long serialVersionUID = 1L;

  private String nif;

  // Número de serie del certificado del usuario autenticado
  private String serialNumber;

  private String unidadOrganicaActiva;

  public UserAuthentication(Object principal, Object credentials,
      Collection<? extends GrantedAuthority> authorities, String serialNumber, String nif,
      String unidadOrganicaActiva) {
    super(principal, credentials, authorities);
    this.serialNumber = serialNumber;
    this.nif = nif;
    this.unidadOrganicaActiva = unidadOrganicaActiva;
  }


  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }


  public String getNif() {
    return nif;
  }


  public void setNif(String nif) {
    this.nif = nif;
  }


  public String getUnidadOrganicaActiva() {
    return unidadOrganicaActiva;
  }


  public void setUnidadOrganicaActiva(String unidadOrganicaActiva) {
    this.unidadOrganicaActiva = unidadOrganicaActiva;
  }

}
