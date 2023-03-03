package com.yena.sns.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yena.sns.common.FileManagerService;
import com.yena.sns.post.comment.bo.CommentBO;
import com.yena.sns.post.comment.model.CommentDetail;
import com.yena.sns.post.dao.PostDAO;
import com.yena.sns.post.like.bo.LikeBO;
import com.yena.sns.post.model.Post;
import com.yena.sns.post.model.PostDetail;
import com.yena.sns.user.bo.UserBO;
import com.yena.sns.user.model.User;

@Service
public class PostBO {
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private UserBO userBO; //user 테이블 정보를 가져오기 위해서
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	//메인 페이지
	public List<PostDetail> getMainInfo(int userId){
		
		List<Post> postList = postDAO.selectPostInfo();
		List<PostDetail> postDetailList = new ArrayList<>();
		
		for(Post post:postList) {
			
			PostDetail postDetail = new PostDetail();
			
			postDetail.setId(post.getId());
			postDetail.setUserId(post.getUserId());
			postDetail.setContent(post.getContent());
			postDetail.setImg_src(post.getImg_src());
			postDetail.setCreatedAt(post.getCreatedAt());
			
			//post 테이블의 userId와 user테이블의 id를 대조해서
			//user 테이블의 정보를 가져옴
			User user = userBO.getUserById(post.getUserId());
			postDetail.setUser_name(user.getUser_name());
			
			//================방식1======================
			//post 테이블의 id와 like 테이블의 postId를 대조해서
			//like 테이블의 정보를 가져옴
			/*List<Like> Likelist = likeBO.sendLikeById(post.getId());
			System.out.println("postBO에서 길이 확인 >> "+ Likelist.size());
			postDetail.setLike_count(Likelist.size());
			*/
			
			//================방식2=======================
			//좋아요 개수 조회
			//select count(1) `like` where postId = #{postId}
			int likeCount = likeBO.likeCount(post.getId());
			postDetail.setLikeCount(likeCount);
			
			int isHeartCHeck = likeBO.isHeartCheck(userId, post.getId());
			postDetail.setIsHeart(isHeartCHeck);
			
			//comment 테이블
			//post 태이블의 id를 보내준다.
			//List<Comment> commentList = commentBO.selectCommentList(post.getId());
			List<CommentDetail> commentList = commentBO.selectCommentList(post.getId());
			postDetail.setCommentList(commentList);
			
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
	
	//게시물 삭제
	public int postDelete(int postId, int userId) {
		
		//id를 가지고 한 행에 대한 post 테이블 정보를 가져오기 때문에
		//Post를 리턴한다.
		Post post = postDAO.showPost(postId);
		
		//대상 post 삭제
		int count = postDAO.deletePost(postId, userId);
		
		if(count == 1) {
		
			System.out.println("file 값 확인 >>> "+ post.getImg_src());
			
			if(post.getImg_src() != null){	//파일사진이 있다면 파일 삭제		
				FileManagerService.removeFile(post.getImg_src());
			}
			
			//대상 post 삭제 => 좋아요와 댓글 삭제
			//delete from `comment` where id = postId;
			//delete from `like` where id = postId;
			
			//post와 관계된 댓글 삭제
			//싦행될 행의 개수. 댓글은 0개일수도 100개일 수도 있다. 
			//리턴되는 값은 몇 개씩 실행될 수 없으니까 기능만 수행하게 한다.
			commentBO.deletePostByPostId(postId);
			
			//좋아요 삭제
			likeBO.deleteLikeByPostId(postId);
			
		}
		
		return count;
	}
	
	
	
}
