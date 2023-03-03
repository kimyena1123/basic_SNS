package com.yena.sns.post.like.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yena.sns.post.like.dao.LikeDAO;
import com.yena.sns.post.like.model.Like;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;
	
	//like 정보 넘기기
	public List<Like> sendLikeById(int postId) {
		return likeDAO.sendLikeById(postId);
	}
	
	//like 정보 넘기기 방식2
	//postId를 전달받고, 좋아요 개수를 리턴
	public int likeCount(int postId) {
		return likeDAO.sendLikeCount(postId);
	}
	
	//isHeart 여부
	public int isHeartCheck(int userId, int postId) {
		return likeDAO.isHeartCheck(userId, postId);
	}
	
	//좋아요 
	public int addLike(int userId, int postId) {
		return likeDAO.insertLike(userId, postId);
	}
	
	//좋아요 취소
	public int deleteLike(int userId, int postId) {
		return likeDAO.destroyLike(userId, postId);
	}
	
	//postId를 기반으로 해당 행의 좋아요 삭제
	public int deleteLikeByPostId(int postId) {
		return likeDAO.deleteLikeByPostId(postId);
	}
}
