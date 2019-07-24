DROP TABLE IF EXISTS AuditoriaAccesoDocumento;
CREATE TABLE AuditoriaAccesoDocumento (id int NOT NULL, DIR3_Peticionario varchar(256) NOT NULL, Usuario_Peticionario varchar(256), Id_Peticion varchar(256) NOT NULL, Id_Documento varchar(256) NOT NULL, Fecha timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, idDir3 int, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS AuditoriaAccesoDocumentoInforme;
CREATE TABLE AuditoriaAccesoDocumentoInforme (id varchar(255) NOT NULL, csv varchar(255) NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS AuditoriaFirmaServidor;
CREATE TABLE AuditoriaFirmaServidor (id int NOT NULL, elemento varchar(3) NOT NULL, identificador varchar(255) NOT NULL, usuario varchar(255) NOT NULL, created_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS AuditoriaToken;
CREATE TABLE AuditoriaToken (id int NOT NULL, id_token int NOT NULL, nifUsuarioUso varchar(255) NOT NULL, acta blob NOT NULL, justificante mediumblob NOT NULL, fechaUso timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, dir3UsuarioUso varchar(256), nombreDir3UsuarioUso varchar(256), idRolUso int, descripcionRolUso varchar(256), PRIMARY KEY (id), INDEX fk_AuditoriaToken_TokenInside_idx (id_token)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS CredencialesEeutil;
CREATE TABLE CredencialesEeutil (id int DEFAULT '0' NOT NULL, idAplicacionInterna int NOT NULL, idAplicacion varchar(255) NOT NULL, password varchar(255) NOT NULL, PRIMARY KEY (id), CONSTRAINT idAplicacionInterna UNIQUE (idAplicacionInterna)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS DocumentoInside;
CREATE TABLE DocumentoInside (id int DEFAULT '0' NOT NULL, identificador varchar(255) NOT NULL, identificador_repositorio varchar(255), version int, fechaVersion timestamp DEFAULT '0000-00-00 00:00:00', versionNti varchar(255), fechaCaptura timestamp DEFAULT '0000-00-00 00:00:00' NULL, origen tinyint(1) DEFAULT '0', nombreFormato varchar(255), tipoMime varchar(255), tipoDocumental varchar(255), estadoElaboracion varchar(255), identificadorDocumentoOrigen varchar(255), identificadorEnDocumento varchar(255), referencia varchar(255), created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL, fechaBaja timestamp NULL, PRIMARY KEY (id), CONSTRAINT identificador_version UNIQUE (identificador, version)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS DocumentoInsideFirmas;
CREATE TABLE DocumentoInsideFirmas (id int DEFAULT '0' NOT NULL, id_documento int NOT NULL, id_firma int NOT NULL, PRIMARY KEY (id), INDEX FK_DocumentoInsideFirmas_Documento (id_documento), INDEX FK_DocumentoInsideFirmas_Firma (id_firma)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS DocumentoInsideMetAdic;
CREATE TABLE DocumentoInsideMetAdic (id int DEFAULT '0' NOT NULL, id_documento int, nombre varchar(255), valor longtext, PRIMARY KEY (id), INDEX FK_DocumentoInsideMetadatosAdicionales_Documento (id_documento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS DocumentoInsideOrgano;
CREATE TABLE DocumentoInsideOrgano (id int DEFAULT '0' NOT NULL, id_documento int NOT NULL, organo varchar(255) NOT NULL, PRIMARY KEY (id), INDEX FK_DocumentoInsideOrgano_Documento (id_documento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS DocumentoUnidad;
CREATE TABLE DocumentoUnidad (id int NOT NULL, identificador varchar(255) NOT NULL, id_unidad int NOT NULL, fechaVersion timestamp NULL, id_procedimiento int, PRIMARY KEY (id), INDEX fk_DocumentoUnidad_DocumentoInside_idx (identificador), INDEX fk_DocumentoUnidad_UnidadOrganica_idx (id_unidad), INDEX fk_DocumentoUnidad_NumeroProcedimiento_idx (id_procedimiento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS EstructuraCarpetaInside;
CREATE TABLE EstructuraCarpetaInside (id int NOT NULL, identificador_estructura varchar(255) NOT NULL, id_unidad int, id_procedimiento int, id_carpeta_estructura int, PRIMARY KEY (id), CONSTRAINT id UNIQUE (id), INDEX FK_ExpInsideIndice_CarInd (id_carpeta_estructura), INDEX fk_EstructuraCarpetaInside_UnidadOrganica_idx (id_unidad), INDEX fk_EstructuraCarpetaInside_NumeroProcedimiento_idx (id_procedimiento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInside;
CREATE TABLE ExpedienteInside (id int DEFAULT '0' NOT NULL, identificador varchar(255) NOT NULL, identificador_repositorio varchar(255), version int DEFAULT '1' NOT NULL, fechaVersion timestamp DEFAULT '0000-00-00 00:00:00', versionNti varchar(255), fechaAperturaExpediente timestamp DEFAULT '0000-00-00 00:00:00' NULL, clasificacion varchar(255), estado varchar(255), id_indice int NOT NULL, created_at timestamp DEFAULT CURRENT_TIMESTAMP NULL, fechaBaja timestamp NULL, PRIMARY KEY (id), INDEX FK_ExpedienteInside_Indice (id_indice)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInsideIndice;
CREATE TABLE ExpedienteInsideIndice (id int DEFAULT '0' NOT NULL, identificador_expediente varchar(255), id_expediente_vinculado int, fecha_indice_electronico timestamp NULL, id_expediente_indizado int, id_carpeta_indizada int, orden int(3), created_at timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id), CONSTRAINT id UNIQUE (id), INDEX FK_ExpedienteInsideIndice_ExpedienteIndizado (id_expediente_indizado), INDEX FK_ExpedienteInsideIndice_CarpetaIndizada (id_carpeta_indizada), INDEX FK_ExpedienteInsideIndice_ExpedienteVinculado (id_expediente_vinculado)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInsideIndiceCarInd;
CREATE TABLE ExpedienteInsideIndiceCarInd (id int DEFAULT '0' NOT NULL, identificador_carpeta varchar(255) NOT NULL, id_expediente_indizado int, id_carpeta_indizada int, orden int(3) NOT NULL, created_at timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id), INDEX FK_ExpedienteInsideIndiceCarpetaIndizada_Carpeta (id_carpeta_indizada), INDEX FK_ExpedienteInsideIndiceCarpetaIndizada_ExpedienteIndizado (id_expediente_indizado)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInsideIndiceDocInd;
CREATE TABLE ExpedienteInsideIndiceDocInd (id int DEFAULT '0' NOT NULL, valorHuella varchar(255), funcionResumen varchar(255), fechaIncorporacionExpediente timestamp NULL, identificadorDocumento varchar(255), id_expediente_indizado int, id_carpeta_indizada int, orden_documento_expediente int(3) DEFAULT '-1', orden int(3) NOT NULL, created_at timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id), INDEX FK_ExpedienteInsideIndiceDocumentoIndizado_Expediente (id_expediente_indizado), INDEX FK_ExpedienteInsideIndiceDocumentoIndizado_CarpetaIndizada (id_carpeta_indizada)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInsideIndiceFirmas;
CREATE TABLE ExpedienteInsideIndiceFirmas (id int DEFAULT '0' NOT NULL, id_expediente int NOT NULL, id_firmaInside int NOT NULL, PRIMARY KEY (id), INDEX FK_ExpedienteInsideIndiceFirmas_Firma (id_firmaInside), INDEX FK_ExpedienteInsideIndiceFirmas_Expediente (id_expediente)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInsideInteresado;
CREATE TABLE ExpedienteInsideInteresado (id int DEFAULT '0' NOT NULL, id_expediente int NOT NULL, interesado varchar(255) NOT NULL, PRIMARY KEY (id), INDEX FK_ExpedienteInsideInteresado_Expediente (id_expediente)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInsideMetAdic;
CREATE TABLE ExpedienteInsideMetAdic (id int DEFAULT '0' NOT NULL, id_expediente int, nombre varchar(255), valor longtext, PRIMARY KEY (id), INDEX FK_ExpedienteInsideMetadatosAdicionales_Expediente (id_expediente)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteInsideOrgano;
CREATE TABLE ExpedienteInsideOrgano (id int DEFAULT '0' NOT NULL, id_expediente int NOT NULL, organo varchar(255) NOT NULL, PRIMARY KEY (id), INDEX FK_ExpedienteInsideOrgano_Expediente (id_expediente)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteToken;
CREATE TABLE ExpedienteToken (id int NOT NULL, id_usuario int NOT NULL, identificador varchar(255) NOT NULL, csv varchar(255) NOT NULL, uuid varchar(255) NOT NULL, fechaCaducidad date, dir3 varchar(255) NOT NULL, nifs varchar(255) NOT NULL, asunto varchar(255), esDocumento tinyint(1) DEFAULT '0', fechaCreacion timestamp NULL, versionExpediente int, PRIMARY KEY (id), INDEX fk_ExpedienteToken_UsuarioInside_idx (id_usuario), INDEX fk_ExpedienteToken_ExpedienteInside_idx (identificador)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS ExpedienteUnidad;
CREATE TABLE ExpedienteUnidad (id int NOT NULL, identificador varchar(255) NOT NULL, id_unidad int NOT NULL, fechaVersion timestamp NULL, estadoExpediente varchar(255) NOT NULL, id_procedimiento int, PRIMARY KEY (id), INDEX fk_ExpedienteUnidad_ExpedienteInside_idx (identificador), INDEX fk_ExpedienteUnidad_UnidadOrganica_idx (id_unidad), INDEX fk_ExpedienteUnidad_NumeroProcedimiento_idx (id_procedimiento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS FirmaInside;
CREATE TABLE FirmaInside (id int DEFAULT '0' NOT NULL, tipoFirma varchar(255) NOT NULL, identificadorRepositorio varchar(255), valorCSV varchar(255), regulacionCSV varchar(255), tipoMime varchar(255), referencia varchar(255), signature tinyint(1) DEFAULT '0', timestamp timestamp DEFAULT CURRENT_TIMESTAMP NULL, identificadorEnDocumento varchar(255), orden int(10), contenido mediumblob, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS GeneradorClaves;
CREATE TABLE GeneradorClaves (GenName varchar(255) NOT NULL, GenValue int NOT NULL, PRIMARY KEY (GenName)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS GeneradorIdInside;
CREATE TABLE GeneradorIdInside (id int NOT NULL, elemento varchar(3) NOT NULL, id_unidad int NOT NULL, numeroId int NOT NULL, PRIMARY KEY (id), INDEX fk_GeneradorIdInside_UnidadOrganica_idx (id_unidad)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS InsideRol;
CREATE TABLE InsideRol (id int NOT NULL, descripcion varchar(256) NOT NULL, descripcionLarga varchar(256) NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS InsideWsAplicacion;
CREATE TABLE InsideWsAplicacion (id int DEFAULT '0' NOT NULL, nombre varchar(255) NOT NULL, passwordHash varchar(255) NOT NULL, activo tinyint(1) DEFAULT '1' NOT NULL, altaExpediente tinyint(1) DEFAULT '1' NOT NULL, modificarExpediente tinyint(1) DEFAULT '1' NOT NULL, leerExpediente tinyint(1) DEFAULT '1' NOT NULL, altaDocumento tinyint(1) DEFAULT '1' NOT NULL, modificarDocumento tinyint(1) DEFAULT '1' NOT NULL, leerDocumento tinyint(1) DEFAULT '1' NOT NULL, fechaCreacion timestamp DEFAULT CURRENT_TIMESTAMP, administrarPermisos tinyint(1) DEFAULT '0' NOT NULL, email varchar(255), telefono varchar(255), responsable varchar(255), serialNumberCertificado varchar(255), PRIMARY KEY (id), CONSTRAINT nombre UNIQUE (nombre)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS InsideWsAudit;
CREATE TABLE InsideWsAudit (id int DEFAULT '0' NOT NULL, id_aplicacion int NOT NULL, id_expediente int, id_documento int, timestamp timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS LogAccesoGenerador;
CREATE TABLE LogAccesoGenerador (id int DEFAULT '0' NOT NULL, nif varchar(10) NOT NULL, timestamp timestamp DEFAULT CURRENT_TIMESTAMP, ip varchar(255), server varchar(255), user_agent varchar(255)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS LogAccesoVisor;
CREATE TABLE LogAccesoVisor (id int NOT NULL AUTO_INCREMENT, nif varchar(10) NOT NULL, timestamp timestamp DEFAULT CURRENT_TIMESTAMP, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS MensajeUsuario;
CREATE TABLE MensajeUsuario (id int NOT NULL, texto varchar(1000) NOT NULL, activo tinyint(1) DEFAULT '1' NOT NULL, fechaCreacion timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, fechaModificacion timestamp NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS NumeroProcedimiento;
CREATE TABLE NumeroProcedimiento (id int NOT NULL, numeroProcedimiento varchar(255) NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
CREATE TABLE QRTZ_BLOB_TRIGGERS (TRIGGER_NAME varchar(80) NOT NULL, TRIGGER_GROUP varchar(80) NOT NULL, BLOB_DATA blob, PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_CALENDARS;
CREATE TABLE QRTZ_CALENDARS (CALENDAR_NAME varchar(80) NOT NULL, CALENDAR blob NOT NULL, PRIMARY KEY (CALENDAR_NAME)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
CREATE TABLE QRTZ_CRON_TRIGGERS (TRIGGER_NAME varchar(80) NOT NULL, TRIGGER_GROUP varchar(80) NOT NULL, CRON_EXPRESSION varchar(80) NOT NULL, TIME_ZONE_ID varchar(80), PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
CREATE TABLE QRTZ_FIRED_TRIGGERS (ENTRY_ID varchar(95) NOT NULL, TRIGGER_NAME varchar(80) NOT NULL, TRIGGER_GROUP varchar(80) NOT NULL, IS_VOLATILE varchar(1) NOT NULL, INSTANCE_NAME varchar(80) NOT NULL, FIRED_TIME bigint(13) NOT NULL, STATE varchar(16) NOT NULL, JOB_NAME varchar(80), JOB_GROUP varchar(80), IS_STATEFUL varchar(1), REQUESTS_RECOVERY varchar(1), PRIMARY KEY (ENTRY_ID)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
CREATE TABLE QRTZ_JOB_DETAILS (JOB_NAME varchar(80) NOT NULL, JOB_GROUP varchar(80) NOT NULL, DESCRIPTION varchar(120), JOB_CLASS_NAME varchar(128) NOT NULL, IS_DURABLE varchar(1) NOT NULL, IS_VOLATILE varchar(1) NOT NULL, IS_STATEFUL varchar(1) NOT NULL, REQUESTS_RECOVERY varchar(1) NOT NULL, JOB_DATA blob, PRIMARY KEY (JOB_NAME, JOB_GROUP)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_JOB_LISTENERS;
CREATE TABLE QRTZ_JOB_LISTENERS (JOB_NAME varchar(80) NOT NULL, JOB_GROUP varchar(80) NOT NULL, JOB_LISTENER varchar(80) NOT NULL, PRIMARY KEY (JOB_NAME, JOB_GROUP, JOB_LISTENER)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_LOCKS;
CREATE TABLE QRTZ_LOCKS (LOCK_NAME varchar(40) NOT NULL, PRIMARY KEY (LOCK_NAME)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (TRIGGER_GROUP varchar(80) NOT NULL, PRIMARY KEY (TRIGGER_GROUP)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
CREATE TABLE QRTZ_SCHEDULER_STATE (INSTANCE_NAME varchar(80) NOT NULL, LAST_CHECKIN_TIME bigint(13) NOT NULL, CHECKIN_INTERVAL bigint(13) NOT NULL, RECOVERER varchar(80), PRIMARY KEY (INSTANCE_NAME)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
CREATE TABLE QRTZ_SIMPLE_TRIGGERS (TRIGGER_NAME varchar(80) NOT NULL, TRIGGER_GROUP varchar(80) NOT NULL, REPEAT_COUNT bigint(7) NOT NULL, REPEAT_INTERVAL bigint(12) NOT NULL, TIMES_TRIGGERED bigint(7) NOT NULL, PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
CREATE TABLE QRTZ_TRIGGERS (TRIGGER_NAME varchar(80) NOT NULL, TRIGGER_GROUP varchar(80) NOT NULL, JOB_NAME varchar(80) NOT NULL, JOB_GROUP varchar(80) NOT NULL, IS_VOLATILE varchar(1) NOT NULL, DESCRIPTION varchar(120), NEXT_FIRE_TIME bigint(13), PREV_FIRE_TIME bigint(13), TRIGGER_STATE varchar(16) NOT NULL, TRIGGER_TYPE varchar(8) NOT NULL, START_TIME bigint(13) NOT NULL, END_TIME bigint(13), CALENDAR_NAME varchar(80), MISFIRE_INSTR smallint(2), JOB_DATA blob, PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP), INDEX JOB_NAME (JOB_NAME, JOB_GROUP)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS QRTZ_TRIGGER_LISTENERS;
CREATE TABLE QRTZ_TRIGGER_LISTENERS (TRIGGER_NAME varchar(80) NOT NULL, TRIGGER_GROUP varchar(80) NOT NULL, TRIGGER_LISTENER varchar(80) NOT NULL, PRIMARY KEY (TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_LISTENER)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS UnidadAplicacionEeutil;
CREATE TABLE UnidadAplicacionEeutil (id int DEFAULT '0' NOT NULL, id_unidad int NOT NULL, id_aplicacion varchar(255) NOT NULL, password varchar(255) NOT NULL, PRIMARY KEY (id)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS UnidadOrganica;
CREATE TABLE UnidadOrganica (id int NOT NULL, Codigo_Unidad_Organica varchar(10) NOT NULL, Nombre_Unidad_Organica varchar(255) NOT NULL, Nivel_Administracion tinyint NOT NULL, Entidad_Derecho_Publico varchar(1) NOT NULL, Codigo_Externo varchar(255), Codigo_Unidad_Superior varchar(10) NOT NULL, Nombre_Unidad_Superior varchar(255) NOT NULL, Codigo_Unidad_Raiz varchar(10) NOT NULL, Nombre_Unidad_Raiz varchar(255) NOT NULL, Codigo_Raiz_Derecho_Publico varchar(10), Nombre_Raiz_Derecho_Publico varchar(255), Nivel_Jerarquico tinyint NOT NULL, Estado varchar(1) NOT NULL, Fecha_Alta datetime, Fecha_Baja datetime, Fecha_Anulacion datetime, Fecha_Extincion datetime, created_at datetime NOT NULL, PRIMARY KEY (id), CONSTRAINT unidad_unica_idx UNIQUE (Codigo_Unidad_Organica)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS UnidadUsuario;
CREATE TABLE UnidadUsuario (id int NOT NULL, id_unidad int NOT NULL, id_usuario int NOT NULL, activo tinyint(1) DEFAULT '0' NOT NULL, id_procedimiento int, id_rol int DEFAULT '0' NOT NULL, PRIMARY KEY (id), INDEX fk_UnidadUsuario_UnidadOrganica_idx (id_unidad), INDEX fk_UnidadUsuario_UsuarioInside_idx (id_usuario), INDEX fk_UnidadUsuario_NumeroProcedimiento_idx (id_procedimiento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS UnidadWsAplicacion;
CREATE TABLE UnidadWsAplicacion (id int NOT NULL, id_unidad int NOT NULL, id_aplicacion int NOT NULL, activo tinyint(1) DEFAULT '0' NOT NULL, id_procedimiento int, PRIMARY KEY (id), INDEX fk_UnidadWsAplicacion_UnidadOrganica_idx (id_unidad), INDEX fk_UnidadWsAplicacion_InsideWsAplicacion_idx (id_aplicacion), INDEX fk_UnidadWsAplicacion_NumeroProcedimiento_idx (id_procedimiento)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS UsuarioInside;
CREATE TABLE UsuarioInside (id int NOT NULL, nif varchar(255) NOT NULL, activo tinyint(1) DEFAULT '1' NOT NULL, fechaCreacion timestamp DEFAULT CURRENT_TIMESTAMP, adminMensaje tinyint(1) DEFAULT '0' NOT NULL, PRIMARY KEY (id), CONSTRAINT nif UNIQUE (nif)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS UsuarioVisor;
CREATE TABLE UsuarioVisor (id int NOT NULL AUTO_INCREMENT, uid varchar(100) NOT NULL COMMENT 'Usuario ldap', nombre varchar(100), apellido1 varchar(100), apellido2 varchar(100), email varchar(255) NOT NULL, telefono varchar(9), activo tinyint DEFAULT '1' NOT NULL COMMENT 'Si el usuario está vigente en la aplicación backend', fecha_creacion timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, fecha_modificacion timestamp DEFAULT '0000-00-00 00:00:00', PRIMARY KEY (id), CONSTRAINT uid UNIQUE (uid)) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE ExpedienteInsideIndiceDocInd ADD CONSTRAINT FK_ExpedienteInsideIndiceDocumentoIndizado_Indice FOREIGN KEY (id_expediente_indizado) REFERENCES ExpedienteInsideIndice (id) ;
ALTER TABLE ExpedienteInsideIndiceDocInd ADD CONSTRAINT FK_ExpedienteInsideIndiceDocumentoIndizado_CarpetaIndizada FOREIGN KEY (id_carpeta_indizada) REFERENCES ExpedienteInsideIndiceCarInd (id);
ALTER TABLE ExpedienteInsideIndiceFirmas ADD CONSTRAINT FK_ExpedienteInsideIndiceFirmas_Firma FOREIGN KEY (id_firmaInside) REFERENCES FirmaInside (id) ;
ALTER TABLE ExpedienteInsideIndiceFirmas ADD CONSTRAINT FK_ExpedienteInsideIndiceFirmas_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id);
ALTER TABLE ExpedienteInsideInteresado ADD CONSTRAINT FK_ExpedienteInsideInteresado_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id);
ALTER TABLE ExpedienteInsideMetAdic ADD CONSTRAINT FK_ExpedienteInsideMetadatosAdicionales_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id);
ALTER TABLE ExpedienteInsideOrgano ADD CONSTRAINT FK_ExpedienteInsideOrgano_Expediente FOREIGN KEY (id_expediente) REFERENCES ExpedienteInside (id);
ALTER TABLE ExpedienteToken ADD CONSTRAINT fk_ExpedienteToken_InsideUsuario FOREIGN KEY (id_usuario) REFERENCES UsuarioInside (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE ExpedienteUnidad ADD CONSTRAINT fk_ExpedienteUnidad_UnidadOrganica FOREIGN KEY (id_unidad) REFERENCES UnidadOrganica (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE ExpedienteUnidad ADD CONSTRAINT fk_ExpedienteUnidad_NumeroProcedimiento FOREIGN KEY (id_procedimiento) REFERENCES NumeroProcedimiento (id);
ALTER TABLE UnidadUsuario ADD CONSTRAINT fk_UnidadUsuario_UsuarioInside FOREIGN KEY (id_usuario) REFERENCES UsuarioInside (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE UnidadUsuario ADD CONSTRAINT fk_UnidadUsuario_NumeroProcedimiento FOREIGN KEY (id_procedimiento) REFERENCES NumeroProcedimiento (id) ;
ALTER TABLE UnidadUsuario ADD CONSTRAINT fk_UnidadUsuario_UnidadOrganica FOREIGN KEY (id_unidad) REFERENCES UnidadOrganica (id);
ALTER TABLE UnidadWsAplicacion ADD CONSTRAINT fk_UnidadWsAplicacion_UnidadOrganica FOREIGN KEY (id_unidad) REFERENCES UnidadOrganica (id) ;
ALTER TABLE UnidadWsAplicacion ADD CONSTRAINT fk_UnidadWsAplicacion_InsideWsAplicacion FOREIGN KEY (id_aplicacion) REFERENCES InsideWsAplicacion (id) ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE UnidadWsAplicacion ADD CONSTRAINT fk_UnidadWsAplicacion_NumeroProcedimiento FOREIGN KEY (id_procedimiento) REFERENCES NumeroProcedimiento (id);

INSERT INTO UnidadOrganica (id, Codigo_Unidad_Organica, Nombre_Unidad_Organica, Nivel_Administracion, Entidad_Derecho_Publico, Codigo_Externo, Codigo_Unidad_Superior, Nombre_Unidad_Superior, Codigo_Unidad_Raiz, Nombre_Unidad_Raiz, Codigo_Raiz_Derecho_Publico, Nombre_Raiz_Derecho_Publico, Nivel_Jerarquico, Estado, Fecha_Alta, Fecha_Baja, Fecha_Anulacion, Fecha_Extincion, created_at) 
VALUES (1, 'E04975701', 'Direccion de Tecnologias de la Informacion y las Comunicaciones', 1, 'N', '04975701', 'E04924801', 'S. de E. de Administraciones Publicas', 'E04921301', 'Ministerio de Hacienda y Administraciones Publicas', null, null, 3, 'E', '2014-09-27 00:00:00', null, null, null, '2014-09-29 13:14:11');
INSERT INTO UsuarioInside (id, nif, activo, fechaCreacion, adminMensaje) VALUES (1, '11111111H', true, '2015-12-11 19:37:14', false);
INSERT INTO UnidadUsuario (id, id_unidad, id_usuario, activo, id_procedimiento, id_rol) VALUES (1, 1, 1, true, null, 1);
INSERT INTO UnidadAplicacionEeutil (id, id_unidad, id_aplicacion, password) VALUES (1, 1, 'UsuarioXXX', 'PasswordXXX');
INSERT INTO CredencialesEeutil (id, idAplicacionInterna, idAplicacion, password) VALUES (1, 1, 'UsuarioXXX', 'PasswordXXX');
INSERT INTO InsideRol (id, descripcion, descripcionLarga) VALUES (0, 'USER_ROLE', 'altaUsuarioWebInside.rol.0.user.role');
INSERT INTO InsideRol (id, descripcion, descripcionLarga) VALUES (1, 'ALTA_USUARIOS_ROLE', 'altaUsuarioWebInside.rol.1.user.role');
INSERT INTO InsideRol (id, descripcion, descripcionLarga) VALUES (2, 'REMISION_JUSTICIA_ROLE', 'altaUsuarioWebInside.rol.2.user.role');
INSERT INTO InsideRol (id, descripcion, descripcionLarga) VALUES (3, 'CONSULTA_ROLE', 'altaUsuarioWebInside.rol.3.user.role');
INSERT INTO InsideRol (id, descripcion, descripcionLarga) VALUES (4, 'REDACTOR_ROLE', 'altaUsuarioWebInside.rol.4.user.role');
INSERT INTO InsideWsAplicacion (id, nombre, passwordHash, activo, altaExpediente, modificarExpediente, leerExpediente, altaDocumento, modificarDocumento, leerDocumento, fechaCreacion, administrarPermisos, email, telefono, responsable, serialNumberCertificado) VALUES (1, 'prueba', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', true, true, true, true, true, true, true, '2013-01-23 15:44:06', true, null, null, null, 'Número de serie en decimal certificado');
INSERT INTO UnidadWsAplicacion (id, id_unidad, id_aplicacion, activo, id_procedimiento) VALUES (1, 1, 1, true, null);

INSERT INTO ExpedienteInsideIndiceCarInd values (1,'EstructuraGenerica',null,null,0,now());
INSERT INTO ExpedienteInsideIndiceCarInd values (2,'CarpetaGenerica',null,1,0,now());
INSERT INTO EstructuraCarpetaInside values (1,'estructuraGenerica',null,null,1);

INSERT INTO QRTZ_LOCKS (LOCK_NAME) VALUES ('CALENDAR_ACCESS');
INSERT INTO QRTZ_LOCKS (LOCK_NAME) VALUES ('JOB_ACCESS');
INSERT INTO QRTZ_LOCKS (LOCK_NAME) VALUES ('MISFIRE_ACCESS');
INSERT INTO QRTZ_LOCKS (LOCK_NAME) VALUES ('STATE_ACCESS');
INSERT INTO QRTZ_LOCKS (LOCK_NAME) VALUES ('TRIGGER_ACCESS');


DROP VIEW IF EXISTS V_DOCUMENTOS_METADATOS_ADICIONALES;
CREATE VIEW V_DOCUMENTOS_METADATOS_ADICIONALES (ID, ID_UNIDAD, IDENTIFICADOR, FECHAVERSION, NOMBRE_VALOR) AS select concat(`DU`.`id_unidad`,`DU`.`identificador`,`DU`.`fechaVersion`) AS `ID`,`DU`.`id_unidad` AS `ID_UNIDAD`,`DU`.`identificador` AS `IDENTIFICADOR`,`DU`.`fechaVersion` AS `FECHAVERSION`,(select group_concat(`DIM`.`nombre`,`DIM`.`valor` separator ',') AS `NOMBREVALOR` from `DocumentoInsideMetAdic` `DIM` where (`DIM`.`id_documento` = `DI`.`id`)) AS `NOMBRE_VALOR` from (`DocumentoUnidad` `DU` left join `DocumentoInside` `DI` on(((`DU`.`identificador` = `DI`.`identificador`) and (`DU`.`fechaVersion` = `DI`.`fechaVersion`))));
DROP VIEW IF EXISTS V_EXPEDIENTES;
CREATE VIEW V_EXPEDIENTES (IDENTIFICADOR, FECHA_ALTA) AS select `ExpedienteInside`.`identificador` AS `IDENTIFICADOR`,min(`ExpedienteInside`.`fechaVersion`) AS `FECHA_ALTA` from `ExpedienteInside` group by `ExpedienteInside`.`identificador`;
DROP VIEW IF EXISTS V_EXPEDIENTES_INSIDE;
CREATE VIEW V_EXPEDIENTES_INSIDE (CODIGO_UNIDAD_ORGANICA, NOMBRE_UNIDAD_ORGANICA, CODIGO_UNIDAD_SUPERIOR, NOMBRE_UNIDAD_SUPERIOR, CODIGO_UNIDAD_RAIZ, NOMBRE_UNIDAD_RAIZ, IDENTIFICADOR, FECHA_ALTA, VERSION) AS select `UO`.`Codigo_Unidad_Organica` AS `CODIGO_UNIDAD_ORGANICA`,`UO`.`Nombre_Unidad_Organica` AS `NOMBRE_UNIDAD_ORGANICA`,`UO`.`Codigo_Unidad_Superior` AS `CODIGO_UNIDAD_SUPERIOR`,`UO`.`Nombre_Unidad_Superior` AS `NOMBRE_UNIDAD_SUPERIOR`,`UO`.`Codigo_Unidad_Raiz` AS `CODIGO_UNIDAD_RAIZ`,`UO`.`Nombre_Unidad_Raiz` AS `NOMBRE_UNIDAD_RAIZ`,`EI`.`identificador` AS `IDENTIFICADOR`,`EI`.`fechaVersion` AS `FECHA_ALTA`,`EI`.`version` AS `VERSION` from ((`ExpedienteInside` `EI` join `ExpedienteUnidad` `EU`) join `UnidadOrganica` `UO`) where ((`EU`.`identificador` = `EI`.`identificador`) and (`UO`.`id` = `EU`.`id_unidad`) and (`EI`.`version` = 1));
DROP VIEW IF EXISTS V_ORGANISMOS_INSIDE_ONLINE;
CREATE VIEW V_ORGANISMOS_INSIDE_ONLINE (CODIGO_UNIDAD_ORGANICA, NOMBRE_UNIDAD_ORGANICA, CODIGO_UNIDAD_SUPERIOR, NOMBRE_UNIDAD_SUPERIOR, CODIGO_UNIDAD_RAIZ, NOMBRE_UNIDAD_RAIZ, FECHA_ALTA) AS select `UO`.`Codigo_Unidad_Organica` AS `CODIGO_UNIDAD_ORGANICA`,`UO`.`Nombre_Unidad_Organica` AS `NOMBRE_UNIDAD_ORGANICA`,`UO`.`Codigo_Unidad_Superior` AS `CODIGO_UNIDAD_SUPERIOR`,`UO`.`Nombre_Unidad_Superior` AS `NOMBRE_UNIDAD_SUPERIOR`,`UO`.`Codigo_Unidad_Raiz` AS `CODIGO_UNIDAD_RAIZ`,`UO`.`Nombre_Unidad_Raiz` AS `NOMBRE_UNIDAD_RAIZ`,min(`U`.`fechaCreacion`) AS `FECHA_ALTA` from ((`UnidadUsuario` `UU` join `UnidadOrganica` `UO`) join `UsuarioInside` `U`) where ((`UU`.`id_unidad` = `UO`.`id`) and (`UU`.`id_usuario` = `U`.`id`) and (`UU`.`activo` = 1) and (`U`.`activo` = 1)) group by `UO`.`Codigo_Unidad_Organica`,`UO`.`Nombre_Unidad_Organica`,`UO`.`Codigo_Unidad_Superior`,`UO`.`Nombre_Unidad_Superior`,`UO`.`Codigo_Unidad_Raiz`,`UO`.`Nombre_Unidad_Raiz`;
DROP VIEW IF EXISTS V_ORGANISMOS_INSIDE_WEB_SERVICE;
CREATE VIEW V_ORGANISMOS_INSIDE_WEB_SERVICE (CODIGO_UNIDAD_ORGANICA, NOMBRE_UNIDAD_ORGANICA, CODIGO_UNIDAD_SUPERIOR, NOMBRE_UNIDAD_SUPERIOR, CODIGO_UNIDAD_RAIZ, NOMBRE_UNIDAD_RAIZ, FECHA_ALTA) AS select `UO`.`Codigo_Unidad_Organica` AS `CODIGO_UNIDAD_ORGANICA`,`UO`.`Nombre_Unidad_Organica` AS `NOMBRE_UNIDAD_ORGANICA`,`UO`.`Codigo_Unidad_Superior` AS `CODIGO_UNIDAD_SUPERIOR`,`UO`.`Nombre_Unidad_Superior` AS `NOMBRE_UNIDAD_SUPERIOR`,`UO`.`Codigo_Unidad_Raiz` AS `CODIGO_UNIDAD_RAIZ`,`UO`.`Nombre_Unidad_Raiz` AS `NOMBRE_UNIDAD_RAIZ`,min(`A`.`fechaCreacion`) AS `FECHA_ALTA` from ((`UnidadWsAplicacion` `UA` join `UnidadOrganica` `UO`) join `InsideWsAplicacion` `A`) where ((`UA`.`id_unidad` = `UO`.`id`) and (`UA`.`id_aplicacion` = `A`.`id`) and (`UA`.`activo` = 1) and (`A`.`activo` = 1)) group by `UO`.`Codigo_Unidad_Organica`,`UO`.`Nombre_Unidad_Organica`,`UO`.`Codigo_Unidad_Superior`,`UO`.`Nombre_Unidad_Superior`,`UO`.`Codigo_Unidad_Raiz`,`UO`.`Nombre_Unidad_Raiz`;
DROP FUNCTION GetAncestry;
--/
CREATE DEFINER=`UsuarioBBDD`@`%` FUNCTION GetAncestry(GivenID INT) RETURNS varchar(200) CHARSET utf8
    READS SQL DATA
    DETERMINISTIC
BEGIN
    DECLARE rv VARCHAR(1024);
    DECLARE cm CHAR(1);
    DECLARE ch INT;

    SET rv = '';
    SET cm = '';
    SET ch = GivenID;
    WHILE ch > 0 DO
        SELECT IFNULL(parent_id,-1) INTO ch FROM
        (SELECT parent_id FROM pctable WHERE id = ch) A;
        IF ch > 0 THEN
            SET rv = CONCAT(rv,cm,ch);
            SET cm = ',';
        END IF;
    END WHILE;
    RETURN rv;
END
/

--/
CREATE DEFINER=`UsuarioBBDD`@`%` TRIGGER update_expediente_vinculado
AFTER INSERT ON 
schemaName.ExpedienteInside
FOR EACH ROW BEGIN
    
	IF(NEW.version > 1) THEN
	
	SET @id_vinculado_a_cambiar = (select id from ExpedienteInside where identificador = New.identificador and version = (NEW.version - 1));
	UPDATE ExpedienteInsideIndice SET id_expediente_vinculado=New.id WHERE id_expediente_vinculado = @id_vinculado_a_cambiar;
	
	END IF;
END
/



