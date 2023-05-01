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
@Table(name="t_envater_cikis")
@Getter @Setter @NoArgsConstructor 
public class EnvanterCikis {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="envanter_cikis_id")
	 private int envanterCikisId;
	 
	@Column(name="depo_id")
	private int depoId;
	
	@Column(name="envanter_id")
	private int envanterId;
	
	@Column(name="cikis_miktar")
	private BigDecimal cikisMiktar;

	@Column(name="cikis_tarih")
	private Date cikisTarih;

}
