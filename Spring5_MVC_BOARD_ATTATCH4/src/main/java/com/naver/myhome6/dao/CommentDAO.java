package com.naver.myhome6.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome6.domain.Comment;

@Repository
public class CommentDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Comment> getList(int board_re_ref) {
//		System.out.println(((Comment)sqlSession.selectList("Comments.getlist", board_re_ref).get(0)).getReg_date());
		return sqlSession.selectList("Comments.getlist", board_re_ref);
	}

	public int write(Comment co) {
		return sqlSession.insert("Comments.write", co);
	}

	public int getListCount(int num) {
		return sqlSession.selectOne("Comments.getlistcount",num);
	}

	public int delete(int num) {
		return sqlSession.delete("Comments.delete", num);
	}

	public int update(Map<String, Object> map){
		return sqlSession.update("Comments.update", map);
	}
}
