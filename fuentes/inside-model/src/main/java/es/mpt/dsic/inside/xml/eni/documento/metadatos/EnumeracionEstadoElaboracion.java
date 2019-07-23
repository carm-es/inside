
package es.mpt.dsic.inside.xml.eni.documento.metadatos;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for enumeracionEstadoElaboracion.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="enumeracionEstadoElaboracion">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="EE01"/>
 *     &lt;enumeration value="EE02"/>
 *     &lt;enumeration value="EE03"/>
 *     &lt;enumeration value="EE04"/>
 *     &lt;enumeration value="EE99"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "enumeracionEstadoElaboracion")
@XmlEnum
public enum EnumeracionEstadoElaboracion {

  @XmlEnumValue("EE01")
  EE_01("EE01"), @XmlEnumValue("EE02")
  EE_02("EE02"), @XmlEnumValue("EE03")
  EE_03("EE03"), @XmlEnumValue("EE04")
  EE_04("EE04"), @XmlEnumValue("EE99")
  EE_99("EE99");
  private final String value;

  EnumeracionEstadoElaboracion(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static EnumeracionEstadoElaboracion fromValue(String v) {
    for (EnumeracionEstadoElaboracion c : EnumeracionEstadoElaboracion.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
