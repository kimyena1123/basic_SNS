package com.yena.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yena.sns.user.bo.UserBO;
import com.yena.sns.user.model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/sns/user")
@RestController //@Controller + @ResponseBody
public class UserRestController {
	
	@Autowired
	private UserBO userBO;

	//회원가입 API
	@PostMapping("/signup")
	public Map<String, Boolean> signup(
			@RequestParam("user_id") String user_id
			,@RequestParam("user_pw") String user_pw
			,@RequestParam("user_name") String user_name
			,@RequestParam("user_birth") String user_birth
			,@RequestParam("user_email") String user_email){
		
		int count = userBO.addSignup(user_id, user_pw, user_name, user_birth, user_email);
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(count == 1) {//insert 성공 => 회원가입 성공
			result.put("result", true);
		}else {
			result.put("result", false);
		}
		
		return result;
	}
	
	//아이디 중복 검사 API
	@GetMapping("checkId")
	public Map<String, Boolean> checkId(
			@RequestParam("user_id") String user_id){
	
		Map<String, Boolean> id = new HashMap<>();
		
		//userBO.checkId(user_id) == true이면 사용 가능
		//userBO.checkId(user_id) == false이면 사용 불가능
		if(userBO.checkId(user_id)) { // true
			id.put("id", true); // 중복없음
		}else {
			id.put("id", false);
		}
		
		return id;
	}
	
	//이메일 중복 검사 API
	@GetMapping("checkEmail")
	public Map<String, Boolean> checkEmail(
			@RequestParam("user_email") String user_email){
		
		Map<String, Boolean> email = new HashMap<>();
		
		if(userBO.checkEmail(user_email)) {
			email.put("email", true); // 사용가능 -> 중복 없음
		}else {
			email.put("email", false);
		}
		
		return email;
	}
	
	//로그인 API
	@PostMapping("/signin")
	public Map<String, Boolean> signin(
			@RequestParam("user_id") String user_id
			,@RequestParam("user_pw") String user_pw
			,HttpServletRequest request){
		
		User user = userBO.getUser(user_id, user_pw);
		
		Map<String, Boolean> result = new HashMap<>();
		
		//조회가 안된다면 null.
		if(user != null) {
			result.put("result", true); // 로그인 성공
			
			//세션 객체 얻어오기
			HttpSession session = request.getSession();
			
			session.setAttribute("session_index", user.getId());
			session.setAttribute("session_id", user.getUser_id());
		}else {
			result.put("result", false); //로그인 실패
		}
		
		return result;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
