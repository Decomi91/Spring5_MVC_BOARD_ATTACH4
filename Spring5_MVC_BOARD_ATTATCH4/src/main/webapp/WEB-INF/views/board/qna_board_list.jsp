<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>QNA 게시판</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<script src="resources/js/jquery-3.4.1.js"></script>
		<script src="resources/js/list.js"></script>
		<jsp:include page="header.jsp"/>
		<style>
			.center-block{
				display:flex;
				justify-content: center;	/* 가운데 정렬 */
			}
		</style>
	</head>
	<body>
	
		<div class="container">
			<c:if test="${listcount > 0 }">
				<div class="row">
					<span>줄보기</span>
					<select class="form-control" id="viewcount" name="limit">
						<option value="1">1</option>
						<option value="3">3</option>
						<option value="5">5</option>
						<option value="7">7</option>
						<option value="10" selected>10</option>
					</select>
				</div>
				<table class="table table-striped">
					<thead>
						<tr>
							<th colspan = "3">MVC 게시판 - List</th>
							<th colspan="2"><font size="3">글 개수 : ${listcount }</font></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="8%">번호</th>
							<th width="50%">제목</th>
							<th width="14%">글쓴이</th>
							<th width="17%">조회수</th>
							<th width="11%">작성시간</th>
						</tr>
						<c:set var="num" value="${listcount-(page-1)*10 }"/>
						<c:forEach var="list" items="${boardlist }">
							<tr>
								<td>
									<c:out value="${num }"/>
									<c:set var="num" value="${num-1 }"/>
								</td>
<%-- 								<td>${list.BOARD_NUM }</td> --%>
								<td>
									<div>
										<c:if test="${list.BOARD_RE_LEV != 0 }">
											<c:forEach var="a" begin="0" end="${list.BOARD_RE_LEV*2 }" step="1">
												&nbsp;
											</c:forEach>
										</c:if>
										
										<c:if test="${list.BOARD_RE_LEV == 0 }">&nbsp;</c:if>
										<a href="BoardDetailAction.bo?num=${list.BOARD_NUM}">${list.BOARD_SUBJECT }</a><!--이동할 곳 -->
									</div>
								</td>
								<td>${list.BOARD_NAME }</td>
								<td>${list.BOARD_READCOUNT }</td>
								<td>${list.BOARD_DATE}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="center-block">
					<div class="row">
						<div class="col">
							<ul class="pagination">
								<c:if test="${page <= 1}">
									<li class="page-item">
										<a class="page-link gray" href="#" style="color:gray">이전&nbsp;</a>
									</li>
								</c:if>
								<c:if test="${page > 1}">
									<li class="page-item">
										<a href="BoardList.bo?page=${page-1 }" class="page-link">이전</a>&nbsp;
									</li>								
								</c:if>
								
								<c:forEach var="i" begin="${startpage }" end="${endpage }">
									<c:if test="${i==page }">
										<li class="page-item"><a href="#" class="page-link gray" style="color:gray">${i }</a></li>
									</c:if>
									<c:if test="${i!=page }">
										<li class="page-item"><a href="BoardList.bo?page=${i }" class="page-link">${i }</a></li>
									</c:if>
								</c:forEach>
								
								<c:if test="${page == endpage}">
									<li class="page-item">
										<a class="page-link gray" href="#" style="color:gray">&nbsp;다음</a>
									</li>
								</c:if>
								<c:if test="${page != endpage}">
									<li class="page-item">
										<a href="BoardList.bo?page=${page+1 }" class="page-link">&nbsp;다음</a>
									</li>								
								</c:if>								
							</ul>
						</div>
					</div>
				</div>
					
			</c:if>
			<c:if test="${listcount == 0 }">
				<font size=5>등록된 글이 없습니다.</font>
			</c:if><br>
			<button type="button" class="btn btn-info float-right" id="write">글 쓰 기</button>
		</div>
	</body>
</html>