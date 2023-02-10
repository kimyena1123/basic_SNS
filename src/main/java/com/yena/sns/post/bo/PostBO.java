package com.yena.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yena.sns.post.dao.PostDAO;
import com.yena.sns.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	//마이페이지 - 사용자에 대한 모든 게시물 데이터 조회
	public List<Post> getPostList(){
		List<Post> userPostList = postDAO.selectPost();
		
		return userPostList;
	}
}
