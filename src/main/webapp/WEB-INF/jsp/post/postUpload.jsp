<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 올리기</title>
<link rel="stylesheet" href="/static/css/style.css" type="text/css">
<link rel="stylesheet" href="/static/css/postUpload.css" type="text/css">

<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

</head>
<body>
	<div class="container">
		<c:import url="/WEB-INF/jsp/include/header.jsp" />
		
		<section>
			<div class="postBox">
				<div class="userDataDiv">
					<img src="#" class="userProfileImg">
					<p>userName</p>
				</div>
				
				<div class="postContents">
					<div class="textContent">
						<textarea id="post_content" name="post_content" placeholder="내용을 입력해주세요"></textarea>
					</div>
					
					<div class="fileImgs">
						<div class="inputImgs">
							<img class="imgs" src="#" id="testimg">
							<img class="imgs" src="#">
							<img class="imgs" src="#">
							<img class="imgs" src="#">
							<img class="imgs" src="#">
						</div>
					</div>
				</div>
				
				<div class="uploadBtns">
					<div class="fileBtns">
						<label for="post_file" class="fileBtn">CHOOSE A FILE</label>
						<input type="file" id="post_file" name="post_file" multiple>
						
						<button type="button" class="selectBtn" id="selectBtn">선택완료</button>
					</div>
					<button type="submit" class="uploadBtn" id="uploadBtn">UPLOAD</button>
				</div>
			</div>
		</section>
	</div>
	
	<script>
		$(document).ready(function(){
			
			
			
			$("#uploadBtn").on("click", function(){
				let post_content = $("#post_content").val();
				
				//input 빈 값
				if(post_content == ""){
					alert("내용을 입력해주세요");
					return;
				}
				
				// 폼 객체 생성
				let formData = new FormData();
				
				console.log('post_file 확인 >> ', $("#post_file")[0]);
				console.log('post_file[0].files[0] >> ', $("#post_file")[0].files[0]);
				
				formData.append('post_content', post_content);
				
				//formData.append('post_file', $("#post_file")[0].files[0]);
				
				//file을 선택안하면 $("#post_file")[0].files[0]은 undefined로 뜬다.
				
				if( $("#post_file")[0].files[0] == undefined){
				//if( $("#post_file")[0].files[0] == null){
				//if( $("#post_file")[0].files[0].lenght == 0){
					formData.append('post_file', null);
					
				}else{
					formData.append('post_file', $("#post_file")[0].files[0]);							
				}
				
				
				$.ajax({
					method: 'post',
					url: '/sns/post/upload',
					data: formData,
					enctype: "multipart/form-data",
					processData:false,
					contentType:false,
					success:function(res){
						if(res.result){
							//insert 성공
							alert("insert 성공");
						}else{
							alert("insert 실패");
						}
					},
					error:function(err){
						alert("post upload error");
					}
				})//ajax
			}) //upload btn
			
			$("#selectBtn").on("click", function()
			{
				let file_img = $("#post_file").val();
				//let testimg = $("#testimg");
				
				console.log('post_file 확인 >> ', $("#post_file")[0]);
				console.log('post_file[0].files[0] >> ', $("#post_file")[0].files[0]);		
				console.log('post_file[0].files[0] >> ', $("#post_file")[0].files[0].type);
				console.log('post_file[0].files[0] >> ', $("#post_file")[0].files[0].name);
				
				
				
				$("#testimg").attr("src","123");
				//console.log(file_img);
			})
			
			$("#post_file").on("change", function(e){
				
				var file = e.target.files[0];
				
				var reader = new FileReader();
				
				
				reader.onload = function(e){
					$("#testimg").attr("src", e.target.result);
				}
				
				reader.readAsDataURL(file);
				
			})
			
			
		})//jquery 
	</script>
</body>
</html>