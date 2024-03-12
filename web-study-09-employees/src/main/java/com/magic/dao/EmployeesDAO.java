package com.magic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.magic.dto.EmployeesVO;

public class EmployeesDAO{
	private static EmployeesDAO instance = new EmployeesDAO();
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private EmployeesDAO() {
		
	}
	
	public static EmployeesDAO getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ezen";
		String password = "1234";
		
		//1. 드라이브 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. DB연결
		return DriverManager.getConnection(url, user, password);
		
	}

	public int userCheck(String userid, String pwd, String admin) {
		int result = -1;
		
		String sql = "select * from employees where id = ?";
		
		//result => -1(아이디불일치), 0(비번불일치), 1(admin불일치), 2(A), 3(B)
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pass").equals(pwd)) {
					if(admin.equals(rs.getString("lev"))) {
						result = admin.equals("A") ? 2 : 3;
					}else {
						result = 1;
					}
				}else {
					result = -1;
				}
			}else {
				result = 0;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public EmployeesVO getMember(String userid) {
		String sql = "select*from employees where id=?";
		
		EmployeesVO vo = new EmployeesVO();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String name = rs.getString("name");
				
				vo.setName(name);
				vo.setId(rs.getString("ID"));
				vo.setPass(rs.getString("pass"));
				vo.setLev(rs.getString("LEV"));
				vo.setEnter(rs.getDate("ENTER"));
				vo.setGender(rs.getString("gender"));
				vo.setPhone(rs.getString("PHONE"));
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return vo;
	}
	
	
}
