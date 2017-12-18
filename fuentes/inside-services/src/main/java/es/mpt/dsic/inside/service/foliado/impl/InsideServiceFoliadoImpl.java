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

package es.mpt.dsic.inside.service.foliado.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Required;

import es.mpt.dsic.eeutil.client.model.ApplicationLogin;
import es.mpt.dsic.eeutil.client.model.Atributos;
import es.mpt.dsic.eeutil.client.model.DocumentoContenido;
import es.mpt.dsic.eeutil.client.model.Item;
import es.mpt.dsic.eeutil.client.model.ListaItem;
import es.mpt.dsic.eeutil.client.model.ListaItem.Items;
import es.mpt.dsic.eeutil.client.model.ListaPropiedades;
import es.mpt.dsic.eeutil.client.model.OpcionesFoliado;
import es.mpt.dsic.eeutil.client.model.Propiedad;
import es.mpt.dsic.eeutil.client.visDocExp.service.EeUtilService;
import es.mpt.dsic.eeutil.client.visDocExp.service.EeUtilServiceImplService;
import es.mpt.dsic.inside.model.objetos.ObjetoInsideMetadatoAdicional;
import es.mpt.dsic.inside.model.objetos.expediente.metadatos.ObjetoExpedienteInsideMetadatos;
import es.mpt.dsic.inside.model.objetos.foliado.FoliadoNodo;
import es.mpt.dsic.inside.service.foliado.InsideServiceFoliado;


public class InsideServiceFoliadoImpl implements InsideServiceFoliado {

	
	protected static final Log logger = LogFactory.getLog(InsideServiceFoliadoImpl.class);
	
	private boolean activo;
	private static String ACTIVO = "S";
	
	private Properties properties;
	private EeUtilService port;
	private ApplicationLogin applicationLogin;
	
	@PostConstruct
	public void configureFoliado() {
		
		
		String foliadoActivo = properties.getProperty("visualizacion.activo");		
				
		if (!ACTIVO.contentEquals(foliadoActivo)) {
			logger.info("El WS de FOLIADO para la obtención del foliado del expediente no está activo");
			activo = false;
		} else {
			URL url = null;
			String urlFoliado = null;
			try {
				urlFoliado = properties.getProperty("visualizacion.url");
				logger.debug(String.format("El WS de FOLIADO se encuentra en %s", urlFoliado));
				url = new URL (urlFoliado);
			} catch (MalformedURLException me) {
				logger.error("No se puede crear la URL del servicio de foliado " + urlFoliado, me);
			}
			
			EeUtilServiceImplService ss = new EeUtilServiceImplService(url);	
			
		    port = ss.getEeUtilServiceImplPort();  
		    
		    applicationLogin = new ApplicationLogin();
		    applicationLogin.setIdaplicacion(properties.getProperty("visualizacion.idaplicacion"));
		    applicationLogin.setPassword(properties.getProperty("visualizacion.password"));
		    
		    logger.debug(String.format("Utilizando para foliado idaplicacion/password : %s/%s", properties.getProperty("visualizacion.idaplicacion"), properties.getProperty("visualizacion.password")));
		    
		    activo=true;
		}
	    
	}
	
	
	/*// objeto que llame al servicio de foliar.
	@Override
	public FoliadoInside foliar (String identificador, ObjetoExpedienteInsideMetadatos metadatos, FoliadoNodo nodo) throws InsideServiceFoliadoException {
		
		if (!activo) {
			throw new InsideServiceFoliadoException ("El WS de FOLIADO para la obtención del foliado del expediente no está activo");
		}
		
		logger.debug ("Foliando expediente con identificador: " + identificador);
		Item item = obtenerItem (nodo);
		Item item = null;
		try {
			item = obtenerItem ();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Atributos atributos = obtenerAtributos (identificador, metadatos);
		OpcionesFoliado opciones = obtenerOpcionesFoliado ();
		
		SalidaFoliado salidaFoliado = null;
		
		try {
			salidaFoliado = port.foliar(applicationLogin, atributos, item, opciones);
		} catch (Exception e) {
			throw new InsideServiceFoliadoException ("Se ha producido un error en la llamada al servicio de foliado " , e);
		}
		
		FoliadoInside foliadoInside = new FoliadoInside ();
		foliadoInside.setContenido(salidaFoliado.getDocumentoContenido().getBytesDocumento());
		foliadoInside.setMime(salidaFoliado.getDocumentoContenido().getMimeDocumento());
		
		logger.debug ("Foliando expediente con identificador: " + identificador +  ".... OK");
		
		return foliadoInside;
		
	}*/
	
	
	private OpcionesFoliado obtenerOpcionesFoliado () {
		OpcionesFoliado oFoliado = new OpcionesFoliado ();
		oFoliado.setNumeroPaginasBlanco(2);
		return oFoliado;
		
	}
	
	private Atributos obtenerAtributos (String identificador, ObjetoExpedienteInsideMetadatos metadatos) {
		Atributos atributos = new Atributos ();
		atributos.setIdentificador(identificador);
		atributos.setPropiedades(obtenerPropiedades(identificador, metadatos));
		return atributos;
	}
	
	
	private ListaPropiedades obtenerPropiedades (String identificador, ObjetoExpedienteInsideMetadatos metadatos) {
		
		ListaPropiedades lista = new ListaPropiedades ();
		ListaPropiedades.Propiedades prop = new ListaPropiedades.Propiedades ();
		lista.setPropiedades(prop);
		
		Propiedad p = new Propiedad ();
		p.setClave("Versión NTI");
		p.setValor(metadatos.getVersionNTI());
		p.setImportancia("0");
		
		Propiedad p1 = new Propiedad ();
		p1.setClave("Identificador");
		p1.setValor(identificador);
		p1.setImportancia("0");
		
		Propiedad p2 = new Propiedad ();
		p2.setClave("Órgano");
		p2.setValor(listaToString(metadatos.getOrgano(), ";"));
		p2.setImportancia("0");
		
		Propiedad p3 = new Propiedad ();
		p3.setClave("Fecha Apertura Expediente");
		p3.setValor(calendarToStringISO8601(metadatos.getFechaAperturaExpediente()));
		p3.setImportancia("0");			
		
		Propiedad p4 = new Propiedad ();
		p4.setClave("Clasificación");
		p4.setValor(metadatos.getClasificacion());
		p4.setImportancia("0");			
					
		Propiedad p5 = new Propiedad ();
		p5.setClave("Estado");
		p5.setValor(metadatos.getEstado().value());
		p5.setImportancia("0");
		
		Propiedad p6 = new Propiedad ();
		p6.setClave("Interesado");
		p6.setValor(listaToString(metadatos.getInteresado(), ";"));
		p6.setImportancia("0");

		lista.getPropiedades().getPropiedades().add(p);
		lista.getPropiedades().getPropiedades().add(p1);
		lista.getPropiedades().getPropiedades().add(p2);
		lista.getPropiedades().getPropiedades().add(p3);
		lista.getPropiedades().getPropiedades().add(p4);
		lista.getPropiedades().getPropiedades().add(p5);
		lista.getPropiedades().getPropiedades().add(p6);
		
		if (metadatos.getMetadatosAdicionales() != null) {
			
			for (ObjetoInsideMetadatoAdicional data : metadatos.getMetadatosAdicionales()) {
				Propiedad propiedad = new Propiedad();
				propiedad.setClave(data.getNombre());
				propiedad.setValor(data.getValor().toString());
				lista.getPropiedades().getPropiedades().add(propiedad);
			}
			
		}
		
		return lista;
		
	}
	
    /*private static byte[] getBytes(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
          size = is.available();
          buf = new byte[size];
          len = is.read(buf, 0, size);
        } else {
          ByteArrayOutputStream bos = new ByteArrayOutputStream();
          buf = new byte[size];
          while ((len = is.read(buf, 0, size)) != -1)
            bos.write(buf, 0, len);
          buf = bos.toByteArray();
        }
        return buf;
    }*/
		
		
	private Item obtenerItem (FoliadoNodo nodo) {
		
		Item item = new Item ();
		item.setNombre(nodo.getNombre());
		
		if (nodo.getHijos() != null) {
			ListaItem.Items items = new Items ();
			ListaItem listaItems = new ListaItem ();
			listaItems.setItems(items);
			item.setHijos(listaItems);
			for (FoliadoNodo nodoHijo : nodo.getHijos()) {
				Item itemHijo = obtenerItem (nodoHijo);
				itemHijo.setPadre(item);
				
				if (nodoHijo.getContenido() != null) {
					DocumentoContenido documentoContenido = new DocumentoContenido ();
					documentoContenido.setBytesDocumento(nodoHijo.getContenido());
					documentoContenido.setMimeDocumento(nodoHijo.getMimeContenido());
					itemHijo.setDocumentoContenido(documentoContenido);
				}
				
				item.getHijos().getItems().getItems().add(itemHijo);
			}
		}	
		
		return item;
		
	}
	
	
	private String listaToString (List<String> lista, String sep) {
		if (lista == null) {
			return null;
		}
		
		StringBuffer sb = new StringBuffer ();
		for (String str : lista) {
			sb.append(str + sep);
		}
		
		return sb.toString();
	}
	
	private String calendarToStringISO8601 (Calendar calendar) {
		
		DateTime dt = new DateTime(calendar);
		DateTimeFormatter fmt = ISODateTimeFormat.dateTime();		
		return dt.toString(fmt);
		
	}
	
	public Properties getProperties() {
		return properties;
	}

	@Required
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	
}
