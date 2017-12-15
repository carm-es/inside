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

package es.mpt.dsic.inside.ws.exception;

/**
 * Enum para agrupar los distintos errores o clases de errores
 * que se pueden generar en el WS.
 * @author miguel.ortega
 *
 */
public enum InsideWsErrors {
	
	/**
	 * Rangos de Errores tal como se han puesto en la documentación:
	 * E-001		Error de autenticación. Crecenciales erroneas
	 * E-002		Error de autenticación. Operación no permitida
	 * E-1xx		Error de Sintaxis en la llamada WS
	 * E-2xx		Omisión de alguno de los parámetros de entrada
	 * E-3xx		Error en el rango de alguno de los parámetros de entrada
	 * E-4XX		Errores diversos motivados por algún error en la petición.
	 * E-5XX		Error interno de Inside
	 * E-6XX		Errores Remisión en la nube
	 * E-999        Operación no permitida
	 * E-998		Aplicación marcada como deshabilitada
	 */
	
	CREDENTIALS_ERROR(new InsideWSError(001,"Credenciales Erroneas. compruebe id de aplicacion y password")),
	CREDENTIALS_ERROR_FORMAT(new InsideWSError(002,"Credenciales Erroneas. formato incorrecto")),
	CREDENTIALS_INVALID(new InsideWSError(003,"Credenciales no válidas. problema de seguridad")),
	UNAUTHORIZED_OPERATION_ERROR (new InsideWSError (999, "Operación no autorizada")),
	DISABLED_ERROR (new InsideWSError (998, "La aplicación está marcada como deshabilitada")),
	
	UNMARSHALL_ERROR(new InsideWSError(101,"Error en los datos de entrada/salida. Posiblemente falte algun campo obligatorio o el xml de la petición esté mal formado. Compruebe el WSDL y los campos obligatorios.")),
	XML_PARSING_ERROR(new InsideWSError(102,"Error parseando xml.")),
	
	EXPEDIENTE_ENI_VACIO(new InsideWSError(201,"El parámetro Expediente ENI no puede estar vacío")),
	METADATOS_EXPEDIENTE_ENI_VACIO(new InsideWSError(202,"El parámetro metadatos del Expediente ENI no puede estar vacío")), 
	IDENTIFICADOR_EXPEDIENTE_ENI_VACIO(new InsideWSError(203,"El parámetro identificador de los metadatos del Expediente ENI no puede estar vacío")),
	IDENTIFICADOR_EXPEDIENTE_VACIO(new InsideWSError(204,"El parámetro identificador del Expediente ENI no puede estar vacío")),
	IDENTIFICADOR_CARPETA_VACIO(new InsideWSError(205,"El parámetro identificador de la carpeta no puede estar vacío")),
	IDENTIFICADOR_EXPEDIENTE_LONGITUD(new InsideWSError(206,"El identificador del expediente no puede ser mayor de 52 caracteres")),
	IDENTIFICADOR_DOCUMENTO_LONGITUD(new InsideWSError(207,"El identificador del documento no puede ser mayor de 52 caracteres")),
	
	VERSION_NTI_DOC_10_ERRONEA(new InsideWSError(290,"La versión del NTI del mensaje es erróneano puede ser distinta a http://administracionelectronica.gob.es/ENI/XSD/v1.0/documento-e")),
	VERSION_NTI_EXP_10_ERRONEA(new InsideWSError(290,"La versión del NTI del mensaje es erróneano puede ser distinta a http://administracionelectronica.gob.es/ENI/XSD/v1.0/expediente-e")),
	
	OBJECT_VALIDATION_ERROR(new InsideWSError(300,"Error en la validación del objeto")),
	ESTADO_VACIO(new InsideWSError(301,"El valor del estado del expediente no es válido")),
	CORRESPONDENCIA_DOCUMENTOS_EXPEDIENTE(new InsideWSError(302,"No hay correspondencia entre el expediente y los doscumentos adjuntos")),
	
	REQUEST_ERROR(new InsideWSError(400,"Error en la petición a Inside")),
	OBJECT_NOT_FOUND(new InsideWSError(401,"No se encuentra el objeto requerido")),
	OBJECT_VERSION_NOT_FOUND(new InsideWSError(402,"No se encuentra el objeto en la versión requerida")),
	OBJECT_ALREADY_EXISTS(new InsideWSError(402,"Ya existe un objeto con el identificador proporcionado")),
	OBJECT_NO_ACTIVE(new InsideWSError(403,"El objeto se encuentra dado de baja")),
	OBJECT_VINCULATED(new InsideWSError(404,"El objeto se encuentra vinculado a")),
	NO_PERMISO(new InsideWSError(405,"La aplicación no tiene permiso")),
	OPERATION_NOT_SUPPORTED(new InsideWSError(499,"Operación no soportada")),
	SIGNER_ERROR(new InsideWSError(406,"Se ha producido un error al realizar el proceso de firma")),
	TOKEN_INCORRECTO(new InsideWSError(407,"El token es incorrecto")),
	CAMPOS_OBLIGATORIOS(new InsideWSError(408,"Existen campos obligatorios sin rellenar")),
	DOCS_SIN_ACTUALIZAR(new InsideWSError(409,"No se puede dar de alta el expediente ya que existen documentos dados de alta en Inside con distinto contenido")),
	CREDENCIALES_REMISION_NUBE_ALREADY_EXISTS(new InsideWSError(410,"Ya existen unas credenciales para la url proporcionada")),
	DOCUMENTOS_REPETIDOS_INDICE(new InsideWSError(411,"Ya existen documentos en el indice con el mismo identificador")),
	SOLICITUD_ACCESO_EXP_APP_URL_ALREADY_EXISTS(new InsideWSError(412,"Ya existe una url de destino donde solicitar peticiones de expedientes para el dir3 padre especificado")),
	ERRORES_EN_EL_INDICE_EXPEDIENTE(new InsideWSError(412,"Errores en el indice del expediente")),
	
	INTERNAL_SERVICE_ERROR(new InsideWSError(500,"Error interno de Inside")), 
	
	PETICION_NULA(new InsideWSError(601,"Se esperaba un objeto petición")),
	PETICION_NO_ENCONTRADO(new InsideWSError(602,"Falta petición")),
	TOKEN_NO_ENCONTRADO(new InsideWSError(603,"Falta token")),
	TOKEN_ERROR(new InsideWSError(604,"El token no existe")),
	TOKEN_CADUCADO(new InsideWSError(605,"El token está caducado")),
	DIR3_ERROR(new InsideWSError(606,"La unidad DIR3 peticionaria errónea")),
	USUARIO_ERROR(new InsideWSError(607,"Usuario peticionario erróneo")),
	DOCUMENTO_NO_ENCONTRADO(new InsideWSError(608,"Documento no encontrado")),
	TOKEN_ERROR_DOCUMENTO(new InsideWSError(609,"El token no es válido para el documento solicitado")),
	DIR3_REMITENTE_ERROR(new InsideWSError(613,"No se especificó dir3 de remitente en el metadato con nombre Dir3Remitente. No se puede redirigir petición a aplicación contenedora")),
	DIR3_REMITENTE_DESCONOCIDO(new InsideWSError(614,"Dir3 de remitente desconocido")),
	COMUNICACION_TOKEN_NO_JUZGADO_ERROR(new InsideWSError(615,"Error al realizar petición a url sw destino")),
	COMUNICACION_TOKEN_FALTAN_DATOS_REMISION_JUSTICIA(new InsideWSError(619,"Si el destino es un juzgado, hay que especificar datos de remisión al cargador de justicia")),
	COMUNICACION_TOKEN_REMISION_JUSTICIA_NIG_INVALIDO(new InsideWSError(620,"Error en parámetro de remisión al cargador de justicia, código NIG debe tener 20 caracteres")),
	COMUNICACION_TOKEN_REMISION_JUSTICIA_ANYO_PROCEDIMIENTO_INVALIDO(new InsideWSError(621,"Error en parámetro de remisión al cargador de justicia, año procedimiento no válido")),
	COMUNICACION_TOKEN_REMISION_JUSTICIA_CLASE_PROCEDIMIENTO_INVALIDO(new InsideWSError(622,"Error en parámetro de remisión al cargador de justicia, clase de procedimiento no válido")),
	COMUNICACION_TOKEN_REMISION_JUSTICIA_NUMERO_PROCEDIMIENTO_INVALIDO(new InsideWSError(623,"Error en parámetro de remisión al cargador de justicia, número de procedimiento debe tener 7 caracteres")),
	COMUNICACION_TOKEN_REMISION_JUSTICIA_DIR3_REMITENTE_INVALIDO(new InsideWSError(624,"Error en parámetro de remisión al cargador de justicia, dir 3 remitente inválido")),
	COMUNICACION_TOKEN_REMISION_JUSTICIA_DESCRIPCION_INVALIDO(new InsideWSError(625,"Error en parámetro de remisión al cargador de justicia, una descripción es obligatoria")),
	COMUNICACION_TOKEN_NO_JUZGADO_NO_SE_ENCONTRO_URL_DESTINATARIO(new InsideWSError(626,"No se pudo encontrar destino para el dir3")),
		
	// Errores en los envíos a Justicia
	UNIDAD_ORGANICA_NO_ENCONTRADA(new InsideWSError(610,"Preparando Remisión a Justicia : No se encontró una unidad orgánica asociada al dir3 destinatario proporcionado")),
	NUMERO_ORDEN_NO_ENCONTRADO(new InsideWSError(611,"Preparando Remisión a Justicia : El número de orden especificado en el Nig no se encuentra para el órgano especificado")),
	CLASE_PROCEDIMIENTO_NO_ENCONTRADO(new InsideWSError(612,"Preparando Remisión a Justicia : La clase de procedimiento especificado no se encuentra para el organo y orden especificado")),
	CONSULTA_MJU_CODIGO_ENVIO_NO_ENCONTRADO(new InsideWSError(616,"No se encuentra código de envío en INSIDE")),
	CONSULTA_MJU_CODIGO_NO_RESULTADO(new InsideWSError(617,"La consulta a MJU no devolvió resultados")),
	CONSULTA_MJU_CODIGO_ERROR(new InsideWSError(618,"La consulta a MJU no se pudo realizar"))
	;

	private final InsideWSError value;
	
	InsideWsErrors(InsideWSError error){
		value = error;
	}

	public InsideWSError getValue() {
		return value;
	}
	
	public static InsideWsErrors valueFromCodigo(int codigo){
		for (InsideWsErrors c: InsideWsErrors.values()) {
            if (c.value.getCodigo() == codigo) {
                return c;
            }
        }
        throw new IllegalArgumentException("No hay error definido para  el código " + codigo);
	}
}
