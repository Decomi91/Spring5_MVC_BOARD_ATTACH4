package com.naver.myhome6.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.myhome6.domain.Board;

@Repository
public class BoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public List<Board> getList(Map paging) {
		return sqlSession.selectList("Boards.getlist", paging);
	}

	public int getListCount() {
		return sqlSession.selectOne("Boards.getlistcount");
	}

	public Board getDetail(int num) {
		return sqlSession.selectOne("Boards.getdetail", num);
	}

	public void insertBoard(Board board) {
		sqlSession.insert("Boards.insert", board);
		
	}

	public void readCountUpdate(int num) {
		sqlSession.update("Boards.increasereadcount",num);
	}

	public int deleteBoard(int num) {
		return sqlSession.delete("Boards.delete", num);
	}

	public boolean isBoardWriter(Map<String, Object> ip) {
		return sqlSession.selectOne("Boards.isboardwriter", ip);
	}

	public int modifyBoard(Board modifyboard) {
		return sqlSession.update("Boards.modifyboard", modifyboard);
	}

	public int replyBoard(Board board) {
		return sqlSession.insert("Boards.replyboard", board);
	}

	public int replyBoardBefore(Board board) {
		return sqlSession.update("Boards.replyboardbefore", board);
	}

	public void deleteBoardTemp(String board_FILE) {
		sqlSession.update("Boards.deletetemp", board_FILE);
	}

	public List<String> getFileList() {
		return sqlSession.selectList("Boards.getfilelist");
	}

	public void deleteBoardTempDelete() {
		sqlSession.delete("Boards.deletetempdelete");
		
	}

}
