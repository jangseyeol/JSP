<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>입력 완료된 정보</h1>
<table width="800" border="1">
	<!-- <tr>
		<th></th><th></th><th></th>
	</tr> -->
	<%
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection con= null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "select * from item";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection(url, "ezen", "1234");
			
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				out.println("<tr>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("<td>" + rs.getInt("price") + "</td>");
				out.println("<td>" + rs.getString("description") + "</td>");
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