package com.eneskaraoglu.ek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eneskaraoglu.ek.entity.Depo;
import com.eneskaraoglu.ek.entity.DepoEnvanter;
import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.entity.EnvanterCikis;
import com.eneskaraoglu.ek.entity.EnvanterLog;

public interface EnvanterLogRepository extends JpaRepository<EnvanterLog, Integer> {

}
