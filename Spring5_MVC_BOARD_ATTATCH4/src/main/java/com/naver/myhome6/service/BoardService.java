package com.naver.myhome6.service;

import java.util.List;

import com.naver.myhome6.domain.Board;

public interface BoardService {
	public List<Board> getList(int page, int limit);

	public int getListCount();
	public Board getDetail(int num);
	public int boardReply(Board board);
	public int boardModify(Board modifyboard);
	public int boardDelete(int num);
	public void setReadCountUpdate(int num);
	public boolean isBoardWriter(int num, String pass);
	public void insertBoard(Board board);

	public void boardDeleteTemp(String board_FILE);

	public List<String> getFileList();

	public void boardDeleteTempDelete();
}
