<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>멤버 리스트 페이지</title>
		<link href="css/join.css" type="resources/text/css" rel="stylesheet">
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
		<script>
			$(function(){
				var check = false;
				$('.del').click(function(){
					check = confirm('삭제하시겠습니까?');
					
					if(check){
						return true;
					}else{
						return false;
					}				
					
					check=false;
				});
				
				
			});
			
		
		</script>
	</head>
	<body>
		<c:if test = "${listcount>0 }"> 
			<input type="hidden" id="sfield" value="${search_field}">
			<input type="hidden" id="sword" value="${search_word}">
			<script>
			$(function(){
				var selectedValue = $('#sfield').val();
				console.log(selectedValue);
				if(selectedValue != '-1'){
					$('#viewcount').val(selectedValue);
				}
			});
			</script>
			<div class="container">
				<form action="member_list.net">
					<div class="input-group">
						<select id="viewcount" name="search_field">
							<option value="0" selected>아이디</option>
							<option value="1">이름</option>
							<option value="2">나이</option>
							<option value="3">성별</option>
						</select>
						<input name="search_word" type="text" class="form-control" placeholder="Search" value="${search_word }">
						<button class="btn btn-primary" type="submit">검색</button>
					</div>
				</form>
			
			
				<table class="table table-striped">
					<caption >
					
					</caption>
					<thead>
						<tr>
							<th>번호</th>
							<th>ID</th>
							<th>Password</th>
							<th>이름</th>
							<th>나이</th>
							<th>성별</th>
							<th>Email</th>
							<th>삭제</th>
						</tr>
					</thead>
					<c:forEach var="member" items="${members}" varStatus="status">
						<tr>
							<td>${status.count + (page-1)*10}</td>
							<td><a href="member_info.net?id=${member.id }">${member.id}</a></td>
							<td>${member.password}</td>
							<td>${member.name}</td>
							<td>${member.age}</td>
							<td>${member.gender}</td>
							<td>${member.email}</td>
							<td><a class="del" href="memberDeleteProcess.net?id=${member.id }" >삭제</a></td>
						</tr>
					</c:forEach>
				</table>
				<div class="center-block">
					<div class="row">
						<div class="col">
							<ul class="pagination">
								<c:if test="${page <= 1}">
									<li class="page-item">
										<a class="page-link" href="#" style="color:gray; cursor: default;">이전&nbsp;</a>
									</li>
								</c:if>
								<c:if test="${page > 1}">
									<li class="page-item">
										<a href="member_list.net?page=${page-1 }&search_field=${search_field}&search_word=${search_word}" class="page-link">이전</a>&nbsp;
									</li>								
								</c:if>
								
								<c:forEach var="i" begin="${startpage }" end="${endpage }">
									<c:if test="${i==page }">
										<li class="page-item"><a href="#" class="page-link" style="color:gray; cursor: default;">${i }</a></li>
									</c:if>
									<c:if test="${i!=page }">
										<li class="page-item"><a href="member_list.net?page=${i }&search_field=${search_field}&search_word=${search_word}" class="page-link">${i }</a></li>
									</c:if>
								</c:forEach>
								
								<c:if test="${page == endpage}">
									<li class="page-item">
										<a class="page-link" href="#" style="color:gray; cursor: default;">&nbsp;다음</a>
									</li>
								</c:if>
								<c:if test="${page != endpage}">
									<li class="page-item">
										<a href="member_list.net?page=${page+1 }&search_field=${search_field}&search_word=${search_word}" class="page-link">&nbsp;다음</a>
									</li>								
								</c:if>								
							</ul>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</body>
</html>