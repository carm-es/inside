
package es.mpt.dsic.inside.xml.eni.expediente.metadatos;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for enumeracionEstados.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="enumeracionEstados">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="E01"/>
 *     &lt;enumeration value="E02"/>
 *     &lt;enumeration value="E03"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "enumeracionEstados")
@XmlEnum
public enum EnumeracionEstados {

  @XmlEnumValue("E01")
  E_01("E01"), @XmlEnumValue("E02")
  E_02("E02"), @XmlEnumValue("E03")
  E_03("E03");
  private final String value;

  EnumeracionEstados(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static EnumeracionEstados fromValue(String v) {
    for (EnumeracionEstados c : EnumeracionEstados.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
