<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	Connection con = null;
	Statement stmt = null;
	String username = "ezen";
	String password = "1234";
	
	PreparedStatement pstmt = null;
	String sql = "insert into member values(?,?,?)";
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 	
	request.setCharacterEncoding("utf-8");

	String name = request.getParameter("name");
	String address = request.getParameter("address");
	String ssn = request.getParameter("ssn");

try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, "ezen","1234");
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, name);
		pstmt.setString(2, address);
		pstmt.setString(3, ssn);
		
		int result = pstmt.executeUpdate();
		System.out.println("저장 성공? : " + result);
}catch(Exception e){
	e.printStackTrace();
}finally{
	try{
		if(pstmt != null) pstmt.close();
		if(con != null) con.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
		
		
%>
</body>
</html>