
package es.mpt.dsic.inside.xml.eni.firma;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoFirma.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoFirma">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TF01"/>
 *     &lt;enumeration value="TF02"/>
 *     &lt;enumeration value="TF03"/>
 *     &lt;enumeration value="TF04"/>
 *     &lt;enumeration value="TF05"/>
 *     &lt;enumeration value="TF06"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoFirma")
@XmlEnum
public enum TipoFirma {

    @XmlEnumValue("TF01")
    TF_01("TF01"),
    @XmlEnumValue("TF02")
    TF_02("TF02"),
    @XmlEnumValue("TF03")
    TF_03("TF03"),
    @XmlEnumValue("TF04")
    TF_04("TF04"),
    @XmlEnumValue("TF05")
    TF_05("TF05"),
    @XmlEnumValue("TF06")
    TF_06("TF06");
    private final String value;

    TipoFirma(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoFirma fromValue(String v) {
        for (TipoFirma c: TipoFirma.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
