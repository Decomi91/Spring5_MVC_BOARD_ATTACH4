package com.naver.myhome6.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;
/*
	JoinPoint �������̽� �޼ҵ�
	1. Signature getSignature() : ȣ��Ǵ� �޼ҵ忡 ���� ����
	2. Object getTarget() : ȣ���� ����Ͻ� �޼ҵ带 �����ϴ� ����Ͻ� ��ü�� ����
	3. getClass().getName() : Ŭ�����̸�
	4. proceeding.getSignature().getName : ȣ��Ǵ� �޼ҵ��� �̸��� ����
 */
//@Service
//@Aspect
public class AfterAdvice {
	@After("execution(* com.naver.myhome6..*Impl.*(..))")
	public void after(JoinPoint proceeding) {
		System.out.println("[AfterAdvice] : ����Ͻ� ���� ���� �� ����");
		System.out.println("[AfterAdvice] : " + proceeding.getTarget().getClass().getName() + "�� " + proceeding.getSignature().getName() + " ȣ��");
		System.out.println("");
	}
}
