package com.eneskaraoglu.ek.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eneskaraoglu.ek.entity.DepoEnvanter;
import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.entity.EnvanterHareket;
import com.eneskaraoglu.ek.lib.Lib;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EnvanterHareketDAOJpaImpl implements EnvanterHareketDAO {

	private EntityManager entityManager;

	@Autowired
	private Lib lib;

	@Autowired
	public EnvanterHareketDAOJpaImpl(EntityManager theEntityManager, Lib theLib) {
		entityManager = theEntityManager;
		lib = theLib;
	}

	@Override
	public List<EnvanterHareket> findAll() {
		TypedQuery<EnvanterHareket> theQuery = entityManager.createQuery(" from EnvanterHareket",
				EnvanterHareket.class);
		List<EnvanterHareket> result = theQuery.getResultList();
		return result;
	}

	@Override
	public EnvanterHareket findById(int theId) {
		EnvanterHareket result = entityManager.find(EnvanterHareket.class, theId);
		return result;
	}

	@Override
	public EnvanterHareket save(EnvanterHareket theEntity) {

		// hareket miktar kadar depo miktarları ve envanter miktarları update edeceğiz
		// loglama yapmak iyi bir çözüm gibi görünyür

		BigDecimal hareketMiktar = lib.bigdecimalZero(theEntity.getHareketMiktar());

		// Update işlemi ise eski kayıt sorulanıp farkı alınarak işlem yapıldı
		if (theEntity.getEnvanterHareketId() != 0) {
			EnvanterHareket oldEnvanterHareket = findById(theEntity.getEnvanterHareketId());
			BigDecimal eskiHareketMiktar = lib.bigdecimalZero(oldEnvanterHareket.getHareketMiktar());
			hareketMiktar = hareketMiktar.subtract(eskiHareketMiktar);

			if (theEntity.getDepoId() != oldEnvanterHareket.getDepoId()) {
				throw new RuntimeException("DEPO update edilemez, kayıt silinip eklenmeli");
			}

			if (theEntity.getEnvanterId() != oldEnvanterHareket.getEnvanterId()) {
				throw new RuntimeException("ENVNATER update edilemez, kayıt silinip eklenmeli");
			}

		}

		miktarUpdate(theEntity, hareketMiktar);

		EnvanterHareket result = entityManager.merge(theEntity);
		return result;
	}

	@Override
	public void deleteById(int theId) {

		EnvanterHareket result = entityManager.find(EnvanterHareket.class, theId);
		BigDecimal hareketMiktar = lib.bigdecimalZero(result.getHareketMiktar());
		hareketMiktar = hareketMiktar.multiply(new BigDecimal(-1));
		System.out.println("hareketMiktar" + hareketMiktar);

		miktarUpdate(result, hareketMiktar);

		entityManager.remove(result);
	}

	public Envanter findByIdEnvanter(int theId) {
		Envanter result = entityManager.find(Envanter.class, theId);
		return result;
	}

	public DepoEnvanter findByIdDepoEnvanter(int depoId, int envanterId) {
		TypedQuery<DepoEnvanter> theQuery = entityManager.createQuery(
				" from DepoEnvanter where depoId = :depoId and envanterId = :envanterId ", DepoEnvanter.class);
		theQuery.setParameter("depoId", depoId).setParameter("envanterId", envanterId);
		DepoEnvanter result = theQuery.getResultList().get(0);

		if (theQuery.getResultList().size() > 1) {
			throw new RuntimeException("Envanter Depo birden fazla olmaz = " + theQuery.getResultList().size());
		}
		return result;
	}

	private void miktarUpdate(EnvanterHareket theEntity, BigDecimal hareketMiktar) {
		// Envanter kayıtı sorgulandı
		Envanter envanter = findByIdEnvanter(theEntity.getEnvanterId());
		BigDecimal envanterMiktar = lib.bigdecimalZero(envanter.getEnvanterMiktar());
		BigDecimal kritikMiktar = lib.bigdecimalZero(envanter.getKritikMiktar());
		BigDecimal sonMiktar = envanterMiktar.add(hareketMiktar);

		// hareket tarihi boş ise set edildi
		if (theEntity.getHareketTarih() == null) {
			theEntity.setHareketTarih(new Date());
		}

		// Hareket türü set edildi
		if (hareketMiktar.compareTo(new BigDecimal(0)) >= 0) {
			theEntity.setHareketTuru("Giriş");
		} else {
			theEntity.setHareketTuru("Cıkış");
		}

		// Depo envanter kayıtı sorgulandı
		DepoEnvanter depoEnvanter = findByIdDepoEnvanter(theEntity.getDepoId(), theEntity.getEnvanterId());
		BigDecimal depoMiktar = lib.bigdecimalZero(depoEnvanter.getDepoMiktar());
		BigDecimal depoSonMiktar = depoMiktar.add(hareketMiktar);

		// yeni kayıt ise insert
		if (sonMiktar.compareTo(new BigDecimal(0)) < 0) {
			System.out.println("Miktar eksiye düşümez!!! kontrol = " + sonMiktar);
			throw new RuntimeException("Miktar eksiye düşümez!!! kontrol = " + sonMiktar);
		}

		if (sonMiktar.compareTo(kritikMiktar) <= 0) {
			System.out.println("Kritik miktara ulaşıldı. Mevcu miktar = " + envanterMiktar.add(hareketMiktar)
					+ ", kritik miktar = " + kritikMiktar);
		}

		if (depoSonMiktar.compareTo(new BigDecimal(0)) < 0) {
			System.out.println("Depo Miktar eksiye düşümez!!! kontrol = " + depoSonMiktar);
			throw new RuntimeException("Miktar eksiye düşümez!!! kontrol = " + depoSonMiktar);
		}

		// Depo miktarlar update edilecek
		depoEnvanter.setDepoMiktar(depoSonMiktar);
		entityManager.merge(depoEnvanter);

		// envanter miktar update edilecek
		envanter.setEnvanterMiktar(sonMiktar);
		entityManager.merge(envanter);
	}
}
