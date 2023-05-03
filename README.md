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
spring-boot-starter-mail
AOP for logging --> t_envater_log
exception handling


Spring data jpa;
Spring data rest servis endPoint;
CRUD
      
    Katalog CRUD işemleri buradan yapıldı  
    katalogs
    http://127.0.0.1:8080/data-rest/katalogs{?page,size,sort}
    
    Depo CRUD işlemleri için;
    depoes
    http://127.0.0.1:8080/data-rest/depoes{?page,size,sort}",
 	
 	Depo Envanter CRUD işlemleri için;
    depoEnvanters
	http://127.0.0.1:8080/data-rest/depoEnvanters{?page,size,sort}",


Envanter tanımlama işlemi için bu servis kullanılacak; Create, Read, Update, Delete
Burada depoi katalog gibi kontroller yapıldı.
Eksik bilgi varsa düzgün bir formatta exception gelmektedir.
POST /env/envanter HTTP/1.1
Host: 127.0.0.1:8080
Content-Type: application/json
Content-Length: 277

{
                "envanterAdi": "urun21",
                "envanterKodu": "Test Ürünümüz Üç",
                "envanterMiktar": 12,
                "kritikMiktar": 100,
                "katalogId":2,
                "depoEnvanter" :  { "depoId" : 2}
                
}


Buradan envanter harekleri tanımlandı Create, Read, Update, Delete
- (eksi) işlem için çıkış kayıtı, diğer türlü giriş kayıtı yapıldı.
Bu işlemler sırasında , envanter miktarı, depo miktarı güncellendi.
Kontrol miktar için işlem burada yapıldı;
İşlemin temel logic'i burada yer almaktadır.
EnvanterHareketDAOJpaImpl.java
http://127.0.0.1:8080/env/envanterHareket
POST /env/envanterHareket HTTP/1.1
Host: 127.0.0.1:8080
Content-Type: application/json
Content-Length: 167

{
                "depoId": 3,
                "envanterId": 3,
                "hareketMiktar": -30,
                "aciklama": "- ise çıkış işlemi, diğer türlü giriş işlemi gibi düşünüldu"
}



http://127.0.0.1:8080/env/envanter
Envanter sorgulama, envater adı, depo kodu, bölge, şehir , adres, katalog a göre sorgu yapılabilir.
Dönüş olarak yukarıda bilgiler dışında envanter miktar ve depo miktar verecek.
Veri tabanına view eklendi, o view üzerinde sorgu yapıldı.
GET /env/envanter HTTP/1.1
Host: 127.0.0.1:8080
Content-Type: application/json
Content-Length: 211

{
                "envanterAdi": null,
                "depoKod": null,
                "bolge": null,
                "sehir": null,
                "adres": null,
                "katalogKodu": "K3"
}


DB NOTES;

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
  `katalog_id` int DEFAULT NULL,
  PRIMARY KEY (`envanter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;


-- envanter_directory.t_envater_log definition

CREATE TABLE `t_envater_log` (
  `envanter_log_id` int NOT NULL AUTO_INCREMENT,
  `log_method` varchar(1000) CHARACTER SET latin1 DEFAULT NULL,
  `log_parametre` varchar(1000) CHARACTER SET latin1 DEFAULT NULL,
  `log_return` varchar(1000) CHARACTER SET latin1 DEFAULT NULL,
  `log_tarih` timestamp NULL DEFAULT NULL,
  `log_sure_sn` decimal(10,2) DEFAULT NULL,
  `log_error` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`envanter_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=272 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- envanter_directory.v_envanter_depo source

CREATE OR REPLACE
ALGORITHM = UNDEFINED VIEW `envanter_directory`.`v_envanter_depo` AS
select
    `a`.`envanter_kodu` AS `envanter_kodu`,
    `a`.`envanter_adi` AS `envanter_adi`,
    `a`.`envanter_miktar` AS `envanter_miktar`,
    `c`.`depo_kod` AS `depo_kod`,
    `c`.`depo_adi` AS `depo_adi`,
    sum(`b`.`depo_miktar`) AS `depo_miktar`,
    `c`.`bolge` AS `bolge`,
    `c`.`sehir` AS `sehir`,
    `c`.`adres` AS `adres`,
    `d`.`katalog_kodu` AS `katalog_kodu`,
    `d`.`katalog_adi` AS `katalog_adi`
from
    (((`envanter_directory`.`t_envanter` `a`
join `envanter_directory`.`t_depo_envanter` `b`)
join `envanter_directory`.`t_depo` `c`)
join `envanter_directory`.`t_katalog` `d`)
where
    ((`a`.`envanter_id` = `b`.`envanter_id`)
        and (`b`.`depo_id` = `c`.`depo_id`)
            and (`a`.`katalog_id` = `d`.`katalog_id`))
group by
    `a`.`envanter_kodu`,
    `a`.`envanter_adi`,
    `a`.`envanter_miktar`,
    `c`.`depo_kod`,
    `c`.`depo_adi`,
    `c`.`bolge`,
    `c`.`sehir`,
    `c`.`adres`,
    `d`.`katalog_kodu`,
    `d`.`katalog_adi`;
