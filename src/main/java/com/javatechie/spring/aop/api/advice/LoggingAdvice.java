package com.javatechie.spring.aop.api.advice;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);
	
	@Pointcut(value="execution(* com.javatechie.spring.aop.api.controller.ProductController.saveProducts(*) )")
	public void myPointcutPost() {
		
	}
	@Pointcut(value="execution(* com.javatechie.spring.aop.api.controller.ProductController.getProducts() )")
	public void myPointcutGet() {
		
	}
	
	@Around("myPointcutPost()")
	public Object applicationLoggerPost(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		Object[] array = pjp.getArgs();
		log.info("method invoked POST " + className + " : " + methodName + "()" + "arguments : "
				+ mapper.writeValueAsString(array));
		Object object = pjp.proceed();
		log.info("Response POST "+className + " : " + methodName + "()" + "Response : "
				+ mapper.writeValueAsString(object));
		return object;
	}
	@AfterReturning(pointcut= "myPointcutGet()", returning = "redVal")
	public Object applicationLoggerGet(JoinPoint pjp,Object redVal) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();
		
		
		log.info("Response GET "+className + " : " + methodName + "()" + "Response : "
				+ mapper.writeValueAsString(redVal));
		return redVal;
		
	}
	

}
