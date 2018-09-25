/* Copyright (C) 2016 MINHAP, Gobierno de España
   This program is licensed and may be used, modified and redistributed under the terms
   of the European Public License (EUPL), either version 1.1 or (at your
   option) any later version as soon as they are approved by the European Commission.
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
   or implied. See the License for the specific language governing permissions and
   more details.
   You should have received a copy of the EUPL1.1 license
   along with this program; if not, you may find it at
   http://joinup.ec.europa.eu/software/page/eupl/licence-eupl */

package es.mpt.dsic.loadTables.utils;

public class Constantes {

	public static String LINE_SEPARATOR = System.getProperty("line.separator");
	public static String FIELD_SEPARATOR = ",";
	public static String ETIQUETA_ORGANISMO = "Organismo";
	public static String ETIQUETA_UNIDAD = "tns:unidad";
	public static String FILE_SEPARATOR = System.getProperty("file.separator");
	public static String RUTA_CONFIG_LOG = "log4j.properties";

	// etiquetas
	public static final String ETIQUETA_CODIGO_UNIDAD_ORGANICA = "tns:codigo";
	public static final String ETIQUETA_NOMBRE_UNIDAD_ORGANICA = "tns:denominacion";
	public static final String ETIQUETA_NIVEL_ADMINISTRACION = "tns:nivelAdministracion";
	public static final String ETIQUETA_INDICADOR_ENTIDAD_DERECHO_PUBLICO = "tns:esEDP";
	public static final String ETIQUETA_CODIGO_EXTERNO = "tns:codExterno";
	public static final String ETIQUETA_CODIGO_UNIDAD_SUPERIOR_JERARQUICA = "tns:codUnidadSuperior";
	public static final String ETIQUETA_DENOMINACION_UNIDAD_SUPERIOR_JERARQUICA = "tns:denomUnidadSuperior";
	public static final String ETIQUETA_CODIGO_UNIDAD_ORGANICA_RAIZ = "tns:codUnidadRaiz";
	public static final String ETIQUETA_DENOMINACION_UNIDAD_ORGANICA_RAIZ = "tns:denomUnidadRaiz";
	public static final String ETIQUETA_CODIGO_UNIDAD_RAIZ_ENTIDAD_DERECHO_PUBLICO = "tns:codEDPPrincipal";
	public static final String ETIQUETA_DENOMINACION_UNIDAD_RAIZ_ENTIDAD_DERECHO_PUBLICO = "tns:denomEDPPrincipal";
	public static final String ETIQUETA_NIVEL_JERARQUICO = "tns:nivelJerarquico";
	public static final String ETIQUETA_ESTADO = "tns:estado";
	public static final String ETIQUETA_FECHA_ALTA = "tns:fechaAltaOficial";
	public static final String ETIQUETA_CODIGO_TIPO_ENT_PUBLICA = "tns:codTipoEntPublica";
	public static final String ETIQUETA_CODIGO_TIPO_UNIDAD = "tns:codTipoUnidad";
	public static final String ETIQUETA_CODIGO_AMB_TERRITORIAL = "tns:codAmbTerritorial";
	public static final String ETIQUETA_CODIGO_AMB_ENT_GEOGRAFICA = "tns:codAmbEntGeografica";

	public static String VALOR_SI = "S";
	public static String VALOR_NO = "N";
	public static String FORMATO_FECHA = "yyyy-MM-dd";

	public static String WS_FORMATO_FECHA = "dd/MM/yyyy";

}
