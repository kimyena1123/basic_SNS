<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
<link rel="stylesheet" href="/static/css/main.css" type="text/css">

<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<!-- bootstrap icon cdn -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section>
			<div class="mainBox">
				<div class="userDataDiv">
					<div class="showUserInfo">
						<img src="#" class="userProfileImg">
						<p>userName</p>
					</div>
					
					<i class="bi bi-three-dots-vertical fa-2x"></i>
				</div>
				
				<div class="postImgs">
					<img src="#" class="showImgs">
				</div>
				
				<div class="userInputs">
					<div class="likeInfos">
						<div class="icons">
							<i class="heartClass bi bi-heart fa-2x"></i>
							<!-- <i class="bi bi-heart-fill fa-2x"></i> -->
							<i class="bi bi-chat-square-dots fa-2x"></i>
						</div>
						<p class="likeCounts">좋아요 100개</p>
					</div>
					
					<div class="userPostUpload">
						<div class="comment">
							<span class="user_id">user_id</span>
							<p class="post_comment">comment</p>
						</div>
						<p class="uploadTime">등록일: 0000/00/00</p>
					</div>
					
					<div class="commentInputs">
						<i class="bi bi-envelope-open-fill fa-2x"></i>
						<input type="text" id="comment_comment" name="comment_comment" placeholder="댓글달기">
						<i class="bi bi-arrow-up-circle-fill fa-2x"></i>
					</div>
				</div>
			</div>
			
			
			
			<div class="mainBox">
				<div class="userDataDiv">
					<div class="showUserInfo">
						<img src="#" class="userProfileImg">
						<p>userName</p>
					</div>
					
					<i class="bi bi-three-dots-vertical fa-2x"></i>
				</div>
				
				<div class="postImgs">
					<img src="#" class="showImgs">
				</div>
				
				<div class="userInputs">
					<div class="likeInfos">
						<div class="icons">
							<i class="heartClass bi bi-heart fa-2x"></i>
							<!-- <i class="bi bi-heart-fill fa-2x"></i> -->
							<i class="bi bi-chat-square-dots fa-2x"></i>
						</div>
						<p class="likeCounts">좋아요 100개</p>
					</div>
					
					<div class="userPostUpload">
						<div class="comment">
							<span class="user_id">user_id</span>
							<p class="post_comment">comment</p>
						</div>
						<p class="uploadTime">등록일: 0000/00/00</p>
					</div>
					
					<div class="commentInputs">
						<i class="bi bi-envelope-open-fill fa-2x"></i>
						<input type="text" id="comment_comment" name="comment_comment" placeholder="댓글달기">
						<i class="bi bi-arrow-up-circle-fill fa-2x"></i>
					</div>
				</div>
			</div>
			
		</section>
	</div>
	
	<script>
		$(document).ready(function(){
			$(".heartClass").on("click", function(){
				
				$(".heartClass").toggleClass( 'bi-heart' );
				$(".heartClass").toggleClass( 'bi-heart-fill' );
			})
		})
	</script>
</body>
</html>