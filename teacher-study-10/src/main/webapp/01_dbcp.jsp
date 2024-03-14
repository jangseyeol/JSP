<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>디비 연동</h1>
	<%
	Context initContext = new InitialContext();
	//InitialContext 객체를 생성한다.
	Context envContext  = (Context)initContext.lookup("java:/comp/env");
	DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
	//lookup 메소드로 DBCP에서 지정한 이름을 찾는다.
	//"jdbc/myoracle"이 <resource>태그의 name속성을 가리킨다.
	Connection conn = ds.getConnection();
	//lookup을 통해 얻어낸 ds(DateSource)객체로 getConnection()메소드를 호출하여 커넥션 객체를 얻어낸다.
	out.println("DBCP 연결 성공");
	%>
</body>
</html>