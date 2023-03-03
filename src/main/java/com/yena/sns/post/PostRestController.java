package com.yena.sns.post;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yena.sns.post.bo.PostBO;
import com.yena.sns.post.model.Post;

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
	
	//메인페이지에 올라와 있는 게시물 삭제
	//파일(사진), 댓글, 게시물 정보 다 삭제해야 함.
	//삭제될 대상의 id 받아오기(post테이블의 id)
	@GetMapping("postDelete")
	public Map<String, Boolean> deletePost(
			@RequestParam("postId") int postId
			,HttpSession session) {
		
		int userId = (Integer)session.getAttribute("session_index");
		
		int count = postBO.postDelete(postId, userId);
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", true);
		}else {
			result.put("result", false);
		}
		
		return result;
	}



}
