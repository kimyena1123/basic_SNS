<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mypage</title>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
<link rel="stylesheet" href="/static/css/mypage.css" type="text/css">
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section>
		<c:forEach var="userPost" items="${ userPostList}">
		
		 	<div class="postInfo">
		 		게시자 : ${userPost.userId } <br>
		 		문구 : ${userPost.content } <br>
		 		사진 : <img src="${userPost.img_src }">
		 	</div>
		 </c:forEach>
		</section>
	</div>
</body>
</html>