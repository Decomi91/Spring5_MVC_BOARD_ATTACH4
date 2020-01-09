<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<script src="js/jquery-3.4.1.js"></script>
		<jsp:include page="header.jsp"/>
		<style>
			 th {text-align:center}
			 .container{width:60%}
		</style>
	</head>
	<body>
		<div class="container">
			<table class="table table-striped">
				<tr>
					<th colspan="2">MVC게시판 - view페이지</th>
				</tr>
				<tr>
					<td width="25%">글쓴이</td>
					<td>${boarddata.BOARD_NAME }</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>${boarddata.BOARD_SUBJECT }</td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="BOARD_CONTENT" id="board_content" cols="67" rows="10" class="form-control"  readonly>${boarddata.BOARD_CONTENT }</textarea></td>
				</tr>
				<c:if test="${!empty boarddata.BOARD_FILE }">
					<tr>
						<td>첨부파일</td>
						<td><img src="image/down.png" width="20px"><a href="BoardFileDown.bo?filename=${boarddata.BOARD_FILE }">${boarddata.BOARD_FILE }</a></td>
					</tr>
				</c:if>
			</table>
			<div class="form-group" align="center">	
 				<a href="BoardReplyView.bo?num=${boarddata.BOARD_NUM }"><button type="button" class="btn btn-primary">답변</button></a>
 				<c:if test="${boarddata.BOARD_NAME == id || id=='admin' }">
 					<a href="BoardModifyView.bo?num=${boarddata.BOARD_NUM }"><button type="button" class="btn btn-info">수정</button></a>
 					<a href="BoardDelete.bo?num=${boarddata.BOARD_NUM }"><button type="button" class="btn btn-danger">삭제</button></a>
		 		</c:if>
 				<a href="BoardList.bo"><button type="button" class="btn btn-primary">목록</button></a>
			</div>
		</div>
	</body>
</html>