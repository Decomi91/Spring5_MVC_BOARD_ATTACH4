package com.naver.myhome6.service;

import java.util.List;

import com.naver.myhome6.domain.Comment;

public interface CommentService {
	public List<Comment> commentList(int board_re_ref);

	public int commentWrite(Comment co);

	public int getListCount(int num);

	public int commentDelete(int num);

	public int commentUpdate(int num, String content);
}
