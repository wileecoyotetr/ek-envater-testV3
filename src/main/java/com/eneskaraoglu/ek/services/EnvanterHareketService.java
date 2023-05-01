package com.eneskaraoglu.ek.services;

import java.util.List;

import com.eneskaraoglu.ek.entity.Depo;
import com.eneskaraoglu.ek.entity.EnvanterHareket;

public interface EnvanterHareketService {

	List<EnvanterHareket> findAll();
	EnvanterHareket findById(int theId);
	EnvanterHareket save(EnvanterHareket theEntity);
	void deleteById(int theId);

}
