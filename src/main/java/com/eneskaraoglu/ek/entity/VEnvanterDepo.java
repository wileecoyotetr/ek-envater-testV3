package com.eneskaraoglu.ek.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Immutable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Immutable
@Table(name = "v_envanter_depo")
@Getter
@Setter
@NoArgsConstructor
public class VEnvanterDepo extends BaseEntity  {

	@Id
	private String envanterKodu;
	private String envanterAdi;
	private BigDecimal envanterMiktar;
	private String depoKod;
	private String depoAdi;
	private BigDecimal depoMiktar;
	private String bolge;
	private String sehir;
	private String adres;
	private String katalogKodu;
	private String katalogAdi;

}
