<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<title>Insert title here</title>
</head>
<body>
<div id="wrap" align="center">
	<h1>로그인 페이지</h1>
	<form name="frm" method="post" action="LoginServlet">
	<input type="hidden" name="command" value="login_success">
	<table align="center">
			<tr>
				<td colspan="2" align="center"> 로그인</td>
			</tr>
			<tr>
				<td align="center">아이디</td>
				<td><input type="text" name="id" value="subin"></td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td><input type="password" name="pwd" value="2222"></td>
			</tr>
			<tr>
				<td align="center">레벨</td>
				<td><select name="lev">
					<option value = "A">운영자</option>
					<option value = "B">일반회원</option>
				</select></td>
			</tr>
			<tr align="center">
				<td colspan="2">
					<input type="submit" value="로그인">
					<input type="reset" value="취소">
				</td>
			</tr>			
		</table>
		<div style="color: red; text-align: center;">${message}</div>
		</form>
</div>
</body>
</html>