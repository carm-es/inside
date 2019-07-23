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

  METADATO_FECHAS_FECHAINICIO("eEMGDE4.1.Fechas.FechaInicio"),

  METADATO_IDENTIFICADOR_SECUENCIA_IDENTIFICADOR("eEMGDE2.1.Identificador.SecuenciaIdentificador"),

  METADATO_FIRMA_FORMATOFIRMA_VALORCSV("eEMGDE17.2.Firma.FormatoFirma.ValorCSV"),

  METADATO_DEFINICION_GENERACION_CSV("eEMGDE17.4.DefinicionGeneracionCSV"),

  METADATO_ESTADO_ELABORACION("eEMGDE20.EstadoElaboracion"),

  METADATO_CLASIFICACION_CODIGO_CLASIFICACION("eEMGDE22.1.Clasificacion.CodigoClasificacion"),

  METADATO_FECHAS_FECHA_FIN("eEMGDE4.2.Fechas.FechaFin"),

  METADATO_SEGURIDAD_SENSIBILIDAD_DATOS_CARACTER_PERSONAL(
      "eEMGDE8.4.Seguridad.SensibilidadDatosCaracterPersonal"),

  METADATO_SEGURIDAD_NIVEL_CONFIDENCIALIDAD_INFORMACION(
      "eEMGDE8.6.Seguridad.NivelConfidencialidadInformacion"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_TIPO_ACCESO(
      "eEMGDE9.1.DerechosAccesoUsoReutilizacion.TipoAcceso"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_CODIGO_CAUSA_LIMITACION(
      "eEMGDE9.1.1.DerechosAccesoUsoReutilizacion.CodigoCausaLimitacion"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_CAUSA_LEGAL_LIMITACION(
      "eEMGDE9.1.2.DerechosAccesoUsoReutilizacion.CausaLegalLimitacion"),

  METADATO_DERECHOS_ACCESO_USO_REUTILIZACION_CONDICIONES_ACCESO_REUTILIZACION(
      "eEMGDE9.2.DerechosAccesoUsoReutilizacion.CondicionesAccesoUsoReutilizacion"),

  METADATO_CALIFICACION_VALORACION_TIPO_VALOR("eEMGDE13.1.1.1.Calificacion.Valoracion.TipoValor"),

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

  METADATO_CALIFICACION_DENOMINACION_CLASE("eEMGDE22.2.Clasificacion.DenominacionClase"),

  METADATO_CLASIFICACION_TIPO_CLASIFICACION("eEMGDE22.3.Clasificacion.TipoClasificacion"),

  METADATO_NOMBRE_NOMBRE_NATURAL("eEMGDE3.1.Nombre.NombreNatural"),

  METADATO_NOMBRE_NOMBRE_FICHERO("eEMGDE3.3.Nombre.NombreFichero"),

  METADATO_DESCRIPCION("eEMGDE5.Descripcion"),

  METADATO_PUNTOS_ACCESO_TERMINO_PUNTO_ACCESO("eEMGDE12.1.PuntosAcceso.TerminoPuntoAcceso"),

  METADATO_PUNTOS_ACCESO_ID_PUNTO_ACCESO("eEMGDE12.2.PuntosAcceso.IdPuntoAcceso"),

  METADATO_PUNTOS_ACCESO_ESQUEMA_PUNTO_ACCESO("eEMGDE12.3.PuntosAcceso.EsquemaPuntoAcceso"),

  METADATO_CARACTERISTICAS_TECNICAS_VERSION_FORMATO(
      "eEMGDE14.3.CaracteristicasTecnicas.VersionFormato"),

  METADATO_CARACTERISTICAS_TECNICAS_RESOLUCION("eEMGDE14.7.CaracteristicasTecnicas.Resolucion"),

  METADATO_CARACTERISTICAS_TECNICAS_TAMANO_TAMANO_LOGICO(
      "eEMGDE14.8.2.CaracteristicasTecnicas.Tamano.TamanoLogico"),

  METADATO_CARACTERISTICAS_TECNICAS_TAMANO_UNIDADES(
      "eEMGDE14.8.4.CaracteristicasTecnicas.Tamano.Unidades"),

  METADATO_CARACTERISTICAS_TECNICAS_NOMBRE_FORMATO(
      "eEMGDE14.2.CaracteristicasTecnicas.NombreFormato"),

  METADATO_CARACTERISTICAS_TECNICAS_EXTENSION_FICHERO(
      "eEMGDE14.2.2.CaracteristicasTecnicas.Formato.ExtensionFichero"),

  METADATO_CARACTERISTICAS_TECNICAS_PROFUNDIDAD_COLOR(
      "eEMGDE14.9.CaracteristicasTecnicas.ProfundidadColor"),

  METADATO_UBICACION_SOPORTE("eEMGDE15.1.Ubicacion.Soporte"),

  METADATO_UBICACION_LOCALIZACION("eEMGDE15.2.Ubicacion.Localizacion"),

  METADATO_VERIFICACION_INTEGRIDAD_ALGORITMO("eEMGDE16.1.VerificacionIntegridad.Algoritmo"),

  METADATO_VERIFICACION_INTEGRIDAD_VALOR("eEMGDE16.2.VerificacionIntegridad.Valor"),

  METADATO_FIRMA_TIPOFIRMA("eEMGDE17.1.Firma.TipoFirma"),

  METADATO_FIRMA_TIPO_FIRMA_PERFIL_FIRMA("eEMGDE17.1.1.Firma.TipoFirma.PerfilFirma"),

  METADATO_FIRMA_FIRMANTE_NOMBRE_APELLIDOS("eEMGDE17.5.1.Firma.Firmante.NombreApellidos"),

  METADATO_FIRMA_FIRMANTE_NUMERO_IDENTIFICACION_FIRMANTES(
      "eEMGDE17.5.2.Firma.Firmante.NumeroIdentificacionFirmantes"),

  METADATO_FIRMA_FIRMANTE_EN_CALIDAD_DE("eEMGDE17.5.3.Firma.Firmante.EnCalidadDe"),

  METADATO_FIRMA_NIVEL_FIRMA("eEMGDE17.5.4.Firma.NivelFirma"),

  METADATO_FIRMA_INFORMACION_ADICIONAL("eEMGDE17.5.5.Firma.InformacionAdicional"),

  METADATO_TRAZABILIDAD_ACCION("eEMGDE21.1.Trazabilidad.Accion"),

  METADATO_TRAZABILIDAD_ACCION_FECHA_ACCION("eEMGDE21.1.1.Trazabilidad.Accion.FechaAccion"),

  METADATO_TRAZABILIDAD_ACCION_ENTIDAD_ACCION("eEMGDE21.1.2.Trazabilidad.Accion.EntidadAccion"),

  METADATO_TRAZABILIDAD_MOTIVO_REGLADO("eEMGDE21.2.Trazabilidad.MotivoReglado"),

  METADATO_TRAZABILIDAD_USUARIO_ACCION("eEMGDE21.3.Trazabilidad.UsuarioAccion"),

  METADATO_TRAZABILIDAD_DESCRIPCION("eEMGDE21.4.Trazabilidad.Descripcion"),

  METADATO_TRAZABILIDAD_MODIFICACION_METADATOS("eEMGDE21.5.Trazabilidad.ModificacionMetadatos"),

  METADATO_TRAZABILIDAD_HISTORIA_CAMBIO_NOMBRE_ELEMENTO(
      "eEMGDE21.6.1.Trazabilidad.HistoriaCambio.NombreElemento"),

  METADATO_TRAZABILIDAD_HISTORIA_CAMBIO_VALOR_ANTERIOR(
      "eEMGDE21.6.2.Trazabilidad.HistoriaCambio.ValorAnterior"),

  METADATO_ORIGEN_CIUDADANO_ADMINISTRACION("OrigenCiudadanoAdministracion"),

  METADATO_IDENTIFICADOR_DOCUMENTO_ORIGEN("IdentificadorDocumentoOrigen"),

  METADATO_TIPO_DOCUMENTAL("TipoDocumental"),

  METADATO_IDIOMA("eEMGDE11.Idioma"),

  METADATO_ASIENTO_REGISTRAL("eEMGDE.AsientoRegistral"),

  METADATO_TIPO_ASIENTO_REGISTRAL("eEMGDE.AsientoRegistral.TipoAsientoRegistral"),

  METADATO_CODIGO_OFICINA_REGISTRO("eEMGDE.AsientoRegistral.CodigoOficinaRegistro"),

  METADATO_FECHA_ASIENTO_REGISTRAL("eEMGDE.AsientoRegistral.FechaAsientoRegistral"),

  METADATO_NUMERO_ASIENTO_REGISTRAL("eEMGDE.AsientoRegistral.NumeroAsientoRegistral"),

  METADATO_VERSION_NTI("VersionNTI"),

  METADATO_ESTADO("Estado"),

  METADATO_INTERESADO("Interesado"),

  METADATO_CLASIFICACION("Clasificacion"),

  METADATO_ORGANO("Organo"),

  METADATO_CATEGORIA("eEMGDE1.Categoria");

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
