<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${member.id }의 정보 페이지</title>
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
		.center-block{
				display:flex;
				justify-content: center;	/* 가운데 정렬 */
			}
		</style>
		<jsp:include page="../board/header.jsp"></jsp:include>
		<script src="resources/js/jquery-3.4.1.js"></script>
	</head>
	<body>
		<div class="container">
			<h1>${member.id }의 정보</h1>
			<table class="table table-striped">
				<tr>
					<th>ID</th>
					<td>${member.id}</td>
				</tr>
				<tr>
					<th>Password</th>
					<td>${member.password}</td>
				</tr>
				<tr>
					<th>이름</th>
					<td>${member.name}</td>
				</tr>
				<tr>
					<th>나이</th>
					<td>${member.age}</td>
				</tr>
				<tr>
					<th>성별</th>
					<td>${member.gender}</td>
				</tr>
				<tr>
					<th>Email</th>
					<td>${member.email}</td>
				</tr>
				<tr align="center">
					<th colspan=2><a href="#" onclick="history.go(-1)">이전 페이지로 돌아가기</a></th>
				</tr>				
			</table>
		</div>
	</body>
</html>