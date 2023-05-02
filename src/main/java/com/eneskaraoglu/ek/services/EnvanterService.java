package com.eneskaraoglu.ek.services;

import java.util.List;

import com.eneskaraoglu.ek.entity.Envanter;

public interface EnvanterService {

	List<Envanter> findAll();
	Envanter findById(int theId);
	Envanter save(Envanter theEntity);
	void deleteById(int theId);
	List<Envanter> findByEntity(Envanter theEntity);

}
