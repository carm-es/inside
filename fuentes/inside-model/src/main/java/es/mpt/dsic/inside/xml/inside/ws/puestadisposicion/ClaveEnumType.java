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


package es.mpt.dsic.inside.xml.inside.ws.puestadisposicion;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClaveEnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClaveEnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VersionNTI"/>
 *     &lt;enumeration value="Identificador"/>
 *     &lt;enumeration value="Organo"/>
 *     &lt;enumeration value="FechaAperturaExpediente"/>
 *     &lt;enumeration value="Clasificacion"/>
 *     &lt;enumeration value="Estado"/>
 *     &lt;enumeration value="interesado"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ClaveEnumType")
@XmlEnum
public enum ClaveEnumType {

    @XmlEnumValue("VersionNTI")
    VERSION_NTI("VersionNTI"),
    @XmlEnumValue("Identificador")
    IDENTIFICADOR("Identificador"),
    @XmlEnumValue("Organo")
    ORGANO("Organo"),
    @XmlEnumValue("FechaAperturaExpediente")
    FECHA_APERTURA_EXPEDIENTE("FechaAperturaExpediente"),
    @XmlEnumValue("Clasificacion")
    CLASIFICACION("Clasificacion"),
    @XmlEnumValue("Estado")
    ESTADO("Estado"),
    @XmlEnumValue("interesado")
    INTERESADO("interesado");
    private final String value;

    ClaveEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClaveEnumType fromValue(String v) {
        for (ClaveEnumType c: ClaveEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
