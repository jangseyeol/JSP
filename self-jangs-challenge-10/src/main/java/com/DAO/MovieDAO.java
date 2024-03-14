package com.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.VO.MovieVO;
import com.util.DBManager;

public class MovieDAO {

	private static MovieDAO instance = new MovieDAO();
	
	private MovieDAO() {
		
	}
	
	public static MovieDAO getInstance() {
		return instance;
	}
	
	public List<MovieVO> selectAllMovies(){
		
		String sql = "select * from movie order by code desc";
		
		List<MovieVO> list = new ArrayList<MovieVO>();
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setCode(rs.getInt("code"));
				vo.setTitle(rs.getString("title"));
				vo.setPrice(rs.getInt("price"));
				vo.setDirector(rs.getString("director"));
				vo.setActor(rs.getString("actor"));
				vo.setPoster(rs.getString("poster"));
				vo.setSysnopsis(rs.getString("synopsis"));
				list.add(vo);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBManager.close(rs, pstmt, con);
		}
		
		return list;
	}

	public void insertMovies(MovieVO vo) {
		String sql = "insert into movie values(movie_seq.nextval, ?,?,?,?,?,?)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBManager.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getPrice());
			pstmt.setString(3, vo.getDirector());
			pstmt.setString(4, vo.getActor());
			pstmt.setString(5, vo.getPoster());
			pstmt.setString(6, vo.getSysnopsis());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(pstmt, con);
		}
		
	}
}
