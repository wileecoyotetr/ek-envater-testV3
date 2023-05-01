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
@Table(name="t_katalog_envanter")
@Getter @Setter @NoArgsConstructor 
public class KatalogEnvanter {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name="katalog_envanter_id")
	 private int katalogEnvanterId;
	 
	@Column(name="katalog_id")
	private int katalogId;
	
	@Column(name="envanter_id")
	private int envanterId;

	
}
