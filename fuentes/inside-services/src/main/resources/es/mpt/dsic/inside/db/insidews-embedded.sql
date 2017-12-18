CREATE TABLE DocumentoInside (
  id INTEGER NOT NULL AUTO_INCREMENT,
  identificador varchar(255) NOT NULL,
  identificador_repositorio varchar(255) DEFAULT NULL,
  version INTEGER NOT NULL DEFAULT '1',
  fechaVersion timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  versionNti varchar(255) DEFAULT NULL,
  fechaCaptura timestamp NULL DEFAULT '0000-00-00 00:00:00',
  origen tinyINTEGER(1) DEFAULT '0',
  nombreFormato varchar(255) DEFAULT NULL,
  tipoMime varchar(255) DEFAULT NULL,
  tipoDocumental varchar(255) DEFAULT NULL,
  estadoElaboracion varchar(255) DEFAULT NULL,
  identificadorDocumentoOrigen varchar(255) DEFAULT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  id_inside_aplicacion INTEGER DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY identificador_version (identificador,version),
  KEY FK_DocumentoInside_Aplicacion (id_inside_aplicacion),
  CONSTRAINTEGER FK_DocumentoInside_Aplicacion FOREIGN KEY (id_inside_aplicacion) REFERENCES InsideAplicacion (id)
) ;


CREATE TABLE DocumentoInsideFirmas (
  id INTEGER NOT NULL AUTO_INCREMENT,
  id_documento INTEGER NOT NULL,
  id_firma INTEGER NOT NULL,
  PRIMARY KEY (id),
  KEY FK_DocumentoInsideFirmas_Documento (id_documento),
  KEY FK_DocumentoInsideFirmas_Firma (id_firma),
  CONSTRAINTEGER FK_DocumentoInsideFirmas_Documento FOREIGN KEY (id_documento) REFERENCES DocumentoInside (id),
  CONSTRAINTEGER FK_DocumentoInsideFirmas_Firma FOREIGN KEY (id_firma) REFERENCES FirmaInside (id)
);

CREATE TABLE DocumentoInsideMetadatosAdicionales (
  id INTEGER NOT NULL AUTO_INCREMENT,
  id_documento INTEGER DEFAULT NULL,
  nombre varchar(255) DEFAULT NULL,
  valor longtext,
  PRIMARY KEY (id),
  KEY FK_DocumentoInsideMetadatosAdicionales_Documento (id_documento),
  CONSTRAINTEGER FK_DocumentoInsideMetadatosAdicionales_Documento FOREIGN KEY (id_documento) REFERENCES DocumentoInside (id)
);

CREATE TABLE DocumentoInsideOrgano (
  id INTEGER NOT NULL AUTO_INCREMENT,
  id_documento INTEGER NOT NULL,
  organo varchar(255) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_DocumentoInsideOrgano_Documento (id_documento),
  CONSTRAINTEGER FK_DocumentoInsideOrgano_Documento FOREIGN KEY (id_documento) REFERENCES DocumentoInside (id)
);

CREATE TABLE ExpedienteInside (
  id INTEGER NOT NULL AUTO_INCREMENT,
  identificador varchar(255) NOT NULL,
  identificador_repositorio varchar(255) DEFAULT NULL,
  version INTEGER NOT NULL DEFAULT '1',
  fechaVersion timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  versionNti varchar(255) DEFAULT NULL,
  fechaAperturaExpediente timestamp NULL DEFAULT '0000-00-00 00:00:00',
  clasificacion varchar(255) DEFAULT NULL,
  estado varchar(255) DEFAULT NULL,
  id_indice INTEGER NOT NULL,
  created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  id_inside_aplicacion INTEGER DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY identificador_version (identificador,version),
  KEY FK_ExpedienteInside_Indice (id_indice),
  KEY FK_ExpedienteInside_Aplicacion (id_inside_aplicacion),
  CONSTRAINTEGER FK_ExpedienteInside_Aplicacion FOREIGN KEY (id_inside_aplicacion) REFERENCES InsideAplicacion (id),
  CONSTRAINTEGER FK_ExpedienteInside_Indice FOREIGN KEY (id_indice) REFERENCES ExpedienteInsideIndice (id)
);

CREATE TABLE ExpedienteInsideIndice (
  id INTEGER NOT NULL AUTO_INCREMENT,
  fecha_indice_electronico timestamp NULL DEFAULT NULL,
  id_expediente_indizado INTEGER DEFAULT NULL,
  id_carpeta_indizada INTEGER DEFAULT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY id (id),
  KEY FK_ExpedienteInsideIndice_ExpedienteIndizado (id_expediente_indizado),
  KEY FK_ExpedienteInsideIndice_CarpetaIndizada (id_carpeta_indizada),
  CONSTRAINTEGER FK_ExpedienteInsideIndice_CarpetaIndizada FOREIGN KEY (id_carpeta_indizada) REFERENCES ExpedienteInsideIndiceCarpetaIndizada (id),
  CONSTRAINTEGER FK_ExpedienteInsideIndice_ExpedienteIndizado FOREIGN KEY (id_expediente_indizado) REFERENCES ExpedienteInsideIndice (id)
);
CREATE TABLE ExpedienteInsideIndiceCarpetaIndizada (
  id INTEGER NOT NULL AUTO_INCREMENT,
  identificador_carpeta varchar(255) DEFAULT NULL,
  id_expediente_indizado INTEGER DEFAULT NULL,
  id_carpeta_indizada INTEGER DEFAULT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY FK_ExpedienteInsideIndiceCarpetaIndizada_Indice (id_expediente_indizado),
  KEY FK_ExpedienteInsideIndiceCarpetaIndizada_Carpeta (id_carpeta_indizada),
  CONSTRAINTEGER FK_ExpedienteInsideIndiceCarpetaIndizada_Carpeta FOREIGN KEY (id_carpeta_indizada) REFERENCES ExpedienteInsideIndiceCarpetaIndizada (id),
  CONSTRAINTEGER FK_ExpedienteInsideIndiceCarpetaIndizada_Indice FOREIGN KEY (id_expediente_indizado) REFERENCES ExpedienteInsideIndice (id)
);

CREATE TABLE ExpedienteInsideIndiceDocumentoIndizado (
  id INTEGER NOT NULL AUTO_INCREMENT,
  valorHuella varchar(255) DEFAULT NULL,
  funcionResumen varchar(255) DEFAULT NULL,
  fechaIncorporacionExpediente timestamp NULL DEFAULT NULL,
  ordenDocumentoExpediente INTEGER DEFAULT NULL,
  identificadorDocumento varchar(255) DEFAULT NULL,
  id_expediente_indizado INTEGER DEFAULT NULL,
  id_carpeta_indizada INTEGER DEFAULT NULL,
  created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  KEY FK_ExpedienteInsideIndiceDocumentoIndizado_Expediente (id_expediente_indizado),
  KEY FK_ExpedienteInsideIndiceDocumentoIndizado_Carpeta (id_carpeta_indizada),
  CONSTRAINTEGER FK_ExpedienteInsideIndiceDocumentoIndizado_Carpeta FOREIGN KEY (id_carpeta_indizada) REFERENCES ExpedienteInsideIndiceCarpetaIndizada (id),
  CONSTRAINTEGER FK_ExpedienteInsideIndiceDocumentoIndizado_Indice FOREIGN KEY (id_expediente_indizado) REFERENCES ExpedienteInsideIndice (id)
);
CREATE TABLE ExpedienteInsideIndiceFirmas (
  id INTEGER NOT NULL AUTO_INCREMENT,
  id_expediente INTEGER NOT NULL,
  id_firmaInside INTEGER NOT NULL,
  PRIMARY KEY (id),
  KEY FK_ExpedienteInsideIndiceFirmas_Firma (id_firmaInside),
  KEY FK_ExpedienteInsideIndiceFirmas_Expediente (id_expediente),
  CONSTRAINTEGER FK_ExpedienteInsideIndiceFirmas_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id),
  CONSTRAINTEGER FK_ExpedienteInsideIndiceFirmas_Firma FOREIGN KEY (id_firmaInside) REFERENCES FirmaInside (id)
);

CREATE TABLE ExpedienteInsideINTEGEReresado (
  id INTEGER NOT NULL AUTO_INCREMENT,
  id_expediente INTEGER NOT NULL,
  INTEGEReresado varchar(255) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_ExpedienteInsideINTEGEReresado_Expediente (id_expediente),
  CONSTRAINTEGER FK_ExpedienteInsideINTEGEReresado_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id)
);

CREATE TABLE ExpedienteInsideMetadatosAdicionales (
  id INTEGER NOT NULL AUTO_INCREMENT,
  id_expediente INTEGER DEFAULT NULL,
  nombre varchar(255) DEFAULT NULL,
  valor longtext,
  PRIMARY KEY (id),
  KEY FK_ExpedienteInsideMetadatosAdicionales_Expediente (id_expediente),
  CONSTRAINTEGER FK_ExpedienteInsideMetadatosAdicionales_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id)
);

CREATE TABLE ExpedienteInsideOrgano (
  id INTEGER NOT NULL AUTO_INCREMENT,
  id_expediente INTEGER NOT NULL,
  organo varchar(255) NOT NULL,
  PRIMARY KEY (id),
  KEY FK_ExpedienteInsideOrgano_Expediente (id_expediente),
  CONSTRAINTEGER FK_ExpedienteInsideOrgano_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id)
);

CREATE TABLE FirmaInside (
  id INTEGER NOT NULL AUTO_INCREMENT,
  tipoFirma varchar(255) NOT NULL,
  identificadorRepositorio varchar(255) DEFAULT NULL,
  valorCSV varchar(255) DEFAULT NULL,
  regulacionCSV varchar(255) DEFAULT NULL,
  tipoMime varchar(255) DEFAULT NULL,
  referencia varchar(255) DEFAULT NULL,
  signature tinyINTEGER(1) DEFAULT '0',
  timestamp timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE TABLE InsideAplicacion (
  id INTEGER NOT NULL AUTO_INCREMENT,
  nombre varchar(255) NOT NULL,
  passwordHash varchar(255) NOT NULL,
  PRIMARY KEY (id)
) ;
