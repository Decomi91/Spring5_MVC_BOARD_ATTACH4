<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>게시글 답변</title>
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
					if($.trim($('input:eq(5)').val()) == ""){
						alert("비밀번호 입력 필요");
						$('input:eq(1)').focus();
						return false;
					}
					
					if($.trim($('input:eq(4)').val()) == ""){
						alert("글제목 입력 필요");
						$('input:eq(2)').focus();
						return false;
					}
					
					if($.trim($('textarea').val()) == ""){
						alert("내용 입력 필요");
						$('textarea').focus();
						return false;
					}
				});
			})
		</script>
	</head>
	<body>
		<div class="container">
			<form action="BoardReplyAction.bo" method="post" name="boardform">
				<input type="hidden" name="BOARD_RE_REF" id="board_re_ref" value="${board.BOARD_RE_REF }">
				<input type="hidden" name="BOARD_RE_LEV" id="board_re_lev" value="${board.BOARD_RE_LEV }">
				<input type="hidden" name="BOARD_RE_SEQ" id="board_re_seq" value="${board.BOARD_RE_SEQ }">
				<h1>MVC 게시판-reply 페이지</h1>
				<div class="form-group">
					<label for="board_name">글쓴이</label>
					<input name="BOARD_NAME" id="board_name" value="${id }" readOnly type="text" size="10" maxlength="30" class="form-control" placeholder="Enter board name">
				</div>
   				<div class="form-group">
     				<label for="board_subject">제목</label>	
	 				<input name="BOARD_SUBJECT" id="board_subject" type="text" size="50" maxlength="100" class="form-control" placeholder="Enter board_subject" value="RE: ${board.BOARD_SUBJECT }">
   				</div>
   				<div class="form-group">
					<label for="board_content">내용</label>	
					<textarea name="BOARD_CONTENT" id="board_content" cols="67" rows="10" class="form-control"></textarea>
   				</div>
				<div class="form-group">
					<label for="board_pass">비밀번호부</label>	
					<input name="BOARD_PASS" id="board_pass" type="password" class="form-control">
   				</div>
   				<div class="form-group">	
					
	 				<input type=submit class="btn btn-primary" value="등록">
			 		<input type=reset class="btn btn-danger" onclick="history.go(-1)" value="취소">
   				</div>
			</form>
		</div>
	</body>
</html>