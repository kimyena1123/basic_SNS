<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sns 로그인</title>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
<link rel="stylesheet" href="/static/css/signin.css" type="text/css">
<!-- jquery cdn -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section>
			<div class="squareBox">
				<div class="mainText">
					<h1>Stargram</h1>
				</div>
				
				<form id="loginForm">
					<div class="inputTags">
						<input type="text" id="user_id" name="user_id" placeholder="아이디" autocomplete="off">
						<input type="password" id="user_pw" name="user_pw" placeholder="비밀번호" autocomplete="off">
			
						<button type="submit" id="loginBtn">로그인</button>
					</div>
				</form>
				
				<div class="signupBox">
					<p>
						계정이 없으신가요?
						<a href="/sns/user/signup/view">가입하기</a>
					</p>
				</div>
			</div>
		</section>
	</div>
	
	<script>
		$(document).ready(function(){
			
			//로그인 버튼 누를 때
			$("#loginForm").on("submit", function(e){
			//$("#loginBtn").on("click", function(){
				
				//해당 이벤트의 기능을 모두 취소한다
				//preventDefault : 브라우저에서 구현된 기본 동작을 취소해준다.
    			//폼 이벤트 : 폼이 제출되면 새로고침되는 기본동작을 취소
				e.preventDefault();
				
				
				let user_id = $("#user_id").val();
				let user_pw = $("#user_pw").val();
				
				if(user_id == ""){
					alert("아이디를 입력해주세요");
					return;
				}
				if(user_pw == ""){
					alert("비밀번호를 입력해주세요");
					return;
				}
				
				$.ajax({
					type: 'post',
					url: '/sns/user/signin',
					data: {
						"user_id" : user_id,
						"user_pw" : user_pw,
					},
					success:function(res){
						if(res.result){
							alert("로그인 성공");
							location.href="/sns/post";
						}else{
							alert("로그인 실패");
						}
					},
					error:function(err){
						alert("login error");
					}
				}) // ajax
			})// 로그인 버튼
		})
	</script>
	
</body>
</html>