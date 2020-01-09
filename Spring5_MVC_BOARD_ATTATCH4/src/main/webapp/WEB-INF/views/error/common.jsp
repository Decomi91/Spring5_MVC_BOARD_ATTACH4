<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>common.jsp</title>
</head>
<body>
죄송합니다<br>
<img src="resources/image/error.png" width="100px"><br>
요청하신 <b>${url }</b> 처리에 오류가 발생했습니다.
<hr>
${exception}
</body>
</html>