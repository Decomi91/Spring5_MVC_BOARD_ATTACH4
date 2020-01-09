<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
  #header{text-align:right;
  background:#096988;
  color:white;margin-bottom:50px}
  #header a{color:white}
  a{text-decoration:none;}
</style>

<div id="header">
	<c:if test="${empty id }">
		<script>
			location.href="../index.jsp";
		</script>
	</c:if>
	<c:if test="${!empty id }">
		<c:if test="${id=='admin' }">
		    <span>관리자 admin 님 환영합니다.</span>
		     <a href="member_list.net">관리자모드 접속(회원 목록 보기)</a>
		     <a href="BoardList.bo">(게시판)</a>
		</c:if>
		<c:if test="${id!='admin' }">
			<span>일반회원 ${id } 님 환영합니다.</span>
		</c:if>
	</c:if>
	<span>(<a href="member_update.net?id=${id }">정보수정</a>)
	(<a href="logout.net">로그아웃</a>)</span>
</div>
