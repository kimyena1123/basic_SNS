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
}
