package com.yena.sns.post;

import java.util.ArrayList;
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
	public String main(Model model) {
		List<Post> list = new ArrayList<>();
		
		Post info = new Post();
		//1. user 테이블
		list.add(info);
		
		info = new Post();
		//2. comment 테이블
		list.add(info);
		
		info = new Post();
		//3. like 테이블
		list.add(info);
		
		model.addAttribute("list", list);
		
		return "post/main";
	}

	
	//게시물 upload 페이지
	@GetMapping("/postUpload/view")
	public String postUpload() {
		
		return "post/postUpload";
	}
}
