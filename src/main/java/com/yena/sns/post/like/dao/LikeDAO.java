package com.yena.sns.post.like.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yena.sns.post.like.model.Like;

@Repository
public interface LikeDAO {
	
	//like 정보 넘기기
	public List<Like> sendLikeById(@Param("postId") int postId);

	//like 정보 넘기기 방식2
	public int sendLikeCount(@Param("postId") int postId);
	
	//isHeart 여부
	public int isHeartCheck(
			@Param("userId") int userId
			,@Param("postId") int postId);
	
	//좋아요 달기
		public int insertLike(
				@Param("userId") int userId
				,@Param("postId") int postIds);
		
	//좋아요 취소
	public int destroyLike(
			@Param("userId") int userId
			,@Param("postId") int postId);
	
	//postId를 기반으로 해당 게시물의 좋아요 삭제
	public int deleteLikeByPostId(@Param("postId") int postId);
}
