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
@Table(name="t_envater_log")
@Getter @Setter @NoArgsConstructor 
public class EnvanterLog {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="envanter_log_id")
	 private int envanterLogId;
	 
	@Column(name="depo_id")
	private int depoId;
	
	@Column(name="envanter_id")
	private int envanterId;
	
	@Column(name="hareket_miktar")
	private BigDecimal hareketMiktar;

	@Column(name="hareket_tarih")
	private Date hareketTarih;

	@Column(name="hareket_turu")
	private String hareketTuru;
	
	@Column(name="hareket_tipi")
	private String hareketTipi;
	
}
