package com.naver.myhome6.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome6.domain.MailVO;
import com.naver.myhome6.domain.Member;
import com.naver.myhome6.service.MemberService;
import com.naver.myhome6.task.SendMail;

/*
 	@Component를 이용해서 스프링컨테이너가 해당 클래스 객체를 생성하도록 설ㄹ정할 수 있지만
 	모든 클래스에 @Component를 할당하면 어떤 클래스가 어떤 역할을 수행하는지 파악하기 어렵다.
 	스프링 프레임워크에서는 이런 클래스들을 분류하기 위해서 @Component를 상속하여 다음과 같은 세 개의 애노테이션을 제공한다..
 	
 	 1. @Controller - 사용자의 요청을 제어하는 Controller 클래스
 	 2. @Repository - 데이터베이스 연동을 처리하는 DAO 클래스
 	 3. @Service - 비즈니스 로직을 처리하는 Service 클래스
 */

@Controller
public class MemberController {
	@Autowired
	private MemberService memberservice;
	
	@Autowired
	private SendMail sendMail;
	
	//로그인 폼 이동
	@RequestMapping(value="/login.net", method=RequestMethod.GET)
	public ModelAndView login(ModelAndView mv, @CookieValue(value="saveid", required = false) Cookie readCookie) throws Exception {
		if(readCookie != null) {
			mv.addObject("saveid", readCookie.getValue());
		}
		mv.setViewName("member/loginForm");
		return mv;
	}
	
	@RequestMapping(value="/join.net", method=RequestMethod.GET)
	public String join() {
		return "member/joinForm";
	}
	
	@RequestMapping(value="/logout.net", method=RequestMethod.GET)
	public String join(HttpServletRequest request) throws Exception {
		request.getSession().invalidate();
		return "redirect:/login.net";
	}
	
	@RequestMapping(value="/idcheck.net", method=RequestMethod.GET)
	public void idCheck(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
		int result = memberservice.isId(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(result);
	}
	
	public void joinPro(@RequestParam Map<String,String> member,  HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		int result = memberservice.insert(member);
		PrintWriter out = response.getWriter();
		out.print("<script>");
		if(result==1) {
			out.println("alert('회원가입 축하');");
			out.print("location.href='login.net';");
		}else if(result == -1) {
			out.print("alert('아이디 중복');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.close();
	}
	
	@RequestMapping(value="/joinProcess.net", method=RequestMethod.POST)
	public void joinPro(Member member,  HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		int result = memberservice.insert(member);
		PrintWriter out = response.getWriter();
		out.print("<script>");
		if(result==1) {
			MailVO vo = new MailVO();
			vo.setTo(member.getEmail());
			vo.setContent(member.getId()+"님 회원가입 축하드립니다.");
			sendMail.sendMail(vo);
			
			out.println("alert('회원가입 축하');");
			out.print("location.href='login.net';");
		}else if(result == -1) {
			out.print("alert('아이디 중복');");
			out.print("history.back();");
		}
		out.print("</script>");
		out.close();
	}
	
	@RequestMapping(value="/loginProcess.net", method=RequestMethod.POST)
	public String loginPro(String id, String pass, String remember,
							HttpServletResponse response, HttpSession session, HttpServletRequest request) throws Exception {
		
		int result = memberservice.isId(id, pass);
		System.out.println("결과는 : "+result);
		
		if(result == 1) {
			Cookie cookie = new Cookie("saveid", id);
			if(remember != null) {
				cookie.setMaxAge(60*60);
			}else {
				cookie.setMaxAge(0);
			}
			response.addCookie(cookie);
			session.setAttribute("id", id);
			return "redirect:BoardList.bo";
		}else {
			String message = "비밀번호가 일치하지 않습니다.";
			if(result == -1) {
				message = "아이디가 존재하지 않습니다.";
			}
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("<script>alert('" +  message + "'); location.href='login.net'; </script>");
			return null;
		}
	}
	
	@GetMapping(value="/member_list.net")
	public ModelAndView memberList(@RequestParam(value="page", defaultValue = "1", required = false)int page, 
			@RequestParam(value="search_field", defaultValue = "-1", required = false) int search_field, String search_word, ModelAndView mv) {
		
		int listcount = 0;
		if(search_word == null || search_word.equals("")) {
			listcount =memberservice.getListCount();
		}else {
			listcount = memberservice.getSearchListCount(search_field, search_word);
		}
		
		int limit = 10;
		
		//페이지 그룹 10개씩 보여주므로 최대치를 10개로 잡은
		int maxpage = (listcount+limit-1)/limit;
		
		int startpage = ((page-1)/limit)*limit + 1;
		int endpage = ((startpage) + limit -1);
		
		if(endpage > maxpage) endpage = maxpage;
		List<Member> memberlist;
		if(search_word == null || search_word.equals("")) {
			memberlist = memberservice.getList(page, limit);
		}else {
			memberlist = memberservice.getSearchList(search_field, search_word, page, limit);
		}
		
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);	// 총 글의 수1
		mv.addObject("members", memberlist);
		mv.addObject("limit", limit);
		if(search_word != null && !search_word.equals("")) {
			mv.addObject("search_field", search_field);
			mv.addObject("search_word", search_word);
		}
		mv.setViewName("member/memberListForm");
		return mv;
	}
	
	@GetMapping(value="/memberDeleteProcess.net")
	public String memberDelete(String id) {
		memberservice.delete(id);
		return "redirect:/member_list.net";
	}
	
	@GetMapping(value="/member_update.net")
	public ModelAndView memberUpdate(ModelAndView mv, HttpServletRequest request) throws Exception {
		Member member =  memberservice.getDetail(request.getSession().getAttribute("id"));
		mv.addObject("member", member);
		mv.setViewName("member/updateForm");
		return mv;
	}
	
	@PostMapping(value="/updateProcess.net")
	public ModelAndView memberUpdateAction(Member member, ModelAndView mv, HttpServletRequest request) throws Exception {
		int result = memberservice.update(member);
		if(result == 1) {
			mv.addObject("member", member);
			mv.setViewName("member/updateForm");
		} else {
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message", "정보수정 실패");
			mv.setViewName("error/error");
		}
		return mv;
	}
	
	@GetMapping(value="/member_info.net")
	public ModelAndView memberInfo(String id, ModelAndView mv) {
		Member member = memberservice.getDetail(id);
		mv.addObject("member",member);
		mv.setViewName("member/memberInfoForm");
		return mv;
	}
	
}
