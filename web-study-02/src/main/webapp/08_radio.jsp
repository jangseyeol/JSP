<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method ="get" action="RadioServlet">
	<label for="gender">성별 :   </label>	
	<input type="radio" id="gender" name="gender" value="남자" checked>남자
	<input type="radio" id="gender" name="gender" value="여자">여자<br><br>
	<label for="chk_mail"> 메일 정보 수신 여부 : </label>
	<input type="radio" id="chk_mail" name="chk_mail" value="yes" checked>수신
	<input type="radio" id="chk_mail" name="chk_mail" value="no"> 거부<br><br>


	<lable> 취미 : </lable>
	
	<input type = "checkbox" name= "item" value ="독서">독서
	<input type = "checkbox" name= "item" value ="운동">운동
	<input type = "checkbox" name= "item" value ="영화">영화
	<input type = "checkbox" name= "item" value ="자바">자바
	<input type = "checkbox" name= "item" value ="공부">공부
	<br><br>


	<label for="content"> 간단한 가입 인사를 적어주세요</label><br>
	<br><textarea id="content" name="content" rows="10" cols="25"></textarea><br>
	<br><input type="submit" value="전송">
</form>
</body>
</html>