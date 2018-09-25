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

package es.mpt.dsic.inside.service.enivalidation.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import es.mpt.dsic.inside.service.enivalidation.InsideEniValidationService;
import es.mpt.dsic.inside.service.enivalidation.exception.InsideEniValidationConfigurationException;
import es.mpt.dsic.inside.service.enivalidation.exception.InsideEniValidationException;
import es.mpt.dsic.inside.service.util.XMLUtils;

public class InsideEniValidationServiceImpl implements InsideEniValidationService {

	protected static final Log logger = LogFactory.getLog(InsideEniValidationServiceImpl.class);
	
	private String schemasDir;
	
	private Source[] schemasSources;
	
	public String getSchemasDir() {
		return schemasDir;
	}

	@Required
	public void setSchemasDir(String schemasDir) {
		this.schemasDir = schemasDir;
	}

	@PostConstruct
	public void configureService()  {
		logger.info("Leyendo esquemas del directorio: " + schemasDir);
		
		schemasSources = XMLUtils.getSchemasSources(schemasDir);
		
		if (schemasSources.length == 0) {
			logger.error ("No se han podido cargar los schemas del directorio: " + schemasDir);
		}
	}

	
	
	@Override
	public boolean validarExpedienteDocumentoEniFile (byte [] xmlBytes) throws InsideEniValidationConfigurationException, InsideEniValidationException{
				
	    
	    XMLReader parser = null;
	    try {
	    	parser = XMLUtils.createParserForValidation(XMLUtils.getSchemasSources(schemasDir));
	    } catch (Exception e) {	    
	    	throw new InsideEniValidationConfigurationException ("Error al crear el parser", e);
	    }
	    
	    try {
	    	InputSource xmlSource = new InputSource (new ByteArrayInputStream (xmlBytes));
	    	parser.parse(xmlSource);
	    } catch (IOException e) {
	    	throw new InsideEniValidationException ("Validación incorrecta: No se puede parsear la fuente");
	    } catch (SAXException e) {
	    	throw new InsideEniValidationException ("Validación incorrecta : " + e.getMessage());
	    }
	    
	    return true;	
	}
	
}
