# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.6/maven-plugin/reference/html/#build-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.0.6/reference/htmlsingle/#data.sql.jpa-and-spring-data)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)


Envanterde bulunacak ürünler için ürün ekleme, çıkarma, silme ve düzenleme
işlemlerinin yapılması.
Bu işlemler yapılırken bunların bir history tablosunda kaydının tutulması
gerekmektedir.
Her ürünün id ‘sinin, adının, kategorisinin ve ürün sayısının olması zorunludur.
Kategori tablosu olmalıdır ve her ürünün bir kategorisi olması zorunludur.
Envanterden çıkartma işlemi yapılabilmelidir. Her bir ürün için bir kritik eşik
belirlenmeli ve envanterden çıkartma işlemi yapıldıktan sonra eğer o eşiğin
altına düşerse mail atma işlemi yapılmalıdır(mail atmak opsiyoneldir, log da
basılabilir.)
Depo tablosu olmalıdır. Deponun adı, adresi, bulunduğu bölge(Akdeniz, Ege
..), ve şehir bilgisi tutulmalıdır. Ürünlerin hangi depolarda tutulduğu
belirtilmelidir. (Bölge ve şehir bilgilerinin tablolarda tutulmasına gerek yoktur.)
Ürünler depoya, deponun bölgesine, deponun şehrine veya kategorisine göre
filtrelenebilir.
Bir ürünün aratılıp hangi depolarda olduğunu gösteren apinin yazılması
gerekmektedir.
Kullanılması gereken teknolojiler:
Spring Boot 2.3 +
Java 8 +
MySql veya Postgresql



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