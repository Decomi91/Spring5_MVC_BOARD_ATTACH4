package com.naver.myhome6.controller;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
/*
 * @ControllerAdvice 어노테이션 : 객체가 컨트롤러에서 발생하는 Exception을 처리
 * 
 * 1. 클래스에 @ControllerAdvice 어노테이션추가
 * 2. 각 메소드에 @Exceptino Handler 사용해서 적절한 Exception 작성
 */
@ControllerAdvice
public class ExceptionController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler(Exception.class) //Exception에 대한 모든 예외처리
	public ModelAndView common(Exception e, HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/common");
		mv.addObject("exception", e);
		mv.addObject("url", request.getRequestURL());
		
		return mv;
	}
	
	@ExceptionHandler(NoHandlerFoundException.class) //Exception에 대한 모든 예외처리
	public ModelAndView handleError404(Exception e, HttpServletRequest request) {
		logger.info(e.toString());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error/404");
		mv.addObject("message", "404오류");
		mv.addObject("url", request.getRequestURL());
		
		return mv;
	}
}
