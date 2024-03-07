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
	ResultSet rs = null;
	String sql = "select * from employee";
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table width = '800' border='1'>
<% 	try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, "ezen","1234");
		stmt = con.createStatement();
		rs = stmt.executeQuery(sql);
		
		while(rs.next()){
			out.println("<tr>");
			out.println("<td>" + rs.getString("name") + "</td>");
			out.println("<td>" + rs.getString("address") + "</td>");
			out.println("<td>" + rs.getString("ssn") + "</td>");
			out.println("</tr>");
		}
}catch(Exception e){
	e.printStackTrace();
}finally{
	try{
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		if(con != null) con.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	
}
		
		
%>
</table>
</body>
</html>