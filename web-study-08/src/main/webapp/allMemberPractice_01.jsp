
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%! Connection conn = null;
 	 Statement stmt = null;
 	 ResultSet rs = null;
   
 	 String url = "jdbc:oracle:thin:@localhost:1521:XE";
 	 String uid = "ezen";
 	 String pass = "1234";
 	 String sql = "select * from member";
   
 %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width = '800' border = '1'>
<tr>
	<th>이름</th><th>아이디</th><th>암호</th><th>이메일</th>
	<th>전화번호</th><th>권한(1:관리자, 0:일반회원)</th>
</tr>
<%
try{
	//1단계 : JDBC 드라이버를 로드한다.
	Class.forName("oracle.jdbc.driver.OracleDriver");
	//2단계 : 데이터베이스 연결 객체인 Connection을 생성한다.
	conn = DriverManager.getConnection(url, uid, pass);
	//Connection 객체로부터 Statement 객체를 얻어온다.
	stmt=conn.createStatement();
	//statement 객체로 executeQuery()실행한 후 결과값을 얻어와서 ResultSet 객체에 저장한다.
	rs = stmt.executeQuery(sql);
	//데이터베이스에 저장된 모든 회원 정보를 얻어 오기 위해서 반복문을 돌면서 컬럼 단위로 데이터값을 얻어온다.
	while(rs.next()){
		out.println("<tr>");
		out.println("<td>" + rs.getString("name") + "</td>");
		out.println("<td>" + rs.getString("userid")+"</td>");
		out.println("<td>" + rs.getString("pwd")+"</td>");
		out.println("<td>" + rs.getString("email")+ "</td>");
		out.println("<td>" + rs.getString("phone")+ "</td>");
		out.println("<td>" + rs.getInt("admin")+ "</td>");
		out.println("</tr>");
	}
	
}catch(Exception e){
	e.printStackTrace();
}finally{
	try{
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}
%>
</table>
</body>
</html>