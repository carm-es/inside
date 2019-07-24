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

public class ExpedienteENICodigosRespuestaValidacion {

  public static final String E_E_000_CODIGO = "[E.E.000]";
  public static final String E_E_999_CODIGO = "[E.E.999]";

  public static final String E_F_000_CODIGO = "[E.F.000]";
  public static final String E_F_001_CODIGO = "[E.F.001]";
  public static final String E_F_002_CODIGO = "[E.F.002]";
  public static final String E_F_003_CODIGO = "[E.F.003]";
  public static final String E_F_998_CODIGO = "[E.F.998]";
  public static final String E_F_999_CODIGO = "[E.F.999]";

  public static final String E_D_000_CODIGO = "[E.D.000]";
  public static final String E_D_999_CODIGO = "[E.D.999]";

  public static final String E_S_000_CODIGO = "[E.S.000]";
  public static final String E_S_999_CODIGO = "[E.S.999]";

  public static final String E_N_000_CODIGO = "[E.N.000]";
  public static final String E_N_999_CODIGO = "[E.N.999]";

  public static final String E_I_000_CODIGO = "[E.I.000]";
  public static final String E_I_999_CODIGO = "[E.I.999]";

  public static final String E_E_000_DETALLE = "Estructura Correcta";
  public static final String E_E_999_DETALLE = "Estructura Incorrecta";

  public static final String E_F_000_DETALLE = "Firma Válida";
  public static final String E_F_001_DETALLE = "Firma Válida pero Caducada";
  public static final String E_F_002_DETALLE = "Firma Válida sin Timestamp";
  public static final String E_F_003_DETALLE = "Firma Válida no Longeva";
  public static final String E_F_998_DETALLE = "Servicio indisponible";
  public static final String E_F_999_DETALLE = "Firma Incorrecta";

  public static final String E_D_000_DETALLE = "Código DIR3 Existente";
  public static final String E_D_999_DETALLE = "Código DIR3 NO existente";

  public static final String E_S_000_DETALLE = "Código SIA Existente";
  public static final String E_S_999_DETALLE = "Código SIA NO existente";

  public static final String E_N_000_DETALLE = "Correspondencia documentos";
  public static final String E_N_999_DETALLE = "No correspondencia documentos";

  public static final String E_I_000_DETALLE = "Integridad documentos";
  public static final String E_I_999_DETALLE = "No integridad documentos";

}
