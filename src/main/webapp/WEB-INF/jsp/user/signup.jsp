<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sns 회원가입</title>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
<link rel="stylesheet" href="/static/css/signup.css" type="text/css">
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
				
				<div class="inputTags">
					<div class="idTag">
						<input type="text" id="user_id" name="user_id" placeholder="아이디">
						<button type="button" id="check_id" class="checkBtn">중복확인</button>
					</div>
					
					<input type="password" id="user_pw" name="user_pw" placeholder="비밀번호">
					<input type="password" id="user_pw_check" name="user_pw_check" placeholder="비밀번호 확인">
					<input type="text" id="user_name" name="user_name" placeholder="이름">
					<input type="text" id="user_birth" name="user_birth" placeholder="생년월일">
					
					<div class="emailTag">
						<input type="email" id="user_email" name="user_email" placeholder="이메일">
						<button type="button" id="check_email" class="checkBtn">중복확인</button>
					</div>
					
					<button type="submit" id="registerBtn">회원가입</button>
				</div>
			</div>
		
		</section>
	</div>
	
	<script>
		$(document).ready(function(){
			//중복체크 여부 확인
			let isCheckedId = false;
			let isCheckedEmail = false;
			
			//중복 상태 저장 변수
			let isDuplicateId = true;
			let isDuplicateEmail = true;
			
			//회원가입 버튼 누를 때
			$("#registerBtn").on("click", function(){
				let user_id = $("#user_id").val();
				let user_pw = $("#user_pw").val();
				let check_pw = $("#user_pw_check").val();
				let user_name = $("#user_name").val();
				let user_birth = $("#user_birth").val();
				let user_email = $("#user_email").val();
				
				//input 빈 값 확인
				if(user_id == ""){
					alert("아이디를 입력해주세요");
					return;
				}
				if(user_pw == ""){
					alert("비밀번호를 입력해주세요");
					return;
				}
				if(user_name == ""){
					alert("이름을 입력해주세요");
					return;
				}
				if(user_birth == ""){
					alert("생년월일을 입력해주세요");
					return;
				}
				if(user_email == ""){
					alert("이메일을 입력해주세요");
					return;
				}
				
				//중복 체크 했는지 유효성 검사
				if(!isCheckedId){
					alert("아이디 중복검사를 해주세요");
					return;
				}
				if(!isCheckedEmail){
					alert("이메일 중복검사를 해주세요");
					return;
				}
				
				//중복되었는지 유효성 검사
				if(isDuplicateId){ // true라면
					alert("이미 존재하는 아이디입니다.");
					return;
				}
				if(isDuplicateEmail){
					alert("이미 가입된 이메일입니다.")
				}
				
				//비밀번호와 비밀번호 확인 일치 여부
				if(user_pw !== check_pw){
					alert("비밀번호가 일치하지 않습니다.");
					return;
				}
						
				$.ajax({
					type:'post',
					url: '/sns/user/signup',
					data: {
						"user_id" : user_id,
						"user_pw" : user_pw,
						"user_name" : user_name,
						"user_birth" : user_birth,
						"user_email" : user_email,
					},
					success:function(res){
						if(res.result){//true라면 insert 성공
							alert("회원가입 성공");
							location.href="/sns/user/signin/view";
						}else{
							alert("다시 시도해주세요");
						}
						
					},
					error:function(err){
						alert("signup error");
					}
				})
			})//회원가입 버튼
			
			//아이디 중복 검사 클릭시
			$("#check_id").on("click", function(){
				 let user_id = $("#user_id").val();
				 
				 //input 빈 값 확인
				 if(user_id == ""){
					 alert("아이디를 입력해주세요");
					 return;
				 }
				 
				 $.ajax({
					 type: 'get',
					 url: '/sns/user/checkId',
					 data: {
						 "user_id" : user_id,
					 },
					 success:function(res){
						 isCheckedId = true;
						 
						 if(res.id){ // 사용 가능 -> 중복 없음
							alert("사용 가능한 아이디입니다.");
							isDuplicateId = false; // 중복되지 않는다.
						 }else{ // 사용 불가능 -> 중복 있음
							alert("이미 존재하는 아이디입니다.");
							isDuplicateId = true;
						 }
					 },
					 error:function(err){
						 alert("idCheck error");
					 }
				 })
			})//아이디 중복 검사
			
			//이메일 중복 검사 클릭시
			$("#check_email").on("click", function(){
				let user_email = $("#user_email").val();
				
				//input 빈 값 확인
				if(user_email == ""){
					alert("이메일을 입력해주세요");
					return;
				}
				
				$.ajax({
					type: 'get',
					url: '/sns/user/checkEmail',
					data: {
						"user_email" : user_email,
					},
					success:function(res){
						isCheckedEmail = true;
						
						if(res.email){ //사용가능. 중복 없음
							alert("사용 가능한 이메일입니다.");
							isDuplicateEmail = false;
						}else{
							alert("이미 가입한 이메일입니다.");
							isDuplicateEmail = false;
						}
					},
					error:function(err){
						alert("emailCheck error");
					}
				})
			})//이메일 중복 검사
			
			$("#user_id").on("input", function(){
				//중복체크 여부를 모두 취소
				isCheckedId = false;
				isDuplicateId = true;
			})
			
			$("#user_email").on("input", function(){
				//중복체크 여부를 모두 취소
				isCheckedEmail = false;
				isDuplicateEmail = true;
			})
			
			
			
		}) // jquery
	</script>
	
	
	
	
	
	
</body>
</html>