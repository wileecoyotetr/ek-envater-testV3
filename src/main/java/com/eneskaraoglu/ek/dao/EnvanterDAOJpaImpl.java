package com.eneskaraoglu.ek.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eneskaraoglu.ek.entity.DepoEnvanter;
import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.exception.NotFoundException;
import com.eneskaraoglu.ek.lib.Lib;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EnvanterDAOJpaImpl implements EnvanterDAO {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	private EntityManager entityManager;

	@Autowired
	private Lib lib;

	@Autowired
	public EnvanterDAOJpaImpl(EntityManager theEntityManager, Lib theLib) {
		entityManager = theEntityManager;
		lib = theLib;
	}

	@Override
	public List<Envanter> findAll() {
		TypedQuery<Envanter> theQuery = entityManager.createQuery(" from Envanter",
				Envanter.class);
		List<Envanter> result = theQuery.getResultList();
		return result;
	}

	@Override
	public Envanter findById(int theId) {
		Envanter result = entityManager.find(Envanter.class, theId);
		return result;
	}

	@Override
	public Envanter save(Envanter entity) {

		//tanımlama sırasında miktar 0 dan farklı olmaz, burada ezildi.
		Envanter theEnvanter =  entity;
		
		//Envanter miktarı buradan update edilemez
		//ilk kayıt ise 0 set edildi
		//Update ise findById yapılıp set edildi.
		DepoEnvanter depoEnvanter =null;
		int id = entity.getEnvanterId();
		if (id == 0) {
			//
			//System.out.println("---getKatalogEnvanter-"+lib.jsonString(entity.getDepoEnvanter()));
			//Katalog envanter kontrol edildi, yoksa uyarı döndürüldü
			System.out.println("----getKatalogId------------------------>"+entity.getKatalogId());
			if (entity.getKatalogId()==0) {
				throw new NotFoundException("Ilk kayıt giriş sırasında Katalog Seçili olmalı->"+id);
			}
			
			if (entity.getDepoEnvanter()==null) {
				throw new NotFoundException("ENVANTER tanımalama sırasında DEPO da tanımlanmalı->"+id);
			}
			else {
				depoEnvanter = entity.getDepoEnvanter();
				System.out.println("-------1--------------------->"+lib.jsonString(depoEnvanter));
			}
			
			entity.setEnvanterMiktar(new BigDecimal(0));
		}
		else
		{
			Optional<Envanter> result = Optional.ofNullable(findById(id));
			Envanter entityOld = null;
			
			if (result.isPresent()) {
				entityOld = result.get();
			}
			else {
				throw new NotFoundException("Envanter Id bulunamadı ->"+id);
			}
			//Miktar buradan update edilemez
			entity.setEnvanterMiktar(entityOld.getEnvanterMiktar());
		}
		

		Envanter result = entityManager.merge(entity);
		
		if (depoEnvanter!=null) {
			depoEnvanter.setDepoEnvanterId(0);
			depoEnvanter.setEnvanterId(result.getEnvanterId());
			depoEnvanter.setDepoMiktar(new BigDecimal(0));
			System.out.println("-------2--------------------->"+lib.jsonString(depoEnvanter));
			DepoEnvanter resultDepoEnavnter = entityManager.merge(depoEnvanter);
			result.setDepoEnvanter(resultDepoEnavnter);
		}
		
		return result;
	}

	@Override
	public void deleteById(int theId) {

		Envanter result = entityManager.find(Envanter.class, theId);
		entityManager.remove(result);
	}

	@Override
	public List<Envanter> findByEntity(Envanter theEntity) {
		TypedQuery<Envanter> theQuery = entityManager.createQuery(
				" from Envanter "
				+ " where "
				+ " (UCASE(envanterAdi) like CONCAT('%',UCASE(:envanterAdi),'%') or :envanterAdi is null) "
				+ " and (UCASE(envanterKodu) like CONCAT('%',UCASE(:envanterKodu),'%') or :envanterKodu is null ) "
				+ " and (katalogId = :katalogId or :katalogId = 0) "
				, Envanter.class);
		theQuery.
		setParameter("envanterAdi", theEntity.getEnvanterAdi())
		.setParameter("envanterKodu", theEntity.getEnvanterKodu())
		.setParameter("katalogId", theEntity.getKatalogId());
		
		List<Envanter>  result = theQuery.getResultList();
		return result;
	}

}
