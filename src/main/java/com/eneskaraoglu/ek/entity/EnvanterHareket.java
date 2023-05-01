package com.eneskaraoglu.ek.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "t_envater_hareket")
@Getter
@Setter
@NoArgsConstructor
public class EnvanterHareket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int envanterHareketId;

	private BigDecimal hareketMiktar;
	private Date hareketTarih;
	private String hareketTuru;
	private String hareketTipi;
	private String aciklama;
	private int depoId;
	private int envanterId;

}
