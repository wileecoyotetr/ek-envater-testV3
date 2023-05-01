package com.eneskaraoglu.ek.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eneskaraoglu.ek.entity.Depo;
import com.eneskaraoglu.ek.services.DepoService;

@RestController
@RequestMapping("/env")
public class DepoController {
	
	private DepoService service;
	
	public DepoController(DepoService theService) {
		service = theService;
	}
	
	@GetMapping("/depo")
	public List<Depo> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/depo/{depoId}")
	public Depo getDepo(@PathVariable int depoId){
		Depo result = service.findByID(depoId);
		
		if (result==null) {
			throw new RuntimeException("Depo Bulunamadı ->"+ depoId);
		}
		
		return result;
	}
	
	@PostMapping("/depo")
	public Depo addDepo(@RequestBody Depo theEntity) {
		theEntity.setDepoId(0);
		Depo result = service.save(theEntity);
		return result;
	}

	@PutMapping("/depo")
	public Depo updateDepo(@RequestBody Depo theEntity) {
		Depo result = service.save(theEntity);
		return result;
	}
	
	@DeleteMapping("/depo/{depoId}")
	public String deleteDepo(@PathVariable int depoId) {
		Depo result = service.findByID(depoId);
		if (result == null) {
			throw new RuntimeException("Depo bulunmadı ->"+ depoId);
		}
		service.deleteByID(depoId);
		return "Silinen Depo ->"+ depoId;
	}
}
