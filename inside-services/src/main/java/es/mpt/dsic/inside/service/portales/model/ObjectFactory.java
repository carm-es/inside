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


package es.mpt.dsic.inside.service.portales.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.mpt.dsic.inside.service.portales.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CargoOrganismoWsObjectArrayElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoOrganismoWsObjectArrayElement");
    private final static QName _CargoUnidadDirWsObjectArrayElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoUnidadDirWsObjectArrayElement");
    private final static QName _CargosWsObjectElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargosWsObjectElement");
    private final static QName _CargoEntidadWsObjectElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoEntidadWsObjectElement");
    private final static QName _CargoUnidadDirWsObjectElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoUnidadDirWsObjectElement");
    private final static QName _CargoInstitucionWsObjectElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoInstitucionWsObjectElement");
    private final static QName _CargoEntidadWsObjectArrayElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoEntidadWsObjectArrayElement");
    private final static QName _CargoInstitucionWsObjectArrayElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoInstitucionWsObjectArrayElement");
    private final static QName _CargoOrganismoWsObjectElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "CargoOrganismoWsObjectElement");
    private final static QName _AuthHeaderElement_QNAME = new QName("https://ssweb.seap.minhap.es/portalEELL/", "AuthHeaderElement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.mpt.dsic.inside.service.portales.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CargoOrganismoWsObject }
     * 
     */
    public CargoOrganismoWsObject createCargoOrganismoWsObject() {
        return new CargoOrganismoWsObject();
    }

    /**
     * Create an instance of {@link AuthHeader }
     * 
     */
    public AuthHeader createAuthHeader() {
        return new AuthHeader();
    }

    /**
     * Create an instance of {@link CargoInstitucionWsObject }
     * 
     */
    public CargoInstitucionWsObject createCargoInstitucionWsObject() {
        return new CargoInstitucionWsObject();
    }

    /**
     * Create an instance of {@link CargoUnidadDirWsObject }
     * 
     */
    public CargoUnidadDirWsObject createCargoUnidadDirWsObject() {
        return new CargoUnidadDirWsObject();
    }

    /**
     * Create an instance of {@link CargoEntidadWsObject }
     * 
     */
    public CargoEntidadWsObject createCargoEntidadWsObject() {
        return new CargoEntidadWsObject();
    }

    /**
     * Create an instance of {@link CargoInstitucionWsObjectArray }
     * 
     */
    public CargoInstitucionWsObjectArray createCargoInstitucionWsObjectArray() {
        return new CargoInstitucionWsObjectArray();
    }

    /**
     * Create an instance of {@link CargoEntidadWsObjectArray }
     * 
     */
    public CargoEntidadWsObjectArray createCargoEntidadWsObjectArray() {
        return new CargoEntidadWsObjectArray();
    }

    /**
     * Create an instance of {@link CargosWsObject }
     * 
     */
    public CargosWsObject createCargosWsObject() {
        return new CargosWsObject();
    }

    /**
     * Create an instance of {@link CargoUnidadDirWsObjectArray }
     * 
     */
    public CargoUnidadDirWsObjectArray createCargoUnidadDirWsObjectArray() {
        return new CargoUnidadDirWsObjectArray();
    }

    /**
     * Create an instance of {@link CargoOrganismoWsObjectArray }
     * 
     */
    public CargoOrganismoWsObjectArray createCargoOrganismoWsObjectArray() {
        return new CargoOrganismoWsObjectArray();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoOrganismoWsObjectArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoOrganismoWsObjectArrayElement")
    public JAXBElement<CargoOrganismoWsObjectArray> createCargoOrganismoWsObjectArrayElement(CargoOrganismoWsObjectArray value) {
        return new JAXBElement<CargoOrganismoWsObjectArray>(_CargoOrganismoWsObjectArrayElement_QNAME, CargoOrganismoWsObjectArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoUnidadDirWsObjectArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoUnidadDirWsObjectArrayElement")
    public JAXBElement<CargoUnidadDirWsObjectArray> createCargoUnidadDirWsObjectArrayElement(CargoUnidadDirWsObjectArray value) {
        return new JAXBElement<CargoUnidadDirWsObjectArray>(_CargoUnidadDirWsObjectArrayElement_QNAME, CargoUnidadDirWsObjectArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargosWsObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargosWsObjectElement")
    public JAXBElement<CargosWsObject> createCargosWsObjectElement(CargosWsObject value) {
        return new JAXBElement<CargosWsObject>(_CargosWsObjectElement_QNAME, CargosWsObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoEntidadWsObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoEntidadWsObjectElement")
    public JAXBElement<CargoEntidadWsObject> createCargoEntidadWsObjectElement(CargoEntidadWsObject value) {
        return new JAXBElement<CargoEntidadWsObject>(_CargoEntidadWsObjectElement_QNAME, CargoEntidadWsObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoUnidadDirWsObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoUnidadDirWsObjectElement")
    public JAXBElement<CargoUnidadDirWsObject> createCargoUnidadDirWsObjectElement(CargoUnidadDirWsObject value) {
        return new JAXBElement<CargoUnidadDirWsObject>(_CargoUnidadDirWsObjectElement_QNAME, CargoUnidadDirWsObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoInstitucionWsObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoInstitucionWsObjectElement")
    public JAXBElement<CargoInstitucionWsObject> createCargoInstitucionWsObjectElement(CargoInstitucionWsObject value) {
        return new JAXBElement<CargoInstitucionWsObject>(_CargoInstitucionWsObjectElement_QNAME, CargoInstitucionWsObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoEntidadWsObjectArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoEntidadWsObjectArrayElement")
    public JAXBElement<CargoEntidadWsObjectArray> createCargoEntidadWsObjectArrayElement(CargoEntidadWsObjectArray value) {
        return new JAXBElement<CargoEntidadWsObjectArray>(_CargoEntidadWsObjectArrayElement_QNAME, CargoEntidadWsObjectArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoInstitucionWsObjectArray }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoInstitucionWsObjectArrayElement")
    public JAXBElement<CargoInstitucionWsObjectArray> createCargoInstitucionWsObjectArrayElement(CargoInstitucionWsObjectArray value) {
        return new JAXBElement<CargoInstitucionWsObjectArray>(_CargoInstitucionWsObjectArrayElement_QNAME, CargoInstitucionWsObjectArray.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CargoOrganismoWsObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "CargoOrganismoWsObjectElement")
    public JAXBElement<CargoOrganismoWsObject> createCargoOrganismoWsObjectElement(CargoOrganismoWsObject value) {
        return new JAXBElement<CargoOrganismoWsObject>(_CargoOrganismoWsObjectElement_QNAME, CargoOrganismoWsObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AuthHeader }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "https://ssweb.seap.minhap.es/portalEELL/", name = "AuthHeaderElement")
    public JAXBElement<AuthHeader> createAuthHeaderElement(AuthHeader value) {
        return new JAXBElement<AuthHeader>(_AuthHeaderElement_QNAME, AuthHeader.class, null, value);
    }

}
