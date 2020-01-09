package com.naver.myhome6.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.myhome6.dao.CommentDAO;
import com.naver.myhome6.domain.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDAO cDao;
	
	@Override
	public List<Comment> commentList(int board_re_ref) {
//		log.beforeLog();
		return cDao.getList(board_re_ref);
	}

	@Override
	public int commentWrite(Comment co) {
		return cDao.write(co);
	}

	@Override
	public int getListCount(int num) {
//		log.beforeLog();
		return cDao.getListCount(num);
	}

	@Override
	public int commentDelete(int num) {
		return cDao.delete(num);
	}

	@Override
	public int commentUpdate(int num, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("content", content);
		return cDao.update(map);
	}

}
