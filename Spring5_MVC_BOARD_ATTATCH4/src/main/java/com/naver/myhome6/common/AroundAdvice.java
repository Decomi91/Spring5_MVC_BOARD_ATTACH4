package com.naver.myhome6.common;


import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/*
 	ProceedingJoinPoint 인터페이스는 JoinPoint를 상속했기 때문에 JoinPoint의 모든 메소드 사용 가능
 	Around Advice에서만 ProceedingJoinPoint를 매개변수로 사용, proceed() 메소드가 필요해서 ㅇㅇ.
 	Around Advice인 경우 비즈니스 메소드 실행 전과 후에 실행되는데 비즈니스 메소드를 호출하려면 proceed() 메소드 필요.
 	즉, 클라이언트 요청을 가로챈 어드바이스는 클라이언트가 호출한 비즈니스 메소드를 호출하기 위해 ProceedingJoinPoint 객체를 매개변수로 받아
 	proceed() 메소드를 통해서 비즈니스 메소드를 호출.
 	proceed() 메소드 실행 후 메소드의 반환값을 리턴해야한다.
 */
//@Service
//@Aspect
public class AroundAdvice {
//	@Pointcut("execution(* com.naver.myhome6..*Impl.*(..))")
//	public void getPointcut() {}
	
	@Around("execution(* com.naver.myhome6..*Impl.*(..))")
	public Object around(ProceedingJoinPoint proceeding) throws Throwable{
		System.out.println("[AroundAdvice의 before] : 비즈니스 메소드 수행 전");
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = proceeding.proceed();
		sw.stop();
		System.out.println("[AroundAdvice의 after] : 비즈니스 메소드 수행 후");
		Signature sig = proceeding.getSignature();
		System.out.printf("[AroundAdvice의 after] : %s.%s(%s)\n", proceeding.getTarget().getClass().getSimpleName(), sig.getName(), Arrays.toString(proceeding.getArgs()));
		System.out.println("[AroundAdvice의 after] : " + proceeding.getSignature().getName() + "() 메소드 수행 시간 : " + sw.getTotalTimeMillis() + "(ms)초");
		System.out.println("[AroundAdvice의 after] : " + proceeding.getTarget().getClass().getName());
		System.out.println("proceeding.proceed() 실행 후 반환값 : " + result);
		return result;
		//return null; -> 오류남
	}
}
