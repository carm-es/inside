
package es.mpt.dsic.inside.xml.inside.ws.validacion.expediente.mtom;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoOpcionValidacionExpedienteMtom.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoOpcionValidacionExpedienteMtom">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TOVE01"/>
 *     &lt;enumeration value="TOVE02"/>
 *     &lt;enumeration value="TOVE03"/>
 *     &lt;enumeration value="TOVE04"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoOpcionValidacionExpedienteMtom")
@XmlEnum
public enum TipoOpcionValidacionExpedienteMtom {

    @XmlEnumValue("TOVE01")
    TOVE_01("TOVE01"),
    @XmlEnumValue("TOVE02")
    TOVE_02("TOVE02"),
    @XmlEnumValue("TOVE03")
    TOVE_03("TOVE03"),
    @XmlEnumValue("TOVE04")
    TOVE_04("TOVE04");
    private final String value;

    TipoOpcionValidacionExpedienteMtom(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoOpcionValidacionExpedienteMtom fromValue(String v) {
        for (TipoOpcionValidacionExpedienteMtom c: TipoOpcionValidacionExpedienteMtom.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
