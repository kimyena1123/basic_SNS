package com.yena.sns.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yena.sns.post.bo.PostBO;
import com.yena.sns.post.model.Post;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@RequestMapping("/sns/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	

	//게시물 upload
	@PostMapping("/upload")
	public Map<String, Boolean> postUpload(
			HttpSession session
			,@RequestParam("post_content") String post_content
			,@RequestParam(value="post_file", required = false) MultipartFile post_file){
		
		int user_index = (Integer)session.getAttribute("session_index");
		
		System.out.println("<Controller> file check >> " + post_file);
		
		int count = postBO.postUpload(user_index, post_content, post_file);
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(count == 1) { //insert 성공
			result.put("result", true);
		}else { //insert 실패
			result.put("result", false);
		}
		
		return result;
	}

}
