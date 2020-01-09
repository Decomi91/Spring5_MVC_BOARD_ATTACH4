package com.naver.myhome6.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome6.domain.Board;
import com.naver.myhome6.domain.Member;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;


	public Member isId(String id) {
		return sqlSession.selectOne("Members.idcheck", id);
	}
	
	public int isId(String id, String pass) {
		return sqlSession.selectOne("Members.isId");
	}

	public int insert(Map<String, String> m) {
		return sqlSession.insert("Members.insert",m);
	}

	public int insert(Member member) {
		return sqlSession.insert("Members.insert2",member);
	}
	
	public Member member_info(String id) {
		return sqlSession.selectOne("Members.member_info", id);
	}

	public void delete(String id) {
		sqlSession.delete("Members.delete", id);
	}

	public int getListCount() {
		return sqlSession.selectOne("Members.getlistcount");
	}

	public List<Member> getList(Map<String, Integer> paging) {
		return sqlSession.selectList("Members.getmemberlist", paging);
	}

	public Member getDetail(String id) {
		return sqlSession.selectOne("Members.getdetail", id);
	}

	public int updateMember(Member m) {
		return sqlSession.update("Members.updatemember", m);
	}

	public int getSearchListCount(Map<String, Object> map) {
		return sqlSession.selectOne("Members.searchlistcount", map);
	}

	public List<Member> getSearchList(Map<String, Object> map) {
		return sqlSession.selectList("Members.searchlist", map);
	}


}
