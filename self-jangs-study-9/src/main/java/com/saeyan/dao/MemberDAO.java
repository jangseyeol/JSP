//DAO(Data Access Object)는 데이터베이스의 데이터에 접근하기 위한 객체이다.
//데이터베이스 레코드의 조회, 추가, 수정, 삭제 역할을 한다.
//데이터베이스 접근을 담당하기 때문에 이 클래스에 DAO란 명칭을 붙인 것이다.
//DAO는 데이터베이스에서 얻은 데이터를 VO에 저장한다.

//DAO의 주된 역할은 데이터베이스 데이터를 VO객체로 얻어오거나(set) VO객체에 저장된 값을 데이터베이스에 추가한다.(get)
//매번 이런 작업을 위해서 객체를 생성하기보다는 싱글톤 패턴으로 클래스를 설계한다.
//싱클톤 패턴은 인스턴스가 오로지 단 하나만 존재할 수 있도록 클래스를 설계하는 것을 말한다.
//싱글톤은 객체를 메모리에 단 한 번만 올려놓고 시스템 전반에 걸쳐서 특정한 자원(Object, Module,Component)를 공유할 때 사용한다.

package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.saeyan.dto.MemberVO;

public class MemberDAO {
	
	//싱글톤 패턴이 되기 위한 첫 번째 조건은 생성자가 private여야 한다는 것이다.
	private MemberDAO() {
		
	}
	
	
	//다른 클래스에서는 인스턴스를 생성하지 못하고 자기 자신만 인스턴스를 생성할 수 있다.
	private static MemberDAO instance = new MemberDAO();
	
	//이렇게 생성된 인스턴스는 외부에서 수정은 못하고 값만 얻을 수 있도록 readonly property로 만들기 위해서
	//setter는 정의하지 않고 getter만 만든다.
	
	public static MemberDAO getinstance() {
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user= "ezen";
		String password = "1234";
		
		//1.드라이브 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. DB연결
		return DriverManager.getConnection(url, user, password);
	}
	
	public int userCheck(String userid, String pwd) {
//사용자 인증시 사용하는 메소드입니다. 이 메소드는 회원 아이디와 암호를 전달 받습니다. 회원이 존재하면 1을, 존재하지 않으면 -1을 리턴하기에 이 메소드의 결과값은 -1입니다.
		int result = -1;
		//result 값에 -1을 주어 일치하는 회원이 없는 것을 가정하고 시작합니다.
		String sql = "select pwd from member where userid=?";
		//로그인 폼에서 입력받은 아이디로 member 테이블을 조회하기 위해서 where 절에서 userid로 검색하여 암호를 얻어옵니다.
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			//위에 sql 문의 ?에 매개변수로 받아온 아이디를 바인딩시킨다.
			rs = pstmt.executeQuery();
			//쿼리 문을 실행하여 결과값을 ResultSet 객체인 rs에 저장한다. (데이터베이스의 값이 rs에 저장된다)
			//executeQuery()문은 select (찾을 때) 사용된다.
			if(rs.next()) {
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
					result = 1;
					//아이디가 일치하는 행이 존재하면 rs.next() 가 true 이기 때문에 매개변수로 받아온 암호와 디비에 저장된 암호(rs.getString("pwd")가 일치하는지 체크하여
					//일치하면 result 에 1을 저장한다.
				} else {
					result = 0;
					//비밀번호가 일치하지 않는다.
				} 
			}else {
				result = -1;
				//아이디 자체가 없다...
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//아이디로 회원 정보 가져오는 메소드
	public MemberVO getMember(String userid) {
		MemberVO mVo = null;
		String sql = "select * from member where userid=?";
		//아이디를 입력하면 그 정보를 다 가지고 오는 문구
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				mVo = new MemberVO();
				mVo.setName(rs.getString("name"));
				//VO객체인 mVo 에 rs를 이용하여 데이터베이스에 있는 name 을 가지고 와서 VO객체에 저장한다.
				mVo.setUserid(rs.getString("userid"));
				//rs를 이용하여 데이터베이스에서 userid값을 가지고 와서, VO객체에 저장한다.
				mVo.setPwd(rs.getString("pwd"));
				//rs를 이용하여 데이터베이스에서 pwd값을 가지고 와서 DAO에서 사용할 수 있도록 pwd값을 VO에 저장하는 것
				mVo.setEmail(rs.getString("email"));
				//데이터베이스에서 email 값을 가지고 와서 VO클래스 의 setEmail을 통해 email값을 저장해 놓는 것
				mVo.setPhone(rs.getString("phone"));
				//데이터베이스에서 phone값을 가지고 와서(get) VO객체로 생성된 mVo변수에 phone값을 저장해 놓는것(setPhone)
				mVo.setAdmin(rs.getInt("admin"));
				//회원이 운영자인지 일반회원인지 구분하기 위한 admin값을 가지고 와서 mVo에 setAdmin을 통해 저장해놓는것
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return mVo;
	
	}
	
	
	//아이디 중복 체크를 위해서는 데이터베이스에 존재하는 아이디인지를 점검하기 위한 메소드를 DAO에 추가해야 한다.
	
	public int confirmID(String userid) {
		int result= -1;
		String sql = "select userid from member where userid = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
				//아이디가 같은 것이 있으면 1
			}else {
				result = -1;
				//아이디가 같은 것이 없으면 -1
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//회원 가입을 위한 메소드로 회원 가입 폼에서 입력받은 회원 정보를 매개 변수로 전달받습니다.
	public int insertMember(MemberVO mVo) {
		int result = -1;
		String sql = "insert into member values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			//vo에 저장되이 있는 Name 을 pstmt, sql 문을 통해 데이터베이스에 저장한다.(set) 
			pstmt.setString(1, mVo.getName());
			//vo에 저장되어 있는 Userid을 얻어와서 이를 데이터베이스에 set 한다.
			pstmt.setString(2, mVo.getUserid());
			//vo에 담겨 있는 pwd를 DAO객체를 통해 데이터베이스에 sql문 형식으로 insert 된다.
			pstmt.setString(3, mVo.getPwd());
			//VO객체에 담겨 있는 mVo 매개변수를 통해 email을 가져와서, pstmt 를 통해 sql 문으로 데이터베이스에 저장된다.
			pstmt.setString(4, mVo.getEmail());
			//Vo객체를 통헤서 DAO 클래스에 저장된 phone 정보를 pstmt 를 통해 데이터베이스에 저장한다.(set)
			pstmt.setString(5, mVo.getPhone());
			//Vo객체를 통해 DAO 클래스에 set되어진 admin 정보를 get으로 가져와서 pstmt를 통해 데이터베이스에 set한다.
			pstmt.setInt(6, mVo.getAdmin());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//회원 정보 테이블 내의 특정 행의 값을 변경하는 메소드입니다.
	public int updateMember(MemberVO mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?, "
				+ "phone=?, admin=? where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn= getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getUserid());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return result;		
	}
	
}

//만들게 되는 메소드
//int userCheck(String userid, String pwd) 
//사용자 인증시 사용하는 메소드이다. member 테이블에서 아이디와 암호를 비교해서 해당 아이기가 존재하지 않으면 -1 을
// 아이디만 일치하고 암호가 다르면 0 을 
// 모두 일치하면 1을 리턴한다.

//MemberVO getMember(String userid)
//member 테이블에서 아이디로 해당회원을 찾아 회원 정보를 가져온다.

//int comfirmID(String userid)
//회원 가입 시 아이디 중복을 확인할 때 사용한다.
//해당 아이디가 있으면 1을, 없으면 -1을 리턴한다.

//void insertMember(MemberVO mVo)
//매개변수로 받은 VO 객체를 member 테이블에 삽입한다.

//void updateMember(MemberVO mVo)
//매개 변수로 받은 VO 객체 내의 아이디로 member테이블에서 검색해서 VO객체에 저장된 정보로 회원 정보를 수정한다.