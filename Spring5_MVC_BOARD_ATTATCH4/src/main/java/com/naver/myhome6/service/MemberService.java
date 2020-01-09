package com.naver.myhome6.service;

import java.util.List;
import java.util.Map;

import com.naver.myhome6.domain.Board;
import com.naver.myhome6.domain.Member;

public interface MemberService {
	public int isId(String id, String pass);
	public int isId(String id);
	public int insert(Map<String, String> member);
	public int insert(Member member);
	public Member member_info(String id);
	public void delete(String id);
	public int update(Member m);
	public List<Member> getSearchList(int search_field, String search_word, int page, int limit);
	public int getSearchListCount(int search_field, String search_word);
	public int getListCount();
	public List<Member> getList(int page, int limit);
	public Member getDetail(Object id);
}
