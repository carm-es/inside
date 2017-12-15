/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.inside.ws.security.wss4j;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.WSPasswordCallback;
import org.apache.ws.security.WSSConfig;
import org.apache.ws.security.WSSecurityException;
import org.apache.ws.security.handler.RequestData;
import org.apache.ws.security.message.token.UsernameToken;
import org.apache.ws.security.util.Base64;
import org.apache.ws.security.validate.Credential;
import org.apache.ws.security.validate.Validator;

public class CustomUserTokenValidator implements Validator {
	
	 private static org.apache.commons.logging.Log log = 
		        org.apache.commons.logging.LogFactory.getLog(CustomUserTokenValidator.class);
		    
		    /**
		     * Validate the credential argument. It must contain a non-null UsernameToken. A 
		     * CallbackHandler implementation is also required to be set.
		     * 
		     * If the password type is either digest or plaintext, it extracts a password from the 
		     * CallbackHandler and then compares the passwords appropriately.
		     * 
		     * If the password is null it queries a hook to allow the user to validate UsernameTokens
		     * of this type. 
		     * 
		     * @param credential the Credential to be validated
		     * @param data the RequestData associated with the request
		     * @throws WSSecurityException on a failed validation
		     */
		    public Credential validate(Credential credential, RequestData data) throws WSSecurityException {
		        if (credential == null || credential.getUsernametoken() == null) {
		            throw new WSSecurityException(WSSecurityException.FAILURE, "noCredential");
		        }
		        
		        boolean handleCustomPasswordTypes = false;
		        boolean passwordsAreEncoded = false;
		        String requiredPasswordType = null;
		        WSSConfig wssConfig = data.getWssConfig();
		        if (wssConfig != null) {
		            handleCustomPasswordTypes = wssConfig.getHandleCustomPasswordTypes();
		            passwordsAreEncoded = wssConfig.getPasswordsAreEncoded();
		            requiredPasswordType = wssConfig.getRequiredPasswordType();
		        }
		        
		        UsernameToken usernameToken = credential.getUsernametoken();
		        usernameToken.setPasswordsAreEncoded(passwordsAreEncoded);
		        
		        String pwType = usernameToken.getPasswordType();
		        if (log.isDebugEnabled()) {
		            log.debug("UsernameToken user " + usernameToken.getName());
		            log.debug("UsernameToken password type " + pwType);
		        }
		        
		        if (requiredPasswordType != null && !requiredPasswordType.equals(pwType)) {
		            if (log.isDebugEnabled()) {
		                log.debug("Authentication failed as the received password type does not " 
		                    + "match the required password type of: " + requiredPasswordType);
		            }
		            throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION);
		        }
		        
		        //
		        // If the UsernameToken is hashed or plaintext, then retrieve the password from the
		        // callback handler and compare directly. If the UsernameToken is of some unknown type,
		        // then delegate authentication to the callback handler
		        //
		        String password = usernameToken.getPassword();
		        if (usernameToken.isHashed()) {
		            verifyDigestPassword(usernameToken, data);
		        } else if (WSConstants.PASSWORD_TEXT.equals(pwType)
		            || (password != null && (pwType == null || "".equals(pwType.trim())))) {
		            verifyPlaintextPassword(usernameToken, data);
		        } else if (password != null) {
		            if (!handleCustomPasswordTypes) {
		                if (log.isDebugEnabled()) {
		                    log.debug("Authentication failed as handleCustomUsernameTokenTypes is false");
		                }
		                throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION);
		            }
		            verifyCustomPassword(usernameToken, data);
		        } else {
		            verifyUnknownPassword(usernameToken, data);
		        }
		        return credential;
		    }
		    
		    /**
		     * Verify a UsernameToken containing a password of some unknown (but specified) password
		     * type. It does this by querying a CallbackHandler instance to obtain a password for the
		     * given username, and then comparing it against the received password.
		     * This method currently uses the same logic as the verifyPlaintextPassword case, but it in
		     * a separate protected method to allow users to override the validation of the custom 
		     * password type specific case.
		     * @param usernameToken The UsernameToken instance to verify
		     * @throws WSSecurityException on a failed authentication.
		     */
		    protected void verifyCustomPassword(UsernameToken usernameToken,
		                                        RequestData data) throws WSSecurityException {
		        verifyPlaintextPassword(usernameToken, data);
		    }
		    
		    /**
		     * Verify a UsernameToken containing a plaintext password. It does this by querying a 
		     * CallbackHandler instance to obtain a password for the given username, and then comparing
		     * it against the received password.
		     * This method currently uses the same logic as the verifyDigestPassword case, but it in
		     * a separate protected method to allow users to override the validation of the plaintext 
		     * password specific case.
		     * @param usernameToken The UsernameToken instance to verify
		     * @throws WSSecurityException on a failed authentication.
		     */
		    protected void verifyPlaintextPassword(UsernameToken usernameToken,
		                                           RequestData data) throws WSSecurityException {
		        verifyDigestPassword(usernameToken, data);
		    }
		    
		    /**
		     * Verify a UsernameToken containing a password digest. It does this by querying a 
		     * CallbackHandler instance to obtain a password for the given username, and then comparing
		     * it against the received password.
		     * @param usernameToken The UsernameToken instance to verify
		     * @throws WSSecurityException on a failed authentication.
		     */
		    protected void verifyDigestPassword(UsernameToken usernameToken,
		                                        RequestData data) throws WSSecurityException {
		        if (data.getCallbackHandler() == null) {
		            throw new WSSecurityException(WSSecurityException.FAILURE, "noCallback");
		        }
		        
		        String user = usernameToken.getName();
		        String password = usernameToken.getPassword();
		        String nonce = usernameToken.getNonce();
		        String createdTime = usernameToken.getCreated();
		        String pwType = usernameToken.getPasswordType();
		        boolean passwordsAreEncoded = usernameToken.getPasswordsAreEncoded();
		        
		        WSPasswordCallback pwCb = 
		            new WSPasswordCallback(user, null, pwType, WSPasswordCallback.USERNAME_TOKEN, data);
		        try {
		            data.getCallbackHandler().handle(new Callback[]{pwCb});
		        } catch (IOException e) {
		            if (log.isDebugEnabled()) {
		                log.debug(e);
		            }
		            throw new WSSecurityException(
		                WSSecurityException.FAILED_AUTHENTICATION, null, null, e
		            );
		        } catch (UnsupportedCallbackException e) {
		            if (log.isDebugEnabled()) {
		                log.debug(e);
		            }
		            throw new WSSecurityException(
		                WSSecurityException.FAILED_AUTHENTICATION, null, null, e
		            );
		        }
		        String origPassword = pwCb.getPassword();
		        if (origPassword == null) {
		            if (log.isDebugEnabled()) {
		                log.debug("Callback supplied no password for: " + user);
		            }
		            throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION);
		        }
		        if (usernameToken.isHashed()) {
		            String passDigest;
		            if (passwordsAreEncoded) {
		                passDigest = UsernameToken.doPasswordDigest(nonce, createdTime, Base64.decode(origPassword));
		            } else {
		                passDigest = UsernameToken.doPasswordDigest(nonce, createdTime, origPassword);
		            }
		            if (!passDigest.equals(password)) {
		                throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION);
		            }
		        } else {
		        	String encodedPassword = DigestUtils.sha256Hex(password);
		            if (!origPassword.equals(encodedPassword)) {
		                throw new WSSecurityException(WSSecurityException.FAILED_AUTHENTICATION);
		            }
		        }
		    }
		    
		    /**
		     * Verify a UsernameToken containing no password. This does nothing - but is in a separate
		     * method to allow the end-user to override validation easily. 
		     * @param usernameToken The UsernameToken instance to verify
		     * @throws WSSecurityException on a failed authentication.
		     */
		    protected void verifyUnknownPassword(UsernameToken usernameToken,
		                                         RequestData data) throws WSSecurityException {
		        //
		    }

}
