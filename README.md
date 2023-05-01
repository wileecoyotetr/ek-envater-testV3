# ek-envater-test
envater-test

sample spring boot project;

Spring Boot 3.0.6

Java 17

MySql


Spring data jpa

JpaRepository

Spring data rest

DAO 

Lombok


Spring data jpa;
Spring data rest;
CRUD
{
    "_links": {
        "depoEnvanters": {
            "href": "http://127.0.0.1:8080/data-rest/depoEnvanters{?page,size,sort}",
            "templated": true
        },
        "envanters": {
            "href": "http://127.0.0.1:8080/data-rest/envanters{?page,size,sort}",
            "templated": true
        },
        "katalogs": {
            "href": "http://127.0.0.1:8080/data-rest/katalogs{?page,size,sort}",
            "templated": true
        },
        "envanterLogs": {
            "href": "http://127.0.0.1:8080/data-rest/envanterLogs{?page,size,sort}",
            "templated": true
        },
        "envanterHarekets": {
            "href": "http://127.0.0.1:8080/data-rest/envanterHarekets{?page,size,sort}",
            "templated": true
        },
        "katalogEnvanters": {
            "href": "http://127.0.0.1:8080/data-rest/katalogEnvanters{?page,size,sort}",
            "templated": true
        },
        "profile": {
            "href": "http://127.0.0.1:8080/data-rest/profile"
        }
    }
}

Create, Read, Update, Delete
http://127.0.0.1:8080/env/envanterHareket
main logic is ;
EnvanterHareketDAOJpaImpl.java


DB NOTES;

CREATE DATABASE  IF NOT EXISTS `envanter_directory`;
USE `envanter_directory`;

-- envanter_directory.t_depo definition

CREATE TABLE `t_depo` (
  `depo_id` int NOT NULL AUTO_INCREMENT,
  `depo_kod` varchar(100) NOT NULL,
  `depo_adi` varchar(100) NOT NULL,
  `bolge` varchar(100) DEFAULT NULL,
  `sehir` varchar(100) DEFAULT NULL,
  `adres` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`depo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- envanter_directory.t_envanter definition

CREATE TABLE `t_envanter` (
  `envanter_id` int NOT NULL AUTO_INCREMENT,
  `envanter_adi` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `envanter_kodu` varchar(45) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `envanter_miktar` decimal(10,0) DEFAULT NULL,
  `kritik_miktar` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`envanter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;


-- envanter_directory.t_envater_log definition

CREATE TABLE `t_envater_log` (
  `envanter_log_id` int NOT NULL AUTO_INCREMENT,
  `depo_id` int DEFAULT NULL,
  `envanter_id` int DEFAULT NULL,
  `hareket_miktar` decimal(10,0) DEFAULT NULL,
  `hareket_tarih` date DEFAULT NULL,
  `hareket_turu` varchar(45) DEFAULT NULL,
  `hareket_tipi` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`envanter_log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- envanter_directory.t_katalog definition

CREATE TABLE `t_katalog` (
  `katalog_id` int NOT NULL AUTO_INCREMENT,
  `katalog_adi` varchar(45) DEFAULT NULL,
  `katalog_kodu` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`katalog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;


-- envanter_directory.t_depo_envanter definition

CREATE TABLE `t_depo_envanter` (
  `depo_envanter_id` int NOT NULL AUTO_INCREMENT,
  `depo_id` int DEFAULT NULL,
  `envanter_id` int DEFAULT NULL,
  `depo_miktar` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`depo_envanter_id`),
  UNIQUE KEY `t_depo_envanter_UN` (`depo_id`,`envanter_id`),
  KEY `t_depo_urun_FK` (`envanter_id`),
  CONSTRAINT `t_depo_urun_FK` FOREIGN KEY (`envanter_id`) REFERENCES `t_envanter` (`envanter_id`),
  CONSTRAINT `t_depo_urun_FK_1` FOREIGN KEY (`depo_id`) REFERENCES `t_depo` (`depo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- envanter_directory.t_envater_cikis definition

CREATE TABLE `t_envater_cikis` (
  `envanter_cikis_id` int NOT NULL AUTO_INCREMENT,
  `depo_id` int DEFAULT NULL,
  `envanter_id` int DEFAULT NULL,
  `cikis_miktar` decimal(10,0) DEFAULT NULL,
  `cikis_tarih` date DEFAULT NULL,
  PRIMARY KEY (`envanter_cikis_id`),
  KEY `t_envater_cikis_FK` (`depo_id`),
  KEY `t_envater_cikis_FK_1` (`envanter_id`),
  CONSTRAINT `t_envater_cikis_FK` FOREIGN KEY (`depo_id`) REFERENCES `t_depo` (`depo_id`),
  CONSTRAINT `t_envater_cikis_FK_1` FOREIGN KEY (`envanter_id`) REFERENCES `t_envanter` (`envanter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- envanter_directory.t_envater_giris definition

CREATE TABLE `t_envater_giris` (
  `envanter_giris_id` int NOT NULL AUTO_INCREMENT,
  `depo_id` int DEFAULT NULL,
  `envanter_id` int DEFAULT NULL,
  `giris_miktar` decimal(10,0) DEFAULT NULL,
  `giris_tarih` date DEFAULT NULL,
  PRIMARY KEY (`envanter_giris_id`),
  KEY `t_envater_giris_FK` (`depo_id`),
  KEY `t_envater_giris_FK_1` (`envanter_id`),
  CONSTRAINT `t_envater_giris_FK` FOREIGN KEY (`depo_id`) REFERENCES `t_depo` (`depo_id`),
  CONSTRAINT `t_envater_giris_FK_1` FOREIGN KEY (`envanter_id`) REFERENCES `t_envanter` (`envanter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- envanter_directory.t_envater_hareket definition

CREATE TABLE `t_envater_hareket` (
  `envanter_hareket_id` int NOT NULL AUTO_INCREMENT,
  `aciklama` varchar(255) DEFAULT NULL,
  `hareket_miktar` decimal(38,2) DEFAULT NULL,
  `hareket_tarih` datetime(6) DEFAULT NULL,
  `hareket_tipi` varchar(255) DEFAULT NULL,
  `hareket_turu` varchar(255) DEFAULT NULL,
  `depo_id` int DEFAULT NULL,
  `envanter_id` int DEFAULT NULL,
  PRIMARY KEY (`envanter_hareket_id`),
  KEY `FK912jtndutic4eudcjo5h7nm1y` (`depo_id`),
  KEY `FK2prxf6qaikj5wf15614bc5iub` (`envanter_id`),
  CONSTRAINT `FK2prxf6qaikj5wf15614bc5iub` FOREIGN KEY (`envanter_id`) REFERENCES `t_envanter` (`envanter_id`),
  CONSTRAINT `FK912jtndutic4eudcjo5h7nm1y` FOREIGN KEY (`depo_id`) REFERENCES `t_depo` (`depo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- envanter_directory.t_katalog_envanter definition

CREATE TABLE `t_katalog_envanter` (
  `katalog_envanter_id` int NOT NULL AUTO_INCREMENT,
  `katalog_id` int NOT NULL,
  `envanter_id` int NOT NULL,
  PRIMARY KEY (`katalog_envanter_id`),
  KEY `t_katalog_urun_FK` (`katalog_id`),
  KEY `t_katalog_urun_FK_1` (`envanter_id`),
  CONSTRAINT `t_katalog_urun_FK` FOREIGN KEY (`katalog_id`) REFERENCES `t_katalog` (`katalog_id`),
  CONSTRAINT `t_katalog_urun_FK_1` FOREIGN KEY (`envanter_id`) REFERENCES `t_envanter` (`envanter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

