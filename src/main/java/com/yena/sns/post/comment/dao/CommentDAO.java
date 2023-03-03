package com.yena.sns.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yena.sns.post.comment.model.Comment;
import com.yena.sns.post.model.Post;

@Repository
public interface CommentDAO {

	//댓글달기 insert
	public int insertComment(
			@Param("userId") int userId
			,@Param("postId") int postId
			,@Param("comment") String comment);

	//댓글 가져오기 select
	public List<Comment> selectCommentList(
			@Param("postId") int postId);
	
	//postId를 기반으로 댓글 삭제 기능
	public int deletePostByPostId(@Param("postId") int postId);
	
	//해당 게시물의 댓글 보여주기 방식2
	public List<Comment> showComments(@Param("postId") int postId);
	
	
	
}
