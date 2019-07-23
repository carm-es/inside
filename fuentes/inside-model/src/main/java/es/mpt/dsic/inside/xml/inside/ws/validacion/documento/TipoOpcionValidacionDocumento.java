
package es.mpt.dsic.inside.xml.inside.ws.validacion.documento;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for TipoOpcionValidacionDocumento.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="TipoOpcionValidacionDocumento">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="TOVD01"/>
 *     &lt;enumeration value="TOVD02"/>
 *     &lt;enumeration value="TOVD03"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoOpcionValidacionDocumento")
@XmlEnum
public enum TipoOpcionValidacionDocumento {

  @XmlEnumValue("TOVD01")
  TOVD_01("TOVD01"), @XmlEnumValue("TOVD02")
  TOVD_02("TOVD02"), @XmlEnumValue("TOVD03")
  TOVD_03("TOVD03");
  private final String value;

  TipoOpcionValidacionDocumento(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static TipoOpcionValidacionDocumento fromValue(String v) {
    for (TipoOpcionValidacionDocumento c : TipoOpcionValidacionDocumento.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
