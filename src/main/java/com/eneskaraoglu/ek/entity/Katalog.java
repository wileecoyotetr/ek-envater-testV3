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
@Table(name="t_katalog")
@Getter @Setter @NoArgsConstructor 
public class Katalog extends BaseEntity  {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="katalog_id")
	 private int katalogId;
	 
	@Column(name="katalog_adi")
	private String katalogAdi;
	
	@Column(name="katalog_kodu")
	private String katalogKodu;

}
