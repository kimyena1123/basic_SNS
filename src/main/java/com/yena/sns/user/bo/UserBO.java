package com.yena.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yena.sns.common.EncryptUtils;
import com.yena.sns.user.dao.UserDAO;
import com.yena.sns.user.model.User;

@Service
public class UserBO {
	@Autowired
	private UserDAO userDAO;

	//회원가입 (insert)
	public int addSignup(
			String user_id, String user_pw
			,String user_name, String user_birth
			,String user_email) {
		//비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(user_pw);
		
		return userDAO.insertSignup(user_id, encryptPassword, user_name, user_birth, user_email);
	}
	
	//아이디 중복 검사(select)
	public boolean checkId(String user_id) {
		int count = userDAO.checkId(user_id);
		
		//count == 0이면 중복 없음
		//count == 1이면 중복 있음
//		if(count == 0) {
//			return true; //아이디 사용 가능
//		}else {
//			return false; //아이디 사용 불가능
//		}
		
		return count != 0;
	}
	
	//이메일 중복 검사(select)
	public boolean checkEmail(String user_email) {
		int count = userDAO.checkEmail(user_email);
		
		//count == 0이면 중복 없음
		//count == 1이면 중복 있음
		if(count == 0) {
			return true; // 중복 없음 -> 사용 가능
		}else {
			return false;
		}
	}
	
	//로그인(select)
	public User getUser(String user_id, String user_pw) {
		//비밀번호 암호화
		String encryptPassword = EncryptUtils.md5(user_pw);
				
		return userDAO.selectUser(user_id, encryptPassword);
	}
}
