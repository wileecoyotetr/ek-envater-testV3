package com.eneskaraoglu.ek.dao;

import java.util.List;

import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.entity.VEnvanterDepo;

public interface EnvanterDAO {
	
	List<Envanter> findAll();
	Envanter findById(int theId);
	Envanter save(Envanter theEntity);
	void deleteById(int theId);
	List<VEnvanterDepo> findByEntity(VEnvanterDepo theEntity);
}
