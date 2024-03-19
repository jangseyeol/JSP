package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.LoginVO;

public class LoginDAO {
	
	private static LoginDAO instance = new LoginDAO();
	
	private LoginDAO() {
		
	}

	public static LoginDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() {
		Connection conn = null;
		try {
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
		conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;	
	}
	
	private void lClose(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//insert, update, delete 수행한 후 리소스 해제를 위한 메소드
	private void lClose(Connection con, Statement stmt) {
		try {
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 체크
	public int userCheck(String id, String pwd, String lev) {
		String sql = "select * from employees where id = ?";
		int result = -1;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getConnection();
		    pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				if(pwd.equals(rs.getString("pass"))) {
					if(lev.equals(rs.getString("lev"))) {
						result = lev.equals("A") ? 2 : 3;
					}else {
						result = 1; //등급 상이
					}
				}else {
					result = 0; //비밀번호 상이
				}
				
			}else {
				result = -1;  //아이디 상이
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
			lClose(con, pstmt, rs);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	
	//id 해당하는 전체 데이타 가져오기
		public LoginVO getMember(String id) {
			String sql = "select * from employees where id = ?";
			LoginVO vo = new LoginVO();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
			    pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					//      rs.getString("여기이름은 데이타베이스 필드명"
					vo.setId(rs.getString("id"));
					vo.setPass(rs.getString("pass"));
					vo.setName(rs.getString("name"));
					vo.setLev(rs.getString("lev"));
					vo.setEnter(rs.getDate("enter"));  //날짜
					vo.setGender(Integer.parseInt(rs.getString("gender")));
					vo.setPhone(rs.getString("phone"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					lClose(con, pstmt, rs);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return vo;
		}
		
		public void insertMember(LoginVO vo) {
			String sql = "insert into employees(id,pass,name,lev,enter,gender,phone) "
					+ "values(?,?,?,?,sysdate,?,?)";
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getId());
				pstmt.setString(2, vo.getPass());
				pstmt.setString(3, vo.getName());
				pstmt.setString(4, vo.getLev());
				pstmt.setString(5, "" + vo.getGender()); //1 ==> "1"
				pstmt.setString(6, vo.getPhone());
				
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					lClose(con, pstmt);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
		public int updateMember(LoginVO vo) {
			String sql = "update EMPLOYEES set pass=?, name=?, lev=?, gender=? "
					+ ", phone=? where id=?";
			int result = -1;

			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, vo.getPass());
				pstmt.setString(2, vo.getName());
				pstmt.setString(3, vo.getLev());
				pstmt.setString(4, String.valueOf(vo.getGender()));
				pstmt.setString(5, vo.getPhone());
				pstmt.setString(6, vo.getId());
				
				result = pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally{
				try {
					lClose(con, pstmt);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			
			return result; 
		}
		
		public int confirmID(String id) {
			int result = -1;
			String sql = "select id from employees where id=?";
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = getConnection();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs= pstmt.executeQuery();
				if(rs.next()) {
					result = 1;
				}else {
					result = -1;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				lClose(con, pstmt, rs);
			}
			return result;
		}
}
