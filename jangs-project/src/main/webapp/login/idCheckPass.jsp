<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
<h2>아이디 중복 체크</h2>
	<form action="LoginServlet" name="frm" method="get">
			<input type="hidden" name="command" value="board_check_pass">
			
			아이디 <input type="text" name="id" value="${id}">
			<input type="submit" value="중복 체크">
</body>
</html>