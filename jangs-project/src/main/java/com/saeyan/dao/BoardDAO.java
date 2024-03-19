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

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
		
	}

	public static BoardDAO getInstance() {
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
	
	//select 수행한 후 리소스 해제를 위한 메소드
	private void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(rs != null)rs.close();
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//insert, update, delete 수행한 후 리소스 해제를 위한 메소드
	private void close(Connection con, Statement stmt) {
		try {
			if(stmt != null)stmt.close();
			if(con != null)con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// 전체 데이터 가져오기
	public List<BoardVO> selectAllBoards(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board order by num desc";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
		
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNum(rs.getInt("num"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadCount(rs.getInt("readcount"));
				vo.setWriteDate(rs.getTimestamp("writedate"));
				
				list.add(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return list;
	}
	
	//단건데이터 가져오기
	public BoardVO selectBoardByNum(int num){
		BoardVO vo = new BoardVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
		//한개 넘어오기때문에
			if(rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadCount(rs.getInt("readcount"));
				vo.setWriteDate(rs.getTimestamp("writedate"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return vo;
	}
	
	//
	public BoardVO checkPassWord(String pass, int num){
		BoardVO vo = new BoardVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from board where pass =? and num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pass);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setPass(rs.getString("pass"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setReadCount(rs.getInt("readcount"));
				vo.setWriteDate(rs.getTimestamp("writedate"));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return vo;
	}
	
	//조회수 증가
	public void updateReadCount(int num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "update board set readcount = readcount+1 where num = ?";
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}
	
	
	public void insertBoard(BoardVO vo) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "insert into board(num, pass, name, email, title, content) "
				+ " values(board_seq.nextval,?,?,?,?,?)";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getPass());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5, vo.getContent());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}
	
	public void updateBoard(BoardVO vo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update board set name=?, pass=?, email=?, title=?, content=? where num=?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPass());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getTitle());
			pstmt.setString(5, vo.getContent());
			pstmt.setInt(6, vo.getNum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}


	public void deleteBoard(int num) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		String sql = "delete from board where num = ?";
		try {
			con = getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
	}


}
