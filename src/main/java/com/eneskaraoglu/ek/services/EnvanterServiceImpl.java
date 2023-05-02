package com.eneskaraoglu.ek.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eneskaraoglu.ek.dao.EnvanterDAO;
import com.eneskaraoglu.ek.entity.Envanter;

@Service
public class EnvanterServiceImpl implements EnvanterService {
	
	private EnvanterDAO dao;
	
	@Autowired
	public EnvanterServiceImpl(EnvanterDAO theDao) {
		dao = theDao;
	}

	@Override
	public List<Envanter> findAll() {
		return dao.findAll();
	}

	@Override
	public Envanter findById(int theId) {
		Optional<Envanter> result = Optional.ofNullable(dao.findById(theId));
		Envanter envanter = null;
		if (result.isPresent()) {
			envanter = result.get();
		}
		else {
			throw new RuntimeException("Depo Id bulunamadı ->"+theId);
		}
		return envanter;
	}

	@Transactional
	@Override
	public Envanter save(Envanter theEntity) {
		return dao.save(theEntity);
	}

	@Transactional
	@Override
	public void deleteById(int theId) {
		dao.deleteById(theId);
	}
	
}
