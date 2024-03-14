package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.ProductVO;

import util.DBManager;

public class ProductDAO {
	
	private static ProductDAO instance = new ProductDAO();
	
	
	private ProductDAO() {
		
	}

	//ProductDAO객체를 리턴한다.
	public static ProductDAO getInstance() {
		return instance;
	}
	
	//최근 등록한 상품을 먼저 출력한다.
	public List<ProductVO> selectAllProducts(){
		//최근에 넣은 것 부터 보여준다.
		String sql = "select * from product order by code desc";
		
		//객체 생성
		//상품 정보를 한 개 이상 저장하기 위해서 ArrayList 클래스로 객체 생성을 하였다.
		List<ProductVO> list = new ArrayList<ProductVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//DB연결
			//커넥션 객체를 얻기 위해 DBManager 의  getConnection() 메소드를 호출한다.
			con = DBManager.getConnection();
			
			//SQl구문 전송
			//커넥션 객체로부터 스테이트먼트객체를 얻어온다.
			//그리고  위의 sql 변수를 전달해준다.
			pstmt = con.prepareStatement(sql);
			
			//sQl 실행
			//쿼리문을 실행한 후 이 결과를 Resultset 객체에 실어둔다.
			rs = pstmt.executeQuery();
			
			//1개 가 아니라서...
			//ResultSet 객체는 여러 개의 상품 정보를 저장하고 있기 때문에 while(반복문)을 돌면서 ResultSet 객체인 rs의 next()메소드를 호출하여 행(로우)단위로 이동하면서
			//상품 테이블에 접근한다.
			while(rs.next()) {
				//ProductVO 클래스 객체로 담아서 실행할 것
				//rs의 next()메서드의 리턴값이 true이면 이동한 행에 데이터 상품 정보가 존재한다는 의미이기 때문에 while 문 내부에서 첫 번째 할 일은 상품 정보를 저장할
				// ProductVO 객체를 생성하는 것이다. 이렇게 생성된 ProductVO 객체에는 아직 데이터가 저장되어 있지 않기 때문에 각 필드에 null 혹은 0으로 채워져 있다.
				ProductVO vo = new ProductVO();
				
				//객체에 담아라.
				//code 커럼은 number형이기 때문에 getInt() 메소드를 호출한다. 
				//이렇게 얻어온 컬럼 값은 ProductVO 객체의 code필드에 저장하기 위해서 setCode()메소드를 호출한다.
				vo.setCode(rs.getInt("code"));
				//product 테이블에서 상품명을 얻어오자. 상품명을 저장한 name 컬럼은 VARCHAR2로 정의되어 있기 때문에 ResultSet 객체의 getString() 메소드를 호출한다.
				//이렇게 얻어온 값을 ProductVO 객체의 setName()으로 name필드에 채운다.
				vo.setName(rs.getString("name"));
				//같은 방식으로 price, pictureUrl, description 컬럼 값을 얻어온다. price컬럼은 Number이기 때문에 getInt()로, 
				//pictureUrl,description 컬럼은 VARCHAR2형이기 때문에 getString()으로 컬럼 값을 얻어온다.
				//이렇게 얻어온 값을 ProductVO 객체의 setter를 호출하여 ProductVO 객체의 각 필드에 채운다.
				vo.setPrice(rs.getInt("price"));
				vo.setPictureUrl(rs.getString("pictureUrl"));
				vo.setDescription(rs.getString("description"));
				//list 에 집어넣어라
				//ArrayList 객체에 ProductVO 객체를 추가한다.
				list.add(vo);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		//list로 값(vo)을 리턴
		//Product테이블의 모든 정보가 ArrayList 객체에 저장되었으므로 이를 리턴한다.
		return list;
	}
	
	//전달인자로 받은 VO객체를 product 테이블에 삽입한다.
	public void insertProduct(ProductVO vo) {
		String sql = "insert into product values(product_seq.nextval, ?,?,?,?)";
		//데이터베이스의 product 테이블에 상품 정보를 추가하기 위한 insert문을 문자열 변수 sql에 저장해 둔다.
		//상품 코드는 시퀀스에서 발급받기 위해서 product_seq.nextval로 지정한다.
		//상품명, 가격, 이미지, 설명은 productWrite.jsp의 입력 폼에서 입력 받은 값을 지정하기 위해서 바인딩 변수인 ?로 지정한후에 
		//insert문을 실행하기 전에 바인딩 변수인 ?에 값을 채운다.
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			//쿼리문을 실행하기 전에 커넥션 객체를 얻기 위해서 DBManager 클래스의 정적(static)메소드인 getConnection()을 호출한다.
			pstmt = con.prepareStatement(sql);
			//쿼리문을 실행하기 위한 스테이트먼트 객체가 필요하다.
			//커넷션 객체로부터 스테이트먼트 객체를 얻어온다. 
			//스테이트먼트 객체를 얻기 위한 perpareStatement() 메소드에 sql변수를 전달한다.
			
			//productWrite.jsp의 입력 폼에서 입력 받은 값을 ProductWriteServlet.java 서블릿 클래스의 doPost() 메소드에서 얻어와서 이를 ProductVO 객체의 필드에
			//채워 넣은 다음 이를 ProductDAO 객체의 insertProduct() 메소드에 전달해 준다.
			//ProductDAO 객체에서 상품명만 얻어오기 위해서 getName() 메소드를 호출한다.
			//이렇게 얻은 상품명을 첫 번째 바인딩 변수(?)에 채워 넣는다.
			//상품명을 저장하는 name 필드는 VARCHAR2형이기 때문에 PreparedStatement 객체의 setString() 메소드로 바인딩시킨다.
			pstmt.setString(1, vo.getName());
			//insertProduct()메소드의 매개 변수인 ProductVO 객체의 getPrice() 메소드를 호출하여 productWrite.jsp의 입력 폼에서 입력 받은 가격을 두 번째 바인딩 변수(?)
			//에 채워 넣는다. 가격을 저장하는 price필드는 NUMBER형이기 때문에 PreparedStatement 객체의 setInt() 메소드로 바인딩시킨다.
			pstmt.setInt(2, vo.getPrice());
			//파일명과 상품 설명 역시 ProductVO 객체로부터 값을 얻어와 PreparedStatement 객체의 setter로 바인딩시킨다.
			pstmt.setString(3, vo.getPictureUrl());
			pstmt.setString(4, vo.getDescription());
			
			//sql 변수의 4개의 바인딩 변수에 ProductVO객체로부터 얻어온 값을 모두 채운 후에 PreparedStatement 객체의 executeUpdate() 메소드를 호출하여
			//insert 문을 실행시켜 데이터베이스에 상품 정보를 추가한다.
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con,pstmt);
		}
	}
	
	//아이디 중복 확인을 한다. 해당 아이디가 있으면 1을 없으면 -1을 리턴한다.
	public int confirmID(String userid) {
		return 0;
	}
	
	//사용자 인증시 사용하는 메소드이다. product 테이블에서 아이디와 암호를 비교해서 해당 아이디가 존재하지 않으면 -1을, 아이디만 일치하고 암호가 다르면 0을
	// 모두 일치하면 1을 리턴한다.
	public int userCheck(String userid, String pwd) {
		return 0;
	}
	
	//product 테이블에서 상품 코드로 해당 상품을 찾아 상품 정보를 ProductVO 객체로 얻어준다.
	public ProductVO selectProductByCode(int code) {

		String sql = "select * from product where code= ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProductVO vo = new ProductVO();
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, code);
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				vo.setCode(rs.getInt("code"));
				vo.setName(rs.getString("name"));
				vo.setPrice(rs.getInt("price"));
				vo.setPictureUrl(rs.getString("pictureurl"));
				vo.setDescription(rs.getString("description"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
		
		return vo;
	}
	
	//매개 변수로 받은 VO객체 내의 코드로 product 테이블에서 검색해서 VO객체에 저장된 정보로 상품 정보를 수정한다.
	public void updateProduct(ProductVO vo) {
		String sql = "update product set name=?, price=?, pictureurl=?, "
				+ "description=? where code=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getPictureUrl());
			pstmt.setString(4, vo.getDescription());
			pstmt.setInt(5, vo.getCode());
			
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con,pstmt);
		}
	}
	
	public void deleteProduct(int code) {
		
		String sql = "delete product where code = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con,pstmt);
		}
	}
}


	