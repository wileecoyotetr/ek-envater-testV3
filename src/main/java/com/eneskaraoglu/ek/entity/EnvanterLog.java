package com.eneskaraoglu.ek.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

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

	@Column(name="log_tarih")
	private Timestamp logTarih;

	@Column(name="log_method")
	private String logMethod;
	
	@Column(name="log_parametre")
	private String logParametre;
	
	@Column(name="log_return")
	private String logReturn;
	
	@Column(name="log_sure_sn")
	private BigDecimal logSureSn;
	
	@Column(name="log_error")
	private String logError;
	
	
}
