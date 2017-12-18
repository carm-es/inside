/*
SQLyog Ultimate v8.55 
MySQL - 5.5.9-log : Database - insidews
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`insidews` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `insidews`;

/*Table structure for table `DocumentoInside` */

DROP TABLE IF EXISTS `DocumentoInside`;

CREATE TABLE `DocumentoInside` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identificador` varchar(255) NOT NULL,
  `identificador_repositorio` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `fechaVersion` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `versionNti` varchar(255) DEFAULT NULL,
  `fechaCaptura` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `origen` tinyint(1) DEFAULT '0',
  `nombreFormato` varchar(255) DEFAULT NULL,
  `tipoMime` varchar(255) DEFAULT NULL,
  `tipoDocumental` varchar(255) DEFAULT NULL,
  `estadoElaboracion` varchar(255) DEFAULT NULL,
  `identificadorDocumentoOrigen` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_inside_aplicacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identificador_version` (`identificador`,`version`),
  KEY `FK_DocumentoInside_Aplicacion` (`id_inside_aplicacion`),
  CONSTRAINT `FK_DocumentoInside_Aplicacion` FOREIGN KEY (`id_inside_aplicacion`) REFERENCES `InsideAplicacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Table structure for table `DocumentoInsideFirmas` */

DROP TABLE IF EXISTS `DocumentoInsideFirmas`;

CREATE TABLE `DocumentoInsideFirmas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_documento` int(11) NOT NULL,
  `id_firma` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentoInsideFirmas_Documento` (`id_documento`),
  KEY `FK_DocumentoInsideFirmas_Firma` (`id_firma`),
  CONSTRAINT `FK_DocumentoInsideFirmas_Documento` FOREIGN KEY (`id_documento`) REFERENCES `DocumentoInside` (`id`),
  CONSTRAINT `FK_DocumentoInsideFirmas_Firma` FOREIGN KEY (`id_firma`) REFERENCES `FirmaInside` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

/*Table structure for table `DocumentoInsideMetadatosAdicionales` */

DROP TABLE IF EXISTS `DocumentoInsideMetadatosAdicionales`;

CREATE TABLE `DocumentoInsideMetadatosAdicionales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_documento` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` longtext,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentoInsideMetadatosAdicionales_Documento` (`id_documento`),
  CONSTRAINT `FK_DocumentoInsideMetadatosAdicionales_Documento` FOREIGN KEY (`id_documento`) REFERENCES `DocumentoInside` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Table structure for table `DocumentoInsideOrgano` */

DROP TABLE IF EXISTS `DocumentoInsideOrgano`;

CREATE TABLE `DocumentoInsideOrgano` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_documento` int(11) NOT NULL,
  `organo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DocumentoInsideOrgano_Documento` (`id_documento`),
  CONSTRAINT `FK_DocumentoInsideOrgano_Documento` FOREIGN KEY (`id_documento`) REFERENCES `DocumentoInside` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInside` */

DROP TABLE IF EXISTS `ExpedienteInside`;

CREATE TABLE `ExpedienteInside` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identificador` varchar(255) NOT NULL,
  `identificador_repositorio` varchar(255) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '1',
  `fechaVersion` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `versionNti` varchar(255) DEFAULT NULL,
  `fechaAperturaExpediente` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `clasificacion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `id_indice` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `id_inside_aplicacion` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `identificador_version` (`identificador`,`version`),
  KEY `FK_ExpedienteInside_Indice` (`id_indice`),
  KEY `FK_ExpedienteInside_Aplicacion` (`id_inside_aplicacion`),
  CONSTRAINT `FK_ExpedienteInside_Aplicacion` FOREIGN KEY (`id_inside_aplicacion`) REFERENCES `InsideAplicacion` (`id`),
  CONSTRAINT `FK_ExpedienteInside_Indice` FOREIGN KEY (`id_indice`) REFERENCES `ExpedienteInsideIndice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInsideIndice` */

DROP TABLE IF EXISTS `ExpedienteInsideIndice`;

CREATE TABLE `ExpedienteInsideIndice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_indice_electronico` timestamp NULL DEFAULT NULL,
  `id_expediente_indizado` int(11) DEFAULT NULL,
  `id_carpeta_indizada` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_ExpedienteInsideIndice_ExpedienteIndizado` (`id_expediente_indizado`),
  KEY `FK_ExpedienteInsideIndice_CarpetaIndizada` (`id_carpeta_indizada`),
  CONSTRAINT `FK_ExpedienteInsideIndice_CarpetaIndizada` FOREIGN KEY (`id_carpeta_indizada`) REFERENCES `ExpedienteInsideIndiceCarpetaIndizada` (`id`),
  CONSTRAINT `FK_ExpedienteInsideIndice_ExpedienteIndizado` FOREIGN KEY (`id_expediente_indizado`) REFERENCES `ExpedienteInsideIndice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInsideIndiceCarpetaIndizada` */

DROP TABLE IF EXISTS `ExpedienteInsideIndiceCarpetaIndizada`;

CREATE TABLE `ExpedienteInsideIndiceCarpetaIndizada` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identificador_carpeta` varchar(255) DEFAULT NULL,
  `id_expediente_indizado` int(11) DEFAULT NULL,
  `id_carpeta_indizada` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_ExpedienteInsideIndiceCarpetaIndizada_Indice` (`id_expediente_indizado`),
  KEY `FK_ExpedienteInsideIndiceCarpetaIndizada_Carpeta` (`id_carpeta_indizada`),
  CONSTRAINT `FK_ExpedienteInsideIndiceCarpetaIndizada_Carpeta` FOREIGN KEY (`id_carpeta_indizada`) REFERENCES `ExpedienteInsideIndiceCarpetaIndizada` (`id`),
  CONSTRAINT `FK_ExpedienteInsideIndiceCarpetaIndizada_Indice` FOREIGN KEY (`id_expediente_indizado`) REFERENCES `ExpedienteInsideIndice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInsideIndiceDocumentoIndizado` */

DROP TABLE IF EXISTS `ExpedienteInsideIndiceDocumentoIndizado`;

CREATE TABLE `ExpedienteInsideIndiceDocumentoIndizado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valorHuella` varchar(255) DEFAULT NULL,
  `funcionResumen` varchar(255) DEFAULT NULL,
  `fechaIncorporacionExpediente` timestamp NULL DEFAULT NULL,
  `ordenDocumentoExpediente` int(11) DEFAULT NULL,
  `identificadorDocumento` varchar(255) DEFAULT NULL,
  `id_expediente_indizado` int(11) DEFAULT NULL,
  `id_carpeta_indizada` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_ExpedienteInsideIndiceDocumentoIndizado_Expediente` (`id_expediente_indizado`),
  KEY `FK_ExpedienteInsideIndiceDocumentoIndizado_Carpeta` (`id_carpeta_indizada`),
  CONSTRAINT `FK_ExpedienteInsideIndiceDocumentoIndizado_Carpeta` FOREIGN KEY (`id_carpeta_indizada`) REFERENCES `ExpedienteInsideIndiceCarpetaIndizada` (`id`),
  CONSTRAINT `FK_ExpedienteInsideIndiceDocumentoIndizado_Indice` FOREIGN KEY (`id_expediente_indizado`) REFERENCES `ExpedienteInsideIndice` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInsideIndiceFirmas` */

DROP TABLE IF EXISTS `ExpedienteInsideIndiceFirmas`;

CREATE TABLE `ExpedienteInsideIndiceFirmas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(11) NOT NULL,
  `id_firmaInside` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ExpedienteInsideIndiceFirmas_Firma` (`id_firmaInside`),
  KEY `FK_ExpedienteInsideIndiceFirmas_Expediente` (`id_expediente`),
  CONSTRAINT `FK_ExpedienteInsideIndiceFirmas_Expediente` FOREIGN KEY (`id_expediente`) REFERENCES `ExpedienteInside` (`id`),
  CONSTRAINT `FK_ExpedienteInsideIndiceFirmas_Firma` FOREIGN KEY (`id_firmaInside`) REFERENCES `FirmaInside` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInsideInteresado` */

DROP TABLE IF EXISTS `ExpedienteInsideInteresado`;

CREATE TABLE `ExpedienteInsideInteresado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(11) NOT NULL,
  `interesado` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ExpedienteInsideInteresado_Expediente` (`id_expediente`),
  CONSTRAINT `FK_ExpedienteInsideInteresado_Expediente` FOREIGN KEY (`id_expediente`) REFERENCES `ExpedienteInside` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInsideMetadatosAdicionales` */

DROP TABLE IF EXISTS `ExpedienteInsideMetadatosAdicionales`;

CREATE TABLE `ExpedienteInsideMetadatosAdicionales` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(11) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` longtext,
  PRIMARY KEY (`id`),
  KEY `FK_ExpedienteInsideMetadatosAdicionales_Expediente` (`id_expediente`),
  CONSTRAINT `FK_ExpedienteInsideMetadatosAdicionales_Expediente` FOREIGN KEY (`id_expediente`) REFERENCES `ExpedienteInside` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `ExpedienteInsideOrgano` */

DROP TABLE IF EXISTS `ExpedienteInsideOrgano`;

CREATE TABLE `ExpedienteInsideOrgano` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_expediente` int(11) NOT NULL,
  `organo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ExpedienteInsideOrgano_Expediente` (`id_expediente`),
  CONSTRAINT `FK_ExpedienteInsideOrgano_Expediente` FOREIGN KEY (`id_expediente`) REFERENCES `ExpedienteInside` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8;

/*Table structure for table `FirmaInside` */

DROP TABLE IF EXISTS `FirmaInside`;

CREATE TABLE `FirmaInside` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoFirma` varchar(255) NOT NULL,
  `identificadorRepositorio` varchar(255) DEFAULT NULL,
  `valorCSV` varchar(255) DEFAULT NULL,
  `regulacionCSV` varchar(255) DEFAULT NULL,
  `tipoMime` varchar(255) DEFAULT NULL,
  `referencia` varchar(255) DEFAULT NULL,
  `signature` tinyint(1) DEFAULT '0',
  `timestamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Table structure for table `InsideAplicacion` */

DROP TABLE IF EXISTS `InsideAplicacion`;

CREATE TABLE `InsideAplicacion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `passwordHash` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
