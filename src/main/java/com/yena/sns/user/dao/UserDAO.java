package com.yena.sns.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yena.sns.user.model.User;

@Repository
public interface UserDAO {
	//user의 모든 정보
	public List<User> selectAllInfo();
	
	//회원가입
	public int insertSignup(
			@Param("user_id") String user_id
			,@Param("user_pw") String user_pw
			,@Param("user_name") String user_name
			,@Param("user_birth") String user_birth
			,@Param("user_email") String user_email);
	
	//아이디 중복 검사
	public int checkId(@Param("user_id") String user_id);
	
	//이메일 중복 검사
	public int checkEmail(@Param("user_email") String user_email);
	
	//로그인 일치
	public User selectUser(
			@Param("user_id") String user_id
			,@Param("user_pw") String user_pw);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
