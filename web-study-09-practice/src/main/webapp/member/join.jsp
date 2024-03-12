<!-- 회원가입을 위해 정보를 입력받는 폼 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type = "text/javascript" src="script/member.js"></script>
<!-- 입력 폼을 정상적으로 수행하기 위해서는 자바스크립트 파일(member.js)이 필요하다.
회원 정보를 입력한 후에 '확인' 버튼을 클릭하면 joinCheck()함수를 호출하는데 이 함수는 자바스크립트 파일에 정의되어 있다.
joinCheck()함수는 입력이 제대로 되었는지 검사한 후에 frm 폼의 action 속성에 지정된 파일로 제어를 옮기는 역할을 한다.  -->

</head>
<body>
	<h2>회원가입</h2>
	* 표시 항목은 필수 입력 항목입니다.
	<form action="join.do" method="post" name="frm">
	<!-- action속성 값이 join.do이기에 '확인' 버튼이 클릭되면 join.do로 요청한다. -->
	
		<table>
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" size="20">*</td>
			</tr>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userid" size="20" id="userid">*
				<input type = "hidden" name="reid" size="20">
				<input type = "button" value="중복 체크" onclick="idCheck()"></td>
				<!-- 데이터베이스를 설계하면서 아이디에 중복된 값을 저장하지 못하도록 primary key 제약조건을 설정해 두었기 때문에
				이미 입력된 아이디로 insert문을 수행하게 되면 에러가 발생하게 된다.
				그래서 회원 정보를 데이터베이스에 추가하기 전에 이미 등록된 회원인지 아이디 중복 체크를 해야한다.
				이를 위해서 <중복체크>버튼을 추가한다. 이 버튼을 클릭하면 idCheck() 자바스크립트 함수를 호출한다. -->
				
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pwd" size="20">*</td>
			</tr>
			<tr height="30">
				<td width="80">암호 확인</td>
				<td><input type="password" name="pwd_check" size="20">*</td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" size="20"></td>
			</tr>
			<tr>
				<td>전화번호</td>
				<td><input type="text" name="phone" size="20"></td>
			</tr>
			<tr>
				<td>등급</td>
				<td><input type="radio" name="admin" value="0" checked="checked">일반회원
				<input type="radio" name="admin" value="1"> 관리자</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="submit" value="확인" onclick="return joinCheck()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" value="취소">
				</td>
			</tr>
			<tr>
				<td colspan="2">${message}</td>
			</tr>
		</table>
	</form>
</body>
</html>