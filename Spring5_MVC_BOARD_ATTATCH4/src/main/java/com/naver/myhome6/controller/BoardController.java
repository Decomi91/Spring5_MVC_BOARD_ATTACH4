package com.naver.myhome6.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.naver.myhome6.domain.Board;
import com.naver.myhome6.service.BoardService;
import com.naver.myhome6.service.CommentService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardservice;
	
	@Autowired
	private CommentService commentservice;
	
	/*
	 * savefolder.properties
	 * 속성 = 값
	 * 
	 *  savefolder = c:\\~~~~~~
	 */
	@Value("${savefoldername}")
	private String saveFolder;
	
	@RequestMapping(value="/BoardList.bo")
	public ModelAndView boardList(@RequestParam(value="page", defaultValue="1", required = false) int page,
//								@RequestParam(value="limit", defaultValue="10", required = false) int limit,
//								@RequestParam(value="state", required = false) String state, 
								ModelAndView mv) throws Exception {
		
		int listcount = boardservice.getListCount();
		int limit = 10;
		
		//페이지 그룹 10개씩 보여주므로 최대치를 10개로 잡은
		int maxpage = (listcount+limit-1)/limit;
		System.out.println("총 페이지 수 = " + maxpage);
		
		//startpage ~ endpage : 페이지 그룹
		//페이지를 10개 단위로 보여줌
		int startpage = ((page-1)/limit)*limit + 1;
		int endpage = ((startpage) + limit -1);
		
		if(endpage > maxpage) endpage = maxpage;
		
		List<Board> boardlist = boardservice.getList(page, limit);		
		
		mv.addObject("page", page);
		mv.addObject("maxpage", maxpage);
		mv.addObject("startpage", startpage);
		mv.addObject("endpage", endpage);
		mv.addObject("listcount", listcount);	// 총 글의 수1
		mv.addObject("boardlist", boardlist);
		mv.addObject("limit", limit);
		
		mv.setViewName("board/qna_board_list");
		return mv;
		
	}
	
	@ResponseBody
	@PostMapping(value="/BoardList.bo")
	public Map<String, Object> boardList(int page, int limit, String state, 
						ModelAndView mv) throws Exception {
	
		int listcount = boardservice.getListCount();
		
		//페이지 그룹 10개씩 보여주므로 최대치를 10개로 잡은
		int maxpage = (listcount+limit-1)/limit;
		System.out.println("총 페이지 수 = " + maxpage);
		
		//startpage ~ endpage : 페이지 그룹
		//페이지를 10개 단위로 보여줌
		int startpage = ((page-1)/10)*10 + 1;
		int endpage = ((startpage) + 10 -1);
		
		if(endpage > maxpage) endpage = maxpage;
		
		List<Board> boardlist = boardservice.getList(page, limit);		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("page", page);
		map.put("maxpage", maxpage);
		map.put("startpage", startpage);
		map.put("endpage", endpage);
		map.put("listcount", listcount);	// 총 글의 수1
		map.put("boardlist", boardlist);
		map.put("limit", limit);
		return map;
	
	}
	
	@RequestMapping(value="/BoardDetailAction.bo")
	public ModelAndView boardDetail(int num,ModelAndView mv,HttpServletRequest request) {
		boardservice.setReadCountUpdate(num);
		Board board = boardservice.getDetail(num);
		if(board != null) {
			int count = commentservice.getListCount(num);
			mv.addObject("boarddata", board);
			mv.addObject("count", count);
			mv.setViewName("board/qna_board_view");
		}else {
			mv.addObject("message","상세보기 실패");
			mv.addObject("url", request.getRequestURL());
			mv.setViewName("error/error");
		}
		return mv;
	}
	
	@GetMapping(value="/BoardWrite.bo")
	public String boardWrite() throws Exception {
		return "board/qna_board_write";
	}
	
	@PostMapping(value="/BoardAddAction.bo")
	public String boardAdd(Board board, MultipartHttpServletRequest request) throws Exception{
		MultipartFile uploadfile = board.getUploadfile();
		if(!uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename();
			board.setBOARD_ORIGINAL(fileName);
			
			Calendar c = Calendar.getInstance();
//			String saveFolder = "C:\\STS_ee\\Spring5_MVC_BOARD_ATTATCH3\\src\\main\\webapp\\resources\\upload\\";
//					request.getSession().getServletContext().getRealPath("resources")+"/upload/";
			int year = c.get(Calendar.YEAR);
			int month = (c.get(Calendar.MONTH)+1);
			int date = c.get(Calendar.DATE);
			String homedir = saveFolder + year + "-" + month + "-" + date;
			System.out.println(homedir);
			File path1 = new File(homedir);
			if(!path1.exists()) {
				path1.mkdir();
			}
			
			Random r = new Random();
			int random = r.nextInt(100000000);
			
			/*** 확장자 구하기 ***/
			int index = fileName.lastIndexOf(".");
			String fileExtension = fileName.substring(index+1);
			System.out.println("fileExtension = " + fileExtension);
			/*** 확장자 구하기 ***/
			
			String refileName = "bbs"+year+month+date+random+"."+fileExtension;
			System.out.println("refileName = " + refileName);
			
			String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
			System.out.println("fileDBName = " + fileDBName);
			
			uploadfile.transferTo(new File(saveFolder+fileDBName));
			
			board.setBOARD_FILE(fileDBName);
		}
		
		boardservice.insertBoard(board);
		return "redirect:BoardList.bo";
	}

	@PostMapping(value="/BoardDeleteAction.bo")
	public ModelAndView boardDelete(int num, String BOARD_PASS, HttpServletRequest request, ModelAndView mv) throws Exception{
		boolean result = boardservice.isBoardWriter(num, BOARD_PASS);
		if(!result) {
			mv.addObject("message", "비밀번호 오류");
			mv.addObject("url", request.getRequestURL());
			mv.setViewName("error/error");
		}else {
			if(boardservice.getDetail(num).getBOARD_FILE() != null)
				boardservice.boardDeleteTemp(boardservice.getDetail(num).getBOARD_FILE());
			int res = boardservice.boardDelete(num);
			if(res != 1) {
				mv.addObject("message", "삭제 오류");
				mv.addObject("url", request.getRequestURL());
				mv.setViewName("error/error");
			}else {
				mv.setViewName("redirect:BoardList.bo");
			}
		}
		return mv;
	}
	
	@GetMapping(value="/BoardModifyView.bo")
	public ModelAndView boardModify(int num, ModelAndView mv) {
		Board board = boardservice.getDetail(num);
		mv.addObject("board", board);
		mv.setViewName("board/qna_board_modify");
		return mv;
	}
	
	@PostMapping(value="/BoardModifyAction.bo")
	public ModelAndView boardModifyAction(Board board, ModelAndView mv, String before_file,
							HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		boolean result = boardservice.isBoardWriter(board.getBOARD_NUM(), board.getBOARD_PASS());
		
		if(!result) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호 오류'); history.back();</script>");
			return null;
		}
		
		MultipartFile uploadfile = board.getUploadfile();
		
		if(uploadfile != null && !uploadfile.isEmpty()) {//파일을 변경한 경우
			String fileName = uploadfile.getOriginalFilename();
			board.setBOARD_ORIGINAL(fileName);
			
			String fileDBName = fileDBName(fileName, saveFolder);
			System.out.println(fileDBName);
			uploadfile.transferTo(new File(saveFolder+fileDBName));
			
			board.setBOARD_FILE(fileDBName);
		}
		
		int res = boardservice.boardModify(board);
		
		if(res == 1) {
			if(!before_file.equals("") && !before_file.equals(board.getBOARD_ORIGINAL())) {
				boardservice.boardDeleteTemp(before_file);
			}
			mv.setViewName("redirect:/BoardDetailAction.bo?num="+board.getBOARD_NUM());
		}else {
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message","수정중 오류 발생");
			mv.setViewName("error/error");
		}
		return mv;
	}
	
	private String fileDBName(String fileName, String saveFolder) {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = (c.get(Calendar.MONTH)+1);
		int date = c.get(Calendar.DATE);
		
		String homedir = saveFolder + year + "-" + month + "-" + date;
		System.out.println(homedir);
		File path1 = new File(homedir);
		if(!path1.exists()) {
			path1.mkdir();
		} 
		
		Random r = new Random();
		int random = r.nextInt(100000000);
		
		/*** 확장자 구하기 ***/
		int index = fileName.lastIndexOf(".");
		String fileExtension = fileName.substring(index+1);
		System.out.println("fileExtension = " + fileExtension);
		/*** 확장자 구하기 ***/
		
		String refileName = "bbs"+year+month+date+random+"."+fileExtension;
		System.out.println("refileName = " + refileName);
		
		String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName;
		System.out.println("fileDBName = " + fileDBName);
		
		return fileDBName;
	}

	@GetMapping(value="BoardReplyView.bo")
	public ModelAndView boardReply(int num, ModelAndView mv) {
		Board board = boardservice.getDetail(num);
		mv.addObject("board", board);
		mv.setViewName("board/qna_board_reply");
		return mv;
	}
	
	@PostMapping(value="BoardReplyAction.bo")
	public ModelAndView boardReplyAction(Board board, ModelAndView mv) {
		boardservice.boardReply(board);
		mv.setViewName("redirect:/BoardList.bo");
		return mv;
	}
	
	@GetMapping(value="BoardFileDown.bo")
	public void boardFileDown(String filename, String original, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServletContext context = request.getSession().getServletContext();
		
//		File file = new File(context.getRealPath("resources")+"\\upload\\"+filename);
		
		byte b[] = new byte[4096];
		
//		String sFilePath = "C:\\STS_ee\\Spring5_MVC_BOARD_ATTATCH3\\src\\main\\webapp\\resources\\upload\\";
		String sFilePath = saveFolder + "/" + filename;
		String sMimeType = context.getMimeType(sFilePath);
		response.setContentType(sMimeType);
		
		if(sMimeType == null)
			sMimeType = "application/octet-stream";
		
//		response.setContentLength((int)file.length());
		
		String sEncoding = new String(original.getBytes("utf-8"),"ISO-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);
		
		
		try (BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));){
			
			int numRead;
			while((numRead=in.read(b,0,b.length)) != -1) {
				out2.write(b,0,numRead);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}
}
