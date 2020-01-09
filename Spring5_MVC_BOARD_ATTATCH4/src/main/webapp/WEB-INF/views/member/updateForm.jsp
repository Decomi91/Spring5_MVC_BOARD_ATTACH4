<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>회원관리 페이지</title>
<link href="resources/css/join.css" type="text/css" rel="stylesheet">
<style>
b {
	width: 100%;
	display: block
}

input[name=email] {
	width: 60%
}

#message, #email_message {
	width: 35%;
	display: inline-block
}
</style>
<jsp:include page="../board/header.jsp"></jsp:include>
<script src="resources/js/jquery-3.4.1.js"></script>
<script>
	$(function() {
		var checkemail=false;
		$('form').submit(function() {
			if(!checkemail){
				alert("email 형식을 확인하세요");
				$("input:eq(6)").focus();
				return false;
			}
			
		}); //submit

		$("input:eq(6)").on('keyup',function() {
				$("#email_message").empty();
				var pattern = /^\w+@\w+[.]\w{3}$/;
				var email = $("input:eq(6)").val();
				if (!pattern.test(email)) {
					$("#email_message").css('color', 'red').html("이메일형식이 맞지 않습니다.");
					checkemail=false;
				}else{
					$("#email_message").css('color', 'green').html("이메일형식에 맞습니다.");
					checkemail=true;
				}
			});
	})
</script>
</head>
<body>
	<form name="userform" action="updateProcess.net" method="post">
		<h1>회원관리 페이지</h1>
		<hr>
		<b>아이디</b> 
		<input type="text" name="id" value="${member.id }" readonly="readonly"> 
	    <b>비밀번호</b><input
			type="password" name="password" placeholder="Enter password" value="${member.password }" required>
		<b>이름</b><input type="text" name="name" placeholder="Enter name" value="${member.name }" maxlength=15 required>
		<b>나이</b><input type="text" name="age" value="${member.age }" readonly="readonly">
		<b>성별</b>
		<div>
			<c:choose>
			<c:when test="${member.gender == '남' }">
				<input type="radio" name="gender" value="남" checked><span>남자</span>
				<input type="radio" name="gender" value="여"><span>여자</span>
			</c:when>
			<c:otherwise>
				<input type="radio" name="gender" value="남"><span>남자</span>
				<input type="radio" name="gender" value="여" checked><span>여자</span>
			</c:otherwise>
			</c:choose>
		</div>
		<b>이메일 주소 </b><input type="text" name="email" placeholder="Enter email" value="${member.email }" required><span id="email_message"></span>
		<div class="clearfix">
			<button type="submit" class="submitbtn">수정</button>
			<button type="button" class="cancelbtn" onclick="history.go(-1)">돌아가기</button>
		</div>
	</form>
</body>
</html>
