package com.yena.sns.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yena.sns.common.FileManagerService;
import com.yena.sns.post.dao.PostDAO;
import com.yena.sns.post.model.Post;
import com.yena.sns.post.model.PostDetail;
import com.yena.sns.user.bo.UserBO;
import com.yena.sns.user.model.User;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	//user 테이블 정보를 가져오기 위해서
	@Autowired
	private UserBO userBO;
	
	//메인 페이지
	public List<PostDetail> getMainInfo(){
		
		List<Post> postList = postDAO.selectPostInfo();
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			
			PostDetail postDetail = new PostDetail();
			
			postDetail.setId(post.getId());
			postDetail.setUserId(post.getUserId());
			postDetail.setContent(post.getContent());
			postDetail.setImg_src(post.getImg_src());
			postDetail.setCreatedAt(post.getCreatedAt());
			
			
			User user = userBO.getUserById(post.getUserId());
			
			postDetail.setUser_name(user.getUser_name());
			postDetailList.add(postDetail);
		}
		
		return postDetailList;
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
