package com.eneskaraoglu.ek.entity;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="t_envater_giris")
@Getter @Setter @NoArgsConstructor 
public class EnvanterGiris {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="envanter_giris_id")
	 private int envanterGirisId;
	 
	@Column(name="depo_id")
	private int depoId;
	
	@Column(name="envanter_id")
	private int envanterId;
	
	@Column(name="giris_miktar")
	private BigDecimal girisMiktar;

	@Column(name="giris_tarih")
	private Date girisTarih;

}
