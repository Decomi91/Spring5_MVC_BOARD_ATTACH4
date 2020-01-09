package com.naver.myhome6.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
/*
 * @ControllerAdvice ������̼� : ��ü�� ��Ʈ�ѷ����� �߻��ϴ� Exception�� ó��
 * 
 * 1. Ŭ������ @ControllerAdvice ������̼��߰�
 * 2. �� �޼ҵ忡 @Exceptino Handler ����ؼ� ������ Exception �ۼ�
 */
@ControllerAdvice
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class) //Exception�� ���� ��� ����ó��
	public ModelAndView common(Exception e, HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/common");
		mv.addObject("exception", e);
		mv.addObject("url", request.getRequestURL());
		
		return mv;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class) //Exception�� ���� ��� ����ó��
	public ModelAndView handleError404(Exception e, HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/404");
		mv.addObject("message", "404����");
		mv.addObject("url", request.getRequestURL());
		
		return mv;
	}
}
