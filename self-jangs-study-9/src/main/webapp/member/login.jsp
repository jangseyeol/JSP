<!-- 회원 인증을 위해 아이디와 비밀번호를 입력받는 폼 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리</title>
<!-- 입력 폼을 정상적으로 수행하기 위해서는 자바스크립트 파일이 필요하기에 이를 포함하였다. member.js에 loginCheck()함수를 정의하여 <로그인>버튼을 클릭하면 
아이디와 암호 모두가 입력되었을 때에만 로그인 처리를 하고 둘 중 하나라도 입력이 안 되면 에러 메시지를 출력하면서 진행이 되지 않도록 할 것이다. -->
<script type = "text/javascript" crc ="script/member.js"></script>
</head>
<body>
	<h2>로그인</h2>
	<form action = "login.do" method="post" name="frm">
		<table>
			<tr>
				<td>아이디	</td>
				<td><input type="text" name="userid" value="${userid}"></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="로그인" onclick="return loginCheck()">&nbsp;&nbsp;
					<input type="reset" value="취소">&nbsp;&nbsp;
					<input type="button" value="회원가입" onclick="location.href='join.do'">
				</td>
			</tr>
			<tr><td colspan="2">${message}</td></tr>
		</table>
	</form>
</body>
</html>