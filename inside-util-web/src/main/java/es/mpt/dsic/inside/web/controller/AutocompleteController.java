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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.mpt.dsic.inside.model.objetos.ObjetoInsideDocumentoUnidad;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideExpedienteUnidad;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatosEnumeracionEstados;
import es.mpt.dsic.inside.model.objetos.usuario.ObjetoInsideUsuario;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.exception.InSideServiceException;
import es.mpt.dsic.inside.service.util.InsideUtils;
import es.mpt.dsic.inside.store.hibernate.entity.UnidadOrganica;
import es.mpt.dsic.inside.web.object.ComboItem;
import es.mpt.dsic.inside.web.util.MetadatosEEMGDE;
import es.mpt.dsic.inside.web.util.WebConstants;
import es.mpt.dsic.loadTables.hibernate.service.impl.UnidadOrganicaServiceImpl;

@Controller
public class AutocompleteController {

	/** Logger de la clase. */
	private static Logger logger = Logger.getLogger(AutocompleteController.class);

	@Autowired
	private UnidadOrganicaServiceImpl unidadOrganicaService;

	@Autowired
	private InSideService insideService;

	private static final int MAX_RESULTS = 10;

	/**
	 * Recupera las unidades DIR3 para el autocompletado.
	 * 
	 * @param codigo
	 *            String con el código a completar o parte de él
	 * @return List<SelectItemVO> lista a mostrar en el autocomplete
	 */
	@RequestMapping(value = "/autocomplete/dir3", method = RequestMethod.GET)
	@ResponseBody
	public List<ComboItem> autocompleteCodigoDIR(@RequestParam(value = "term") final String codigo) {

		logger.info("[INI] Entramos en autocompleteCodigoDIR. ");

		// Se obtienen las unidades que cumplen con la búsqueda
		logger.info("Buscamos las unidades...");
		List<Criterion> criterios = new ArrayList<Criterion>();
		criterios.add(Restrictions.or(Restrictions.like("nombreUnidadOrganica", "%" + codigo + "%"),
				Restrictions.like("codigoUnidadOrganica", "%" + codigo + "%")));

		List<UnidadOrganica> unidades = unidadOrganicaService.findByCriterias(0, 10, UnidadOrganica.class, criterios);

		// Se carga la lista a devolver convirtiendo los resultados
		logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
		List<ComboItem> listaReturn = new ArrayList<ComboItem>();
		for (UnidadOrganica unidad : unidades) {
			listaReturn.add(new ComboItem(unidad.getCodigoUnidadOrganica(), unidad.getNombreUnidadOrganica(), null));
		}

		logger.info("[FIN] Salimos de autocompleteCodigoDIR. Total a mostrar: " + listaReturn.size());

		return listaReturn;
	}

	/**
	 * Recupera las unidades DIR3 vigentes para el autocompletado. Son los de
	 * estado en V o T
	 * 
	 * @param codigo
	 *            String con el código a completar o parte de él
	 * @return List<SelectItemVO> lista a mostrar en el autocomplete
	 */
	@RequestMapping(value = "/autocomplete/dir3Vigentes", method = RequestMethod.GET)
	@ResponseBody
	public List<ComboItem> autocompleteCodigoDIRVigentes(@RequestParam(value = "term") final String codigo) {

		logger.info("[INI] Entramos en autocompleteCodigoDIRVigentes. ");

		// Se obtienen las unidades que cumplen con la búsqueda
		logger.info("Buscamos las unidades...");
		List<Criterion> criterios = new ArrayList<Criterion>();
		criterios.add(Restrictions.or(Restrictions.like("nombreUnidadOrganica", "%" + codigo + "%"),
				Restrictions.like("codigoUnidadOrganica", "%" + codigo + "%")));
		criterios.add(Restrictions.or(Restrictions.eq("estado", "T"), Restrictions.eq("estado", "V")));

		List<UnidadOrganica> unidades = unidadOrganicaService.findByCriterias(0, 10, UnidadOrganica.class, criterios);

		// Se carga la lista a devolver convirtiendo los resultados
		logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
		List<ComboItem> listaReturn = new ArrayList<ComboItem>();
		for (UnidadOrganica unidad : unidades) {
			listaReturn.add(new ComboItem(unidad.getCodigoUnidadOrganica(), unidad.getNombreUnidadOrganica(), null));
		}

		logger.info("[FIN] Salimos de autocompleteCodigoDIRVigentes. Total a mostrar: " + listaReturn.size());

		return listaReturn;
	}

	/**
	 * Recupera las unidades DIR3 para el autocompletado del envio a justicia.
	 * son las que tienen valor en el campo codigoExterno distinto de null y
	 * nivelAdministracion = 6
	 * 
	 * @param codigo
	 *            String con el código a completar o parte de él
	 * @return List<SelectItemVO> lista a mostrar en el autocomplete
	 */
	@RequestMapping(value = "/autocomplete/dir3EnvioJusticia", method = RequestMethod.GET)
	@ResponseBody
	public List<ComboItem> autocompleteCodigoDIREnvioJusticia(@RequestParam(value = "term") final String codigo) {

		logger.info("[INI] Entramos en autocompleteCodigoDIREnvioJusticia. ");

		// Se obtienen las unidades que cumplen con la búsqueda
		logger.info("Buscamos las unidades...");
		List<Criterion> criterios = new ArrayList<Criterion>();
		java.lang.Byte nivelAdministracion = 6;
		criterios.add(Restrictions.and(
				Restrictions.and(Restrictions.isNotNull("codigoExterno"),
						Restrictions.eq("nivelAdministracion", nivelAdministracion)),
				Restrictions.or(Restrictions.like("nombreUnidadOrganica", "%" + codigo + "%"),
						Restrictions.like("codigoUnidadOrganica", "%" + codigo + "%"))));

		List<UnidadOrganica> unidades = unidadOrganicaService.findByCriterias(0, 10, UnidadOrganica.class, criterios);

		// Se carga la lista a devolver convirtiendo los resultados
		logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
		List<ComboItem> listaReturn = new ArrayList<ComboItem>();
		for (UnidadOrganica unidad : unidades) {
			listaReturn.add(
					new ComboItem(unidad.getCodigoUnidadOrganica(), unidad.getNombreUnidadOrganica(), unidad.getCodigoExterno()));
		}

		logger.info("[FIN] Salimos de autocompleteCodigoDIREnvioJusticia. Total a mostrar: " + listaReturn.size());

		return listaReturn;
	}

	/**
	 * Recupera las unidades DIR3 para el autocompletado del envio a justicia.
	 * Los dir3 vigentes son estada V y T son las que tienen valor en el campo
	 * codigoExterno distinto de null y nivelAdministracion = 6
	 * 
	 * @param codigo
	 *            String con el código a completar o parte de él
	 * @return List<SelectItemVO> lista a mostrar en el autocomplete
	 */
	@RequestMapping(value = "/autocomplete/dir3EnvioJusticiaVigentes", method = RequestMethod.GET)
	@ResponseBody
	public List<ComboItem> autocompleteCodigoDIREnvioJusticiaVigentes(
			@RequestParam(value = "term") final String codigo) {

		logger.info("[INI] Entramos en autocompleteCodigoDIREnvioJusticiaVigentes. ");

		// Se obtienen las unidades que cumplen con la búsqueda
		logger.info("Buscamos las unidades...");
		List<Criterion> criterios = new ArrayList<Criterion>();
		java.lang.Byte nivelAdministracion = 6;
		criterios.add(Restrictions.and(
				Restrictions.and(Restrictions.isNotNull("codigoExterno"),
						Restrictions.eq("nivelAdministracion", nivelAdministracion)),
				Restrictions.or(Restrictions.like("nombreUnidadOrganica", "%" + codigo + "%"),
						Restrictions.like("codigoUnidadOrganica", "%" + codigo + "%"))));

		criterios.add(Restrictions.or(Restrictions.eq("estado", "T"), Restrictions.eq("estado", "V")));

		List<UnidadOrganica> unidades = unidadOrganicaService.findByCriterias(0, 10, UnidadOrganica.class, criterios);

		// Se carga la lista a devolver convirtiendo los resultados
		logger.info("Transformamos en tipo SelectIntemVO para mostrar lista de autocompletado...");
		List<ComboItem> listaReturn = new ArrayList<ComboItem>();
		for (UnidadOrganica unidad : unidades) {
			listaReturn.add(
					new ComboItem(unidad.getCodigoUnidadOrganica(), unidad.getNombreUnidadOrganica(), unidad.getCodigoExterno()));
		}

		logger.info("[FIN] Salimos de autocompleteCodigoDIREnvioJusticiaVigentes. Total a mostrar: " + listaReturn.size());

		return listaReturn;
	}

	/**
	 * Recupera los expedientes para el autocompletado.
	 * 
	 * @param codigo
	 *            String con el código a completar o parte de él
	 * @param allExp
	 *            incluimos expedientes cerrados y abierto
	 * @param uniAct
	 *            solo expedientes asociados a nuestra unidad activa
	 * @return List<SelectItemVO> lista a mostrar en el autocomplete
	 */
	@RequestMapping(value = "/autocomplete/expedientes", method = RequestMethod.GET)
	@ResponseBody
	public List<ComboItem> autocompleteExpedientes(final HttpServletRequest request,
			@RequestParam(value = "allExp") final boolean allExp, @RequestParam(value = "uniAct") final boolean uniAct,
			@RequestParam(value = "term") final String codigo) {
		List<ComboItem> listaReturn = new ArrayList<ComboItem>();
		logger.info("[INI] Entramos en autocompleteExpediente. ");
		
		ObjetoInsideUsuario usuario = (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);

		if (usuario != null) {
			try {
				List<ObjetoInsideExpedienteUnidad> expedientes = insideService.getExpedientesUnidadAutocompleter(usuario, uniAct);

				if (CollectionUtils.isNotEmpty(expedientes)) {
					addElementsComboExpedient(allExp, codigo, listaReturn, expedientes);
				}
			} catch (InSideServiceException e) {
				logger.error("Error en el autocompleteExpedientes" + codigo, e);
			}
		} else {
			ComboItem comboItem = new ComboItem("", "Perdió la sesión del usuario, acceda de nuevo a Inside", null);
			listaReturn.add(comboItem);
		}
		logger.info("[FIN] Salimos de autocompleteExpediente.");
		
		return listaReturn;
	}

	public int addElementsComboExpedient(final boolean allExp, final String codigo, List<ComboItem> listaReturn,
			List<ObjetoInsideExpedienteUnidad> expedientes) {
		int i = 0;
		for (ObjetoInsideExpedienteUnidad expedienteUnidad : expedientes) {
			if (isCandidateForCombo(allExp, codigo, i, expedienteUnidad)) {
				i++;
				listaReturn.add(new ComboItem(expedienteUnidad.getIdentificador(), expedienteUnidad.getIdentificador(), null));
			} else if (i == MAX_RESULTS) {
				break;
			}
		}
		return i;
	}

	public boolean isCandidateForCombo(final boolean allExp, final String codigo, int i,
			ObjetoInsideExpedienteUnidad expedienteUnidad) {
		return like(expedienteUnidad.getIdentificador().toLowerCase(), codigo.toLowerCase()) && i < MAX_RESULTS
				&& (allExp ? true
						: ObjetoExpedienteInsideMetadatosEnumeracionEstados.E_01.value()
								.equals(expedienteUnidad.getEstadoExpediente().value()));
	}

	/**
	 * Recupera los documentos para el autocompletado.
	 * 
	 * @param codigo
	 *            String con el código a completar o parte de él
	 * @return List<SelectItemVO> lista a mostrar en el autocomplete
	 * @throws IOException
	 */
	@RequestMapping(value = "/autocomplete/documentos", method = RequestMethod.GET)
	@ResponseBody
	public List<ComboItem> autocompleteDocumentos(final HttpServletRequest request,
			@RequestParam(value = "term") final String codigo) {
		List<ComboItem> listaReturn = new ArrayList<ComboItem>();
		logger.info("[INI] Entramos en autocompleteDocumentos");

		ObjetoInsideUsuario usuario = (ObjetoInsideUsuario) request.getSession().getAttribute(WebConstants.USUARIO_SESSION);

		if (usuario != null) {
			try {
				List<ObjetoInsideDocumentoUnidad> documentos = insideService.getDocumentosUnidad(usuario, false);

				if (CollectionUtils.isNotEmpty(documentos)) {
					addElementsComboDocs(codigo, listaReturn, documentos);
				}
			} catch (InSideServiceException e) {
				logger.error("Error en el autocompleteDocumentos" + codigo, e);
			}

			logger.info("[FIN] Salimos de autocompleteDocumentos");
		} else {
			ComboItem comboItem = new ComboItem("", "Perdió la sesión del usuario, acceda de nuevo a Inside", null);
			listaReturn.add(comboItem);
		}
		return listaReturn;
	}

	public void addElementsComboDocs(final String codigo, List<ComboItem> listaReturn,
			List<ObjetoInsideDocumentoUnidad> documentos) {
		int i = 0;
		for (ObjetoInsideDocumentoUnidad documento : documentos) {
			if (isCandidateForComboDocs(codigo, i, documento)) {
				i++;
				listaReturn.add(new ComboItem(documento.getIdentificador(), documento.getIdentificador(), null));
			} else if (i == MAX_RESULTS) {
				break;
			}
		}
	}

	public boolean isCandidateForComboDocs(final String codigo, int i, ObjetoInsideDocumentoUnidad documento) {
		return like(documento.getIdentificador().toLowerCase(), codigo.toLowerCase()) && i < MAX_RESULTS;
	}

	/**
	 * Recupera los metadatos para el autocompletado.
	 * 
	 * @param codigo
	 *            String con el código a completar o parte de él
	 * @return List<SelectItemVO> lista a mostrar en el autocomplete
	 */
	@RequestMapping(value = "/autocomplete/metadatos", method = RequestMethod.GET)
	@ResponseBody
	public List<ComboItem> autocompleteMetadatos(@RequestParam(value = "term") final String codigo) {
		logger.info("[INI] Entramos en autocompleteMetadatos");

		List<ComboItem> listaReturn = new ArrayList<ComboItem>();
		List<MetadatosEEMGDE> metadatos = Arrays.asList(MetadatosEEMGDE.values());
		addElementsComboMetadatos(codigo, listaReturn, metadatos);

		logger.info("[FIN] Salimos de autocompleteDocumentos");

		return listaReturn;
	}

	public void addElementsComboMetadatos(final String codigo, List<ComboItem> listaReturn, List<MetadatosEEMGDE> metadatos) {
		int i = 0;
		for (MetadatosEEMGDE metadato : metadatos) {
			if (isCandidateForComboMetadatos(codigo, i, metadato)) {
				i++;
				listaReturn.add(new ComboItem(metadato.getValue(), metadato.getValue(), null));
			} else if (i == MAX_RESULTS) {
				break;
			}
		}
	}

	public boolean isCandidateForComboMetadatos(final String codigo, int i, MetadatosEEMGDE metadato) {
		return like(metadato.getValue().toLowerCase(), codigo.toLowerCase()) && i < MAX_RESULTS;
	}

	private boolean like(String toBeCompare, String by) {
		if (by != null && toBeCompare != null) {
			return InsideUtils.remplazaCaracteres(toBeCompare).contains(InsideUtils.remplazaCaracteres(by));
		} else {
			return false;
		}

	}

}
