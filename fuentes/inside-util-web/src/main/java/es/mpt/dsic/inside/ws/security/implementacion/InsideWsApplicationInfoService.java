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

package es.mpt.dsic.inside.ws.security.implementacion;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideAplicacion;
import es.mpt.dsic.inside.service.users.InsideUsersService;
import es.mpt.dsic.inside.service.users.exception.InsideUsersServiceException;

public class InsideWsApplicationInfoService implements UserDetailsService {

  @Autowired
  private InsideUsersService usersService;

  protected static final Log logger = LogFactory.getLog(InsideWsApplicationInfoService.class);

  public UserDetails loadUserByUsername(String username)
      throws UsernameNotFoundException, DataAccessException {
    ObjetoInsideAplicacion obj = null;
    try {
      obj = usersService.getAplicacion(username);
    } catch (InsideUsersServiceException e) {
      throw new InsideWsApplicationDataAccessException(
          "Error al recuperar la aplicación " + username, e);
    }

    if (obj == null) {
      throw new UsernameNotFoundException("Usuario <" + username + ">  no encontrado");
    }

    String id = obj.getAplicacion();
    String password = obj.getPasswordHash();

    boolean enabled = obj.isActiva();
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

    for (String rol : obj.getRoles()) {
      roles.add(new SimpleGrantedAuthority(rol));
    }

    logger.debug("Se intenta logar el usuario : " + username);
    User user = new User(id, password, enabled, accountNonExpired, credentialsNonExpired,
        accountNonLocked, roles);
    logger.debug("Logado correctamente usuario: " + username);
    return user;

  }

}


