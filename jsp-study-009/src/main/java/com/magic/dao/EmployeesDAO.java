package com.magic.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.magic.EmployeesVO;

public class EmployeesDAO {

	private static EmployeesDAO instance = new EmployeesDAO();
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private EmployeesDAO() {
		
	}
	
	public static EmployeesDAO getInstance() {
		return instance;
	}
	
	private Connection getConnection() throws Exception {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ezen";
		String password = "1234";
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection(url, user, password);
		
	}

	//로그인 체크
	//id, pwd, lev 확인해서 result 값에 따라 설정해준 메소드
	public int userCheck(String id, String pwd, String lev) {
		//DB연결
		String sql = "select * from employees where id=?";
		int result = -1;
		
		try{
			//db 전송
			con = getConnection();
			//sql과 비교
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				if(pwd.equals(rs.getString("pass"))) {
					if(lev.equals(rs.getString("lev"))) {
						result = lev.equals("A") ? 2 : 3;
						//아이디/비밀번호/A 이면 "2"
						//아이디/비밀번호/B 이면 "3"
					}else {
						result = 1; //등급(A(운영자) , B(일반회원))이 일치하지 않는다.
					}
				}else {
					result = 0; //비밀번호 틀릴 때
				}
				
			}else { // 여기에서의 else는 rs.next() 가 아니면... 이기 때문에 
					// 아이디가 틀릴 때이다. 왜냐하면 위에서 pstmt.setStirng(1, id); 를 했기 때문에
				result = -1; 
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

	//id에 해당하는 전체 데이터 가져오기
	//그리고 다시 보내줘야 함 그리서 VO 클래스 작성해야 함
	public EmployeesVO getMember(String id) {
		String sql = "select * from employees where id=?";
		EmployeesVO vo = new EmployeesVO();
		
		try{//db 전송
			con = getConnection();
			//sql과 비교
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//rs.getString("id") 에서 id 는 db에 있는 이름
				//rs.getString을 통해 id 가져와서 vo에 넣기
				vo.setId(rs.getString("id"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setLev(rs.getString("lev"));
				vo.setEnter(rs.getDate("enter"));
				vo.setGender(Integer.parseInt(rs.getString("gender")));
				vo.setPhone(rs.getString("phone"));
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

		return vo;

	}

	//마이페이지에서 mypageServlet, post방식을 통해 수정할 때 쓰는 메소드
	public int updateEmployee(EmployeesVO vo) {
		String sql = "update employees set pass=?, name=?, lev=?, gender=? "
				+ ", phone=? where id=?";
		int result = -1;

		try{
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

		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void insertEmployee(EmployeesVO vo) {
		String sql = "insert into employees(id,pass,name,lev,enter,gender,phone) "
				+ "values(?,?,?,?,sysdate,?,?)";

		try{
			con=getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getLev());
			pstmt.setString(5, ""+vo.getGender()); // 1 => "1"
			pstmt.setString(6, vo.getPhone());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}

	}
}

/*
String sql = "select * from employees where id=?";
int result = -1;

try{
	
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
*/