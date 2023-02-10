<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" href="/static/css/style.css" type="text/css">

<!-- font awesome -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css" integrity="sha512-1PKOgIY59xJ8Co8+NE6FZ+LOAZKjy+KY8iq0G4B3CyeY6wYHN3yt9PW0XpSriVlkMXe40PTKnXrLnZ9+fkDaog==" crossorigin="anonymous" />
<header>
	<div class="logo">
		<img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSK7iDhSwnD0pn3g-ZiJcXniGMltBRFAr6ngg&usqp=CAU">
		<h1>Stargram</h1>
	</div>
	
	<c:choose>
		<c:when test="${not empty session_index }">
			<div class="userSelect">
				<div class="userNameShow">
					<h2>${session_id }</h2>
					<a href="/sns/user/signout">로그아웃</a>
				</div>
				<div class="icons"><a href="#"><i class="fas fa-plus fa-3x"></i></a></div>
				<div class="icons"><a href="#"><i class="fas fa-user fa-3x"></i></a></div>
				<div class="icons"><a href="#"><i class="fas fa-home fa-3x"></i></a></div>
			</div>
		</c:when>
		<c:otherwise>
			<div class="userSelect">
				<div class="icons"><a href="#"><i class="fas fa-home fa-3x"></i></a></div>
			</div>
		</c:otherwise>
	</c:choose>
	
</header>