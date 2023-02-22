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
		<!--  <h1>${postList.size() }</h1>-->
		
		<c:forEach var="post" items="${postList }">
			<div class="mainBox">
				<div class="userDataDiv">
					<div class="showUserInfo">
					
						<img src="#" class="userProfileImg">
						<p>${post.user_name }</p>
					</div>
					
					<i class="bi bi-three-dots-vertical fa-2x"></i>
				</div>
				
				<div class="postImgs">
					<img src="${post.img_src }" class="showImgs">
				</div>
				
				<div class="userInputs">
					<div class="likeInfos">
						<div class="icons">
						
						<!-- 현재 로그인한 사람이 게시물을 눌렀는지 여부 -->
						<c:choose>
							<c:when test="${post.isHeart == 0 }">
							<i class="bi bi-heart fa-2x noHeart" 
								id="heart${post.id }"
								data-post-id="${post.id }"></i>
							</c:when>
							
							<c:otherwise>
							<i class="bi bi-heart-fill fa-2x yesHeart"></i>
							</c:otherwise>
							
						</c:choose>
						
							<i class="bi bi-chat-square-dots fa-2x btn-open-popup" data-modal-Id="${ post.id}" data-modal-content="${post.content }"></i>
							
						</div>
						<!-- <p class="likeCounts">좋아요 ${post.like_count}개</p> -->
						<p class="likeCounts">좋아요 ${post.likeCount}개</p>
					</div>
					
					<div class="userPostUpload">
						<div class="comment">
							<span class="user_id">${post.user_name }</span>
							<p class="post_comment">${post.content }</p>
							
							<c:forEach var="comment" items="${post.commentList }">
								<b style="color:blue">${comment.user_name }</b><p style="color:red">${comment.comment }</p>
							</c:forEach>
						</div>
						<p class="uploadTime">등록일: <fmt:formatDate value="${post.createdAt }" pattern="yyyy년 MM월 dd일" /></p>
					</div>
					
					<div class="commentInputs">
					
						<i class="bi bi-envelope-open-fill fa-2x"></i>
						<input type="text"
								name="comment_comment"
								id="commentInput${post.id }"
								placeholder="댓글달기">
						<i class="bi bi-arrow-up-circle-fill fa-2x createComment" 
							data-post-id="${post.id }"></i>
					
					</div>
					
				</div>
			</div>
			
		
			<div class="modal">
				<div class="modal_body" data-modal-name="${ post.id}">
					<h1 class="modalText">MODAL</h1>
					
					<div class="modalDiv">
						<h1>${post.id }</h1>
						<h1>${post.content }</h1>
					</div>
					
					<button type="button" class="btn-close-popup">모달 닫기</button>
				</div>
			</div>
		
		</c:forEach>
		
		
		</section>
	</div>
	
	<script>
	
	
		$(document).ready(function(){
			
			$(".bi-heart").on("click", function(){
				let postId = $(this).data("postId");
				let heart = $("#heart" + postId);
				
				//heart.toggleClass("bi-heart");
				//heart.toggleClass("bi-heart-fill");
				
				//console.log($(this).hasClass("bi-heart-fill"))
				//true일 때 insert를 해야한다.
				//false일 때 delete를 해야한다.
				
				console.log("noheart(비어있는 상태) >> " , $(this).hasClass("noHeart"));
				console.log("yesheart(빨간하트) >> ", $(this).hasClass("yesHeart"));
				
				//아이콘 클래스가 bi-heart-fill이라면 insert
				if($(this).hasClass("noHeart")){
					$.ajax({
						type:'get',
						url: '/sns/post/like',
						data: {
							"postId": postId,
						},
						success:function(res){
							if(res.result){
								alert("좋아요 추가 성공");
								location.reload();
							}else{
								alert("좋아요 추가 실패");
							}
						},
						error:function(err){
							alert("좋아요 추가 에러");
						}
					})//like ajax
				}else{
					$.ajax({
						type:'get',
						url: '/sns/post/unlike',
						data: {
							"postId": postId,
						},
						success:function(res){
							if(res.result){
								alert("좋아요 취소 성공");
								location.reload();
							}else{
								alert("좋아요 취소 실패")
							}
						},
						error:function(err){
							alert("좋아요 추가 에러");
						}
					})
				}
				
				
			});//좋아요 기능
			
			$(".btn-open-popup").on("click", function(){
				$(".modal").css("display", "block");
				let modalId = $(this).data("modalId");
				let modalContent = $(this).data("modalContent");
				
				//$(".modalDiv").append($("<h1>").text(modalContent));
				
				
			});
			
			$(".btn-close-popup").on("click", function(){
				$(".modal").css("display", "none");
				
			});
			
			$(".createComment").on("click", function(){
				//postId, content를 넘겨줘야 한다.
				let postId = $(this).data("postId");
				let comment = $("#commentInput" + postId).val();
				
				//$(this).siblings()
				//$(this).prev()
				
				$.ajax({
					type: 'post',
					url:'/sns/post/comment/create',
					data: {
						"comment": comment,
						"postId": postId,
					},
					success:function(res){
						if(res.result){
							alert('댓글 달기 성공');
							location.reload();
						}else{
							alert("댓글 달기 실패");
						}
					},
					error:function(err){
						alert("댓글 달기 에러")
					}
				})
			});//댓글달기
			
			
			
		})//jquery
	</script>
</body>
</html>