package com.eneskaraoglu.ek.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eneskaraoglu.ek.entity.Depo;
import com.eneskaraoglu.ek.entity.EnvanterHareket;
import com.eneskaraoglu.ek.services.EnvanterHareketService;

@RestController
@RequestMapping("/env")
public class EnvanterHareketController {
	
	private EnvanterHareketService service;
	
	public EnvanterHareketController(EnvanterHareketService theService) {
		service = theService;
	}
	
	
	@PostMapping("/envanterHareket")
	public EnvanterHareket addEnvanterHareket(@RequestBody EnvanterHareket theEntity) {
		theEntity.setEnvanterHareketId(0);
		EnvanterHareket result = service.save(theEntity);
		return result;
	}

	@PutMapping("/envanterHareket")
	public EnvanterHareket updateEnvanterHareket(@RequestBody EnvanterHareket theEntity) {
		EnvanterHareket result = service.save(theEntity);
		return result;
	}
	
	@DeleteMapping("/envanterHareket/{id}")
	public String deleteEnvanterHareket(@PathVariable int id) {
		EnvanterHareket result = service.findById(id);
		if (result == null) {
			throw new RuntimeException("EnvanterHareket bulunmadı ->"+ id);
		}
		service.deleteById(id);
		return "Silinen EnvanterHareket ->"+ id;
	}
}
