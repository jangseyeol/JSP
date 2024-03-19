<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	.main{
		margin: 0 auto;
	}
	td{
		border: 1px solid black;
		width: 200px;
		text-align: center;		
	}
	td a{
		text-decoration:  none;
	}
</style>

</head>
<body>
	<table class="main">
		<c:if test="${empty loginUser}">
			<tr>
				<td></td>
				<td><a href="LoginServlet?command=login_start" style="text-decoration: none;">로그인</a></td>
				<td><a href="LoginServlet?command=join_form" style="text-decoration: none;">회원가입</td>
				<td> 게시판<br>
					<span style="color: red">(로그인 후 사용가능)</span>
				</td>
				<td> 마이페이지<br>
					<span style="color: red">(로그인 후 사용가능)</span>
				</td>
			</tr>
		</c:if>
		
		<c:if test="${!empty loginUser}">
			<tr>
				<td>${loginUser.name}님 반갑습니다</td>
				<td>레벨 : ${loginUser.lev}</td>
				<td><a href="LoginServlet?command=logout">로그아웃</a></td>
				
				<td><a href="BoardServlet?command=board_list" style="text-decoration: none;">게시판</a></td>
				<c:if test="${result==2}">
					<td><a href="custom.do">사원등록</a></td>
				</c:if>

				<c:if test="${result==3}">
					<td>
						사원등록<br>
						<span style="color:red">(관리자로 로그인 후 사용 가능)</span>
					</td>
				</c:if>
				
				<td><a href="mypage.do"> 마이페이지</a></td>
			</tr>
		</c:if>
		
	</table>
	<br>
</body>
</html>