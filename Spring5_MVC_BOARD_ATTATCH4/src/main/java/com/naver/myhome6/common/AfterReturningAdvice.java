package com.naver.myhome6.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Service;


//@Service
//@Aspect
public class AfterReturningAdvice {
//	@Pointcut("execution(* com.naver.myhome6..*Impl.*(..))")
//	public void getPointcut() {}
	
//	@org.aspectj.lang.annotation.AfterReturning("execution(* com.naver.myhome6..*Impl.*(..))")
	@AfterReturning(pointcut="execution(* com.naver.myhome6..*Impl.*(..))", returning="obj")
	public Object afterReturning(JoinPoint proceeding, Object obj) {
		System.out.println("[AfterReturning] : " + proceeding.getTarget().getClass().getSimpleName());
		System.out.println("obj : " + obj.toString());
		
		return obj;
	}
}
