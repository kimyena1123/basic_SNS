package com.yena.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {

	//파일 저장
	//파일을 저장하려면 얻니에 저장할 지 설정해야 한다.
	public static final String PILE_UPLOAD_PATH = "C:\\java_kyn\\SpringProject\\upload\\images\\sns";

	//파일 저장을 위한 메소드
	//파일을 저장하고 client에서 접근 가능한 주소를 만들어 리턴하는 기능
	//static: 객체 생성 없이 메소드를 호출하기 위해
	public static String saveFile(int userId, MultipartFile file) {
		
		//사용자가 올린 파일 경로가 겹치더라도 문제없이 저장할 수 있도록 구성해야 한다.
		//사용자가 올린 파일을 서로 다른 파일로 저장하도록 정리해야 한다.
		//=> 사용자별로 폴더를 구분한다. 폴더를 나눠놓으면 사용자별로 같은 곳에 저장되지 안ㄴㅎ아 파일이름이 겹쳐도 문제되지 X
		//=> 사용자별로 폴더를 새로 만든다.
		//폴더이름: userId_현재시간
		//UNIX TIME: 1970년 1월 1일부터 흐른 시간(millisecond 1/1000)
		//똑같은 파일이 올라와도 구분할 수 있도록!
		
		//PILE_UPLOAD_PATH//userId_현재시간//asdf.png
		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		String directoryPath = PILE_UPLOAD_PATH + directoryName;
		//디렉토리 생성
		File directory = new File(directoryPath);
		
		if(directory.mkdir() == false) { //디렉토리 생성 실패
			return null;
		}
		
		//파일 저장
		//바이트 단위로 꺼내야 함
		try {
			byte[] bytes = file.getBytes();
			
			//fileUploadPath//userId_현재시간//사진이름.png
			//directoryPath = PILE_UPLOAD_PATH + directoryName;
			String filePath = directoryPath + file.getOriginalFilename();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return null;
		}
		
		//client에서 접근 가능한 경로를 문자열로 리턴
		//http://localhost:8900/images/sns/~
		// /images/sns/userId_현재시간/파일이름.png
		return "/images/sns" + directoryName + file.getOriginalFilename();
	}
}
