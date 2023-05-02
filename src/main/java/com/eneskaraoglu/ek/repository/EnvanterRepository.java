package com.eneskaraoglu.ek.repository;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.exception.NotFoundException;

public interface EnvanterRepository extends JpaRepository<Envanter, Integer> {
	

	@SuppressWarnings("unchecked")
	@Override
	default <S extends Envanter> S save(S entity) {
		//tanımlama sırasında miktar 0 dan farklı olmaz, burada ezildi.
		System.out.println(entity.toString());
		Envanter theEnvanter =  entity;
		//Envanter miktarı buradan update edilemez
		//ilk kayıt ise 0 set edildi
		//Update ise findById yapılıp set edildi.
		int id = entity.getEnvanterId();
		if (id == 0) {
			entity.setEnvanterMiktar(new BigDecimal(0));
		}
		else
		{
			Optional<Envanter> result = findById(id);
			Envanter entityOld = null;
			
			if (result.isPresent()) {
				entityOld = result.get();
			}
			else {
				throw new NotFoundException("Envanter Id bulunamadı ->"+id);
			}
			entity.setEnvanterMiktar(entityOld.getEnvanterMiktar());
		}
		
		
		return (S) saveAndFlush(theEnvanter);
	}
}
