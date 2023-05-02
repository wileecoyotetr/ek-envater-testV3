package com.eneskaraoglu.ek.aspect;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.eneskaraoglu.ek.entity.BaseEntity;
import com.eneskaraoglu.ek.entity.Envanter;
import com.eneskaraoglu.ek.entity.EnvanterLog;
import com.eneskaraoglu.ek.lib.Lib;
import com.eneskaraoglu.ek.repository.EnvanterLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Entity;

@Aspect
@Component
public class EkControllerAspect {

	private EnvanterLogRepository envanterLogRepository;
	
	Lib lib = new Lib();
	
	public EkControllerAspect(EnvanterLogRepository envanterLogRepository) {
		this.envanterLogRepository = envanterLogRepository;
	}



	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.eneskaraoglu.ek.services.*.*(..))")
	public void forAllServices() {}
	
	@Pointcut("execution(* com.eneskaraoglu.ek.rest.*.*(..))")
	public void forAllRest() {}
	
	@Pointcut("execution(* com.eneskaraoglu.ek.repository.*.*(..))")
	public void forAllRepostory() {}
	
	@Pointcut("execution(* com.eneskaraoglu.ek.dao.*.*(..))")
	public void forAllDao() {}
	
	@Pointcut("execution(* com.eneskaraoglu.ek.repository.EnvanterLogRepository.*(..))")
	public void forNotInRepostory() {}

	
	@Around(value = "(forAllServices() || forAllRest() || forAllRepostory()  || forAllDao() ) &&  !forNotInRepostory()")	
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		//myLogger.info("\n=====>>> Executing @Around on method: " + method+"==========="+theProceedingJoinPoint.getTarget());
		
		// get args
		Object[] args = theProceedingJoinPoint.getArgs();
		EnvanterLog log = new EnvanterLog();
		log.setEnvanterLogId(0);
		log.setLogTarih(lib.getSysDate());
		log.setLogMethod(method);
		
		String parameters = "";
		// loop thru args
		if (args!=null) {
			for (Object tempArg : args) {
				if (tempArg!=null) {
					
					if (tempArg instanceof BaseEntity) {

						  //myLogger.info("\n"+"========>"+method+"---getClass:"+tempArg.getClass()+" param: "+ lib.jsonString(tempArg));	
						  parameters = parameters.concat("/**/").concat(tempArg.getClass().toString()).concat(":").concat(lib.jsonString(tempArg));
					}
					else {
						//myLogger.info("\n"+"========>"+method+"---getClass:"+tempArg.getClass()+" param: "+ tempArg.toString());					
						parameters = parameters.concat("/**/").concat(tempArg.toString());
					}
				}
			}
		}
		log.setLogParametre(parameters);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
			if (result != null && result instanceof BaseEntity) {
				  log.setLogReturn(result.getClass().toString().concat(":").concat(lib.jsonString(result)));	
			}
			else {
				log.setLogReturn(lib.str(result));
			}
				
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());
			//myLogger.info("\n"+"========>"+e.getMessage());
			log.setLogError(e.getMessage().toString());
			envanterLogRepository.save(log);
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n"+""+theProceedingJoinPoint.getTarget()+"\n"+ "========>"+method+" Duration: " + duration / 1000.0 + " seconds \n"
		);
		
		
		log.setLogSureSn(BigDecimal.valueOf(duration / 1000.0 ));
		envanterLogRepository.save(log);
		
		return result;
	}
	

}
