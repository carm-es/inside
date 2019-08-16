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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import es.mpt.dsic.inside.service.LoginBusinessService;
import es.mpt.dsic.inside.service.exception.ServiceException;
import eu.stork.peps.auth.commons.IPersonalAttributeList;
import eu.stork.peps.auth.commons.PersonalAttribute;
import eu.stork.peps.auth.commons.PersonalAttributeList;
import eu.stork.peps.auth.commons.STORKAuthnRequest;
import eu.stork.peps.auth.engine.STORKSAMLEngine;
import eu.stork.peps.exceptions.STORKSAMLEngineException;

@Service("loginBusinessService")
@PropertySource("file:${config.path}/clave.properties")
public class LoginBusinessServiceImpl implements LoginBusinessService {

  @Autowired
  private Environment env;

  // CARM ### v2.0.7.1
  @Bean // To resolve ${} in @Value
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }

  @Value("${sp.return}")
  private String spReturn;
  // CARM 2.0.7.1 ###

  @Override
  public byte[] generaTokenClave(String url) throws ServiceException {

    IPersonalAttributeList pAttList = new PersonalAttributeList();

    /* eIdentifier */
    PersonalAttribute att = new PersonalAttribute();
    att.setName(env.getProperty("attribute1.name"));
    att.setIsRequired(true);
    pAttList.add(att);

    /* givenName */
    att = new PersonalAttribute();
    att.setName(env.getProperty("attribute2.name"));
    att.setIsRequired(true);
    pAttList.add(att);

    /* surname */
    att = new PersonalAttribute();
    att.setName(env.getProperty("attribute3.name"));
    att.setIsRequired(true);
    pAttList.add(att);

    STORKAuthnRequest authnRequest = new STORKAuthnRequest();

    authnRequest.setDestination(url);
    authnRequest.setProviderName(env.getProperty("provider.name"));
    authnRequest.setQaa(Integer.parseInt(env.getProperty("sp.qaalevel")));
    authnRequest.setPersonalAttributeList(pAttList);
    /*
     * CARM ### v2.0.7.1^M
     * authnRequest.setAssertionConsumerServiceURL(env.getProperty("sp.return"));
     */
    authnRequest.setAssertionConsumerServiceURL(spReturn);
    // CARM 2.0.7.1 ###

    // new parameters
    authnRequest.setSpSector(env.getProperty("sp.sector"));
    authnRequest.setSpApplication(env.getProperty("sp.aplication"));

    // V-IDP parameters
    authnRequest.setSPID(env.getProperty("provider.name"));

    try {
      STORKSAMLEngine engine = STORKSAMLEngine.getInstance("SP");
      authnRequest = engine.generateSTORKAuthnRequest(authnRequest);

    } catch (STORKSAMLEngineException e) {
      throw new ServiceException("Could not generate token for Saml Request", e.getErrorMessage());
    }

    return authnRequest.getTokenSaml();
  }

}
