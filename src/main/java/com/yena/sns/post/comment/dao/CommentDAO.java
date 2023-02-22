package com.yena.sns.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yena.sns.post.comment.model.Comment;

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
}
