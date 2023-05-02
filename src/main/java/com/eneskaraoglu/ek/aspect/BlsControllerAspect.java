package com.eneskaraoglu.ek.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BlsControllerAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.eneskaraoglu.ek.services.*.*(..))")
	public void forAllServices() {}
	
	@Pointcut("execution(* com.eneskaraoglu.ek.rest.*.*(..))")
	public void forAllRest() {}
	
	@Pointcut("execution(* com.eneskaraoglu.ek.repository.*.*(..))")
	public void forAllRepostory() {}
	
	@Pointcut("execution(* com.eneskaraoglu.ek.dao.*.*(..))")
	public void forAllDao() {}
	

	
	@Around(value = "forAllServices() || forAllRest() || forAllRepostory()  || forAllDao()")	
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		//myLogger.info("\n=====>>> Executing @Around on method: " + method+"==========="+theProceedingJoinPoint.getTarget());
		
		// get args
		Object[] args = theProceedingJoinPoint.getArgs();
		
		// loop thru args
		if (args!=null) {
			for (Object tempArg : args) {
				if (tempArg!=null) {
					myLogger.info("\n"+"========>"+method+" param: "+tempArg.toString());
					
					/*if (tempArg instanceof Account) {
						
						// downcast and print Account specific stuff
						Account theAccount = (Account) tempArg;
						
						myLogger.info("account name: " + theAccount.getName());
						myLogger.info("account level: " + theAccount.getLevel());								

					}*/
				}

			}
		}

		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = null;
		
		try {
			result = theProceedingJoinPoint.proceed();
		} catch (Exception e) {
			// log the exception
			myLogger.warning(e.getMessage());

			// rethrow exception
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		
		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n"+""+theProceedingJoinPoint.getTarget()+"\n"+ "========>"+method+" Duration: " + duration / 1000.0 + " seconds \n"
		);
		
		return result;
	}
	

}
