<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String username = "ezen";
	String password = "1234";
	
	String sql = "select * from employee";
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="600" border ="1">
		<tr>
			<th>이름</th><th>주소</th><th>ssn</th>
		</tr>
		<%
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url,username,password);
		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			out.println("<tr>");
			out.println("<td>" + rs.getString("name")+"</td>");
			out.println("<td>" + rs.getString("address")+"</td>");
			out.println("<td>" + rs.getString("ssn")+"</td>");
			out.println("</tr>");
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(rs != null)rs.close();
				if(pstmt != null)pstmt.close();
				if(con != null) con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		%>
	
	</table>
</body>
</html>