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

package es.mpt.dsic.inside.web.util;

public enum MetadatosEEMGDE {

  METADATO_TIPO_ENTIDAD("eEMGDE0.TipoEntidad"),

  METADATO_CATEGORIA("eEMGDE1.Categoria"),

  METADATO_IDENTIFICADOR_SECUENCIA_IDENTIFICADOR("eEMGDE2.1.Identificador.SecuenciaIdentificador"),

  METADATO_IDENTIFICADOR_ESQUEMA_IDENTIFICADOR("eEMGDE2.2.Identificador.EsquemaIdentificador"),

  METADATO_NOMBRE_NOMBRE_NATURAL("eEMGDE3.1.Nombre.NombreNatural"),

  METADATO_NOMBRE_NOMBRE_FICHERO("eEMGDE3.2.Nombre.NombreFichero"),

  METADATO_FECHAS_FECHAINICIO("eEMGDE4.1.Fechas.FechaInicio"),

  METADATO_FECHAS_FECHA_FIN("eEMGDE4.2.Fechas.FechaFin"),

  METADATO_DESCRIPCION("eEMGDE5.Descripcion"),

  METADATO_AMBITO("eEMGDE.7.Ambito"),

  METADATO_SEGURIDAD_NIVELSEGURIDAD_NIVELACCESO("eEMGDE8.1.1.Seguridad.NivelSeguridad.NivelAcceso"),

  METADATO_SEGURIDAD_NIVELSEGURIDAD_CODIGO_POLITICA_CONTROL_ACCESO(
      "eEMGDE8.1.2.Seguridad.NivelSeguridad.CodigoPoliticaControlAcceso"),

  METADATO_SEGURIDAD_ADVERTENCIA_SEGURIDAD_TEXTO_ADVERTENCIA(
      "eEMGDE8.2.1.Seguridad.AdvertenciaSeguridad.TextoAdvertencia"),

  METADATO_SEGURIDAD_ADVERTENCIA_SEGURIDAD_CATEGORIA(
      "eEMGDE8.2.2.Seguridad.AdvertenciaSeguridad.Categoria"),

  METADATO_SEGURIDAD_SENSIBILIDAD_DATOS_CARACTER_PERSONAL(
      "eEMGDE8.4.Seguridad.SensibilidadDatosCaracterPersonal"),

  METADATO_SEGURIDAD_CLASIFICACION_ENS("eEMGDE8.5.Seguridad.ClasificacionENS"),

  METADATO_SEGURIDAD_NIVEL_CONFIDENCIALIDAD_INFORMACION(
      "eEMGDE8.6.Seguridad.NivelConfidencialidadInformacion"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_TIPO_ACCESO(
      "eEMGDE9.1.DerechosAccesoUsoReutilizacion.TipoAcceso"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_CODIGO_CAUSA_LIMITACION(
      "eEMGDE9.2.DerechosAccesoUsoReutilizacion.CodigoCausaLimitacion"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_CAUSA_LEGAL_LIMITACION(
      "eEMGDE9.3.DerechosAccesoUsoReutilizacion.CausaLegalLimitación"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_CONDICIONES_REUTILIZACION(
      "eEMGDE9.4.DerechosAccesoUsoReutilizacion.CondicionesReutilizacion"),

  METADATO_IDIOMA("eEMGDE11.Idioma"),

  METADATO_PUNTOS_ACCESO_TERMINO_PUNTO_ACCESO("eEMGDE12.1.PuntosAcceso.TerminoPuntoAcceso"),

  METADATO_PUNTOS_ACCESO_ID_PUNTO_ACCESO("eEMGDE12.2.PuntosAcceso.IdPuntoAcceso"),

  METADATO_PUNTOS_ACCESO_ESQUEMA_PUNTO_ACCESO("eEMGDE12.3.PuntosAcceso.EsquemaPuntoAcceso"),

  METADATO_CALIFICACION_VALORACION_VALOR_PRIMARIO__TIPO_VALOR(
      "eEMGDE13.1.1.1.Calificacion.Valoracion.ValorPrimario.TipoValor"),

  METADATO_CALIFICACION_VALORACION_VALOR_PRIMARIO_PLAZO(
      "eEMGDE13.1.1.2.Calificacion.Valoracion.ValorPrimario.Plazo"),

  METADATO_CALIFICACION_VALORACION_VALOR_SECUNDARIO(
      "eEMGDE13.1.2.Calificacion.Valoracion.ValorSecundario"),

  METADATO_CALIFICACION_DICTAMEN_TIPO_DICTAMEN("eEMGDE13.2.1.Calificacion.Dictamen.TipodeDictamen"),

  METADATO_CALIFICACION_DICTAMEN_ACCION_DICTAMINADA(
      "eEMGDE13.2.2.Calificacion.Dictamen.AccionDictaminada"),

  METADATO_CALIFICACION_DICTAMEN_PLAZO_EJECUCION_ACCION_DICTAMINADA(
      "eEMGDE13.2.3.Calificacion.Dictamen.PlazoEjecucionAccionDictaminada"),

  METADATO_CALIFICACION_TRANSFERENCIA_FASE_ARCHIVO(
      "eEMGDE13.3.1.Calificacion.Transferencia.FaseArchivo"),

  METADATO_CALIFICACION_TRANSFERENCIA_PLAZO_TRANSFERENCIA(
      "eEMGDE13.3.2.Calificacion.Transferencia.PlazoTransferencia"),

  METADATO_CALIFICACION_DOCUMENTO_ESENCIAL("eEMGDE13.4.Calificacion.DocumentoEsencial"),

  METADATO_CARACTERISTICAS_TECNICAS_FORMATO_NOMBRE_FORMATO(
      "eEMGDE14.1.1.CaracteristicasTecnicas.Formato.NombreFormato"),

  METADATO_CARACTERISTICAS_TECNICAS_FORMATO_EXTENSION_FICHERO(
      "eEMGDE14.1.2.CaracteristicasTecnicas.Formato.ExtensionFichero"),

  METADATO_CARACTERISTICAS_TECNICAS_VERSION_FORMATO(
      "eEMGDE14.2.CaracteristicasTecnicas.VersionFormato"),

  METADATO_CARACTERISTICAS_TECNICAS_RESOLUCION("eEMGDE14.3.CaracteristicasTecnicas.Resolucion"),

  METADATO_CARACTERISTICAS_TECNICAS_TAMANO_DIMENSIONES_FISICAS(
      "eEMGDE14.4.1.CaracteristicasTecnicas.Tamano.DimensionesFisicas"),

  METADATO_CARACTERISTICAS_TECNICAS_TAMANO_TAMANO_LOGICO(
      "eEMGDE14.4.2.CaracteristicasTecnicas.Tamano.TamanoLogico"),

  METADATO_CARACTERISTICAS_TECNICAS_TAMANO_CANTIDAD(
      "eEMGDE14.4.3.CaracteristicasTecnicas.Tamano.Cantidad"),

  METADATO_CARACTERISTICAS_TECNICAS_TAMANO_UNIDADES(
      "eEMGDE14.4.4.CaracteristicasTecnicas.Tamano.Unidades"),

  METADATO_CARACTERISTICAS_TECNICAS_PROFUNDIDAD_COLOR(
      "eEMGDE14.5.CaracteristicasTecnicas.ProfundidadColor"),

  METADATO_UBICACION_SOPORTE("eEMGDE15.1.Ubicacion.Soporte"),

  METADATO_UBICACION_LOCALIZACION("eEMGDE15.2.Ubicacion.Localizacion"),

  METADATO_VERIFICACION_INTEGRIDAD_ALGORITMO("eEMGDE16.1.VerificacionIntegridad.Algoritmo"),

  METADATO_VERIFICACION_INTEGRIDAD_VALOR("eEMGDE16.2.VerificacionIntegridad.Valor"),

  METADATO_FIRMA_TIPO_FIRMA_FORMATO_FIRMA("eEMGDE17.1.1.Firma.TipoFirma.FormatoFirma"),

  METADATO_FIRMA_TIPO_FIRMA_PERFIL_FIRMA("eEMGDE17.1.2.Firma.TipoFirma.PerfilFirma"),

  METADATO_FIRMA_ROLFIRMA("eEMGDE17.2.Firma.RolFirma"),

  METADATO_FIRMA_FORMATOFIRMA_VALOR_CSV("eEMGDE17.3.Firma.FormatoFirma.ValorCSV"),

  METADATO_DEFINICION_GENERACION_CSV("eEMGDE17.4.DefinicionGeneracionCSV"),

  METADATO_FIRMA_FIRMANTE_NOMBRE_APELLIDOS("eEMGDE17.5.1.Firma.Firmante.NombreApellidos"),

  METADATO_FIRMA_FIRMANTE_NUMERO_IDENTIFICACION_FIRMANTES(
      "eEMGDE17.5.2.Firma.Firmante.NumeroIdentificacionFirmantes"),

  METADATO_FIRMA_FIRMANTE_EN_CALIDAD_DE("eEMGDE17.5.3.Firma.Firmante.EnCalidadDe"),

  METADATO_FIRMA_NIVEL_FIRMA("eEMGDE17.5.4.Firma.NivelFirma"),

  METADATO_FIRMA_INFORMACION_ADICIONAL("eEMGDE17.5.5.Firma.InformacionAdicional"),

  METADATO_TIPO_DOCUMENTAL("eEMGDE18.TipoDocumental"),

  METADATO_PRIORIDAD("eEMGDE19.Prioridad"),

  METADATO_ESTADO_ELABORACION("eEMGDE20.EstadoElaboracion"),

  METADATO_TRAZABILIDAD_ACCION_DESCRIPCION_ACCION(
      "eEMGDE21.1.1.Trazabilidad.Accion.DescripcionAccion"),

  METADATO_TRAZABILIDAD_ACCION_FECHA_ACCION("eEMGDE21.1.2.Trazabilidad.Accion.FechaAccion"),

  METADATO_TRAZABILIDAD_ACCION_OBJETO_ACCION("eEMGDE21.1.3.Trazabilidad.Accion.ObjetoAccion"),

  METADATO_TRAZABILIDAD_MOTIVO_REGLADO("eEMGDE21.2.Trazabilidad.MotivoReglado"),

  METADATO_TRAZABILIDAD_USUARIO_ACCION("eEMGDE21.3.Trazabilidad.UsuarioAccion"),

  METADATO_TRAZABILIDAD_DESCRIPCION("eEMGDE21.4.Trazabilidad.Descripcion"),

  METADATO_TRAZABILIDAD_MODIFICACION_METADATOS("eEMGDE21.5.Trazabilidad.ModificacionMetadatos"),

  METADATO_CLASIFICACION_CODIGO_CLASIFICACION("eEMGDE22.1.Clasificacion.CodigoClasificacion"),

  METADATO_CALIFICACION_DENOMINACION_CLASE("eEMGDE22.2.Clasificacion.DenominacionClase"),

  METADATO_CLASIFICACION_TIPO_CLASIFICACION("eEMGDE22.3.Clasificacion.TipoClasificacion"),

  METADATO_VERSION_NTI("eEMGDE23.VersionNTI"),

  METADATO_ORGANO("eEMGDE24.Organo"),

  METADATO_ORIGEN_DOCUMENTO("eEMGDE25.OrigenDocumento"),

  METADATO_IDENTIFICADOR_DOCUMENTO_ORIGEN("eEMGDE26.IdentificadorDocumentoOrigen"),

  METADATO_ESTADO_EXPEDIENTE("eEMGDE27.EstadoExpediente"),

  METADATO_INTERESADO("eEMGDE28.Interesado"),

  METADATO_ASIENTO_REGISTRAL("eEMGDE29.1.AsientoRegistral"),

  METADATO_ASIENTO_REGISTRAL_CODIGO_OFICINA_REGISTRO(
      "eEMGDE29.2.AsientoRegistral.CodigoOficinaRegistro"),

  METADATO_ASIENTO_REGISTRAL_FECHA_ASIENTO_REGISTRAL(
      "eEMGDE29.3.AsientoRegistral.FechaAsientoRegistral"),

  METADATO_ASIENTO_REGISTRAL_NUMERO_ASIENTO_REGISTRAL(
      "eEMGDE29.4.AsientoRegistral.NumeroAsientoRegistral");


  private String value;

  private MetadatosEEMGDE(String value) {
    this.setValue(value);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
