<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<h2>아이디 중복 체크</h2>
	<form action="idCheck.do" method="get" name="frm">
	<!-- 중복체크 버튼을 누르면 idCheck.do 가 요청되어 또다시 아이디 중복 체크를 진행한다. -->
	
		아이디<input type="text" name="userid" value="${userid}">
		<input type ="submit" value="중복 체크">
		
		<br>
		<c:if test = "${result == 1}">
			<script type ="text/javascript">
				opener.document.frm.userid.value="";
			/* 회원 가입 폼에서 입력받은 아이디를 지운다. */	
				
			</script>
		<!-- 서블릿에서 넘겨준 result 값이 1이면 이미 사용 중인 아이디이기 때문에 아이디를 새로 입력 받아야 한다. -->
			${userid}는 이미 사용 중인 아이디 입니다.
		<!-- 사용중인 아이디임을 출력한다. -->
		</c:if>
		
		<c:if test="${result == -1}">
			<span>
			<div style="color:red">
			${userid}는 사용 가능한 아이디입니다.
			</div>
			<input type = "button" value="사용" class="cancel" onclick="idok()">
			</span>
		</c:if>
		<!-- 서블릿에서 넘겨준 result 값이 -1이면 등록되지 않은 아이디이기에 사용 가능하다는 메시지를 출력하고 
		<사용>버튼이 나타난다. <사용>버튼을 클릭하면 자바스크립트 함수 idok()가 호출된다. -->
		
	</form>
</body>
</html>