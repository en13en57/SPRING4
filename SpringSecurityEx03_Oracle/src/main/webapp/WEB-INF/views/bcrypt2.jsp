<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	검사 1 : ${isCheck1 } <br />
	검사 2 : ${isCheck2 } <br />
	DB   암호 : ${dbPassword } <br />
	현재 암호 : ${newPassword } <br />
	
	<hr />
	<a href="${pageContext.request.contextPath }">홈으로</a>
</body>
</html>