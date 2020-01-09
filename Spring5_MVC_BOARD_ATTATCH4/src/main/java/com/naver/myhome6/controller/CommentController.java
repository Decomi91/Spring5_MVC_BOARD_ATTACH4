package com.naver.myhome6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.myhome6.domain.Comment;
import com.naver.myhome6.service.CommentService;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentservice;
	
	@ResponseBody
	@PostMapping(value="/CommentList.bo")
	public List<Comment> commentList(@RequestParam(value="BOARD_RE_REF") int board_re_ref) throws Exception{
		List<Comment> list = commentservice.commentList(board_re_ref);
		return list;
	}
	
	@ResponseBody
	@PostMapping(value="/CommentAdd.bo",produces = "application/text;charset=utf-8")
	public String commentWrite(Comment co) throws Exception {
		int result = commentservice.commentWrite(co);
		return result+"";
	}
	
	@ResponseBody
	@PostMapping(value="/CommentDelete.bo",produces = "application/text;charset=utf-8")
	public String commentDelete(@RequestParam(value="num") int num) throws Exception {
		int result = commentservice.commentDelete(num);
		return result+"";
	}
	
	@ResponseBody
	@PostMapping(value="/CommentUpdate.bo",produces = "application/text;charset=utf-8")
	public String commentUpdate(@RequestParam(value="num") int num, @RequestParam(value="content") String content) throws Exception {
		int result = commentservice.commentUpdate(num, content);
		return result+"";
	}
}
