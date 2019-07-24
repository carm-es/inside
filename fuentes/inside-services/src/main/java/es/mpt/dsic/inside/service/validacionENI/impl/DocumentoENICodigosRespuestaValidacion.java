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

package es.mpt.dsic.inside.service.validacionENI.impl;

public class DocumentoENICodigosRespuestaValidacion {

  public static final String D_E_000_CODIGO = "[D.E.000]";
  public static final String D_E_000_DETALLE = "Estructura Correcta";

  public static final String D_E_999_CODIGO = "[D.E.999]";
  public static final String D_E_999_DETALLE = "Estructura Incorrecta";

  public static final String D_F_000_CODIGO = "[D.F.000]";
  public static final String D_F_000_DETALLE = "Firma Valida";

  public static final String D_F_001_CODIGO = "[D.F.001]";
  public static final String D_F_001_DETALLE = "Firma Válida pero Caducada";

  public static final String D_F_002_CODIGO = "[D.F.002]";
  public static final String D_F_002_DETALLE = " Firma Válida sin Timestamp";

  public static final String D_F_003_CODIGO = "[D.F.003]";
  public static final String D_F_003_DETALLE = "Firma Válida no Longeva";

  public static final String D_F_998_CODIGO = "[D.F.998]";
  public static final String D_F_998_DETALLE = "Servicio indisponible";

  public static final String D_F_999_CODIGO = "[D.F.999]";
  public static final String D_F_999_DETALLE = "Firma Incorrecta";

  public static final String D_D_000_CODIGO = "[D.D.000]";
  public static final String D_D_000_DETALLE = "CodigoDIR3 Existente";
  public static final String D_D_999_CODIGO = "[D.D.999]";
  public static final String D_D_999_DETALLE = "CodigoDIR3 NO existente";

}
