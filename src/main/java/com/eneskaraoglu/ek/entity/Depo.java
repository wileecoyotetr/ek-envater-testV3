package com.eneskaraoglu.ek.entity;

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
@Table(name="t_depo")
@Getter @Setter @NoArgsConstructor 
public class Depo {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="depo_id")
	 private int depoId;
	 
	@Column(name="depo_kod")
	private String depoKod;
	
	@Column(name="depo_adi")
	private String depoAdi;
	
	@Column(name="bolge")
	private String bolge;
	
	@Column(name="sehir")
	private String sehir;
	
	@Column(name="adres")
	private String adres;

}
