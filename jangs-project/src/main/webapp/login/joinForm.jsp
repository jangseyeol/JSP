<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="script/member.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form name="frm" method="post" action="LoginServlet">
	<input type="hidden" name="command" value="join_save_form">
	
	<table align="center" width="550">
		<tr>
			<td colspan="2" align="center"> 회원가입</td>
		</tr>
		<tr>
			<td align="center">아이디</td>
			<td><input type="text" name="id">
			<input type="button" value="중복 체크" onclick="idCheck()">
			<input type="hidden" name="reid" size="20"> 
			</td>
		</tr>
		<tr>
			<td align="center">비밀번호</td>
			<td><input type="password" name="pwd"></td>
		</tr>
		<tr>
			<td align="center">이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		
		<tr>
			<td align="center">권한</td>
			<td><select name="lev">
				<option value = "A">운영자</option>
				<option value = "B">일반회원</option>
			</select></td>
		</tr>
		<tr>
			<td align="center">성별</td>
			<td><select name="gender">
				<option value = "1">남자</option>
				<option value = "2">여자</option>
			</select></td>
		</tr>
		<tr>
			<td align="center">전화번호</td>
			<td colspan="3">
			<input type="text" name="phone"></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="submit" value="등록">
				<input type="reset" value="취소">
				<input type="button" value="메인 페이지로 이동" onclick="location.href='login.do'">
			</td>
		</tr>	
	</table>
	</form>
</body>
</html>