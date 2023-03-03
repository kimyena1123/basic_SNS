package com.yena.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.yena.sns.post.model.Post;

@Repository
public interface PostDAO {
	
	public List<Post> selectPostInfo();
	
	//게시물 올리기(insert)
	public int postUpload(
			@Param("user_index") int user_index
			,@Param("post_content") String post_content
			,@Param("imagePath") String imagePath);
	
	//id로 그 행의 정보 가져오기
	public Post showPost(@Param("id") int id);
	
	//메인페이지의 개시물 삭제
	public int deletePost(@Param("postId") int postId
						,@Param("userId") int userId);
		
	
}
