// 데이터베이스 테이블과 연동해서 작업하는 데이터베이스 처리 클래스
/* 
 * DAO(Data Access Object)는 데이터베이스의 데이터에 접근하기 위한 객체입니다.
 * 데이터베이스 레코드의 조회, 추가, 수정, 삭제 역할을 합니다.
 * DAO는 데이터베이스에서 얻은 데이터를 VO에 저장합니다.
 * 데이터베이스 데이터를 VO객체로 얻어오거나 VO객체에 저장된 값을 데이터베이스에 추가한다.
 * 매번 이런 작업을 위해서 객체를 생성하기보다는 싱글톤 패턴으로 클래스를 설계한다.
 * 싱글톤 패턴은 인스턴스가 오로지 단 하나만 존재할 수 있도록 클래스를 설계하는 것을 말한다.
 * 싱글톤은 객체를 메모리에 단 한 번만 올려놓고 시스템 전반에 걸쳐서 특정한 자원을 공유할 때 사용한다.
 * 
 * 싱글톤 패턴 클래스는 메모리 낭비를 막기 위해서 만드는 것이다.
 * new연산자를 클래스 앞에 기술하여 객체를 생성하면 메모리를 할당받게 된다.
 * new연산자를 사용할 때마다 동일한 형태의 객체가 계속 만들어진다.
 * 이를 막기 위해서 생성자를 외부에서 호출하지 못하도록 private 접근 제한자로 선언하고 대신 클래스 선언부 내부에서 자신이 객체를 생성한 후 이렇게 생성된
 * 오로지 한 개의 객체를 getInstance() 메소드로 접근해서 사용할 수 있도록 한다. 
 * 이는 오로지 한 번의 객체 생성으로 메모리를 효율적으로 관리하려는 목적이다.
 * 
 * 결국, 싱글톤 형태의 클래스 객체를 생성하려면 private 접근 제한자로 선언한 생성자 때문에
 * new 연산자를 사용하지 못하고
 * getInstance() 메소드로 대신 클래스 선언부 내부에서 자신이 생성한 객체를 얻어 와야 한다.
 * 
 * DAO는 데이터 접근이 목적인 객체이다.
 * 즉, 데이터베이스에 들어 있는 데이터를 어떻게 이용할지에 초점을 맞추어 설계하는 클래스이다.
 * 데이터베이스에 저장된 정보를 얻어 오거나 전달하기 위해서 테이블에 저장된 데이터를 VO 객체 단위로 저장해서 사용한다.
 * 
 * 데이터베이스에 저장된 회원 정보는 새롭게 추가되거나 조회되고 수정되어야 하는데, 이러한 작업을 모두 DAO클래스에서 한다.
 * DAO클래스에서 이러한 작업이 원활하게 이루어지도록 하기 위한 메소드는 다음과 같다.
 * 
 * 1.int userCheck(String userid, String pwd) 
 * 사용자 인증시 사용하는 메소드이다. member테이블에서 아이디와 암호를 비교해서 해당 아이디가 존재하지 않으면 -1을
 * 해당 아이디가 존재하고 암호가 다르면 0을,
 * 오두 일치하면 1을 리턴한다.
 * 
 * 2.MemberVO getMember(String userid)
 * member 테이블에서 아이디로 해당 회원을 찾아 회원 정보를 가져온다.
 * 
 * 3.int confirmID(String userid)
 * 회원 가입 시 아이디 중복을 확인할 때 사용한다. 해당 아이디가 있으면 1을 없으면 -1을 리턴한다.
 * 
 * 4.void insertMember(MemberVO mVo)
 * 매개 변수로 받은 VO 객체를 member테이블에 삽입한다.
 * 
 * 5.void updateMember(MemberVO mVo)
 * 매개 변수로 받은 VO 객체 내의 아이디로 member테이블에서 검색해서 VO객체에 저장된 정보로 회원 정보를 수정한다.
 * */

package com.saeyan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.saeyan.dto.MemberVO;

public class MemberDAO{
	
	private static MemberDAO instance = new MemberDAO();
	//다른 클래스에서는 절대 인스턴스를 생성하지 못하고 자기 자신만 인스턴스를 생성할 수 있다.
	//생성된 인스턴스는 외부에서 접근할 수 없다.
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private MemberDAO() {
		
	}
	//싱글톤 패턴이 되기 위한 첫번째 조건은 생성자가 private이어야 한다는 것이다.

	public static MemberDAO getInstance() {
		return instance; //MemberDAO 객체를 리턴합니다.
	}
	//생성된 인스턴스는 외부에서 수정은 못하고 값을 얻을 수만 있도록 read only property로 만들기 위해서 setter는 정의하지 않고 getter만 만든다.
	
	public Connection getConnection() throws Exception{
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "ezen";
		String password = "1234";
		
		//1.드라이브 로드
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2.DB연결
		return DriverManager.getConnection(url,user,password);
	}
	
	public int confirmID(String userid) {
		//회원 가입 시 아이디 중복을 확인할 때 사용한다. 해당 아이디가 있으면 1을 , 없으면 -1을 리턴한다.
		int result = -1;
		String sql = "select userid from member2 where userid= ?";
		try {
			//1. 연결
			con = getConnection();
			//2. sql 문 전송
			pstmt = con.prepareStatement(sql);
			//3. 맵핑
			pstmt.setString(1, userid);
			//4. 실행 및 결과값 받기
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result=1;
			}else {
				result=-1;
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

	public int insertMember(MemberVO vo) {
		//회원 가입을 위한 메소드로 회원 가입 폼에서 입력받은 회원 정보를 매개 변수로 전달받는다.
		int result = -1;
		
		String sql = "insert into member2 values(?, ?, ?, ?, ?, ?)";
		//회원 정보를 member 테이블에 삽입하기 위한 insert문이다.
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				//1.연결
				con = getConnection();
				//2.sql구문 전송
				pstmt = con.prepareStatement(sql);
				//3.맵핑
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getUserid());
				pstmt.setString(3, vo.getPwd());
				pstmt.setString(4, vo.getEmail());
				pstmt.setString(5, vo.getPhone());
				pstmt.setInt(6, vo.getAdmin());
				//컬럼에 저장할 값은 매개 변수로 받은 VO객체에서 얻어와 바인딩한다.
				
				//4. 구문실행
				result = pstmt.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	
	}

	public int userCheck(String userid, String pwd) {
		//사용자 인증시 사용하는 메소드입니다. 이 메소드는 회원 아이디와 암호를 전달받습니다.
		// 회원이 존재하면 1을, 존재하지 않으면 -1을 리턴합니다.
		int result = -1;
		//아이디가 일치하는 회원이 존재하지 않으면 result에 -1을 저장합니다.
		//result에 초기값 -1을 주어 일치하는 회원이 없는 것을 가정하고 시작합니다.
		String sql = "select pwd from member2 where userid = ?";
		//로그인 폼에서 입력받은 아이디로 member테이블을 조회하기 위해 where절에서 userid로 검색하여 암호를 얻어온다.
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			//select의 ?에 매개변수로 받아온 아이디를 바인딩시킨다.
			rs = pstmt.executeQuery();
			//쿼리문을 실행하여 결과값을 ResultSet 객체인 rs에 저장한다.
			if(rs.next()) {
				if(rs.getString("pwd") != null && rs.getString("pwd").equals(pwd)) {
					result = 1;
			//아이디가 일치하는 행(row)이 존재하면 rs.next()가 true 이므로 매개 변수로 받아온 암호와 디비에 저장된 암호( rs.getStirng("pwd"))가 일치하는지 체크하여
			//일치하면 result에 1을 저장한다.
				}else {
					result = 0;
					//아이디가 일치하지만 암호가 불일치하면 result에 0을 저장합니다.
				}
			}else {
				result = -1;
				//해당 아이디가 존재하지 않으면 result에 -1을 저장합니다.
			}
		}catch(Exception e){
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

	public MemberVO getMember(String userid) {
		//아이디가 일치하는 멤버의 정보를 얻어오는 메소드입니다.
		String sql = "select * from member2 where userid=?";
		MemberVO vo = new MemberVO();
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setName(rs.getString("name"));
				vo.setUserid(rs.getString("pwd"));
				vo.setPwd(rs.getString("pwd"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAdmin(rs.getInt("admin"));
			}
			//아이디가 일치하는 로우가 존재하면 VO객체에 디비에 저장된 회원 정보를 채운다.
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
	
	
	public int updateMember(MemberVO vo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?,"
				+ "phone=?, admin=? where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setInt(4, vo.getAdmin());
			pstmt.setString(5, vo.getUserid());
			result = pstmt.executeUpdate();
		}catch(Exception e) {
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
}