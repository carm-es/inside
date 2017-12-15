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

package es.mpt.dsic.inside.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.mpt.dsic.inside.service.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.exception.ServiceException;
import es.mpt.dsic.inside.web.util.WebConstants;

@Controller
public class UploaderController {

	protected static final Log logger = LogFactory.getLog(UploaderController.class);
	
	@Autowired
	private TemporalDataBusinessService temporalDataBusinessService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(value = "/uploadTempData", method = RequestMethod.POST)
	@ResponseBody
	public String uploadTempData(@RequestBody MultipartFile file,
            @RequestParam String name,
            @RequestParam(required = false, defaultValue = "-1") int chunks,
            @RequestParam(required = false, defaultValue = "-1") int chunk,
            HttpSession session) throws ServiceException {
		logger.debug("inicio uploadTempData");
		String retorno = null;
		try {
			String folder = session.getId();
			retorno = temporalDataBusinessService.escribir(file.getBytes(), folder, name, chunk == 0);
		} catch (FileNotFoundException e) {
			logger.error("Error al escribir fichero temporal");
			throw new ServiceException(e.getMessage(), e, false);
		} catch (ServiceException e) {
			logger.error("Error al escribir fichero temporal");
			throw new ServiceException(e.getMessage(), e, false);
		} catch (IOException e) {
			logger.error("Error al escribir fichero temporal");
			throw new ServiceException(e.getMessage(), e, false);
		}
		logger.debug("fin uploadTempData");
		return retorno;
	}

	@RequestMapping(value = "/uploadTempDataString", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> uploadTempDataString(HttpServletRequest request,
            HttpSession session, Locale locale) throws ServiceException {
		logger.debug("inicio uploadTempData");
		Map<String, String> retorno = new HashMap<String, String>();
		try {
			String folder = session.getId();
			int chunk = Integer.valueOf(request.getParameter("chunk"));
			int chunks = Integer.valueOf(request.getParameter("chunks"));
			String name = request.getParameter("name");
			String data = request.getParameter("data");
			String fileUploaded = temporalDataBusinessService.escribir(data.getBytes(), folder, "tempBase64_" + chunk + ".txt", true);
			
			if (chunk == chunks - 1) {
				StringBuilder strBuilder = new StringBuilder();
				for (int position = 0; position < chunks; position++) {
					strBuilder.append(temporalDataBusinessService.getFileContent(folder, "tempBase64_" + position + ".txt"));
				}
				byte[] binaryData = Base64.decodeBase64(strBuilder.toString());
				
				//borrado fichero cadena base64
				File file  = new File(fileUploaded);
				if (file.exists()) {
					file.delete();
				}
				
				//borrado de fichero original
				File original = new File(temporalDataBusinessService.getPath(folder, name));
				if (original.exists()) {
					original.delete();
				}
				
				//escribimos el fichero firmado
				FileUtils.writeByteArrayToFile(original, binaryData);
				retorno.put("status", "finalizado");
			} else {
				retorno.put("status", "ok");
			}
		} catch (FileNotFoundException e) {
			logger.error("Error al escribir fichero temporal");
			retorno.put("status", "error");
			retorno.put("msg", messageSource.getMessage(
					WebConstants.MSG_FILE_UPLOAD_ERROR, null, locale));
		} catch (ServiceException e) {
			logger.error("Error al escribir fichero temporal");
			retorno.put("status", "error");
			retorno.put("msg", messageSource.getMessage(
					WebConstants.MSG_FILE_UPLOAD_ERROR, null, locale));
		} catch (IOException e) {
			logger.error("Error al escribir fichero temporal");
			retorno.put("status", "error");
			retorno.put("msg", messageSource.getMessage(
					WebConstants.MSG_FILE_UPLOAD_ERROR, null, locale));
		}  catch (Exception e) {
			logger.error("Error al escribir fichero temporal");
			retorno.put("status", "error");
			retorno.put("msg", messageSource.getMessage(
					WebConstants.MSG_FILE_UPLOAD_ERROR, null, locale));
		}
		logger.debug("fin uploadTempData");
		return retorno;
	}
	
	@RequestMapping(value = "/getDataTemp", method = RequestMethod.POST)
	@ResponseBody
	public String getDataTemp(@RequestParam String name, HttpSession session) throws ServiceException {
		String retorno = null;
		try {
			retorno = Base64.encodeBase64String(temporalDataBusinessService.getFile(session.getId(), name));
		} catch (FileNotFoundException e) {
			logger.error("Error al leer fichero temporal");
			throw new ServiceException(e.getMessage(), e, false);
		} catch (IOException e) {
			logger.error("Error al leer fichero temporal");
			throw new ServiceException(e.getMessage(), e, false);
		}
		return retorno;
	}

}