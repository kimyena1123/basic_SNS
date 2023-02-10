package com.yena.sns.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yena.sns.post.model.Post;

@Repository
public interface PostDAO {

	//마이페이지 - 사용자가 올린 모든 게시물에 대한 정보
	public List<Post> selectPost();
}
