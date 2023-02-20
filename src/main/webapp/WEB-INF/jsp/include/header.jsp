<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="/static/css/style.css" type="text/css">

<!-- font awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous" />
<!-- bootstrap icon cdn -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<header>
	<div class="logo">
		<i class="bi bi-instagram fa-4x"></i>
		<h1>Stargram</h1>
	</div>
	
	<c:choose>
		<c:when test="${not empty session_index }">
			<div class="userSelect">
				<div class="userNameShow">
					<h2>${session_id }</h2>
					<a href="/sns/user/signout">로그아웃</a>
				</div>
				<a href="/sns/post/postUpload/view"><i class="bi bi-plus-square fa-3x loginSuccess"></i></a>
				<a href="/sns/post/mypage/view"><i class="bi bi-person-circle fa-3x loginSuccess"></i></a>
				<a href="/sns/post"><i class="bi bi-house-door fa-3x loginSuccess"></i></a>
			</div>
		</c:when>
		
		<c:otherwise>
			<div class="userSelect">
				<a href="/sns/user/signup/view"><i class="bi bi-person-plus fa-3x notSignin"></i></a>
				<a href="/sns/user/signin/view"><i class="bi bi-person-check fa-3x notSignin"></i></a>
				<a href="/sns/post"><i class="bi bi-house-door fa-3x notSignin"></i></a>
			
				<!--폰트어위썸: <div class="icons"><a href="#"><i class="fas fa-home fa-3x"></i></a></div> -->
			</div>
		</c:otherwise>
	</c:choose>
</header>