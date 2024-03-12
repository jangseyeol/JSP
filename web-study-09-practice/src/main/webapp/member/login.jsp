<!-- lonin.jsp : 회원인증을 위한 아이디와 비밀번호를 입력받는 폼 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/member.js"></script>
<!-- 입력 폼을 정상적으로 수행하기 위해서는 자바스크립트 파일(member.js)이 필요하기에 이를 포함하였다.
member.js에 loginCheck() 함수를 정의하여 밑에 '로그인' 버튼을 클릭하면 아이디와 암호가 모두 입력되었을 때에만 로그인 인증 처리를 하고 
둘 중 하나라도 입력이 안 되면 에러 메시지를 출력하면서 진해이 되지 않도록 할 것이다. -->
</head>
<body>
	<h2>로그인</h2>
	<form action = "login.do" method="post" name = "frm">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" value="${userid}"?></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pwd"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type = "submit" value="로그인" onclick="return loginCheck()">&nbsp;&nbsp;
				<!-- 로그인 버튼이 submit 형식으로 작성되었으므로 이를 클릭하면 form 태그의 action속성에 기술한 "login.do"가 요청된다.
				요청에 대한 처리를 서블릿에서 한다. 이처럼 서블릿은 클래스 이름으로 요청하기보다는 login.do와 같이 일정한 패턴 방식을 사용한다. -->
				
				<input type = "reset" value="취소">&nbsp;&nbsp;
				<input type = "button" value="회원 가입" onclick="location.href='join.do'"></td>
				<!-- '회원 가입' 버튼을 클릭하면 회원 가입을 위한 "join.do"가 요청된다. -->
			</tr>
			<tr>
				<td colspan="2">${message}</td>
			</tr>
		</table>
	</form>
</body>
</html>