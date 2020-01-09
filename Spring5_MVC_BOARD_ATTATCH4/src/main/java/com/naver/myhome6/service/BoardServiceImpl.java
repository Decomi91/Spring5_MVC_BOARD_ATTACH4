package com.naver.myhome6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.myhome6.dao.BoardDAO;
import com.naver.myhome6.domain.Board;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO bDao;
	
	@Override
	public List<Board> getList(int page, int limit) {

		Map<String, Integer> paging = new HashMap<String, Integer>();
		
		int startrow = (page-1)*limit + 1;
		int endrow = startrow+limit-1;
		
		paging.put("start", startrow);
		paging.put("end", endrow);
		
		return bDao.getList(paging);
	}

	@Override
	public int getListCount() {
		return bDao.getListCount();
	}

	@Override
	public Board getDetail(int num) {
		return bDao.getDetail(num);
	}

	@Override
	@Transactional
	public int boardReply(Board board) {
		bDao.replyBoardBefore(board);
//		double e = 1/0;
		board.setBOARD_RE_LEV(board.getBOARD_RE_LEV()+1);
		board.setBOARD_RE_SEQ(board.getBOARD_RE_SEQ()+1);
		return bDao.replyBoard(board);
	}

	@Override
	public int boardModify(Board modifyboard) {
		return bDao.modifyBoard(modifyboard);
	}

	@Override
	public int boardDelete(int num) {
		return bDao.deleteBoard(num);
	}

	@Override
	public void setReadCountUpdate(int num) {
		bDao.readCountUpdate(num);
		
	}

	@Override
	public boolean isBoardWriter(int num, String pass) {
		Map<String, Object> ip = new HashMap<String, Object>();
		ip.put("num", num);
		ip.put("pass", pass);
		return bDao.isBoardWriter(ip);
	}

	@Override
	public void insertBoard(Board board) {
		bDao.insertBoard(board);
	}

	@Override
	public void boardDeleteTemp(String board_FILE) {
		bDao.deleteBoardTemp(board_FILE);
		
	}

	@Override
	public List<String> getFileList() {
		return bDao.getFileList();
	}

	@Override
	public void boardDeleteTempDelete() {
		bDao.deleteBoardTempDelete();
	}
	

}
