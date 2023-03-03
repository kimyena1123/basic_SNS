package com.yena.sns.post.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yena.sns.post.comment.dao.CommentDAO;
import com.yena.sns.post.comment.model.Comment;
import com.yena.sns.post.comment.model.CommentDetail;
import com.yena.sns.user.bo.UserBO;
import com.yena.sns.user.model.User;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private UserBO userBO;
	

	//댓글달기 insert
	public int addComment(int userId, int postId, String content) {
		
		return commentDAO.insertComment(userId, postId, content);
	}
	
	//댓글 보여주기 select
	//특정 post의 댓글 목록을 조회하는 기능
	//select * from `comment` where `postId` = 3
	//public List<Comment> selectCommentList(int postId) {
	public List<CommentDetail> selectCommentList(int postId) {	
		//댓글 조회 결과를 기반으로 댓글마다 댓글작성자 정보를 조회한다.
		List<Comment> commentList = commentDAO.selectCommentList(postId);
		List<CommentDetail> commentDetailList = new ArrayList<>();
		
		for(Comment comment:commentList) {
			CommentDetail commentDetail = new CommentDetail();
			
			commentDetail.setId(comment.getId());
			commentDetail.setPostId(comment.getPostId());
			commentDetail.setUserId(comment.getUserId());
			commentDetail.setComment(comment.getComment());
			
			//user 테이블의 user_name 가져오기
			User user = userBO.getUserById(comment.getUserId()); //괄호 안: user테이블의 id와 comment의 userId 대조
			commentDetail.setUser_name(user.getUser_name()); //대조했을 때 맞는 정보를 기반으로 user 테이블에서 맞는 id와 같은 행에 있는 user_name을 가져온다.
			
			commentDetailList.add(commentDetail);
		}
		
		//return commentDAO.selectCommentList(postId);
		return commentDetailList;
	}
	
	
	//postId를 기반으로 댓글 삭제 기능
	public int deletePostByPostId(int postId) {
		return commentDAO.deletePostByPostId(postId);
	}
	
	
	//해당 게시물의 댓글 보여주기 방식2
	public List<Comment> showComments(int postId){
		return commentDAO.showComments(postId);
	}
	
	
	
	

}
