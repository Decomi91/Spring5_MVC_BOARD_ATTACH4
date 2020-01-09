package com.naver.myhome6.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
/*
	JoinPoint 인터페이스 메소드
	1. Signature getSignature() : 호출되는 메소드에 대한 정보
	2. Object getTarget() : 호출한 비즈니스 메소드를 포함하는 비즈니스 객체를 구함
	3. getClass().getName() : 클래스이름
	4. proceeding.getSignature().getName : 호출되는 메소드의 이름을 구함
 */
//@Service
//@Aspect
public class AfterAdvice {
	@After("execution(* com.naver.myhome6..*Impl.*(..))")
	public void after(JoinPoint proceeding) {
		System.out.println("[AfterAdvice] : 비즈니스 로직 수행 후 동작");
		System.out.println("[AfterAdvice] : " + proceeding.getTarget().getClass().getName() + "의 " + proceeding.getSignature().getName() + " 호출");
		System.out.println("");
	}
}
