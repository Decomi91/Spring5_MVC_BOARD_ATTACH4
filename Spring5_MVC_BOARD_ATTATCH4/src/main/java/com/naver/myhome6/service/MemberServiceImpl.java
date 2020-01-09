package com.naver.myhome6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome6.dao.MemberDAO;
import com.naver.myhome6.domain.Board;
import com.naver.myhome6.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO dao;
	@Override
	public int isId(String id, String pass) {
		Member rmember = dao.isId(id);
		if(rmember != null) {
			if(rmember.getPassword().equals(pass)) {
				return 1;
			}else {
				return 0;
			}
		}
		return -1;
	}

	@Override
	public int isId(String id) {
		Member rmember = dao.isId(id);
		return (rmember == null ) ? -1 : 1;
	}

	@Override
	public int insert(Map<String,String> m) {
		return dao.insert(m);
	}

	@Override
	public int insert(Member member) {
		return dao.insert(member);
	}

	@Override
	public Member member_info(String id) {
		return dao.member_info(id);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public int update(Member m) {
		return dao.updateMember(m);
	}

	@Override
	public List<Member> getSearchList(int index, String search_word, int page, int limit) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startrow = (page-1)*limit + 1;
		int endrow = startrow+limit-1;
		
		map.put("search", index);
		map.put("word", search_word);
		map.put("start", startrow);
		map.put("end", endrow);
		
		return dao.getSearchList(map);
	}

	@Override
	public int getSearchListCount(int index, String search_word) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", index);
		map.put("word", search_word);
		
		return dao.getSearchListCount(map);
	}

	public MemberDAO getDao() {
		return dao;
	}

	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}

	@Override
	public int getListCount() {
		return dao.getListCount();
	}

	@Override
	public List<Member> getList(int page, int limit) {
		Map<String, Integer> paging = new HashMap<String, Integer>();
		
		int startrow = (page-1)*limit + 1;
		int endrow = startrow+limit-1;
		
		
		paging.put("start", startrow);
		paging.put("end", endrow);
		
		return dao.getList(paging);
	}

	@Override
	public Member getDetail(Object id) {
		return dao.getDetail((String)id);
	}

}
