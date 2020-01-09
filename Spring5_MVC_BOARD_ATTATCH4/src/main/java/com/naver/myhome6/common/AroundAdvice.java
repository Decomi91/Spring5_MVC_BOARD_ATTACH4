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
 	ProceedingJoinPoint �������̽��� JoinPoint�� ����߱� ������ JoinPoint�� ��� �޼ҵ� ��� ����
 	Around Advice������ ProceedingJoinPoint�� �Ű������� ���, proceed() �޼ҵ尡 �ʿ��ؼ� ����.
 	Around Advice�� ��� ����Ͻ� �޼ҵ� ���� ���� �Ŀ� ����Ǵµ� ����Ͻ� �޼ҵ带 ȣ���Ϸ��� proceed() �޼ҵ� �ʿ�.
 	��, Ŭ���̾�Ʈ ��û�� ����æ �����̽��� Ŭ���̾�Ʈ�� ȣ���� ����Ͻ� �޼ҵ带 ȣ���ϱ� ���� ProceedingJoinPoint ��ü�� �Ű������� �޾�
 	proceed() �޼ҵ带 ���ؼ� ����Ͻ� �޼ҵ带 ȣ��.
 	proceed() �޼ҵ� ���� �� �޼ҵ��� ��ȯ���� �����ؾ��Ѵ�.
 */
//@Service
//@Aspect
public class AroundAdvice {
//	@Pointcut("execution(* com.naver.myhome6..*Impl.*(..))")
//	public void getPointcut() {}
	
	@Around("execution(* com.naver.myhome6..*Impl.*(..))")
	public Object around(ProceedingJoinPoint proceeding) throws Throwable{
		System.out.println("[AroundAdvice�� before] : ����Ͻ� �޼ҵ� ���� ��");
		
		StopWatch sw = new StopWatch();
		sw.start();
		
		Object result = proceeding.proceed();
		sw.stop();
		System.out.println("[AroundAdvice�� after] : ����Ͻ� �޼ҵ� ���� ��");
		Signature sig = proceeding.getSignature();
		System.out.printf("[AroundAdvice�� after] : %s.%s(%s)\n", proceeding.getTarget().getClass().getSimpleName(), sig.getName(), Arrays.toString(proceeding.getArgs()));
		System.out.println("[AroundAdvice�� after] : " + proceeding.getSignature().getName() + "() �޼ҵ� ���� �ð� : " + sw.getTotalTimeMillis() + "(ms)��");
		System.out.println("[AroundAdvice�� after] : " + proceeding.getTarget().getClass().getName());
		System.out.println("proceeding.proceed() ���� �� ��ȯ�� : " + result);
		return result;
		//return null; -> ������
	}
}
