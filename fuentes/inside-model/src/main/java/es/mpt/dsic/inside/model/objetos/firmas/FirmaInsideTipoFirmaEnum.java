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

package es.mpt.dsic.inside.model.objetos.firmas;

import java.io.Serializable;

public enum FirmaInsideTipoFirmaEnum implements Serializable {

  /**
   * - TF01 - CSV. - TF02 - XAdES internally detached signature. - TF03 - XAdES enveloped signature.
   * - TF04 - CAdES detached/explicit signature. - TF05 - CAdES attached/implicit signature. - TF06
   * - PAdES.
   */

  TF_01("TF01"), TF_02("TF02"), TF_03("TF03"), TF_04("TF04"), TF_05("TF05"), TF_06("TF06");

  private final String value;

  FirmaInsideTipoFirmaEnum(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static FirmaInsideTipoFirmaEnum fromValue(String v) {
    for (FirmaInsideTipoFirmaEnum c : FirmaInsideTipoFirmaEnum.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

}
