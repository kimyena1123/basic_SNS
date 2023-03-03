package com.yena.sns.post.comment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yena.sns.post.comment.bo.CommentBO;
import com.yena.sns.post.model.Post;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/sns/post/comment")
@RestController
public class CommentController {
	@Autowired
	private CommentBO commentBO;
	
	//댓글쓰기
	@PostMapping("/create")
	public Map<String, Boolean> createComment(
			@RequestParam("postId") int postId
			,@RequestParam("comment") String comment
			,HttpSession session){
		
		//session 정보 가져오기
		int userId = (Integer)session.getAttribute("session_index");
		
		int count = commentBO.addComment(userId, postId, comment);
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", true);
		}else {
			result.put("result", false);
		}
		return result;
	}
	
	
	
}
