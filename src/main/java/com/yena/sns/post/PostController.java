package com.yena.sns.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yena.sns.post.bo.PostBO;
import com.yena.sns.post.model.Post;

@RequestMapping("/sns/post")
@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;

	//메인페이지
	@GetMapping() //기본 주소 localhost:port/sns/post
	public String main() {
		
		return "post/main";
	}
	
	//마이페이지 - 모든 데이터 조회
	@GetMapping("/mypage")
	public String mypage(Model model) {
		
		List<Post> userPostList = postBO.getPostList();
		
		model.addAttribute("userPostList", userPostList);
		
		return "post/mypage";
	}
}
