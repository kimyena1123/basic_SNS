package com.yena.sns.post.model;

import java.util.Date;

public class PostDetail {
	
	//post 테이블에 대한 정보
	private int id;
	private int userId;
	private String content;
	private String img_src;
	private Date createdAt;
	private Date updatedAt;
	
	//user 테이블에 대한 정보
	private String user_name;

	//like 테이블에 대한 정보 => 방식1
	private int like_count;
	
	//like 테이블에 대한 정보 => 방식2
	private int likeCount;
	private int isHeart;



	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg_src() {
		return img_src;
	}

	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getLike_count() {
		return like_count;
	}

	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}

	
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getIsHeart() {
		return isHeart;
	}

	public void setIsHeart(int isHeart) {
		this.isHeart = isHeart;
	}


	
	
}
