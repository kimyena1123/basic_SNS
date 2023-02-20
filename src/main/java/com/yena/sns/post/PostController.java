package com.yena.sns.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yena.sns.post.bo.PostBO;
import com.yena.sns.post.model.PostDetail;

@RequestMapping("/sns/post")
@Controller
public class PostController {
	
	@Autowired
	private PostBO postBO;
	

	//메인페이지
	@GetMapping() //기본 주소 localhost:port/sns/post
	public String main(Model model) {
		
		List<PostDetail> postList = postBO.getMainInfo();
		
		model.addAttribute("postList", postList);
		
		return "post/main";
	}

	
	//게시물 upload 페이지
	@GetMapping("/postUpload/view")
	public String postUpload() {
		
		return "post/postUpload";
	}
}
