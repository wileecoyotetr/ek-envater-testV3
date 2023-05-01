package com.eneskaraoglu.ek.dao;

import java.util.List;

import com.eneskaraoglu.ek.entity.EnvanterHareket;

public interface EnvanterHareketDAO {
	
	List<EnvanterHareket> findAll();
	EnvanterHareket findById(int theId);
	EnvanterHareket save(EnvanterHareket theEntity);
	void deleteById(int theId);
}
