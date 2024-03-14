<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri ="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.main{
	margin : 0 auto;
	}
	td{
		border: 1px solid black;
		width : 200px;
		text-align: center;

		}

	td a{
			text-decoration: none;
	}	
</style>

</head>
<body>
	<table class="main">
	<!-- loginUser 가 없을때 -->
		<c:if test="${empty loginUser}">
			<tr>
				<td></td><td></td> <!--2칸 비우기-->
				<td><a href="login.do">로그인</a></td> <!-- login.do로 연결, 밑줄없애기 -->
				<td width = "250px">
					사원등록<br>
					<span style="color:red">(관리자로 로그인 후 사용 가능)</span>
				</td>
				<td>
					마이페이지<br>
					<span style="color:red">(로그인 후 사용 가능)</span>
				</td>
			</tr>
		</c:if>	
		
	<!-- loginUser가 있을 때  -->	
		<c:if test="${!empty loginUser}">
			<tr>
				<td>${loginUser.name}님 반갑습니다.</td>
				
				<td>레벨 : ${loginUser.lev}</td>	<!-- loginUser로 묶어놨기 때문에 이렇게 씀 -->
				
				<td><a href="logout.do">로그아웃</a></td>
				
					<c:if test="${result==2}"> <!-- loginServlet 에서 result 로 설정해줌 -->
						<td><a href="custom.do">사원등록</a></td>
					</c:if>
					
					<c:if test="${result==3}"> <!-- loginServlet 에서 result 로 설정해줌 -->
						<td>
						사원등록<br>
						<span style="color:red">(관리자로 로그인 후 사용 가능)</span>
						</td>
					</c:if>
				
				<td><a href="mypage.do">마이페이지</a></td> 
			</tr>
		</c:if>
		
	</table> 
	<br>
</body>
</html>