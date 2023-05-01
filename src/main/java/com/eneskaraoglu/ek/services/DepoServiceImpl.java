package com.eneskaraoglu.ek.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneskaraoglu.ek.entity.Depo;
import com.eneskaraoglu.ek.repository.DepoRepository;

@Service
public class DepoServiceImpl implements DepoService {
	
	private DepoRepository repository;
	
	@Autowired
	public DepoServiceImpl(DepoRepository theRepository) {
		repository = theRepository;
	}

	@Override
	public List<Depo> findAll() {
		return repository.findAll();
	}

	@Override
	public Depo findByID(int theId) {
		Optional<Depo> result = repository.findById(theId);
		Depo theDepo = null;
		if (result.isPresent()) {
			theDepo = result.get();
		}
		else {
			throw new RuntimeException("Depo Id bulunamadı ->"+theId);
		}
		return theDepo;
	}

	@Override
	public Depo save(Depo theEntity) {
		return repository.save(theEntity);
	}

	@Override
	public void deleteByID(int theId) {
		repository.deleteById(theId);
	}
	
}
