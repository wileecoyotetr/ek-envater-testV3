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


DROP TABLE IF EXISTS `t_urun`;

CREATE TABLE `t_urun` (
  `urun_id` int NOT NULL AUTO_INCREMENT,
  `urun_adi` varchar(45) DEFAULT NULL,
  `urun_kodu` varchar(45) DEFAULT NULL,
  `urun_miktar` decimal(10,0) DEFAULT NULL,
  PRIMARY KEY (`urun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- envanter_directory.t_katalog definition

CREATE TABLE `t_katalog` (
  `katalog_id` int NOT NULL AUTO_INCREMENT,
  `katalog_adi` varchar(45) DEFAULT NULL,
  `katalog_kodu` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`katalog_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


-- envanter_directory.t_katalog_urun definition

CREATE TABLE `t_katalog_urun` (
  `katalog_urun_id` int NOT NULL AUTO_INCREMENT,
  `katalog_id` int NOT NULL,
  `urun_id` int NOT NULL,
  PRIMARY KEY (`katalog_urun_id`),
  KEY `t_katalog_urun_FK` (`katalog_id`),
  KEY `t_katalog_urun_FK_1` (`urun_id`),
  CONSTRAINT `t_katalog_urun_FK` FOREIGN KEY (`katalog_id`) REFERENCES `t_katalog` (`katalog_id`),
  CONSTRAINT `t_katalog_urun_FK_1` FOREIGN KEY (`urun_id`) REFERENCES `t_urun` (`urun_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
