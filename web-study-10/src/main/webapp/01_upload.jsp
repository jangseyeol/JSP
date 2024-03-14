<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "upload.do" method="post" enctype="multipart/form-data">
		글쓴이 : <input type="text" name ="name"><br>
		제목 : <input type ="text" name = "title"><br>
		파일 지정하기 : <input type="file" name = "uploadFile"><br>
		<!-- type를 file로 지정해야 파일을 선택할 수 있는 버튼을 만들 수 있다. -->
		<input type="submit" value="전송">
	</form>
</body>
</html>