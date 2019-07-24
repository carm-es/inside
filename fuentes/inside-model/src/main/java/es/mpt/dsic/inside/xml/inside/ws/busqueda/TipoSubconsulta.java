
package es.mpt.dsic.inside.xml.inside.ws.busqueda;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for tipoSubconsulta.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * <p>
 * 
 * <pre>
 * &lt;simpleType name="tipoSubconsulta">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OR"/>
 *     &lt;enumeration value="AND"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoSubconsulta")
@XmlEnum
public enum TipoSubconsulta {

  OR, AND;

  public String value() {
    return name();
  }

  public static TipoSubconsulta fromValue(String v) {
    return valueOf(v);
  }

}
