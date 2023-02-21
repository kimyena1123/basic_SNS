package com.yena.sns.post.comment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yena.sns.post.comment.dao.CommentDAO;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;

	//댓글달기
	public int addComment(int userId, int postId, String content) {
		
		return commentDAO.insertComment(userId, postId, content);
	}
	

}
