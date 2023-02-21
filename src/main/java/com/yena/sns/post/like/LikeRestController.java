package com.yena.sns.post.like;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yena.sns.post.like.bo.LikeBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/sns/post")
public class LikeRestController {
	@Autowired
	private LikeBO likeBO;

	//좋아요 달기
	@GetMapping("/like")
	public Map<String, Boolean> like(
			@RequestParam("postId") int postId
			,HttpSession session){
		

		//좋아요를 누른 사람은 로그인을 한 사용자
		int userId = (Integer)session.getAttribute("session_index");
		
		int count = likeBO.addLike(userId, postId);
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", true);
		}else {
			result.put("result", false);
		}
		
		return result;
		
	}
	
	//좋아요 취소 API
	//DELETE FROM `like` WHERE userId = 로그인한 사람 and postId = 원하는 게시물
	@GetMapping("/unlike")
	public Map<String, Boolean> deleteLike(
			@RequestParam("postId") int postId
			,HttpSession session){
		
		int userId = (Integer)session.getAttribute("session_index");
		int count = likeBO.deleteLike(userId, postId);
		
		Map<String, Boolean> result = new HashMap<>();
		
		if(count == 1) {
			result.put("result", true); //delete성공
		}else {
			result.put("result", false); //delete 실패
		}
		
		return result;
		
	}
}
