package com.eneskaraoglu.ek.entity;

import java.math.BigDecimal;

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
@Table(name="t_envanter")
@Getter @Setter @NoArgsConstructor 
public class Envanter {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="envanter_id")
	 private int envanterId;
	 
	@Column(name="envanter_adi")
	private String envanterAdi;
	
	@Column(name="envanter_kodu")
	private String envanterKodu;
	
	@Column(name="envanter_miktar")
	private BigDecimal envanterMiktar;

	@Column(name="kritik_miktar")
	private BigDecimal kritikMiktar;

}
