package com.naver.myhome6.common;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

//@Service
//@Aspect
public class AfterThrowingAdvice {
	@AfterThrowing(pointcut="execution(* com.naver.myhome6..*Impl.*(..))", throwing = "exp")
	public void afterThrowing(Throwable exp) {
		System.out.println("[AfterThrowing] : 비즈니스 로직 중 오류");
		System.out.println("ex : " + exp.toString());
	}
}
