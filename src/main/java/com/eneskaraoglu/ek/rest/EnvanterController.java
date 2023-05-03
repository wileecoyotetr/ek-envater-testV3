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

import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.entity.VEnvanterDepo;
import com.eneskaraoglu.ek.services.EnvanterService;

@RestController
@RequestMapping("/env")
public class EnvanterController {
	
	private EnvanterService service;
	
	public EnvanterController(EnvanterService theService) {
		service = theService;
	}
	
	@PostMapping("/envanter")
	public Envanter addEnvanter(@RequestBody Envanter theEntity) {
		theEntity.setEnvanterId(0);
		Envanter result = service.save(theEntity);
		return result;
	}

	@PutMapping("/envanter")
	public Envanter updateEnvanter(@RequestBody Envanter theEntity) {
		Envanter result = service.save(theEntity);
		return result;
	}
	
	@DeleteMapping("/envanter/{id}")
	public String deleteEnvanter(@PathVariable int id) {
		Envanter result = service.findById(id);
		if (result == null) {
			throw new RuntimeException("Envanter bulunmadı ->"+ id);
		}
		service.deleteById(id);
		return "Silinen Envanter ->"+ id;
	}
	
	@GetMapping("/envanterAll")
	public List<Envanter> getEnvanter() {
		List<Envanter> result = service.findAll();
		return result;
	}
	
	@GetMapping("/envanter/{id}")
	public Envanter getEnvanter(@PathVariable int id) {
		Envanter result = service.findById(id);
		return result;
	}
	
	@GetMapping("/envanter")
	public List<VEnvanterDepo> getEnvanterDetail(@RequestBody VEnvanterDepo theEntity) {
		List<VEnvanterDepo> result = service.findByEntity(theEntity);
		return result;
	}
}
