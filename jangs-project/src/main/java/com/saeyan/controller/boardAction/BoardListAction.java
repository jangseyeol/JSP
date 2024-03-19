package com.saeyan.controller.boardAction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

//게시글 리스트를 위한 액션 클래스
public class BoardListAction implements BoardAction{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "/board/boardList.jsp";
		
		BoardDAO bDao = BoardDAO.getInstance();
		
		List<BoardVO> list = bDao.selectAllBoards();
		/*
		 * public List<BoardVO> selectAllBoards(){
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
		 */

		request.setAttribute("boardList", list);
		//					   속성 이름,    속성 값
		//request객체는 그대로 유지가 되기 때문에 setAttribute메소드로 request객체에 속성 값을 저장해서 보내준다.
		request.getRequestDispatcher(url).forward(request, response);
	}

}
