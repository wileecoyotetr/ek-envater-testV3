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
@Table(name="t_depo_envanter")
@Getter @Setter @NoArgsConstructor 
public class DepoEnvanter extends BaseEntity  {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="depo_envanter_id")
	 private int depoEnvanterId;
	 
	@Column(name="depo_id")
	private int depoId;
	
	@Column(name="envanter_id")
	private int envanterId;
	
	@Column(name="depo_miktar")
	private BigDecimal depoMiktar;
	
}
