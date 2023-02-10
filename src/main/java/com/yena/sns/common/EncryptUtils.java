package com.yena.sns.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
	
	
	// 비밀번호 암호화
	//hashing을 사용해 암호화 진행
	//암호화는 회원가입 뿐만 아니라 로그인 시에도 사용된다. => 메소드로!
	public static String md5(String message) { // message 비밀번호 받기
		String resultData = "";
		
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			
			//message를 바이트 단위로 바꿔줘야 한다.
			byte[] bytes = message.getBytes();
			md.update(bytes); // 이 안에서 message를 암호화한다.
			
			// 그 결과를 bytes 배열 형태로 돌려준다.
			//digest : 암호화된 결과임.
			byte[] digest = md.digest();
			
			//암호화 된 걸 문자열로
			//=> 16진수 형태의 문자열로 변환
			for(int i = 0; i < digest.length; i++) {
				resultData += Integer.toHexString(digest[i] & 0xff); // 비트연산
			}
			
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
		
		return resultData;
	}
}
