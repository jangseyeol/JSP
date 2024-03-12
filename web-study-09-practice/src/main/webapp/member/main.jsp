<!-- 회원 인증 확인 후 다양한 서비스를 제공하는 폼  -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty loginUser}">
	<jsp:forward page='login.do' />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="script/member.js"></script>
</head>
<body>
	<h2>회원 전용 페이지</h2>
	<form action="logout.do">
		<table>
			<tr>
				<td>안녕하세요. ${loginUser.name}(${loginUser.userid})님</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type ="submit" value="로그아웃">&nbsp;&nbsp;
				<!--로그아웃을 클릭하면 logout.do가 요청되며 이 요청을 받는 서블릿에서 logout.do로 이동시킨다.
				logout.do에서는 인증된 사용자의 인증을 무효화시킨다.  -->
		
					<input type = "button" value="회원정보변경"
						onclick="location.href='memberUpdate.do?userid=${loginUser.userid}'">
				<!--회원정보변경 버튼을 클릭하면 회원 정보 수정 페이지로 이동한다. 이전에 입력된 회원 정보를 보여주여야 하기 때문에 
				memberUpddate.do요청시 사용자 아이디를 파라미터로 전달한다.  -->
		
				</td>
			</tr>
		</table>
	</form>
</body>
</html>