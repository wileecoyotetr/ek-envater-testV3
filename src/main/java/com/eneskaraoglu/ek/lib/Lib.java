package com.eneskaraoglu.ek.lib;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Lib {

	public BigDecimal bigdecimalZero(BigDecimal value) {
		BigDecimal reValue = new BigDecimal(0);
		if (value == null) {
			return reValue;
		} else {
			return value;
		}
	}
	
	public Date sysdate() {
		return new Date();  
	}

}
