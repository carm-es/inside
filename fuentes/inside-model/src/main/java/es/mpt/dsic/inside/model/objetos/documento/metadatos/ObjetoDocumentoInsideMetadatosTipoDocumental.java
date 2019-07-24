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

package es.mpt.dsic.inside.model.objetos.documento.metadatos;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlEnumValue;

/**
 * Documentos de decisión - TD01 - Resolución. - TD02 - Acuerdo. - TD03 - Contrato. - TD04 -
 * Convenio. - TD05 - Declaración.
 * 
 * Documentos de transmisión - TD06 - Comunicación. - TD07 - Notificación. - TD08 - Publicación. -
 * TD09 - Acuse de recibo.
 * 
 * Documentos de constancia - TD10 - Acta. - TD11 - Certificado. - TD12 - Diligencia.
 * 
 * Documentos de juicio - TD13 - Informe.
 * 
 * Documentos de ciudadano - TD14 - Solicitud. - TD15 - Denuncia. - TD16 - Alegación. - TD17 -
 * Recursos. - TD18 - Comunicación ciudadano. - TD19 - Factura. - TD20 - Otros incautados.
 * 
 * Otros - TD99 - Otros.
 */
public enum ObjetoDocumentoInsideMetadatosTipoDocumental implements Serializable {

  TD_01("TD01"), TD_02("TD02"), TD_03("TD03"), TD_04("TD04"), TD_05("TD05"), TD_06("TD06"), TD_07(
      "TD07"), TD_08("TD08"), TD_09("TD09"), TD_10("TD10"), TD_11("TD11"), TD_12(
          "TD12"), TD_13("TD13"), TD_14("TD14"), TD_15("TD15"), TD_16("TD16"), TD_17(
              "TD17"), TD_18("TD18"), TD_19("TD19"), TD_20("TD20"), TD_51("TD51"), TD_52(
                  "TD52"), TD_53("TD53"), TD_54("TD54"), TD_55("TD55"), TD_56("TD56"), TD_57(
                      "TD57"), TD_58("TD58"), TD_59("TD59"), TD_60("TD60"), TD_61("TD61"), TD_62(
                          "TD62"), TD_63("TD63"), TD_64("TD64"), TD_65("TD65"), TD_66(
                              "TD66"), TD_67("TD67"), TD_68("TD68"), TD_69("TD69"), TD_99("TD99");

  private final String value;

  ObjetoDocumentoInsideMetadatosTipoDocumental(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static ObjetoDocumentoInsideMetadatosTipoDocumental fromValue(String v) {
    for (ObjetoDocumentoInsideMetadatosTipoDocumental c : ObjetoDocumentoInsideMetadatosTipoDocumental
        .values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
