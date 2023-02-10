package com.yena.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/sns/user")
@Controller
public class UserController {

	//회원가입 화면
	@GetMapping("/signup/view")
	public String signupView() {
		
		return "user/signup";
	}
	
	//로그인 화면
	@GetMapping("/signin/view")
	public String signinView() {
		
		return "user/signin";
	}
	
	//로그아웃 기능
	@GetMapping("/signout")
	public String signout(
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("session_index");
		session.removeAttribute("session_id");
		
		return "redirect:/sns/user/signin/view";
	}
}
