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

package es.mpt.dsic.inside.web.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import es.mpt.dsic.eeutil.client.visDocExp.model.Item;
import es.mpt.dsic.eeutil.client.visDocExp.model.Propiedad;
import es.mpt.dsic.inside.model.converter.InsideConverterDocumento;
import es.mpt.dsic.inside.model.objetos.documento.ObjetoDocumentoInside;
import es.mpt.dsic.inside.model.objetos.visualizacion.TipoResultadoVisualizacion;
import es.mpt.dsic.inside.service.InSideService;
import es.mpt.dsic.inside.service.InsideUtilService;
import es.mpt.dsic.inside.service.TemporalDataBusinessService;
import es.mpt.dsic.inside.service.util.Constantes;
import es.mpt.dsic.inside.service.util.XMLUtils;
import es.mpt.dsic.inside.service.visualizacion.InsideServiceVisualizacion;
import es.mpt.dsic.inside.web.object.VisualizacionItem;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoEniBinarioOTipo;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoDocumentoVisualizacionInside;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoOpcionesVisualizacionDocumento;
import es.mpt.dsic.inside.xml.inside.ws.visualizacion.documento.TipoResultadoVisualizacionDocumentoInside;

@Component
public class VisualizacionUtils {

  @Autowired
  TemporalDataBusinessService temporalDataBusinessService;

  @Autowired
  InsideServiceVisualizacion insideServiceVisualizacion;

  @Autowired
  InsideUtilService insideUtilService;

  @Autowired
  InSideService insideService;

  private static final String DOCUMENT_ID = "documentId";

  public void visualizarDocumento(ModelAndView retorno, HttpSession session,
      HttpServletRequest request) throws Exception {

    TipoResultadoVisualizacion resultado = null;

    if (StringUtils.isBlank(request.getParameter("plantilla"))
        || request.getParameter("plantilla").equals(Constantes.TEMPLATE_DEFAULT)) {
      resultado = getDefaultVisualizacion(session.getId(), request.getParameter(DOCUMENT_ID),
          request.getParameter("metadatosAdicionalesVisualizar"));
    } else {
      resultado = getVisualizacionPlantilla(session.getId(), request.getParameter(DOCUMENT_ID),
          request.getParameter("metadatosAdicionalesVisualizar"),
          request.getParameter("plantilla"));
    }

    retorno.addObject("IDENTIFICADORDOCUMENTO",
        resultado.getObjetoDocumentoInside().getIdentificador());
    byte[] data = resultado.getTipoResultadoVisualizacionDocumentoInside().getContenido();
    retorno.addObject("data", Base64.encodeBase64String(data));

    ObjetoDocumentoInside documentoInside = resultado.getObjetoDocumentoInside();
    Item itemDocumento =
        insideServiceVisualizacion.documentoInsideToItemVisualizacion(documentoInside, null);

    // Metadatos Eni
    List<VisualizacionItem> visItems = obtenerVisualizacionItems(itemDocumento);

    // Listado de firmas
    getFirmas(visItems, retorno);

    // Metadatos Adicionales
    retorno.addObject("listAdicionales", resultado.getListAdicionales());
  }

  private TipoDocumentoEniBinarioOTipo getDocumentoEniBinario(String idSession, String documentId)
      throws FileNotFoundException, IOException {
    TipoDocumentoEniBinarioOTipo docEniBinario = new TipoDocumentoEniBinarioOTipo();
    byte[] decoded = temporalDataBusinessService.getFile(idSession, documentId);
    docEniBinario.setDocumentoEniBinario(decoded);
    return docEniBinario;
  }

  private List<MetadatoAdicional> getMetadatosAdicionales(byte[] data)
      throws ParserConfigurationException, SAXException, IOException {
    List<MetadatoAdicional> listaAdicionales = null;
    Node nodoAdicionales = XMLUtils.getNode(data, "ns7:metadatosAdicionales");
    if (nodoAdicionales != null) {
      NodeList nodosAdicionales = nodoAdicionales.getChildNodes();
      if (nodosAdicionales != null && nodosAdicionales.getLength() > 0) {
        listaAdicionales = new ArrayList<MetadatoAdicional>();
        for (int i = 0; i < nodosAdicionales.getLength(); i++) {
          if (nodosAdicionales.item(i).getNodeType() == Node.ELEMENT_NODE) {
            Node node = nodosAdicionales.item(i);
            MetadatoAdicional metadatoAdicional = new MetadatoAdicional();
            metadatoAdicional.setNombre(node.getAttributes().getNamedItem("nombre").getNodeValue());
            metadatoAdicional.setValor(node.getTextContent().trim());
            metadatoAdicional.setTipo("string");
            listaAdicionales.add(metadatoAdicional);
          }
        }
      }
    }
    return listaAdicionales;
  }

  /**
   * Recorre la estructura de Ã¡rbol en preorden para obtener una lista ordenada de Items de
   * Visualizacion. *
   * 
   * @param nodo Estructura en Ã¡rbol que contiene la informaciÃ³n que quiere foliarse.
   * 
   * @return Lista ordenada de VisualizacionItem en el orden en que se quieren imprimir.
   * @throws Exception
   */
  public List<VisualizacionItem> obtenerVisualizacionItems(Item nodo) {
    List<VisualizacionItem> visualizacionItems = new ArrayList<VisualizacionItem>();
    int prof = 0;
    int[] marcadores = {0};
    addVisualizacionItemToList(nodo, prof, visualizacionItems, marcadores);
    return visualizacionItems;
  }

  /**
   * AÃ±ade un nuevo elemento a la lista pasada como parÃ¡metro.
   * 
   * @param nodo
   * @param prof
   * @param lista
   */
  private void addVisualizacionItemToList(final Item nodo, int prof, List<VisualizacionItem> lista,
      int[] marcadores) {

    VisualizacionItem ii = new VisualizacionItem();
    ii.setNombre(nodo.getNombre());
    ii.setProfundidad(prof + 1);
    ii.setPropiedades(listaPropiedadesToEntryList(nodo));
    ii.setMarcadores(marcadores);
    lista.add(ii);

    if (nodo.getHijos() != null) {
      prof++;

      for (Item hijo : nodo.getHijos().getItems().getItems()) {
        int[] nuevosMarcadores = Arrays.copyOf(marcadores, marcadores.length + 1);
        nuevosMarcadores[nuevosMarcadores.length - 1] =
            nodo.getHijos().getItems().getItems().indexOf(hijo) + 1;
        addVisualizacionItemToList(hijo, prof, lista, nuevosMarcadores);
      }
    }
  }

  private List<Entry<String, String>> listaPropiedadesToEntryList(final Item nodo) {
    List<Entry<String, String>> entryList = null;
    if (nodo.getPropiedadesItem() != null) {
      entryList = new ArrayList<Entry<String, String>>();
      for (Propiedad propiedad : nodo.getPropiedadesItem().getPropiedades().getPropiedades()) {
        Entry<String, String> entry =
            new AbstractMap.SimpleEntry<String, String>(propiedad.getClave(), propiedad.getValor());
        entryList.add(entry);
      }
    }

    return entryList;
  }

  public void getFirmas(List<VisualizacionItem> visItems, ModelAndView retorno) {
    List<VisualizacionItem> firmas = null;
    for (VisualizacionItem item : visItems) {
      if (item.getProfundidad() == 1) {
        retorno.addObject("metadatoDocumento", item);
      } else if (item.getProfundidad() == 2) {
        if (firmas == null)
          firmas = new ArrayList<VisualizacionItem>();
        firmas.add(item);
      }
    }
    retorno.addObject("firmas", firmas);
  }

  private TipoResultadoVisualizacion getDefaultVisualizacion(String idSession, String documentId,
      String metadatosAdicionales) throws Exception {
    TipoDocumentoVisualizacionInside visualizar = new TipoDocumentoVisualizacionInside();
    visualizar.setDocumentoEni(getDocumentoEniBinario(idSession, documentId));

    TipoOpcionesVisualizacionDocumento opciones = new TipoOpcionesVisualizacionDocumento();
    opciones.setEstamparImagen(false);
    opciones.setEstamparNombreOrganismo(false);
    opciones.setEstamparPie(false);
    opciones.setFilasNombreOrganismo(null);
    opciones.setTextoPie(null);
    visualizar.setOpcionesVisualizacionDocumento(opciones);

    setAdicionalesToVisualizar(metadatosAdicionales, visualizar);
    List<MetadatoAdicional> listaAdiconales = visualizar.getMetadatosAdicionales() != null
        ? visualizar.getMetadatosAdicionales().getMetadatoAdicional()
        : null;
    return new TipoResultadoVisualizacion(insideUtilService.visualizarDocumentoEni(visualizar),
        listaAdiconales, InsideConverterDocumento.documentoVisualizacionToInside(visualizar));
  }

  private void setAdicionalesToVisualizar(String adicionales,
      TipoDocumentoVisualizacionInside visualizar) throws JsonParseException, JsonMappingException,
      IOException, ParserConfigurationException, SAXException {
    List<MetadatoAdicional> listAdicionales = null;
    if (StringUtils.isNotEmpty(adicionales)) {
      ObjectMapper mapper = new ObjectMapper();
      listAdicionales =
          mapper.readValue(adicionales, new TypeReference<List<MetadatoAdicional>>() {});
    } else {
      listAdicionales =
          getMetadatosAdicionales(visualizar.getDocumentoEni().getDocumentoEniBinario());
    }

    TipoMetadatosAdicionales aditionalData = new TipoMetadatosAdicionales();
    if (listAdicionales != null) {
      aditionalData.getMetadatoAdicional().addAll(listAdicionales);
      visualizar.setMetadatosAdicionales(aditionalData);
    }
  }

  private TipoResultadoVisualizacion getVisualizacionPlantilla(String idSession, String documentId,
      String metadatosAdicionales, String plantilla) throws Exception {
    TipoDocumentoVisualizacionInside visualizar = new TipoDocumentoVisualizacionInside();
    TipoDocumentoEniBinarioOTipo docEniBinario = getDocumentoEniBinario(idSession, documentId);
    visualizar.setDocumentoEni(docEniBinario);

    setAdicionalesToVisualizar(metadatosAdicionales, visualizar);

    TipoResultadoVisualizacionDocumentoInside result =
        insideServiceVisualizacion.visualizacionConPlantilla(plantilla, visualizar);

    return new TipoResultadoVisualizacion(result,
        visualizar.getMetadatosAdicionales().getMetadatoAdicional(),
        InsideConverterDocumento.documentoVisualizacionToInside(visualizar));
  }
}
