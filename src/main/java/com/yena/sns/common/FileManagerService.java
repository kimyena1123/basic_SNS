package com.yena.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {

	//파일 저장
	//파일을 저장하려면 얻니에 저장할 지 설정해야 한다.
	public static final String PILE_UPLOAD_PATH = "C:\\java_kyn\\SpringProject\\upload\\images\\sns";
	
	private static Logger logger = LoggerFactory.getLogger(FileManagerService.class);
	
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
		
		if(directory.mkdir() == false) { 
			//디렉토리 생성 실패
			
			//디렉토리 생성 실패 로그 추가
			logger.error("savefile : 디렉토리 생성 실패 " + directoryPath);
			return null;
		}
	
		
		//파일 저장
		//바이트 단위로 꺼내야 함
		
		String filePath = null;
		
		try {
			byte[] bytes = file.getBytes();
			
			//fileUploadPath//userId_현재시간//사진이름.png
			//directoryPath = PILE_UPLOAD_PATH + directoryName;
			filePath = directoryPath + file.getOriginalFilename();
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			//디렉토리 생성 실패 로그 추가
			logger.error("saveFile : 파일 생성 에러 - " + filePath);
			
			return null;
		}
		
		//client에서 접근 가능한 경로를 문자열로 리턴
		//http://localhost:8900/images/sns/~
		// /images/sns/userId_현재시간/파일이름.png
		return "/images/sns" + directoryName + file.getOriginalFilename();
	}
	
	
	
	//파일 삭제 메소드
		//post테이블의 img_path에 저장되어 있는 이미지 경로는 
		//내가 만들어 놓은 규칙으로 만들어져 있다.
		//그래서 삭제 대상이 될 행의 이미지 경로를 얻어와서 그 경로를 기반으로
		//해당하는 파일의 위치를 찾아 삭제하는 과정이 진행되어야 한다.
		
		//데이터베이스에 저장해놓았던 그 규칙의 경로를 전달받아서
		//그에 대응되는 파일을 삭제하는 메소드를 구현.
		//파일 삭제 성공 실패 여부를 리턴할 것이기에 리턴타입은 boolean으로.
		
		//매개변수로 "파일의 경로"를 전달받아야 한다.
		//그 경로는 데이터베이스에 (img_path)에 저장된 형태를 의미. 
		// => /images/userId_현재시간/파일이름.png
		//위 형태의 "경로" 문자열을 전달받을 것이다.
		public static boolean removeFile(String filePath) {
			///filePath (/images/userId_현재시간/파일이름.png)로부터
			//실제 저장된 위치의 경로를 만들어내야 한다.
			//근데 내가 filePath 형태를 만들어 냈던 이유는  
			//FILE_UPLOAD_PATH (C:\\java_kyn\\SpringProject\\upload\\images)경로 아래쪽 경로를
			//images라고 하는 파일 뒷부분에 적용시키게 하기 위해서 우리가 규칙을 만든 것이다.
			
			//삭제는 그 반대로 가면 된다.
			//images 뒷부분(filePath)를 실제 파일 경로(FILE_UPLOAD_PATH) 뒤에 이어붇이면 
			//실제 그 파일이 저장된 경로가 된다.
			
			//filePath(/images/userId_현재시간/파일이름.png)에서 /images/를 제외한 나머지를
			//실제 저장파일 경로(FILE_UPLOAD_PATH) 뒤에 이어붙이면
			//데이터베이스 post 테이블의 img_path에 저장된 그 위치(내용)가 된다.
			
			// 즉, 삭제경로는
			// /images를 제거하고
			// 실제 파일 저장 경로(FILE_UPLOAD_PATH) 이어붙여준다.
			// C:\\java_kyn\\SpringProject\\upload\\images 와
			// /images/userId_현재시간/파일이름.png 두 개가 있는데 여기서 /images를 뺀 나머지를 위에 붙인다.
			// 즉, C:\\java_kyn\\SpringProject\\upload\\images/userId_현재시간/파일이름.png 가 되고
			// 이 경로에 있는 파일을 삭제해주면 된다.

			//filePath에서 /images를 어떻게 제거할까?
			// => 1. substring을 쓰는 방법, 2. replace를 쓰는 방법 등등 여러가지가 있음. 나는 2번 사용.
			String realFilePath = PILE_UPLOAD_PATH + filePath.replace("/images", ""); // /images로 되어있는 문자열을 빈 문자열로!
			
			//realFilePath는 문자열로 이루어진 경로.
			//realFilePath 이 경로를 Path라는 클래스로 만들어진 객체로 변환을 해야지 간단하게 삭제가 된다.
			//문자열로 만들어진 경로(realFilePath)를 Path라고 하는 경로를 관리하는 클래스가 있다.
			//그 클래스에 객체로 만들어주는 과정이다. 이걸 활용하면 삭제를 매우 간단하게 진행할 수 있기 때문임.
			Path path = Paths.get(realFilePath);
			
			//삭제할 때는 실제 파일이 존재한는지 확인하고 그러고 나서 파일삭제를 진행해줘야 한다.
			//해당 파일이 있는지 없는지 여부는 Files라는 클래스의 static 메소드로 exists라는 메소드를 통해서 알 수 있다.
			// 존재하면 true 아니면 false를 리턴한다.		
			
			if(Files.exists(path)) { // 존재할 때만 파일을 삭제하는 과정을 진행해주면 된다.
				try {
					Files.delete(path);
				} catch (IOException e) {
					
					e.printStackTrace();
					
					//잘못됐다고 하는 false를 리턴.
					return false;
				}
			}
			
			//우리가 파일을 저장할 때 파일만 저장한게 아니라 또 하나를 만들었다.
			//디렉토리를 만들었다(파일).
			//파일경로, 파일 디렉토리 경로는 "C:\\java_kyn\\SpringProject\\upload\\images/userId_현재시간" 이다.
			//Path 클래스가 상위 폴더가 뭔지 알려준다.
			//realFilePath는 path에 저장. 여기서 상위폴더(디렉토리)도 삭제
			//디렉토리 경로
			Path dirPath = path.getParent();
			
			//디렉토리 존재하는지
			if(Files.exists(dirPath)) {
				try {
					Files.delete(dirPath);
				} catch (IOException e) {
					
					e.printStackTrace();
					
					//예외상황 발생하면 문제 있다고 알려줌
					return false;
				}
			}
			
			return true; // 정상정으로 수행이 됐다라는 걸 리턴
			
		}//removeFile 메소드 end
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
