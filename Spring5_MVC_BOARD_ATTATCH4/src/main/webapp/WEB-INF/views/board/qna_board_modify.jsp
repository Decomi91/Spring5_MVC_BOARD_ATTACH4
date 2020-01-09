<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 수정</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
		<script src="resources/js/jquery-3.4.1.js"></script>
		<jsp:include page="header.jsp"/>
		<style>
			 tr.center-block {text-align:center}
			 h1{font-size:1.5rem; text-align:center; color:#1a92b9}
			 .container{width:60%}
			 label{font-weight:bold}
			 #upfile{display:none} 
		     img{width:20px;}
		</style>
		
		<script>
			$(function(){
				$('form').submit(function(){
					if($.trim($('input:eq(6)').val()) == ""){
						alert("비밀번호 입력 필요");
						$('input:eq(6)').focus();
						return false;
					}
					
					if($.trim($('input:eq(5)').val()) == ""){
						alert("글제목 입력 필요");
						$('input:eq(5)').focus();
						return false;
					}
					
					if($.trim($('textarea').val()) == ""){
						alert("내용 입력 필요");
						$('textarea').focus();
						return false;
					}
					
				});
				
				$("#upfile").change(function(){
			    	$('#filevalue').val('');
			    	var inputfile=$(this).val().split('\\');
			    	$('#filevalue').text(inputfile[inputfile.length-1]);
			    	$('input[name=BOARD_ORIGINAL]').val(inputfile[inputfile.length-1]);
			    });
				
				$(".remove").click(function(){
					check=1;
					$('#filevalue').text('');
					$('input[name=BOARD_FILE]').val('');
					$('input[name=BOARD_ORIGINAL]').val('');
					$(this).css('display','none');
				})
			})
		</script>
	</head>
	<body>
		<div class="container">
			<form action="BoardModifyAction.bo" method="post" name="boardform" enctype="multipart/form-data">
				<input type="hidden" name="BOARD_NUM" value="${board.BOARD_NUM }">
				<input type="hidden" name="BOARD_FILE" value="${board.BOARD_FILE }">
				<input type="hidden" name="BOARD_ORIGINAL" value="${board.BOARD_ORIGINAL }">
				<input type="hidden" name="before_file" value="${board.BOARD_FILE }">
				<h1>MVC 게시판-reply 페이지</h1>
				<div class="form-group">
					<label for="board_name">글쓴이</label>
					<input name="BOARD_NAME" id="board_name" value="${board.BOARD_NAME }" readOnly type="text" size="10" maxlength="30" class="form-control" placeholder="Enter board name">
				</div>
   				<div class="form-group">
     				<label for="board_subject">제목</label>	
	 				<input name="BOARD_SUBJECT" id="board_subject" type="text" size="50" maxlength="100" class="form-control" placeholder="Enter board_subject" value="${board.BOARD_SUBJECT }">
   				</div>
   				<div class="form-group">
					<label for="board_content">내용</label>	
					<textarea name="BOARD_CONTENT" id="board_content" cols="67" rows="10" class="form-control">${board.BOARD_CONTENT }</textarea>
   				</div>
				<div class="form-group">
					<label for="board_pass">비밀번호</label>	
					<input name="BOARD_PASS" id="board_pass" type="password" class="form-control" value="${board.BOARD_PASS }">
   				</div>
   				<c:if test="${board.BOARD_RE_LEV==0 }">
   					<div class="form-group read">
   						<label for="board_file">파일 첨부</label>
   						<label for="upfile"><img src="resources/image/attach.png" alt="파일첨부" width="20px"></label>
   						<input type="file" id="upfile" name="uploadfile">
   						<span id="filevalue">${board.BOARD_ORIGINAL }</span>
   						<img src="resources/image/remove.png" alt="파일삭제" width="10px" class="remove">
   					</div>
   				</c:if>
   				<div class="form-group">
	 				<input type=submit class="btn btn-primary" value="등록">
			 		<input type=reset class="btn btn-danger" onclick="history.go(-1)" value="취소">
   				</div>
			</form>
		</div>
	</body>
</html>