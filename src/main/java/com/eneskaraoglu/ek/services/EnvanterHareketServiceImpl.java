package com.eneskaraoglu.ek.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eneskaraoglu.ek.dao.EnvanterHareketDAO;
import com.eneskaraoglu.ek.entity.EnvanterHareket;

@Service
public class EnvanterHareketServiceImpl implements EnvanterHareketService {
	
	private EnvanterHareketDAO dao;
	
	@Autowired
	public EnvanterHareketServiceImpl(EnvanterHareketDAO theDao) {
		dao = theDao;
	}

	@Override
	public List<EnvanterHareket> findAll() {
		return dao.findAll();
	}

	@Override
	public EnvanterHareket findById(int theId) {
		Optional<EnvanterHareket> result = Optional.ofNullable(dao.findById(theId));
		EnvanterHareket theDepo = null;
		if (result.isPresent()) {
			theDepo = result.get();
		}
		else {
			throw new RuntimeException("Depo Id bulunamadı ->"+theId);
		}
		return theDepo;
	}

	@Transactional
	@Override
	public EnvanterHareket save(EnvanterHareket theEntity) {
		return dao.save(theEntity);
	}

	@Transactional
	@Override
	public void deleteById(int theId) {
		dao.deleteById(theId);
	}
	
}
