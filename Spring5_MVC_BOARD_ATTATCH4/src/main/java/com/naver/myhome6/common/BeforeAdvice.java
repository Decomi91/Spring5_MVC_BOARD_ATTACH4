package com.naver.myhome6.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;
/*
	JoinPoint �������̽� �޼ҵ�
	1. Signature get Signature() : ȣ��Ǵ� �޼ҵ忡 ���� ����
	2. Object getTarget() : ȣ���� ����Ͻ� �޼ҵ带 �����ϴ� ����Ͻ� ��ü�� ����
	3. getClass().getName() : Ŭ�����̸�
	4. proceeding.getSignature().getName : ȣ��Ǵ� �޼ҵ��� �̸��� ����
 */
//@Service
//@Aspect
public class BeforeAdvice {
//	@Pointcut("execution(* com.naver.myhome6..*Impl.get*(..))")
//	public void getPointcut() {}
	
	@Before("execution(* com.naver.myhome6..*Impl.get*(..))")
	public void before(JoinPoint proceeding) {
		System.out.println("[BeforeAdvice] : ����Ͻ� ���� ���� �� ����");
		System.out.println("[BeforeAdvice] : " + proceeding.getTarget().getClass().getName() + "�� " + proceeding.getSignature().getName() + " ȣ��");
	}
}
