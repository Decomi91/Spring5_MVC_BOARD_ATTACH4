package com.naver.myhome6.task;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.naver.myhome6.service.BoardService;

@Service
public class FileCheckTest {
	@Value("${savefoldername}")
	private String saveFolder;
	
	@Autowired
	private BoardService boardservice;
	
//	@Scheduled(fixedDelay = 100000)//밀리세컨드 단위
//	public void test() throws Exception{
//		System.out.println("test");
//	}
	
	// 초 분 시 일 월 요일 (년)
	@Scheduled(cron = "10 20 12 * * *")
	public void checkFiles() throws Exception {
		List<String> fileList = boardservice.getFileList();
		for(String s : fileList) System.out.println(s);
		deleteFile(saveFolder, fileList);
		boardservice.boardDeleteTempDelete();
	}
	
	public static void deleteFile(String path, List<String> fileList) {
		File deleteFolder = new File(path);
		System.out.println("삭제중");
		
		if(deleteFolder.exists()){
			File[] deleteFolderList = deleteFolder.listFiles();
			System.out.println("삭제진입");
			boolean judge = false;
			for (int i = 0; i < deleteFolderList.length; i++) {
				System.out.println(deleteFolderList[i].getName());
				String path2 = deleteFolderList[i].getPath();
				if(deleteFolderList[i].isFile()) {
					if(fileList.contains(path2.substring(
							path2.lastIndexOf("\\", path2.lastIndexOf("\\")-1)).replace("\\", "/")
						)) {
						System.out.println("삭제진행");
						deleteFolderList[i].delete();
					}else {
						judge = true;
					}
					
				}else {
					deleteFile(deleteFolderList[i].getPath(), fileList);
				}
				if(!judge) {
					deleteFolderList[i].delete();
				}
				judge = false;
			}
		}
	}
}
