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

package es.mpt.dsic.inside.service.store.impl.hibernate.converter;


import java.io.Reader;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import es.mpt.dsic.inside.model.objetos.expediente.ObjetoSolicitudAccesoExpediente;
import es.mpt.dsic.inside.service.store.exception.InsideServiceStoreException;
import es.mpt.dsic.inside.store.hibernate.entity.SolicitudAccesoExpediente;
import es.mpt.dsic.inside.xml.inside.MetadatoAdicional;
import es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales;


public class InsideServiceStoreHibernateConverterSolicitudAccesoExpediente {

  public static SolicitudAccesoExpediente toEntity(
      ObjetoSolicitudAccesoExpediente objetoSolicitudAccesoExpediente)
      throws InsideServiceStoreException {

    SolicitudAccesoExpediente entity = new SolicitudAccesoExpediente();

    entity.setId(objetoSolicitudAccesoExpediente.getId());
    entity.setEndpointRemitente(objetoSolicitudAccesoExpediente.getEndpointRemitente());
    entity.setDir3Peticionario(objetoSolicitudAccesoExpediente.getDir3Peticionario());
    entity.setAsunto(objetoSolicitudAccesoExpediente.getAsunto());
    entity.setMotivacion(objetoSolicitudAccesoExpediente.getMotivacion());
    entity.setFechaPeticion(objetoSolicitudAccesoExpediente.getFechaPeticion());
    entity.setIdPeticion(objetoSolicitudAccesoExpediente.getIdPeticion());
    entity.setResultado(objetoSolicitudAccesoExpediente.getResultado());
    entity.setUsuarioPeticionario(objetoSolicitudAccesoExpediente.getUsuarioPeticionario());
    entity.setCodigoSia(objetoSolicitudAccesoExpediente.getCodigoSia());
    entity.setIdExpedienteSolicitado(objetoSolicitudAccesoExpediente.getIdExpedienteSolicitado());
    entity.setEnvioCorrecto(objetoSolicitudAccesoExpediente.getEnvioCorrecto());
    entity.setSolicitudTokenDenegada(objetoSolicitudAccesoExpediente.getSolicitudTokenDenegada());
    if (objetoSolicitudAccesoExpediente.getMetadatos() != null) {
      entity.setMetadatos(objetoSolicitudAccesoExpediente.getMetadatos().getBytes());
    }
    entity
        .setDir3ExpedienteSolicitado(objetoSolicitudAccesoExpediente.getDir3ExpedienteSolicitado());
    entity.setNifUsuarioGeneraToken(objetoSolicitudAccesoExpediente.getNifUsuarioGeneraToken());

    return entity;

  }

  public static ObjetoSolicitudAccesoExpediente toInside(SolicitudAccesoExpediente entity)
      throws InsideServiceStoreException, JAXBException {

    ObjetoSolicitudAccesoExpediente objetoSolicitudAccesoExpediente =
        new ObjetoSolicitudAccesoExpediente();

    objetoSolicitudAccesoExpediente.setId(entity.getId());
    objetoSolicitudAccesoExpediente.setEndpointRemitente(entity.getEndpointRemitente());
    objetoSolicitudAccesoExpediente.setDir3Peticionario(entity.getDir3Peticionario());
    objetoSolicitudAccesoExpediente.setAsunto(entity.getAsunto());
    objetoSolicitudAccesoExpediente.setMotivacion(entity.getMotivacion());
    objetoSolicitudAccesoExpediente.setFechaPeticion(entity.getFechaPeticion());
    objetoSolicitudAccesoExpediente.setIdPeticion(entity.getIdPeticion());
    objetoSolicitudAccesoExpediente.setResultado(entity.getResultado());
    objetoSolicitudAccesoExpediente.setUsuarioPeticionario(entity.getUsuarioPeticionario());
    objetoSolicitudAccesoExpediente.setCodigoSia(entity.getCodigoSia());
    objetoSolicitudAccesoExpediente.setIdExpedienteSolicitado(entity.getIdExpedienteSolicitado());
    objetoSolicitudAccesoExpediente.setEnvioCorrecto(entity.getEnvioCorrecto());
    objetoSolicitudAccesoExpediente.setSolicitudTokenDenegada(entity.getSolicitudTokenDenegada());
    try {
      if (entity.getMetadatos() != null) {
        objetoSolicitudAccesoExpediente.setMetadatos(new String(entity.getMetadatos()));
        JAXBContext jc =
            JAXBContext.newInstance(es.mpt.dsic.inside.xml.inside.TipoMetadatosAdicionales.class);
        Unmarshaller unMarshaller = jc.createUnmarshaller();
        Reader reader = new StringReader(objetoSolicitudAccesoExpediente.getMetadatos());
        XMLInputFactory factory = XMLInputFactory.newInstance(); // Or newFactory()
        XMLStreamReader xmlReader;
        xmlReader = factory.createXMLStreamReader(reader);
        JAXBElement<TipoMetadatosAdicionales> root2 =
            unMarshaller.unmarshal(xmlReader, TipoMetadatosAdicionales.class);
        TipoMetadatosAdicionales car = root2.getValue();

        String pintarMetadatos = "";
        String pintarMetadatosTitle = "";
        if (!car.getMetadatoAdicional().isEmpty()) {
          for (MetadatoAdicional iterable_element : car.getMetadatoAdicional()) {
            org.w3c.dom.Element e = (org.w3c.dom.Element) iterable_element.getValor();
            pintarMetadatos +=
                "<p><b>" + iterable_element.getNombre() + "</b> : " + e.getTextContent() + "</p>";
            pintarMetadatosTitle +=
                "" + iterable_element.getNombre() + " : " + e.getTextContent() + "\n";

          }
        }

        objetoSolicitudAccesoExpediente.setPintarMetadatos(pintarMetadatos);
        objetoSolicitudAccesoExpediente.setPintarMetadatosTitle(pintarMetadatosTitle);
      }
    } catch (XMLStreamException e) {

    }

    objetoSolicitudAccesoExpediente
        .setDir3ExpedienteSolicitado(entity.getDir3ExpedienteSolicitado());

    return objetoSolicitudAccesoExpediente;
  }

}
