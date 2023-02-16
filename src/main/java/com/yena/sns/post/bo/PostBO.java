package com.yena.sns.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yena.sns.common.FileManagerService;
import com.yena.sns.post.dao.PostDAO;
import com.yena.sns.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	//메인 페이지
	public List<Post> selectPostInfo(){
		List<Post> post = postDAO.selectPostInfo();
		
		return post;
	}
	
	//게시물 올리기 API(insert)
	public int postUpload(int user_index, String post_content, MultipartFile post_file) {
		
		System.out.println("<BO> file check >> "+ post_file);
		
		if(post_file != null) { // 사용자가 파일을 올렸을 때
			
			String imagePath = FileManagerService.saveFile(user_index, post_file);			
			return postDAO.postUpload(user_index, post_content, imagePath);
			
		}else { // 사용자가 파일 없이 게시물 올렸을 때
			
			return postDAO.postUpload(user_index, post_content, null);
		}
		
	}
}
