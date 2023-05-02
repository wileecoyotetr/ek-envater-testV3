package com.eneskaraoglu.ek.lib;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.eneskaraoglu.ek.entity.BaseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	public String jsonString(Object obj) {
		if (obj instanceof BaseEntity) {
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
			try {
				json = mapper.writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			return json.toString();
		}
		return "";
	}

	public Timestamp getSysDate() {
		return new Timestamp(System.currentTimeMillis());
	}

	public String str(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}
}
