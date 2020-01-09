<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<script src="resources/js/jquery-3.4.1.js"></script>
		<jsp:include page="header.jsp"/>
		<style>
			 th {text-align:center}
			 .container{width:60%}
			 #myModal {display:none;}
			 #count {
			 	position : relative;
			 	top:-10px;
			 	left:-10px;
			 	background: orange;
			 	color:white;
			 	border-radius: 30%
			 }
		</style>
		<script>
			$(function(){
				function getList(){
					$.ajax({
						type : "POST",
						data : {"BOARD_RE_REF" : $("#BOARD_RE_REF").val()},
						dataType : "json",
						url : "CommentList.bo",
						success : function(rdata){
							if(rdata.length>0){
								$('#comment table').show();
								$('#comment thead').show();
								$("#comment tbody").empty();
								$('#message').text('');
								
								output = '';
								$.each(rdata,function(index, item){
									img = '';
									if(item.id == $("#loginid").val() || $("#loginid").val() == 'admin'){
										img = "<img src='resources/image/pencil2.png' width='15px' class='update'>"
												+"<img src='resources/image/remove.png' width='15px' class='remove'>"
												+"<input type='hidden' value='" + this.num + "'>";
									}				
									output += '<tr>'
													+'<td>'+item.id+'</td>'
													+'<td>'+item.content+'</td>'
													+'<td>'+item.reg_date + img;
									output += '</td>'
												+'</tr>';
								});
								$("#comment tbody").append(output);
							}else{
								$("#message").text("등록된 댓글이 없습니다.");
								$("#comment thead").hide();
							}
							$("#count").text(rdata.length);
						}
					});
				}
				getList();
				
				$('#content').on('input',function(){
					length = $(this).val().length;
					if(length>50){
						length=50;
						content = content.substring(0,length);
					}
					$(".float-left").text(length+"/50");
				});
				$('.start').click(function(){
					getList();
				});
				
				$('#write').click(function(){
					buttonText = $('#write').text();
					$('.float-left').text('총 50글자까지 가능합니다.');
					
					if(buttonText=="등록"){
						url = "CommentAdd.bo";
						data = {"id" : $("#loginid").val(), "content" : $('#content').val(), "board_re_ref":$("#BOARD_RE_REF").val()}; 
					}else{
						url = "CommentUpdate.bo";
						data = {"num" : num, "content" : $('#content').val()};
						$('#write').text('등록');
					}
					
					
					$.ajax({
						type : "POST",
						data : data,
						url : url,
						success : function(result){
							$('#content').val('');
							if(result==1){
								getList();
							}
						}
					});
				});
				
				$("#comment").on('click', '.update', function(){//바로 업데이트 클래스로 갈 수 없으니 코멘트를 경유해서 들어감
					before = $(this).parent().prev().text(); //선택한 내용을 가져옵니다.
			        $("#content").focus().val(before); //textarea에 수정전 내용을 보여줍니다.
			        num = $(this).next().next().val(); //수정할 댓글번호를 저장합니다.
			        $("#write").text("수정완료"); //등록버튼의 라벨을  '수정완료'로 변경합니다.
			        $(this).parent().parent().css('background','lightgray');//수정할 행의 배경색을 변경합니다.
			    })
				
				//remove.png를 클릭하는 경우
				$("#comment").on('click', '.remove', function(){
					 var num = $(this).next().val(); //댓글번호
					 
					 $.ajax({
							type:"post",
							url : "CommentDelete.bo",
							data : {"num": num},				
							success:function(result){
								if(result==1){
									getList();
								}
							}
						})//ajax end
				})
			});
		
		</script>
	</head>
	<body>
		<input type="hidden" id="loginid" value="${id }" name="loginid">
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
				<c:if test="${!empty boarddata.BOARD_ORIGINAL }">
					<tr>
						<td>첨부파일</td>
						<td><img src="resources/image/down.png" width="20px"><a href="BoardFileDown.bo?filename=${boarddata.BOARD_FILE }&original=${boarddata.BOARD_ORIGINAL}">${boarddata.BOARD_ORIGINAL }</a></td>
					</tr>
				</c:if>
			</table>
			<div class="form-group" align="center">	
<%-- 				<input type="hidden" id="BOARD_RE_REF" value="${boarddata.BOARD_RE_REF }"> --%>
				<button class="btn btn-primary start">댓글</button><span id="count">${count }</span>
 				<a href="BoardReplyView.bo?num=${boarddata.BOARD_NUM }"><button type="button" class="btn btn-primary">답변</button></a>
 				<c:if test="${boarddata.BOARD_NAME == id || id=='admin' }">
 					<a href="BoardModifyView.bo?num=${boarddata.BOARD_NUM }"><button type="button" class="btn btn-info">수정</button></a>
 					<a href="#"><button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">삭제</button></a>
		 		</c:if>
 				<a href="BoardList.bo"><button type="button" class="btn btn-primary">목록</button></a>
			</div>
			<div id="comment">
				<button class="btn btn-info float-left">총 50자까지 가능합니다.</button>
				<button id="write" class="btn btn-info float-right">등록</button>
				<textarea rows="3" class="form-control" id="content" maxLength="50" style="resize:none;" required="required"></textarea>
				<table class="table table-striped">
					<thead>
						<tr>
							<td>아이디</td>
							<td>내용</td>
							<td>날짜</td>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>
				<div id="message"></div>
			</div>
		</div>
		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<form name="deleteForm" action="BoardDeleteAction.bo" method="post">
							<input type="hidden" name="num" value="${param.num}" id="BOARD_RE_REF">
							<input type="hidden" name="BOARD_FILE" value="${boarddata.BOARD_ORIGINAL }" id="BOARD_FILE">
							<div class="form-group">
								<label for="pwd">비밀번호</label> 
								<input type="password" class="form-control" placeholder="Enter password" name="BOARD_PASS" id="board_pass">
							</div>
							<button type="submit" class="btn btn-primary" >Submit</button>
						    <button type="button" class="btn btn-danger">Close</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	
	</body>
</html>