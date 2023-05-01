package com.eneskaraoglu.ek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eneskaraoglu.ek.entity.Depo;
import com.eneskaraoglu.ek.entity.DepoEnvanter;
import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.entity.EnvanterCikis;

public interface EnvanterCikisRepository extends JpaRepository<EnvanterCikis, Integer> {

}
