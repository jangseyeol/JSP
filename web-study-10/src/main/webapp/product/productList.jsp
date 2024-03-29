<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/shopping.css">
<title>Insert title here</title>
</head>
<body>
	<div id="wrap" align="center">
	
		<h1>상품 리스트 - 관리자 페이지</h1>
		<table class="list">
			<tr>
				<td colspan="5" style="border : white; text-align: right;">
					<a href="productWrite.do">상품등록</a>
				</td>
			</tr>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>가격</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
			<c:forEach items="${productList}" var ="product">
			<!-- 서블릿에서 속성에 실어 넘겨준 productList에서 상품 정보를 한 개씩 얻어서 var속성 값인 product에 저장해 두기 위해 c:forEach 태그를 사용한다. -->
			<tr>
				<td>${product.code}</td><!-- 상품 정보 한 개를 저장해 둔 product에서 상품 코드를 얻어와 출력한다.  -->
				<td>${product.name}</td><!-- 상품 정보 한 개를 저장해 둔 product에서 상품명을 얻어와 출력한다.  -->
				<td><fmt:formatNumber value="${product.price}" pattern="#,###" />원</td><!-- 상품 정보 한 개를 저장해 둔 product에서 상품 가격을 얻어와 출력한다.  -->
				<!-- 위에 이 표현은 인터넷에서 복사해서 옴 -->
				<td>
					<a href="productUpdate.do?code=${product.code}">상품 수정</a><!-- 해당 상품에 대한 수정을 위한 페이지로 이동하기 위한 링크를 추가한다.  -->
				</td>
				<td>
					<a href="productDelete.do?code=${product.code}">상품 삭제</a><!-- 해당 상품에 대한 삭제을 위한 페이지로 이동하기 위한 링크를 추가한다.  -->
				</td>
			</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>