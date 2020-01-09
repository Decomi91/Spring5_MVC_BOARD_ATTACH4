<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>로그인 폼 - loginForm.jsp</title>
		<link href="resources/css/login.css"
	      type="text/css"
	      rel ="stylesheet">
	    <script src="resources/js/jquery-3.4.1.js"></script>
	    <script>
			$(function(){
				$(".join").click(function(){
					location.href="join.net";
				});
				var check = $('input:text[name="id"]').val();
				if(check != null){
					$('input:checkbox[name="remember"]').attr("checked", true);
				}
			})
	      </script>
	</head>
	<body>
		<form name="myform" action="loginProcess.net" method="post">
			<h1>로그인 </h1>
		    <hr>
		    <b>아이디</b>
		    <input type="text" name="id" placeholder="Enter id" value="${saveid }" required>
		    <b>Password</b>
		    <input type="password" name="pass" placeholder="Enter password" required>
		    <input type="checkbox" name="remember" value="1"><span style="font-size: 1em; font-weight: bolder;"> Remember me</span>
		    <div class="clearfix">
		    	<button type="submit" class="submitbtn">로그인</button>
		    	<button type="button" class="join">회원가입</button>
		    </div>
		</form>
	</body>
</html>