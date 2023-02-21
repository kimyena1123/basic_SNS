package com.yena.sns.post.comment.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO {

	//댓글달기
	public int insertComment(
			@Param("userId") int userId
			,@Param("postId") int postId
			,@Param("comment") String comment);

}
