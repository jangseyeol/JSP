<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>정보 입력 폼</h1>
<form method="post" action="itemWriteResult.jsp">
	<tr>
		<td>상품명</td>
		<td><input type="text" name = "name" size= "20"></td>
	</tr><br>
	<tr>
		<td>가격</td>
		<td><input type="text" name = "price" size="20"></td>
	</tr><br>
	<tr>
		<td>설명</td>
		<td><input type="text" name = "description" style="width:800px; height:200px;"></td>
	</tr><br>
	<tr>
		<td><input type ="submit" value="전송" style=""></td>
		<td><input type = "reset" value="취소"></td>
	</tr>
	
</form>
</body>
</html>